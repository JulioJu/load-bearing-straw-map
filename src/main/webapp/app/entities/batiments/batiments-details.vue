<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="batiments">
        <h2 class="jh-entity-heading" data-cy="batimentsDetailsHeading">
          <span v-text="$t('lbstrawmapApp.batiments.detail.title')">Batiments</span> {{ batiments.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.latitude')">Latitude</span>
          </dt>
          <dd>
            <span>{{ batiments.latitude }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.longitude')">Longitude</span>
          </dt>
          <dd>
            <span>{{ batiments.longitude }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.nomBatiment')">Nom Batiment</span>
          </dt>
          <dd>
            <span>{{ batiments.nomBatiment }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photoPrincipale')">Photo Principale</span>
          </dt>
          <dd>
            <div v-if="batiments.photoPrincipale">
              <a v-on:click="openFile(batiments.photoPrincipaleContentType, batiments.photoPrincipale)">
                <img
                  v-bind:src="'data:' + batiments.photoPrincipaleContentType + ';base64,' + batiments.photoPrincipale"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photoPrincipaleContentType }}, {{ byteSize(batiments.photoPrincipale) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photoPrincipaleLegende')">Photo Principale Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photoPrincipaleLegende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photoPrincipaleDescription')">Photo Principale Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photoPrincipaleDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo1')">Photo 1</span>
          </dt>
          <dd>
            <div v-if="batiments.photo1">
              <a v-on:click="openFile(batiments.photo1ContentType, batiments.photo1)">
                <img
                  v-bind:src="'data:' + batiments.photo1ContentType + ';base64,' + batiments.photo1"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photo1ContentType }}, {{ byteSize(batiments.photo1) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo1Legende')">Photo 1 Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photo1Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo1Description')">Photo 1 Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photo1Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo2')">Photo 2</span>
          </dt>
          <dd>
            <div v-if="batiments.photo2">
              <a v-on:click="openFile(batiments.photo2ContentType, batiments.photo2)">
                <img
                  v-bind:src="'data:' + batiments.photo2ContentType + ';base64,' + batiments.photo2"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photo2ContentType }}, {{ byteSize(batiments.photo2) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo2Legende')">Photo 2 Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photo2Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo2Description')">Photo 2 Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photo2Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo3')">Photo 3</span>
          </dt>
          <dd>
            <div v-if="batiments.photo3">
              <a v-on:click="openFile(batiments.photo3ContentType, batiments.photo3)">
                <img
                  v-bind:src="'data:' + batiments.photo3ContentType + ';base64,' + batiments.photo3"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photo3ContentType }}, {{ byteSize(batiments.photo3) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo3Legende')">Photo 3 Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photo3Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo3Description')">Photo 3 Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photo3Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo4')">Photo 4</span>
          </dt>
          <dd>
            <div v-if="batiments.photo4">
              <a v-on:click="openFile(batiments.photo4ContentType, batiments.photo4)">
                <img
                  v-bind:src="'data:' + batiments.photo4ContentType + ';base64,' + batiments.photo4"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photo4ContentType }}, {{ byteSize(batiments.photo4) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo4Legende')">Photo 4 Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photo4Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo4Description')">Photo 4 Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photo4Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo5')">Photo 5</span>
          </dt>
          <dd>
            <div v-if="batiments.photo5">
              <a v-on:click="openFile(batiments.photo5ContentType, batiments.photo5)">
                <img
                  v-bind:src="'data:' + batiments.photo5ContentType + ';base64,' + batiments.photo5"
                  style="max-width: 100%"
                  alt="batiments image"
                />
              </a>
              {{ batiments.photo5ContentType }}, {{ byteSize(batiments.photo5) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo5Legende')">Photo 5 Legende</span>
          </dt>
          <dd>
            <span>{{ batiments.photo5Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.photo5Description')">Photo 5 Description</span>
          </dt>
          <dd>
            <span>{{ batiments.photo5Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')">Technique Secondaire</span>
          </dt>
          <dd>
            <span>{{ batiments.techniqueSecondaire }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.usageBatiment')">Usage Batiment</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.UsageBatiment.' + batiments.usageBatiment)">{{ batiments.usageBatiment }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.cout')">Cout</span>
          </dt>
          <dd>
            <span>{{ batiments.cout }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.surfacePlancher')">Surface Plancher</span>
          </dt>
          <dd>
            <span>{{ batiments.surfacePlancher }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.niveaux')">Niveaux</span>
          </dt>
          <dd>
            <span>{{ batiments.niveaux }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.travauxNeuf')">Travaux Neuf</span>
          </dt>
          <dd>
            <span>{{ batiments.travauxNeuf }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.travauxExtension')">Travaux Extension</span>
          </dt>
          <dd>
            <span>{{ batiments.travauxExtension }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.travauxRenov')">Travaux Renov</span>
          </dt>
          <dd>
            <span>{{ batiments.travauxRenov }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.travauxIte')">Travaux Ite</span>
          </dt>
          <dd>
            <span>{{ batiments.travauxIte }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.travauxIti')">Travaux Iti</span>
          </dt>
          <dd>
            <span>{{ batiments.travauxIti }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.constructionDebut')">Construction Debut</span>
          </dt>
          <dd>
            <span>{{ batiments.constructionDebut }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.constructionFin')">Construction Fin</span>
          </dt>
          <dd>
            <span>{{ batiments.constructionFin }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesTaille')">Bottes Taille</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.TaillesBottes.' + batiments.bottesTaille)">{{ batiments.bottesTaille }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.botteTailleAutre')">Botte Taille Autre</span>
          </dt>
          <dd>
            <span>{{ batiments.botteTailleAutre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesDensite')">Bottes Densite</span>
          </dt>
          <dd>
            <span>{{ batiments.bottesDensite }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesCereale')">Bottes Cereale</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.Cereale.' + batiments.bottesCereale)">{{ batiments.bottesCereale }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.distanceAppro')">Distance Appro</span>
          </dt>
          <dd>
            <span>{{ batiments.distanceAppro }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.autoconstruction')">Autoconstruction</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.autoconstruction)">{{ batiments.autoconstruction }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.participatif')">Participatif</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.participatif)">{{ batiments.participatif }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.structSuppl')">Struct Suppl</span>
          </dt>
          <dd>
            <span>{{ batiments.structSuppl }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.structSupplNature')">Struct Suppl Nature</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.StructureSupplementaire.' + batiments.structSupplNature)">{{
              batiments.structSupplNature
            }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.noteCalcul')">Note Calcul</span>
          </dt>
          <dd>
            <span>{{ batiments.noteCalcul }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.nbrRangDeBottes')">Nbr Rang De Bottes</span>
          </dt>
          <dd>
            <span>{{ batiments.nbrRangDeBottes }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.longMaxSansMurRefend')">Long Max Sans Mur Refend</span>
          </dt>
          <dd>
            <span>{{ batiments.longMaxSansMurRefend }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.integBaie')">Integ Baie</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.IntegBaie.' + batiments.integBaie)">{{ batiments.integBaie }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.supportAncrage')">Support Ancrage</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.SupportAncrage.' + batiments.supportAncrage)">{{ batiments.supportAncrage }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.supportAncragePrecisions')">Support Ancrage Precisions</span>
          </dt>
          <dd>
            <span>{{ batiments.supportAncragePrecisions }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.revetInt')">Revet Int</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.RevetInt.' + batiments.revetInt)">{{ batiments.revetInt }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.revetExt')">Revet Ext</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.RevetExt.' + batiments.revetExt)">{{ batiments.revetExt }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.revetExtAutre')">Revet Ext Autre</span>
          </dt>
          <dd>
            <span>{{ batiments.revetExtAutre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.maitreDOuvrage')">Maitre D Ouvrage</span>
          </dt>
          <dd>
            <span>{{ batiments.maitreDOuvrage }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.maitreDOeuvre')">Maitre D Oeuvre</span>
          </dt>
          <dd>
            <span>{{ batiments.maitreDOeuvre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.architecte')">Architecte</span>
          </dt>
          <dd>
            <span>{{ batiments.architecte }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bureauDEtudeStructure')">Bureau D Etude Structure</span>
          </dt>
          <dd>
            <span>{{ batiments.bureauDEtudeStructure }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bureauControl')">Bureau Control</span>
          </dt>
          <dd>
            <span>{{ batiments.bureauControl }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.entrepriseBottes')">Entreprise Bottes</span>
          </dt>
          <dd>
            <span>{{ batiments.entrepriseBottes }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.entrepriseCharpente')">Entreprise Charpente</span>
          </dt>
          <dd>
            <span>{{ batiments.entrepriseCharpente }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.entrepriseEnduits')">Entreprise Enduits</span>
          </dt>
          <dd>
            <span>{{ batiments.entrepriseEnduits }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.descriptionProjet')">Description Projet</span>
          </dt>
          <dd>
            <span>{{ batiments.descriptionProjet }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.difficultees')">Difficultees</span>
          </dt>
          <dd>
            <span>{{ batiments.difficultees }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.astuces')">Astuces</span>
          </dt>
          <dd>
            <span>{{ batiments.astuces }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.divers')">Divers</span>
          </dt>
          <dd>
            <span>{{ batiments.divers }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.contactNom')">Contact Nom</span>
          </dt>
          <dd>
            <span>{{ batiments.contactNom }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.contactMail')">Contact Mail</span>
          </dt>
          <dd>
            <span>{{ batiments.contactMail }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.contactPhone')">Contact Phone</span>
          </dt>
          <dd>
            <span>{{ batiments.contactPhone }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.codePostal')">Code Postal</span>
          </dt>
          <dd>
            <span>{{ batiments.codePostal }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.nonBatimentEtPhotosPublics')">Non Batiment Et Photos Publics</span>
          </dt>
          <dd>
            <span>{{ batiments.nonBatimentEtPhotosPublics }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.dateCreationFiche')">Date Creation Fiche</span>
          </dt>
          <dd>
            <span>{{ batiments.dateCreationFiche }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.dateModificationFiche')">Date Modification Fiche</span>
          </dt>
          <dd>
            <span>{{ batiments.dateModificationFiche }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.creator')">Creator</span>
          </dt>
          <dd>
            {{ batiments.creator ? batiments.creator.email : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="batiments.id"
          :to="{ name: 'BatimentsEdit', params: { batimentsId: batiments.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./batiments-details.component.ts"></script>
