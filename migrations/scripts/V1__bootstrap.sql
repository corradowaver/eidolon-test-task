CREATE TABLE shortcuts (
    id           BIGSERIAL  NOT NULL PRIMARY KEY,
    binding      TEXT       NOT NULL,
    description  TEXT       NOT NULL,
    category     TEXT       NOT NULL,
    action       TEXT       NOT NULL
);

CREATE UNIQUE INDEX udx_shortcuts_on_category ON shortcuts USING btree (category);
