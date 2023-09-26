import { Component } from '@angular/core';
import { AuthDetails } from '../authdetails';
import { DetailsService } from '../details.service';
import { AppComponent } from '../app.component';
import { Route, Router } from '@angular/router';
import { CustomerDetails } from '../customer-details';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  authDetail: AuthDetails = new AuthDetails();
  authDetail2: any;
  customerObj: CustomerDetails = new CustomerDetails();

  constructor(private detailService: DetailsService, private router: Router){}
  
  ngOnInit(){
    this.detailService.getUser().subscribe(data => this.authDetail2 = data);
  }

  addUser(){
    this.authDetail.roles = "USER";
    this.customerObj.emailId = this.authDetail.emailid;
    console.log(this.authDetail);

    this.detailService.addCustomer(this.customerObj).subscribe();
    this.detailService.addCustomerInCart(this.authDetail.emailid).subscribe();
    this.detailService.addUser(this.authDetail).subscribe();
    this.router.navigate(['/login']);
  }
}
