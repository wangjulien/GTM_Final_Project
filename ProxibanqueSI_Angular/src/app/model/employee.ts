import { Adresse } from './adresse';
import { UserRole } from './user-role';

export class Employee {
  id: string;
  refEmployee: string;
  nom: string;
  prenom: string;
  login: string;
  password: string;
  adresse: Adresse;

  roles: UserRole[];

  constructor() {
    this.adresse = new Adresse();
  }
}
