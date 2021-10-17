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

  it('should load Batiments', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('batiments');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Batiments').should('exist');
    cy.url().should('match', batimentsPageUrlPattern);
  });

  it('should load details Batiments page', function () {
    cy.visit(batimentsPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('batiments');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', batimentsPageUrlPattern);
  });

  it('should load create Batiments page', () => {
    cy.visit(batimentsPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Batiments');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', batimentsPageUrlPattern);
  });

  it('should load edit Batiments page', function () {
    cy.visit(batimentsPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('Batiments');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', batimentsPageUrlPattern);
  });

  it('should create an instance of Batiments', () => {
    cy.visit(batimentsPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Batiments');

    cy.get(`[data-cy="latitude"]`).type('-84').should('have.value', '-84');

    cy.get(`[data-cy="longitude"]`).type('56').should('have.value', '56');

    cy.get(`[data-cy="nom"]`).type('Assistant drive a').should('have.value', 'Assistant drive a');

    cy.get(`[data-cy="contactNom"]`).type('monitor').should('have.value', 'monitor');

    cy.get(`[data-cy="contactMail"]`).type('a HDD networks').should('have.value', 'a HDD networks');

    cy.get(`[data-cy="contactPhone"]`).type('programming c').should('have.value', 'programming c');

    cy.get(`[data-cy="constructionDebut"]`).type('2021-10-16').should('have.value', '2021-10-16');

    cy.get(`[data-cy="constructionFin"]`).type('2021-10-17').should('have.value', '2021-10-17');

    cy.get(`[data-cy="surface"]`).type('23500').should('have.value', '23500');

    cy.get(`[data-cy="cout"]`).type('7110').should('have.value', '7110');

    cy.get(`[data-cy="bottesTaille"]`).select('AUTRE');

    cy.get(`[data-cy="autoconstruction"]`).select('NON');

    cy.get(`[data-cy="concepteur"]`).type('Bedfordshire transmit').should('have.value', 'Bedfordshire transmit');

    cy.get(`[data-cy="realisateur"]`).type('groupware a').should('have.value', 'groupware a');

    cy.get(`[data-cy="participatif"]`).select('OUI');

    cy.get(`[data-cy="usage"]`).select('LOGEMENT_COLLECTIF');

    cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
    cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

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

    cy.get(`[data-cy="niveaux"]`).type('71776').should('have.value', '71776');

    cy.get(`[data-cy="bottesDensite"]`).type('74588').should('have.value', '74588');

    cy.get(`[data-cy="distanceAppro"]`).type('51377').should('have.value', '51377');

    cy.get(`[data-cy="bottesCereale"]`).select('BLE');

    cy.get(`[data-cy="structSuppl"]`).should('not.be.checked');
    cy.get(`[data-cy="structSuppl"]`).click().should('be.checked');

    cy.get(`[data-cy="revetInt"]`).select('PLAQUE_DE_PLATRE');

    cy.get(`[data-cy="revetExt"]`).select('BARDAGE_VENTILE');

    cy.get(`[data-cy="techniqueSecondaire"]`).should('not.be.checked');
    cy.get(`[data-cy="techniqueSecondaire"]`).click().should('be.checked');

    cy.get(`[data-cy="codePostal"]`).type('Afghan').should('have.value', 'Afghan');

    cy.get(`[data-cy="integBaie"]`).select('PRE_CADRE_FLOTTANT');

    cy.get(`[data-cy="materiauSb"]`).select('AUTRE');

    cy.get(`[data-cy="description"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', batimentsPageUrlPattern);
  });

  it('should delete last instance of Batiments', function () {
    cy.visit(batimentsPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.getEntityDeleteDialogHeading('batiments').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', batimentsPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
