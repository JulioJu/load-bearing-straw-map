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
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.latitude')">Latitude</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.longitude')">Longitude</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.nom')">Nom</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')">Technique Secondaire</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.usage')">Usage</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.cout')">Cout</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.surface')">Surface</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.niveaux')">Niveaux</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.travauxNeuf')">Travaux Neuf</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.travauxExtension')">Travaux Extension</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.travauxRenov')">Travaux Renov</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.travauxIte')">Travaux Ite</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.travauxIti')">Travaux Iti</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.constructionDebut')">Construction Debut</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.constructionFin')">Construction Fin</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.bottesTaille')">Bottes Taille</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.bottesDensite')">Bottes Densite</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.bottesCereale')">Bottes Cereale</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.distanceAppro')">Distance Appro</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.autoconstruction')">Autoconstruction</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.participatif')">Participatif</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.integBaie')">Integ Baie</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.structSuppl')">Struct Suppl</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.noteCalcul')">Note Calcul</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.materiauSb')">Materiau Sb</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.revetInt')">Revet Int</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.revetExt')">Revet Ext</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.concepteur')">Concepteur</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.realisateur')">Realisateur</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.description')">Description</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.contactNom')">Contact Nom</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.contactMail')">Contact Mail</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.contactPhone')">Contact Phone</span></th>
            <th scope="row"><span v-text="$t('lbstrawmapApp.batiments.codePostal')">Code Postal</span></th>
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
            <td>{{ batiments.nom }}</td>
            <td>{{ batiments.techniqueSecondaire }}</td>
            <td v-text="$t('lbstrawmapApp.UsageBatiment.' + batiments.usage)">{{ batiments.usage }}</td>
            <td>{{ batiments.cout }}</td>
            <td>{{ batiments.surface }}</td>
            <td>{{ batiments.niveaux }}</td>
            <td>{{ batiments.travauxNeuf }}</td>
            <td>{{ batiments.travauxExtension }}</td>
            <td>{{ batiments.travauxRenov }}</td>
            <td>{{ batiments.travauxIte }}</td>
            <td>{{ batiments.travauxIti }}</td>
            <td>{{ batiments.constructionDebut }}</td>
            <td>{{ batiments.constructionFin }}</td>
            <td v-text="$t('lbstrawmapApp.TaillesBottes.' + batiments.bottesTaille)">{{ batiments.bottesTaille }}</td>
            <td>{{ batiments.bottesDensite }}</td>
            <td v-text="$t('lbstrawmapApp.Cereale.' + batiments.bottesCereale)">{{ batiments.bottesCereale }}</td>
            <td>{{ batiments.distanceAppro }}</td>
            <td v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.autoconstruction)">{{ batiments.autoconstruction }}</td>
            <td v-text="$t('lbstrawmapApp.YesNoPartial.' + batiments.participatif)">{{ batiments.participatif }}</td>
            <td v-text="$t('lbstrawmapApp.IntegBaie.' + batiments.integBaie)">{{ batiments.integBaie }}</td>
            <td>{{ batiments.structSuppl }}</td>
            <td>{{ batiments.noteCalcul }}</td>
            <td v-text="$t('lbstrawmapApp.MateriauSb.' + batiments.materiauSb)">{{ batiments.materiauSb }}</td>
            <td v-text="$t('lbstrawmapApp.RevetInt.' + batiments.revetInt)">{{ batiments.revetInt }}</td>
            <td v-text="$t('lbstrawmapApp.RevetExt.' + batiments.revetExt)">{{ batiments.revetExt }}</td>
            <td>{{ batiments.concepteur }}</td>
            <td>{{ batiments.realisateur }}</td>
            <td>{{ batiments.description }}</td>
            <td>{{ batiments.contactNom }}</td>
            <td>{{ batiments.contactMail }}</td>
            <td>{{ batiments.contactPhone }}</td>
            <td>{{ batiments.codePostal }}</td>
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
  </div>
</template>

<script lang="ts" src="./batiments.component.ts"></script>
