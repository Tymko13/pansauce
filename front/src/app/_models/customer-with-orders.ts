import {Customer} from './customer';
import {Order} from './order';

export interface CustomerWithOrders extends Customer {
  orders: Order[];
}
