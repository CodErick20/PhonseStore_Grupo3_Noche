import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Carrito} from "./carrito";


@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private urlEndPoint: string = 'http://localhost:8080/carrito';
  constructor(private http: HttpClient) { }

  getCarrito(): Observable<Carrito[]>{
    return this.http.get<Carrito[]>(this.urlEndPoint).pipe(
      map((response) => response as Carrito[])
    )
  }
}
