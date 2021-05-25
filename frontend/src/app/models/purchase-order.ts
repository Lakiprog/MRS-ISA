import { Pharmacy } from '../user-complaint/user-complaint.component';
import { PurchaseOrderMedicine } from './purchase-order-medicine';
import { PurchaseOrderSupplier } from './purchase-order-supplier';

export class PurchaseOrder {
  pharmacy!: Pharmacy;
  purchaseOrderName!: String;
  purchaseOrderMedicine!: PurchaseOrderMedicine[];
  purchaseOrderSupplier!: PurchaseOrderSupplier;
}
