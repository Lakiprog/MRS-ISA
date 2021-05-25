import { Pharmacy } from '../user-complaint/user-complaint.component';

export class Appointment {
  start!: Date;
  end!: Date;
  price!: Number;
  discount!: Number;
  done!: Boolean;
  pharmacy!: Pharmacy;
}
