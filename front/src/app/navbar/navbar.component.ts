import { Component } from '@angular/core';
import {DarkLightBtnComponent} from '../dark-light-btn/dark-light-btn.component';
import {RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  standalone: true,
  imports: [
    DarkLightBtnComponent,
    RouterLink,
    NgIf
  ],
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  protected readonly DarkLightBtnComponent = DarkLightBtnComponent;
}
