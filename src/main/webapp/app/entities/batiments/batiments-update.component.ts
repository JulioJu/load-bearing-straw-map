import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { decimal, required, minValue, maxValue, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import UserService from '@/admin/user-management/user-management.service';

import { IBatiments, Batiments } from '@/shared/model/batiments.model';
import BatimentsService from './batiments.service';
import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { MateriauSb } from '@/shared/model/enumerations/materiau-sb.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';

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
    techniqueSecondaire: {},
    usage: {},
    cout: {},
    surface: {},
    niveaux: {},
    travauxNeuf: {},
    travauxExtension: {},
    travauxRenov: {},
    travauxIte: {},
    travauxIti: {},
    constructionDebut: {},
    constructionFin: {},
    bottesTaille: {},
    bottesDensite: {},
    bottesCereale: {},
    distanceAppro: {},
    autoconstruction: {},
    participatif: {},
    integBaie: {},
    structSuppl: {},
    noteCalcul: {},
    materiauSb: {},
    revetInt: {},
    revetExt: {},
    concepteur: {
      maxLength: maxLength(512),
    },
    realisateur: {
      maxLength: maxLength(512),
    },
    description: {},
    contactNom: {},
    contactMail: {},
    contactPhone: {},
    codePostal: {
      maxLength: maxLength(6),
    },
  },
};

@Component({
  validations,
})
export default class BatimentsUpdate extends mixins(JhiDataUtils) {
  @Inject('batimentsService') private batimentsService: () => BatimentsService;
  @Inject('alertService') private alertService: () => AlertService;

  public batiments: IBatiments = new Batiments();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public usageBatimentValues: string[] = Object.keys(UsageBatiment);
  public taillesBottesValues: string[] = Object.keys(TaillesBottes);
  public cerealeValues: string[] = Object.keys(Cereale);
  public yesNoPartialValues: string[] = Object.keys(YesNoPartial);
  public integBaieValues: string[] = Object.keys(IntegBaie);
  public materiauSbValues: string[] = Object.keys(MateriauSb);
  public revetIntValues: string[] = Object.keys(RevetInt);
  public revetExtValues: string[] = Object.keys(RevetExt);
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
      vm.initRelationships();
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
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
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
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveBatiments(batimentsId): void {
    this.batimentsService()
      .find(batimentsId)
      .then(res => {
        this.batiments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
