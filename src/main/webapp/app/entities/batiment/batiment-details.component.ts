import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IBatiment } from '@/shared/model/batiment.model';
import BatimentService from './batiment.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class BatimentDetails extends mixins(JhiDataUtils) {
  @Inject('batimentService') private batimentService: () => BatimentService;
  @Inject('alertService') private alertService: () => AlertService;

  public batiment: IBatiment = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.batimentId) {
        vm.retrieveBatiment(to.params.batimentId);
      }
    });
  }

  public retrieveBatiment(batimentId) {
    this.batimentService()
      .find(batimentId)
      .then(res => {
        this.batiment = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
