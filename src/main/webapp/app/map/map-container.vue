<!-- Inspired from https://openlayers.org/en/latest/examples/popup.html -->
<template>
  <section>
    <template v-if="authenticated">
      <template v-if="isEditMode">
        <div class="text-info">Double cliquer sur la carte pour ajouter un bâti</div>
        <div class="text-danger mb-3" v-text="$t('cartoPaillePorteuseApp.batiment.localisationRecommendation')"></div>
      </template>
      <button @click="toggleEditionMode" class="btn btn-primary jh-create-entity mb-3">
        <template v-if="!isEditMode">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span> Basculer en mode ajout d'un bâti </span>
        </template>
        <template v-else>
          <span> Basculer en mode normal </span>
        </template>
      </button>
    </template>
    <div class="map-container" ref="map-root"></div>
    <div ref="popup" class="ol-popup">
      <a ref="popup-closer" class="ol-popup-closer"></a>
      <div ref="popup-content"></div>
    </div>
  </section>
</template>

<script lang="ts" src="./map-container.component.ts"></script>

<style scoped="true" lang="scss">
.map-container {
  width: 100%;
  height: 80vh;
}

.map-container--add {
  cursor: pointer;
}

/** Inspired from https://openlayers.org/en/latest/examples/popup.html */
.ol-popup {
  position: absolute;
  background-color: white;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #cccccc;
  bottom: 12px;
  left: -50px;
  min-width: 280px;
}
.ol-popup:after,
.ol-popup:before {
  top: 100%;
  border: solid transparent;
  content: ' ';
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}
.ol-popup:after {
  border-top-color: white;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}
.ol-popup:before {
  border-top-color: #cccccc;
  border-width: 11px;
  left: 48px;
  margin-left: -11px;
}
.ol-popup-closer {
  text-decoration: none;
  position: absolute;
  top: 2px;
  right: 8px;
}
.ol-popup-closer:after {
  content: '✖';
}
</style>
