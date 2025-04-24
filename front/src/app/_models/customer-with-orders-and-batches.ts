import {CustomerWithOrders} from './customer-with-orders';

export interface CustomerWithOrdersAndBatches extends CustomerWithOrders {
  batchKeys: string[];
}
