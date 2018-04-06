import { Adresse } from './adresse';

export class Employee {
  id: string;
  refEmployee: string;
  nom: string;
  prenom: string;
  login: string;
  password: string;
  adresse: Adresse;

  roles: string[];

  constructor() {
    this.adresse = new Adresse();
  }
}
