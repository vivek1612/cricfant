import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatCardModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule,
  MatProgressSpinnerModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTabsModule,
  MatExpansionModule, MatTableModule, MatSortModule, MatTooltipModule, MatGridListModule,
  MatStepperModule, MatPaginatorModule, MatDividerModule, MatOptionModule, MatSelectModule, MatButtonToggleModule
} from '@angular/material';

@NgModule({
  imports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule, MatExpansionModule,
  MatTableModule, MatSortModule, MatTooltipModule, MatGridListModule, MatStepperModule, MatPaginatorModule,
  MatDividerModule, MatOptionModule, MatSelectModule, MatButtonToggleModule],
  exports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule, MatExpansionModule,
  MatTableModule, MatSortModule, MatTooltipModule, MatGridListModule, MatStepperModule, MatPaginatorModule,
    MatDividerModule, MatOptionModule, MatSelectModule, MatButtonToggleModule],
})
export class MaterialModule { }
