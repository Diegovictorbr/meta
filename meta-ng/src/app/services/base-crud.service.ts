import { HttpClient, HttpParams } from '@angular/common/http';
import { Inject, Injectable, InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export const PRIMITIVE_PARAMETER = new InjectionToken<any>(null);
export const CALLBACK_PARAMETER = new InjectionToken<any>(null);

@Injectable({
  providedIn: 'root'
})
export abstract class BaseCrudService<M> {

  public apiUrl: string;
  // Funçãp que tipa o content de DataResponse para M, se passada como argumento no construtor
  public deserializerFunction: (json) => M;

  constructor(
    protected httpClient: HttpClient,
    @Inject(PRIMITIVE_PARAMETER) apiUrl: string,
    @Inject(CALLBACK_PARAMETER) deserializerFunction?
  ) {
    this.apiUrl = apiUrl;
    this.deserializerFunction = deserializerFunction;
  }

  get(pageable?, customParams?): Observable<any> {
    let httpParams = new HttpParams();

    if (customParams)
      Object.keys(customParams).forEach(key => {
        httpParams = httpParams.set(key, customParams[key]);
      });

    if (pageable && pageable.page)
      httpParams = httpParams.set('page', pageable.page);

    if (pageable && pageable.size)
      httpParams = httpParams.set('size', pageable.size);

    if (pageable && pageable.fetchAll)
      httpParams = httpParams.set('fetchAll', pageable.fetchAll);

    // Resposta paginada
    let observable = this.httpClient.get<any>(this.apiUrl, {
      params: httpParams
    });

    if (this.deserializerFunction)
      observable = observable.pipe(map(response => {
        response.content = response.content.map(this.deserializerFunction);
        return response;
      }));

    return observable;
  }

  getById(id: any): Observable<M> {
    return this.pipeFn(this.httpClient.get<M>(`${this.apiUrl}/${id}`));
  }

  post(body: M): Observable<any> {
    return this.pipeFn(this.httpClient.post(`${this.apiUrl}`, body));
  }

  put(body: M): Observable<any> {
    return this.pipeFn(this.httpClient.put(`${this.apiUrl}/${(<any>body).id}`, body));
  }

  delete(id: any): Observable<any> {
    return this.pipeFn(this.httpClient.delete(`${this.apiUrl}/${id}`));
  }

  changeActiveState(id: number, activeState): Observable<any> {
    return this.httpClient.patch(`${this.apiUrl}/${id}`, null, {
      params: new HttpParams().set('activeState', activeState)
    });
  }

  protected pipeFn(observable) {
    if (this.deserializerFunction)
      observable = observable.pipe(map(this.deserializerFunction));

    return observable;
  }
}
