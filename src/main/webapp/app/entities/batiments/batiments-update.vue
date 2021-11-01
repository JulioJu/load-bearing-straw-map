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
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.nom')" for="batiments-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="batiments-nom"
              data-cy="nom"
              :class="{ valid: !$v.batiments.nom.$invalid, invalid: $v.batiments.nom.$invalid }"
              v-model="$v.batiments.nom.$model"
            />
            <div v-if="$v.batiments.nom.$anyDirty && $v.batiments.nom.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.nom.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 40 })"
              >
                This field cannot be longer than 40 characters.
              </small>
            </div>
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
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.usage')" for="batiments-usage">Usage</label>
            <select
              class="form-control"
              name="usage"
              :class="{ valid: !$v.batiments.usage.$invalid, invalid: $v.batiments.usage.$invalid }"
              v-model="$v.batiments.usage.$model"
              id="batiments-usage"
              data-cy="usage"
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
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.surface')" for="batiments-surface">Surface</label>
            <input
              type="number"
              class="form-control"
              name="surface"
              id="batiments-surface"
              data-cy="surface"
              :class="{ valid: !$v.batiments.surface.$invalid, invalid: $v.batiments.surface.$invalid }"
              v-model.number="$v.batiments.surface.$model"
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
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.materiauSb')" for="batiments-materiauSb"
              >Materiau Sb</label
            >
            <select
              class="form-control"
              name="materiauSb"
              :class="{ valid: !$v.batiments.materiauSb.$invalid, invalid: $v.batiments.materiauSb.$invalid }"
              v-model="$v.batiments.materiauSb.$model"
              id="batiments-materiauSb"
              data-cy="materiauSb"
            >
              <option
                v-for="materiauSb in materiauSbValues"
                :key="materiauSb"
                v-bind:value="materiauSb"
                v-bind:label="$t('lbstrawmapApp.MateriauSb.' + materiauSb)"
              >
                {{ materiauSb }}
              </option>
            </select>
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
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.concepteur')" for="batiments-concepteur"
              >Concepteur</label
            >
            <input
              type="text"
              class="form-control"
              name="concepteur"
              id="batiments-concepteur"
              data-cy="concepteur"
              :class="{ valid: !$v.batiments.concepteur.$invalid, invalid: $v.batiments.concepteur.$invalid }"
              v-model="$v.batiments.concepteur.$model"
            />
            <div v-if="$v.batiments.concepteur.$anyDirty && $v.batiments.concepteur.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.concepteur.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.realisateur')" for="batiments-realisateur"
              >Realisateur</label
            >
            <input
              type="text"
              class="form-control"
              name="realisateur"
              id="batiments-realisateur"
              data-cy="realisateur"
              :class="{ valid: !$v.batiments.realisateur.$invalid, invalid: $v.batiments.realisateur.$invalid }"
              v-model="$v.batiments.realisateur.$model"
            />
            <div v-if="$v.batiments.realisateur.$anyDirty && $v.batiments.realisateur.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.batiments.realisateur.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 512 })"
              >
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('lbstrawmapApp.batiments.description')" for="batiments-description"
              >Description</label
            >
            <textarea
              class="form-control"
              name="description"
              id="batiments-description"
              data-cy="description"
              :class="{ valid: !$v.batiments.description.$invalid, invalid: $v.batiments.description.$invalid }"
              v-model="$v.batiments.description.$model"
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
