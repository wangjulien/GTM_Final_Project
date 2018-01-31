import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { RouterModule } from '@angular/router';
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AlertComponent } from './alert/alert.component';
import { ProfileComponent } from './profile/profile.component';

import { ListeClientsComponent } from './liste-clients/liste-clients.component';
import { ListeConseillersComponent } from './liste-conseillers/liste-conseillers.component';
import { UpdateclientComponent } from './updateclient/updateclient.component';
import { VirementComponent } from './virement/virement.component';

import { AlertService } from './service/alert.service';
import { AuthenticationService } from './service/authentication.service';
import { AuthGuard } from './service/auth.guard';
import { JwtInterceptor } from './service/jwt.interceptor';
import { ConseillerClientService } from './service/conseiller-client.service';
import { GerantConseillerService } from './service/gerant-conseiller.service';
import { VirementService } from './service/virement.service';

@NgModule({
  declarations: [
    AppComponent,
    UpdateclientComponent,
    ListeClientsComponent,
    VirementComponent,
    LoginComponent,
    AlertComponent,
    ProfileComponent,
    HomeComponent,
    HeaderComponent,
    ListeConseillersComponent
  ],
  imports: [
    BrowserModule, 
    HttpClientModule, 
    FormsModule, 
    routing
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    ConseillerClientService,
    GerantConseillerService,
    VirementService,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: JwtInterceptor,
        multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
