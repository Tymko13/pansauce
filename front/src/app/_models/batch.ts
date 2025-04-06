export interface Batch {
  number: string;
  productionDate: Date;
  expirationDate: Date;
  sauceCost: number;
  cost: number;
  status: string;
  sauceNumber: string;
  sauceName: string;
  orderNumber?: string;
}
