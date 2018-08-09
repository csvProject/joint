import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplatesetComponent } from './templateset.component';

describe('TemplatesetComponent', () => {
  let component: TemplatesetComponent;
  let fixture: ComponentFixture<TemplatesetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemplatesetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplatesetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
