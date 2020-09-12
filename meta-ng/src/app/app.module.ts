import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorIntl } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { ContatoFormComponent } from './components/contato/contato-form/contato-form.component';
import { ContatoListComponent } from './components/contato/contato-list/contato-list.component';
import { CustomPaginator } from './model/custom-paginator.model';
import { MaterialModule } from './modules/material/material.module';
import { ContatoService } from './services/contato.service';
import { UtilService } from './services/util.service';


@NgModule({
  declarations: [
    AppComponent,
    ConfirmationDialogComponent,
    ContatoFormComponent,
    ContatoListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    routing
  ],
  providers: [
    ContatoService,
    UtilService,
    { provide: MatPaginatorIntl, useClass: CustomPaginator }
  ],
  entryComponents: [
    ConfirmationDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
