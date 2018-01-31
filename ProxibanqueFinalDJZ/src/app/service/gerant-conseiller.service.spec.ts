import { TestBed, inject } from '@angular/core/testing';

import { GerantConseillerService } from './gerant-conseiller.service';

describe('GerantConseillerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GerantConseillerService]
    });
  });

  it('should be created', inject([GerantConseillerService], (service: GerantConseillerService) => {
    expect(service).toBeTruthy();
  }));
});
