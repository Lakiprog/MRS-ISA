import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMedicinePharmacyPriceComponent } from './edit-medicine-pharmacy-price.component';

describe('EditMedicinePharmacyPriceComponent', () => {
  let component: EditMedicinePharmacyPriceComponent;
  let fixture: ComponentFixture<EditMedicinePharmacyPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditMedicinePharmacyPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMedicinePharmacyPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
