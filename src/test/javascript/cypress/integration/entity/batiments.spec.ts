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

    cy.get(`[data-cy="techniqueSecondaire"]`).should('not.be.checked');
    cy.get(`[data-cy="techniqueSecondaire"]`).click().should('be.checked');

    cy.get(`[data-cy="usage"]`).select('BATIMENT_SOCIO_CULTUREl');

    cy.get(`[data-cy="cout"]`).type('72313').should('have.value', '72313');

    cy.get(`[data-cy="surface"]`).type('19088').should('have.value', '19088');

    cy.get(`[data-cy="niveaux"]`).type('81520').should('have.value', '81520');

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

    cy.get(`[data-cy="constructionDebut"]`).type('2021-10-17').should('have.value', '2021-10-17');

    cy.get(`[data-cy="constructionFin"]`).type('2021-10-17').should('have.value', '2021-10-17');

    cy.get(`[data-cy="bottesTaille"]`).select('AUTRE');

    cy.get(`[data-cy="bottesDensite"]`).type('55711').should('have.value', '55711');

    cy.get(`[data-cy="bottesCereale"]`).select('SEIGLE');

    cy.get(`[data-cy="distanceAppro"]`).type('84962').should('have.value', '84962');

    cy.get(`[data-cy="autoconstruction"]`).select('PARTIEL');

    cy.get(`[data-cy="participatif"]`).select('NON');

    cy.get(`[data-cy="integBaie"]`).select('AUTRE');

    cy.get(`[data-cy="structSuppl"]`).should('not.be.checked');
    cy.get(`[data-cy="structSuppl"]`).click().should('be.checked');

    cy.get(`[data-cy="noteCalcul"]`).should('not.be.checked');
    cy.get(`[data-cy="noteCalcul"]`).click().should('be.checked');

    cy.get(`[data-cy="materiauSb"]`).select('PIERRE');

    cy.get(`[data-cy="revetInt"]`).select('ENDUIT_TERRE');

    cy.get(`[data-cy="revetExt"]`).select('ENDUIT_TERRE');

    cy.get(`[data-cy="concepteur"]`).type('SMTP').should('have.value', 'SMTP');

    cy.get(`[data-cy="realisateur"]`).type('didactic digital').should('have.value', 'didactic digital');

    cy.get(`[data-cy="description"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="contactNom"]`).type('repurpose').should('have.value', 'repurpose');

    cy.get(`[data-cy="contactMail"]`).type('Checking').should('have.value', 'Checking');

    cy.get(`[data-cy="contactPhone"]`).type('bottom-line Incredible generate').should('have.value', 'bottom-line Incredible generate');

    cy.get(`[data-cy="codePostal"]`).type('Bacon').should('have.value', 'Bacon');

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
