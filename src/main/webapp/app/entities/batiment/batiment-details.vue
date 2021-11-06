<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="batiment">
        <h2 class="jh-entity-heading" data-cy="batimentDetailsHeading">
          <span v-text="$t('lbstrawmapApp.batiment.detail.title')">Batiment</span> {{ batiment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.latitude')">Latitude</span>
          </dt>
          <dd>
            <span>{{ batiment.latitude }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.longitude')">Longitude</span>
          </dt>
          <dd>
            <span>{{ batiment.longitude }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.nomBatiment')">Nom Batiment</span>
          </dt>
          <dd>
            <span>{{ batiment.nomBatiment }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photoPrincipale')">Photo Principale</span>
          </dt>
          <dd>
            <div v-if="batiment.photoPrincipale">
              <a v-on:click="openFile(batiment.photoPrincipaleContentType, batiment.photoPrincipale)">
                <img
                  v-bind:src="'data:' + batiment.photoPrincipaleContentType + ';base64,' + batiment.photoPrincipale"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photoPrincipaleContentType }}, {{ byteSize(batiment.photoPrincipale) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photoPrincipaleLegende')">Photo Principale Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photoPrincipaleLegende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photoPrincipaleDescription')">Photo Principale Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photoPrincipaleDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo1')">Photo 1</span>
          </dt>
          <dd>
            <div v-if="batiment.photo1">
              <a v-on:click="openFile(batiment.photo1ContentType, batiment.photo1)">
                <img
                  v-bind:src="'data:' + batiment.photo1ContentType + ';base64,' + batiment.photo1"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photo1ContentType }}, {{ byteSize(batiment.photo1) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo1Legende')">Photo 1 Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photo1Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo1Description')">Photo 1 Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photo1Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo2')">Photo 2</span>
          </dt>
          <dd>
            <div v-if="batiment.photo2">
              <a v-on:click="openFile(batiment.photo2ContentType, batiment.photo2)">
                <img
                  v-bind:src="'data:' + batiment.photo2ContentType + ';base64,' + batiment.photo2"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photo2ContentType }}, {{ byteSize(batiment.photo2) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo2Legende')">Photo 2 Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photo2Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo2Description')">Photo 2 Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photo2Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo3')">Photo 3</span>
          </dt>
          <dd>
            <div v-if="batiment.photo3">
              <a v-on:click="openFile(batiment.photo3ContentType, batiment.photo3)">
                <img
                  v-bind:src="'data:' + batiment.photo3ContentType + ';base64,' + batiment.photo3"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photo3ContentType }}, {{ byteSize(batiment.photo3) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo3Legende')">Photo 3 Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photo3Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo3Description')">Photo 3 Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photo3Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo4')">Photo 4</span>
          </dt>
          <dd>
            <div v-if="batiment.photo4">
              <a v-on:click="openFile(batiment.photo4ContentType, batiment.photo4)">
                <img
                  v-bind:src="'data:' + batiment.photo4ContentType + ';base64,' + batiment.photo4"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photo4ContentType }}, {{ byteSize(batiment.photo4) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo4Legende')">Photo 4 Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photo4Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo4Description')">Photo 4 Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photo4Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo5')">Photo 5</span>
          </dt>
          <dd>
            <div v-if="batiment.photo5">
              <a v-on:click="openFile(batiment.photo5ContentType, batiment.photo5)">
                <img
                  v-bind:src="'data:' + batiment.photo5ContentType + ';base64,' + batiment.photo5"
                  style="max-width: 100%"
                  alt="batiment image"
                />
              </a>
              {{ batiment.photo5ContentType }}, {{ byteSize(batiment.photo5) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo5Legende')">Photo 5 Legende</span>
          </dt>
          <dd>
            <span>{{ batiment.photo5Legende }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.photo5Description')">Photo 5 Description</span>
          </dt>
          <dd>
            <span>{{ batiment.photo5Description }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.techniqueSecondaire')">Technique Secondaire</span>
          </dt>
          <dd>
            <span>{{ batiment.techniqueSecondaire }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.usageBatiment')">Usage Batiment</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.UsageBatiment.' + batiment.usageBatiment)">{{ batiment.usageBatiment }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.cout')">Cout</span>
          </dt>
          <dd>
            <span>{{ batiment.cout }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.surfacePlancher')">Surface Plancher</span>
          </dt>
          <dd>
            <span>{{ batiment.surfacePlancher }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.niveaux')">Niveaux</span>
          </dt>
          <dd>
            <span>{{ batiment.niveaux }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.travauxNeuf')">Travaux Neuf</span>
          </dt>
          <dd>
            <span>{{ batiment.travauxNeuf }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.travauxExtension')">Travaux Extension</span>
          </dt>
          <dd>
            <span>{{ batiment.travauxExtension }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.travauxRenov')">Travaux Renov</span>
          </dt>
          <dd>
            <span>{{ batiment.travauxRenov }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.travauxIte')">Travaux Ite</span>
          </dt>
          <dd>
            <span>{{ batiment.travauxIte }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.travauxIti')">Travaux Iti</span>
          </dt>
          <dd>
            <span>{{ batiment.travauxIti }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.constructionDebut')">Construction Debut</span>
          </dt>
          <dd>
            <span>{{ batiment.constructionDebut }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.constructionFin')">Construction Fin</span>
          </dt>
          <dd>
            <span>{{ batiment.constructionFin }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.bottesTaille')">Bottes Taille</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.TaillesBottes.' + batiment.bottesTaille)">{{ batiment.bottesTaille }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.botteTailleAutre')">Botte Taille Autre</span>
          </dt>
          <dd>
            <span>{{ batiment.botteTailleAutre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.bottesDensite')">Bottes Densite</span>
          </dt>
          <dd>
            <span>{{ batiment.bottesDensite }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.bottesCereale')">Bottes Cereale</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.Cereale.' + batiment.bottesCereale)">{{ batiment.bottesCereale }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.distanceAppro')">Distance Appro</span>
          </dt>
          <dd>
            <span>{{ batiment.distanceAppro }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.autoconstruction')">Autoconstruction</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiment.autoconstruction)">{{ batiment.autoconstruction }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.participatif')">Participatif</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiment.participatif)">{{ batiment.participatif }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.structSuppl')">Struct Suppl</span>
          </dt>
          <dd>
            <span>{{ batiment.structSuppl }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.structSupplNature')">Struct Suppl Nature</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.StructureSupplementaire.' + batiment.structSupplNature)">{{ batiment.structSupplNature }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.noteCalcul')">Note Calcul</span>
          </dt>
          <dd>
            <span>{{ batiment.noteCalcul }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.nbrRangDeBottes')">Nbr Rang De Bottes</span>
          </dt>
          <dd>
            <span>{{ batiment.nbrRangDeBottes }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.longMaxSansMurRefend')">Long Max Sans Mur Refend</span>
          </dt>
          <dd>
            <span>{{ batiment.longMaxSansMurRefend }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.integBaie')">Integ Baie</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.IntegBaie.' + batiment.integBaie)">{{ batiment.integBaie }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.supportAncrage')">Support Ancrage</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.SupportAncrage.' + batiment.supportAncrage)">{{ batiment.supportAncrage }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.supportAncragePrecisions')">Support Ancrage Precisions</span>
          </dt>
          <dd>
            <span>{{ batiment.supportAncragePrecisions }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.revetInt')">Revet Int</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.RevetInt.' + batiment.revetInt)">{{ batiment.revetInt }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.revetExt')">Revet Ext</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.RevetExt.' + batiment.revetExt)">{{ batiment.revetExt }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.revetExtAutre')">Revet Ext Autre</span>
          </dt>
          <dd>
            <span>{{ batiment.revetExtAutre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.maitreDOuvrage')">Maitre D Ouvrage</span>
          </dt>
          <dd>
            <span>{{ batiment.maitreDOuvrage }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.maitreDOeuvre')">Maitre D Oeuvre</span>
          </dt>
          <dd>
            <span>{{ batiment.maitreDOeuvre }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.architecte')">Architecte</span>
          </dt>
          <dd>
            <span>{{ batiment.architecte }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.bureauDEtudeStructure')">Bureau D Etude Structure</span>
          </dt>
          <dd>
            <span>{{ batiment.bureauDEtudeStructure }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.bureauControl')">Bureau Control</span>
          </dt>
          <dd>
            <span>{{ batiment.bureauControl }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.entrepriseBottes')">Entreprise Bottes</span>
          </dt>
          <dd>
            <span>{{ batiment.entrepriseBottes }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.entrepriseCharpente')">Entreprise Charpente</span>
          </dt>
          <dd>
            <span>{{ batiment.entrepriseCharpente }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.entrepriseEnduits')">Entreprise Enduits</span>
          </dt>
          <dd>
            <span>{{ batiment.entrepriseEnduits }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.descriptionProjet')">Description Projet</span>
          </dt>
          <dd>
            <span>{{ batiment.descriptionProjet }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.difficultees')">Difficultees</span>
          </dt>
          <dd>
            <span>{{ batiment.difficultees }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.astuces')">Astuces</span>
          </dt>
          <dd>
            <span>{{ batiment.astuces }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.divers')">Divers</span>
          </dt>
          <dd>
            <span>{{ batiment.divers }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.contactNom')">Contact Nom</span>
          </dt>
          <dd>
            <span>{{ batiment.contactNom }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.contactMail')">Contact Mail</span>
          </dt>
          <dd>
            <span>{{ batiment.contactMail }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.contactPhone')">Contact Phone</span>
          </dt>
          <dd>
            <span>{{ batiment.contactPhone }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.codePostal')">Code Postal</span>
          </dt>
          <dd>
            <span>{{ batiment.codePostal }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.nonBatimentEtPhotosPublics')">Non Batiment Et Photos Publics</span>
          </dt>
          <dd>
            <span>{{ batiment.nonBatimentEtPhotosPublics }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.dateCreationFiche')">Date Creation Fiche</span>
          </dt>
          <dd>
            <span>{{ batiment.dateCreationFiche }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.dateModificationFiche')">Date Modification Fiche</span>
          </dt>
          <dd>
            <span>{{ batiment.dateModificationFiche }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiment.creator')">Creator</span>
          </dt>
          <dd>
            {{ batiment.creator ? batiment.creator.login : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="batiment.id" :to="{ name: 'BatimentEdit', params: { batimentId: batiment.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./batiment-details.component.ts"></script>
