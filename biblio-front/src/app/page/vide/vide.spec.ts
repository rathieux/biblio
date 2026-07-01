import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Vide } from './vide';

describe('Vide', () => {
  let component: Vide;
  let fixture: ComponentFixture<Vide>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Vide],
    }).compileComponents();

    fixture = TestBed.createComponent(Vide);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
