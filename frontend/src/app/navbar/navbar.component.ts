import { Component } from '@angular/core';
import {DarkLightBtnComponent} from '../dark-light-btn/dark-light-btn.component';

@Component({
  selector: 'app-navbar',
  imports: [
    DarkLightBtnComponent
  ],
  templateUrl: './navbar.component.html',
  standalone: true,
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
