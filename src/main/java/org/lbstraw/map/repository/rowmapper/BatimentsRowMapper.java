package org.lbstraw.map.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.LocalDate;
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
import org.lbstraw.map.service.ColumnConverter;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Batiments}, with proper type conversions.
 */
@Service
public class BatimentsRowMapper implements BiFunction<Row, String, Batiments> {

    private final ColumnConverter converter;

    public BatimentsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Batiments} stored in the database.
     */
    @Override
    public Batiments apply(Row row, String prefix) {
        Batiments entity = new Batiments();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setLatitude(converter.fromRow(row, prefix + "_latitude", Float.class));
        entity.setLongitude(converter.fromRow(row, prefix + "_longitude", Float.class));
        entity.setNom(converter.fromRow(row, prefix + "_nom", String.class));
        entity.setContactNom(converter.fromRow(row, prefix + "_contact_nom", String.class));
        entity.setContactMail(converter.fromRow(row, prefix + "_contact_mail", String.class));
        entity.setContactPhone(converter.fromRow(row, prefix + "_contact_phone", String.class));
        entity.setConstructionDebut(converter.fromRow(row, prefix + "_construction_debut", LocalDate.class));
        entity.setConstructionFin(converter.fromRow(row, prefix + "_construction_fin", LocalDate.class));
        entity.setSurface(converter.fromRow(row, prefix + "_surface", Integer.class));
        entity.setCout(converter.fromRow(row, prefix + "_cout", Integer.class));
        entity.setBottesTaille(converter.fromRow(row, prefix + "_bottes_taille", TaillesBottes.class));
        entity.setAutoconstruction(converter.fromRow(row, prefix + "_autoconstruction", YesNoPartial.class));
        entity.setConcepteur(converter.fromRow(row, prefix + "_concepteur", String.class));
        entity.setRealisateur(converter.fromRow(row, prefix + "_realisateur", String.class));
        entity.setParticipatif(converter.fromRow(row, prefix + "_participatif", YesNoPartial.class));
        entity.setUsage(converter.fromRow(row, prefix + "_usage", UsageBatiment.class));
        entity.setNoteCalcul(converter.fromRow(row, prefix + "_note_calcul", Boolean.class));
        entity.setTravauxNeuf(converter.fromRow(row, prefix + "_travaux_neuf", Boolean.class));
        entity.setTravauxExtension(converter.fromRow(row, prefix + "_travaux_extension", Boolean.class));
        entity.setTravauxRenov(converter.fromRow(row, prefix + "_travaux_renov", Boolean.class));
        entity.setTravauxIte(converter.fromRow(row, prefix + "_travaux_ite", Boolean.class));
        entity.setTravauxIti(converter.fromRow(row, prefix + "_travaux_iti", Boolean.class));
        entity.setNiveaux(converter.fromRow(row, prefix + "_niveaux", Integer.class));
        entity.setBottesDensite(converter.fromRow(row, prefix + "_bottes_densite", Integer.class));
        entity.setDistanceAppro(converter.fromRow(row, prefix + "_distance_appro", Integer.class));
        entity.setBottesCereale(converter.fromRow(row, prefix + "_bottes_cereale", Cereale.class));
        entity.setStructSuppl(converter.fromRow(row, prefix + "_struct_suppl", Boolean.class));
        entity.setRevetInt(converter.fromRow(row, prefix + "_revet_int", RevetInt.class));
        entity.setRevetExt(converter.fromRow(row, prefix + "_revet_ext", RevetExt.class));
        entity.setTechniqueSecondaire(converter.fromRow(row, prefix + "_technique_secondaire", Boolean.class));
        entity.setCodePostal(converter.fromRow(row, prefix + "_code_postal", String.class));
        entity.setIntegBaie(converter.fromRow(row, prefix + "_integ_baie", IntegBaie.class));
        entity.setMateriauSb(converter.fromRow(row, prefix + "_materiau_sb", MateriauSb.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        return entity;
    }
}
