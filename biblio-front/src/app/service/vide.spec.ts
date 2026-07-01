import { TestBed } from '@angular/core/testing';

import { Vide } from './vide';

describe('Vide', () => {
  let service: Vide;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Vide);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
