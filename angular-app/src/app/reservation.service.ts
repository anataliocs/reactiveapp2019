import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080';
  private postUrl: string = this.baseUrl + '/room/v1/reservation/';

  getReservations() {
    let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON

    return this.http.get<Reservation[]>(this.postUrl);
  }

  createReservation(body: Object) {
    let bodyString = JSON.stringify(body); // Stringify payload
    let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON

    this.http.post<Reservation>(this.postUrl, body)
      .subscribe(res => {
        console.log(res);
        console.log(res.price);

      });
  }

}

export class ReserveRoomRequest {
  roomNumber: number;
  checkin: string;
  checkout: string;
  price: number;

  constructor(roomNumber: number,
              price: number,
              checkin: string,
              checkout: string) {

    this.roomNumber = roomNumber;
    this.price = price;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

export interface Reservation {
  id: string;
  roomNumber: number;
  checkin: Date;
  checkout: Date;
  price: number;
}
