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

describe('LoadBearingStrawMap e2e test', () => {
  const loadBearingStrawMapPageUrl = '/load-bearing-straw-map';
  const loadBearingStrawMapPageUrlPattern = new RegExp('/load-bearing-straw-map(\\?.*)?$');
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
    cy.intercept('GET', '/api/load-bearing-straw-maps+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/load-bearing-straw-maps').as('postEntityRequest');
    cy.intercept('DELETE', '/api/load-bearing-straw-maps/*').as('deleteEntityRequest');
  });

  it('should load LoadBearingStrawMaps', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('load-bearing-straw-map');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('LoadBearingStrawMap').should('exist');
    cy.url().should('match', loadBearingStrawMapPageUrlPattern);
  });

  it('should load details LoadBearingStrawMap page', function () {
    cy.visit(loadBearingStrawMapPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('loadBearingStrawMap');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', loadBearingStrawMapPageUrlPattern);
  });

  it('should load create LoadBearingStrawMap page', () => {
    cy.visit(loadBearingStrawMapPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('LoadBearingStrawMap');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', loadBearingStrawMapPageUrlPattern);
  });

  it('should load edit LoadBearingStrawMap page', function () {
    cy.visit(loadBearingStrawMapPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('LoadBearingStrawMap');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', loadBearingStrawMapPageUrlPattern);
  });

  it('should create an instance of LoadBearingStrawMap', () => {
    cy.visit(loadBearingStrawMapPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('LoadBearingStrawMap');

    cy.get(`[data-cy="name"]`).type('Barbade').should('have.value', 'Barbade');

    cy.get(`[data-cy="latitude"]`).type('-54').should('have.value', '-54');

    cy.get(`[data-cy="longitude"]`).type('12').should('have.value', '12');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', loadBearingStrawMapPageUrlPattern);
  });

  it('should delete last instance of LoadBearingStrawMap', function () {
    cy.visit(loadBearingStrawMapPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.getEntityDeleteDialogHeading('loadBearingStrawMap').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', loadBearingStrawMapPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
