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
              <option value="LOGEMENT_COLLECTIF" v-bind:label="$t('lbstrawmapApp.UsageBatiment.LOGEMENT_COLLECTIF')">
                LOGEMENT_COLLECTIF
              </option>
              <option value="LOGEMENT_INDIVIDUEL" v-bind:label="$t('lbstrawmapApp.UsageBatiment.LOGEMENT_INDIVIDUEL')">
                LOGEMENT_INDIVIDUEL
              </option>
              <option value="LOGEMENT_INDIVIDUEL_GROUPE" v-bind:label="$t('lbstrawmapApp.UsageBatiment.LOGEMENT_INDIVIDUEL_GROUPE')">
                LOGEMENT_INDIVIDUEL_GROUPE
              </option>
              <option value="BATIMENT_ADMINISTRATIF" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_ADMINISTRATIF')">
                BATIMENT_ADMINISTRATIF
              </option>
              <option value="BATIMENT_COMMERCIAL" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_COMMERCIAL')">
                BATIMENT_COMMERCIAL
              </option>
              <option value="BATIMENT_INDUSTRIEL" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_INDUSTRIEL')">
                BATIMENT_INDUSTRIEL
              </option>
              <option value="BATIMENT_DE_LOISIRS" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_DE_LOISIRS')">
                BATIMENT_DE_LOISIRS
              </option>
              <option value="BATIMENT_DE_SANTE" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_DE_SANTE')">
                BATIMENT_DE_SANTE
              </option>
              <option value="BATIMENT_DE_RETRAITE" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_DE_RETRAITE')">
                BATIMENT_DE_RETRAITE
              </option>
              <option value="BATIMENT_EDUCATIF" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_EDUCATIF')">
                BATIMENT_EDUCATIF
              </option>
              <option value="BATIMENT_SOCIO_CULTUREl" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_SOCIO_CULTUREl')">
                BATIMENT_SOCIO_CULTUREl
              </option>
              <option value="BATIMENT_AGRICOLE" v-bind:label="$t('lbstrawmapApp.UsageBatiment.BATIMENT_AGRICOLE')">
                BATIMENT_AGRICOLE
              </option>
              <option value="OUVRAGE_EXCEPTIONNEL" v-bind:label="$t('lbstrawmapApp.UsageBatiment.OUVRAGE_EXCEPTIONNEL')">
                OUVRAGE_EXCEPTIONNEL
              </option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.UsageBatiment.AUTRE')">AUTRE</option>
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
              <option value="T_80_X_120_CM" v-bind:label="$t('lbstrawmapApp.TaillesBottes.T_80_X_120_CM')">T_80_X_120_CM</option>
              <option value="T_50_X_80_CM" v-bind:label="$t('lbstrawmapApp.TaillesBottes.T_50_X_80_CM')">T_50_X_80_CM</option>
              <option value="T_37_X_47_CM" v-bind:label="$t('lbstrawmapApp.TaillesBottes.T_37_X_47_CM')">T_37_X_47_CM</option>
              <option value="T_26_X_45_CM" v-bind:label="$t('lbstrawmapApp.TaillesBottes.T_26_X_45_CM')">T_26_X_45_CM</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.TaillesBottes.AUTRE')">AUTRE</option>
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
              <option value="BLE" v-bind:label="$t('lbstrawmapApp.Cereale.BLE')">BLE</option>
              <option value="ORGE" v-bind:label="$t('lbstrawmapApp.Cereale.ORGE')">ORGE</option>
              <option value="AVOINE" v-bind:label="$t('lbstrawmapApp.Cereale.AVOINE')">AVOINE</option>
              <option value="SEIGLE" v-bind:label="$t('lbstrawmapApp.Cereale.SEIGLE')">SEIGLE</option>
              <option value="TRITICALE" v-bind:label="$t('lbstrawmapApp.Cereale.TRITICALE')">TRITICALE</option>
              <option value="RIZ" v-bind:label="$t('lbstrawmapApp.Cereale.RIZ')">RIZ</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.Cereale.AUTRE')">AUTRE</option>
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
              <option value="OUI" v-bind:label="$t('lbstrawmapApp.YesNoPartial.OUI')">OUI</option>
              <option value="NON" v-bind:label="$t('lbstrawmapApp.YesNoPartial.NON')">NON</option>
              <option value="PARTIEL" v-bind:label="$t('lbstrawmapApp.YesNoPartial.PARTIEL')">PARTIEL</option>
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
              <option value="OUI" v-bind:label="$t('lbstrawmapApp.YesNoPartial.OUI')">OUI</option>
              <option value="NON" v-bind:label="$t('lbstrawmapApp.YesNoPartial.NON')">NON</option>
              <option value="PARTIEL" v-bind:label="$t('lbstrawmapApp.YesNoPartial.PARTIEL')">PARTIEL</option>
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
              <option value="PRE_CADRE_FLOTTANT" v-bind:label="$t('lbstrawmapApp.IntegBaie.PRE_CADRE_FLOTTANT')">PRE_CADRE_FLOTTANT</option>
              <option value="COULISSANT" v-bind:label="$t('lbstrawmapApp.IntegBaie.COULISSANT')">COULISSANT</option>
              <option value="BLOQUEURS" v-bind:label="$t('lbstrawmapApp.IntegBaie.BLOQUEURS')">BLOQUEURS</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.IntegBaie.AUTRE')">AUTRE</option>
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
              <option value="BETON_ARME" v-bind:label="$t('lbstrawmapApp.MateriauSb.BETON_ARME')">BETON_ARME</option>
              <option value="PARPAING_DE_CIMENT" v-bind:label="$t('lbstrawmapApp.MateriauSb.PARPAING_DE_CIMENT')">
                PARPAING_DE_CIMENT
              </option>
              <option value="BRIQUE_DE_TERRE_CUITE" v-bind:label="$t('lbstrawmapApp.MateriauSb.BRIQUE_DE_TERRE_CUITE')">
                BRIQUE_DE_TERRE_CUITE
              </option>
              <option value="BRIQUE_DE_PIERRE_PONCE" v-bind:label="$t('lbstrawmapApp.MateriauSb.BRIQUE_DE_PIERRE_PONCE')">
                BRIQUE_DE_PIERRE_PONCE
              </option>
              <option value="BETON_LEGER_ISOLANT" v-bind:label="$t('lbstrawmapApp.MateriauSb.BETON_LEGER_ISOLANT')">
                BETON_LEGER_ISOLANT
              </option>
              <option value="PIERRE" v-bind:label="$t('lbstrawmapApp.MateriauSb.PIERRE')">PIERRE</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.MateriauSb.AUTRE')">AUTRE</option>
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
              <option value="PLAQUE_DE_PLATRE" v-bind:label="$t('lbstrawmapApp.RevetInt.PLAQUE_DE_PLATRE')">PLAQUE_DE_PLATRE</option>
              <option value="LAMBRIS" v-bind:label="$t('lbstrawmapApp.RevetInt.LAMBRIS')">LAMBRIS</option>
              <option value="ENDUIT_TERRE" v-bind:label="$t('lbstrawmapApp.RevetInt.ENDUIT_TERRE')">ENDUIT_TERRE</option>
              <option value="ENDUIT_CHAUX" v-bind:label="$t('lbstrawmapApp.RevetInt.ENDUIT_CHAUX')">ENDUIT_CHAUX</option>
              <option value="ENDUIT_TERRE_ET_CHAUX" v-bind:label="$t('lbstrawmapApp.RevetInt.ENDUIT_TERRE_ET_CHAUX')">
                ENDUIT_TERRE_ET_CHAUX
              </option>
              <option value="ENDUIT_PLATRE" v-bind:label="$t('lbstrawmapApp.RevetInt.ENDUIT_PLATRE')">ENDUIT_PLATRE</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.RevetInt.AUTRE')">AUTRE</option>
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
              <option value="BARDAGE_VENTILE" v-bind:label="$t('lbstrawmapApp.RevetExt.BARDAGE_VENTILE')">BARDAGE_VENTILE</option>
              <option value="ENDUIT_TERRE" v-bind:label="$t('lbstrawmapApp.RevetExt.ENDUIT_TERRE')">ENDUIT_TERRE</option>
              <option value="ENDUIT_CHAUX" v-bind:label="$t('lbstrawmapApp.RevetExt.ENDUIT_CHAUX')">ENDUIT_CHAUX</option>
              <option value="ENDUIT_TERRE_ET_CHAUX" v-bind:label="$t('lbstrawmapApp.RevetExt.ENDUIT_TERRE_ET_CHAUX')">
                ENDUIT_TERRE_ET_CHAUX
              </option>
              <option value="ENDUIT_PLATRE" v-bind:label="$t('lbstrawmapApp.RevetExt.ENDUIT_PLATRE')">ENDUIT_PLATRE</option>
              <option value="AUTRE" v-bind:label="$t('lbstrawmapApp.RevetExt.AUTRE')">AUTRE</option>
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
