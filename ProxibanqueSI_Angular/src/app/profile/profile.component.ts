import { Component, OnInit } from '@angular/core';

import { Conseiller } from '../model/conseiller';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  model: any;

  constructor() { }

  update() {
    
  }

  ngOnInit() {
    this.model = JSON.parse(localStorage.getItem('currentUser'));
  }

}
