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
import { Collection } from '../../model/collection';
import { CollectionService } from '../../service/collection-service';

@Component({
  selector: 'app-collection-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {
  private titleService: Title = inject(Title);
  private collectionService: CollectionService = inject(CollectionService);

  private refresh$: Subject<void> = new Subject<void>();
  protected collections$!: Observable<Collection[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formCollection!: FormGroup;
  protected formCtrlNom!: FormControl;
  protected editingCollectionId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Liste des collections');

    this.collections$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.collectionService.findAll()),
    );

    this.formCtrlNom = this.formBuilder.control('', Validators.required);

    this.formCollection = this.formBuilder.group({
      nom: this.formCtrlNom,
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    const collection: Collection = this.formCollection.getRawValue();

    if (this.editingCollectionId) {
      collection.id = this.editingCollectionId;
      this.collectionService.update(collection).subscribe(() => this.reload());
    } else {
      this.collectionService.add(collection).subscribe(() => this.reload());
    }

    this.formCollection.reset();
    this.editingCollectionId = 0;
  }

  protected edit(collection: Collection) {
    this.editingCollectionId = collection.id;
    this.formCtrlNom.setValue(collection.nom);
  }

  protected remove(collection: Collection) {
    this.collectionService.remove(collection).subscribe(() => this.reload());
  }
}
