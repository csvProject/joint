import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldeidComponent } from './fieldeid.component';

describe('FieldeidComponent', () => {
  let component: FieldeidComponent;
  let fixture: ComponentFixture<FieldeidComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FieldeidComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldeidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
