import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatformacctsetComponent } from './platformacctset.component';

describe('PlatformacctsetComponent', () => {
  let component: PlatformacctsetComponent;
  let fixture: ComponentFixture<PlatformacctsetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlatformacctsetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlatformacctsetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
