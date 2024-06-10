import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IProduct } from 'src/app/shared/model/product';
import { CartService } from 'src/app/cart/cart.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent {
  @Input() product?: IProduct;

  constructor(private cartService: CartService) {

  }
  addItemToCart() {
    this.product&&this.cartService.addItemToCart(this.product);
  }
}
