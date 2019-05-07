import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Reservation, ReservationService, ReserveRoomRequest} from "./reservation.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';

  constructor(private  reservationService: ReservationService) {
  }

  private baseUrl: string = 'http://localhost:8080';
  private postUrl: string = this.baseUrl + '/room/v1/reservation/';
  public submitted: boolean;

  roomsearch: FormGroup;
  rooms: Room[];
  request: ReserveRoomRequest;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentRoomNumber: string;
  currentReservations: Reservation[];

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(form => {
      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;
      this.currentRoomNumber = form.roomNumber;
    });

    this.rooms = [new Room("123", "123", "150"),
      new Room("127", "127", "175"),
      new Room("138", "138", "175"),
    ]

    this.getCurrentReservations();
  }

  getCurrentReservations() {
    this.reservationService.getReservations()
      .subscribe(result => {
          console.log(result);
          this.currentReservations = result;
        }
      );
  }

  reserveRoom() {

    this.request = new ReserveRoomRequest(this.currentRoomNumber, this.currentCheckInVal, this.currentCheckOutVal);
    this.reservationService.createReservation(this.request);
    this.getCurrentReservations();
  }
}

export class Room {
  id: string;
  roomNumber: string;
  price: string;


  constructor(id: string, roomNumber: string, price: string) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.price = price;
  }
}


