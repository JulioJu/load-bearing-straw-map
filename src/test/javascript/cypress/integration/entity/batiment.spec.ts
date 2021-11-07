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
  const batimentSample = {
    latitude: 21,
    longitude: -69,
    createdDate: '2021-11-07T17:31:00.029Z',
    lastModifiedDate: '2021-11-07T19:55:14.121Z',
  };

  let batiment: any;
  //let user: any;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });
    cy.visit('');
    cy.login(username, password);
    cy.get(entityItemSelector).should('exist');
  });

  /* Disabled due to incompatibility
  beforeEach(() => {
    // create an instance at the required relationship entity:
    cy.authenticatedRequest({
      method: 'POST',
      url: '/api/users',
      body: {"login":"c blue","firstName":"Céleste","lastName":"Lopez"},
    }).then(({ body }) => {
      user = body;
    });
  });
   */

  beforeEach(() => {
    cy.intercept('GET', '/api/batiments+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/batiments').as('postEntityRequest');
    cy.intercept('DELETE', '/api/batiments/*').as('deleteEntityRequest');
  });

  /* Disabled due to incompatibility
  beforeEach(() => {
    // Simulate relationships api for better performance and reproducibility.
    cy.intercept('GET', '/api/users', {
      statusCode: 200,
      body: [user],
    });

  });
   */

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

  /* Disabled due to incompatibility
  afterEach(() => {
    if (user) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/users/${user.id}`,
      }).then(() => {
        user = undefined;
      });
    }
  });
   */

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
      /* Disabled due to incompatibility
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/batiments',
  
          body: {
            ...batimentSample,
            createdBy: user,
          },
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
       */

      beforeEach(function () {
        cy.visit(batimentPageUrl);

        cy.wait('@entitiesRequest').then(({ response }) => {
          if (response!.body.length === 0) {
            this.skip();
          }
        });
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

      it.skip('last delete button click should delete instance of Batiment', () => {
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

    it.skip('should create an instance of Batiment', () => {
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

      cy.get(`[data-cy="usageBatiment"]`).select('AUTRE');

      cy.get(`[data-cy="cout"]`).type('23718').should('have.value', '23718');

      cy.get(`[data-cy="surfacePlancher"]`).type('65659').should('have.value', '65659');

      cy.get(`[data-cy="niveaux"]`).type('49566').should('have.value', '49566');

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

      cy.get(`[data-cy="constructionDebut"]`).type('2021-11-07').should('have.value', '2021-11-07');

      cy.get(`[data-cy="constructionFin"]`).type('2021-11-07').should('have.value', '2021-11-07');

      cy.get(`[data-cy="bottesTaille"]`).select('T_70_X_120_X_230_CM');

      cy.get(`[data-cy="botteTailleAutre"]`).type('Australie Baby').should('have.value', 'Australie Baby');

      cy.get(`[data-cy="bottesDensite"]`).type('58951').should('have.value', '58951');

      cy.get(`[data-cy="bottesCereale"]`).select('AVOINE');

      cy.get(`[data-cy="distanceAppro"]`).type('62424').should('have.value', '62424');

      cy.get(`[data-cy="autoconstruction"]`).select('OUI');

      cy.get(`[data-cy="participatif"]`).select('OUI');

      cy.get(`[data-cy="structCompl"]`).should('not.be.checked');
      cy.get(`[data-cy="structCompl"]`).click().should('be.checked');

      cy.get(`[data-cy="structComplNature"]`).select('METAL');

      cy.get(`[data-cy="structComplNatureAutre"]`)
        .type('Shoes Uruguayo Franche-Comté')
        .should('have.value', 'Shoes Uruguayo Franche-Comté');

      cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
      cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

      cy.get(`[data-cy="nbrRangDeBottes"]`).type('79122').should('have.value', '79122');

      cy.get(`[data-cy="longMaxSansMurRefend"]`).type('54816').should('have.value', '54816');

      cy.get(`[data-cy="integBaie"]`).select('COULISSANT');

      cy.get(`[data-cy="supportAncrage"]`).select('AUTRE');

      cy.get(`[data-cy="supportAncrageAutre"]`).type('a').should('have.value', 'a');

      cy.get(`[data-cy="revetInt"]`).select('PLAQUE_DE_PLATRE');

      cy.get(`[data-cy="revetExt"]`).select('ENDUIT_TERRE_ET_CHAUX');

      cy.get(`[data-cy="revetExtAutre"]`).type('PNG').should('have.value', 'PNG');

      cy.get(`[data-cy="maitreDOuvrage"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="maitreDOeuvre"]`).type('Liberia lime a').should('have.value', 'Liberia lime a');

      cy.get(`[data-cy="architecte"]`).type('a').should('have.value', 'a');

      cy.get(`[data-cy="bureauDEtudeStructure"]`).type('Manager b red').should('have.value', 'Manager b red');

      cy.get(`[data-cy="bureauControl"]`).type('BCEAO Metal').should('have.value', 'BCEAO Metal');

      cy.get(`[data-cy="entrepriseBottes"]`).type('Granite reintermediate').should('have.value', 'Granite reintermediate');

      cy.get(`[data-cy="entrepriseCharpente"]`).type('Plastic').should('have.value', 'Plastic');

      cy.get(`[data-cy="entrepriseEnduits"]`).type('Comores').should('have.value', 'Comores');

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

      cy.get(`[data-cy="contactNom"]`).type('back toolset cyan').should('have.value', 'back toolset cyan');

      cy.get(`[data-cy="contactMail"]`).type('Pants Loan').should('have.value', 'Pants Loan');

      cy.get(`[data-cy="contactPhone"]`).type('withdrawal redundant overriding').should('have.value', 'withdrawal redundant overriding');

      cy.get(`[data-cy="codePostal"]`).type('Pizza').should('have.value', 'Pizza');

      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).should('not.be.checked');
      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).click().should('be.checked');

      cy.get(`[data-cy="createdDate"]`).type('2021-11-07T15:06').should('have.value', '2021-11-07T15:06');

      cy.get(`[data-cy="lastModifiedDate"]`).type('2021-11-07T09:23').should('have.value', '2021-11-07T09:23');

      cy.get(`[data-cy="createdBy"]`).select(1);

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
