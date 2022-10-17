CREATE TABLE shortcut (
    id            BIGSERIAL  NOT NULL PRIMARY KEY,
    binding_hash  TEXT       NOT NULL UNIQUE,
    binding       TEXT       NOT NULL UNIQUE,
    description   TEXT       NOT NULL,
    category      TEXT       NOT NULL,
    action        TEXT       NOT NULL
);

CREATE INDEX idx_shortcut_on_category ON shortcut USING btree (category);
