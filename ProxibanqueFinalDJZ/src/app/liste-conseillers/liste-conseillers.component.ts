import { Component, OnInit, Input } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { Conseiller } from '../model/conseiller';
import { GerantConseillerService } from '../service/gerant-conseiller.service';
import { AlertService } from '../service/alert.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-liste-conseillers',
  templateUrl: './liste-conseillers.component.html',
  styleUrls: ['./liste-conseillers.component.css']
})
export class ListeConseillersComponent implements OnInit {

  conseillers: Array<Conseiller>;

  constructor(
    private gerantConseillerService: GerantConseillerService,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private router: Router) { }

  loginAsConeiller(conseiller) {
    this.authenticationService.auth(conseiller.login, conseiller.password)
          .subscribe(data => this.router.navigate(['/home']),
                    error => this.alertService.error(error.error));
  }

  getAllConseilers() {
    this.gerantConseillerService.getConseillersByGerant(JSON.parse(localStorage.getItem('currentUser')).id)
      .subscribe(data => this.conseillers = data, error => this.alertService.error(error.error));

    return false;
  }

  goToDetails(conseiller) {
    this.router.navigate(['/update-conseiller', conseiller.id])

    return false;
  }

  deleteConseiller(conseiller: Conseiller) {
    console.log('Conseiller à supprimer : ' + JSON.stringify(conseiller));

    if (confirm("Supprimer le conseiller " + conseiller.nom + " " + conseiller.prenom + " ?")) {
      this.gerantConseillerService.deleteConseiller(conseiller.id)
        .subscribe(() => { this.alertService.success('Suppression du conseiller réussi') },
        error => this.alertService.error(error.error));
    }
    return false;
  }

  ngOnInit() {
    console.log('liste-conseiller component marche');
    this.getAllConseilers();
  }

}
