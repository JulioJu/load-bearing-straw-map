import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Batiment = () => import('@/entities/batiment/batiment.vue');
const BatimentUpdate = () => import('@/entities/batiment/batiment-update.vue');
const BatimentDetails = () => import('@/entities/batiment/batiment-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'batiment',
      name: 'Batiment',
      component: Batiment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'batiment/new',
      name: 'BatimentCreate',
      component: BatimentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'batiment/:batimentId/edit',
      name: 'BatimentEdit',
      component: BatimentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'batiment/:batimentId/view',
      name: 'BatimentView',
      component: BatimentDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
