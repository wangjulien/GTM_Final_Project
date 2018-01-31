import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser = new Employee();;
  
  constructor() { }

  ngOnInit() {
    console.log('home fonctionne');
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

}
