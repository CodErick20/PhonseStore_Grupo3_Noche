import { BuiltinTypeName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import swal  from 'sweetalert2';
import {Categoria} from "./categoria";
import {CategoriaService} from "./categoria.service";


@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categoria: Categoria[] = [];
  constructor(private categoriaServicio: CategoriaService) { }

  ngOnInit(): void {
    this.categoriaServicio.getCategorias().subscribe(
      (categoria) =>this.categoria = categoria
    )
  }

  delete(categoria: Categoria): void {
    swal({
      title: 'Esta Seguro?',
      text: `Seguro que desea eliminar la categirua ${categoria.nombre}?`,
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
        this.categoriaServicio.delete(categoria.id_categoria).subscribe(
          response => {
            this.categoria = this.categoria.filter(cli => cli !== categoria)
            swal(
              'Categoria Eliminado!',
              `La Categoria ${categoria.nombre} eliminado con exito`,
              'success'
            )
          }
        )
      }
    })
  }
}







