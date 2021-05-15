import {
  Dermatologist,
  Pharmacy,
} from '../user-complaint/user-complaint.component';
import { Medicine } from './medicine';

export class Employment {
  start!: Number;
  end!: Number;
  pharmacist!: Medicine;
  dermatologist!: Dermatologist;
  pharmacy!: Pharmacy;
}
