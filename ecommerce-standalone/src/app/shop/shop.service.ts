import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IProduct} from "../shared/model/product";
import {environment} from "../../environments/environment";
import {IPagination} from "../shared/model/pagination";

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  getProducts() {
    return this.http.get<IPagination<IProduct[]>>(environment.apiUrl + 'shop/products');
  }
}
