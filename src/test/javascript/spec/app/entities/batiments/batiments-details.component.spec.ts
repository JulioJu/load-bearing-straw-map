/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import BatimentsDetailComponent from '@/entities/batiments/batiments-details.vue';
import BatimentsClass from '@/entities/batiments/batiments-details.component';
import BatimentsService from '@/entities/batiments/batiments.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Batiments Management Detail Component', () => {
    let wrapper: Wrapper<BatimentsClass>;
    let comp: BatimentsClass;
    let batimentsServiceStub: SinonStubbedInstance<BatimentsService>;

    beforeEach(() => {
      batimentsServiceStub = sinon.createStubInstance<BatimentsService>(BatimentsService);

      wrapper = shallowMount<BatimentsClass>(BatimentsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { batimentsService: () => batimentsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBatiments = { id: 123 };
        batimentsServiceStub.find.resolves(foundBatiments);

        // WHEN
        comp.retrieveBatiments(123);
        await comp.$nextTick();

        // THEN
        expect(comp.batiments).toBe(foundBatiments);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBatiments = { id: 123 };
        batimentsServiceStub.find.resolves(foundBatiments);

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
