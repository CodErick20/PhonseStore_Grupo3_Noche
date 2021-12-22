import { Component, OnInit } from '@angular/core';
import {Carrito} from "./carrito";
import {CarritoService} from "./carrito.service";

@Component({
  selector: 'app-carritos',
  templateUrl: './carritos.component.html',
  styleUrls: ['./carritos.component.css']
})
export class CarritosComponent implements OnInit {

  carrito: Carrito[] = [];
  constructor(private carritoServicio: CarritoService) { }

  ngOnInit(): void {
    this.carritoServicio.getCarrito().subscribe(
      (carrito) => this.carrito = carrito
    );
  }

}
