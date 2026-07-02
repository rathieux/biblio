import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  const apiRequest = req.clone({
    url: `http://java:8080/api${ req.url }`
  });

  return next(apiRequest);
};
