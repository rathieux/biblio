import { Routes } from '@angular/router';
import { CollectionPage } from './page/collection-page/collection-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';
import { EditeurPage } from './page/editeur-page/editeur-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
    canActivate: [authGuard],
  },

   {
    path: 'auteur',
    component: CollectionPage,
    canActivate: [authGuard],
  },

  { path: 'login', component: LoginPage },

  {
    path: 'editeur',
    component: EditeurPage,
    canActivate: [authGuard],
  },


];
