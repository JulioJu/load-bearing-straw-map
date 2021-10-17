import 'ol/ol.css';
import { Map, Overlay } from 'ol';
import VueRouter from 'vue-router';

const appendList = (list: HTMLUListElement, content: string) => {
  const li = document.createElement('li');
  li.innerHTML = content;
  list.appendChild(li);
};

const disposePopup = (overlay: Overlay, popupCloser: HTMLDivElement) => {
  overlay.setPosition(undefined);
  popupCloser.blur();
};

const registerMapEvents = (map: Map, popupContent: HTMLDivElement, overlay: Overlay, popupCloser: HTMLDivElement, router: VueRouter) => {
  // display popup on click
  map.on('click', evt => {
    const feature = map.forEachFeatureAtPixel(evt.pixel, feature => {
      return feature;
    });
    if (feature) {
      popupContent.innerHTML = '';
      overlay.setPosition(evt.coordinate);
      const list = document.createElement('ul');
      const name = feature.get('name');
      if (name) {
        appendList(list, `nom: ${name}`);
      }
      appendList(list, `latitude: ${feature.get('latitude')}`);
      appendList(list, `longitude: ${feature.get('longitude')}`);
      const li = document.createElement('li');
      const anchor = document.createElement('a');
      anchor.innerText = 'Voir +';
      anchor.addEventListener('click', (ev: MouseEvent) => {
        ev.preventDefault();
        router.push({ name: 'BatimentsView', params: { batimentsId: feature.get('id') } });
      });
      li.appendChild(anchor);
      list.appendChild(li);
      popupContent.appendChild(list);
    } else {
      disposePopup(overlay, popupCloser);
    }
  });

  // change mouse cursor when over marker
  map.on('pointermove', function (e) {
    const pixel = map.getEventPixel(e.originalEvent);
    const hit = map.hasFeatureAtPixel(pixel);
    (map.getTarget() as HTMLElement).style.cursor = hit ? 'pointer' : '';
  });
  // Close the popup when the map is moved
  map.on('movestart', function () {
    disposePopup(overlay, popupCloser);
  });
};

/**
 * Inspired from
 * https://openlayers.org/en/latest/examples/icon.html
 * https://openlayers.org/en/latest/examples/popup.html
 */
export default ({
  map,
  popup,
  popupCloser,
  popupContent,
  router,
}: {
  map: Map;
  popup: HTMLDivElement;
  popupCloser: HTMLDivElement;
  popupContent: HTMLDivElement;
  router: VueRouter;
}) => {
  const overlay = new Overlay({
    element: popup,
    autoPan: true,
    autoPanAnimation: {
      duration: 250,
    },
  });
  map.addOverlay(overlay);

  popupCloser.onclick = () => {
    disposePopup(overlay, popupCloser);
    return false;
  };

  registerMapEvents(map, popupContent, overlay, popupCloser, router);
};
