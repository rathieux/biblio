import { Auteur } from './auteur';
import { Editeur } from './editeur';
import { Collection } from './collection';

export interface Livre {
  id?: number;
  titre: string;
  resume?: string;
  annee?: number;
  auteur: Auteur;
  editeur: Editeur;
  collection?: Collection;
}
