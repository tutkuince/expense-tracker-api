import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import { IPagination } from '../shared/model/pagination';
import { IBrand, ICategory, IProduct } from '../shared/model/product';
import { environment } from 'src/environments/environment';
import { ShopParams } from '../shared/model/shopparams';
import { Observable, map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ShopService {

  shopParams = new ShopParams();
pagination?: IPagination<IProduct[]>;

  constructor(private  http: HttpClient) { }
  
  getProducts() :Observable<IPagination<IProduct[]>>{
    let params = new HttpParams();
    console.log("page size:");
    console.log(this.shopParams.pageSize);
    if(this.shopParams.brandId > 0) params = params.append('brandId', this.shopParams.brandId);
    if(this.shopParams.categoryId > 0) params = params.append('categoryId', this.shopParams.categoryId);
    params = params.append('sort', this.shopParams.sort);
    
    params = params.append('pageIndex', this.shopParams.pageIndex);
   // if(this.shopParams?.pageSize === 'undefined')  this.shopParams.pageSize=environment.pageSize;
    params = params.append('pageSize', environment.pageSize);
    if(this.shopParams.search) params = params.append('search',this.shopParams.search.trim());

    return this.http.get<IPagination<IProduct[]>>(environment.apiUrl +'shop/products', {params}).pipe(
    
      map(response => {
         this.pagination= response;
         console.log(response);
          return response;
        
      })

    );
  }
  getCategories() {
    return this.http.get<ICategory[]>(environment.apiUrl +'shop/categories');
  }
  getBrands() {
    return this.http.get<IBrand[]>(environment.apiUrl +'shop/brands');
  }

  setShopParams(params: ShopParams) {
    this.shopParams = params;
  }
  getShopParams() {
    return this.shopParams;
  }
}
