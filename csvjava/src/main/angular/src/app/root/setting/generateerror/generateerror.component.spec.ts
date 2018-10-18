import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateerrorComponent } from './generateerror.component';

describe('GenerateerrorComponent', () => {
  let component: GenerateerrorComponent;
  let fixture: ComponentFixture<GenerateerrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenerateerrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateerrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
