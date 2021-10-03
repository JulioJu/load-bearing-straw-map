import TileLayer from 'ol/layer/Tile';
import Map from 'ol/Map';
import 'ol/ol.css';
import { fromLonLat } from 'ol/proj';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';
import drawIcon from './draw-icon';
import drawPopup from './draw-popup';

// https://openlayers.org/en/latest/doc/faq.html#why-is-my-map-centered-on-the-gulf-of-guinea-or-africa-the-ocean-null-island-
// https://openlayers.org/en/latest/doc/faq.html#why-is-the-order-of-a-coordinate-lon-lat-and-not-lat-lon-
const franceLonLat = [2.2137, 46.2276];

@Component
export default class MapContainer extends Vue {
  public mounted(): void {
    const map = new Map({
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
    const icon = drawIcon({ long: 2.2137, lat: 46.2276 });
    map.addLayer(icon);
    drawPopup({
      map,
      popup: this.$refs['popup'] as HTMLDivElement,
      popupCloser: this.$refs['popup-closer'] as HTMLDivElement,
      popupContent: this.$refs['popup-content'] as HTMLDivElement,
    });
  }
}
