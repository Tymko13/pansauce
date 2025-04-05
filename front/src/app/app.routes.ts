import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AuthGuard} from './_helpers/auth.guard';
import {BatchComponent} from './batch/batch.component';
import {AnalyticsComponent} from './analytics/analytics.component';

export const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "batches", component: BatchComponent, canActivate: [AuthGuard]},
  {path: "analytics", component: AnalyticsComponent, canActivate: [AuthGuard]},
  {path: "", component: HomeComponent},
  {path: "**", redirectTo: ''}
];
