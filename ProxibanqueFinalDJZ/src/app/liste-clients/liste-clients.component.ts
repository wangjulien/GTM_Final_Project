import { Component, OnInit, Input } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { Client } from '../model/client';
import { ConseillerClientService } from '../service/conseiller-client.service';
import { AlertService } from '../service/alert.service';

import * as CONST from '../constants';

@Component({
  selector: 'app-liste-clients',
  templateUrl: './liste-clients.component.html',
  styleUrls: ['./liste-clients.component.css']
})
export class ListeClientsComponent implements OnInit {

  private soldeFortune: number = CONST.CLIENT_RICH;

  clients: Array<Client>;

  constructor(
    private conseillerCLientService: ConseillerClientService,
    private alertService: AlertService,
    private router: Router) { }

  getAllClients() {
    this.conseillerCLientService.getClientsByConseiller(JSON.parse(localStorage.getItem('currentUser')).id)
      .subscribe(data => this.clients = data, error => this.alertService.error(error.error));

    return false;
  }

  goToDetails(client) {
    this.router.navigate(['/update-client', client.id])
    
    return false;
  } 

  deleteClient(client: Client) {
    console.log('Client à supprimer : ' + JSON.stringify(client));

    if (client.compteCourant.solde != 0 || client.compteEpargne.solde != 0) {
      if (confirm("Le compte courant ou épargne contient encore de l'argent. Veuillez d'abord faire le virement du client "
        + client.nom + ' ' + client.prenom + " vers un compte interne. Confirmer-vous votre redirection vers la page du virement?")) {
          this.router.navigate(['/virement']);
      }
      return false;
    }

    if (confirm("Veuillez supprimer le client " + client.nom + ' ' + client.prenom + " et tous ses comptes associés?")) {
      this.conseillerCLientService.deleteClient(client.id)
        .subscribe(() => { this.getAllClients(); this.alertService.success('Suppression de client réussi'); },
                  error => this.alertService.error(error.error));
    }

    return false;
  }

  ngOnInit() {
    console.log('liste-client component marche');
    this.getAllClients();
  }

}
