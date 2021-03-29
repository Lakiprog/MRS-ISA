import { NgModule } from  '@angular/core';
 
import { MatButtonModule } from  '@angular/material/button';
import { MatToolbarModule } from  '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
imports: [FormsModule, ReactiveFormsModule, MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule, MatSnackBarModule, MatSelectModule],
exports: [FormsModule, ReactiveFormsModule, MatButtonModule,MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule, MatSnackBarModule, MatSelectModule],
 
})
 
export class AngularMaterialModule { }