import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IBatiments } from '@/shared/model/batiments.model';
import BatimentsService from './batiments.service';

@Component
export default class BatimentsDetails extends mixins(JhiDataUtils) {
  @Inject('batimentsService') private batimentsService: () => BatimentsService;
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
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
