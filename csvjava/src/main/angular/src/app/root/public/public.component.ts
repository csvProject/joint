import { Component, OnInit } from '@angular/core';
import {PublicService} from "../../http/public.service";

@Component({
  selector: 'app-public',
  templateUrl: './public.component.html',
  styleUrls: ['./public.component.css']
})
export class PublicComponent implements OnInit {

  constructor(private service:PublicService) { }

  ngOnInit() {
  }

}
