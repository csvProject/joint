import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldeditComponent } from './fieldedit.component';

describe('FieldeditComponent', () => {
  let component: FieldeditComponent;
  let fixture: ComponentFixture<FieldeditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FieldeditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
