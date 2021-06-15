import { PurchaseOrder } from './purchase-order';
import { Supplier } from './supplier';

export class PurchaseOrderSupplier {
  deliveryDate!: Date;
  price!: Number;
  purchaseOrder!: PurchaseOrder;
  supplier!: Supplier;
  offerStatus!: string;
  isAfterToday!: boolean;
}
