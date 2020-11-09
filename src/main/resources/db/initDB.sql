DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS securities;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE securities
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    id_on_exchange  VARCHAR NOT NULL,
    name            VARCHAR NOT NULL,
    reg_number      VARCHAR,
    isin            VARCHAR,
    emitent_title   VARCHAR,
    CONSTRAINT sec_id_idx UNIQUE (id_on_exchange)
);


CREATE TABLE history
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    security_id     INTEGER NOT NULL,
    board_id        VARCHAR NOT NULL,
    trade_date      DATE NOT NULL,
    num_trades      DOUBLE PRECISION,
    value           DOUBLE PRECISION,
    low             DOUBLE PRECISION,
    high            DOUBLE PRECISION,
    open            DOUBLE PRECISION,
    close           DOUBLE PRECISION,
    volume          DOUBLE PRECISION,
    FOREIGN KEY (security_id) REFERENCES securities (id) ON DELETE CASCADE,
    CONSTRAINT history_security_id_board_id_trade_date_idx UNIQUE (security_id, board_id, trade_date)
);
