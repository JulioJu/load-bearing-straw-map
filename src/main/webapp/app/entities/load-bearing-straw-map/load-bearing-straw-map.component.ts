import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILoadBearingStrawMap } from '@/shared/model/load-bearing-straw-map.model';

import LoadBearingStrawMapService from './load-bearing-straw-map.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LoadBearingStrawMap extends Vue {
  @Inject('loadBearingStrawMapService') private loadBearingStrawMapService: () => LoadBearingStrawMapService;
  private removeId: number = null;

  public loadBearingStrawMaps: ILoadBearingStrawMap[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLoadBearingStrawMaps();
  }

  public clear(): void {
    this.retrieveAllLoadBearingStrawMaps();
  }

  public retrieveAllLoadBearingStrawMaps(): void {
    this.isFetching = true;
    this.loadBearingStrawMapService()
      .retrieve()
      .then(
        res => {
          this.loadBearingStrawMaps = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ILoadBearingStrawMap): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLoadBearingStrawMap(): void {
    this.loadBearingStrawMapService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('lbstrawmapApp.loadBearingStrawMap.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLoadBearingStrawMaps();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
