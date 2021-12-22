import { Component, OnInit } from '@angular/core';
import {Producto} from "./producto";
import {Router} from "@angular/router";
import {ProductoService} from "./producto.service";

//
import swal from "sweetalert2";

@Component({
  selector: 'app-form-prod',
  templateUrl: './form-prod.component.html',
  styleUrls: ['./form-prod.component.css']
})
export class FormProdComponent implements OnInit {

  public producto:Producto = new Producto()
  public titulo: string ="Creat Producto"
  constructor(private router: Router, private  servicio: ProductoService) { }

  ngOnInit(): void {
  }

  create(producto:Producto){
    this.servicio.createProducto(this.producto).subscribe(
      response => this.router.navigate(['/productos'])
    )
  }/*


  create():void{
    this.servicio.createProducto(this.producto)
    .subscribe(producto => {
        this.router.navigate(['/productos'])
        swal('Nueva Producto', `El Producto ${this.producto.nombre}, ha diso creado con exito`, 'success')
    }
    );
  }*/
  update():void{
    this.servicio.update(this.producto)
      .subscribe(producto =>{
      this.router.navigate(['/productos'])
      swal('Producto Actualizado', `El Producto  ${producto.nombre} ha sido actualizado`, 'success')
    })
  }
}
