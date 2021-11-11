/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from '@/shared/date/filters';
import BatimentService from '@/entities/batiment/batiment.service';
import { Batiment } from '@/shared/model/batiment.model';
import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { StructureComplementaire } from '@/shared/model/enumerations/structure-complementaire.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { SupportAncrage } from '@/shared/model/enumerations/support-ancrage.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Batiment Service', () => {
    let service: BatimentService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BatimentService();
      currentDate = new Date();
      elemDefault = new Batiment(
        123,
        0,
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        UsageBatiment.LOGEMENT_COLLECTIF,
        'AAAAAAA',
        0,
        0,
        0,
        false,
        false,
        false,
        false,
        false,
        currentDate,
        currentDate,
        TaillesBottes.T_70_X_120_X_230_CM,
        'AAAAAAA',
        0,
        Cereale.BLE,
        0,
        YesNoPartial.OUI,
        YesNoPartial.OUI,
        false,
        StructureComplementaire.BOIS,
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        0,
        IntegBaie.PRE_CADRE_FLOTTANT,
        'AAAAAAA',
        SupportAncrage.BOIS,
        'AAAAAAA',
        RevetInt.PLAQUE_DE_PLATRE,
        'AAAAAAA',
        RevetExt.BARDAGE_VENTILE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a Batiment', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Batiment', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Batiment', async () => {
        const returnedFromService = Object.assign(
          {
            latitude: 1,
            longitude: 1,
            nomBatiment: 'BBBBBB',
            photoPrincipale: 'BBBBBB',
            photoPrincipaleLegende: 'BBBBBB',
            photoPrincipaleDescription: 'BBBBBB',
            photo1: 'BBBBBB',
            photo1Legende: 'BBBBBB',
            photo1Description: 'BBBBBB',
            photo2: 'BBBBBB',
            photo2Legende: 'BBBBBB',
            photo2Description: 'BBBBBB',
            photo3: 'BBBBBB',
            photo3Legende: 'BBBBBB',
            photo3Description: 'BBBBBB',
            photo4: 'BBBBBB',
            photo4Legende: 'BBBBBB',
            photo4Description: 'BBBBBB',
            photo5: 'BBBBBB',
            photo5Legende: 'BBBBBB',
            photo5Description: 'BBBBBB',
            nomBatimentEtPhotosPublics: true,
            usageBatiment: 'BBBBBB',
            usageBatimentAutre: 'BBBBBB',
            cout: 1,
            surfacePlancher: 1,
            niveaux: 1,
            travauxNeuf: true,
            travauxExtension: true,
            travauxRenov: true,
            travauxIte: true,
            travauxIti: true,
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            bottesTaille: 'BBBBBB',
            botteTailleAutre: 'BBBBBB',
            bottesDensite: 1,
            bottesCereale: 'BBBBBB',
            distanceAppro: 1,
            autoconstruction: 'BBBBBB',
            participatif: 'BBBBBB',
            structCompl: true,
            structComplNature: 'BBBBBB',
            structComplAutre: 'BBBBBB',
            structComplInfos: 'BBBBBB',
            longMaxSansMurRefend: 1,
            noteCalcul: true,
            nbrRangDeBottes: 1,
            integBaie: 'BBBBBB',
            integBaieAutre: 'BBBBBB',
            supportAncrage: 'BBBBBB',
            supportAncrageAutre: 'BBBBBB',
            revetInt: 'BBBBBB',
            revetIntAutre: 'BBBBBB',
            revetExt: 'BBBBBB',
            revetExtAutre: 'BBBBBB',
            maitreDOuvrage: 'BBBBBB',
            maitreDOeuvre: 'BBBBBB',
            architecte: 'BBBBBB',
            bureauDEtudeStructure: 'BBBBBB',
            bureauControl: 'BBBBBB',
            entrepriseBottes: 'BBBBBB',
            entrepriseCharpente: 'BBBBBB',
            entrepriseEnduits: 'BBBBBB',
            descriptionProjet: 'BBBBBB',
            difficultees: 'BBBBBB',
            astuces: 'BBBBBB',
            divers: 'BBBBBB',
            contactNom: 'BBBBBB',
            contactMail: 'BBBBBB',
            contactPhone: 'BBBBBB',
            codePostal: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Batiment', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Batiment', async () => {
        const patchObject = Object.assign(
          {
            latitude: 1,
            nomBatiment: 'BBBBBB',
            photoPrincipale: 'BBBBBB',
            photo1Legende: 'BBBBBB',
            photo1Description: 'BBBBBB',
            photo2Legende: 'BBBBBB',
            photo2Description: 'BBBBBB',
            photo3: 'BBBBBB',
            photo4Legende: 'BBBBBB',
            photo4Description: 'BBBBBB',
            nomBatimentEtPhotosPublics: true,
            usageBatimentAutre: 'BBBBBB',
            cout: 1,
            surfacePlancher: 1,
            niveaux: 1,
            travauxExtension: true,
            travauxIte: true,
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            botteTailleAutre: 'BBBBBB',
            bottesDensite: 1,
            structCompl: true,
            structComplNature: 'BBBBBB',
            longMaxSansMurRefend: 1,
            integBaie: 'BBBBBB',
            supportAncrage: 'BBBBBB',
            supportAncrageAutre: 'BBBBBB',
            revetInt: 'BBBBBB',
            revetIntAutre: 'BBBBBB',
            maitreDOeuvre: 'BBBBBB',
            architecte: 'BBBBBB',
            bureauDEtudeStructure: 'BBBBBB',
            bureauControl: 'BBBBBB',
            astuces: 'BBBBBB',
            contactNom: 'BBBBBB',
            contactMail: 'BBBBBB',
            contactPhone: 'BBBBBB',
            codePostal: 'BBBBBB',
          },
          new Batiment()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Batiment', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Batiment', async () => {
        const returnedFromService = Object.assign(
          {
            latitude: 1,
            longitude: 1,
            nomBatiment: 'BBBBBB',
            photoPrincipale: 'BBBBBB',
            photoPrincipaleLegende: 'BBBBBB',
            photoPrincipaleDescription: 'BBBBBB',
            photo1: 'BBBBBB',
            photo1Legende: 'BBBBBB',
            photo1Description: 'BBBBBB',
            photo2: 'BBBBBB',
            photo2Legende: 'BBBBBB',
            photo2Description: 'BBBBBB',
            photo3: 'BBBBBB',
            photo3Legende: 'BBBBBB',
            photo3Description: 'BBBBBB',
            photo4: 'BBBBBB',
            photo4Legende: 'BBBBBB',
            photo4Description: 'BBBBBB',
            photo5: 'BBBBBB',
            photo5Legende: 'BBBBBB',
            photo5Description: 'BBBBBB',
            nomBatimentEtPhotosPublics: true,
            usageBatiment: 'BBBBBB',
            usageBatimentAutre: 'BBBBBB',
            cout: 1,
            surfacePlancher: 1,
            niveaux: 1,
            travauxNeuf: true,
            travauxExtension: true,
            travauxRenov: true,
            travauxIte: true,
            travauxIti: true,
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            bottesTaille: 'BBBBBB',
            botteTailleAutre: 'BBBBBB',
            bottesDensite: 1,
            bottesCereale: 'BBBBBB',
            distanceAppro: 1,
            autoconstruction: 'BBBBBB',
            participatif: 'BBBBBB',
            structCompl: true,
            structComplNature: 'BBBBBB',
            structComplAutre: 'BBBBBB',
            structComplInfos: 'BBBBBB',
            longMaxSansMurRefend: 1,
            noteCalcul: true,
            nbrRangDeBottes: 1,
            integBaie: 'BBBBBB',
            integBaieAutre: 'BBBBBB',
            supportAncrage: 'BBBBBB',
            supportAncrageAutre: 'BBBBBB',
            revetInt: 'BBBBBB',
            revetIntAutre: 'BBBBBB',
            revetExt: 'BBBBBB',
            revetExtAutre: 'BBBBBB',
            maitreDOuvrage: 'BBBBBB',
            maitreDOeuvre: 'BBBBBB',
            architecte: 'BBBBBB',
            bureauDEtudeStructure: 'BBBBBB',
            bureauControl: 'BBBBBB',
            entrepriseBottes: 'BBBBBB',
            entrepriseCharpente: 'BBBBBB',
            entrepriseEnduits: 'BBBBBB',
            descriptionProjet: 'BBBBBB',
            difficultees: 'BBBBBB',
            astuces: 'BBBBBB',
            divers: 'BBBBBB',
            contactNom: 'BBBBBB',
            contactMail: 'BBBBBB',
            contactPhone: 'BBBBBB',
            codePostal: 'BBBBBB',
            createdDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Batiment', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Batiment', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Batiment', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
