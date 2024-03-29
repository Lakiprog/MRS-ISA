import { Pharmacy } from '../user-complaint/user-complaint.component';
import { PharmacyAdmin } from './pharmacy-admin';
import { PurchaseOrderMedicine } from './purchase-order-medicine';
import { PurchaseOrderSupplier } from './purchase-order-supplier';

export class PurchaseOrder {
  id!: Number;
  pharmacy!: Pharmacy;
  purchaseOrderName!: String;
  purchaseOrderDate!: Date;
  dueDateOffer!: Date;
  purchaseOrderMedicine!: PurchaseOrderMedicine[];
  purchaseOrderSupplier!: PurchaseOrderSupplier;
  pharmacyAdmin!: PharmacyAdmin;
}
