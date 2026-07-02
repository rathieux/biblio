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
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Livre } from '../../model/livre';
import { Auteur } from '../../model/auteur';
import { Editeur } from '../../model/editeur';
import { Collection } from '../../model/collection';
import { LivreService } from '../../service/livre-service';
import { AuteurService } from '../../service/auteur-service/auteur-service';
import { EditeurService } from '../../service/editeur-service';
import { CollectionService } from '../../service/collection-service';

@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './livre-page.html',
  styleUrl: '../shared-styling.css',
})
export class LivrePage implements OnInit {
  private titleService: Title = inject(Title);
  private livreService: LivreService = inject(LivreService);
  private auteurService: AuteurService = inject(AuteurService);
  private editeurService: EditeurService = inject(EditeurService);
  private collectionService: CollectionService = inject(CollectionService);

  private refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<Livre[]>;

  // Listes pour les selects du formulaire
  protected auteurs$!: Observable<Auteur[]>;
  protected editeurs$!: Observable<Editeur[]>;
  protected collections$!: Observable<Collection[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formLivre!: FormGroup;
  protected formCtrlTitre!: FormControl;
  protected formCtrlResume!: FormControl;
  protected formCtrlAnnee!: FormControl;
  protected formCtrlAuteur!: FormControl;
  protected formCtrlEditeur!: FormControl;
  protected formCtrlCollection!: FormControl;
  protected editingLivreId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Liste des livres');

    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll()),
    );

    this.auteurs$ = this.auteurService.findAll();
    this.editeurs$ = this.editeurService.findAll();
    this.collections$ = this.collectionService.findAll();

    this.formCtrlTitre = this.formBuilder.control('', Validators.required);
    this.formCtrlResume = this.formBuilder.control('');
    this.formCtrlAnnee = this.formBuilder.control('', Validators.required);
    this.formCtrlAuteur = this.formBuilder.control('', Validators.required);
    this.formCtrlEditeur = this.formBuilder.control('', Validators.required);
    this.formCtrlCollection = this.formBuilder.control('');

    this.formLivre = this.formBuilder.group({
      titre: this.formCtrlTitre,
      resume: this.formCtrlResume,
      annee: this.formCtrlAnnee,
      auteur: this.formCtrlAuteur,
      editeur: this.formCtrlEditeur,
      collection: this.formCtrlCollection,
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    const raw = this.formLivre.getRawValue();

    const livre: Livre = {
      titre: raw.titre,
      resume: raw.resume,
      annee: raw.annee,
      auteur: { id: raw.auteur }  as Auteur,
      editeur: { id: raw.editeur } as Editeur,
      collection: raw.collection ? ({ id: raw.collection } as Collection) : undefined,
    };

    if (this.editingLivreId) {
      livre.id = this.editingLivreId;
      this.livreService.update(livre).subscribe(() => this.reload());
    } else {
      this.livreService.add(livre).subscribe(() => this.reload());
    }

    this.formLivre.reset();
    this.editingLivreId = 0;
  }

  protected edit(livre: Livre) {
    this.editingLivreId = livre.id;
    this.formCtrlTitre.setValue(livre.titre);
    this.formCtrlResume.setValue(livre.resume);
    this.formCtrlAnnee.setValue(livre.annee);
    this.formCtrlAuteur.setValue(livre.auteur?.id);
    this.formCtrlEditeur.setValue(livre.editeur?.id);
    this.formCtrlCollection.setValue(livre.collection?.id);
  }

  protected remove(livre: Livre) {
    this.livreService.remove(livre).subscribe(() => this.reload());
  }
}
