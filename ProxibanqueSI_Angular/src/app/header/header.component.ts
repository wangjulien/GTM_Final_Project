import { Component, OnInit } from '@angular/core';
import { Conseiller } from '../model/conseiller';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../service/authentication.service';
import { Employee } from '../model/employee';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$: Observable<boolean>;
  currentUser = new Employee();

  isGerant = false;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
    this.isLoggedIn$.subscribe(() => {
      if (null != localStorage.getItem('currentUser')) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (this.currentUser.roles[0] === 'ROLE_GERANT') {
          this.isGerant = true;
        } else {
          this.isGerant = false;
        }
      }
    });
  }
}