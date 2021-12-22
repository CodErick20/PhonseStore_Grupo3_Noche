import {Producto} from "../productos/producto";
import {Usuario} from "../usuarios/usuario";

export class Carrito {
  id_carrito:number;
  cantidad: number;
  producto: Producto[]
  usuario: Usuario[]
}
