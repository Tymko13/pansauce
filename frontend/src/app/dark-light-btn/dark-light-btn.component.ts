import { Component } from '@angular/core';
import {state, style, animate, trigger, transition} from '@angular/animations'

@Component({
  selector: 'app-dark-light-btn',
  imports: [],
  templateUrl: './dark-light-btn.component.html',
  standalone: true,
  styleUrl: './dark-light-btn.component.css',
  animations: [

    trigger('darkLight', [
      state(
        'dark',
        style({
          opacity: 1,
          transform: 'rotate(360deg)'
        })
      ),
      state(
        'light',
        style({
          opacity: 0,
          transform: 'rotate(0)'
        })
      ),
      transition('dark => light', [animate('250ms 0s ease-out')]),
      transition('light => dark', [animate('250ms 0s ease-out')])
    ])
  ]
})
export class DarkLightBtnComponent {
  lightMode: boolean = true;

  switchMode() {
    if(this.lightMode) this.setDarkMode();
    else this.setLightMode();
    this.lightMode = !this.lightMode;
  }
  setDarkMode() {
    document.documentElement.style.setProperty('--primary-color', '#4A525A');
    document.documentElement.style.setProperty('--secondary-color', '#24272B');
    document.documentElement.style.setProperty('--text-color', '#FFFFFF');

  }
  setLightMode() {
    document.documentElement.style.setProperty('--primary-color', '#FFFFFF');
    document.documentElement.style.setProperty('--secondary-color', '#FCF7F8');
    document.documentElement.style.setProperty('--text-color', '#000000');
  }
}
