export interface Order {
  number: string;
  registrationDate: Date;
  expectedDate: Date;
  realDate?: Date;
  deliveryCost?: number;
  totalCost: number;
  customerNumber: string;
}
