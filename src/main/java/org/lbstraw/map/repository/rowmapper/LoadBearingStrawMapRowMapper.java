package org.lbstraw.map.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.lbstraw.map.domain.LoadBearingStrawMap;
import org.lbstraw.map.service.ColumnConverter;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link LoadBearingStrawMap}, with proper type conversions.
 */
@Service
public class LoadBearingStrawMapRowMapper implements BiFunction<Row, String, LoadBearingStrawMap> {

    private final ColumnConverter converter;

    public LoadBearingStrawMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link LoadBearingStrawMap} stored in the database.
     */
    @Override
    public LoadBearingStrawMap apply(Row row, String prefix) {
        LoadBearingStrawMap entity = new LoadBearingStrawMap();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setLongitude(converter.fromRow(row, prefix + "_longitude", Float.class));
        entity.setLatitude(converter.fromRow(row, prefix + "_latitude", Float.class));
        return entity;
    }
}
