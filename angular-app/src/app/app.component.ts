import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';

  constructor(private http: HttpClient) {
  }

  private baseUrl:string = 'http://localhost:8080';
  private getUrl:string = this.baseUrl + '/room/v1/reservation/';
  private postUrl:string = this.baseUrl + '/room/v1/reservation/';
  public submitted:boolean;

  roomsearch:FormGroup;
  rooms:Room[];
  request:ReserveRoomRequest;
  currentCheckInVal:string;
  currentCheckOutVal:string;

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });
  }

  onSubmit({value, valid}: { value:RoomSearch, valid:boolean }) {


  }

  reserveRoom(value:string) {

    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

    this.createReservation(this.request);
  }


  createReservation(body:Object) {
    let bodyString = JSON.stringify(body); // Stringify payload
    let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON

    this.http.post(this.postUrl, body)
      .subscribe(res => console.log(res));
  }

  mapRoom(response:Response):Room[] {

    //TODO Fix
    return null;
  }

}

export interface RoomSearch {
  checkin:string;
  checkout:string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string,
              checkin: string,
              checkout: string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

export interface Room {
  id:string,
  roomNumber:string,
  price:string,
  links:string

}
