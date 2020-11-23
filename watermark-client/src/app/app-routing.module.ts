import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { UploadImageComponent } from './components/upload-image/upload-image.component';
import { AuthenticatedGuard} from './guards/authenticated.guard';
import { DisplayImagesComponent } from './components/display-images/display-images.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login'},
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'display', component: DisplayImagesComponent },
  { path: 'watermark', component: UploadImageComponent, canActivate: [AuthenticatedGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
