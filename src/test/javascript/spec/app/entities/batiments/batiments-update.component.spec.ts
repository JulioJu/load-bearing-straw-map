/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import BatimentsUpdateComponent from '@/entities/batiments/batiments-update.vue';
import BatimentsClass from '@/entities/batiments/batiments-update.component';
import BatimentsService from '@/entities/batiments/batiments.service';

import UserService from '@/admin/user-management/user-management.service';
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
  describe('Batiments Management Update Component', () => {
    let wrapper: Wrapper<BatimentsClass>;
    let comp: BatimentsClass;
    let batimentsServiceStub: SinonStubbedInstance<BatimentsService>;

    beforeEach(() => {
      batimentsServiceStub = sinon.createStubInstance<BatimentsService>(BatimentsService);

      wrapper = shallowMount<BatimentsClass>(BatimentsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          batimentsService: () => batimentsServiceStub,
          alertService: () => new AlertService(),

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.batiments = entity;
        batimentsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(batimentsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.batiments = entity;
        batimentsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(batimentsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBatiments = { id: 123 };
        batimentsServiceStub.find.resolves(foundBatiments);
        batimentsServiceStub.retrieve.resolves([foundBatiments]);

        // WHEN
        comp.beforeRouteEnter({ params: { batimentsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.batiments).toBe(foundBatiments);
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
