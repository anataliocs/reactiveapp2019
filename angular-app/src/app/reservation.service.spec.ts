import {TestBed} from '@angular/core/testing';

import {ReservationService} from './reservation.service';
import {HttpClientModule} from '@angular/common/http';

describe('ReservationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ]
    });
  });

  it('should be created', () => {
    const service: ReservationService = TestBed.get(ReservationService);
    expect(service).toBeTruthy();
  });
});
