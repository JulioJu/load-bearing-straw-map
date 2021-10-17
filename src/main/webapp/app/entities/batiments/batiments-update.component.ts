import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { decimal, required, minValue, maxValue, maxLength } from 'vuelidate/lib/validators';

import { IBatiments, Batiments } from '@/shared/model/batiments.model';
import BatimentsService from './batiments.service';

const validations: any = {
  batiments: {
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
    nom: {
      maxLength: maxLength(40),
    },
    contactNom: {},
    contactMail: {},
    contactPhone: {},
    constructionDebut: {},
    constructionFin: {},
    surface: {},
    cout: {},
    bottesTaille: {},
    autoconstruction: {},
    concepteur: {
      maxLength: maxLength(128),
    },
    realisateur: {
      maxLength: maxLength(512),
    },
    participatif: {},
    usage: {},
    noteCalcul: {},
    travauxNeuf: {},
    travauxExtension: {},
    travauxRenov: {},
    travauxIte: {},
    travauxIti: {},
    niveaux: {},
    bottesDensite: {},
    distanceAppro: {},
    bottesCereale: {},
    structSuppl: {},
    revetInt: {},
    revetExt: {},
    techniqueSecondaire: {},
    codePostal: {
      maxLength: maxLength(6),
    },
    integBaie: {},
    materiauSb: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class BatimentsUpdate extends mixins(JhiDataUtils) {
  @Inject('batimentsService') private batimentsService: () => BatimentsService;
  public batiments: IBatiments = new Batiments();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.batimentsId) {
        vm.retrieveBatiments(to.params.batimentsId);
      }

      // START added by JulioJu
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
      // END added by JulioJu
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
    if (this.batiments.id) {
      this.batimentsService()
        .update(this.batiments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('lbstrawmapApp.batiments.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.batimentsService()
        .create(this.batiments)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('lbstrawmapApp.batiments.created', { param: param.id });
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

  public retrieveBatiments(batimentsId): void {
    this.batimentsService()
      .find(batimentsId)
      .then(res => {
        this.batiments = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
