/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import BatimentDetailComponent from '@/entities/batiment/batiment-details.vue';
import BatimentClass from '@/entities/batiment/batiment-details.component';
import BatimentService from '@/entities/batiment/batiment.service';
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
  describe('Batiment Management Detail Component', () => {
    let wrapper: Wrapper<BatimentClass>;
    let comp: BatimentClass;
    let batimentServiceStub: SinonStubbedInstance<BatimentService>;

    beforeEach(() => {
      batimentServiceStub = sinon.createStubInstance<BatimentService>(BatimentService);

      wrapper = shallowMount<BatimentClass>(BatimentDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { batimentService: () => batimentServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBatiment = { id: 123 };
        batimentServiceStub.find.resolves(foundBatiment);

        // WHEN
        comp.retrieveBatiment(123);
        await comp.$nextTick();

        // THEN
        expect(comp.batiment).toBe(foundBatiment);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBatiment = { id: 123 };
        batimentServiceStub.find.resolves(foundBatiment);

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
