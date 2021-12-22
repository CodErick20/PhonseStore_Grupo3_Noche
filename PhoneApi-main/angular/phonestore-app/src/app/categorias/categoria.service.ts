import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, map, catchError, throwError} from "rxjs";
import {Categoria} from "./categoria";
import swal from "sweetalert2";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private urlEndPoint: string = 'http://localhost:8080/categoria';

  /*esto envia a la lista de categorias depues que si inserto una categoria*/
  private httpHeader = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient, private router:Router) { }

  getCategorias(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>(this.urlEndPoint).pipe(
      map((response) => response as Categoria[]));
  }

  createCategoria(categoria:Categoria): Observable<Categoria>{
    return this.http.post(this.urlEndPoint,categoria, {headers: this.httpHeader}).pipe(
      map((response: any) => response.categoria as Categoria),
      catchError(e =>{
        /* error 400 viene de la validacion del backend */
        /* el objeto e(error) tiene el atrubuto con la respuesta */
        if(e.status == 400){
          /* retorna al component para que controle el  error */
          return throwError(e);
        }
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }
/*actualizar*/
  update(categoria:Categoria): Observable<Categoria>{
    return this.http.put(`${this.urlEndPoint}/${categoria.id_categoria}`, categoria, {headers: this.httpHeader}).pipe(
      map((response:any) => response.categoria as Categoria),
      catchError(e =>{
          /* error 4oo viene de la validacion del backend */
          if(e.status == 400){
            /* retorna al component para que controle el  error */
            return throwError(e);
          }
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }

  delete(id:number): Observable<Categoria>{
    return this.http.delete(`${this.urlEndPoint}/${id}`, {headers: this.httpHeader}).pipe(
      map((response:any) => response.categoria as Categoria),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }

/*obtener*/
  getCategoria(id:number): Observable<Categoria>{
    return this.http.get<Categoria>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/categorias'])
        console.error(e.error.mensaje);
        swal('Error al editar la categoria', e.error.mensaje, 'error');
        return throwError(e);
      })
    )
  }
}

