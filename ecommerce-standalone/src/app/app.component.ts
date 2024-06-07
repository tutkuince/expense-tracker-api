import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {NavBarComponent} from './core/nav-bar/nav-bar.component';
import {ShopComponent} from './shop/shop.component';
import {HomeComponent} from './home/home.component';
import {CartService} from './cart/cart.service';
import {BehaviorSubject, Subject, map} from 'rxjs';
import {ICart} from './shared/model/cart';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavBarComponent, ShopComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  pageTitle: string = 'Welcome to Stylish Online Shop';

  /*  private bsSource = new BehaviorSubject<ICart | null>(null);
   bs$= this.bsSource.asObservable(); */


  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.loadBasket();
    this.demoSubject();

  }


  loadBasket() {
    const cartId = localStorage.getItem('angular_cart_id');
    if (cartId) {
      this.cartService.getCart(cartId).subscribe({
        next: response => {
          console.log("Cart Initialized");
          console.log(response);
        },
        error: error => console.log(error)
      });


    }
  }

  demoSubject() {
    /*const subject = new Subject();
    subject.subscribe(
      data => console.log(`Observer A receives : ${data}`)
    );
    subject.subscribe(
      data => console.log(`Observer B receives : ${data}`)
    );

    subject.next(10);
    subject.next(20);
    /* subject.subscribe(
       data => console.log(`Observer C receives : ${data}`)
     );
     subject.next(30);
    */
    const bs = new BehaviorSubject(50);
    bs.subscribe(
      data => console.log(`Observer A receives : ${data}`)
    );
    bs.subscribe(
      data => console.log(`Observer B receives : ${data}`)
    );
    bs.next(10);
    bs.next(20);
    bs.subscribe(
      data => console.log(`Observer C receives : ${data}`)
    );
    bs.next(30);
    console.log(bs.value);

  }

}
