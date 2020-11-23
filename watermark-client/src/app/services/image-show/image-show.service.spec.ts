import { TestBed } from '@angular/core/testing';

import { ImageShowService } from './image-show.service';

describe('ImageShowService', () => {
  let service: ImageShowService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageShowService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
