import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBatiments } from '@/shared/model/batiments.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import BatimentsService from './batiments.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Batiments extends mixins(JhiDataUtils) {
  @Inject('batimentsService') private batimentsService: () => BatimentsService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public batiments: IBatiments[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBatimentss();
  }

  public clear(): void {
    this.retrieveAllBatimentss();
  }

  public retrieveAllBatimentss(): void {
    this.isFetching = true;
    this.batimentsService()
      .retrieve()
      .then(
        res => {
          this.batiments = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IBatiments): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBatiments(): void {
    this.batimentsService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('lbstrawmapApp.batiments.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllBatimentss();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
