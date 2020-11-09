DELETE FROM history;
DELETE FROM securities;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO securities (id_on_exchange, name, reg_number, isin, emitent_title)
VALUES ('ACKO', 'АСКО-СТРАХОВАНИЕ ПАО ао', '1-01-52065-Z', 'RU000A0JXS91', 'Публичное акционерное общество "АСКО-СТРАХОВАНИЕ"'),
       ('AFKS', 'АФК "Система" ПАО ао', '1-05-01669-A', 'RU000A0DQZE3', 'ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО "АКЦИОНЕРНАЯ ФИНАНСОВАЯ КОРПОРАЦИЯ "СИСТЕМА"'),
       ('AFLT', 'Аэрофлот-росс.авиалин(ПАО)ао', '1-01-00010-A', 'RU0009062285', 'Публичное акционерное общество "Аэрофлот – российские авиалинии"');

INSERT INTO history (security_id, board_id, trade_date, num_trades, value, low, high, open, close, volume)
VALUES (100000, 'TQDE', '2020-04-15', 148, 497102, 4.04, 4.02, 3.94, 4.24, 121700),
       (100001, 'SMAL', '2020-04-15', 3, 203.61, 14.441, 13.776, 13.44, 14.441, 15),
       (100002, 'SMAL', '2020-04-15', 9, 1757.98, 77.36, 71.02, 71.02, 77.36, 24);



