import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TempeditComponent } from './tempedit.component';

describe('TempeditComponent', () => {
  let component: TempeditComponent;
  let fixture: ComponentFixture<TempeditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TempeditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TempeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
