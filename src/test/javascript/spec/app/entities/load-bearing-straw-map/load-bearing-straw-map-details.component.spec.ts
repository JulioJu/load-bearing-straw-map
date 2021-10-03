/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LoadBearingStrawMapDetailComponent from '@/entities/load-bearing-straw-map/load-bearing-straw-map-details.vue';
import LoadBearingStrawMapClass from '@/entities/load-bearing-straw-map/load-bearing-straw-map-details.component';
import LoadBearingStrawMapService from '@/entities/load-bearing-straw-map/load-bearing-straw-map.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('LoadBearingStrawMap Management Detail Component', () => {
    let wrapper: Wrapper<LoadBearingStrawMapClass>;
    let comp: LoadBearingStrawMapClass;
    let loadBearingStrawMapServiceStub: SinonStubbedInstance<LoadBearingStrawMapService>;

    beforeEach(() => {
      loadBearingStrawMapServiceStub = sinon.createStubInstance<LoadBearingStrawMapService>(LoadBearingStrawMapService);

      wrapper = shallowMount<LoadBearingStrawMapClass>(LoadBearingStrawMapDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { loadBearingStrawMapService: () => loadBearingStrawMapServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLoadBearingStrawMap = { id: 123 };
        loadBearingStrawMapServiceStub.find.resolves(foundLoadBearingStrawMap);

        // WHEN
        comp.retrieveLoadBearingStrawMap(123);
        await comp.$nextTick();

        // THEN
        expect(comp.loadBearingStrawMap).toBe(foundLoadBearingStrawMap);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLoadBearingStrawMap = { id: 123 };
        loadBearingStrawMapServiceStub.find.resolves(foundLoadBearingStrawMap);

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
