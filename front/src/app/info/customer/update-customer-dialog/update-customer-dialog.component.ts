import {Component, computed, Inject, inject, signal, WritableSignal} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatChipGrid, MatChipInput, MatChipRemove, MatChipRow} from '@angular/material/chips';
import {MatIcon, MatIconRegistry} from '@angular/material/icon';
import {CustomerService} from '../../../_services/customer.service';
import {PhoneService} from '../../../_services/phone.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Customer} from '../../../_models/customer';

@Component({
  standalone: true,
  selector: 'app-update-customer-dialog',
  templateUrl: './update-customer-dialog.component.html',
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogContent,
    MatDialogActions,
    MatDialogTitle,
    MatSelectModule,
    MatChipInput,
    MatChipGrid,
    MatChipRow,
    MatIcon,
    MatChipRemove
  ],
  styles: `
    .name {width: 30%; margin-right: 3%;}
    .phones, .address { width: 96%;}
  `
})
export class UpdateCustomerDialogComponent {
  private fb = inject(FormBuilder);
  private customerService = inject(CustomerService);
  private phoneService = inject(PhoneService);
  private snackBar = inject(MatSnackBar);

  customer: WritableSignal<Partial<Customer>> = signal({});
  phones = signal<string[]>([]);

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { customer: string },
    public dialogRef: MatDialogRef<UpdateCustomerDialogComponent>
  ) {
    this.customerService.getCustomerByKey(this.data.customer).subscribe(data => {
      this.customer.set(data);
      this.phones.set(data.phones);
    });
  }

  form = computed(() => this.fb.group({
    name: [this.customer().name, Validators.required],
    surname: [this.customer().surname, Validators.required],
    patronymic: [this.customer().patronymic],
    address: [this.customer().address, Validators.required],
    phones: [this.customer().phones, Validators.required]
  }));

  removePhone(phone: string): void {
    this.phones.update(phones => {
      return phones.filter(value => {return value !== phone; });
    });
  }

  addPhone(phone: string): void {
    const pattern = /^\+380\d{9}$/;

    if (!pattern.test(phone)) {
      this.snackBar.open("Phone number must be in the format +380XXXXXXXXX", "Close", {
        duration: 3000
      });
    }
    else {
      this.phoneService.existsPhone(phone).subscribe(exists => {
        if(exists){
          this.snackBar.open(`${phone} already exists in the database`, "Close", {
            duration: 3000
          });
        }
        else {
          this.phones.update(phones => {
            return [...phones, phone];
          });
        }
      })
    }
  }

  submit() {
    if (this.form().valid) {
      this.dialogRef.close(this.form().value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
