import { HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { empty } from 'rxjs';
import swal from 'sweetalert2';
import { Producto } from '../producto';

import { ProductoService } from '../producto.service';

@Component({
  selector: 'detalle-producto',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {
  // producto: Producto;
  producto: Producto;
  // producto1: Producto[];
  progreso: number = 0;

  public titulo: string = "Detalle del Producto"
  public fotoSeleccionada: File;

  constructor(private productoServicio: ProductoService,

    private activeRoute: ActivatedRoute) { }

  ngOnInit() {

    this.activeRoute.paramMap.subscribe(params => {
      let id: any = params.get('id');
      if (id) {
        this.productoServicio.getProducto(id).subscribe(productos => {
          this.producto = productos;
        });
      }
    });
  }

  seleccionarFoto(event: any) {
    this.fotoSeleccionada = event.target.files[0];
    console.log(this.fotoSeleccionada);
    /* buscar coisidencias */
    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      swal('Error seleccionar imagen: ', 'El archivo debe ser del tipo imagen', 'error' );
      this.fotoSeleccionada == null;
    }
  }

  subirFoto() {
    if (!this.fotoSeleccionada) {
      swal('Error: Debe seleccionar una foto')
    } else {
      this.productoServicio.subirFoto(this.fotoSeleccionada, this.producto.id_producto)
        .subscribe(producto => {
          this.producto = producto;
          swal('La foto se ha cargado con éxito!', `foto:  ${this.producto.url_imagen}`);
        }
        );
    }

  }

  // subirFoto() {

  //   if (!this.fotoSeleccionada) {
  //     swal('Error Upload: ', 'Debe seleccionar una foto', 'error');
  //   } else {
  //     this.productoServicio.subirFoto(this.fotoSeleccionada, this.producto.id_producto)
  //       .subscribe(event => {
  //         if (event.type === HttpEventType.UploadProgress) {
  //           this.progreso = Math.round((event.loaded / event.total) * 100);
  //         } else if (event.type === HttpEventType.Response) {
  //           let response: any = event.body;
  //           this.producto = response.cliente as Producto;
  //           swal('La foto se ha subido completamente!', response.mensaje, 'success');
  //         }
  //       });
  //   }
  // }
}
