import { Routes } from '@angular/router';
import { CollectionPage } from './collection-page/collection-page';
import { LivrePage } from './page/livre-page/livre-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
  },
    {
    path: 'livre',
    component: LivrePage,
  },
];
