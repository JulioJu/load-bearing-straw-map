import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILoadBearingStrawMap } from '@/shared/model/load-bearing-straw-map.model';
import LoadBearingStrawMapService from './load-bearing-straw-map.service';

@Component
export default class LoadBearingStrawMapDetails extends Vue {
  @Inject('loadBearingStrawMapService') private loadBearingStrawMapService: () => LoadBearingStrawMapService;
  public loadBearingStrawMap: ILoadBearingStrawMap = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.loadBearingStrawMapId) {
        vm.retrieveLoadBearingStrawMap(to.params.loadBearingStrawMapId);
      }
    });
  }

  public retrieveLoadBearingStrawMap(loadBearingStrawMapId) {
    this.loadBearingStrawMapService()
      .find(loadBearingStrawMapId)
      .then(res => {
        this.loadBearingStrawMap = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
