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

describe('Batiments e2e test', () => {
  const batimentsPageUrl = '/batiments';
  const batimentsPageUrlPattern = new RegExp('/batiments(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'admin';
  const password = Cypress.env('E2E_PASSWORD') ?? 'admin';
  const batimentsSample = { latitude: -15, longitude: -51, dateCreationFiche: '2021-11-06', dateModificationFiche: '2021-11-05' };

  let batiments: any;

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
    if (batiments) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/batiments/${batiments.id}`,
      }).then(() => {
        batiments = undefined;
      });
    }
  });

  afterEach(() => {
    cy.oauthLogout();
    cy.clearCache();
  });

  it('Batiments menu should load Batiments page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('batiments');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Batiments').should('exist');
    cy.url().should('match', batimentsPageUrlPattern);
  });

  describe('Batiments page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(batimentsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Batiments page', () => {
        cy.get(entityCreateButtonSelector).click({ force: true });
        cy.url().should('match', new RegExp('/batiments/new$'));
        cy.getEntityCreateUpdateHeading('Batiments');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/batiments',
          body: batimentsSample,
        }).then(({ body }) => {
          batiments = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/batiments+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [batiments],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(batimentsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Batiments page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('batiments');
        cy.get(entityDetailsBackButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentsPageUrlPattern);
      });

      it('edit button click should load edit Batiments page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Batiments');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click({ force: true });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentsPageUrlPattern);
      });

      it('last delete button click should delete instance of Batiments', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('batiments').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentsPageUrlPattern);

        batiments = undefined;
      });
    });
  });

  describe('new Batiments page', () => {
    beforeEach(() => {
      cy.visit(`${batimentsPageUrl}`);
      cy.get(entityCreateButtonSelector).click({ force: true });
      cy.getEntityCreateUpdateHeading('Batiments');
    });

    it('should create an instance of Batiments', () => {
      cy.get(`[data-cy="latitude"]`).type('-84').should('have.value', '-84');

      cy.get(`[data-cy="longitude"]`).type('56').should('have.value', '56');

      cy.get(`[data-cy="nomBatiment"]`).type('Assistant drive a').should('have.value', 'Assistant drive a');

      cy.setFieldImageAsBytesOfEntity('photoPrincipale', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photoPrincipaleLegende"]`).type('monitor').should('have.value', 'monitor');

      cy.get(`[data-cy="photoPrincipaleDescription"]`).type('a HDD networks').should('have.value', 'a HDD networks');

      cy.setFieldImageAsBytesOfEntity('photo1', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo1Legende"]`).type('programming c').should('have.value', 'programming c');

      cy.get(`[data-cy="photo1Description"]`)
        .type('contextually-based SMTP Bedfordshire')
        .should('have.value', 'contextually-based SMTP Bedfordshire');

      cy.setFieldImageAsBytesOfEntity('photo2', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo2Legende"]`).type('digital groupware a').should('have.value', 'digital groupware a');

      cy.get(`[data-cy="photo2Description"]`).type('Concrete').should('have.value', 'Concrete');

      cy.setFieldImageAsBytesOfEntity('photo3', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo3Legende"]`).type('paradigm').should('have.value', 'paradigm');

      cy.get(`[data-cy="photo3Description"]`).type('Small wireless').should('have.value', 'Small wireless');

      cy.setFieldImageAsBytesOfEntity('photo4', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo4Legende"]`).type('bandwidth').should('have.value', 'bandwidth');

      cy.get(`[data-cy="photo4Description"]`).type('Pants XML').should('have.value', 'Pants XML');

      cy.setFieldImageAsBytesOfEntity('photo5', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="photo5Legende"]`).type('PNG').should('have.value', 'PNG');

      cy.get(`[data-cy="photo5Description"]`).type('SAS radical navigate').should('have.value', 'SAS radical navigate');

      cy.get(`[data-cy="techniqueSecondaire"]`).should('not.be.checked');
      cy.get(`[data-cy="techniqueSecondaire"]`).click().should('be.checked');

      cy.get(`[data-cy="usageBatiment"]`).select('BATIMENT_AGRICOLE');

      cy.get(`[data-cy="cout"]`).type('51944').should('have.value', '51944');

      cy.get(`[data-cy="surfacePlancher"]`).type('45043').should('have.value', '45043');

      cy.get(`[data-cy="niveaux"]`).type('66260').should('have.value', '66260');

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

      cy.get(`[data-cy="constructionDebut"]`).type('2021-11-05').should('have.value', '2021-11-05');

      cy.get(`[data-cy="constructionFin"]`).type('2021-11-05').should('have.value', '2021-11-05');

      cy.get(`[data-cy="bottesTaille"]`).select('T_50_X_80_X_110_a_200_CM');

      cy.get(`[data-cy="botteTailleAutre"]`).type('envisioneer Steel').should('have.value', 'envisioneer Steel');

      cy.get(`[data-cy="bottesDensite"]`).type('12279').should('have.value', '12279');

      cy.get(`[data-cy="bottesCereale"]`).select('RIZ');

      cy.get(`[data-cy="distanceAppro"]`).type('76283').should('have.value', '76283');

      cy.get(`[data-cy="autoconstruction"]`).select('PARTIEL');

      cy.get(`[data-cy="participatif"]`).select('NON');

      cy.get(`[data-cy="structSuppl"]`).should('not.be.checked');
      cy.get(`[data-cy="structSuppl"]`).click().should('be.checked');

      cy.get(`[data-cy="structSupplNature"]`).select('MACONNERIE');

      cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
      cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

      cy.get(`[data-cy="nbrRangDeBottes"]`).type('49393').should('have.value', '49393');

      cy.get(`[data-cy="longMaxSansMurRefend"]`).type('20589').should('have.value', '20589');

      cy.get(`[data-cy="integBaie"]`).select('FIXE');

      cy.get(`[data-cy="supportAncrage"]`).select('AUTRE');

      cy.get(`[data-cy="supportAncragePrecisions"]`).type('Outdoors SMS').should('have.value', 'Outdoors SMS');

      cy.get(`[data-cy="revetInt"]`).select('ENDUIT_TERRE_ET_CHAUX');

      cy.get(`[data-cy="revetExt"]`).select('BARDAGE_VENTILE');

      cy.get(`[data-cy="revetExtAutre"]`).type('Chat-qui-Pêche').should('have.value', 'Chat-qui-Pêche');

      cy.get(`[data-cy="maitreDOuvrage"]`).type('gold synergistic').should('have.value', 'gold synergistic');

      cy.get(`[data-cy="maitreDOeuvre"]`).type('panel deploy').should('have.value', 'panel deploy');

      cy.get(`[data-cy="architecte"]`).type('Mandatory').should('have.value', 'Mandatory');

      cy.get(`[data-cy="bureauDEtudeStructure"]`).type('c hack Electronics').should('have.value', 'c hack Electronics');

      cy.get(`[data-cy="bureauControl"]`).type('maroon turquoise blue').should('have.value', 'maroon turquoise blue');

      cy.get(`[data-cy="entrepriseBottes"]`).type('edge').should('have.value', 'edge');

      cy.get(`[data-cy="entrepriseCharpente"]`).type('Plastic payment Beauty').should('have.value', 'Plastic payment Beauty');

      cy.get(`[data-cy="entrepriseEnduits"]`).type('Horizontal c array').should('have.value', 'Horizontal c array');

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

      cy.get(`[data-cy="contactNom"]`).type('Steel deposit').should('have.value', 'Steel deposit');

      cy.get(`[data-cy="contactMail"]`).type('disintermediate initiatives').should('have.value', 'disintermediate initiatives');

      cy.get(`[data-cy="contactPhone"]`).type('copying Producteur').should('have.value', 'copying Producteur');

      cy.get(`[data-cy="codePostal"]`).type('c revo').should('have.value', 'c revo');

      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).should('not.be.checked');
      cy.get(`[data-cy="nonBatimentEtPhotosPublics"]`).click().should('be.checked');

      cy.get(`[data-cy="dateCreationFiche"]`).type('2021-11-06').should('have.value', '2021-11-06');

      cy.get(`[data-cy="dateModificationFiche"]`).type('2021-11-06').should('have.value', '2021-11-06');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        batiments = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', batimentsPageUrlPattern);
    });
  });
});
