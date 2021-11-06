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
  const batimentSample = { latitude: -21, longitude: -80, dateCreationFiche: '2021-11-06', dateModificationFiche: '2021-11-06' };

  let batiment: any;

  beforeEach(() => {
    cy.getOauth2Data();
    cy.get('@oauth2Data').then(oauth2Data => {
      cy.oauthLogin(oauth2Data, username, password);
    });
    cy.intercept('GET', '/api/batiments').as('entitiesRequest');
    cy.visit('');
    cy.get(entityItemSelector).should('exist');
  });

  beforeEach(() => {
    Cypress.Cookies.preserveOnce('XSRF-TOKEN', 'JSESSIONID');
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

  afterEach(() => {
    cy.oauthLogout();
    cy.clearCache();
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

      cy.get(`[data-cy="techniqueSecondaire"]`).should('not.be.checked');
      cy.get(`[data-cy="techniqueSecondaire"]`).click().should('be.checked');

      cy.get(`[data-cy="usageBatiment"]`).select('BATIMENT_ADMINISTRATIF');

      cy.get(`[data-cy="cout"]`).type('65659').should('have.value', '65659');

      cy.get(`[data-cy="surfacePlancher"]`).type('49566').should('have.value', '49566');

      cy.get(`[data-cy="niveaux"]`).type('45866').should('have.value', '45866');

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

      cy.get(`[data-cy="constructionDebut"]`).type('2021-11-06').should('have.value', '2021-11-06');

      cy.get(`[data-cy="constructionFin"]`).type('2021-11-06').should('have.value', '2021-11-06');

      cy.get(`[data-cy="bottesTaille"]`).select('T_50_X_80_X_110_a_200_CM');

      cy.get(`[data-cy="botteTailleAutre"]`).type('Tuna Corse').should('have.value', 'Tuna Corse');

      cy.get(`[data-cy="bottesDensite"]`).type('7630').should('have.value', '7630');

      cy.get(`[data-cy="bottesCereale"]`).select('AVOINE');

      cy.get(`[data-cy="distanceAppro"]`).type('17001').should('have.value', '17001');

      cy.get(`[data-cy="autoconstruction"]`).select('NON');

      cy.get(`[data-cy="participatif"]`).select('PARTIEL');

      cy.get(`[data-cy="structSuppl"]`).should('not.be.checked');
      cy.get(`[data-cy="structSuppl"]`).click().should('be.checked');

      cy.get(`[data-cy="structSupplNature"]`).select('BETON_ARME');

      cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
      cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

      cy.get(`[data-cy="nbrRangDeBottes"]`).type('69899').should('have.value', '69899');

      cy.get(`[data-cy="longMaxSansMurRefend"]`).type('84580').should('have.value', '84580');

      cy.get(`[data-cy="integBaie"]`).select('AUTRE');

      cy.get(`[data-cy="supportAncrage"]`).select('METAL');

      cy.get(`[data-cy="supportAncragePrecisions"]`).type('Plastic Guinée').should('have.value', 'Plastic Guinée');

      cy.get(`[data-cy="revetInt"]`).select('PLAQUE_DE_PLATRE');

      cy.get(`[data-cy="revetExt"]`).select('AUTRE');

      cy.get(`[data-cy="revetExtAutre"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="maitreDOuvrage"]`).type('PNG').should('have.value', 'PNG');

      cy.get(`[data-cy="maitreDOeuvre"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="architecte"]`).type('Liberia lime a').should('have.value', 'Liberia lime a');

      cy.get(`[data-cy="bureauDEtudeStructure"]`).type('a').should('have.value', 'a');

      cy.get(`[data-cy="bureauControl"]`).type('Manager b red').should('have.value', 'Manager b red');

      cy.get(`[data-cy="entrepriseBottes"]`).type('BCEAO Metal').should('have.value', 'BCEAO Metal');

      cy.get(`[data-cy="entrepriseCharpente"]`).type('Granite reintermediate').should('have.value', 'Granite reintermediate');

      cy.get(`[data-cy="entrepriseEnduits"]`).type('Plastic').should('have.value', 'Plastic');

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

      cy.get(`[data-cy="contactNom"]`).type('Comores').should('have.value', 'Comores');

      cy.get(`[data-cy="contactMail"]`).type('back toolset cyan').should('have.value', 'back toolset cyan');

      cy.get(`[data-cy="contactPhone"]`).type('Pants Loan').should('have.value', 'Pants Loan');

      cy.get(`[data-cy="codePostal"]`).type('withdr').should('have.value', 'withdr');

      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).should('not.be.checked');
      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).click().should('be.checked');

      cy.get(`[data-cy="dateCreationFiche"]`).type('2021-11-06').should('have.value', '2021-11-06');

      cy.get(`[data-cy="dateModificationFiche"]`).type('2021-11-05').should('have.value', '2021-11-05');

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
