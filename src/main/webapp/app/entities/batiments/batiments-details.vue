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
            <span v-text="$t('lbstrawmapApp.batiments.nom')">Nom</span>
          </dt>
          <dd>
            <span>{{ batiments.nom }}</span>
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
            <span v-text="$t('lbstrawmapApp.batiments.surface')">Surface</span>
          </dt>
          <dd>
            <span>{{ batiments.surface }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.cout')">Cout</span>
          </dt>
          <dd>
            <span>{{ batiments.cout }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesTaille')">Bottes Taille</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.TaillesBottes.' + batiments.bottesTaille)">{{ batiments.bottesTaille }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.autoconstruction')">Autoconstruction</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.autoconstruction)">{{ batiments.autoconstruction }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.concepteur')">Concepteur</span>
          </dt>
          <dd>
            <span>{{ batiments.concepteur }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.realisateur')">Realisateur</span>
          </dt>
          <dd>
            <span>{{ batiments.realisateur }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.participatif')">Participatif</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.participatif)">{{ batiments.participatif }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.usage')">Usage</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.UsageBatiment.' + batiments.usage)">{{ batiments.usage }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.noteCalcul')">Note Calcul</span>
          </dt>
          <dd>
            <span>{{ batiments.noteCalcul }}</span>
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
            <span v-text="$t('lbstrawmapApp.batiments.niveaux')">Niveaux</span>
          </dt>
          <dd>
            <span>{{ batiments.niveaux }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesDensite')">Bottes Densite</span>
          </dt>
          <dd>
            <span>{{ batiments.bottesDensite }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.distanceAppro')">Distance Appro</span>
          </dt>
          <dd>
            <span>{{ batiments.distanceAppro }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.bottesCereale')">Bottes Cereale</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.Cereale.' + batiments.bottesCereale)">{{ batiments.bottesCereale }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.structSuppl')">Struct Suppl</span>
          </dt>
          <dd>
            <span>{{ batiments.structSuppl }}</span>
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
            <span v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')">Technique Secondaire</span>
          </dt>
          <dd>
            <span>{{ batiments.techniqueSecondaire }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.codePostal')">Code Postal</span>
          </dt>
          <dd>
            <span>{{ batiments.codePostal }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.integBaie')">Integ Baie</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.IntegBaie.' + batiments.integBaie)">{{ batiments.integBaie }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.materiauSb')">Materiau Sb</span>
          </dt>
          <dd>
            <span v-text="$t('lbstrawmapApp.MateriauSb.' + batiments.materiauSb)">{{ batiments.materiauSb }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.batiments.description')">Description</span>
          </dt>
          <dd>
            <span>{{ batiments.description }}</span>
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
