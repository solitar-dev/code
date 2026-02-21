CREATE SEQUENCE IF NOT EXISTS url_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE urls
(
    id           BIGINT       NOT NULL,
    short_code   VARCHAR(255) NOT NULL,
    has_alias    BOOLEAN      NOT NULL,
    original_url TEXT         NOT NULL,
    click_count  BIGINT       NOT NULL,
    is_disabled  BOOLEAN      NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expires_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_urls PRIMARY KEY (id)
);

ALTER TABLE urls
    ADD CONSTRAINT uc_urls_shortcode UNIQUE (short_code);