import 'ol/ol.css';

import BatimentsService from '@/entities/batiments/batiments.service';
import { IBatiments } from '@/shared/model/batiments.model';
import TileLayer from 'ol/layer/Tile';
import Map from 'ol/Map';
import { fromLonLat } from 'ol/proj';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';

import drawIcon from './draw-icon';
import drawPopup from './draw-popup';
import { AxiosResponse } from 'axios';
import eventRetrieveCoordAndNavigate from './event-retrieve-coord-and-navigate';

// https://openlayers.org/en/latest/doc/faq.html#why-is-my-map-centered-on-the-gulf-of-guinea-or-africa-the-ocean-null-island-
// https://openlayers.org/en/latest/doc/faq.html#why-is-the-order-of-a-coordinate-lon-lat-and-not-lat-lon-
const franceLonLat = [2.2137, 46.2276];

@Component
export default class MapContainer extends Vue {
  @Inject('batimentsService')
  private batimentsService: () => BatimentsService;
  private map: Map;
  public isEditMode = false;

  public async retrieveAllBatimentss(): Promise<IBatiments[]> {
    const result: AxiosResponse<IBatiments[]> = await this.batimentsService().retrieveLazy();
    return result.data;
  }

  public async mounted(): Promise<void> {
    this.map = new Map({
      target: this.$refs['map-root'] as HTMLDivElement,
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: new View({
        zoom: 1,
        center: fromLonLat(franceLonLat),
        extent: [-1944469.271982851, 4954231.814876032, 2437325.1855209903, 6679563.3825181695],
      }),
    });

    const batiments = await this.retrieveAllBatimentss();
    batiments.forEach(aBatiment => {
      const icon = drawIcon({
        id: aBatiment.id,
        name: aBatiment.nomBatiment,
        lat: aBatiment.latitude,
        long: aBatiment.longitude,
        usageBatiment: aBatiment.usageBatiment ? this.$t(`lbstrawmapApp.UsageBatiment.${aBatiment.usageBatiment}`) : undefined,
        surface: aBatiment.surfacePlancher ? `${aBatiment.surfacePlancher} m²` : undefined,
      });
      this.map.addLayer(icon);
    });
    drawPopup({
      batimentsService: this.batimentsService,
      map: this.map,
      popup: this.$refs['popup'] as HTMLDivElement,
      popupCloser: this.$refs['popup-closer'] as HTMLDivElement,
      popupContent: this.$refs['popup-content'] as HTMLDivElement,
      router: this.$router,
    });
  }

  public toggleEditionMode(): void {
    if (!this.isEditMode) {
      eventRetrieveCoordAndNavigate.register(this.map, this.$router, this.$refs['map-root'] as HTMLDivElement);
    } else {
      eventRetrieveCoordAndNavigate.unregister(this.map, this.$refs['map-root'] as HTMLDivElement);
    }
    this.isEditMode = !this.isEditMode;
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }
}
