CREATE TABLE public."InvoicePositions"
(
    id integer,
    price bigint,
    nomenclature_name character varying(50) NOT NULL ,
    count integer,
    CONSTRAINT nomencl_fk FOREIGN KEY (nomenclature_name)
        REFERENCES public."Nomenclature" (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID

)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."InvoicePositions"
    OWNER to postgres;