import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseOrderSupplierOffersComponent } from './purchase-order-supplier-offers.component';

describe('PurchaseOrderSupplierOffersComponent', () => {
  let component: PurchaseOrderSupplierOffersComponent;
  let fixture: ComponentFixture<PurchaseOrderSupplierOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseOrderSupplierOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseOrderSupplierOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
