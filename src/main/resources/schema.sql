CREATE TABLE IF NOT EXISTS transactions (
  client_id INT NOT NULL,
  date DATE NOT NULL,
  currency VARCHAR(3) NOT NULL,
  amount DECIMAl NOT NULL
);