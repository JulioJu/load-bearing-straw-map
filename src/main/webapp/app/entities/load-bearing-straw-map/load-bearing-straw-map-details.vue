<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="loadBearingStrawMap">
        <h2 class="jh-entity-heading" data-cy="loadBearingStrawMapDetailsHeading">
          <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.detail.title')">LoadBearingStrawMap</span> {{ loadBearingStrawMap.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.name')">Name</span>
          </dt>
          <dd>
            <span>{{ loadBearingStrawMap.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.latitude')">Latitude</span>
          </dt>
          <dd>
            <span>{{ loadBearingStrawMap.latitude }}</span>
          </dd>
          <dt>
            <span v-text="$t('lbstrawmapApp.loadBearingStrawMap.longitude')">Longitude</span>
          </dt>
          <dd>
            <span>{{ loadBearingStrawMap.longitude }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="loadBearingStrawMap.id"
          :to="{ name: 'LoadBearingStrawMapEdit', params: { loadBearingStrawMapId: loadBearingStrawMap.id } }"
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

<script lang="ts" src="./load-bearing-straw-map-details.component.ts"></script>
