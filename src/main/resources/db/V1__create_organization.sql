CREATE TABLE public."Organization"
(
    name character varying(50) NOT NULL,
    inn integer,
    payment_account bigint,
    CONSTRAINT name_pk PRIMARY KEY (name)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."Organization"
    OWNER to postgres;