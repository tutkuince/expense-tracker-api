export interface IProduct {
  productId: number;
  sku: string;
  title: string;
  description: string;
  unitPrice: number;
  imageUrl: string;
  isActive: boolean;
  unitsInStock: number;
  createdDate: Date;
  updatedDate: Date;
}

export interface IBrand {
  brandId: number;
  name: string;
}

export interface ICategory {
  categoryId: number;
  name: string;
}
