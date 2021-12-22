import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
//
import {Observable, map, catchError, throwError} from "rxjs";
import {Producto} from "./producto";
//
import swal from "sweetalert2";
import {Router} from "@angular/router";




@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPoint: string = 'http://localhost:8080/producto';

  private  httpHeader = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient, private router:Router) { }

  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.urlEndPoint).pipe(
      map((response) => response as Producto[])
    )
  }
  

  createProducto(producto: Producto): Observable<Producto>{
    return this.http.post<Producto>(this.urlEndPoint, producto, {headers: this.httpHeader});
  }
/*
  createProducto2(producto:Producto): Observable<Producto>{
    return this.http.post(this.urlEndPoint,producto, {headers: this.httpHeader}).pipe(
      map((response: any) => response.categoria as Producto),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }*/

  /*actualizar*/
  update(producto:Producto): Observable<Producto>{
    return this.http.put(`${this.urlEndPoint}/${producto.id_producto}`, producto, {headers: this.httpHeader}).pipe(
      map((response:any) => response.producto as Producto),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }
  delete(id:number): Observable<Producto>{
    return this.http.delete(`${this.urlEndPoint}/${id}`, {headers: this.httpHeader}).pipe(
      map((response:any) => response.producto as Producto),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }


/*obtener*/
getCategoria(id:number): Observable<Producto>{
  return this.http.get<Producto>(`${this.urlEndPoint}/${id}`).pipe(
    catchError(e => {
      this.router.navigate(['/productos'])
      console.error(e.error.mensaje);
      swal('Error al editar el producto', e.error.mensaje, 'error');
      return throwError(e);
    })
  )
}


}

