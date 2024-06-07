import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {CartService} from 'src/app/cart/cart.service';
import {Observable} from 'rxjs';
import {ICart} from 'src/app/shared/model/cart';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {
  cart$: Observable<ICart | null>;

  constructor(public cartService: CartService) {
    this.cart$ = this.cartService.cart$;
  }


}
