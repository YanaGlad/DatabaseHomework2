CREATE TABLE public."Nomenclature"
(
    name character varying(50) NOT NULL,
    inside_code integer,
    PRIMARY KEY (name)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."Nomenclature"
    OWNER to postgres;