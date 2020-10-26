CREATE TABLE public."Invoice"
(
    id integer,
    organization_date date,
    organization_name character varying(50),
    CONSTRAINT inv_id_pk PRIMARY KEY (id),
    CONSTRAINT org_fk FOREIGN KEY (organization_name)
        REFERENCES public."Organization" (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."Invoice"
    OWNER to postgres;