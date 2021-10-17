package org.lbstraw.map.repository;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.domain.enumeration.Cereale;
import org.lbstraw.map.domain.enumeration.IntegBaie;
import org.lbstraw.map.domain.enumeration.MateriauSb;
import org.lbstraw.map.domain.enumeration.RevetExt;
import org.lbstraw.map.domain.enumeration.RevetInt;
import org.lbstraw.map.domain.enumeration.TaillesBottes;
import org.lbstraw.map.domain.enumeration.UsageBatiment;
import org.lbstraw.map.domain.enumeration.YesNoPartial;
import org.lbstraw.map.repository.rowmapper.BatimentsRowMapper;
import org.lbstraw.map.service.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive custom repository implementation for the Batiments entity.
 */
@SuppressWarnings("unused")
class BatimentsRepositoryInternalImpl implements BatimentsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BatimentsRowMapper batimentsMapper;

    private static final Table entityTable = Table.aliased("batiments", EntityManager.ENTITY_ALIAS);

    public BatimentsRepositoryInternalImpl(R2dbcEntityTemplate template, EntityManager entityManager, BatimentsRowMapper batimentsMapper) {
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.batimentsMapper = batimentsMapper;
    }

    @Override
    public Flux<Batiments> findAllBy(Pageable pageable) {
        return findAllBy(pageable, null);
    }

    @Override
    public Flux<Batiments> findAllBy(Pageable pageable, Criteria criteria) {
        return createQuery(pageable, criteria).all();
    }

    RowsFetchSpec<Batiments> createQuery(Pageable pageable, Criteria criteria) {
        List<Expression> columns = BatimentsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);

        String select = entityManager.createSelect(selectFrom, Batiments.class, pageable, criteria);
        String alias = entityTable.getReferenceName().getReference();
        String selectWhere = Optional
            .ofNullable(criteria)
            .map(crit ->
                new StringBuilder(select)
                    .append(" ")
                    .append("WHERE")
                    .append(" ")
                    .append(alias)
                    .append(".")
                    .append(crit.toString())
                    .toString()
            )
            .orElse(select); // TODO remove once https://github.com/spring-projects/spring-data-jdbc/issues/907 will be fixed
        return db.sql(selectWhere).map(this::process);
    }

    @Override
    public Flux<Batiments> findAll() {
        return findAllBy(null, null);
    }

    @Override
    public Mono<Batiments> findById(Long id) {
        return createQuery(null, where("id").is(id)).one();
    }

    private Batiments process(Row row, RowMetadata metadata) {
        Batiments entity = batimentsMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Batiments> Mono<S> insert(S entity) {
        return entityManager.insert(entity);
    }

    @Override
    public <S extends Batiments> Mono<S> save(S entity) {
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            return update(entity)
                .map(numberOfUpdates -> {
                    if (numberOfUpdates.intValue() <= 0) {
                        throw new IllegalStateException("Unable to update Batiments with id = " + entity.getId());
                    }
                    return entity;
                });
        }
    }

    @Override
    public Mono<Integer> update(Batiments entity) {
        //fixme is this the proper way?
        return r2dbcEntityTemplate.update(entity).thenReturn(1);
    }
}

class BatimentsSqlHelper {

    static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("latitude", table, columnPrefix + "_latitude"));
        columns.add(Column.aliased("longitude", table, columnPrefix + "_longitude"));
        columns.add(Column.aliased("nom", table, columnPrefix + "_nom"));
        columns.add(Column.aliased("contact_nom", table, columnPrefix + "_contact_nom"));
        columns.add(Column.aliased("contact_mail", table, columnPrefix + "_contact_mail"));
        columns.add(Column.aliased("contact_phone", table, columnPrefix + "_contact_phone"));
        columns.add(Column.aliased("construction_debut", table, columnPrefix + "_construction_debut"));
        columns.add(Column.aliased("construction_fin", table, columnPrefix + "_construction_fin"));
        columns.add(Column.aliased("surface", table, columnPrefix + "_surface"));
        columns.add(Column.aliased("cout", table, columnPrefix + "_cout"));
        columns.add(Column.aliased("bottes_taille", table, columnPrefix + "_bottes_taille"));
        columns.add(Column.aliased("autoconstruction", table, columnPrefix + "_autoconstruction"));
        columns.add(Column.aliased("concepteur", table, columnPrefix + "_concepteur"));
        columns.add(Column.aliased("realisateur", table, columnPrefix + "_realisateur"));
        columns.add(Column.aliased("participatif", table, columnPrefix + "_participatif"));
        columns.add(Column.aliased("usage", table, columnPrefix + "_usage"));
        columns.add(Column.aliased("note_calcul", table, columnPrefix + "_note_calcul"));
        columns.add(Column.aliased("travaux_neuf", table, columnPrefix + "_travaux_neuf"));
        columns.add(Column.aliased("travaux_extension", table, columnPrefix + "_travaux_extension"));
        columns.add(Column.aliased("travaux_renov", table, columnPrefix + "_travaux_renov"));
        columns.add(Column.aliased("travaux_ite", table, columnPrefix + "_travaux_ite"));
        columns.add(Column.aliased("travaux_iti", table, columnPrefix + "_travaux_iti"));
        columns.add(Column.aliased("niveaux", table, columnPrefix + "_niveaux"));
        columns.add(Column.aliased("bottes_densite", table, columnPrefix + "_bottes_densite"));
        columns.add(Column.aliased("distance_appro", table, columnPrefix + "_distance_appro"));
        columns.add(Column.aliased("bottes_cereale", table, columnPrefix + "_bottes_cereale"));
        columns.add(Column.aliased("struct_suppl", table, columnPrefix + "_struct_suppl"));
        columns.add(Column.aliased("revet_int", table, columnPrefix + "_revet_int"));
        columns.add(Column.aliased("revet_ext", table, columnPrefix + "_revet_ext"));
        columns.add(Column.aliased("technique_secondaire", table, columnPrefix + "_technique_secondaire"));
        columns.add(Column.aliased("code_postal", table, columnPrefix + "_code_postal"));
        columns.add(Column.aliased("integ_baie", table, columnPrefix + "_integ_baie"));
        columns.add(Column.aliased("materiau_sb", table, columnPrefix + "_materiau_sb"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));

        return columns;
    }
}
