import { AbstractControl } from "@angular/forms";

export function DateValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const date = control.get('deliveryTime');
    const currentDate = new Date();
    if (new Date(date?.value) <= currentDate) {
        return {'dateInvalid': true}
    } else {
        return null;
    }
}