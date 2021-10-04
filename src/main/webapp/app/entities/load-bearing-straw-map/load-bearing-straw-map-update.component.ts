import { Component, Vue, Inject } from 'vue-property-decorator';

import { decimal, required, minValue, maxValue } from 'vuelidate/lib/validators';

import { ILoadBearingStrawMap, LoadBearingStrawMap } from '@/shared/model/load-bearing-straw-map.model';
import LoadBearingStrawMapService from './load-bearing-straw-map.service';

const validations: any = {
  loadBearingStrawMap: {
    name: {},
    latitude: {
      required,
      decimal,
      min: minValue(-90),
      max: maxValue(90),
    },
    longitude: {
      required,
      decimal,
      min: minValue(-90),
      max: maxValue(90),
    },
  },
};

@Component({
  validations,
})
export default class LoadBearingStrawMapUpdate extends Vue {
  @Inject('loadBearingStrawMapService') private loadBearingStrawMapService: () => LoadBearingStrawMapService;
  public loadBearingStrawMap: ILoadBearingStrawMap = new LoadBearingStrawMap();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.loadBearingStrawMapId) {
        vm.retrieveLoadBearingStrawMap(to.params.loadBearingStrawMapId);
      }

      /** START added by JulioJu **/
      if (
        to.query.lat &&
        to.query.long &&
        !isNaN(to.query.lat) &&
        !isNaN(to.query.long) &&
        to.query.lat >= -90 &&
        to.query.lat <= 90 &&
        to.query.long >= -90 &&
        to.query.long <= 90
      ) {
        vm.loadBearingStrawMap.latitude = to.query.lat;
        vm.loadBearingStrawMap.longitude = to.query.long;
      }
      /** END added by JulioJu **/
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.loadBearingStrawMap.id) {
      this.loadBearingStrawMapService()
        .update(this.loadBearingStrawMap)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('lbstrawmapApp.loadBearingStrawMap.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.loadBearingStrawMapService()
        .create(this.loadBearingStrawMap)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('lbstrawmapApp.loadBearingStrawMap.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveLoadBearingStrawMap(loadBearingStrawMapId): void {
    this.loadBearingStrawMapService()
      .find(loadBearingStrawMapId)
      .then(res => {
        this.loadBearingStrawMap = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
