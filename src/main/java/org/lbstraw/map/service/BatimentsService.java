package org.lbstraw.map.service;

import java.util.Optional;
import org.lbstraw.map.domain.Batiments;
import org.lbstraw.map.repository.BatimentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Batiments}.
 */
@Service
@Transactional
public class BatimentsService {

    private final Logger log = LoggerFactory.getLogger(BatimentsService.class);

    private final BatimentsRepository batimentsRepository;

    public BatimentsService(BatimentsRepository batimentsRepository) {
        this.batimentsRepository = batimentsRepository;
    }

    /**
     * Save a batiments.
     *
     * @param batiments the entity to save.
     * @return the persisted entity.
     */
    public Batiments save(Batiments batiments) {
        log.debug("Request to save Batiments : {}", batiments);
        return batimentsRepository.save(batiments);
    }

    /**
     * Partially update a batiments.
     *
     * @param batiments the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Batiments> partialUpdate(Batiments batiments) {
        log.debug("Request to partially update Batiments : {}", batiments);

        return batimentsRepository
            .findById(batiments.getId())
            .map(existingBatiments -> {
                if (batiments.getLatitude() != null) {
                    existingBatiments.setLatitude(batiments.getLatitude());
                }
                if (batiments.getLongitude() != null) {
                    existingBatiments.setLongitude(batiments.getLongitude());
                }
                if (batiments.getNomBatiment() != null) {
                    existingBatiments.setNomBatiment(batiments.getNomBatiment());
                }
                if (batiments.getPhotoPrincipale() != null) {
                    existingBatiments.setPhotoPrincipale(batiments.getPhotoPrincipale());
                }
                if (batiments.getPhotoPrincipaleContentType() != null) {
                    existingBatiments.setPhotoPrincipaleContentType(batiments.getPhotoPrincipaleContentType());
                }
                if (batiments.getPhotoPrincipaleLegende() != null) {
                    existingBatiments.setPhotoPrincipaleLegende(batiments.getPhotoPrincipaleLegende());
                }
                if (batiments.getPhotoPrincipaleDescription() != null) {
                    existingBatiments.setPhotoPrincipaleDescription(batiments.getPhotoPrincipaleDescription());
                }
                if (batiments.getPhoto1() != null) {
                    existingBatiments.setPhoto1(batiments.getPhoto1());
                }
                if (batiments.getPhoto1ContentType() != null) {
                    existingBatiments.setPhoto1ContentType(batiments.getPhoto1ContentType());
                }
                if (batiments.getPhoto1Legende() != null) {
                    existingBatiments.setPhoto1Legende(batiments.getPhoto1Legende());
                }
                if (batiments.getPhoto1Description() != null) {
                    existingBatiments.setPhoto1Description(batiments.getPhoto1Description());
                }
                if (batiments.getPhoto2() != null) {
                    existingBatiments.setPhoto2(batiments.getPhoto2());
                }
                if (batiments.getPhoto2ContentType() != null) {
                    existingBatiments.setPhoto2ContentType(batiments.getPhoto2ContentType());
                }
                if (batiments.getPhoto2Legende() != null) {
                    existingBatiments.setPhoto2Legende(batiments.getPhoto2Legende());
                }
                if (batiments.getPhoto2Description() != null) {
                    existingBatiments.setPhoto2Description(batiments.getPhoto2Description());
                }
                if (batiments.getPhoto3() != null) {
                    existingBatiments.setPhoto3(batiments.getPhoto3());
                }
                if (batiments.getPhoto3ContentType() != null) {
                    existingBatiments.setPhoto3ContentType(batiments.getPhoto3ContentType());
                }
                if (batiments.getPhoto3Legende() != null) {
                    existingBatiments.setPhoto3Legende(batiments.getPhoto3Legende());
                }
                if (batiments.getPhoto3Description() != null) {
                    existingBatiments.setPhoto3Description(batiments.getPhoto3Description());
                }
                if (batiments.getPhoto4() != null) {
                    existingBatiments.setPhoto4(batiments.getPhoto4());
                }
                if (batiments.getPhoto4ContentType() != null) {
                    existingBatiments.setPhoto4ContentType(batiments.getPhoto4ContentType());
                }
                if (batiments.getPhoto4Legende() != null) {
                    existingBatiments.setPhoto4Legende(batiments.getPhoto4Legende());
                }
                if (batiments.getPhoto4Description() != null) {
                    existingBatiments.setPhoto4Description(batiments.getPhoto4Description());
                }
                if (batiments.getPhoto5() != null) {
                    existingBatiments.setPhoto5(batiments.getPhoto5());
                }
                if (batiments.getPhoto5ContentType() != null) {
                    existingBatiments.setPhoto5ContentType(batiments.getPhoto5ContentType());
                }
                if (batiments.getPhoto5Legende() != null) {
                    existingBatiments.setPhoto5Legende(batiments.getPhoto5Legende());
                }
                if (batiments.getPhoto5Description() != null) {
                    existingBatiments.setPhoto5Description(batiments.getPhoto5Description());
                }
                if (batiments.getTechniqueSecondaire() != null) {
                    existingBatiments.setTechniqueSecondaire(batiments.getTechniqueSecondaire());
                }
                if (batiments.getUsageBatiment() != null) {
                    existingBatiments.setUsageBatiment(batiments.getUsageBatiment());
                }
                if (batiments.getCout() != null) {
                    existingBatiments.setCout(batiments.getCout());
                }
                if (batiments.getSurfacePlancher() != null) {
                    existingBatiments.setSurfacePlancher(batiments.getSurfacePlancher());
                }
                if (batiments.getNiveaux() != null) {
                    existingBatiments.setNiveaux(batiments.getNiveaux());
                }
                if (batiments.getTravauxNeuf() != null) {
                    existingBatiments.setTravauxNeuf(batiments.getTravauxNeuf());
                }
                if (batiments.getTravauxExtension() != null) {
                    existingBatiments.setTravauxExtension(batiments.getTravauxExtension());
                }
                if (batiments.getTravauxRenov() != null) {
                    existingBatiments.setTravauxRenov(batiments.getTravauxRenov());
                }
                if (batiments.getTravauxIte() != null) {
                    existingBatiments.setTravauxIte(batiments.getTravauxIte());
                }
                if (batiments.getTravauxIti() != null) {
                    existingBatiments.setTravauxIti(batiments.getTravauxIti());
                }
                if (batiments.getConstructionDebut() != null) {
                    existingBatiments.setConstructionDebut(batiments.getConstructionDebut());
                }
                if (batiments.getConstructionFin() != null) {
                    existingBatiments.setConstructionFin(batiments.getConstructionFin());
                }
                if (batiments.getBottesTaille() != null) {
                    existingBatiments.setBottesTaille(batiments.getBottesTaille());
                }
                if (batiments.getBotteTailleAutre() != null) {
                    existingBatiments.setBotteTailleAutre(batiments.getBotteTailleAutre());
                }
                if (batiments.getBottesDensite() != null) {
                    existingBatiments.setBottesDensite(batiments.getBottesDensite());
                }
                if (batiments.getBottesCereale() != null) {
                    existingBatiments.setBottesCereale(batiments.getBottesCereale());
                }
                if (batiments.getDistanceAppro() != null) {
                    existingBatiments.setDistanceAppro(batiments.getDistanceAppro());
                }
                if (batiments.getAutoconstruction() != null) {
                    existingBatiments.setAutoconstruction(batiments.getAutoconstruction());
                }
                if (batiments.getParticipatif() != null) {
                    existingBatiments.setParticipatif(batiments.getParticipatif());
                }
                if (batiments.getStructSuppl() != null) {
                    existingBatiments.setStructSuppl(batiments.getStructSuppl());
                }
                if (batiments.getStructSupplNature() != null) {
                    existingBatiments.setStructSupplNature(batiments.getStructSupplNature());
                }
                if (batiments.getNoteCalcul() != null) {
                    existingBatiments.setNoteCalcul(batiments.getNoteCalcul());
                }
                if (batiments.getNbrRangDeBottes() != null) {
                    existingBatiments.setNbrRangDeBottes(batiments.getNbrRangDeBottes());
                }
                if (batiments.getLongMaxSansMurRefend() != null) {
                    existingBatiments.setLongMaxSansMurRefend(batiments.getLongMaxSansMurRefend());
                }
                if (batiments.getIntegBaie() != null) {
                    existingBatiments.setIntegBaie(batiments.getIntegBaie());
                }
                if (batiments.getSupportAncrage() != null) {
                    existingBatiments.setSupportAncrage(batiments.getSupportAncrage());
                }
                if (batiments.getSupportAncragePrecisions() != null) {
                    existingBatiments.setSupportAncragePrecisions(batiments.getSupportAncragePrecisions());
                }
                if (batiments.getRevetInt() != null) {
                    existingBatiments.setRevetInt(batiments.getRevetInt());
                }
                if (batiments.getRevetExt() != null) {
                    existingBatiments.setRevetExt(batiments.getRevetExt());
                }
                if (batiments.getRevetExtAutre() != null) {
                    existingBatiments.setRevetExtAutre(batiments.getRevetExtAutre());
                }
                if (batiments.getMaitreDOuvrage() != null) {
                    existingBatiments.setMaitreDOuvrage(batiments.getMaitreDOuvrage());
                }
                if (batiments.getMaitreDOeuvre() != null) {
                    existingBatiments.setMaitreDOeuvre(batiments.getMaitreDOeuvre());
                }
                if (batiments.getArchitecte() != null) {
                    existingBatiments.setArchitecte(batiments.getArchitecte());
                }
                if (batiments.getBureauDEtudeStructure() != null) {
                    existingBatiments.setBureauDEtudeStructure(batiments.getBureauDEtudeStructure());
                }
                if (batiments.getBureauControl() != null) {
                    existingBatiments.setBureauControl(batiments.getBureauControl());
                }
                if (batiments.getEntrepriseBottes() != null) {
                    existingBatiments.setEntrepriseBottes(batiments.getEntrepriseBottes());
                }
                if (batiments.getEntrepriseCharpente() != null) {
                    existingBatiments.setEntrepriseCharpente(batiments.getEntrepriseCharpente());
                }
                if (batiments.getEntrepriseEnduits() != null) {
                    existingBatiments.setEntrepriseEnduits(batiments.getEntrepriseEnduits());
                }
                if (batiments.getDescriptionProjet() != null) {
                    existingBatiments.setDescriptionProjet(batiments.getDescriptionProjet());
                }
                if (batiments.getDifficultees() != null) {
                    existingBatiments.setDifficultees(batiments.getDifficultees());
                }
                if (batiments.getAstuces() != null) {
                    existingBatiments.setAstuces(batiments.getAstuces());
                }
                if (batiments.getDivers() != null) {
                    existingBatiments.setDivers(batiments.getDivers());
                }
                if (batiments.getContactNom() != null) {
                    existingBatiments.setContactNom(batiments.getContactNom());
                }
                if (batiments.getContactMail() != null) {
                    existingBatiments.setContactMail(batiments.getContactMail());
                }
                if (batiments.getContactPhone() != null) {
                    existingBatiments.setContactPhone(batiments.getContactPhone());
                }
                if (batiments.getCodePostal() != null) {
                    existingBatiments.setCodePostal(batiments.getCodePostal());
                }
                if (batiments.getNonBatimentEtPhotosPublics() != null) {
                    existingBatiments.setNonBatimentEtPhotosPublics(batiments.getNonBatimentEtPhotosPublics());
                }
                if (batiments.getDateCreationFiche() != null) {
                    existingBatiments.setDateCreationFiche(batiments.getDateCreationFiche());
                }
                if (batiments.getDateModificationFiche() != null) {
                    existingBatiments.setDateModificationFiche(batiments.getDateModificationFiche());
                }

                return existingBatiments;
            })
            .map(batimentsRepository::save);
    }

    /**
     * Get all the batiments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Batiments> findAll(Pageable pageable) {
        log.debug("Request to get all Batiments");
        return batimentsRepository.findAll(pageable);
    }

    /**
     * Get one batiments by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Batiments> findOne(Long id) {
        log.debug("Request to get Batiments : {}", id);
        return batimentsRepository.findById(id);
    }

    /**
     * Delete the batiments by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Batiments : {}", id);
        batimentsRepository.deleteById(id);
    }
}
