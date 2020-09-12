import { RouterModule, Routes } from '@angular/router';
import { ContatoFormComponent } from './components/contato/contato-form/contato-form.component';
import { ContatoListComponent } from './components/contato/contato-list/contato-list.component';


const appRoutes: Routes = [
  { path: '', component: ContatoListComponent },
  { path: 'contatoForm', component: ContatoFormComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
