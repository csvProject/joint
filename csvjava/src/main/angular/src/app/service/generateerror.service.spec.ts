import { TestBed, inject } from '@angular/core/testing';

import { GenerateerrorService } from './generateerror.service';

describe('GenerateerrorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GenerateerrorService]
    });
  });

  it('should be created', inject([GenerateerrorService], (service: GenerateerrorService) => {
    expect(service).toBeTruthy();
  }));
});
