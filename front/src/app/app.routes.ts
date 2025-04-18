import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AuthGuard} from './_helpers/auth.guard';
import {BatchComponent} from './batch/batch.component';
import {AnalyticsComponent} from './analytics/analytics.component';
import {OrderComponent} from './order/order.component';
import {CustomerComponent} from './customer/customer.component';
import {SauceComponent} from './sauce/sauce.component';

export const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "sauces", component: SauceComponent, canActivate: [AuthGuard]},
  {path: "batches", component: BatchComponent, canActivate: [AuthGuard]},
  {path: "orders", component: OrderComponent, canActivate: [AuthGuard]},
  {path: "customers", component: CustomerComponent, canActivate: [AuthGuard]},
  {path: "analytics", component: AnalyticsComponent, canActivate: [AuthGuard]},
  {path: "", component: HomeComponent},
  {path: "**", redirectTo: ''}
];
