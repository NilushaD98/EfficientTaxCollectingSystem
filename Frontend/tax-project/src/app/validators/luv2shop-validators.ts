// luv2shop-validators.ts
import { FormControl, ValidationErrors } from "@angular/forms";

export class Luv2shopValidators {
  static notOnlyWhiteSpace(control: FormControl): ValidationErrors | null {
    if (control.value != null && control.value.trim().length === 0) {
      return { 'notOnlyWhiteSpace': true };
    } else {
      return null;
    }
  }
}
