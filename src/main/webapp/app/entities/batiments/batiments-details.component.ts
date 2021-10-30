import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IBatiments } from '@/shared/model/batiments.model';
import BatimentsService from './batiments.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class BatimentsDetails extends mixins(JhiDataUtils) {
  @Inject('batimentsService') private batimentsService: () => BatimentsService;
  @Inject('alertService') private alertService: () => AlertService;

  public batiments: IBatiments = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.batimentsId) {
        vm.retrieveBatiments(to.params.batimentsId);
      }
    });
  }

  public retrieveBatiments(batimentsId) {
    this.batimentsService()
      .find(batimentsId)
      .then(res => {
        this.batiments = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
