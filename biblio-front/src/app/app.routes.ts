import { Routes } from '@angular/router';
import { CollectionPage } from './collection-page/collection-page';
import { EditeurPage } from './page/editeur-page/editeur-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
  },

  {
    path: 'editeur',
    component: EditeurPage,
  },
];
