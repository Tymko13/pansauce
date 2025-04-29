import {Component, inject, signal, computed} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {UpdateTypeDialogComponent} from './update-ingredient-dialog/update-type-dialog.component';
import {TypeService} from '../../_services/type.service';
import {Type} from '../../_models/type';


@Component({
  selector: 'app-type',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatSelect,
    MatOption,
    MatIconModule
  ],
  templateUrl: './type.component.html',
  styleUrls: ['./type.component.css'],
})
export class TypeComponent {
  private typeService = inject(TypeService);
  private dialog = inject(MatDialog);

  searchOptions = ['Type Number', 'Type Name'];
  sortOptions = ["number", "name"];
  showOptions = ["All"]
  displayedColumns = [
    'number',
    'name',
    'action'
  ];

  searchTerm = signal('');
  selectedSort = signal<string>(this.sortOptions[0]);
  selectedShow = signal<string>(this.showOptions[0]);
  selectedSearch = signal<string>(this.searchOptions[0]);

  dbUpdated = signal(0);
  types = computed(() => {
    this.dbUpdated();
    const term = this.searchTerm();
    const sort = this.selectedSort();
    const show = this.selectedShow();

    switch (show) {
      case 'All': {
        if (term) switch (this.selectedSearch()) {
          case 'Type Number':
            return this.typeService.getTypesWithNumberPrefixSortedBy(term, sort);
          case 'Type Name':
            return this.typeService.getTypesWithNamePrefixSortedBy(term, sort);
        }
        break;
      }
    }
    return this.typeService.getAllTypesSortedBy(sort);
  });

  updateDB() {
    this.dbUpdated.update(e => ++e);
  }

  update(number: string) {
    const update = this.dialog.open(UpdateTypeDialogComponent, {
      data: {type: number}
    });
    update.afterClosed().subscribe(res => {
      if (res) {
        let updatedType: Partial<Type> = {
          typeNumber: number,
          typeName: res.name
        }
        this.typeService.updateType(updatedType).subscribe(() => {
          this.updateDB();
        });
      }
    });
  }
}
