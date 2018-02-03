import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VirementComponent } from './virement/virement.component';
import { UpdateclientComponent } from './updateclient/updateclient.component';
import { ListeClientsComponent } from './liste-clients/liste-clients.component';
import { ListeConseillersComponent } from './liste-conseillers/liste-conseillers.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './service/auth.guard';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]  },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'virement', component: VirementComponent, canActivate: [AuthGuard] },
  { path: 'update-client/:id', component: UpdateclientComponent, canActivate: [AuthGuard] },
  { path: 'update-client', component: UpdateclientComponent, canActivate: [AuthGuard] },
  { path: 'gestion-client', component: ListeClientsComponent, canActivate: [AuthGuard] },
  { path: 'gestion-conseiller', component: ListeConseillersComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: '**', redirectTo: '' }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
