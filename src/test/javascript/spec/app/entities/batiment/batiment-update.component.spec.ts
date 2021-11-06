/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import BatimentUpdateComponent from '@/entities/batiment/batiment-update.vue';
import BatimentClass from '@/entities/batiment/batiment-update.component';
import BatimentService from '@/entities/batiment/batiment.service';

import UserOAuth2Service from '@/entities/user/user.oauth2.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Batiment Management Update Component', () => {
    let wrapper: Wrapper<BatimentClass>;
    let comp: BatimentClass;
    let batimentServiceStub: SinonStubbedInstance<BatimentService>;

    beforeEach(() => {
      batimentServiceStub = sinon.createStubInstance<BatimentService>(BatimentService);

      wrapper = shallowMount<BatimentClass>(BatimentUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          batimentService: () => batimentServiceStub,
          alertService: () => new AlertService(),

          userOAuth2Service: () => new UserOAuth2Service(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.batiment = entity;
        batimentServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(batimentServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.batiment = entity;
        batimentServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(batimentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBatiment = { id: 123 };
        batimentServiceStub.find.resolves(foundBatiment);
        batimentServiceStub.retrieve.resolves([foundBatiment]);

        // WHEN
        comp.beforeRouteEnter({ params: { batimentId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.batiment).toBe(foundBatiment);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
