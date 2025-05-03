import {Component, computed, inject, signal} from '@angular/core';
import {
  MatDialog,
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
import {MatIcon} from '@angular/material/icon';
import {PhoneService} from '../../../_services/phone.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddOrderDialogComponent} from '../../order/add-order-dialog/add-order-dialog.component';

@Component({
  standalone: true,
  selector: 'app-add-customer-dialog',
  templateUrl: './add-customer-dialog.component.html',
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
export class AddCustomerDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<AddCustomerDialogComponent>);
  private phoneService = inject(PhoneService);
  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);
  phones = signal<string[]>([]);

  form = computed(() => this.fb.group({
    name: [null, Validators.required],
    surname: [null, Validators.required],
    patronymic: [null],
    address: [null, Validators.required],
    phones: [null, Validators.required],
    order: [null]
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
      const order = this.dialog.open(AddOrderDialogComponent, {
        data: {askForCustomer: false}
      });
      order.afterClosed().subscribe(newOrder => {
        if(newOrder) {
          this.form().value.order = newOrder;
          this.dialogRef.close(this.form().value);
        }
      })
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
