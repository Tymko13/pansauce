import { Component } from '@angular/core';
import {state, style, animate, trigger, transition} from '@angular/animations'

@Component({
  selector: 'app-dark-light-btn',
  templateUrl: './dark-light-btn.component.html',
  styleUrl: './dark-light-btn.component.css',
  standalone: true,
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
  static lightMode: boolean = true;

  constructor() { this.loadTheme() }

 switchMode() {
    if(this.lightMode) this.setDarkMode();
    else this.setLightMode();
    this.lightMode = !this.lightMode;
    DarkLightBtnComponent.lightMode = this.lightMode;
    localStorage.setItem('theme', this.lightMode ? 'light' : 'dark');
  }
  setDarkMode() {
    document.documentElement.style.setProperty('--primary-color', '#24272B');
    document.documentElement.style.setProperty('--secondary-color', '#4A525A');
    document.documentElement.style.setProperty('--text-color', '#FFFFFF');
    document.documentElement.style.setProperty('--text-color', '#FFFFFF');
    document.documentElement.style.setProperty('--white-color', '#6a5454')

  }
  setLightMode() {
    document.documentElement.style.setProperty('--primary-color', '#ffdcc7');
    document.documentElement.style.setProperty('--secondary-color', '#f8ebe6');
    document.documentElement.style.setProperty('--text-color', '#000000');
    document.documentElement.style.setProperty('--white-color', '#faf8f8')

  }

  loadTheme() {
    const mode = (localStorage.getItem('theme') as 'light' | 'dark') || 'light';
    if(mode === 'dark') this.switchMode()
  }
}
