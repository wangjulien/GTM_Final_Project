import { TestBed, inject } from '@angular/core/testing';

import { ConseillerClientService } from './conseiller-client.service';

describe('ConseillerClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConseillerClientService]
    });
  });

  it('should be created', inject([ConseillerClientService], (service: ConseillerClientService) => {
    expect(service).toBeTruthy();
  }));
});
