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
            <th scope="row" v-on:click="changeOrder('photoPrincipale')">
              <span v-text="$t('lbstrawmapApp.batiments.photoPrincipale')">Photo Principale</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photoPrincipale'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nom')">
              <span v-text="$t('lbstrawmapApp.batiments.nom')">Nom</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nom'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('techniqueSecondaire')">
              <span v-text="$t('lbstrawmapApp.batiments.techniqueSecondaire')">Technique Secondaire</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'techniqueSecondaire'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('usage')">
              <span v-text="$t('lbstrawmapApp.batiments.usage')">Usage</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'usage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cout')">
              <span v-text="$t('lbstrawmapApp.batiments.cout')">Cout</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cout'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('surface')">
              <span v-text="$t('lbstrawmapApp.batiments.surface')">Surface</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'surface'"></jhi-sort-indicator>
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
            <th scope="row" v-on:click="changeOrder('integBaie')">
              <span v-text="$t('lbstrawmapApp.batiments.integBaie')">Integ Baie</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'integBaie'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('structSuppl')">
              <span v-text="$t('lbstrawmapApp.batiments.structSuppl')">Struct Suppl</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'structSuppl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('noteCalcul')">
              <span v-text="$t('lbstrawmapApp.batiments.noteCalcul')">Note Calcul</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'noteCalcul'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('materiauSb')">
              <span v-text="$t('lbstrawmapApp.batiments.materiauSb')">Materiau Sb</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'materiauSb'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('revetInt')">
              <span v-text="$t('lbstrawmapApp.batiments.revetInt')">Revet Int</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revetInt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('revetExt')">
              <span v-text="$t('lbstrawmapApp.batiments.revetExt')">Revet Ext</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revetExt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('concepteur')">
              <span v-text="$t('lbstrawmapApp.batiments.concepteur')">Concepteur</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'concepteur'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('realisateur')">
              <span v-text="$t('lbstrawmapApp.batiments.realisateur')">Realisateur</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'realisateur'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('lbstrawmapApp.batiments.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
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
