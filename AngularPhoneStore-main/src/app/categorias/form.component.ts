import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {Categoria} from "./categoria";
import { CategoriaService } from './categoria.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  public categoria:Categoria = new Categoria()
  public titulo: string = "Crear Categoria"
/* contiene un arreglo de mensajes de errores */
  public errores: string[];

  constructor(private router: Router, private service: CategoriaService,
              private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCategoria()
  }
/*obtener*/
  cargarCategoria(): void{
    this.activeRoute.params.subscribe(params =>{
      let id = params['id']
      if (id){
        this.service.getCategoria(id).subscribe((categoria) => this.categoria = categoria)
      }
    })
  }

  create():void{
    this.service.createCategoria(this.categoria)
    .subscribe(categoria => {
        this.router.navigate(['/categorias'])
        swal('Nueva Categoria', `La categoria ${categoria.nombre}, ha diso creado con exito`, 'success')
    },
    /* obtenemos el error */
    /* argumento err */
    err => {
      /* pasamos los errores al atributo errores[] */
      /* usamos el argumento err, error, es el atributo del objeto error que contiene el json y se lo pasamos los errores dentro del parametro erros */
      this.errores = err.error.errors  as string[];
      /* muestrea el status*/
      console.log('Codigo del error desde el backend: ' + err.status);
      /* muestra los mensajes de errores en la consola del navegador */
      console.log(err.error.errors)
    }
    );
  }

  update():void{
    this.service.update(this.categoria)
      .subscribe(categoria =>{
      this.router.navigate(['/categorias'])
      swal('Categoria Actualizada', `La categoria  ${categoria.nombre} ha sido actualizada`, 'success')
    },
    /* obtenemos el error */
    /* argumento err */
    err => {
      /* pasamos los errores al atributo errores[] */
      /* usamos el argumento err, error, es el atributo del objeto error que contiene el json y se lo pasamos los errores dentro del parametro erros */
      this.errores = err.error.errors  as string[];
      /* muestrea el status*/
      console.log('Codigo del error desde el backend: ' + err.status);
      /* muestra los mensajes de errores en la consola del navegador */
      console.log(err.error.errors)
    }
    );
  }
}
