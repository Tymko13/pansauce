import { Component } from '@angular/core';
import {DarkLightBtnComponent} from '../dark-light-btn/dark-light-btn.component';

@Component({
  selector: 'app-home',
  imports: [
    DarkLightBtnComponent
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
