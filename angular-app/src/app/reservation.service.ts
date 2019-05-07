import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080';
  private postUrl: string = this.baseUrl + '/room/v1/reservation/';

  getReservations(): Observable<Reservation[]> {

    return this.http.get<Reservation[]>(this.postUrl);
  }

  createReservation(body: ReservationRequest): Observable<Reservation> {
    let httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    return this.http.post<Reservation>(this.postUrl, body, httpOptions);
  }
}

export class ReservationRequest {
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
