import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { ConseillerClientService } from '../service/conseiller-client.service';
import { AlertService } from '../service/alert.service';
import * as CONST from '../constants';
import { Client } from '../model/client';

@Component({
  selector: 'app-updateclient',
  templateUrl: './updateclient.component.html',
  styleUrls: ['./updateclient.component.css']
})
export class UpdateclientComponent implements OnInit {

  actualClient: Client = new Client();

  private clients_list_url: string = CONST.REST_HOST + '/clients';

  constructor(
    private conseillerCLientService: ConseillerClientService,
    private route: ActivatedRoute,
    private alertService: AlertService) {
    this.actualClient = new Client();
  }


  onSubmit() {
    
    console.log('Client à traiter : ' + JSON.stringify(this.actualClient));
    
    if (null == this.actualClient.id) {
      this.actualClient.conseiller = JSON.parse(localStorage.getItem('currentUser'));
      
      console.log('Client à ajouter : ' + JSON.stringify(this.actualClient));
        
      this.conseillerCLientService.addClient(this.actualClient)
        .subscribe(data => { this.actualClient = data; this.alertService.success('Nouveau client ajouté');},
                    error => this.alertService.error(error.error));
        
    } else {
      console.log('Client à modifier : ' + JSON.stringify(this.actualClient));
  
      this.conseillerCLientService.updateClient(this.actualClient)
        .subscribe(data => { this.actualClient = data; this.alertService.success('Enregistrement réussi');},
                    error => this.alertService.error(error.error));
    }
  }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      let id = params.get('id');
      if (null != id) {
        this.conseillerCLientService.getClient(id)
          .subscribe(data => this.actualClient = data, 
                      error => this.alertService.error(error.error));
      }
    });
  }

}

