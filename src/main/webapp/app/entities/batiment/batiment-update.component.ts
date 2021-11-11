import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { decimal, required, minValue, maxValue, maxLength, numeric } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import UserService from '@/admin/user-management/user-management.service';

import { IBatiment, Batiment } from '@/shared/model/batiment.model';
import BatimentService from './batiment.service';
import { UsageBatiment } from '@/shared/model/enumerations/usage-batiment.model';
import { TaillesBottes } from '@/shared/model/enumerations/tailles-bottes.model';
import { Cereale } from '@/shared/model/enumerations/cereale.model';
import { YesNoPartial } from '@/shared/model/enumerations/yes-no-partial.model';
import { StructureComplementaire } from '@/shared/model/enumerations/structure-complementaire.model';
import { IntegBaie } from '@/shared/model/enumerations/integ-baie.model';
import { SupportAncrage } from '@/shared/model/enumerations/support-ancrage.model';
import { RevetInt } from '@/shared/model/enumerations/revet-int.model';
import { RevetExt } from '@/shared/model/enumerations/revet-ext.model';
// START added by JulioJu
import { BCard, BCardHeader, BCollapse } from 'bootstrap-vue';
// END added by JulioJu

const validations: any = {
  batiment: {
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
    nomBatiment: {
      maxLength: maxLength(40),
    },
    photoPrincipale: {},
    photoPrincipaleLegende: {
      maxLength: maxLength(30),
    },
    photoPrincipaleDescription: {},
    photo1: {},
    photo1Legende: {
      maxLength: maxLength(30),
    },
    photo1Description: {},
    photo2: {},
    photo2Legende: {
      maxLength: maxLength(30),
    },
    photo2Description: {},
    photo3: {},
    photo3Legende: {
      maxLength: maxLength(30),
    },
    photo3Description: {},
    photo4: {},
    photo4Legende: {
      maxLength: maxLength(30),
    },
    photo4Description: {},
    photo5: {},
    photo5Legende: {
      maxLength: maxLength(30),
    },
    photo5Description: {},
    usageBatiment: {},
    usageBatimentAutre: {},
    cout: {},
    surfacePlancher: {},
    niveaux: {
      numeric,
      min: minValue(1),
    },
    travauxNeuf: {},
    travauxExtension: {},
    travauxRenov: {},
    travauxIte: {},
    travauxIti: {},
    constructionDebut: {},
    constructionFin: {},
    bottesTaille: {},
    botteTailleAutre: {},
    bottesDensite: {},
    bottesCereale: {},
    distanceAppro: {},
    autoconstruction: {},
    participatif: {},
    structCompl: {},
    structComplNature: {},
    structComplAutre: {
      maxLength: maxLength(512),
    },
    structComplInfos: {},
    longMaxSansMurRefend: {},
    noteCalcul: {},
    nbrRangDeBottes: {},
    integBaie: {},
    integBaieAutre: {},
    supportAncrage: {},
    supportAncrageAutre: {},
    revetInt: {},
    revetIntAutre: {},
    revetExt: {},
    revetExtAutre: {},
    maitreDOuvrage: {
      maxLength: maxLength(512),
    },
    maitreDOeuvre: {
      maxLength: maxLength(512),
    },
    architecte: {
      maxLength: maxLength(512),
    },
    bureauDEtudeStructure: {
      maxLength: maxLength(512),
    },
    bureauControl: {
      maxLength: maxLength(512),
    },
    entrepriseBottes: {
      maxLength: maxLength(512),
    },
    entrepriseCharpente: {
      maxLength: maxLength(512),
    },
    entrepriseEnduits: {
      maxLength: maxLength(512),
    },
    descriptionProjet: {},
    difficultees: {},
    astuces: {},
    divers: {},
    contactNom: {},
    contactMail: {},
    contactPhone: {},
    codePostal: {
      maxLength: maxLength(6),
    },
    profilPublic: {},
    createdDate: {},
    lastModifiedDate: {},
    createdBy: {
      // START added by JulioJu
      // required,
      // END added by JulioJu
    },
  },
};

@Component({
  validations,
  // START added by JulioJu
  components: { BCard, BCardHeader, BCollapse },
  // END added by JulioJu
})
export default class BatimentUpdate extends mixins(JhiDataUtils) {
  @Inject('batimentService') private batimentService: () => BatimentService;
  @Inject('alertService') private alertService: () => AlertService;

  public batiment: IBatiment = new Batiment();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public usageBatimentValues: string[] = Object.keys(UsageBatiment);
  public taillesBottesValues: string[] = Object.keys(TaillesBottes);
  public cerealeValues: string[] = Object.keys(Cereale);
  public yesNoPartialValues: string[] = Object.keys(YesNoPartial);
  public structureComplementaireValues: string[] = Object.keys(StructureComplementaire);
  public integBaieValues: string[] = Object.keys(IntegBaie);
  public supportAncrageValues: string[] = Object.keys(SupportAncrage);
  public revetIntValues: string[] = Object.keys(RevetInt);
  public revetExtValues: string[] = Object.keys(RevetExt);
  public isSaving = false;
  public currentLanguage = '';

  // START added by JulioJu
  public visiblePhotoPrincipale = false;
  public visiblePhoto1 = false;
  public visiblePhoto2 = false;
  public visiblePhoto3 = false;
  public visiblePhoto4 = false;
  public visiblePhoto5 = false;
  // END added by JulioJu

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.batimentId) {
        vm.retrieveBatiment(to.params.batimentId);
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
        vm.batiment.latitude = to.query.lat;
        vm.batiment.longitude = to.query.long;
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
    if (this.batiment.id) {
      this.batimentService()
        .update(this.batiment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('cartoPaillePorteuseApp.batiment.updated', { param: param.id });
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
      this.batimentService()
        .create(this.batiment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('cartoPaillePorteuseApp.batiment.created', { param: param.id });
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.batiment[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.batiment[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.batiment[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.batiment[field] = null;
    }
  }

  public retrieveBatiment(batimentId): void {
    this.batimentService()
      .find(batimentId)
      .then(res => {
        res.createdDate = new Date(res.createdDate);
        res.lastModifiedDate = new Date(res.lastModifiedDate);
        this.batiment = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.batiment && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.batiment, field)) {
        this.batiment[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.batiment, fieldContentType)) {
        this.batiment[fieldContentType] = null;
      }
      if (idInput) {
        (<any>this).$refs[idInput] = null;
      }
    }
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
