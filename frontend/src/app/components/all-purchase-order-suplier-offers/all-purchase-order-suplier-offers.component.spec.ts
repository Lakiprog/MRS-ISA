import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPurchaseOrderSuplierOffersComponent } from './all-purchase-order-suplier-offers.component';

describe('AllPurchaseOrderSuplierOffersComponent', () => {
  let component: AllPurchaseOrderSuplierOffersComponent;
  let fixture: ComponentFixture<AllPurchaseOrderSuplierOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllPurchaseOrderSuplierOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPurchaseOrderSuplierOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
