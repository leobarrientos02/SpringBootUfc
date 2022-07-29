import { TestBed } from '@angular/core/testing';

import { FrontEndFunctionsService } from './front-end-functions.service';

describe('FrontEndFunctionsService', () => {
  let service: FrontEndFunctionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FrontEndFunctionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
