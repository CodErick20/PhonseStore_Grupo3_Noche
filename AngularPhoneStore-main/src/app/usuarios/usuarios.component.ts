import { Component, OnInit } from '@angular/core';
import {Usuario} from "./usuario";
import {UsuarioService} from "./usuario.service";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  usuario: Usuario[] = [];
  constructor(private usuarioServicio: UsuarioService) { }

  ngOnInit(): void {
    this.usuarioServicio.getUsuarios().subscribe(
      (usuario) => this.usuario = usuario
    )
  }

}
