import {Phone} from './phone';

export interface Customer {
  number: string;
  name: string;
  surname: string;
  patronymic?: string;
  address: string;
  phones: Phone[]
}
