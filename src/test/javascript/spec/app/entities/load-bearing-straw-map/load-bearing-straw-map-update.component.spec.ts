/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import LoadBearingStrawMapUpdateComponent from '@/entities/load-bearing-straw-map/load-bearing-straw-map-update.vue';
import LoadBearingStrawMapClass from '@/entities/load-bearing-straw-map/load-bearing-straw-map-update.component';
import LoadBearingStrawMapService from '@/entities/load-bearing-straw-map/load-bearing-straw-map.service';

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
  describe('LoadBearingStrawMap Management Update Component', () => {
    let wrapper: Wrapper<LoadBearingStrawMapClass>;
    let comp: LoadBearingStrawMapClass;
    let loadBearingStrawMapServiceStub: SinonStubbedInstance<LoadBearingStrawMapService>;

    beforeEach(() => {
      loadBearingStrawMapServiceStub = sinon.createStubInstance<LoadBearingStrawMapService>(LoadBearingStrawMapService);

      wrapper = shallowMount<LoadBearingStrawMapClass>(LoadBearingStrawMapUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          loadBearingStrawMapService: () => loadBearingStrawMapServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.loadBearingStrawMap = entity;
        loadBearingStrawMapServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(loadBearingStrawMapServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.loadBearingStrawMap = entity;
        loadBearingStrawMapServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(loadBearingStrawMapServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLoadBearingStrawMap = { id: 123 };
        loadBearingStrawMapServiceStub.find.resolves(foundLoadBearingStrawMap);
        loadBearingStrawMapServiceStub.retrieve.resolves([foundLoadBearingStrawMap]);

        // WHEN
        comp.beforeRouteEnter({ params: { loadBearingStrawMapId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.loadBearingStrawMap).toBe(foundLoadBearingStrawMap);
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
