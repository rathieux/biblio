import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';

export const authGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  return true;
  const authService: AuthService = inject(AuthService);
  if (authService.isLogged()) {
    return true;
  }
  return router.createUrlTree(['login']);
};
