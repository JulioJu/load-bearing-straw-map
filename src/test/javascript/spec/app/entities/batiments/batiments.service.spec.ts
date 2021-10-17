/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import BatimentsService from '@/entities/batiments/batiments.service';
import { Batiments } from '@/shared/model/batiments.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { MateriauSb } from '@/shared/model/enumerations/materiau-sb.model';

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
  describe('Batiments Service', () => {
    let service: BatimentsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new BatimentsService();
      currentDate = new Date();
      elemDefault = new Batiments(
        123,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        0,
        TaillesBottes.T_80_X_120_CM,
        YesNoPartial.OUI,
        'AAAAAAA',
        'AAAAAAA',
        YesNoPartial.OUI,
        UsageBatiment.LOGEMENT_COLLECTIF,
        false,
        false,
        false,
        false,
        false,
        false,
        0,
        0,
        0,
        Cereale.BLE,
        false,
        RevetInt.PLAQUE_DE_PLATRE,
        RevetExt.BARDAGE_VENTILE,
        false,
        'AAAAAAA',
        IntegBaie.PRE_CADRE_FLOTTANT,
        MateriauSb.BETON_ARME,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Batiments', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Batiments', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Batiments', async () => {
        const returnedFromService = Object.assign(
          {
            latitude: 1,
            longitude: 1,
            nom: 'BBBBBB',
            contactNom: 'BBBBBB',
            contactMail: 'BBBBBB',
            contactPhone: 'BBBBBB',
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            surface: 1,
            cout: 1,
            bottesTaille: 'BBBBBB',
            autoconstruction: 'BBBBBB',
            concepteur: 'BBBBBB',
            realisateur: 'BBBBBB',
            participatif: 'BBBBBB',
            usage: 'BBBBBB',
            noteCalcul: true,
            travauxNeuf: true,
            travauxExtension: true,
            travauxRenov: true,
            travauxIte: true,
            travauxIti: true,
            niveaux: 1,
            bottesDensite: 1,
            distanceAppro: 1,
            bottesCereale: 'BBBBBB',
            structSuppl: true,
            revetInt: 'BBBBBB',
            revetExt: 'BBBBBB',
            techniqueSecondaire: true,
            codePostal: 'BBBBBB',
            integBaie: 'BBBBBB',
            materiauSb: 'BBBBBB',
            description: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Batiments', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Batiments', async () => {
        const patchObject = Object.assign(
          {
            latitude: 1,
            longitude: 1,
            contactNom: 'BBBBBB',
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            surface: 1,
            autoconstruction: 'BBBBBB',
            concepteur: 'BBBBBB',
            participatif: 'BBBBBB',
            travauxExtension: true,
            travauxIte: true,
            niveaux: 1,
            revetInt: 'BBBBBB',
            codePostal: 'BBBBBB',
            integBaie: 'BBBBBB',
            description: 'BBBBBB',
          },
          new Batiments()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Batiments', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Batiments', async () => {
        const returnedFromService = Object.assign(
          {
            latitude: 1,
            longitude: 1,
            nom: 'BBBBBB',
            contactNom: 'BBBBBB',
            contactMail: 'BBBBBB',
            contactPhone: 'BBBBBB',
            constructionDebut: dayjs(currentDate).format(DATE_FORMAT),
            constructionFin: dayjs(currentDate).format(DATE_FORMAT),
            surface: 1,
            cout: 1,
            bottesTaille: 'BBBBBB',
            autoconstruction: 'BBBBBB',
            concepteur: 'BBBBBB',
            realisateur: 'BBBBBB',
            participatif: 'BBBBBB',
            usage: 'BBBBBB',
            noteCalcul: true,
            travauxNeuf: true,
            travauxExtension: true,
            travauxRenov: true,
            travauxIte: true,
            travauxIti: true,
            niveaux: 1,
            bottesDensite: 1,
            distanceAppro: 1,
            bottesCereale: 'BBBBBB',
            structSuppl: true,
            revetInt: 'BBBBBB',
            revetExt: 'BBBBBB',
            techniqueSecondaire: true,
            codePostal: 'BBBBBB',
            integBaie: 'BBBBBB',
            materiauSb: 'BBBBBB',
            description: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            constructionDebut: currentDate,
            constructionFin: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Batiments', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Batiments', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Batiments', async () => {
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
