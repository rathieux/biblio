import { LivreService } from './../../service/livre-service';
import { Component, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AvisService } from '../../service/avis-service';
import { Avis } from '../../model/avis';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Livre } from '../../model/livre';

@Component({
  selector: 'app-avis-page',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './avis-page.html',
  styleUrl: './avis-page.css',
})
export class AvisPage {
  private titleService: Title = inject(Title);
  private avisService: AvisService = inject(AvisService);
  private livreService: LivreService= inject(LivreService)

  private refresh$: Subject<void> = new Subject<void>();
  protected avis$!: Observable<Avis[]>;
  protected livres$!: Observable<Livre[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formAvis!: FormGroup;
  protected formCtrlNote!: FormControl;
  protected formCtrlComment!: FormControl;
  protected formCtrlDate!: FormControl;
  protected formCtrlLivre!:FormControl;

  protected editingAvisId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Liste des Avis');

    this.avis$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.avisService.findAll()),
    );
    this.livres$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.livreService.findAll()),
    );

    this.formCtrlNote = this.formBuilder.control('', Validators.required);
    this.formCtrlComment = this.formBuilder.control('');
    this.formCtrlDate = this.formBuilder.control('');
    this.formCtrlLivre = this.formBuilder.control('');

    this.formAvis = this.formBuilder.group({
      note: this.formCtrlNote,
      commentaire: this.formCtrlComment,
      date: this.formCtrlDate,
      livre: this.formCtrlLivre
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    const avis: Avis = this.formAvis.getRawValue();
    if (this.editingAvisId) {
      avis.id = this.editingAvisId;
      this.avisService.update(avis).subscribe(() => this.reload());
    } else {
      this.avisService.add(avis).subscribe(() => this.reload());
    }
    this.formAvis.reset();
    this.editingAvisId = 0;
  }

  protected edit(avis: Avis) {
    this.editingAvisId = avis.id;
    this.formCtrlNote.setValue(avis.note);
    this.formCtrlComment.setValue(avis.commentaire);
    this.formCtrlDate.setValue(avis.date);
    this.formCtrlLivre.setValue(avis.livreCustom)
  }

  protected remove(avis: Avis) {
    this.avisService.remove(avis).subscribe(() => this.reload());
  }

protected compareLivre(l1: Livre | null, l2: Livre | null): boolean {
  if (l1 === null || l2 === null) {
    return false;
  }
  return l1.id === l2.id;

}
}
