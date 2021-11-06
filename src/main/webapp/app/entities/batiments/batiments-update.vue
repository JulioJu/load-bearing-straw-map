<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="lbstrawmapApp.batiments.home.createOrEditLabel"
          data-cy="BatimentsCreateUpdateHeading"
          v-text="$t('lbstrawmapApp.batiments.home.createOrEditLabel')"
        >
          Create or edit a Batiments
        </h2>
        <div>
          <div class="form-group" v-if="batiments.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="batiments.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.latitude')" for="batiments-latitude">Latitude</label>
            <input
              type="number"
              class="form-control"
              name="latitude"
              id="batiments-latitude"
              data-cy="latitude"
              :class="{ valid: !$v.batiments.latitude.$invalid, invalid: $v.batiments.latitude.$invalid }"
              v-model.number="$v.batiments.latitude.$model"
              required
            />
            <div v-if="$v.batiments.latitude.$anyDirty && $v.batiments.latitude.$invalid">
              <small class="form-text text-danger" v-if="!$v.batiments.latitude.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.latitude.min" v-text="$t('entity.validation.min', { min: -90 })">
                This field should be at least -90.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.latitude.max" v-text="$t('entity.validation.max', { max: 90 })">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.latitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.longitude')" for="batiments-longitude">Longitude</label>
            <input
              type="number"
              class="form-control"
              name="longitude"
              id="batiments-longitude"
              data-cy="longitude"
              :class="{ valid: !$v.batiments.longitude.$invalid, invalid: $v.batiments.longitude.$invalid }"
              v-model.number="$v.batiments.longitude.$model"
              required
            />
            <div v-if="$v.batiments.longitude.$anyDirty && $v.batiments.longitude.$invalid">
              <small class="form-text text-danger" v-if="!$v.batiments.longitude.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.longitude.min" v-text="$t('entity.validation.min', { min: -90 })">
                This field should be at least -90.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.longitude.max" v-text="$t('entity.validation.max', { max: 90 })">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiments.longitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.nomBatiment')" for="batiments-nomBatiment"
              >Nom Batiment</label
            >
            <input
              type="text"
              class="form-control"
              name="nomBatiment"
              id="batiments-nomBatiment"
              data-cy="nomBatiment"
              :class="{ valid: !$v.batiments.nomBatiment.$invalid, invalid: $v.batiments.nomBatiment.$invalid }"
              v-model="$v.batiments.nomBatiment.$model"
            />
            <div v-if="$v.batiments.nomBatiment.$anyDirty && $v.batiments.nomBatiment.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.nomBatiment.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 40 })"
              >
                This field cannot be longer than 40 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photoPrincipale')" for="batiments-photoPrincipale"
              >Photo Principale</label
            >
            <div>
              <img
                v-bind:src="'data:' + batiments.photoPrincipaleContentType + ';base64,' + batiments.photoPrincipale"
                style="max-height: 100px"
                v-if="batiments.photoPrincipale"
                alt="batiments image"
              />
              <div v-if="batiments.photoPrincipale" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photoPrincipaleContentType }}, {{ byteSize(batiments.photoPrincipale) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photoPrincipale', 'photoPrincipaleContentType', 'file_photoPrincipale')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photoPrincipale"
                id="file_photoPrincipale"
                data-cy="photoPrincipale"
                v-on:change="setFileData($event, batiments, 'photoPrincipale', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photoPrincipale"
              id="batiments-photoPrincipale"
              data-cy="photoPrincipale"
              :class="{ valid: !$v.batiments.photoPrincipale.$invalid, invalid: $v.batiments.photoPrincipale.$invalid }"
              v-model="$v.batiments.photoPrincipale.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photoPrincipaleContentType"
              id="batiments-photoPrincipaleContentType"
              v-model="batiments.photoPrincipaleContentType"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.photoPrincipaleLegende')"
              for="batiments-photoPrincipaleLegende"
              >Photo Principale Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photoPrincipaleLegende"
              id="batiments-photoPrincipaleLegende"
              data-cy="photoPrincipaleLegende"
              :class="{ valid: !$v.batiments.photoPrincipaleLegende.$invalid, invalid: $v.batiments.photoPrincipaleLegende.$invalid }"
              v-model="$v.batiments.photoPrincipaleLegende.$model"
            />
            <div v-if="$v.batiments.photoPrincipaleLegende.$anyDirty && $v.batiments.photoPrincipaleLegende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photoPrincipaleLegende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.photoPrincipaleDescription')"
              for="batiments-photoPrincipaleDescription"
              >Photo Principale Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photoPrincipaleDescription"
              id="batiments-photoPrincipaleDescription"
              data-cy="photoPrincipaleDescription"
              :class="{
                valid: !$v.batiments.photoPrincipaleDescription.$invalid,
                invalid: $v.batiments.photoPrincipaleDescription.$invalid,
              }"
              v-model="$v.batiments.photoPrincipaleDescription.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo1')" for="batiments-photo1">Photo 1</label>
            <div>
              <img
                v-bind:src="'data:' + batiments.photo1ContentType + ';base64,' + batiments.photo1"
                style="max-height: 100px"
                v-if="batiments.photo1"
                alt="batiments image"
              />
              <div v-if="batiments.photo1" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photo1ContentType }}, {{ byteSize(batiments.photo1) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photo1', 'photo1ContentType', 'file_photo1')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photo1"
                id="file_photo1"
                data-cy="photo1"
                v-on:change="setFileData($event, batiments, 'photo1', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo1"
              id="batiments-photo1"
              data-cy="photo1"
              :class="{ valid: !$v.batiments.photo1.$invalid, invalid: $v.batiments.photo1.$invalid }"
              v-model="$v.batiments.photo1.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo1ContentType"
              id="batiments-photo1ContentType"
              v-model="batiments.photo1ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo1Legende')" for="batiments-photo1Legende"
              >Photo 1 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo1Legende"
              id="batiments-photo1Legende"
              data-cy="photo1Legende"
              :class="{ valid: !$v.batiments.photo1Legende.$invalid, invalid: $v.batiments.photo1Legende.$invalid }"
              v-model="$v.batiments.photo1Legende.$model"
            />
            <div v-if="$v.batiments.photo1Legende.$anyDirty && $v.batiments.photo1Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photo1Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo1Description')" for="batiments-photo1Description"
              >Photo 1 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo1Description"
              id="batiments-photo1Description"
              data-cy="photo1Description"
              :class="{ valid: !$v.batiments.photo1Description.$invalid, invalid: $v.batiments.photo1Description.$invalid }"
              v-model="$v.batiments.photo1Description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo2')" for="batiments-photo2">Photo 2</label>
            <div>
              <img
                v-bind:src="'data:' + batiments.photo2ContentType + ';base64,' + batiments.photo2"
                style="max-height: 100px"
                v-if="batiments.photo2"
                alt="batiments image"
              />
              <div v-if="batiments.photo2" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photo2ContentType }}, {{ byteSize(batiments.photo2) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photo2', 'photo2ContentType', 'file_photo2')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photo2"
                id="file_photo2"
                data-cy="photo2"
                v-on:change="setFileData($event, batiments, 'photo2', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo2"
              id="batiments-photo2"
              data-cy="photo2"
              :class="{ valid: !$v.batiments.photo2.$invalid, invalid: $v.batiments.photo2.$invalid }"
              v-model="$v.batiments.photo2.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo2ContentType"
              id="batiments-photo2ContentType"
              v-model="batiments.photo2ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo2Legende')" for="batiments-photo2Legende"
              >Photo 2 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo2Legende"
              id="batiments-photo2Legende"
              data-cy="photo2Legende"
              :class="{ valid: !$v.batiments.photo2Legende.$invalid, invalid: $v.batiments.photo2Legende.$invalid }"
              v-model="$v.batiments.photo2Legende.$model"
            />
            <div v-if="$v.batiments.photo2Legende.$anyDirty && $v.batiments.photo2Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photo2Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo2Description')" for="batiments-photo2Description"
              >Photo 2 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo2Description"
              id="batiments-photo2Description"
              data-cy="photo2Description"
              :class="{ valid: !$v.batiments.photo2Description.$invalid, invalid: $v.batiments.photo2Description.$invalid }"
              v-model="$v.batiments.photo2Description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo3')" for="batiments-photo3">Photo 3</label>
            <div>
              <img
                v-bind:src="'data:' + batiments.photo3ContentType + ';base64,' + batiments.photo3"
                style="max-height: 100px"
                v-if="batiments.photo3"
                alt="batiments image"
              />
              <div v-if="batiments.photo3" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photo3ContentType }}, {{ byteSize(batiments.photo3) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photo3', 'photo3ContentType', 'file_photo3')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photo3"
                id="file_photo3"
                data-cy="photo3"
                v-on:change="setFileData($event, batiments, 'photo3', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo3"
              id="batiments-photo3"
              data-cy="photo3"
              :class="{ valid: !$v.batiments.photo3.$invalid, invalid: $v.batiments.photo3.$invalid }"
              v-model="$v.batiments.photo3.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo3ContentType"
              id="batiments-photo3ContentType"
              v-model="batiments.photo3ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo3Legende')" for="batiments-photo3Legende"
              >Photo 3 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo3Legende"
              id="batiments-photo3Legende"
              data-cy="photo3Legende"
              :class="{ valid: !$v.batiments.photo3Legende.$invalid, invalid: $v.batiments.photo3Legende.$invalid }"
              v-model="$v.batiments.photo3Legende.$model"
            />
            <div v-if="$v.batiments.photo3Legende.$anyDirty && $v.batiments.photo3Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photo3Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo3Description')" for="batiments-photo3Description"
              >Photo 3 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo3Description"
              id="batiments-photo3Description"
              data-cy="photo3Description"
              :class="{ valid: !$v.batiments.photo3Description.$invalid, invalid: $v.batiments.photo3Description.$invalid }"
              v-model="$v.batiments.photo3Description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo4')" for="batiments-photo4">Photo 4</label>
            <div>
              <img
                v-bind:src="'data:' + batiments.photo4ContentType + ';base64,' + batiments.photo4"
                style="max-height: 100px"
                v-if="batiments.photo4"
                alt="batiments image"
              />
              <div v-if="batiments.photo4" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photo4ContentType }}, {{ byteSize(batiments.photo4) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photo4', 'photo4ContentType', 'file_photo4')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photo4"
                id="file_photo4"
                data-cy="photo4"
                v-on:change="setFileData($event, batiments, 'photo4', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo4"
              id="batiments-photo4"
              data-cy="photo4"
              :class="{ valid: !$v.batiments.photo4.$invalid, invalid: $v.batiments.photo4.$invalid }"
              v-model="$v.batiments.photo4.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo4ContentType"
              id="batiments-photo4ContentType"
              v-model="batiments.photo4ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo4Legende')" for="batiments-photo4Legende"
              >Photo 4 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo4Legende"
              id="batiments-photo4Legende"
              data-cy="photo4Legende"
              :class="{ valid: !$v.batiments.photo4Legende.$invalid, invalid: $v.batiments.photo4Legende.$invalid }"
              v-model="$v.batiments.photo4Legende.$model"
            />
            <div v-if="$v.batiments.photo4Legende.$anyDirty && $v.batiments.photo4Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photo4Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo4Description')" for="batiments-photo4Description"
              >Photo 4 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo4Description"
              id="batiments-photo4Description"
              data-cy="photo4Description"
              :class="{ valid: !$v.batiments.photo4Description.$invalid, invalid: $v.batiments.photo4Description.$invalid }"
              v-model="$v.batiments.photo4Description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo5')" for="batiments-photo5">Photo 5</label>
            <div>
              <img
                v-bind:src="'data:' + batiments.photo5ContentType + ';base64,' + batiments.photo5"
                style="max-height: 100px"
                v-if="batiments.photo5"
                alt="batiments image"
              />
              <div v-if="batiments.photo5" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiments.photo5ContentType }}, {{ byteSize(batiments.photo5) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('photo5', 'photo5ContentType', 'file_photo5')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_photo5"
                id="file_photo5"
                data-cy="photo5"
                v-on:change="setFileData($event, batiments, 'photo5', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo5"
              id="batiments-photo5"
              data-cy="photo5"
              :class="{ valid: !$v.batiments.photo5.$invalid, invalid: $v.batiments.photo5.$invalid }"
              v-model="$v.batiments.photo5.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo5ContentType"
              id="batiments-photo5ContentType"
              v-model="batiments.photo5ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo5Legende')" for="batiments-photo5Legende"
              >Photo 5 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo5Legende"
              id="batiments-photo5Legende"
              data-cy="photo5Legende"
              :class="{ valid: !$v.batiments.photo5Legende.$invalid, invalid: $v.batiments.photo5Legende.$invalid }"
              v-model="$v.batiments.photo5Legende.$model"
            />
            <div v-if="$v.batiments.photo5Legende.$anyDirty && $v.batiments.photo5Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.photo5Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.photo5Description')" for="batiments-photo5Description"
              >Photo 5 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo5Description"
              id="batiments-photo5Description"
              data-cy="photo5Description"
              :class="{ valid: !$v.batiments.photo5Description.$invalid, invalid: $v.batiments.photo5Description.$invalid }"
              v-model="$v.batiments.photo5Description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')" for="batiments-techniqueSecondaire"
              >Technique Secondaire</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="techniqueSecondaire"
              id="batiments-techniqueSecondaire"
              data-cy="techniqueSecondaire"
              :class="{ valid: !$v.batiments.techniqueSecondaire.$invalid, invalid: $v.batiments.techniqueSecondaire.$invalid }"
              v-model="$v.batiments.techniqueSecondaire.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.usageBatiment')" for="batiments-usageBatiment"
              >Usage Batiment</label
            >
            <select
              class="form-control"
              name="usageBatiment"
              :class="{ valid: !$v.batiments.usageBatiment.$invalid, invalid: $v.batiments.usageBatiment.$invalid }"
              v-model="$v.batiments.usageBatiment.$model"
              id="batiments-usageBatiment"
              data-cy="usageBatiment"
            >
              <option
                v-for="usageBatiment in usageBatimentValues"
                :key="usageBatiment"
                v-bind:value="usageBatiment"
                v-bind:label="$t('lbstrawmapApp.UsageBatiment.' + usageBatiment)"
              >
                {{ usageBatiment }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.cout')" for="batiments-cout">Cout</label>
            <input
              type="number"
              class="form-control"
              name="cout"
              id="batiments-cout"
              data-cy="cout"
              :class="{ valid: !$v.batiments.cout.$invalid, invalid: $v.batiments.cout.$invalid }"
              v-model.number="$v.batiments.cout.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.surfacePlancher')" for="batiments-surfacePlancher"
              >Surface Plancher</label
            >
            <input
              type="number"
              class="form-control"
              name="surfacePlancher"
              id="batiments-surfacePlancher"
              data-cy="surfacePlancher"
              :class="{ valid: !$v.batiments.surfacePlancher.$invalid, invalid: $v.batiments.surfacePlancher.$invalid }"
              v-model.number="$v.batiments.surfacePlancher.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.niveaux')" for="batiments-niveaux">Niveaux</label>
            <input
              type="number"
              class="form-control"
              name="niveaux"
              id="batiments-niveaux"
              data-cy="niveaux"
              :class="{ valid: !$v.batiments.niveaux.$invalid, invalid: $v.batiments.niveaux.$invalid }"
              v-model.number="$v.batiments.niveaux.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.travauxNeuf')" for="batiments-travauxNeuf"
              >Travaux Neuf</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxNeuf"
              id="batiments-travauxNeuf"
              data-cy="travauxNeuf"
              :class="{ valid: !$v.batiments.travauxNeuf.$invalid, invalid: $v.batiments.travauxNeuf.$invalid }"
              v-model="$v.batiments.travauxNeuf.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.travauxExtension')" for="batiments-travauxExtension"
              >Travaux Extension</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxExtension"
              id="batiments-travauxExtension"
              data-cy="travauxExtension"
              :class="{ valid: !$v.batiments.travauxExtension.$invalid, invalid: $v.batiments.travauxExtension.$invalid }"
              v-model="$v.batiments.travauxExtension.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.travauxRenov')" for="batiments-travauxRenov"
              >Travaux Renov</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxRenov"
              id="batiments-travauxRenov"
              data-cy="travauxRenov"
              :class="{ valid: !$v.batiments.travauxRenov.$invalid, invalid: $v.batiments.travauxRenov.$invalid }"
              v-model="$v.batiments.travauxRenov.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.travauxIte')" for="batiments-travauxIte"
              >Travaux Ite</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxIte"
              id="batiments-travauxIte"
              data-cy="travauxIte"
              :class="{ valid: !$v.batiments.travauxIte.$invalid, invalid: $v.batiments.travauxIte.$invalid }"
              v-model="$v.batiments.travauxIte.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.travauxIti')" for="batiments-travauxIti"
              >Travaux Iti</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxIti"
              id="batiments-travauxIti"
              data-cy="travauxIti"
              :class="{ valid: !$v.batiments.travauxIti.$invalid, invalid: $v.batiments.travauxIti.$invalid }"
              v-model="$v.batiments.travauxIti.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.constructionDebut')" for="batiments-constructionDebut"
              >Construction Debut</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiments-constructionDebut"
                  v-model="$v.batiments.constructionDebut.$model"
                  name="constructionDebut"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="batiments-constructionDebut"
                data-cy="constructionDebut"
                type="text"
                class="form-control"
                name="constructionDebut"
                :class="{ valid: !$v.batiments.constructionDebut.$invalid, invalid: $v.batiments.constructionDebut.$invalid }"
                v-model="$v.batiments.constructionDebut.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.constructionFin')" for="batiments-constructionFin"
              >Construction Fin</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiments-constructionFin"
                  v-model="$v.batiments.constructionFin.$model"
                  name="constructionFin"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="batiments-constructionFin"
                data-cy="constructionFin"
                type="text"
                class="form-control"
                name="constructionFin"
                :class="{ valid: !$v.batiments.constructionFin.$invalid, invalid: $v.batiments.constructionFin.$invalid }"
                v-model="$v.batiments.constructionFin.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.bottesTaille')" for="batiments-bottesTaille"
              >Bottes Taille</label
            >
            <select
              class="form-control"
              name="bottesTaille"
              :class="{ valid: !$v.batiments.bottesTaille.$invalid, invalid: $v.batiments.bottesTaille.$invalid }"
              v-model="$v.batiments.bottesTaille.$model"
              id="batiments-bottesTaille"
              data-cy="bottesTaille"
            >
              <option
                v-for="taillesBottes in taillesBottesValues"
                :key="taillesBottes"
                v-bind:value="taillesBottes"
                v-bind:label="$t('lbstrawmapApp.TaillesBottes.' + taillesBottes)"
              >
                {{ taillesBottes }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.botteTailleAutre')" for="batiments-botteTailleAutre"
              >Botte Taille Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="botteTailleAutre"
              id="batiments-botteTailleAutre"
              data-cy="botteTailleAutre"
              :class="{ valid: !$v.batiments.botteTailleAutre.$invalid, invalid: $v.batiments.botteTailleAutre.$invalid }"
              v-model="$v.batiments.botteTailleAutre.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.bottesDensite')" for="batiments-bottesDensite"
              >Bottes Densite</label
            >
            <input
              type="number"
              class="form-control"
              name="bottesDensite"
              id="batiments-bottesDensite"
              data-cy="bottesDensite"
              :class="{ valid: !$v.batiments.bottesDensite.$invalid, invalid: $v.batiments.bottesDensite.$invalid }"
              v-model.number="$v.batiments.bottesDensite.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.bottesCereale')" for="batiments-bottesCereale"
              >Bottes Cereale</label
            >
            <select
              class="form-control"
              name="bottesCereale"
              :class="{ valid: !$v.batiments.bottesCereale.$invalid, invalid: $v.batiments.bottesCereale.$invalid }"
              v-model="$v.batiments.bottesCereale.$model"
              id="batiments-bottesCereale"
              data-cy="bottesCereale"
            >
              <option
                v-for="cereale in cerealeValues"
                :key="cereale"
                v-bind:value="cereale"
                v-bind:label="$t('lbstrawmapApp.Cereale.' + cereale)"
              >
                {{ cereale }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.distanceAppro')" for="batiments-distanceAppro"
              >Distance Appro</label
            >
            <input
              type="number"
              class="form-control"
              name="distanceAppro"
              id="batiments-distanceAppro"
              data-cy="distanceAppro"
              :class="{ valid: !$v.batiments.distanceAppro.$invalid, invalid: $v.batiments.distanceAppro.$invalid }"
              v-model.number="$v.batiments.distanceAppro.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.autoconstruction')" for="batiments-autoconstruction"
              >Autoconstruction</label
            >
            <select
              class="form-control"
              name="autoconstruction"
              :class="{ valid: !$v.batiments.autoconstruction.$invalid, invalid: $v.batiments.autoconstruction.$invalid }"
              v-model="$v.batiments.autoconstruction.$model"
              id="batiments-autoconstruction"
              data-cy="autoconstruction"
            >
              <option
                v-for="yesNoPartial in yesNoPartialValues"
                :key="yesNoPartial"
                v-bind:value="yesNoPartial"
                v-bind:label="$t('lbstrawmapApp.YesNoPartial.' + yesNoPartial)"
              >
                {{ yesNoPartial }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.participatif')" for="batiments-participatif"
              >Participatif</label
            >
            <select
              class="form-control"
              name="participatif"
              :class="{ valid: !$v.batiments.participatif.$invalid, invalid: $v.batiments.participatif.$invalid }"
              v-model="$v.batiments.participatif.$model"
              id="batiments-participatif"
              data-cy="participatif"
            >
              <option
                v-for="yesNoPartial in yesNoPartialValues"
                :key="yesNoPartial"
                v-bind:value="yesNoPartial"
                v-bind:label="$t('lbstrawmapApp.YesNoPartial.' + yesNoPartial)"
              >
                {{ yesNoPartial }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.structSuppl')" for="batiments-structSuppl"
              >Struct Suppl</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="structSuppl"
              id="batiments-structSuppl"
              data-cy="structSuppl"
              :class="{ valid: !$v.batiments.structSuppl.$invalid, invalid: $v.batiments.structSuppl.$invalid }"
              v-model="$v.batiments.structSuppl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.structSupplNature')" for="batiments-structSupplNature"
              >Struct Suppl Nature</label
            >
            <select
              class="form-control"
              name="structSupplNature"
              :class="{ valid: !$v.batiments.structSupplNature.$invalid, invalid: $v.batiments.structSupplNature.$invalid }"
              v-model="$v.batiments.structSupplNature.$model"
              id="batiments-structSupplNature"
              data-cy="structSupplNature"
            >
              <option
                v-for="structureSupplementaire in structureSupplementaireValues"
                :key="structureSupplementaire"
                v-bind:value="structureSupplementaire"
                v-bind:label="$t('lbstrawmapApp.StructureSupplementaire.' + structureSupplementaire)"
              >
                {{ structureSupplementaire }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.noteCalcul')" for="batiments-noteCalcul"
              >Note Calcul</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="noteCalcul"
              id="batiments-noteCalcul"
              data-cy="noteCalcul"
              :class="{ valid: !$v.batiments.noteCalcul.$invalid, invalid: $v.batiments.noteCalcul.$invalid }"
              v-model="$v.batiments.noteCalcul.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.nbrRangDeBottes')" for="batiments-nbrRangDeBottes"
              >Nbr Rang De Bottes</label
            >
            <input
              type="number"
              class="form-control"
              name="nbrRangDeBottes"
              id="batiments-nbrRangDeBottes"
              data-cy="nbrRangDeBottes"
              :class="{ valid: !$v.batiments.nbrRangDeBottes.$invalid, invalid: $v.batiments.nbrRangDeBottes.$invalid }"
              v-model.number="$v.batiments.nbrRangDeBottes.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.longMaxSansMurRefend')"
              for="batiments-longMaxSansMurRefend"
              >Long Max Sans Mur Refend</label
            >
            <input
              type="number"
              class="form-control"
              name="longMaxSansMurRefend"
              id="batiments-longMaxSansMurRefend"
              data-cy="longMaxSansMurRefend"
              :class="{ valid: !$v.batiments.longMaxSansMurRefend.$invalid, invalid: $v.batiments.longMaxSansMurRefend.$invalid }"
              v-model.number="$v.batiments.longMaxSansMurRefend.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.integBaie')" for="batiments-integBaie">Integ Baie</label>
            <select
              class="form-control"
              name="integBaie"
              :class="{ valid: !$v.batiments.integBaie.$invalid, invalid: $v.batiments.integBaie.$invalid }"
              v-model="$v.batiments.integBaie.$model"
              id="batiments-integBaie"
              data-cy="integBaie"
            >
              <option
                v-for="integBaie in integBaieValues"
                :key="integBaie"
                v-bind:value="integBaie"
                v-bind:label="$t('lbstrawmapApp.IntegBaie.' + integBaie)"
              >
                {{ integBaie }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.supportAncrage')" for="batiments-supportAncrage"
              >Support Ancrage</label
            >
            <select
              class="form-control"
              name="supportAncrage"
              :class="{ valid: !$v.batiments.supportAncrage.$invalid, invalid: $v.batiments.supportAncrage.$invalid }"
              v-model="$v.batiments.supportAncrage.$model"
              id="batiments-supportAncrage"
              data-cy="supportAncrage"
            >
              <option
                v-for="supportAncrage in supportAncrageValues"
                :key="supportAncrage"
                v-bind:value="supportAncrage"
                v-bind:label="$t('lbstrawmapApp.SupportAncrage.' + supportAncrage)"
              >
                {{ supportAncrage }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.supportAncragePrecisions')"
              for="batiments-supportAncragePrecisions"
              >Support Ancrage Precisions</label
            >
            <input
              type="text"
              class="form-control"
              name="supportAncragePrecisions"
              id="batiments-supportAncragePrecisions"
              data-cy="supportAncragePrecisions"
              :class="{ valid: !$v.batiments.supportAncragePrecisions.$invalid, invalid: $v.batiments.supportAncragePrecisions.$invalid }"
              v-model="$v.batiments.supportAncragePrecisions.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.revetInt')" for="batiments-revetInt">Revet Int</label>
            <select
              class="form-control"
              name="revetInt"
              :class="{ valid: !$v.batiments.revetInt.$invalid, invalid: $v.batiments.revetInt.$invalid }"
              v-model="$v.batiments.revetInt.$model"
              id="batiments-revetInt"
              data-cy="revetInt"
            >
              <option
                v-for="revetInt in revetIntValues"
                :key="revetInt"
                v-bind:value="revetInt"
                v-bind:label="$t('lbstrawmapApp.RevetInt.' + revetInt)"
              >
                {{ revetInt }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.revetExt')" for="batiments-revetExt">Revet Ext</label>
            <select
              class="form-control"
              name="revetExt"
              :class="{ valid: !$v.batiments.revetExt.$invalid, invalid: $v.batiments.revetExt.$invalid }"
              v-model="$v.batiments.revetExt.$model"
              id="batiments-revetExt"
              data-cy="revetExt"
            >
              <option
                v-for="revetExt in revetExtValues"
                :key="revetExt"
                v-bind:value="revetExt"
                v-bind:label="$t('lbstrawmapApp.RevetExt.' + revetExt)"
              >
                {{ revetExt }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.revetExtAutre')" for="batiments-revetExtAutre"
              >Revet Ext Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="revetExtAutre"
              id="batiments-revetExtAutre"
              data-cy="revetExtAutre"
              :class="{ valid: !$v.batiments.revetExtAutre.$invalid, invalid: $v.batiments.revetExtAutre.$invalid }"
              v-model="$v.batiments.revetExtAutre.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.maitreDOuvrage')" for="batiments-maitreDOuvrage"
              >Maitre D Ouvrage</label
            >
            <input
              type="text"
              class="form-control"
              name="maitreDOuvrage"
              id="batiments-maitreDOuvrage"
              data-cy="maitreDOuvrage"
              :class="{ valid: !$v.batiments.maitreDOuvrage.$invalid, invalid: $v.batiments.maitreDOuvrage.$invalid }"
              v-model="$v.batiments.maitreDOuvrage.$model"
            />
            <div v-if="$v.batiments.maitreDOuvrage.$anyDirty && $v.batiments.maitreDOuvrage.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.maitreDOuvrage.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.maitreDOeuvre')" for="batiments-maitreDOeuvre"
              >Maitre D Oeuvre</label
            >
            <input
              type="text"
              class="form-control"
              name="maitreDOeuvre"
              id="batiments-maitreDOeuvre"
              data-cy="maitreDOeuvre"
              :class="{ valid: !$v.batiments.maitreDOeuvre.$invalid, invalid: $v.batiments.maitreDOeuvre.$invalid }"
              v-model="$v.batiments.maitreDOeuvre.$model"
            />
            <div v-if="$v.batiments.maitreDOeuvre.$anyDirty && $v.batiments.maitreDOeuvre.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.maitreDOeuvre.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.architecte')" for="batiments-architecte"
              >Architecte</label
            >
            <input
              type="text"
              class="form-control"
              name="architecte"
              id="batiments-architecte"
              data-cy="architecte"
              :class="{ valid: !$v.batiments.architecte.$invalid, invalid: $v.batiments.architecte.$invalid }"
              v-model="$v.batiments.architecte.$model"
            />
            <div v-if="$v.batiments.architecte.$anyDirty && $v.batiments.architecte.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.architecte.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.bureauDEtudeStructure')"
              for="batiments-bureauDEtudeStructure"
              >Bureau D Etude Structure</label
            >
            <input
              type="text"
              class="form-control"
              name="bureauDEtudeStructure"
              id="batiments-bureauDEtudeStructure"
              data-cy="bureauDEtudeStructure"
              :class="{ valid: !$v.batiments.bureauDEtudeStructure.$invalid, invalid: $v.batiments.bureauDEtudeStructure.$invalid }"
              v-model="$v.batiments.bureauDEtudeStructure.$model"
            />
            <div v-if="$v.batiments.bureauDEtudeStructure.$anyDirty && $v.batiments.bureauDEtudeStructure.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.bureauDEtudeStructure.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.bureauControl')" for="batiments-bureauControl"
              >Bureau Control</label
            >
            <input
              type="text"
              class="form-control"
              name="bureauControl"
              id="batiments-bureauControl"
              data-cy="bureauControl"
              :class="{ valid: !$v.batiments.bureauControl.$invalid, invalid: $v.batiments.bureauControl.$invalid }"
              v-model="$v.batiments.bureauControl.$model"
            />
            <div v-if="$v.batiments.bureauControl.$anyDirty && $v.batiments.bureauControl.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.bureauControl.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.entrepriseBottes')" for="batiments-entrepriseBottes"
              >Entreprise Bottes</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseBottes"
              id="batiments-entrepriseBottes"
              data-cy="entrepriseBottes"
              :class="{ valid: !$v.batiments.entrepriseBottes.$invalid, invalid: $v.batiments.entrepriseBottes.$invalid }"
              v-model="$v.batiments.entrepriseBottes.$model"
            />
            <div v-if="$v.batiments.entrepriseBottes.$anyDirty && $v.batiments.entrepriseBottes.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.entrepriseBottes.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.entrepriseCharpente')" for="batiments-entrepriseCharpente"
              >Entreprise Charpente</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseCharpente"
              id="batiments-entrepriseCharpente"
              data-cy="entrepriseCharpente"
              :class="{ valid: !$v.batiments.entrepriseCharpente.$invalid, invalid: $v.batiments.entrepriseCharpente.$invalid }"
              v-model="$v.batiments.entrepriseCharpente.$model"
            />
            <div v-if="$v.batiments.entrepriseCharpente.$anyDirty && $v.batiments.entrepriseCharpente.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.entrepriseCharpente.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.entrepriseEnduits')" for="batiments-entrepriseEnduits"
              >Entreprise Enduits</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseEnduits"
              id="batiments-entrepriseEnduits"
              data-cy="entrepriseEnduits"
              :class="{ valid: !$v.batiments.entrepriseEnduits.$invalid, invalid: $v.batiments.entrepriseEnduits.$invalid }"
              v-model="$v.batiments.entrepriseEnduits.$model"
            />
            <div v-if="$v.batiments.entrepriseEnduits.$anyDirty && $v.batiments.entrepriseEnduits.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.entrepriseEnduits.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.descriptionProjet')" for="batiments-descriptionProjet"
              >Description Projet</label
            >
            <textarea
              class="form-control"
              name="descriptionProjet"
              id="batiments-descriptionProjet"
              data-cy="descriptionProjet"
              :class="{ valid: !$v.batiments.descriptionProjet.$invalid, invalid: $v.batiments.descriptionProjet.$invalid }"
              v-model="$v.batiments.descriptionProjet.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.difficultees')" for="batiments-difficultees"
              >Difficultees</label
            >
            <textarea
              class="form-control"
              name="difficultees"
              id="batiments-difficultees"
              data-cy="difficultees"
              :class="{ valid: !$v.batiments.difficultees.$invalid, invalid: $v.batiments.difficultees.$invalid }"
              v-model="$v.batiments.difficultees.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.astuces')" for="batiments-astuces">Astuces</label>
            <textarea
              class="form-control"
              name="astuces"
              id="batiments-astuces"
              data-cy="astuces"
              :class="{ valid: !$v.batiments.astuces.$invalid, invalid: $v.batiments.astuces.$invalid }"
              v-model="$v.batiments.astuces.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.divers')" for="batiments-divers">Divers</label>
            <textarea
              class="form-control"
              name="divers"
              id="batiments-divers"
              data-cy="divers"
              :class="{ valid: !$v.batiments.divers.$invalid, invalid: $v.batiments.divers.$invalid }"
              v-model="$v.batiments.divers.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.contactNom')" for="batiments-contactNom"
              >Contact Nom</label
            >
            <input
              type="text"
              class="form-control"
              name="contactNom"
              id="batiments-contactNom"
              data-cy="contactNom"
              :class="{ valid: !$v.batiments.contactNom.$invalid, invalid: $v.batiments.contactNom.$invalid }"
              v-model="$v.batiments.contactNom.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.contactMail')" for="batiments-contactMail"
              >Contact Mail</label
            >
            <input
              type="text"
              class="form-control"
              name="contactMail"
              id="batiments-contactMail"
              data-cy="contactMail"
              :class="{ valid: !$v.batiments.contactMail.$invalid, invalid: $v.batiments.contactMail.$invalid }"
              v-model="$v.batiments.contactMail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.contactPhone')" for="batiments-contactPhone"
              >Contact Phone</label
            >
            <input
              type="text"
              class="form-control"
              name="contactPhone"
              id="batiments-contactPhone"
              data-cy="contactPhone"
              :class="{ valid: !$v.batiments.contactPhone.$invalid, invalid: $v.batiments.contactPhone.$invalid }"
              v-model="$v.batiments.contactPhone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.codePostal')" for="batiments-codePostal"
              >Code Postal</label
            >
            <input
              type="text"
              class="form-control"
              name="codePostal"
              id="batiments-codePostal"
              data-cy="codePostal"
              :class="{ valid: !$v.batiments.codePostal.$invalid, invalid: $v.batiments.codePostal.$invalid }"
              v-model="$v.batiments.codePostal.$model"
            />
            <div v-if="$v.batiments.codePostal.$anyDirty && $v.batiments.codePostal.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.codePostal.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 6 })"
              >
                This field cannot be longer than 6 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.nonBatimentEtPhotosPublics')"
              for="batiments-nonBatimentEtPhotosPublics"
              >Non Batiment Et Photos Publics</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="nonBatimentEtPhotosPublics"
              id="batiments-nonBatimentEtPhotosPublics"
              data-cy="nonBatimentEtPhotosPublics"
              :class="{
                valid: !$v.batiments.nonBatimentEtPhotosPublics.$invalid,
                invalid: $v.batiments.nonBatimentEtPhotosPublics.$invalid,
              }"
              v-model="$v.batiments.nonBatimentEtPhotosPublics.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.dateCreationFiche')" for="batiments-dateCreationFiche"
              >Date Creation Fiche</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiments-dateCreationFiche"
                  v-model="$v.batiments.dateCreationFiche.$model"
                  name="dateCreationFiche"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="batiments-dateCreationFiche"
                data-cy="dateCreationFiche"
                type="text"
                class="form-control"
                name="dateCreationFiche"
                :class="{ valid: !$v.batiments.dateCreationFiche.$invalid, invalid: $v.batiments.dateCreationFiche.$invalid }"
                v-model="$v.batiments.dateCreationFiche.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.batiments.dateCreationFiche.$anyDirty && $v.batiments.dateCreationFiche.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.dateCreationFiche.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('lbstrawmapApp.batiments.dateModificationFiche')"
              for="batiments-dateModificationFiche"
              >Date Modification Fiche</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiments-dateModificationFiche"
                  v-model="$v.batiments.dateModificationFiche.$model"
                  name="dateModificationFiche"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="batiments-dateModificationFiche"
                data-cy="dateModificationFiche"
                type="text"
                class="form-control"
                name="dateModificationFiche"
                :class="{ valid: !$v.batiments.dateModificationFiche.$invalid, invalid: $v.batiments.dateModificationFiche.$invalid }"
                v-model="$v.batiments.dateModificationFiche.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.batiments.dateModificationFiche.$anyDirty && $v.batiments.dateModificationFiche.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.dateModificationFiche.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.creator')" for="batiments-creator">Creator</label>
            <select class="form-control" id="batiments-creator" data-cy="creator" name="creator" v-model="batiments.creator">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="batiments.creator && userOption.id === batiments.creator.id ? batiments.creator : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.email }}
              </option>
            </select>
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
            :disabled="$v.batiments.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./batiments-update.component.ts"></script>
