import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Contato } from 'src/app/model/contato.model';
import { FormState } from 'src/app/model/form-state.model';
import { ContatoService } from 'src/app/services/contato.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-contato-form',
  templateUrl: './contato-form.component.html',
  styleUrls: ['./contato-form.component.scss']
})
export class ContatoFormComponent implements OnInit {

  contato: Contato;
  formState: FormState;
  formGroup: FormGroup;
  requested: boolean;

  constructor(
    private contatoService: ContatoService,
    public location: Location,
    public utilService: UtilService
  ) {
    this.formState = window.history.state.formState;
    this.contato = window.history.state.contato;
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      id: new FormControl(),
      nome: new FormControl('', Validators.required),
      tipoCanal: new FormControl('', Validators.required),
      valor: new FormControl('', Validators.required),
      obs: new FormControl('')
    });

    if (this.formState == FormState.EDITION)
      this.formGroup.patchValue({
        ...this.contato
      });
  }

  onSubmitBtnClick() {
    if (this.isFormInvalid())
      return;

    this.requested = true;
    const observable = this.contatoService[this.formState == FormState.CREATION ? 'post' : 'put'](this.formGroup.value);
    observable.subscribe(
      response => {
        this.utilService.showSnackBar(`O contato foi ${this.formState == FormState.CREATION ? 'criado' : 'atualizado'}.`);
        this.location.back();
      },
      response => {
        this.utilService.showSnackBar(response.error.messages[0].message);
        this.requested = false;
      }
    );
  }

  private isFormInvalid() {
    let invalid = false;

    if (this.formGroup.invalid) {
      invalid = true;
      this.utilService.showSnackBar('Preencha os campos obrigatórios.');
    }

    return invalid;
  }

}
