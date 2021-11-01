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
                if (batiments.getPhotoPrincipale() != null) {
                    existingBatiments.setPhotoPrincipale(batiments.getPhotoPrincipale());
                }
                if (batiments.getPhotoPrincipaleContentType() != null) {
                    existingBatiments.setPhotoPrincipaleContentType(batiments.getPhotoPrincipaleContentType());
                }
                if (batiments.getNom() != null) {
                    existingBatiments.setNom(batiments.getNom());
                }
                if (batiments.getTechniqueSecondaire() != null) {
                    existingBatiments.setTechniqueSecondaire(batiments.getTechniqueSecondaire());
                }
                if (batiments.getUsage() != null) {
                    existingBatiments.setUsage(batiments.getUsage());
                }
                if (batiments.getCout() != null) {
                    existingBatiments.setCout(batiments.getCout());
                }
                if (batiments.getSurface() != null) {
                    existingBatiments.setSurface(batiments.getSurface());
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
                if (batiments.getIntegBaie() != null) {
                    existingBatiments.setIntegBaie(batiments.getIntegBaie());
                }
                if (batiments.getStructSuppl() != null) {
                    existingBatiments.setStructSuppl(batiments.getStructSuppl());
                }
                if (batiments.getNoteCalcul() != null) {
                    existingBatiments.setNoteCalcul(batiments.getNoteCalcul());
                }
                if (batiments.getMateriauSb() != null) {
                    existingBatiments.setMateriauSb(batiments.getMateriauSb());
                }
                if (batiments.getRevetInt() != null) {
                    existingBatiments.setRevetInt(batiments.getRevetInt());
                }
                if (batiments.getRevetExt() != null) {
                    existingBatiments.setRevetExt(batiments.getRevetExt());
                }
                if (batiments.getConcepteur() != null) {
                    existingBatiments.setConcepteur(batiments.getConcepteur());
                }
                if (batiments.getRealisateur() != null) {
                    existingBatiments.setRealisateur(batiments.getRealisateur());
                }
                if (batiments.getDescription() != null) {
                    existingBatiments.setDescription(batiments.getDescription());
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
