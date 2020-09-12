import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Contato } from '../model/contato.model';
import { BaseCrudService } from './base-crud.service';

@Injectable({
  providedIn: 'root'
})
export class ContatoService extends BaseCrudService<Contato> {

  constructor(protected httpClient: HttpClient) {
    super(httpClient, `${environment.API_URL}`, j => new Contato(j));
  }

  // TODO: select de tipos de canais
  // getFormula(examTypeFilename): Observable<any> {
  //   return this.httpClient.get<any>(`${environment.SISMED_URL}/getFormula`, {
  //     params: { examTypeFilename: examTypeFilename }
  //   });
  // }

  // getModalities(): Observable<any[]> {
  //   return of([{
  //     name: 'Tomografia Computadorizada',
  //     value: 'CT'
  //   }, {
  //     name: 'Ressonância Magnética',
  //     value: 'MR'
  //   }, {
  //     name: 'Mamografia',
  //     value: 'MG'
  //   }, {
  //     name: 'Radiografia Computadorizada',
  //     value: 'CR'
  //   }]);
  // }
}
