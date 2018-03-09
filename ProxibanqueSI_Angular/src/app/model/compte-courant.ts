import { Compte } from './compte';

export class CompteCourant extends Compte {
    private type = 'CompteCourant';
    decouvertAuthorise: number;
}
