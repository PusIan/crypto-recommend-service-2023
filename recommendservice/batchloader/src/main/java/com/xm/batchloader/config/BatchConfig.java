package com.xm.batchloader.config;

import com.xm.batchloader.BeanWrapperFieldSetMapperCustom;
import com.xm.batchloader.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableBatchProcessing
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final JobCompletionListener jobCompletionListener;

    @Value("${springbatch.resource}")
    private Resource[] inputFiles;

    @Bean
    public MultiResourceItemReader<Price> multiResourceItemReader() {
        MultiResourceItemReader<Price> reader = new MultiResourceItemReader<>();
        reader.setDelegate(flatFileItemReader());
        reader.setResources(inputFiles);
        return reader;
    }

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCryptoPriceFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(loadFiles())
                .listener(jobCompletionListener)
                .build();
    }

    @Bean
    public Step loadFiles() {
        return stepBuilderFactory
                .get("loadFiles")
                .<Price, Price>chunk(100)
                .reader(multiResourceItemReader())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<Price> flatFileItemReader() {
        FlatFileItemReader<Price> itemReader = new FlatFileItemReader<Price>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        return itemReader;
    }

    @Bean
    public LineMapper<Price> lineMapper() {
        DefaultLineMapper<Price> lineMapper = new DefaultLineMapper<Price>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"dt", "symbol", "price"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2});
        BeanWrapperFieldSetMapper<Price> fieldSetMapper = new BeanWrapperFieldSetMapperCustom<Price>();
        fieldSetMapper.setTargetType(Price.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<Price> writer() {
        JdbcBatchItemWriter<Price> itemWriter = new JdbcBatchItemWriter<Price>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO PRICES (dt, symbol, price) VALUES (:dt, :symbol, :price)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Price>());
        return itemWriter;
    }
}