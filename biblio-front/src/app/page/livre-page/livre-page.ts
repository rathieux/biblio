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
import { LivreService } from '../../service/livre-service';

@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  private titleService: Title = inject(Title);
  private livreService: LivreService = inject(LivreService);

  private refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<Livre[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formLivre!: FormGroup;
  protected formCtrlTitre!: FormControl;
  protected editingLivreId: number | undefined;

  ngOnInit(): void {
    this.titleService.setTitle('Liste des livres');

    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll()),
    );

    this.formCtrlTitre = this.formBuilder.control('', Validators.required);

    this.formLivre = this.formBuilder.group({
      titre: this.formCtrlTitre,
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    const { titre } = this.formLivre.getRawValue();

    const request$ =
      this.editingLivreId !== undefined
        ? this.livreService.update({ id: this.editingLivreId, titre })
        : this.livreService.add({ titre });

    request$.subscribe(() => {
      this.reload();
      this.formLivre.reset();
      this.editingLivreId = undefined;
    });
  }

  protected edit(livre: Livre) {
    this.editingLivreId = livre.id;
    this.formCtrlTitre.setValue(livre.titre);
  }

  protected remove(livre: Livre) {
    this.livreService.remove(livre).subscribe(() => this.reload());
  }
}
