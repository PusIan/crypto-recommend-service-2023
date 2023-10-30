insert into symbols (symbol, approved) (select symbol, approved from symbols where not exists (select 1 from symbols where symbol = 'BTC'));
insert into symbols (symbol, approved) (select symbol, approved from symbols where not exists (select 1 from symbols where symbol = 'DOGE'));
insert into symbols (symbol, approved) (select symbol, approved from symbols where not exists (select 1 from symbols where symbol = 'LTC'));
insert into symbols (symbol, approved) (select symbol, approved from symbols where not exists (select 1 from symbols where symbol = 'ETH'));
insert into symbols (symbol, approved) (select symbol, approved from symbols where not exists (select 1 from symbols where symbol = 'XRP'));