import { Routes } from '@angular/router';
import { CollectionPage } from './page/collection-page/collection-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage,
    canActivate: [authGuard],
  },
  { path: 'login', component: LoginPage },
];
