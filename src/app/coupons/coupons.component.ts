import { Component } from '@angular/core';
import { DetailsService } from '../details.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent {

  couponList: any[] = [];

  constructor(private detailService: DetailsService){}

  ngOnInit(){
    this.detailService.getCoupons().subscribe(data => this.couponList = data);
  }
}
