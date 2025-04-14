import {Order} from './order';

export interface OrderWithCustomerData extends Order {
  customerName: string;
  customerSurname: string;
  customerPatronymic?: string;
  customerPhoneNumbers: string[];
}
