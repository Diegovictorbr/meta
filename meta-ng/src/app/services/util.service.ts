import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(
    private snackBar: MatSnackBar
  ) { }

  // Retorna o nome de um dia da semana por extenso
  getDayName = (date: Date, countryCode = 'pt-BR') => {
    const dayName = date.toLocaleDateString(countryCode, { weekday: 'long' });
    return `${dayName[0].toUpperCase()}${dayName.substring(1)}`;
  }

  // Retorna o nome do mês por extenso
  getMonthName = (date: Date, countryCode: string = 'pt-BR') => {
    const monthName = date.toLocaleDateString(countryCode, { month: 'long' });
    return `${monthName[0].toUpperCase()}${monthName.substring(1)}`;
  }

  // Retorna os nomes de todos os dias da semana por extenso. A semana de referência é a última de 11/2019
  getDaysNames = (countryCode: string = 'pt-BR'): string[] =>
    [25, 26, 27, 28, 29, 30, 31].map(day => new Date(2019, 10, day).toLocaleDateString(countryCode, { weekday: 'long' }))

  // Retorna a quantidade de dias contidos num mês
  getDaysInMonth = (year = 2019, month: number): number => new Date(year, month + 1, 0).getDate()

  showSnackBar(text: string, duration = 2000, actionText = 'Fechar'): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(text, actionText, {
      duration
    });
  }

  // Envia um arquivo para download no browser
  download(files: any, fileName: string) {
    const blob = new Blob(files, { type: 'application/octet-stream' });
    if (window.navigator.msSaveOrOpenBlob)
      window.navigator.msSaveBlob(blob, fileName);
    else {
      const a = window.document.createElement('a');
      a.href = window.URL.createObjectURL(blob);
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    }
  }

  // Troca as chaves de um JSON de acordo com os mapeamentos na variável translation. Opcionalmente, remove do objeto as chaves que não foram traduzidas
  translateKeys(translations: { translatedName: string, key: string }[], records: any[], removeUntranslated = false) {
    records.forEach(record => Object.keys(record).forEach(key => {
      const translation = translations.find(translation => translation.key == key);
      if (translation) {
        record[translation.translatedName] = record[key];
        delete record[key];
      } else if (removeUntranslated)
        delete record[key];
    }));
  }

  compareFn = (a: any, b: any) => ((a && b) && (a.id === b.id)) || a == b
}
