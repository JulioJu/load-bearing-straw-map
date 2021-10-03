<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="lbstrawmapApp.loadBearingStrawMap.home.createOrEditLabel"
          data-cy="LoadBearingStrawMapCreateUpdateHeading"
          v-text="$t('lbstrawmapApp.loadBearingStrawMap.home.createOrEditLabel')"
        >
          Create or edit a LoadBearingStrawMap
        </h2>
        <div>
          <div class="form-group" v-if="loadBearingStrawMap.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="loadBearingStrawMap.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.loadBearingStrawMap.name')" for="load-bearing-straw-map-name"
              >Name</label
            >
            <input
              type="text"
              class="form-control"
              name="name"
              id="load-bearing-straw-map-name"
              data-cy="name"
              :class="{ valid: !$v.loadBearingStrawMap.name.$invalid, invalid: $v.loadBearingStrawMap.name.$invalid }"
              v-model="$v.loadBearingStrawMap.name.$model"
              required
            />
            <div v-if="$v.loadBearingStrawMap.name.$anyDirty && $v.loadBearingStrawMap.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.loadBearingStrawMap.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.loadBearingStrawMap.longitude')"
              for="load-bearing-straw-map-longitude"
              >Longitude</label
            >
            <input
              type="number"
              class="form-control"
              name="longitude"
              id="load-bearing-straw-map-longitude"
              data-cy="longitude"
              :class="{ valid: !$v.loadBearingStrawMap.longitude.$invalid, invalid: $v.loadBearingStrawMap.longitude.$invalid }"
              v-model.number="$v.loadBearingStrawMap.longitude.$model"
              required
            />
            <div v-if="$v.loadBearingStrawMap.longitude.$anyDirty && $v.loadBearingStrawMap.longitude.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.loadBearingStrawMap.longitude.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.loadBearingStrawMap.longitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.loadBearingStrawMap.latitude')"
              for="load-bearing-straw-map-latitude"
              >Latitude</label
            >
            <input
              type="number"
              class="form-control"
              name="latitude"
              id="load-bearing-straw-map-latitude"
              data-cy="latitude"
              :class="{ valid: !$v.loadBearingStrawMap.latitude.$invalid, invalid: $v.loadBearingStrawMap.latitude.$invalid }"
              v-model.number="$v.loadBearingStrawMap.latitude.$model"
              required
            />
            <div v-if="$v.loadBearingStrawMap.latitude.$anyDirty && $v.loadBearingStrawMap.latitude.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.loadBearingStrawMap.latitude.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.loadBearingStrawMap.latitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.loadBearingStrawMap.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./load-bearing-straw-map-update.component.ts"></script>
