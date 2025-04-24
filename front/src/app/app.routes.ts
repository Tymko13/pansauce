import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AuthGuard} from './_auth/auth.guard';
import {SauceComponent} from './info/sauce/sauce.component';
import {BatchComponent} from './info/batch/batch.component';
import {OrderComponent} from './info/order/order.component';
import {CustomerComponent} from './info/customer/customer.component';
import {AnalyticsComponent} from './info/analytics/analytics.component';

export const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "sauces", component: SauceComponent, canActivate: [AuthGuard]},
  {path: "batches", component: BatchComponent, canActivate: [AuthGuard]},
  {path: "orders", component: OrderComponent, canActivate: [AuthGuard]},
  {path: "customers", component: CustomerComponent, canActivate: [AuthGuard]},
  {path: "analytics", component: AnalyticsComponent, canActivate: [AuthGuard]},
  {path: "", component: HomeComponent},
  {path: "**", redirectTo: ""}
];
