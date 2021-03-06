import { Feature } from 'ol';
import Point from 'ol/geom/Point';
import VectorLayer from 'ol/layer/Vector';
import 'ol/ol.css';
import { transform } from 'ol/proj';
import VectorSource from 'ol/source/Vector';
import { Icon, Style } from 'ol/style';
import { LocaleMessage } from 'vue-i18n';

/** Inspired from https://openlayers.org/en/latest/examples/icon.html */
export default ({
  id,
  name,
  lat,
  long,
  usageBatiment,
  surface,
}: {
  id: number;
  name: string;
  lat: number;
  long: number;
  usageBatiment: LocaleMessage | undefined;
  surface: string | undefined;
}) => {
  const icon = new Image(32, 48);
  /** Download from https://openlayers.org/en/latest/examples/data/icon.png */
  icon.src =
    'data:image/png;base64,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';

  const iconFeature = new Feature({
    geometry: new Point(transform([long, lat], 'EPSG:4326', 'EPSG:3857'), 1000),
    id,
    name,
    usageBatiment,
    surface,
  });

  const iconStyle = new Style({
    image: new Icon({
      anchor: [0.5, 46],
      anchorXUnits: 'fraction',
      anchorYUnits: 'pixels',
      img: icon,
      imgSize: [icon.width, icon.height],
      scale: 0.4,
    }),
  });

  iconFeature.setStyle(iconStyle);

  const vectorSource = new VectorSource({
    features: [iconFeature],
  });

  return new VectorLayer({
    source: vectorSource,
  });
};
