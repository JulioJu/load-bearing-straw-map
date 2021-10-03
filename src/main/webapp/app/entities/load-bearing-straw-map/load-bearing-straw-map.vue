<template>
  <div>
    <h2 id="page-heading" data-cy="LoadBearingStrawMapHeading">
      <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.home.title')" id="load-bearing-straw-map-heading">Load Bearing Straw Maps</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LoadBearingStrawMapCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-load-bearing-straw-map"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.home.createLabel')"> Create a new Load Bearing Straw Map </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && loadBearingStrawMaps && loadBearingStrawMaps.length === 0">
      <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.home.notFound')">No loadBearingStrawMaps found</span>
    </div>
    <div class="table-responsive" v-if="loadBearingStrawMaps && loadBearingStrawMaps.length > 0">
      <table class="table table-striped" aria-describedby="loadBearingStrawMaps">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.loadBearingStrawMap.name')">Name</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.loadBearingStrawMap.longitude')">Longitude</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.loadBearingStrawMap.latitude')">Latitude</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="loadBearingStrawMap in loadBearingStrawMaps" :key="loadBearingStrawMap.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LoadBearingStrawMapView', params: { loadBearingStrawMapId: loadBearingStrawMap.id } }">{{
                loadBearingStrawMap.id
              }}</router-link>
            </td>
            <td>{{ loadBearingStrawMap.name }}</td>
            <td>{{ loadBearingStrawMap.longitude }}</td>
            <td>{{ loadBearingStrawMap.latitude }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'LoadBearingStrawMapView', params: { loadBearingStrawMapId: loadBearingStrawMap.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'LoadBearingStrawMapEdit', params: { loadBearingStrawMapId: loadBearingStrawMap.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(loadBearingStrawMap)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="lbstrawmapApp.loadBearingStrawMap.delete.question"
          data-cy="loadBearingStrawMapDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-loadBearingStrawMap-heading" v-text="$t('lbstrawmapApp.loadBearingStrawMap.delete.question', { id: removeId })">
          Are you sure you want to delete this Load Bearing Straw Map?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-loadBearingStrawMap"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLoadBearingStrawMap()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./load-bearing-straw-map.component.ts"></script>
