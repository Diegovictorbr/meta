import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { Contato } from 'src/app/model/contato.model';
import { FormState } from 'src/app/model/form-state.model';
import { ContatoService } from 'src/app/services/contato.service';
import { UtilService } from 'src/app/services/util.service';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-contato-list',
  templateUrl: './contato-list.component.html',
  styleUrls: ['./contato-list.component.scss']
})
export class ContatoListComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns = ['nome', 'tipoCanal', 'valor', 'actions'];
  data: Contato[] = [];
  resultsLength = 0;
  isLoadingResults = true;

  constructor(
    private contatoService: ContatoService,
    private matDialog: MatDialog,
    private router: Router,
    private utilService: UtilService
  ) { }

  ngOnInit() {
    this.reload();
  }

  reload() {
    this.getFilteredRecords({
      pageIndex: 0,
      pageSize: 10
    });
  }

  onPageChange(event) {
    this.getFilteredRecords(event);
  }

  getFilteredRecords(event) {
    this.isLoadingResults = true;
    const params = {
      page: event.pageIndex,
      size: event.pageSize
    };

    this.contatoService.get(params).subscribe(response => {
      this.isLoadingResults = false;
      this.resultsLength = response.totalElements;
      this.data = response.content;
    });
  }

  onCreationBtnClick() {
    this.router.navigateByUrl('/contatoForm', {
      state: {
        formState: FormState.CREATION
      }
    });
  }

  onEditionBtnClick(row: Contato) {
    this.router.navigateByUrl('/contatoForm', {
      state: {
        formState: FormState.EDITION,
        contato: row
      }
    });
  }

  onDeletionBtnClick(contato: Contato) {
    const dialogRef = this.matDialog.open(ConfirmationDialogComponent, {
      width: '300px',
      data: {
        text: 'Apagar contato?',
        yesText: 'Apagar',
        noText: 'Cancelar'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result)
        this.contatoService.delete(contato.id).subscribe(
          response => {
            this.utilService.showSnackBar('O contato foi apagado.');
            this.reload();
          },
          response => this.utilService.showSnackBar(response.error.messages[0].message, 4000)
        );
    });
  }
}
