import { Component, OnInit } from '@angular/core';
import {CsvTemplateDetail} from "../../../../entity/tempData";

@Component({
  selector: 'app-fieldedit',
  templateUrl: './fieldedit.component.html',
  styleUrls: ['./fieldedit.component.css']
})
export class FieldEditComponent implements OnInit {
  selectFielDetail:CsvTemplateDetail;

  constructor() { }

  ngOnInit() {
  }
  itemObjectsLeft: any[] = [
    { id: 1, name: 'Windstorm' },
    { id: 2, name: 'Bombasto' },
    { id: 3, name: 'Magneta' }
  ];

  onChangeSorTable(ev){
    console.log(ev);
  }

  deleteField(data){

  }

  isConfirmLoading = false;
  isVisible = false;
  handleCancel(): void {
    this.isVisible = false;

  }
  handleOk(): void {

  }
  showModel(csvTemplateDetail :CsvTemplateDetail){
    console.log(csvTemplateDetail);
    this.isVisible = true;
  }
}
