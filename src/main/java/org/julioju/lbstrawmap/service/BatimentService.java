package org.julioju.lbstrawmap.service;

import java.util.Optional;
import org.julioju.lbstrawmap.domain.Batiment;
import org.julioju.lbstrawmap.repository.BatimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Batiment}.
 */
@Service
@Transactional
public class BatimentService {

    private final Logger log = LoggerFactory.getLogger(BatimentService.class);

    private final BatimentRepository batimentRepository;

    public BatimentService(BatimentRepository batimentRepository) {
        this.batimentRepository = batimentRepository;
    }

    /**
     * Save a batiment.
     *
     * @param batiment the entity to save.
     * @return the persisted entity.
     */
    public Batiment save(Batiment batiment) {
        log.debug("Request to save Batiment : {}", batiment);
        return batimentRepository.save(batiment);
    }

    /**
     * Partially update a batiment.
     *
     * @param batiment the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Batiment> partialUpdate(Batiment batiment) {
        log.debug("Request to partially update Batiment : {}", batiment);

        return batimentRepository
            .findById(batiment.getId())
            .map(existingBatiment -> {
                if (batiment.getLatitude() != null) {
                    existingBatiment.setLatitude(batiment.getLatitude());
                }
                if (batiment.getLongitude() != null) {
                    existingBatiment.setLongitude(batiment.getLongitude());
                }
                if (batiment.getNomBatiment() != null) {
                    existingBatiment.setNomBatiment(batiment.getNomBatiment());
                }
                if (batiment.getPhotoPrincipale() != null) {
                    existingBatiment.setPhotoPrincipale(batiment.getPhotoPrincipale());
                }
                if (batiment.getPhotoPrincipaleContentType() != null) {
                    existingBatiment.setPhotoPrincipaleContentType(batiment.getPhotoPrincipaleContentType());
                }
                if (batiment.getPhotoPrincipaleLegende() != null) {
                    existingBatiment.setPhotoPrincipaleLegende(batiment.getPhotoPrincipaleLegende());
                }
                if (batiment.getPhotoPrincipaleDescription() != null) {
                    existingBatiment.setPhotoPrincipaleDescription(batiment.getPhotoPrincipaleDescription());
                }
                if (batiment.getPhoto1() != null) {
                    existingBatiment.setPhoto1(batiment.getPhoto1());
                }
                if (batiment.getPhoto1ContentType() != null) {
                    existingBatiment.setPhoto1ContentType(batiment.getPhoto1ContentType());
                }
                if (batiment.getPhoto1Legende() != null) {
                    existingBatiment.setPhoto1Legende(batiment.getPhoto1Legende());
                }
                if (batiment.getPhoto1Description() != null) {
                    existingBatiment.setPhoto1Description(batiment.getPhoto1Description());
                }
                if (batiment.getPhoto2() != null) {
                    existingBatiment.setPhoto2(batiment.getPhoto2());
                }
                if (batiment.getPhoto2ContentType() != null) {
                    existingBatiment.setPhoto2ContentType(batiment.getPhoto2ContentType());
                }
                if (batiment.getPhoto2Legende() != null) {
                    existingBatiment.setPhoto2Legende(batiment.getPhoto2Legende());
                }
                if (batiment.getPhoto2Description() != null) {
                    existingBatiment.setPhoto2Description(batiment.getPhoto2Description());
                }
                if (batiment.getPhoto3() != null) {
                    existingBatiment.setPhoto3(batiment.getPhoto3());
                }
                if (batiment.getPhoto3ContentType() != null) {
                    existingBatiment.setPhoto3ContentType(batiment.getPhoto3ContentType());
                }
                if (batiment.getPhoto3Legende() != null) {
                    existingBatiment.setPhoto3Legende(batiment.getPhoto3Legende());
                }
                if (batiment.getPhoto3Description() != null) {
                    existingBatiment.setPhoto3Description(batiment.getPhoto3Description());
                }
                if (batiment.getPhoto4() != null) {
                    existingBatiment.setPhoto4(batiment.getPhoto4());
                }
                if (batiment.getPhoto4ContentType() != null) {
                    existingBatiment.setPhoto4ContentType(batiment.getPhoto4ContentType());
                }
                if (batiment.getPhoto4Legende() != null) {
                    existingBatiment.setPhoto4Legende(batiment.getPhoto4Legende());
                }
                if (batiment.getPhoto4Description() != null) {
                    existingBatiment.setPhoto4Description(batiment.getPhoto4Description());
                }
                if (batiment.getPhoto5() != null) {
                    existingBatiment.setPhoto5(batiment.getPhoto5());
                }
                if (batiment.getPhoto5ContentType() != null) {
                    existingBatiment.setPhoto5ContentType(batiment.getPhoto5ContentType());
                }
                if (batiment.getPhoto5Legende() != null) {
                    existingBatiment.setPhoto5Legende(batiment.getPhoto5Legende());
                }
                if (batiment.getPhoto5Description() != null) {
                    existingBatiment.setPhoto5Description(batiment.getPhoto5Description());
                }
                if (batiment.getUsageBatiment() != null) {
                    existingBatiment.setUsageBatiment(batiment.getUsageBatiment());
                }
                if (batiment.getCout() != null) {
                    existingBatiment.setCout(batiment.getCout());
                }
                if (batiment.getSurfacePlancher() != null) {
                    existingBatiment.setSurfacePlancher(batiment.getSurfacePlancher());
                }
                if (batiment.getNiveaux() != null) {
                    existingBatiment.setNiveaux(batiment.getNiveaux());
                }
                if (batiment.getTravauxNeuf() != null) {
                    existingBatiment.setTravauxNeuf(batiment.getTravauxNeuf());
                }
                if (batiment.getTravauxExtension() != null) {
                    existingBatiment.setTravauxExtension(batiment.getTravauxExtension());
                }
                if (batiment.getTravauxRenov() != null) {
                    existingBatiment.setTravauxRenov(batiment.getTravauxRenov());
                }
                if (batiment.getTravauxIte() != null) {
                    existingBatiment.setTravauxIte(batiment.getTravauxIte());
                }
                if (batiment.getTravauxIti() != null) {
                    existingBatiment.setTravauxIti(batiment.getTravauxIti());
                }
                if (batiment.getConstructionDebut() != null) {
                    existingBatiment.setConstructionDebut(batiment.getConstructionDebut());
                }
                if (batiment.getConstructionFin() != null) {
                    existingBatiment.setConstructionFin(batiment.getConstructionFin());
                }
                if (batiment.getBottesTaille() != null) {
                    existingBatiment.setBottesTaille(batiment.getBottesTaille());
                }
                if (batiment.getBotteTailleAutre() != null) {
                    existingBatiment.setBotteTailleAutre(batiment.getBotteTailleAutre());
                }
                if (batiment.getBottesDensite() != null) {
                    existingBatiment.setBottesDensite(batiment.getBottesDensite());
                }
                if (batiment.getBottesCereale() != null) {
                    existingBatiment.setBottesCereale(batiment.getBottesCereale());
                }
                if (batiment.getDistanceAppro() != null) {
                    existingBatiment.setDistanceAppro(batiment.getDistanceAppro());
                }
                if (batiment.getAutoconstruction() != null) {
                    existingBatiment.setAutoconstruction(batiment.getAutoconstruction());
                }
                if (batiment.getParticipatif() != null) {
                    existingBatiment.setParticipatif(batiment.getParticipatif());
                }
                if (batiment.getStructCompl() != null) {
                    existingBatiment.setStructCompl(batiment.getStructCompl());
                }
                if (batiment.getStructComplNature() != null) {
                    existingBatiment.setStructComplNature(batiment.getStructComplNature());
                }
                if (batiment.getStructComplNatureAutre() != null) {
                    existingBatiment.setStructComplNatureAutre(batiment.getStructComplNatureAutre());
                }
                if (batiment.getNoteCalcul() != null) {
                    existingBatiment.setNoteCalcul(batiment.getNoteCalcul());
                }
                if (batiment.getNbrRangDeBottes() != null) {
                    existingBatiment.setNbrRangDeBottes(batiment.getNbrRangDeBottes());
                }
                if (batiment.getLongMaxSansMurRefend() != null) {
                    existingBatiment.setLongMaxSansMurRefend(batiment.getLongMaxSansMurRefend());
                }
                if (batiment.getIntegBaie() != null) {
                    existingBatiment.setIntegBaie(batiment.getIntegBaie());
                }
                if (batiment.getSupportAncrage() != null) {
                    existingBatiment.setSupportAncrage(batiment.getSupportAncrage());
                }
                if (batiment.getSupportAncrageAutre() != null) {
                    existingBatiment.setSupportAncrageAutre(batiment.getSupportAncrageAutre());
                }
                if (batiment.getRevetInt() != null) {
                    existingBatiment.setRevetInt(batiment.getRevetInt());
                }
                if (batiment.getRevetExt() != null) {
                    existingBatiment.setRevetExt(batiment.getRevetExt());
                }
                if (batiment.getRevetExtAutre() != null) {
                    existingBatiment.setRevetExtAutre(batiment.getRevetExtAutre());
                }
                if (batiment.getMaitreDOuvrage() != null) {
                    existingBatiment.setMaitreDOuvrage(batiment.getMaitreDOuvrage());
                }
                if (batiment.getMaitreDOeuvre() != null) {
                    existingBatiment.setMaitreDOeuvre(batiment.getMaitreDOeuvre());
                }
                if (batiment.getArchitecte() != null) {
                    existingBatiment.setArchitecte(batiment.getArchitecte());
                }
                if (batiment.getBureauDEtudeStructure() != null) {
                    existingBatiment.setBureauDEtudeStructure(batiment.getBureauDEtudeStructure());
                }
                if (batiment.getBureauControl() != null) {
                    existingBatiment.setBureauControl(batiment.getBureauControl());
                }
                if (batiment.getEntrepriseBottes() != null) {
                    existingBatiment.setEntrepriseBottes(batiment.getEntrepriseBottes());
                }
                if (batiment.getEntrepriseCharpente() != null) {
                    existingBatiment.setEntrepriseCharpente(batiment.getEntrepriseCharpente());
                }
                if (batiment.getEntrepriseEnduits() != null) {
                    existingBatiment.setEntrepriseEnduits(batiment.getEntrepriseEnduits());
                }
                if (batiment.getDescriptionProjet() != null) {
                    existingBatiment.setDescriptionProjet(batiment.getDescriptionProjet());
                }
                if (batiment.getDifficultees() != null) {
                    existingBatiment.setDifficultees(batiment.getDifficultees());
                }
                if (batiment.getAstuces() != null) {
                    existingBatiment.setAstuces(batiment.getAstuces());
                }
                if (batiment.getDivers() != null) {
                    existingBatiment.setDivers(batiment.getDivers());
                }
                if (batiment.getContactNom() != null) {
                    existingBatiment.setContactNom(batiment.getContactNom());
                }
                if (batiment.getContactMail() != null) {
                    existingBatiment.setContactMail(batiment.getContactMail());
                }
                if (batiment.getContactPhone() != null) {
                    existingBatiment.setContactPhone(batiment.getContactPhone());
                }
                if (batiment.getCodePostal() != null) {
                    existingBatiment.setCodePostal(batiment.getCodePostal());
                }
                if (batiment.getNonBatimentEtPhotosPublics() != null) {
                    existingBatiment.setNonBatimentEtPhotosPublics(batiment.getNonBatimentEtPhotosPublics());
                }
                if (batiment.getCreatedDate() != null) {
                    existingBatiment.setCreatedDate(batiment.getCreatedDate());
                }
                if (batiment.getLastModifiedDate() != null) {
                    existingBatiment.setLastModifiedDate(batiment.getLastModifiedDate());
                }

                return existingBatiment;
            })
            .map(batimentRepository::save);
    }

    /**
     * Get all the batiments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Batiment> findAll(Pageable pageable) {
        log.debug("Request to get all Batiments");
        return batimentRepository.findAll(pageable);
    }

    /**
     * Get one batiment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Batiment> findOne(Long id) {
        log.debug("Request to get Batiment : {}", id);
        return batimentRepository.findById(id);
    }

    /**
     * Delete the batiment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Batiment : {}", id);
        batimentRepository.deleteById(id);
    }
}
