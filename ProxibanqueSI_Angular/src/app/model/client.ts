import { Adresse } from './adresse';
import { CompteCourant } from './compte-courant';
import { CompteEpargne } from './compte-epargne';
import { Conseiller } from './conseiller';

export class Client {
  id: string;
  refClient = '';
  nom: string;
  prenom: string;
  compteCourant: CompteCourant;
  compteEpargne: CompteEpargne;
  adresse: Adresse;
  login: string;
  password: string;
  conseiller: Conseiller;

  constructor() {
    this.compteCourant = new CompteCourant();
    this.compteEpargne = new CompteEpargne();
    this.adresse = new Adresse();
    this.conseiller = new Conseiller();
  }

}
