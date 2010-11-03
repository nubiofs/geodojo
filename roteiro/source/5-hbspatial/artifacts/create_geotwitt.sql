CREATE TABLE geotwitt
(
  id serial NOT NULL,
  autor character varying(254),
  mensagem character varying(140),
  CONSTRAINT geotwitt PRIMARY KEY (id), 
  )
WITH (
  OIDS=FALSE
);
ALTER TABLE geotwitt OWNER TO postgres;

SELECT AddGeometryColumn('','geotwitt','the_geom','4326','POINT',2);