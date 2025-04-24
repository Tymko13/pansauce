import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {SauceIngredient} from '../../_models/sauce-ingredient';

@Component({
  standalone: true,
  selector: 'app-see-recipe-dialog',
  templateUrl: './see-recipe-dialog.component.html',
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
  styles: "mat-form-field {width: 45%;} mat-form-field:nth-of-type(2n+1) {margin-right: 5%;}"
})
export class SeeRecipeDialogComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { recipe: SauceIngredient[]},
    public dialogRef: MatDialogRef<SeeRecipeDialogComponent>
  ) {}

  done() {
    this.dialogRef.close(null);
  }
}
