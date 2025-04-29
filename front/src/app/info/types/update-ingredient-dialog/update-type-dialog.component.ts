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
import {TypeService} from '../../../_services/type.service';
import {Type} from '../../../_models/type';

@Component({
  standalone: true,
  selector: 'app-update-type-dialog',
  templateUrl: './update-type-dialog.component.html',
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
    MatSelectModule
  ],
  styles: "mat-form-field {width: 100%;}"
})
export class UpdateTypeDialogComponent {
  private fb = inject(FormBuilder);
  private typeService = inject(TypeService);

  type: WritableSignal<Partial<Type>> = signal({});

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { type: string },
    public dialogRef: MatDialogRef<UpdateTypeDialogComponent>
  ) {
    this.typeService.getTypeByKey(this.data.type).subscribe(data => {this.type.set(data);});
  }

  form = computed(() => this.fb.group({
    name: [this.type().typeName, Validators.required],
  }));

  submit() {
    if (this.form().valid) {
      this.dialogRef.close(this.form().value);
    }
  }

  cancel() {
    this.dialogRef.close(null);
  }
}
