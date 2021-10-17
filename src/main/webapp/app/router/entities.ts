import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const LoadBearingStrawMap = () => import('@/entities/load-bearing-straw-map/load-bearing-straw-map.vue');
// prettier-ignore
const LoadBearingStrawMapUpdate = () => import('@/entities/load-bearing-straw-map/load-bearing-straw-map-update.vue');
// prettier-ignore
const LoadBearingStrawMapDetails = () => import('@/entities/load-bearing-straw-map/load-bearing-straw-map-details.vue');
// prettier-ignore
const Batiments = () => import('@/entities/batiments/batiments.vue');
// prettier-ignore
const BatimentsUpdate = () => import('@/entities/batiments/batiments-update.vue');
// prettier-ignore
const BatimentsDetails = () => import('@/entities/batiments/batiments-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/load-bearing-straw-map',
    name: 'LoadBearingStrawMap',
    component: LoadBearingStrawMap,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/load-bearing-straw-map/new',
    name: 'LoadBearingStrawMapCreate',
    component: LoadBearingStrawMapUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/load-bearing-straw-map/:loadBearingStrawMapId/edit',
    name: 'LoadBearingStrawMapEdit',
    component: LoadBearingStrawMapUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/load-bearing-straw-map/:loadBearingStrawMapId/view',
    name: 'LoadBearingStrawMapView',
    component: LoadBearingStrawMapDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/batiments',
    name: 'Batiments',
    component: Batiments,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/batiments/new',
    name: 'BatimentsCreate',
    component: BatimentsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/batiments/:batimentsId/edit',
    name: 'BatimentsEdit',
    component: BatimentsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/batiments/:batimentsId/view',
    name: 'BatimentsView',
    component: BatimentsDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
