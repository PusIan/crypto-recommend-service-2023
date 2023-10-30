package com.xm.batchloader.config;

import com.xm.batchloader.service.StatRefreshService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {
    private final StatRefreshService statRefreshService;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Files were loaded");
            statRefreshService.refreshMonthStat();
            log.info("Month stat was refreshed");
            statRefreshService.refreshDayStat();
            log.info("Day stat was refreshed");
            log.info("Job execution completed successfully");
        } else {
            log.error("Job Execution Failed");
        }
    }
}
