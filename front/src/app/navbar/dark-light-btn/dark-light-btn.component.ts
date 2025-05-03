import {Component, inject} from '@angular/core';
import {state, style, animate, trigger, transition} from '@angular/animations'
import {ThemeService} from '../../_services/theme.service';

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
  themeService= inject(ThemeService);
}
