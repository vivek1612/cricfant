import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatCardModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule,
  MatProgressSpinnerModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTabsModule,
  MatExpansionModule, MatTableModule, MatSortModule, MatTooltipModule, MatGridListModule,
  MatStepperModule
} from '@angular/material';

@NgModule({
  imports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule, MatExpansionModule,
  MatTableModule,MatSortModule, MatTooltipModule,MatGridListModule, MatStepperModule],
  exports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule, MatExpansionModule,
  MatTableModule,MatSortModule, MatTooltipModule,MatGridListModule, MatStepperModule],
})
export class MaterialModule { }
