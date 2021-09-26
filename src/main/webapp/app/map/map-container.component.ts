import TileLayer from 'ol/layer/Tile';
import Map from 'ol/Map';
import 'ol/ol.css';
import { fromLonLat } from 'ol/proj';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';
import drawCircle from './draw-circle';

// https://openlayers.org/en/latest/doc/faq.html#why-is-my-map-centered-on-the-gulf-of-guinea-or-africa-the-ocean-null-island-
// https://openlayers.org/en/latest/doc/faq.html#why-is-the-order-of-a-coordinate-lon-lat-and-not-lat-lon-
const franceLonLat = [2.2137, 46.2276];

@Component
export default class MapContainer extends Vue {
  public mounted(): void {
    const aCircle = drawCircle({ long: 2.2137, lat: 46.2276 });
    new Map({
      target: this.$refs['map-root'] as HTMLElement,
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
        // TODO use https://openlayers.org/en/latest/examples/icon.html instead
        aCircle,
      ],

      view: new View({
        zoom: 6.1,
        // TODO use zoomToExtent instead, with bound of France
        center: fromLonLat(franceLonLat),
      }),
    });
  }
}
