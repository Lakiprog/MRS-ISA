import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMedicinePricePopupComponentComponent } from './update-medicine-price-popup-component.component';

describe('UpdateMedicinePricePopupComponentComponent', () => {
  let component: UpdateMedicinePricePopupComponentComponent;
  let fixture: ComponentFixture<UpdateMedicinePricePopupComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateMedicinePricePopupComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMedicinePricePopupComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
