import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatCardModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule,
  MatProgressSpinnerModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTabsModule
} from '@angular/material';

@NgModule({
  imports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule],
  exports: [MatCardModule, MatFormFieldModule, MatInputModule,
    MatProgressSpinnerModule, MatButtonModule, MatToolbarModule, MatIconModule,
  MatMenuModule, MatSidenavModule, MatListModule, MatTabsModule, MatIconModule],
})
export class MaterialModule { }
