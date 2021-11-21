-- We use ../database_dump/database_created_by_roman_without_postgis_with_six_rows_corrected.sql
-- For security reasons, dump of the old db is not saved into public GitHub repository
--
-- psql -U postgres -c 'create database without_postgis_with_corrections'
-- psql -U postgres -d 'without_postgis_with_corrections' -f ./database_created_by_roman_without_postgis_with_six_rows_corrected.sql
-- psql -U postgres -d 'without_postgis_with_corrections' -f ./migration-old-db-to-jhipster.sql
-- -----------------------------------------------------------------------------

-- drop potential potential personal informations (we have no right to publish it)

alter table batiments drop column nom;

alter table batiments drop column contact_nom;

alter table batiments drop column contact_mail;

alter table batiments drop column contact_phone;

alter table batiments drop column description;

-- surface_plancher --------------------------------------------------------------

alter table batiments rename column surface to surface_plancher;

-- tailles_bottes --------------------------------------------------------------

alter type tailles_bottes rename value '80 x 120 cm' to 'T_70_X_120_X_230_CM';
alter type tailles_bottes rename value '50 x 80 cm' to 'T_50_X_80_X_110_a_200_CM';
alter type tailles_bottes rename value '37 x 47 cm' to 'T_36_X_46_X_70_a_120_CM';
alter type tailles_bottes rename value 'Autre' to 'AUTRE';

-- bottes_taille_autre

alter table batiments add column bottes_taille_infos varchar(255);

update batiments set bottes_taille_infos = '26 x 45 cm' where bottes_taille = '26 x 45 cm';
-- UPDATE 2

update batiments set bottes_taille = 'AUTRE' where bottes_taille = '26 x 45 cm';
-- UPDATE 2

-- YesNoPartial -----------------------------------------------------------------

alter type yes_no_partial rename value 'Oui' to 'OUI';
alter type yes_no_partial rename value 'Non' to 'NON';
alter type yes_no_partial rename value 'Partiel' to 'PARTIEL';

--- usage_batiment -------------------------------------------------------------

alter table batiments rename usage to usage_batiment;

alter type usage_batiment rename value 'Logement collectif' to          'LOGEMENT_COLLECTIF';
alter type usage_batiment rename value 'Logement individuel' to         'LOGEMENT_INDIVIDUEL';
alter type usage_batiment rename value 'Logement individuel groupé' to  'LOGEMENT_INDIVIDUEL_GROUPE';
alter type usage_batiment rename value 'Bâtiment administratif' to      'BATIMENT_ADMINISTRATIF';
alter type usage_batiment rename value 'Bâtiment commercial' to         'BATIMENT_COMMERCIAL';
alter type usage_batiment rename value 'Bâtiment industriel' to         'BATIMENT_INDUSTRIEL';
alter type usage_batiment rename value 'Bâtiment de loisirs' to         'BATIMENT_DE_LOISIRS';
alter type usage_batiment rename value 'Bâtiment de santé' to           'BATIMENT_DE_SANTE';
alter type usage_batiment rename value 'Bâtiment de retraite' to        'BATIMENT_DE_RETRAITE';
alter type usage_batiment rename value 'Bâtiment éducatif' to           'BATIMENT_EDUCATIF';
alter type usage_batiment rename value 'Bâtiment socio-culturel' to     'BATIMENT_SOCIO_CULTUREl';
alter type usage_batiment rename value 'Bâtiment agricole' to           'BATIMENT_AGRICOLE';
alter type usage_batiment rename value 'Ouvrage exceptionnel' to        'OUVRAGE_EXCEPTIONNEL';
alter type usage_batiment rename value 'Autre' to                       'AUTRE';

--- niveaux --------------------------------------------------------------------

update batiments set niveaux = 1 where niveaux = 0;
-- UPDATE 4

update batiments set niveaux = 2 where niveaux = 3;
-- UPDATE 1

-- concepteurs / réalisateurs ------------------------------------------------------

update batiments set concepteur = 'Trait Vivant Architectes' where  concepteur = 'Volker Ehrlich';
-- UPDATE 1

alter table batiments rename column concepteur to architecte;

alter table batiments add column entreprise_enduits varchar(512);

update batiments set entreprise_enduits = 'Enduits terre Frédérique Jonnard' where realisateur = 'Enduits terre Frédérique Jonnard';
-- UPDATE 1

update batiments set realisateur = NULL where realisateur = 'Enduits terre Frédérique Jonnard';
-- UPDATE 1

update batiments set entreprise_enduits = 'Collect''IF Paille, Trait Vivant, Nydia Solis' where realisateur = 'Collect''IF Paille, Trait Vivant, Nydia Solis';
-- UPDATE 1

update batiments set entreprise_enduits = 'Botmobil (Anaig Madec)' where realisateur = 'Botmobil (Anaig Madec)';
-- UPDATE 1

