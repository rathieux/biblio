import { Routes } from '@angular/router';
import { CollectionPage } from './collection-page/collection-page';
import { LivrePage } from './page/livre-page/livre-page';
import { EditeurPage } from './page/editeur-page/editeur-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
  },
    {
    path: 'livre',
    component: LivrePage,
    },
  {
    path: 'editeur',
    component: EditeurPage,
  },
];
