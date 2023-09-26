import { Component } from '@angular/core';
import { DetailsService } from '../details.service';
import { Itemdetail } from '../itemdetail';
import { ActivatedRoute, Router } from '@angular/router';
import { Cartdetail } from '../cartdetail';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  itemDetailList: Itemdetail[] = [];
  cartObj: Cartdetail = new Cartdetail();
  isAuthenticated:any;
  constructor(private detailService: DetailsService, private router: Router, private route: ActivatedRoute){}
  
  ngOnInit(){
    this.detailService.getProducts().subscribe(data => this.itemDetailList = data);
    // console.log("home " +this.detailService.isAuthenticated);
    this.isAuthenticated = this.route.snapshot.paramMap.get('isUserValid');
  }

  viewCart(itemId: string){
    this.cartObj.customerId = this.detailService.authDetailObj.emailid;
    this.cartObj.itemId = itemId;
    this.detailService.addProductInCart(this.cartObj).subscribe();
    this.router.navigate(['/cart', this.detailService.authDetailObj.emailid]);
  }

  displayProductDetails(prodId: string){
    this.router.navigate(['/productDetail', prodId]);
  }
}
