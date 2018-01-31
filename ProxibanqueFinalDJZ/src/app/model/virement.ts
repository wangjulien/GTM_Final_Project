import {Client} from './client';
import {Compte} from './compte';

export class Virement {
  id: string;
  dateOperation: Date = new Date();
  clientDebiteur: Client = new Client();
  clientCrediteur: Client  = new Client();
  depart: Compte;
  cible: Compte;
  montant: number;
}
