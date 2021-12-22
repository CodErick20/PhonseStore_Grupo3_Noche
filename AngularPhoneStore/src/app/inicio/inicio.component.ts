import { Component, OnInit } from '@angular/core';
///
import {Producto} from "../productos/producto";
import {ProductoService} from "../productos/producto.service";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
/*
  constructor() { }

  ngOnInit(): void {
  }
*/
producto: Producto[] =[];
constructor(private productoServicio: ProductoService ) {

}
  ngOnInit(): void {
    this.productoServicio.getProductos().subscribe(
      (producto) => this.producto = producto
    );
   
  }

  
}
