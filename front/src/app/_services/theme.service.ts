import {Injectable, signal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  lightMode= signal<boolean>(false);

  constructor() {
    this.loadTheme();
  }

  toggleTheme() {
    if(this.lightMode()) this.setDarkMode();
    else this.setLightMode();
    this.lightMode.set(!this.lightMode());
    localStorage.setItem('theme', this.lightMode() ? 'light' : 'dark');
  }

  setDarkMode() {
    document.documentElement.style.setProperty('--primary-color', "var(--primary-dark-color)");
    document.documentElement.style.setProperty('--secondary-color', "var(--secondary-dark-color)");
    document.documentElement.style.setProperty('--text-color', "var(--text-dark-color)");
    document.documentElement.style.setProperty('--white-color', "var(--white-dark-color)")
    document.body.classList.remove("light-mode");
    document.body.classList.add("dark-mode");
  }
  setLightMode() {
    document.documentElement.style.setProperty('--primary-color', "var(--primary-light-color)");
    document.documentElement.style.setProperty('--secondary-color', "var(--secondary-light-color)");
    document.documentElement.style.setProperty('--text-color', "var(--text-light-color)");
    document.documentElement.style.setProperty('--white-color', "var(--white-light-color)")
    document.documentElement.style.setProperty('color-theme', 'light');
    document.body.classList.remove("dark-mode");
    document.body.classList.add("light-mode");
  }

  loadTheme() {
    const mode = (localStorage.getItem('theme') as 'light' | 'dark') || 'dark';
    if(mode === 'light') this.toggleTheme();
  }
}
