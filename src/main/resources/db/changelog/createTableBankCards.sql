CREATE table "BankCard"(
    id_cards BIGINT primary key not null,
    card_number VARCHAR(255),
    card_holder VARCHAR(255),
    expiry_date DATE,
    status VARCHAR(255),
    balance double precision,
    transaction_history VARCHAR(255)
)


