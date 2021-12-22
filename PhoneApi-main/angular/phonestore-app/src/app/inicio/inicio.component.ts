import { Component, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import { ProductoService } from '../productos/producto.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  constructor(private productoService: ProductoService) { }
  public producto: Producto[];

  ngOnInit(): void {
    this.productoService.getProductos().subscribe(
      (producto) => this.producto = producto
    );
  }

}
