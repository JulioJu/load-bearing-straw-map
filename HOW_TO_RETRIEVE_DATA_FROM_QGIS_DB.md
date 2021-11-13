<!-- vim-markdown-toc GFM -->

- [Import remote database locally](#import-remote-database-locally)
- [Create latitude and longitude row](#create-latitude-and-longitude-row)
- [Convert PostGIS database to normal database](#convert-postgis-database-to-normal-database)
  - [Optional (not useful for JHipster migration)](#optional-not-useful-for-jhipster-migration)
  - [Convert table "batiments" to JHipster](#convert-table-batiments-to-jhipster)

<!-- vim-markdown-toc -->

## Import remote database locally

Before all, you should have PostgreSQL installed with postGIS and started (check official doc).

Dump database with

```sh
pg_dump db_name --host aHost --port 5432 --username aUsername --format tar --verbose --file "/tmp/filename.tar" --no-owner --no-privileges
```

To restore it, run

```sql
CREATE DATABASE db_name;
```

```bash
pg_restore --no-owner --dbname db_name --verbose /tmp/filename.tar
```

## Create latitude and longitude row

- https://gis.stackexchange.com/questions/95373/convert-geometry-to-latitude-longitude-using-postgis-st-transform
- https://gis.stackexchange.com/questions/145007/creating-geometry-from-lat-lon-in-table-using-postgis

```sql
ALTER TABLE batiments ADD COLUMN latitude real;
ALTER TABLE batiments ADD COLUMN longitude real;
UPDATE batiments SET latitude = ST_Y (ST_Transform (geom, 4326));
UPDATE batiments SET longitude = ST_X (ST_Transform (geom, 4326));
```

## Convert PostGIS database to normal database

1.  create latitude and longitude columns (see above)

2.  If there are elements with fid, and adresse but without latitude and longitude, complete it.
    Check all save with

    ```sql
    SELECT * FROM batiments WHERE latitude IS NULL; `
    ```

    To better access it, we could backup data as CSV then read it thanks LibreOffice. To extract, run:

    ```sql
    \COPY (SELECT * FROM batiments WHERE id=2927) TO '/tmp/recordWithoutLatAndLong.csv' (format csv, header, delimiter ',');
    ```

    Add latitude and longitude where you want

    ```sql
    UPDATE batiments
    SET latitude = 0, longitude = 0
    WHERE id = 1;
    ```

3.  Delete batiments where latitude is null

    ```sql
    DELETE FROM batiments WHERE latitude IS NULL;
    ```

4.  Set columns latitude and longitude `NOT NULL`

    ```sql
    ALTER TABLE batiments ALTER COLUMN latitude SET NOT NULL;
    ALTER TABLE batiments ALTER COLUMN longitude SET NOT NULL;
    ```

5.  DROP not useful columns

    ```sql
    -- mandatory because it needs postgis
    ALTER TABLE batiments DROP COLUMN geom;
    -- fid note useful, but not mandatory to drop it
    -- ALTER TABLE batiments DROP COLUMN fid;
    ```

### Optional (not useful for JHipster migration)

1.  Backup again (see above), but this time with format "Plain"

2.  I advise to use a git project and commit file retrieved to check what we will delete

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

3.  Restore the new sql file (mandatory with psql)

    ```sql
    create database load_bearing_straw_map;
    ```

    ```bash
    psql -U postgres -d load_bearing_straw_map < ~/Downloads/leniaw_pailleporteusesql/postgis_without_geo_column.sql
    ```

4.  Check that postgis is not installed
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

### Convert table "batiments" to JHipster

```zsh
set -e
psql -U postgres -c 'create database without_postgis_with_corrections'
psql -U postgres -d 'without_postgis_with_corrections' -f ../database_dump/database_created_by_roman_without_postgis_with_six_rows_corrected.sql
psql -U postgres -d 'without_postgis_with_corrections' -f ./migration-old-db-to-jhipster.sql
psql -U postgres -d 'carto_paille_porteuse' -c 'delete from batiment;'
pg_dump without_postgis_with_corrections --host localhost --port 5432 --username postgres --format plain --verbose --file "/tmp/data_batiment.sql" --table public.batiment --data-only
psql -U postgres -d 'carto_paille_porteuse' -f /tmp/data_batiment.sql
```

- Note:

  - See also https://www.jhipster.tech/jdl/intro)

  - To simply check schema created by JHipster or by QGIS, run for instance:
    ```sh
    pg_dump XXXXX --host localhost --port 5432 --username postgres --format plain --verbose --file "/tmp/schema_batiments.sql" --table public.batiments --schema-only --no-owner --no-privileges
    ```
  - _JDL `Enum` does not generate PostgreSQL type_
  - JDL type `String` create `varying(255)` PostgreSQL type, by default. Add `maxlength` change the number.