alter table batiments rename column realisateur to entreprise_bottes;

-- bottes_cereale --------------------------------------------------------------

alter type cereale rename value 'Blé' to           'BLE';
alter type cereale rename value 'Orge' to          'ORGE';
alter type cereale rename value 'Avoine' to        'AVOINE';
alter type cereale rename value 'Seigle' to        'SEIGLE';
alter type cereale rename value 'Triticale' to     'TRITICALE';
alter type cereale rename value 'Riz' to           'RIZ';
alter type cereale rename value 'Autre' to         'AUTRE';

-- struct_suppl ----------------------------------------------------------------

alter table batiments rename column struct_suppl to struct_compl;

-- revet_int -------------------------------------------------------------------

alter type revet_int rename value 'Plaque de plâtre' to              'PLAQUE_DE_PLATRE';
alter type revet_int rename value 'Lambris' to                       'LAMBRIS';
alter type revet_int rename value 'Enduit terre' to                  'ENDUIT_TERRE';
alter type revet_int rename value 'Enduit chaux' to                  'ENDUIT_CHAUX';
alter type revet_int rename value 'Enduit terre et chaux' to         'ENDUIT_TERRE_ET_CHAUX';
alter type revet_int rename value 'Enduit plâtre' to                 'ENDUIT_PLATRE';
alter type revet_int rename value 'Autre' to                         'AUTRE';

-- revet_ext -------------------------------------------------------------------

alter type revet_ext rename value 'Bardage ventilé' to           'BARDAGE_VENTILE';
alter type revet_ext rename value 'Enduit terre' to              'ENDUIT_TERRE';
alter type revet_ext rename value 'Enduit chaux' to              'ENDUIT_CHAUX';
alter type revet_ext rename value 'Enduit terre et chaux' to     'ENDUIT_TERRE_ET_CHAUX';
alter type revet_ext rename value 'Enduit plâtre' to             'ENDUIT_PLATRE';
alter type revet_ext rename value 'Autre' to                     'AUTRE';

update batiments set revet_ext = NULL where revet_ext = 'AUTRE';
-- UPDATE 5

-- technique_secondaire --------------------------------------------------------

-- All values are false. Not needed.

alter table batiments drop technique_secondaire;

--- integ_baie -----------------------------------------------------------------

alter type integ_baie rename value 'pré-cadre flottant' to 'PRE_CADRE_FLOTTANT';
alter type integ_baie rename value 'bloqueurs' to 'FIXE';
alter type integ_baie rename value 'autre' to 'AUTRE';

--- materiau_sb / support_ancrage ----------------------------------------------

alter type materiau_sb rename to support_ancrage;
alter table batiments rename materiau_sb to support_ancrage;

alter type support_ancrage rename value 'Béton armé' to 'BETON_ARME';
alter type support_ancrage rename value 'Autre' to                        'AUTRE';

alter table batiments add column support_ancrage_infos text;
update batiments set support_ancrage_infos = 'Parpaing de ciment'     where support_ancrage = 'Parpaing de ciment';
-- UPDATE 10

update batiments set support_ancrage_infos = 'Brique de terre cuite'  where support_ancrage = 'Brique de terre cuite';
-- UPDATE 4

update batiments set support_ancrage_infos = 'Brique de pierre ponce' where support_ancrage = 'Brique de pierre ponce';
-- UPDATE 1

update batiments set support_ancrage_infos = 'Béton léger isolant'    where support_ancrage = 'Béton léger isolant';
-- UPDATE 4

update batiments set support_ancrage_infos = 'Pierre'                 where support_ancrage = 'Pierre';
-- UPDATE 4

alter type support_ancrage add value 'MACONNERIE';

update batiments set support_ancrage = 'MACONNERIE' where support_ancrage = 'Parpaing de ciment';
-- UPDATE 10

update batiments set support_ancrage = 'MACONNERIE' where support_ancrage = 'Brique de terre cuite';
-- UPDATE 4

update batiments set support_ancrage = 'MACONNERIE' where support_ancrage = 'Brique de pierre ponce';
-- UPDATE 1

update batiments set support_ancrage = 'MACONNERIE' where support_ancrage = 'Béton léger isolant';
-- UPDATE 4

update batiments set support_ancrage = 'MACONNERIE' where support_ancrage = 'Pierre';
-- UPDATE 4

update batiments set support_ancrage = NULL where support_ancrage = 'AUTRE';
-- UPDATE 14

-- fid -------------------------------------------------------------------------

alter table batiments drop column fid;

-- rename batiments to batiment ------------------------------------------------

alter table batiments rename to batiment;

-- created_by_id -----------------------------------------------

alter table batiment add column created_by_id bigint;

update batiment set created_by_id = 2;
