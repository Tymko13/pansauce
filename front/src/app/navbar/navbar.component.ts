import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';
import {DarkLightBtnComponent} from './dark-light-btn/dark-light-btn.component';
import {LoginLogoutBtnComponent} from './login-logout-btn/login-logout-btn.component';
import {MatButtonModule} from '@angular/material/button';
import {AuthService} from '../_auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  standalone: true,
  imports: [
    DarkLightBtnComponent,
    RouterLink,
    NgIf,
    DarkLightBtnComponent,
    LoginLogoutBtnComponent,
    MatButtonModule
  ],
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  protected readonly DarkLightBtnComponent = DarkLightBtnComponent;

  constructor(public authService: AuthService) {}
}
