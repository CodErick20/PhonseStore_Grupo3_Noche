import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html'
  // styleUrls: ['./directiva.component.css']
})
export class DirectivaComponent implements OnInit {

  listaCurso: string[] = ['TypeScripty', 'JavaScript', 'Java SE', 'C#', 'PHP'];
  constructor() { }



  habilitar: boolean = true;
  ngOnInit(): void {
  }

  setHabilitar(): void{
  this.habilitar = (this.habilitar==true)?false:true;
}

}
