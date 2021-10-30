/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BatimentsComponent from '@/entities/batiments/batiments.vue';
import BatimentsClass from '@/entities/batiments/batiments.component';
import BatimentsService from '@/entities/batiments/batiments.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Batiments Management Component', () => {
    let wrapper: Wrapper<BatimentsClass>;
    let comp: BatimentsClass;
    let batimentsServiceStub: SinonStubbedInstance<BatimentsService>;

    beforeEach(() => {
      batimentsServiceStub = sinon.createStubInstance<BatimentsService>(BatimentsService);
      batimentsServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BatimentsClass>(BatimentsComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          batimentsService: () => batimentsServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      batimentsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBatimentss();
      await comp.$nextTick();

      // THEN
      expect(batimentsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.batiments[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      batimentsServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeBatiments();
      await comp.$nextTick();

      // THEN
      expect(batimentsServiceStub.delete.called).toBeTruthy();
      expect(batimentsServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
