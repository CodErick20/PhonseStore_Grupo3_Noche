import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html'
  // styleUrls: ['./directiva.component.css']
})
export class DirectivaComponent implements OnInit {

  listaCurso: string[] = ['Henry', 'Erick', 'Jaime', 'Ingrid', 'Jonathan', 'Genesis'];
  constructor() { }



  habilitar: boolean = true;
  ngOnInit(): void {
  }

  setHabilitar(): void{
  this.habilitar = (this.habilitar==true)?false:true;
}

}
