import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMedicineByIdComponent } from './search-medicine-by-id.component';

describe('SearchMedicineByIdComponent', () => {
  let component: SearchMedicineByIdComponent;
  let fixture: ComponentFixture<SearchMedicineByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchMedicineByIdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMedicineByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
