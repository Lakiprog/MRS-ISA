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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 
 
@NgModule({
imports: [FormsModule, ReactiveFormsModule, MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule],
exports: [FormsModule, ReactiveFormsModule, MatButtonModule,MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule],
 
})
 
export class AngularMaterialModule { }