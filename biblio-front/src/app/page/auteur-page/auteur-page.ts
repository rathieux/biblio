import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuteurService } from '../../service/auteur-service/auteur-service';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Auteur } from '../../model/auteur';

@Component({
  selector: 'app-auteur-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './auteur-page.html',
  styleUrl: '../shared-styling.css',
})
export class AuteurPage implements OnInit {
  private titleService: Title = inject(Title);
  private auteurService: AuteurService = inject(AuteurService);

  private refresh$: Subject<void> = new Subject<void>();
  protected auteurs$!: Observable<Auteur[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formAuteur!: FormGroup;
  protected formCtrlNom!: FormControl;
  protected formCtrlPrenom!: FormControl;
  protected formCtrlNationalite!: FormControl;
  protected editingAuteurId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Liste des auteurs');

    this.auteurs$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.auteurService.findAll()),
    );

    this.formCtrlNom = this.formBuilder.control('', Validators.required);
    this.formCtrlPrenom = this.formBuilder.control('', Validators.required);
    this.formCtrlNationalite = this.formBuilder.control('', Validators.required);

    this.formAuteur = this.formBuilder.group({
      nom: this.formCtrlNom,
      prenom: this.formCtrlPrenom,
      nationalite: this.formCtrlNationalite,
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    const raw = this.formAuteur.getRawValue();

    const auteur: Auteur = {
        nom: raw.nom,
        prenom: raw.prenom,
        nationalite: raw.nationalite,
        };

    if (this.editingAuteurId) {
      auteur.id = this.editingAuteurId;
      this.auteurService.update(auteur).subscribe(() => this.reload());
    } else {
      this.auteurService.add(auteur).subscribe(() => this.reload());
    }

    this.formAuteur.reset();
    this.editingAuteurId = 0;
  }

  protected edit(auteur: Auteur) {
    this.editingAuteurId = auteur.id;
    this.formCtrlNom.setValue(auteur.nom);
    this.formCtrlPrenom.setValue(auteur.prenom);
    this.formCtrlNationalite.setValue(auteur.nationalite);
  }

  protected remove(auteur: Auteur) {
    this.auteurService.remove(auteur).subscribe(() => this.reload());
  }
}
