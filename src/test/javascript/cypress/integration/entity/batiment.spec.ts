import { entityItemSelector } from '../../support/commands';
import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Batiment e2e test', () => {
  const batimentPageUrl = '/batiment';
  const batimentPageUrlPattern = new RegExp('/batiment(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'admin';
  const password = Cypress.env('E2E_PASSWORD') ?? 'admin';
  const batimentSample = { latitude: 57, longitude: -39 };

  let batiment: any;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });
    cy.visit('');
    cy.login(username, password);
    cy.get(entityItemSelector).should('exist');
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/batiments+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/batiments').as('postEntityRequest');
    cy.intercept('DELETE', '/api/batiments/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (batiment) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/batiments/${batiment.id}`,
      }).then(() => {
        batiment = undefined;
      });
    }
  });

  it('Batiments menu should load Batiments page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('batiment');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Batiment').should('exist');
    cy.url().should('match', batimentPageUrlPattern);
  });

  describe('Batiment page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(batimentPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Batiment page', () => {
        cy.get(entityCreateButtonSelector).click({ force: true });
        cy.url().should('match', new RegExp('/batiment/new$'));
        cy.getEntityCreateUpdateHeading('Batiment');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/batiments',
          body: batimentSample,
        }).then(({ body }) => {
          batiment = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/batiments+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [batiment],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(batimentPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Batiment page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('batiment');
        cy.get(entityDetailsBackButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentPageUrlPattern);
      });

      it('edit button click should load edit Batiment page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Batiment');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentPageUrlPattern);
      });

      it('last delete button click should delete instance of Batiment', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('batiment').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentPageUrlPattern);

        batiment = undefined;
      });
    });
  });

  describe('new Batiment page', () => {
    beforeEach(() => {
      cy.visit(`${batimentPageUrl}`);
      cy.get(entityCreateButtonSelector).click({ force: true });
      cy.getEntityCreateUpdateHeading('Batiment');
    });

    it('should create an instance of Batiment', () => {
      cy.get(`[data-cy="latitude"]`).type('31').should('have.value', '31');

      cy.get(`[data-cy="longitude"]`).type('3').should('have.value', '3');

      cy.get(`[data-cy="nomBatiment"]`).type('Manager').should('have.value', 'Manager');

      cy.setFieldImageAsBytesOfEntity('photoPrincipale', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photoPrincipaleLegende"]`).type('parse Open-source a').should('have.value', 'parse Open-source a');

      cy.get(`[data-cy="photoPrincipaleDescription"]`)
        .type('initiative wireless digital')
        .should('have.value', 'initiative wireless digital');

      cy.setFieldImageAsBytesOfEntity('photo1', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo1Legende"]`).type('Personal a').should('have.value', 'Personal a');

      cy.get(`[data-cy="photo1Description"]`).type('violet well-modulated').should('have.value', 'violet well-modulated');

      cy.setFieldImageAsBytesOfEntity('photo2', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo2Legende"]`).type('silver Lorraine').should('have.value', 'silver Lorraine');

      cy.get(`[data-cy="photo2Description"]`).type('FTP c').should('have.value', 'FTP c');

      cy.setFieldImageAsBytesOfEntity('photo3', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo3Legende"]`).type('Directeur indigo').should('have.value', 'Directeur indigo');

      cy.get(`[data-cy="photo3Description"]`).type('Incredible Incredible indigo').should('have.value', 'Incredible Incredible indigo');

      cy.setFieldImageAsBytesOfEntity('photo4', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo4Legende"]`).type('intelligence').should('have.value', 'intelligence');

      cy.get(`[data-cy="photo4Description"]`).type('SCSI Account').should('have.value', 'SCSI Account');

      cy.setFieldImageAsBytesOfEntity('photo5', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo5Legende"]`)
        .type('relationships auxiliary Architecte')
        .should('have.value', 'relationships auxiliary Architecte');

      cy.get(`[data-cy="photo5Description"]`).type('PCI Peso interactive').should('have.value', 'PCI Peso interactive');

      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).should('not.be.checked');
      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).click().should('be.checked');

      cy.get(`[data-cy="usageBatiment"]`).select('BATIMENT_ADMINISTRATIF');

      cy.get(`[data-cy="usageBatimentAutre"]`).select('BATIMENT_EDUCATIF');

      cy.get(`[data-cy="cout"]`).type('49566').should('have.value', '49566');

      cy.get(`[data-cy="surfacePlancher"]`).type('45866').should('have.value', '45866');

      cy.get(`[data-cy="niveaux"]`).type('40741').should('have.value', '40741');

      cy.get(`[data-cy="travauxNeuf"]`).should('not.be.checked');
      cy.get(`[data-cy="travauxNeuf"]`).click().should('be.checked');

      cy.get(`[data-cy="travauxExtension"]`).should('not.be.checked');
      cy.get(`[data-cy="travauxExtension"]`).click().should('be.checked');

      cy.get(`[data-cy="travauxRenov"]`).should('not.be.checked');
      cy.get(`[data-cy="travauxRenov"]`).click().should('be.checked');

      cy.get(`[data-cy="travauxIte"]`).should('not.be.checked');
      cy.get(`[data-cy="travauxIte"]`).click().should('be.checked');

      cy.get(`[data-cy="travauxIti"]`).should('not.be.checked');
      cy.get(`[data-cy="travauxIti"]`).click().should('be.checked');

      cy.get(`[data-cy="constructionDebut"]`).type('2021-11-08').should('have.value', '2021-11-08');

      cy.get(`[data-cy="constructionFin"]`).type('2021-11-08').should('have.value', '2021-11-08');

      cy.get(`[data-cy="bottesTaille"]`).select('T_36_X_46_X_70_a_120_CM');

      cy.get(`[data-cy="botteTailleAutre"]`).type('Chair').should('have.value', 'Chair');

      cy.get(`[data-cy="bottesDensite"]`).type('82610').should('have.value', '82610');

      cy.get(`[data-cy="bottesCereale"]`).select('TRITICALE');

      cy.get(`[data-cy="distanceAppro"]`).type('36649').should('have.value', '36649');

      cy.get(`[data-cy="autoconstruction"]`).select('NON');

      cy.get(`[data-cy="participatif"]`).select('OUI');

      cy.get(`[data-cy="structCompl"]`).should('not.be.checked');
      cy.get(`[data-cy="structCompl"]`).click().should('be.checked');

      cy.get(`[data-cy="structComplNature"]`).select('BOIS');

      cy.get(`[data-cy="structComplAutre"]`).type('hacking Metal').should('have.value', 'hacking Metal');

      cy.get(`[data-cy="structComplNaturePrecision"]`)
        .type('Franche-Comté project wireless')
        .should('have.value', 'Franche-Comté project wireless');

      cy.get(`[data-cy="longMaxSansMurRefend"]`).type('93843').should('have.value', '93843');

      cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
      cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

      cy.get(`[data-cy="nbrRangDeBottes"]`).type('65545').should('have.value', '65545');

      cy.get(`[data-cy="integBaie"]`).select('PRE_CADRE_FLOTTANT');

      cy.get(`[data-cy="integBaieAutre"]`).type('solution up').should('have.value', 'solution up');

      cy.get(`[data-cy="supportAncrage"]`).select('METAL');

      cy.get(`[data-cy="supportAncrageAutre"]`).type('EXE override').should('have.value', 'EXE override');

      cy.get(`[data-cy="revetInt"]`).select('ENDUIT_PLATRE');

      cy.get(`[data-cy="revetIntAutre"]`).type('architecture a Zimbabwe').should('have.value', 'architecture a Zimbabwe');

      cy.get(`[data-cy="revetExt"]`).select('ENDUIT_TERRE_ET_CHAUX');

      cy.get(`[data-cy="revetExtAutre"]`).type('transform Games BCEAO').should('have.value', 'transform Games BCEAO');

      cy.get(`[data-cy="maitreDOuvrage"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="maitreDOeuvre"]`).type('d&#39;Orsel').should('have.value', 'd&#39;Orsel');

      cy.get(`[data-cy="architecte"]`).type('strategic copying').should('have.value', 'strategic copying');

      cy.get(`[data-cy="bureauDEtudeStructure"]`).type('Hat programming').should('have.value', 'Hat programming');

      cy.get(`[data-cy="bureauControl"]`).type('Incredible').should('have.value', 'Incredible');

      cy.get(`[data-cy="entrepriseBottes"]`).type('Table visionary b').should('have.value', 'Table visionary b');

      cy.get(`[data-cy="entrepriseCharpente"]`).type('cross-media schemas').should('have.value', 'cross-media schemas');

      cy.get(`[data-cy="entrepriseEnduits"]`).type('microchip').should('have.value', 'microchip');

      cy.get(`[data-cy="descriptionProjet"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="difficultees"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="astuces"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="divers"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="contactNom"]`).type('streamline des Steel').should('have.value', 'streamline des Steel');

      cy.get(`[data-cy="contactMail"]`).type('transform Dollar').should('have.value', 'transform Dollar');

      cy.get(`[data-cy="contactPhone"]`).type('Outdoors').should('have.value', 'Outdoors');

      cy.get(`[data-cy="codePostal"]`).type('encomp').should('have.value', 'encomp');

      cy.get(`[data-cy="createdDate"]`).type('2021-11-08T19:56').should('have.value', '2021-11-08T19:56');

      cy.get(`[data-cy="lastModifiedDate"]`).type('2021-11-08T00:44').should('have.value', '2021-11-08T00:44');

      cy.get(`[data-cy="createdBy"]`).type('Lituanie').should('have.value', 'Lituanie');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        batiment = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', batimentPageUrlPattern);
    });
  });
});
