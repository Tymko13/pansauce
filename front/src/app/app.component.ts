import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FooterComponent} from './footer/footer.component';
import {NavbarComponent} from './navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [
    RouterOutlet,
    FooterComponent,
    NavbarComponent
  ],
  styleUrl: './app.component.css'
})
export class AppComponent {

}
