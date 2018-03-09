import { Compte } from './compte';

export class CompteEpargne extends Compte {
    private type = 'CompteEpargne';
    tauxInteret: number;
}
