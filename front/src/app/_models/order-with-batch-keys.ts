import {Order} from './order';

export interface OrderWithBatchKeys extends Order {
  batchKeys: string[];
}
