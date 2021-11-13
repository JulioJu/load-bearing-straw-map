<template>
  <div class="row justify-content-center">
    <!-- START added by JulioJu -->
    <!-- eslint-disable prettier/prettier -->
    <div class="col-4">
      <!-- END added by JulioJu -->
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="cartoPaillePorteuseApp.batiment.home.createOrEditLabel"
          data-cy="BatimentCreateUpdateHeading"
          v-text="$t('cartoPaillePorteuseApp.batiment.home.createOrEditLabel')"
        >
          Create or edit a Batiment
        </h2>
        <div>
          <div class="form-group" v-if="batiment.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="batiment.id" readonly />
          </div>

          <!-- START added by JulioJu -->
        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionGenerale')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.latitude')" for="batiment-latitude"
              >Latitude</label
            >
            <input
              type="number"
              class="form-control"
              name="latitude"
              id="batiment-latitude"
              data-cy="latitude"
              :class="{ valid: !$v.batiment.latitude.$invalid, invalid: $v.batiment.latitude.$invalid }"
              v-model.number="$v.batiment.latitude.$model"
              required
            />
            <div v-if="$v.batiment.latitude.$anyDirty && $v.batiment.latitude.$invalid">
              <small class="form-text text-danger" v-if="!$v.batiment.latitude.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.latitude.min" v-text="$t('entity.validation.min', { min: -90 })">
                This field should be at least -90.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.latitude.max" v-text="$t('entity.validation.max', { max: 90 })">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.latitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.longitude')" for="batiment-longitude"
              >Longitude</label
            >
            <input
              type="number"
              class="form-control"
              name="longitude"
              id="batiment-longitude"
              data-cy="longitude"
              :class="{ valid: !$v.batiment.longitude.$invalid, invalid: $v.batiment.longitude.$invalid }"
              v-model.number="$v.batiment.longitude.$model"
              required
            />
            <div v-if="$v.batiment.longitude.$anyDirty && $v.batiment.longitude.$invalid">
              <small class="form-text text-danger" v-if="!$v.batiment.longitude.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.longitude.min" v-text="$t('entity.validation.min', { min: -90 })">
                This field should be at least -90.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.longitude.max" v-text="$t('entity.validation.max', { max: 90 })">
                This field cannot be longer than 90 characters.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.longitude.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.nomBatiment')" for="batiment-nomBatiment"
              >Nom Batiment</label
            >
            <input
              type="text"
              class="form-control"
              name="nomBatiment"
              id="batiment-nomBatiment"
              data-cy="nomBatiment"
              :class="{ valid: !$v.batiment.nomBatiment.$invalid, invalid: $v.batiment.nomBatiment.$invalid }"
              v-model="$v.batiment.nomBatiment.$model"
            />
            <div v-if="$v.batiment.nomBatiment.$anyDirty && $v.batiment.nomBatiment.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.nomBatiment.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 40 })"
              >
                This field cannot be longer than 40 characters.
              </small>
            </div>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

    <b-card
      border-variant="primary"
      :header="$t('cartoPaillePorteuseApp.batiment.sectionPhoto')"
      header-bg-variant="primary"
      header-text-variant="white"
      align="left"
    >
      <b-card
        class="clickable"
        :aria-expanded="visiblePhotoPrincipale ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhotoPrincipale = !visiblePhotoPrincipale"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photoPrincipale')"
        align="left"
      >
        <b-collapse v-model="visiblePhotoPrincipale">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photoPrincipale')" for="batiment-photoPrincipale" -->
            <!--   >Photo Principale</label -->
            <!-- > -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photoPrincipaleContentType + ';base64,' + batiment.photoPrincipale"
                style="max-height: 100px"
                v-if="batiment.photoPrincipale"
                alt="batiment image"
              />
              <div v-if="batiment.photoPrincipale" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photoPrincipaleContentType }}, {{ byteSize(batiment.photoPrincipale) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photoPrincipale', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photoPrincipale"
              id="batiment-photoPrincipale"
              data-cy="photoPrincipale"
              :class="{ valid: !$v.batiment.photoPrincipale.$invalid, invalid: $v.batiment.photoPrincipale.$invalid }"
              v-model="$v.batiment.photoPrincipale.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photoPrincipaleContentType"
              id="batiment-photoPrincipaleContentType"
              v-model="batiment.photoPrincipaleContentType"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photoPrincipaleLegende')"
              for="batiment-photoPrincipaleLegende"
              >Photo Principale Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photoPrincipaleLegende"
              id="batiment-photoPrincipaleLegende"
              data-cy="photoPrincipaleLegende"
              :class="{ valid: !$v.batiment.photoPrincipaleLegende.$invalid, invalid: $v.batiment.photoPrincipaleLegende.$invalid }"
              v-model="$v.batiment.photoPrincipaleLegende.$model"
            />
            <div v-if="$v.batiment.photoPrincipaleLegende.$anyDirty && $v.batiment.photoPrincipaleLegende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photoPrincipaleLegende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photoPrincipaleDescription')"
              for="batiment-photoPrincipaleDescription"
              >Photo Principale Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photoPrincipaleDescription"
              id="batiment-photoPrincipaleDescription"
              data-cy="photoPrincipaleDescription"
              :class="{ valid: !$v.batiment.photoPrincipaleDescription.$invalid, invalid: $v.batiment.photoPrincipaleDescription.$invalid }"
              v-model="$v.batiment.photoPrincipaleDescription.$model"
            />
          </div>
        <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>

      <b-card
        class="clickable"
        :aria-expanded="visiblePhoto1 ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhoto1 = !visiblePhoto1"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photo1')"
        align="left"
      >
        <b-collapse v-model="visiblePhoto1">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo1')" for="batiment-photo1">Photo 1</label> -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photo1ContentType + ';base64,' + batiment.photo1"
                style="max-height: 100px"
                v-if="batiment.photo1"
                alt="batiment image"
              />
              <div v-if="batiment.photo1" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photo1ContentType }}, {{ byteSize(batiment.photo1) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photo1', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo1"
              id="batiment-photo1"
              data-cy="photo1"
              :class="{ valid: !$v.batiment.photo1.$invalid, invalid: $v.batiment.photo1.$invalid }"
              v-model="$v.batiment.photo1.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo1ContentType"
              id="batiment-photo1ContentType"
              v-model="batiment.photo1ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo1Legende')" for="batiment-photo1Legende"
              >Photo 1 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo1Legende"
              id="batiment-photo1Legende"
              data-cy="photo1Legende"
              :class="{ valid: !$v.batiment.photo1Legende.$invalid, invalid: $v.batiment.photo1Legende.$invalid }"
              v-model="$v.batiment.photo1Legende.$model"
            />
            <div v-if="$v.batiment.photo1Legende.$anyDirty && $v.batiment.photo1Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photo1Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photo1Description')"
              for="batiment-photo1Description"
              >Photo 1 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo1Description"
              id="batiment-photo1Description"
              data-cy="photo1Description"
              :class="{ valid: !$v.batiment.photo1Description.$invalid, invalid: $v.batiment.photo1Description.$invalid }"
              v-model="$v.batiment.photo1Description.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>

      <b-card
        class="clickable"
        :aria-expanded="visiblePhoto2 ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhoto2 = !visiblePhoto2"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photo2')"
        align="left"
      >
        <b-collapse v-model="visiblePhoto2">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo2')" for="batiment-photo2">Photo 2</label> -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photo2ContentType + ';base64,' + batiment.photo2"
                style="max-height: 100px"
                v-if="batiment.photo2"
                alt="batiment image"
              />
              <div v-if="batiment.photo2" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photo2ContentType }}, {{ byteSize(batiment.photo2) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photo2', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo2"
              id="batiment-photo2"
              data-cy="photo2"
              :class="{ valid: !$v.batiment.photo2.$invalid, invalid: $v.batiment.photo2.$invalid }"
              v-model="$v.batiment.photo2.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo2ContentType"
              id="batiment-photo2ContentType"
              v-model="batiment.photo2ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo2Legende')" for="batiment-photo2Legende"
              >Photo 2 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo2Legende"
              id="batiment-photo2Legende"
              data-cy="photo2Legende"
              :class="{ valid: !$v.batiment.photo2Legende.$invalid, invalid: $v.batiment.photo2Legende.$invalid }"
              v-model="$v.batiment.photo2Legende.$model"
            />
            <div v-if="$v.batiment.photo2Legende.$anyDirty && $v.batiment.photo2Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photo2Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photo2Description')"
              for="batiment-photo2Description"
              >Photo 2 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo2Description"
              id="batiment-photo2Description"
              data-cy="photo2Description"
              :class="{ valid: !$v.batiment.photo2Description.$invalid, invalid: $v.batiment.photo2Description.$invalid }"
              v-model="$v.batiment.photo2Description.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>

      <b-card
        class="clickable"
        :aria-expanded="visiblePhoto3 ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhoto3 = !visiblePhoto3"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photo3')"
        align="left"
      >
        <b-collapse v-model="visiblePhoto3">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo3')" for="batiment-photo3">Photo 3</label> -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photo3ContentType + ';base64,' + batiment.photo3"
                style="max-height: 100px"
                v-if="batiment.photo3"
                alt="batiment image"
              />
              <div v-if="batiment.photo3" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photo3ContentType }}, {{ byteSize(batiment.photo3) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photo3', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo3"
              id="batiment-photo3"
              data-cy="photo3"
              :class="{ valid: !$v.batiment.photo3.$invalid, invalid: $v.batiment.photo3.$invalid }"
              v-model="$v.batiment.photo3.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo3ContentType"
              id="batiment-photo3ContentType"
              v-model="batiment.photo3ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo3Legende')" for="batiment-photo3Legende"
              >Photo 3 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo3Legende"
              id="batiment-photo3Legende"
              data-cy="photo3Legende"
              :class="{ valid: !$v.batiment.photo3Legende.$invalid, invalid: $v.batiment.photo3Legende.$invalid }"
              v-model="$v.batiment.photo3Legende.$model"
            />
            <div v-if="$v.batiment.photo3Legende.$anyDirty && $v.batiment.photo3Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photo3Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photo3Description')"
              for="batiment-photo3Description"
              >Photo 3 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo3Description"
              id="batiment-photo3Description"
              data-cy="photo3Description"
              :class="{ valid: !$v.batiment.photo3Description.$invalid, invalid: $v.batiment.photo3Description.$invalid }"
              v-model="$v.batiment.photo3Description.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>

      <b-card
        class="clickable"
        :aria-expanded="visiblePhoto4 ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhoto4 = !visiblePhoto4"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photo4')"
        align="left"
      >
        <b-collapse v-model="visiblePhoto4">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo4')" for="batiment-photo4">Photo 4</label> -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photo4ContentType + ';base64,' + batiment.photo4"
                style="max-height: 100px"
                v-if="batiment.photo4"
                alt="batiment image"
              />
              <div v-if="batiment.photo4" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photo4ContentType }}, {{ byteSize(batiment.photo4) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photo4', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo4"
              id="batiment-photo4"
              data-cy="photo4"
              :class="{ valid: !$v.batiment.photo4.$invalid, invalid: $v.batiment.photo4.$invalid }"
              v-model="$v.batiment.photo4.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo4ContentType"
              id="batiment-photo4ContentType"
              v-model="batiment.photo4ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo4Legende')" for="batiment-photo4Legende"
              >Photo 4 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo4Legende"
              id="batiment-photo4Legende"
              data-cy="photo4Legende"
              :class="{ valid: !$v.batiment.photo4Legende.$invalid, invalid: $v.batiment.photo4Legende.$invalid }"
              v-model="$v.batiment.photo4Legende.$model"
            />
            <div v-if="$v.batiment.photo4Legende.$anyDirty && $v.batiment.photo4Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photo4Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photo4Description')"
              for="batiment-photo4Description"
              >Photo 4 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo4Description"
              id="batiment-photo4Description"
              data-cy="photo4Description"
              :class="{ valid: !$v.batiment.photo4Description.$invalid, invalid: $v.batiment.photo4Description.$invalid }"
              v-model="$v.batiment.photo4Description.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>

      <b-card
        class="clickable"
        :aria-expanded="visiblePhoto5 ? 'true' : 'false'"
        aria-controls="collapse-4"
        @click="visiblePhoto5 = !visiblePhoto5"
        border-variant="secondary"
        :header="$t('cartoPaillePorteuseApp.batiment.photo5')"
        align="left"
      >
        <b-collapse v-model="visiblePhoto5">
          <div class="form-group">
            <!-- <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo5')" for="batiment-photo5">Photo 5</label> -->
            <!-- END added by JulioJu -->
            <div>
              <img
                v-bind:src="'data:' + batiment.photo5ContentType + ';base64,' + batiment.photo5"
                style="max-height: 100px"
                v-if="batiment.photo5"
                alt="batiment image"
              />
              <div v-if="batiment.photo5" class="form-text text-danger clearfix">
                <span class="pull-left">{{ batiment.photo5ContentType }}, {{ byteSize(batiment.photo5) }}</span>
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
                v-on:change="setFileData($event, batiment, 'photo5', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="photo5"
              id="batiment-photo5"
              data-cy="photo5"
              :class="{ valid: !$v.batiment.photo5.$invalid, invalid: $v.batiment.photo5.$invalid }"
              v-model="$v.batiment.photo5.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="photo5ContentType"
              id="batiment-photo5ContentType"
              v-model="batiment.photo5ContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.photo5Legende')" for="batiment-photo5Legende"
              >Photo 5 Legende</label
            >
            <input
              type="text"
              class="form-control"
              name="photo5Legende"
              id="batiment-photo5Legende"
              data-cy="photo5Legende"
              :class="{ valid: !$v.batiment.photo5Legende.$invalid, invalid: $v.batiment.photo5Legende.$invalid }"
              v-model="$v.batiment.photo5Legende.$model"
            />
            <div v-if="$v.batiment.photo5Legende.$anyDirty && $v.batiment.photo5Legende.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.photo5Legende.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.photo5Description')"
              for="batiment-photo5Description"
              >Photo 5 Description</label
            >
            <input
              type="text"
              class="form-control"
              name="photo5Description"
              id="batiment-photo5Description"
              data-cy="photo5Description"
              :class="{ valid: !$v.batiment.photo5Description.$invalid, invalid: $v.batiment.photo5Description.$invalid }"
              v-model="$v.batiment.photo5Description.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-collapse>
      </b-card>
    </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionInformationGenerales')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.usageBatiment')" for="batiment-usageBatiment"
              >Usage Batiment</label
            >
            <select
              class="form-control"
              name="usageBatiment"
              :class="{ valid: !$v.batiment.usageBatiment.$invalid, invalid: $v.batiment.usageBatiment.$invalid }"
              v-model="$v.batiment.usageBatiment.$model"
              id="batiment-usageBatiment"
              data-cy="usageBatiment"
            >
              <option
                v-for="usageBatiment in usageBatimentValues"
                :key="usageBatiment"
                v-bind:value="usageBatiment"
                v-bind:label="$t('cartoPaillePorteuseApp.UsageBatiment.' + usageBatiment)"
              >
                {{ usageBatiment }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.usageBatimentAutre')"
              for="batiment-usageBatimentAutre"
              >Usage Batiment Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="usageBatimentAutre"
              id="batiment-usageBatimentAutre"
              data-cy="usageBatimentAutre"
              :class="{ valid: !$v.batiment.usageBatimentAutre.$invalid, invalid: $v.batiment.usageBatimentAutre.$invalid }"
              v-model="$v.batiment.usageBatimentAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>
            <!-- END added by JulioJu -->

          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.cout')" for="batiment-cout">Cout</label>
            <input
              type="number"
              class="form-control"
              name="cout"
              id="batiment-cout"
              data-cy="cout"
              :class="{ valid: !$v.batiment.cout.$invalid, invalid: $v.batiment.cout.$invalid }"
              v-model.number="$v.batiment.cout.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.surfacePlancher')" for="batiment-surfacePlancher"
              >Surface Plancher</label
            >
            <input
              type="number"
              class="form-control"
              name="surfacePlancher"
              id="batiment-surfacePlancher"
              data-cy="surfacePlancher"
              :class="{ valid: !$v.batiment.surfacePlancher.$invalid, invalid: $v.batiment.surfacePlancher.$invalid }"
              v-model.number="$v.batiment.surfacePlancher.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.niveaux')" for="batiment-niveaux">Niveaux</label>
            <input
              type="number"
              class="form-control"
              name="niveaux"
              id="batiment-niveaux"
              data-cy="niveaux"
              :class="{ valid: !$v.batiment.niveaux.$invalid, invalid: $v.batiment.niveaux.$invalid }"
              v-model.number="$v.batiment.niveaux.$model"
            />
            <div v-if="$v.batiment.niveaux.$anyDirty && $v.batiment.niveaux.$invalid">
              <small class="form-text text-danger" v-if="!$v.batiment.niveaux.min" v-text="$t('entity.validation.min', { min: 1 })">
                This field should be at least 1.
              </small>
              <small class="form-text text-danger" v-if="!$v.batiment.niveaux.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.travauxNeuf')" for="batiment-travauxNeuf"
              >Travaux Neuf</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxNeuf"
              id="batiment-travauxNeuf"
              data-cy="travauxNeuf"
              :class="{ valid: !$v.batiment.travauxNeuf.$invalid, invalid: $v.batiment.travauxNeuf.$invalid }"
              v-model="$v.batiment.travauxNeuf.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.travauxExtension')"
              for="batiment-travauxExtension"
              >Travaux Extension</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxExtension"
              id="batiment-travauxExtension"
              data-cy="travauxExtension"
              :class="{ valid: !$v.batiment.travauxExtension.$invalid, invalid: $v.batiment.travauxExtension.$invalid }"
              v-model="$v.batiment.travauxExtension.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.travauxRenov')" for="batiment-travauxRenov"
              >Travaux Renov</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxRenov"
              id="batiment-travauxRenov"
              data-cy="travauxRenov"
              :class="{ valid: !$v.batiment.travauxRenov.$invalid, invalid: $v.batiment.travauxRenov.$invalid }"
              v-model="$v.batiment.travauxRenov.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.travauxIte')" for="batiment-travauxIte"
              >Travaux Ite</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxIte"
              id="batiment-travauxIte"
              data-cy="travauxIte"
              :class="{ valid: !$v.batiment.travauxIte.$invalid, invalid: $v.batiment.travauxIte.$invalid }"
              v-model="$v.batiment.travauxIte.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.travauxIti')" for="batiment-travauxIti"
              >Travaux Iti</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="travauxIti"
              id="batiment-travauxIti"
              data-cy="travauxIti"
              :class="{ valid: !$v.batiment.travauxIti.$invalid, invalid: $v.batiment.travauxIti.$invalid }"
              v-model="$v.batiment.travauxIti.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionChronologie')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.constructionDebut')"
              for="batiment-constructionDebut"
              >Construction Debut</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiment-constructionDebut"
                  v-model="$v.batiment.constructionDebut.$model"
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
                id="batiment-constructionDebut"
                data-cy="constructionDebut"
                type="text"
                class="form-control"
                name="constructionDebut"
                :class="{ valid: !$v.batiment.constructionDebut.$invalid, invalid: $v.batiment.constructionDebut.$invalid }"
                v-model="$v.batiment.constructionDebut.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.constructionFin')" for="batiment-constructionFin"
              >Construction Fin</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="batiment-constructionFin"
                  v-model="$v.batiment.constructionFin.$model"
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
                id="batiment-constructionFin"
                data-cy="constructionFin"
                type="text"
                class="form-control"
                name="constructionFin"
                :class="{ valid: !$v.batiment.constructionFin.$invalid, invalid: $v.batiment.constructionFin.$invalid }"
                v-model="$v.batiment.constructionFin.$model"
              />
            </b-input-group>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionBotteDePaille')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.bottesTaille')" for="batiment-bottesTaille"
              >Bottes Taille</label
            >
            <select
              class="form-control"
              name="bottesTaille"
              :class="{ valid: !$v.batiment.bottesTaille.$invalid, invalid: $v.batiment.bottesTaille.$invalid }"
              v-model="$v.batiment.bottesTaille.$model"
              id="batiment-bottesTaille"
              data-cy="bottesTaille"
            >
              <option
                v-for="taillesBottes in taillesBottesValues"
                :key="taillesBottes"
                v-bind:value="taillesBottes"
                v-bind:label="$t('cartoPaillePorteuseApp.TaillesBottes.' + taillesBottes)"
              >
                {{ taillesBottes }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.botteTailleAutre')"
              for="batiment-botteTailleAutre"
              >Botte Taille Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="botteTailleAutre"
              id="batiment-botteTailleAutre"
              data-cy="botteTailleAutre"
              :class="{ valid: !$v.batiment.botteTailleAutre.$invalid, invalid: $v.batiment.botteTailleAutre.$invalid }"
              v-model="$v.batiment.botteTailleAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.bottesDensite')" for="batiment-bottesDensite"
              >Bottes Densite</label
            >
            <input
              type="number"
              class="form-control"
              name="bottesDensite"
              id="batiment-bottesDensite"
              data-cy="bottesDensite"
              :class="{ valid: !$v.batiment.bottesDensite.$invalid, invalid: $v.batiment.bottesDensite.$invalid }"
              v-model.number="$v.batiment.bottesDensite.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.bottesCereale')" for="batiment-bottesCereale"
              >Bottes Cereale</label
            >
            <select
              class="form-control"
              name="bottesCereale"
              :class="{ valid: !$v.batiment.bottesCereale.$invalid, invalid: $v.batiment.bottesCereale.$invalid }"
              v-model="$v.batiment.bottesCereale.$model"
              id="batiment-bottesCereale"
              data-cy="bottesCereale"
            >
              <option
                v-for="cereale in cerealeValues"
                :key="cereale"
                v-bind:value="cereale"
                v-bind:label="$t('cartoPaillePorteuseApp.Cereale.' + cereale)"
              >
                {{ cereale }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.distanceAppro')" for="batiment-distanceAppro"
              >Distance Appro</label
            >
            <input
              type="number"
              class="form-control"
              name="distanceAppro"
              id="batiment-distanceAppro"
              data-cy="distanceAppro"
              :class="{ valid: !$v.batiment.distanceAppro.$invalid, invalid: $v.batiment.distanceAppro.$invalid }"
              v-model.number="$v.batiment.distanceAppro.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionChantier')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.autoconstruction')"
              for="batiment-autoconstruction"
              >Autoconstruction</label
            >
            <select
              class="form-control"
              name="autoconstruction"
              :class="{ valid: !$v.batiment.autoconstruction.$invalid, invalid: $v.batiment.autoconstruction.$invalid }"
              v-model="$v.batiment.autoconstruction.$model"
              id="batiment-autoconstruction"
              data-cy="autoconstruction"
            >
              <option
                v-for="yesNoPartial in yesNoPartialValues"
                :key="yesNoPartial"
                v-bind:value="yesNoPartial"
                v-bind:label="$t('cartoPaillePorteuseApp.YesNoPartial.' + yesNoPartial)"
              >
                {{ yesNoPartial }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.participatif')" for="batiment-participatif"
              >Participatif</label
            >
            <select
              class="form-control"
              name="participatif"
              :class="{ valid: !$v.batiment.participatif.$invalid, invalid: $v.batiment.participatif.$invalid }"
              v-model="$v.batiment.participatif.$model"
              id="batiment-participatif"
              data-cy="participatif"
            >
              <option
                v-for="yesNoPartial in yesNoPartialValues"
                :key="yesNoPartial"
                v-bind:value="yesNoPartial"
                v-bind:label="$t('cartoPaillePorteuseApp.YesNoPartial.' + yesNoPartial)"
              >
                {{ yesNoPartial }}
              </option>
            </select>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionConstruction')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.structCompl')" for="batiment-structCompl"
              >Struct Compl</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="structCompl"
              id="batiment-structCompl"
              data-cy="structCompl"
              :class="{ valid: !$v.batiment.structCompl.$invalid, invalid: $v.batiment.structCompl.$invalid }"
              v-model="$v.batiment.structCompl.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.structComplNature')"
              for="batiment-structComplNature"
              >Struct Compl Nature</label
            >
            <select
              class="form-control"
              name="structComplNature"
              :class="{ valid: !$v.batiment.structComplNature.$invalid, invalid: $v.batiment.structComplNature.$invalid }"
              v-model="$v.batiment.structComplNature.$model"
              id="batiment-structComplNature"
              data-cy="structComplNature"
            >
              <option
                v-for="structureComplementaire in structureComplementaireValues"
                :key="structureComplementaire"
                v-bind:value="structureComplementaire"
                v-bind:label="$t('cartoPaillePorteuseApp.StructureComplementaire.' + structureComplementaire)"
              >
                {{ structureComplementaire }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.structComplAutre')"
              for="batiment-structComplAutre"
              >Struct Compl Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="structComplAutre"
              id="batiment-structComplAutre"
              data-cy="structComplAutre"
              :class="{ valid: !$v.batiment.structComplAutre.$invalid, invalid: $v.batiment.structComplAutre.$invalid }"
              v-model="$v.batiment.structComplAutre.$model"
            />
            <div v-if="$v.batiment.structComplAutre.$anyDirty && $v.batiment.structComplAutre.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.structComplAutre.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.structComplInfos')"
              for="batiment-structComplInfos"
              >Struct Compl Infos</label
            >
            <textarea
              class="form-control"
              name="structComplInfos"
              id="batiment-structComplInfos"
              data-cy="structComplInfos"
              :class="{ valid: !$v.batiment.structComplInfos.$invalid, invalid: $v.batiment.structComplInfos.$invalid }"
              v-model="$v.batiment.structComplInfos.$model"
            ></textarea>
          </div>
          <!-- START added by JulioJu -->
        </b-card>
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.longMaxSansMurRefend')"
              for="batiment-longMaxSansMurRefend"
              >Long Max Sans Mur Refend</label
            >
            <input
              type="number"
              class="form-control"
              name="longMaxSansMurRefend"
              id="batiment-longMaxSansMurRefend"
              data-cy="longMaxSansMurRefend"
              :class="{ valid: !$v.batiment.longMaxSansMurRefend.$invalid, invalid: $v.batiment.longMaxSansMurRefend.$invalid }"
              v-model.number="$v.batiment.longMaxSansMurRefend.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.noteCalcul')" for="batiment-noteCalcul"
              >Note Calcul</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="noteCalcul"
              id="batiment-noteCalcul"
              data-cy="noteCalcul"
              :class="{ valid: !$v.batiment.noteCalcul.$invalid, invalid: $v.batiment.noteCalcul.$invalid }"
              v-model="$v.batiment.noteCalcul.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.nbrRangDeBottes')" for="batiment-nbrRangDeBottes"
              >Nbr Rang De Bottes</label
            >
            <input
              type="number"
              class="form-control"
              name="nbrRangDeBottes"
              id="batiment-nbrRangDeBottes"
              data-cy="nbrRangDeBottes"
              :class="{ valid: !$v.batiment.nbrRangDeBottes.$invalid, invalid: $v.batiment.nbrRangDeBottes.$invalid }"
              v-model.number="$v.batiment.nbrRangDeBottes.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.integBaie')" for="batiment-integBaie"
              >Integ Baie</label
            >
            <select
              class="form-control"
              name="integBaie"
              :class="{ valid: !$v.batiment.integBaie.$invalid, invalid: $v.batiment.integBaie.$invalid }"
              v-model="$v.batiment.integBaie.$model"
              id="batiment-integBaie"
              data-cy="integBaie"
            >
              <option
                v-for="integBaie in integBaieValues"
                :key="integBaie"
                v-bind:value="integBaie"
                v-bind:label="$t('cartoPaillePorteuseApp.IntegBaie.' + integBaie)"
              >
                {{ integBaie }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.integBaieAutre')" for="batiment-integBaieAutre"
              >Integ Baie Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="integBaieAutre"
              id="batiment-integBaieAutre"
              data-cy="integBaieAutre"
              :class="{ valid: !$v.batiment.integBaieAutre.$invalid, invalid: $v.batiment.integBaieAutre.$invalid }"
              v-model="$v.batiment.integBaieAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.supportAncrage')" for="batiment-supportAncrage"
              >Support Ancrage</label
            >
            <select
              class="form-control"
              name="supportAncrage"
              :class="{ valid: !$v.batiment.supportAncrage.$invalid, invalid: $v.batiment.supportAncrage.$invalid }"
              v-model="$v.batiment.supportAncrage.$model"
              id="batiment-supportAncrage"
              data-cy="supportAncrage"
            >
              <option
                v-for="supportAncrage in supportAncrageValues"
                :key="supportAncrage"
                v-bind:value="supportAncrage"
                v-bind:label="$t('cartoPaillePorteuseApp.SupportAncrage.' + supportAncrage)"
              >
                {{ supportAncrage }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.supportAncrageAutre')"
              for="batiment-supportAncrageAutre"
              >Support Ancrage Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="supportAncrageAutre"
              id="batiment-supportAncrageAutre"
              data-cy="supportAncrageAutre"
              :class="{ valid: !$v.batiment.supportAncrageAutre.$invalid, invalid: $v.batiment.supportAncrageAutre.$invalid }"
              v-model="$v.batiment.supportAncrageAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>

      <b-card border-variant="secondary" :header="$t('cartoPaillePorteuseApp.batiment.sousSectionRevetement')" align="left">
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.revetInt')" for="batiment-revetInt"
              >Revet Int</label
            >
            <select
              class="form-control"
              name="revetInt"
              :class="{ valid: !$v.batiment.revetInt.$invalid, invalid: $v.batiment.revetInt.$invalid }"
              v-model="$v.batiment.revetInt.$model"
              id="batiment-revetInt"
              data-cy="revetInt"
            >
              <option
                v-for="revetInt in revetIntValues"
                :key="revetInt"
                v-bind:value="revetInt"
                v-bind:label="$t('cartoPaillePorteuseApp.RevetInt.' + revetInt)"
              >
                {{ revetInt }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.revetIntAutre')" for="batiment-revetIntAutre"
              >Revet Int Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="revetIntAutre"
              id="batiment-revetIntAutre"
              data-cy="revetIntAutre"
              :class="{ valid: !$v.batiment.revetIntAutre.$invalid, invalid: $v.batiment.revetIntAutre.$invalid }"
              v-model="$v.batiment.revetIntAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>
        <b-card border-variant="secondary" align="left" class="mb-3">
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.revetExt')" for="batiment-revetExt"
              >Revet Ext</label
            >
            <select
              class="form-control"
              name="revetExt"
              :class="{ valid: !$v.batiment.revetExt.$invalid, invalid: $v.batiment.revetExt.$invalid }"
              v-model="$v.batiment.revetExt.$model"
              id="batiment-revetExt"
              data-cy="revetExt"
            >
              <option
                v-for="revetExt in revetExtValues"
                :key="revetExt"
                v-bind:value="revetExt"
                v-bind:label="$t('cartoPaillePorteuseApp.RevetExt.' + revetExt)"
              >
                {{ revetExt }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.revetExtAutre')" for="batiment-revetExtAutre"
              >Revet Ext Autre</label
            >
            <input
              type="text"
              class="form-control"
              name="revetExtAutre"
              id="batiment-revetExtAutre"
              data-cy="revetExtAutre"
              :class="{ valid: !$v.batiment.revetExtAutre.$invalid, invalid: $v.batiment.revetExtAutre.$invalid }"
              v-model="$v.batiment.revetExtAutre.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>
      </b-card>
    </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionActrices')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.maitreDOuvrage')" for="batiment-maitreDOuvrage"
              >Maitre D Ouvrage</label
            >
            <input
              type="text"
              class="form-control"
              name="maitreDOuvrage"
              id="batiment-maitreDOuvrage"
              data-cy="maitreDOuvrage"
              :class="{ valid: !$v.batiment.maitreDOuvrage.$invalid, invalid: $v.batiment.maitreDOuvrage.$invalid }"
              v-model="$v.batiment.maitreDOuvrage.$model"
            />
            <div v-if="$v.batiment.maitreDOuvrage.$anyDirty && $v.batiment.maitreDOuvrage.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.maitreDOuvrage.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.maitreDOeuvre')" for="batiment-maitreDOeuvre"
              >Maitre D Oeuvre</label
            >
            <input
              type="text"
              class="form-control"
              name="maitreDOeuvre"
              id="batiment-maitreDOeuvre"
              data-cy="maitreDOeuvre"
              :class="{ valid: !$v.batiment.maitreDOeuvre.$invalid, invalid: $v.batiment.maitreDOeuvre.$invalid }"
              v-model="$v.batiment.maitreDOeuvre.$model"
            />
            <div v-if="$v.batiment.maitreDOeuvre.$anyDirty && $v.batiment.maitreDOeuvre.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.maitreDOeuvre.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.architecte')" for="batiment-architecte"
              >Architecte</label
            >
            <input
              type="text"
              class="form-control"
              name="architecte"
              id="batiment-architecte"
              data-cy="architecte"
              :class="{ valid: !$v.batiment.architecte.$invalid, invalid: $v.batiment.architecte.$invalid }"
              v-model="$v.batiment.architecte.$model"
            />
            <div v-if="$v.batiment.architecte.$anyDirty && $v.batiment.architecte.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.architecte.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.bureauDEtudeStructure')"
              for="batiment-bureauDEtudeStructure"
              >Bureau D Etude Structure</label
            >
            <input
              type="text"
              class="form-control"
              name="bureauDEtudeStructure"
              id="batiment-bureauDEtudeStructure"
              data-cy="bureauDEtudeStructure"
              :class="{ valid: !$v.batiment.bureauDEtudeStructure.$invalid, invalid: $v.batiment.bureauDEtudeStructure.$invalid }"
              v-model="$v.batiment.bureauDEtudeStructure.$model"
            />
            <div v-if="$v.batiment.bureauDEtudeStructure.$anyDirty && $v.batiment.bureauDEtudeStructure.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.bureauDEtudeStructure.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.bureauControl')" for="batiment-bureauControl"
              >Bureau Control</label
            >
            <input
              type="text"
              class="form-control"
              name="bureauControl"
              id="batiment-bureauControl"
              data-cy="bureauControl"
              :class="{ valid: !$v.batiment.bureauControl.$invalid, invalid: $v.batiment.bureauControl.$invalid }"
              v-model="$v.batiment.bureauControl.$model"
            />
            <div v-if="$v.batiment.bureauControl.$anyDirty && $v.batiment.bureauControl.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.bureauControl.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.entrepriseBottes')"
              for="batiment-entrepriseBottes"
              >Entreprise Bottes</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseBottes"
              id="batiment-entrepriseBottes"
              data-cy="entrepriseBottes"
              :class="{ valid: !$v.batiment.entrepriseBottes.$invalid, invalid: $v.batiment.entrepriseBottes.$invalid }"
              v-model="$v.batiment.entrepriseBottes.$model"
            />
            <div v-if="$v.batiment.entrepriseBottes.$anyDirty && $v.batiment.entrepriseBottes.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.entrepriseBottes.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.entrepriseCharpente')"
              for="batiment-entrepriseCharpente"
              >Entreprise Charpente</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseCharpente"
              id="batiment-entrepriseCharpente"
              data-cy="entrepriseCharpente"
              :class="{ valid: !$v.batiment.entrepriseCharpente.$invalid, invalid: $v.batiment.entrepriseCharpente.$invalid }"
              v-model="$v.batiment.entrepriseCharpente.$model"
            />
            <div v-if="$v.batiment.entrepriseCharpente.$anyDirty && $v.batiment.entrepriseCharpente.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.entrepriseCharpente.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.entrepriseEnduits')"
              for="batiment-entrepriseEnduits"
              >Entreprise Enduits</label
            >
            <input
              type="text"
              class="form-control"
              name="entrepriseEnduits"
              id="batiment-entrepriseEnduits"
              data-cy="entrepriseEnduits"
              :class="{ valid: !$v.batiment.entrepriseEnduits.$invalid, invalid: $v.batiment.entrepriseEnduits.$invalid }"
              v-model="$v.batiment.entrepriseEnduits.$model"
            />
            <div v-if="$v.batiment.entrepriseEnduits.$anyDirty && $v.batiment.entrepriseEnduits.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.entrepriseEnduits.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionCommentaire')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('cartoPaillePorteuseApp.batiment.descriptionProjet')"
              for="batiment-descriptionProjet"
              >Description Projet</label
            >
            <textarea
              class="form-control"
              name="descriptionProjet"
              id="batiment-descriptionProjet"
              data-cy="descriptionProjet"
              :class="{ valid: !$v.batiment.descriptionProjet.$invalid, invalid: $v.batiment.descriptionProjet.$invalid }"
              v-model="$v.batiment.descriptionProjet.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.difficultees')" for="batiment-difficultees"
              >Difficultees</label
            >
            <textarea
              class="form-control"
              name="difficultees"
              id="batiment-difficultees"
              data-cy="difficultees"
              :class="{ valid: !$v.batiment.difficultees.$invalid, invalid: $v.batiment.difficultees.$invalid }"
              v-model="$v.batiment.difficultees.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.astuces')" for="batiment-astuces">Astuces</label>
            <textarea
              class="form-control"
              name="astuces"
              id="batiment-astuces"
              data-cy="astuces"
              :class="{ valid: !$v.batiment.astuces.$invalid, invalid: $v.batiment.astuces.$invalid }"
              v-model="$v.batiment.astuces.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.divers')" for="batiment-divers">Divers</label>
            <textarea
              class="form-control"
              name="divers"
              id="batiment-divers"
              data-cy="divers"
              :class="{ valid: !$v.batiment.divers.$invalid, invalid: $v.batiment.divers.$invalid }"
              v-model="$v.batiment.divers.$model"
            ></textarea>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="primary"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionContact')"
          header-bg-variant="primary"
          header-text-variant="white"
          align="left"
          class="mb-3"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.contactNom')" for="batiment-contactNom"
              >Contact Nom</label
            >
            <input
              type="text"
              class="form-control"
              name="contactNom"
              id="batiment-contactNom"
              data-cy="contactNom"
              :class="{ valid: !$v.batiment.contactNom.$invalid, invalid: $v.batiment.contactNom.$invalid }"
              v-model="$v.batiment.contactNom.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.contactMail')" for="batiment-contactMail"
              >Contact Mail</label
            >
            <input
              type="text"
              class="form-control"
              name="contactMail"
              id="batiment-contactMail"
              data-cy="contactMail"
              :class="{ valid: !$v.batiment.contactMail.$invalid, invalid: $v.batiment.contactMail.$invalid }"
              v-model="$v.batiment.contactMail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.contactPhone')" for="batiment-contactPhone"
              >Contact Phone</label
            >
            <input
              type="text"
              class="form-control"
              name="contactPhone"
              id="batiment-contactPhone"
              data-cy="contactPhone"
              :class="{ valid: !$v.batiment.contactPhone.$invalid, invalid: $v.batiment.contactPhone.$invalid }"
              v-model="$v.batiment.contactPhone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.codePostal')" for="batiment-codePostal"
              >Code Postal</label
            >
            <input
              type="text"
              class="form-control"
              name="codePostal"
              id="batiment-codePostal"
              data-cy="codePostal"
              :class="{ valid: !$v.batiment.codePostal.$invalid, invalid: $v.batiment.codePostal.$invalid }"
              v-model="$v.batiment.codePostal.$model"
            />
            <div v-if="$v.batiment.codePostal.$anyDirty && $v.batiment.codePostal.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiment.codePostal.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 6 })"
              >
                This field cannot be longer than 6 characters.
              </small>
            </div>
          </div>
          <!-- START added by JulioJu -->
        </b-card>

        <b-card
          border-variant="danger"
          :header="$t('cartoPaillePorteuseApp.batiment.sectionAutorisation')"
          header-bg-variant="danger"
          header-text-variant="white"
          align="left"
          class="mb-3"
        >
          <!-- END added by JulioJu -->
          <div class="form-group">
            <label class="form-control-label" v-text="$t('cartoPaillePorteuseApp.batiment.profilPublic')" for="batiment-profilPublic"
              >Profil Public</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="profilPublic"
              id="batiment-profilPublic"
              data-cy="profilPublic"
              :class="{ valid: !$v.batiment.profilPublic.$invalid, invalid: $v.batiment.profilPublic.$invalid }"
              v-model="$v.batiment.profilPublic.$model"
            />
          </div>
          <!-- START added by JulioJu -->
        </b-card>

          <!-- START added by JulioJu -->
          <div class="mb-3">
            <strong class="text-danger">
              En cliquant sur "{{ $t('entity.action.save') }}" vous vous engagagez à avoir lu
              <a class="text-info" target="_blank" to="/legal"
                                 >les Conditions générales d'utilisation, les limitations de responsabilité, la politique de traitement des données</a
                               >
                               et vous manifestez votre acceptation de toutes les dispositions sans restriction. Vous vous engagez également à ce que votre enregistrement soit conforme aux dispositions énoncées.
            </strong>
          </div>
          <!-- END added by JulioJu -->
          <!-- END added by JulioJu -->

        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.batiment.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./batiment-update.component.ts"></script>
<style>
/* START added by JulioJu */
.clickable {
  cursor: pointer;
}
/* END added by JulioJu */
</style>
