import { Component, OnInit } from '@angular/core';
import {Producto} from "./producto";
import {Router} from "@angular/router";
import {ProductoService} from "./producto.service";
import swal from 'sweetalert2';

@Component({
  selector: 'app-form-prod',
  templateUrl: './form-prod.component.html',
  styleUrls: ['./form-prod.component.css']
})
export class FormProdComponent implements OnInit {

  public producto:Producto = new Producto()
  public titulo: string ="Crear Producto"
  constructor(private router: Router, private  servicio: ProductoService) { }

  ngOnInit(): void {
  }

  create():void{
    this.servicio.createProducto(this.producto)
    .subscribe(producto => {
        this.router.navigate(['/productos'])
        swal('Nueva Producto', `El Producto ${producto.nombre}, ha diso creado con exito`, 'success')
    }
    );
  }
}
