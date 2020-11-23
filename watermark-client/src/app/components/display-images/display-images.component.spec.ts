import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayImagesComponent } from './display-images.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';


describe('DisplayImagesComponent', () => {
  let component: DisplayImagesComponent;
  let fixture: ComponentFixture<DisplayImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayImagesComponent ],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
