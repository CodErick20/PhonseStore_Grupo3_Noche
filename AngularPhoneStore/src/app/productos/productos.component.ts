import { Component, OnInit } from '@angular/core';
import {Producto} from "./producto";
import {ProductoService} from "./producto.service";

import swal  from 'sweetalert2';


@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  producto: Producto[] =[];
  constructor(private productoServicio: ProductoService ) {

}

  ngOnInit(): void {
    this.productoServicio.getProductos().subscribe(
      (producto) => this.producto = producto
    );
  }


  delete(producto: Producto): void {
    swal({
      title: 'Esta Seguro?',
      text: `Seguro que desea eliminar el producto ${producto.nombre}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: 'No, cancelar!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.productoServicio.delete(producto.id_producto).subscribe(
          response => {
            this.producto = this.producto.filter(cli => cli !== producto)
            swal(
              'Producto Eliminado!',
              `El Producto ${producto.nombre} eliminado con exito`,
              'success'
            )
          }
        )
      }
    })
  }

}


  