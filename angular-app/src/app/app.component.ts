import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Reservation, ReservationService, ReservationRequest} from "./reservation.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';

  constructor(private  reservationService: ReservationService) {
  }

  roomsearch: FormGroup;
  rooms: Room[];
  request: ReservationRequest;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentRoomNumber: number;
  currentPrice: number;
  currentReservations: Reservation[];

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl(''),
      roomNumber: new FormControl(null),
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(form => {
      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;

      if (form.roomNumber) {
        let roomVals: string[] = form.roomNumber.split("|");
        this.currentRoomNumber = Number(roomVals[0]);
        this.currentPrice = Number(roomVals[1]);
      }
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

  createReservation() {
    this.request = new ReservationRequest(this.currentRoomNumber, this.currentPrice,
      this.currentCheckInVal, this.currentCheckOutVal);
    this.reservationService.createReservation(this.request)
      .subscribe(postResult => {
        console.log(postResult);
        this.getCurrentReservations();
      });
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


