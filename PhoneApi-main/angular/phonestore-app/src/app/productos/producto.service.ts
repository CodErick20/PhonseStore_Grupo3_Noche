import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";

import {Observable, map, catchError, throwError} from "rxjs";
import {Producto} from "./producto";
import swal from 'sweetalert2';
import { formatDate, registerLocaleData } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPoint: string = 'http://localhost:8080/producto';

  private  httpHeader = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient, private router: Router) { }

  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.urlEndPoint).pipe(
      map(response => {
/* formatear fecha */
        let producto = response as Producto[];
        return producto.map(producto => {

          producto.fecha_Ingreso = formatDate(producto.fecha_Ingreso, 'EEEE dd, MMMM yyyy', 'es');
          return producto;
        })
      }
      )
    );
  }

  getProducto(id:number): Observable<Producto> {
    return this.http.get<Producto>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/productos']);
        console.error(e.error.mensaje);
        swal('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  createProducto(producto:Producto): Observable<Producto>{
    return this.http.post(this.urlEndPoint,producto, {headers: this.httpHeader}).pipe(
      map((response: any) => response.producto as Producto),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  subirFoto(archivo: File, id:any): Observable<Producto>{
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);
    return this.http.post(`${this.urlEndPoint}/upload`, formData).pipe(
      map((response: any) => response.producto as Producto),
      catchError(e =>{
        console.error(e.error.mensaje)
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    )
  }

  // subirFoto(archivo: File, id:any): Observable<HttpEvent<{}>> {

  //   let formData = new FormData();
  //   formData.append("archivo", archivo);
  //   formData.append("id", id);

  //   const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`, formData, {
  //     reportProgress: true
  //   });

  //   return this.http.request(req);

  // }
}
