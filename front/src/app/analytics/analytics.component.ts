import { Component } from '@angular/core';
import {SauceService} from '../_services/sauce.service';

@Component({
  selector: 'app-analytics',
  imports: [],
  templateUrl: './analytics.component.html',
  standalone: true,
  styleUrl: './analytics.component.css'
})
export class AnalyticsComponent {
  constructor(sauceService: SauceService) {}
}
