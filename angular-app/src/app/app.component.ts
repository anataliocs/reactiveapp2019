import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';

  constructor(private http: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080';
  private postUrl: string = this.baseUrl + '/room/v1/reservation/';
  public submitted: boolean;

  roomsearch: FormGroup;
  rooms: Room[];
  request: ReserveRoomRequest;
  currentCheckInVal: string;
  currentCheckOutVal: string;

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

    this.rooms = [new Room("123", "123", "150", ""),
      new Room("127", "127", "175", ""),
      new Room("138", "138", "175", ""),
    ]
  }

  reserveRoom(value: string) {

    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

    this.createReservation(this.request);
  }


  createReservation(body: Object) {
    let bodyString = JSON.stringify(body); // Stringify payload
    let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON

    this.http.post(this.postUrl, body)
      .subscribe(res => console.log(res));
  }

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

export class Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;


  constructor(id: string, roomNumber: string, price: string, links: string) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.price = price;
    this.links = links;
  }
}
