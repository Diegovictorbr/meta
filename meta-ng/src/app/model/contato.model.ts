export class Contato {
  id: string;
  nome: string;
  tipoCanal: string;
  valor: string;
  obs: number;

  constructor(json) {
    Object.assign(this, json);
  }
}
