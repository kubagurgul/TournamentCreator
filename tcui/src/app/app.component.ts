import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.httpClient.get("http://localhost:4200/api/tournaments").toPromise().then( x => {
      console.log(x);
    });
  }


  title = 'app';
}
