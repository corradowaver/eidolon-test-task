CREATE TABLE shortcut (
    id           BIGSERIAL  NOT NULL PRIMARY KEY,
    binding      TEXT       NOT NULL,
    description  TEXT       NOT NULL,
    category     TEXT       NOT NULL,
    action       TEXT       NOT NULL
);

CREATE INDEX udx_shortcut_on_category ON shortcut USING btree (category);
