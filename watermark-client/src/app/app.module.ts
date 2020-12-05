import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';

/* Routing */
import { AppRoutingModule } from './app-routing.module';

/* Angular Material */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

/* Components */
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';

/* Forms */
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/* Angular Flex */
import { FlexLayoutModule } from '@angular/flex-layout';

/* Angular Flex */
import { UploadImageComponent } from './components/upload-image/upload-image.component';

/* Drop File*/
import {NgxFileDropModule} from 'ngx-file-drop';

/* Http Client*/
import {HttpClientModule} from '@angular/common/http';
import { CheckComponent } from './components/check/check.component';

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    UploadImageComponent,
    CheckComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    AngularMaterialModule,
    NgxFileDropModule,
    HttpClientModule,
    AngularMaterialModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
