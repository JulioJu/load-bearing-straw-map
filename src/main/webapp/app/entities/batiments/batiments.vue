<template>
  <div>
    <h2 id="page-heading" data-cy="BatimentsHeading">
      <span v-text="$t('lbstrawmapApp.batiments.home.title')" id="batiments-heading">Batiments</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('lbstrawmapApp.batiments.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'BatimentsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-batiments"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('lbstrawmapApp.batiments.home.createLabel')"> Create a new Batiments </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && batiments && batiments.length === 0">
      <span v-text="$t('lbstrawmapApp.batiments.home.notFound')">No batiments found</span>
    </div>
    <div class="table-responsive" v-if="batiments && batiments.length > 0">
      <table class="table table-striped" aria-describedby="batiments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('latitude')">
              <span v-text="$t('lbstrawmapApp.batiments.latitude')">Latitude</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'latitude'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('longitude')">
              <span v-text="$t('lbstrawmapApp.batiments.longitude')">Longitude</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'longitude'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nomBatiment')">
              <span v-text="$t('lbstrawmapApp.batiments.nomBatiment')">Nom Batiment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nomBatiment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photoPrincipale')">
              <span v-text="$t('lbstrawmapApp.batiments.photoPrincipale')">Photo Principale</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photoPrincipale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photoPrincipaleLegende')">
              <span v-text="$t('lbstrawmapApp.batiments.photoPrincipaleLegende')">Photo Principale Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photoPrincipaleLegende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photoPrincipaleDescription')">
              <span v-text="$t('lbstrawmapApp.batiments.photoPrincipaleDescription')">Photo Principale Description</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'photoPrincipaleDescription'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo1')">
              <span v-text="$t('lbstrawmapApp.batiments.photo1')">Photo 1</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo1'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo1Legende')">
              <span v-text="$t('lbstrawmapApp.batiments.photo1Legende')">Photo 1 Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo1Legende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo1Description')">
              <span v-text="$t('lbstrawmapApp.batiments.photo1Description')">Photo 1 Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo1Description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo2')">
              <span v-text="$t('lbstrawmapApp.batiments.photo2')">Photo 2</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo2'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo2Legende')">
              <span v-text="$t('lbstrawmapApp.batiments.photo2Legende')">Photo 2 Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo2Legende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo2Description')">
              <span v-text="$t('lbstrawmapApp.batiments.photo2Description')">Photo 2 Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo2Description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo3')">
              <span v-text="$t('lbstrawmapApp.batiments.photo3')">Photo 3</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo3'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo3Legende')">
              <span v-text="$t('lbstrawmapApp.batiments.photo3Legende')">Photo 3 Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo3Legende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo3Description')">
              <span v-text="$t('lbstrawmapApp.batiments.photo3Description')">Photo 3 Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo3Description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo4')">
              <span v-text="$t('lbstrawmapApp.batiments.photo4')">Photo 4</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo4'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo4Legende')">
              <span v-text="$t('lbstrawmapApp.batiments.photo4Legende')">Photo 4 Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo4Legende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo4Description')">
              <span v-text="$t('lbstrawmapApp.batiments.photo4Description')">Photo 4 Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo4Description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo5')">
              <span v-text="$t('lbstrawmapApp.batiments.photo5')">Photo 5</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo5'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo5Legende')">
              <span v-text="$t('lbstrawmapApp.batiments.photo5Legende')">Photo 5 Legende</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo5Legende'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('photo5Description')">
              <span v-text="$t('lbstrawmapApp.batiments.photo5Description')">Photo 5 Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo5Description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techniqueSecondaire')">
              <span v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')">Technique Secondaire</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techniqueSecondaire'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('usageBatiment')">
              <span v-text="$t('lbstrawmapApp.batiments.usageBatiment')">Usage Batiment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'usageBatiment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cout')">
              <span v-text="$t('lbstrawmapApp.batiments.cout')">Cout</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cout'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('surfacePlancher')">
              <span v-text="$t('lbstrawmapApp.batiments.surfacePlancher')">Surface Plancher</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'surfacePlancher'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('niveaux')">
              <span v-text="$t('lbstrawmapApp.batiments.niveaux')">Niveaux</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'niveaux'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('travauxNeuf')">
              <span v-text="$t('lbstrawmapApp.batiments.travauxNeuf')">Travaux Neuf</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'travauxNeuf'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('travauxExtension')">
              <span v-text="$t('lbstrawmapApp.batiments.travauxExtension')">Travaux Extension</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'travauxExtension'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('travauxRenov')">
              <span v-text="$t('lbstrawmapApp.batiments.travauxRenov')">Travaux Renov</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'travauxRenov'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('travauxIte')">
              <span v-text="$t('lbstrawmapApp.batiments.travauxIte')">Travaux Ite</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'travauxIte'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('travauxIti')">
              <span v-text="$t('lbstrawmapApp.batiments.travauxIti')">Travaux Iti</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'travauxIti'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('constructionDebut')">
              <span v-text="$t('lbstrawmapApp.batiments.constructionDebut')">Construction Debut</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'constructionDebut'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('constructionFin')">
              <span v-text="$t('lbstrawmapApp.batiments.constructionFin')">Construction Fin</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'constructionFin'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bottesTaille')">
              <span v-text="$t('lbstrawmapApp.batiments.bottesTaille')">Bottes Taille</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bottesTaille'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('botteTailleAutre')">
              <span v-text="$t('lbstrawmapApp.batiments.botteTailleAutre')">Botte Taille Autre</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'botteTailleAutre'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bottesDensite')">
              <span v-text="$t('lbstrawmapApp.batiments.bottesDensite')">Bottes Densite</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bottesDensite'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bottesCereale')">
              <span v-text="$t('lbstrawmapApp.batiments.bottesCereale')">Bottes Cereale</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bottesCereale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distanceAppro')">
              <span v-text="$t('lbstrawmapApp.batiments.distanceAppro')">Distance Appro</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distanceAppro'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('autoconstruction')">
              <span v-text="$t('lbstrawmapApp.batiments.autoconstruction')">Autoconstruction</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'autoconstruction'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('participatif')">
              <span v-text="$t('lbstrawmapApp.batiments.participatif')">Participatif</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'participatif'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('structSuppl')">
              <span v-text="$t('lbstrawmapApp.batiments.structSuppl')">Struct Suppl</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'structSuppl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('structSupplNature')">
              <span v-text="$t('lbstrawmapApp.batiments.structSupplNature')">Struct Suppl Nature</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'structSupplNature'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('noteCalcul')">
              <span v-text="$t('lbstrawmapApp.batiments.noteCalcul')">Note Calcul</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'noteCalcul'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nbrRangDeBottes')">
              <span v-text="$t('lbstrawmapApp.batiments.nbrRangDeBottes')">Nbr Rang De Bottes</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nbrRangDeBottes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('longMaxSansMurRefend')">
              <span v-text="$t('lbstrawmapApp.batiments.longMaxSansMurRefend')">Long Max Sans Mur Refend</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'longMaxSansMurRefend'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('integBaie')">
              <span v-text="$t('lbstrawmapApp.batiments.integBaie')">Integ Baie</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'integBaie'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('supportAncrage')">
              <span v-text="$t('lbstrawmapApp.batiments.supportAncrage')">Support Ancrage</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'supportAncrage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('supportAncragePrecisions')">
              <span v-text="$t('lbstrawmapApp.batiments.supportAncragePrecisions')">Support Ancrage Precisions</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'supportAncragePrecisions'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('revetInt')">
              <span v-text="$t('lbstrawmapApp.batiments.revetInt')">Revet Int</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revetInt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('revetExt')">
              <span v-text="$t('lbstrawmapApp.batiments.revetExt')">Revet Ext</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revetExt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('revetExtAutre')">
              <span v-text="$t('lbstrawmapApp.batiments.revetExtAutre')">Revet Ext Autre</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revetExtAutre'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('maitreDOuvrage')">
              <span v-text="$t('lbstrawmapApp.batiments.maitreDOuvrage')">Maitre D Ouvrage</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'maitreDOuvrage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('maitreDOeuvre')">
              <span v-text="$t('lbstrawmapApp.batiments.maitreDOeuvre')">Maitre D Oeuvre</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'maitreDOeuvre'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('architecte')">
              <span v-text="$t('lbstrawmapApp.batiments.architecte')">Architecte</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'architecte'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bureauDEtudeStructure')">
              <span v-text="$t('lbstrawmapApp.batiments.bureauDEtudeStructure')">Bureau D Etude Structure</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bureauDEtudeStructure'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('bureauControl')">
              <span v-text="$t('lbstrawmapApp.batiments.bureauControl')">Bureau Control</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bureauControl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('entrepriseBottes')">
              <span v-text="$t('lbstrawmapApp.batiments.entrepriseBottes')">Entreprise Bottes</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'entrepriseBottes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('entrepriseCharpente')">
              <span v-text="$t('lbstrawmapApp.batiments.entrepriseCharpente')">Entreprise Charpente</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'entrepriseCharpente'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('entrepriseEnduits')">
              <span v-text="$t('lbstrawmapApp.batiments.entrepriseEnduits')">Entreprise Enduits</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'entrepriseEnduits'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('descriptionProjet')">
              <span v-text="$t('lbstrawmapApp.batiments.descriptionProjet')">Description Projet</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'descriptionProjet'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('difficultees')">
              <span v-text="$t('lbstrawmapApp.batiments.difficultees')">Difficultees</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'difficultees'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('astuces')">
              <span v-text="$t('lbstrawmapApp.batiments.astuces')">Astuces</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'astuces'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('divers')">
              <span v-text="$t('lbstrawmapApp.batiments.divers')">Divers</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'divers'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('contactNom')">
              <span v-text="$t('lbstrawmapApp.batiments.contactNom')">Contact Nom</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'contactNom'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('contactMail')">
              <span v-text="$t('lbstrawmapApp.batiments.contactMail')">Contact Mail</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'contactMail'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('contactPhone')">
              <span v-text="$t('lbstrawmapApp.batiments.contactPhone')">Contact Phone</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'contactPhone'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('codePostal')">
              <span v-text="$t('lbstrawmapApp.batiments.codePostal')">Code Postal</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'codePostal'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nonBatimentEtPhotosPublics')">
              <span v-text="$t('lbstrawmapApp.batiments.nonBatimentEtPhotosPublics')">Non Batiment Et Photos Publics</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'nonBatimentEtPhotosPublics'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dateCreationFiche')">
              <span v-text="$t('lbstrawmapApp.batiments.dateCreationFiche')">Date Creation Fiche</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateCreationFiche'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dateModificationFiche')">
              <span v-text="$t('lbstrawmapApp.batiments.dateModificationFiche')">Date Modification Fiche</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateModificationFiche'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('creator.email')">
              <span v-text="$t('lbstrawmapApp.batiments.creator')">Creator</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'creator.email'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="batiments in batiments" :key="batiments.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BatimentsView', params: { batimentsId: batiments.id } }">{{ batiments.id }}</router-link>
            </td>
            <td>{{ batiments.latitude }}</td>
            <td>{{ batiments.longitude }}</td>
            <td>{{ batiments.nomBatiment }}</td>
            <td>
              <a v-if="batiments.photoPrincipale" v-on:click="openFile(batiments.photoPrincipaleContentType, batiments.photoPrincipale)">
                <img
                  v-bind:src="'data:' + batiments.photoPrincipaleContentType + ';base64,' + batiments.photoPrincipale"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photoPrincipale"
                >{{ batiments.photoPrincipaleContentType }}, {{ byteSize(batiments.photoPrincipale) }}</span
              >
            </td>
            <td>{{ batiments.photoPrincipaleLegende }}</td>
            <td>{{ batiments.photoPrincipaleDescription }}</td>
            <td>
              <a v-if="batiments.photo1" v-on:click="openFile(batiments.photo1ContentType, batiments.photo1)">
                <img
                  v-bind:src="'data:' + batiments.photo1ContentType + ';base64,' + batiments.photo1"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photo1">{{ batiments.photo1ContentType }}, {{ byteSize(batiments.photo1) }}</span>
            </td>
            <td>{{ batiments.photo1Legende }}</td>
            <td>{{ batiments.photo1Description }}</td>
            <td>
              <a v-if="batiments.photo2" v-on:click="openFile(batiments.photo2ContentType, batiments.photo2)">
                <img
                  v-bind:src="'data:' + batiments.photo2ContentType + ';base64,' + batiments.photo2"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photo2">{{ batiments.photo2ContentType }}, {{ byteSize(batiments.photo2) }}</span>
            </td>
            <td>{{ batiments.photo2Legende }}</td>
            <td>{{ batiments.photo2Description }}</td>
            <td>
              <a v-if="batiments.photo3" v-on:click="openFile(batiments.photo3ContentType, batiments.photo3)">
                <img
                  v-bind:src="'data:' + batiments.photo3ContentType + ';base64,' + batiments.photo3"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photo3">{{ batiments.photo3ContentType }}, {{ byteSize(batiments.photo3) }}</span>
            </td>
            <td>{{ batiments.photo3Legende }}</td>
            <td>{{ batiments.photo3Description }}</td>
            <td>
              <a v-if="batiments.photo4" v-on:click="openFile(batiments.photo4ContentType, batiments.photo4)">
                <img
                  v-bind:src="'data:' + batiments.photo4ContentType + ';base64,' + batiments.photo4"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photo4">{{ batiments.photo4ContentType }}, {{ byteSize(batiments.photo4) }}</span>
            </td>
            <td>{{ batiments.photo4Legende }}</td>
            <td>{{ batiments.photo4Description }}</td>
            <td>
              <a v-if="batiments.photo5" v-on:click="openFile(batiments.photo5ContentType, batiments.photo5)">
                <img
                  v-bind:src="'data:' + batiments.photo5ContentType + ';base64,' + batiments.photo5"
                  style="max-height: 30px"
                  alt="batiments image"
                />
              </a>
              <span v-if="batiments.photo5">{{ batiments.photo5ContentType }}, {{ byteSize(batiments.photo5) }}</span>
            </td>
            <td>{{ batiments.photo5Legende }}</td>
            <td>{{ batiments.photo5Description }}</td>
            <td>{{ batiments.techniqueSecondaire }}</td>
            <td v-text="$t('lbstrawmapApp.UsageBatiment.' + batiments.usageBatiment)">{{ batiments.usageBatiment }}</td>
            <td>{{ batiments.cout }}</td>
            <td>{{ batiments.surfacePlancher }}</td>
            <td>{{ batiments.niveaux }}</td>
            <td>{{ batiments.travauxNeuf }}</td>
            <td>{{ batiments.travauxExtension }}</td>
            <td>{{ batiments.travauxRenov }}</td>
            <td>{{ batiments.travauxIte }}</td>
            <td>{{ batiments.travauxIti }}</td>
            <td>{{ batiments.constructionDebut }}</td>
            <td>{{ batiments.constructionFin }}</td>
            <td v-text="$t('lbstrawmapApp.TaillesBottes.' + batiments.bottesTaille)">{{ batiments.bottesTaille }}</td>
            <td>{{ batiments.botteTailleAutre }}</td>
            <td>{{ batiments.bottesDensite }}</td>
            <td v-text="$t('lbstrawmapApp.Cereale.' + batiments.bottesCereale)">{{ batiments.bottesCereale }}</td>
            <td>{{ batiments.distanceAppro }}</td>
            <td v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.autoconstruction)">{{ batiments.autoconstruction }}</td>
            <td v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.participatif)">{{ batiments.participatif }}</td>
            <td>{{ batiments.structSuppl }}</td>
            <td v-text="$t('lbstrawmapApp.StructureSupplementaire.' + batiments.structSupplNature)">{{ batiments.structSupplNature }}</td>
            <td>{{ batiments.noteCalcul }}</td>
            <td>{{ batiments.nbrRangDeBottes }}</td>
            <td>{{ batiments.longMaxSansMurRefend }}</td>
            <td v-text="$t('lbstrawmapApp.IntegBaie.' + batiments.integBaie)">{{ batiments.integBaie }}</td>
            <td v-text="$t('lbstrawmapApp.SupportAncrage.' + batiments.supportAncrage)">{{ batiments.supportAncrage }}</td>
            <td>{{ batiments.supportAncragePrecisions }}</td>
            <td v-text="$t('lbstrawmapApp.RevetInt.' + batiments.revetInt)">{{ batiments.revetInt }}</td>
            <td v-text="$t('lbstrawmapApp.RevetExt.' + batiments.revetExt)">{{ batiments.revetExt }}</td>
            <td>{{ batiments.revetExtAutre }}</td>
            <td>{{ batiments.maitreDOuvrage }}</td>
            <td>{{ batiments.maitreDOeuvre }}</td>
            <td>{{ batiments.architecte }}</td>
            <td>{{ batiments.bureauDEtudeStructure }}</td>
            <td>{{ batiments.bureauControl }}</td>
            <td>{{ batiments.entrepriseBottes }}</td>
            <td>{{ batiments.entrepriseCharpente }}</td>
            <td>{{ batiments.entrepriseEnduits }}</td>
            <td>{{ batiments.descriptionProjet }}</td>
            <td>{{ batiments.difficultees }}</td>
            <td>{{ batiments.astuces }}</td>
            <td>{{ batiments.divers }}</td>
            <td>{{ batiments.contactNom }}</td>
            <td>{{ batiments.contactMail }}</td>
            <td>{{ batiments.contactPhone }}</td>
            <td>{{ batiments.codePostal }}</td>
            <td>{{ batiments.nonBatimentEtPhotosPublics }}</td>
            <td>{{ batiments.dateCreationFiche }}</td>
            <td>{{ batiments.dateModificationFiche }}</td>
            <td>
              {{ batiments.creator ? batiments.creator.email : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BatimentsView', params: { batimentsId: batiments.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BatimentsEdit', params: { batimentsId: batiments.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(batiments)"
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
        ><span id="lbstrawmapApp.batiments.delete.question" data-cy="batimentsDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-batiments-heading" v-text="$t('lbstrawmapApp.batiments.delete.question', { id: removeId })">
          Are you sure you want to delete this Batiments?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-batiments"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeBatiments()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="batiments && batiments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./batiments.component.ts"></script>
