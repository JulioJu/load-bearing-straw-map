import { Feature } from 'ol';
import Circle from 'ol/geom/Circle';
import VectorLayer from 'ol/layer/Vector';
import 'ol/ol.css';
import { transform } from 'ol/proj';
import VectorSource from 'ol/source/Vector';
import Fill from 'ol/style/Fill';
import Stroke from 'ol/style/Stroke';
import Style from 'ol/style/Style';

export default ({ long, lat }: { long: number; lat: number }) => {
  const circle = new Circle(transform([long, lat], 'EPSG:4326', 'EPSG:3857'), 1000);

  const CircleFeature = new Feature(circle);

  const vectorSource = new VectorSource();

  vectorSource.addFeatures([CircleFeature]);

  return new VectorLayer({
    source: vectorSource,
    style: [
      new Style({
        stroke: new Stroke({
          color: 'blue',
          width: 10,
        }),
        fill: new Fill({
          color: 'rgba(0, 0, 255, 0.1)',
        }),
      }),
    ],
  });
};
