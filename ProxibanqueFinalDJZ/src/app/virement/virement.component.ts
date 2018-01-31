import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {Client} from '../model/client';
import {Compte} from '../model/compte';
import {Virement} from '../model/virement';
import {ConseillerClientService} from '../service/conseiller-client.service';
import {VirementService} from '../service/virement.service';
import {AlertService} from '../service/alert.service';

@Component({
  selector: 'app-virement',
  templateUrl: './virement.component.html',
  styleUrls: ['./virement.component.css']
})

export class VirementComponent implements OnInit {

  clients: Client[];
  allClients: Client[];

  departs = new Array<Compte>();
  cibles = new Array<Compte>();

  clientDebiteur: Client;
  clientCrediteur: Client;

  compteDepart: Compte;
  compteCible: Compte;

  virement: Virement = new Virement();

  constructor(
    private conseillerCLientService: ConseillerClientService,
    private virementService: VirementService,
    private alertService: AlertService) {}

  selectDebiteur(client: Client): void {
    console.log(JSON.stringify(client));
    
    this.departs = new Array<Compte>();
        
    this.virement.clientDebiteur = client;

    if (client.compteCourant.etatActif) {
      this.departs.push(client.compteCourant);
    }

    if (client.compteEpargne.etatActif) {
      this.departs.push(client.compteEpargne);
    }
  }

  selectCrediteur(client: Client): void {
    console.log(JSON.stringify(client));
    
    this.cibles = new Array<Compte>();

    this.virement.clientCrediteur = client;

    if (client.compteCourant.etatActif) {
      this.cibles.push(client.compteCourant);
    }

    if (client.compteEpargne.etatActif) {
      this.cibles.push(client.compteEpargne);
    }
  }

  selectDepart(compte: Compte) {
    console.log(JSON.stringify(compte));
    
    this.virement.depart = compte;
  }

  selectCible(compte: Compte) {
    console.log(JSON.stringify(compte));

    this.virement.cible = compte;
  }

  onSubmit() {

    if (this.virement.montant <= 0) {
      confirm("Le montant à virer doit être positif");
      return false;
    } else {

      console.log('Effectuer un virement : \n' + JSON.stringify(this.virement));
      this.virementService.doVirement(this.virement)
        .subscribe(data => {this.virement = data; this.alertService.success('Virement réussi');},
        error => this.alertService.error(error.error));
    }
  }

  getClientsByConseiller() {
    this.conseillerCLientService.getClientsByConseiller(JSON.parse(localStorage.getItem('currentUser')).id)
      .subscribe(data => this.clients = data, error => this.alertService.error(error.error));

    return false;
  }

  getAllClients() {
    this.conseillerCLientService.getClients()
      .subscribe(data => this.allClients = data, error => this.alertService.error(error.error));

    return false;
  }

  ngOnInit() {

    this.getAllClients();
    this.getClientsByConseiller();
  }
}
