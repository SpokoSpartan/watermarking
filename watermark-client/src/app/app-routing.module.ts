import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { UploadImageComponent } from './components/upload-image/upload-image.component';
import {AuthenticatedGuard} from './guards/authenticated.guard';
import {CheckComponent} from './components/check/check.component';
import { DisplayImagesComponent } from './components/display-image/display-image.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login'},
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'display', component: DisplayImagesComponent },
  { path: 'watermark', component: UploadImageComponent, canActivate: [AuthenticatedGuard]},
  { path: 'verify', component: CheckComponent, canActivate: [AuthenticatedGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
