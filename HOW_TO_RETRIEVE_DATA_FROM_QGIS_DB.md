## Import database

To import a qgis database. With pgadmin, make a backup from qgis database and on dump options disable enable `Do not save` `Owner` and `Privilege`.

Then restore it in an other database. You could use Format `custom` and restore backup in the other database thanks pgadmin.

postgis installed in the OS is mandatory.

## Create latitude and longitude row

- https://gis.stackexchange.com/questions/95373/convert-geometry-to-latitude-longitude-using-postgis-st-transform
- https://gis.stackexchange.com/questions/145007/creating-geometry-from-lat-lon-in-table-using-postgis

```sql
qgis=# alter table batiments add column latitude real;
qgis=# alter table batiments add column longitude real;
qgis=# UPDATE batiments SET latitude = ST_Y (ST_Transform (geom, 4326));
qgis=# UPDATE batiments SET longitude = ST_X (ST_Transform (geom, 4326));
```

## Convert postgis database to normal database

1.  create latitude and longitude columns (see above)
2.  You could remove row without `fid` (i.e. feature id) and with only null informations
3.  if there are element with fid, and adresse but without latitude and longitude, complete it.
    Check all save with
    `sql select * from batiments where latitude is null; `

    We could extract and save in a file some request thanks

    ```sql
    sql \copy (select * from batiments where id=2927) to '/tmp/recordWithoutLatAndLong.csv' (for mat csv, header, delimiter ',');
    ```

4.  Delete batiments where latitude is null

    ```sql
    delete from batiments where latitude is null;
    ```

5.  Delete column `fid`

    ```sql
    ALTER TABLE batiments
    DROP COLUMN fid;
    ```

6.  Set columns latitude and longitude `NOT NULL`

```sql
ALTER TABLE batiments ALTER COLUMN latitude SET NOT NULL;
ALTER TABLE batiments ALTER COLUMN longitude SET NOT NULL;
```

7.  Backup again (see above), but this time with format "Plain"
8.  I advise to use a git project and commit file retrieved to check what we will delete

    1. Remove all lines with

    ```sql
    CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;
    COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';

    CREATE SEQUENCE public."batiments-paille-porteuse_id_seq"
        AS integer
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1;
    ALTER SEQUENCE public."batiments-paille-porteuse_id_seq" OWNED BY public.batiments.id;
    ALTER TABLE ONLY public.batiments ALTER COLUMN id SET DEFAULT nextval('public."batiments-paille-porteuse_id_seq"'::regclass);


    COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
    \.


    SELECT pg_catalog.setval('public."batiments-paille-porteuse_id_seq"', 2920, true);
    ```

9.  Restore the new sql file (mandatory with psql)
    ```sql
    create database load_bearing_straw_map;
    ```
    ```bash
    psql -U postgres -d load_bearing_straw_map < ~/Downloads/leniaw_pailleporteusesql/postgis_without_geo_column.sql
    ```
10. Check that postgis is not installed
    (see also https://wiki.archlinux.org/title/PostGIS)
    If installed version column is empty, it's cool!


    ```
    qgis=# \c load_bearing_straw_map
    You are now connected to database "load_bearing_straw_map" as user "postgres".
    load_bearing_straw_map=# -- verify available extensions
    SELECT name, default_version,installed_version
    FROM pg_available_extensions WHERE name LIKE 'postgis%' ;
              name          | default_version | installed_version
    ------------------------+-----------------+-------------------
     postgis                | 3.1.4           |
     postgis_raster         | 3.1.4           |
     postgis_topology       | 3.1.4           |
     postgis_tiger_geocoder | 3.1.4           |
    (4 rows)
    ```
