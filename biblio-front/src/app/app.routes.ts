import { Routes } from '@angular/router';
import { CollectionPage } from './page/collection-page/collection-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';
import { EditeurPage } from './page/editeur-page/editeur-page';
import { RegisterPage } from './page/register-page/register-page';
import { AvisPage } from './page/avis-page/avis-page';
import { AuteurPage } from './page/auteur-page/auteur-page/auteur-page';
import { LivrePage } from './page/livre-page/livre-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
    canActivate: [authGuard],
  },

   {
    path: 'auteur',
    component: AuteurPage,
    canActivate: [authGuard],
  },

  { path: 'login', component: LoginPage },
  { path: 'register', component: RegisterPage },
  {
    path: 'editeur',
    component: EditeurPage,
    canActivate: [authGuard],
  },
  {
    path: 'avis',
    component: AvisPage,
    canActivate: [authGuard]
  },

    {
    path: 'livre',
    component: LivrePage,
    canActivate: [authGuard],
  },
];
