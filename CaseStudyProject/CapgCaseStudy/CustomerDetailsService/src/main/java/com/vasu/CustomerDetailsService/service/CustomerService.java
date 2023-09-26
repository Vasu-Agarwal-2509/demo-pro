package com.vasu.CustomerDetailsService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasu.CustomerDetailsService.model.CustomerDetail;
import com.vasu.CustomerDetailsService.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public boolean addDetail(CustomerDetail custDetail) {
		Optional<CustomerDetail> obj = customerRepo.findById(custDetail.getEmailId()); 
		if(!obj.isEmpty()) {
			return false;
		}
//		custDetail.setEmailId(emailid);
		customerRepo.save(custDetail);
		return true;
	}
	
	public CustomerDetail getDetails(String username) {
		return customerRepo.findById(username).get();
	} 
	
	public void addCoupon(String emailid, String couponCode) {
		List<String> couponList = new ArrayList<String>();
		CustomerDetail customerObj = customerRepo.findById(emailid).get(); 
		couponList = customerObj.getCoupons();
		if(couponList == null) {
			List<String> list = new ArrayList<String>();
			list.add(couponCode);
			customerObj.setCoupons(list);
			customerRepo.save(customerObj);
		}
		else {
			for(String coupon : couponList) {
				if(coupon.equals(couponCode)) {
					return;
				}
			}
			couponList.add(couponCode);
			customerObj.setCoupons(couponList);
			customerRepo.save(customerObj);
		}
	}
	public List<String> getAllCoupons(String emailid){
		return customerRepo.findById(emailid).get().getCoupons();
	}
	
	public void updateCustomerDetails(CustomerDetail customerDetail) {
		customerRepo.save(customerDetail);
	}
	
	public void updateByPhoneNumber(CustomerDetail customerDetail) {
		Optional<CustomerDetail> obj = customerRepo.findById(customerDetail.getEmailId());
		CustomerDetail customerObj =  obj.get();
		customerObj.setPhoneNumber(customerDetail.getPhoneNumber());
		customerRepo.save(customerObj);
	}
	
	public void updateAddress(CustomerDetail customerDetail) {
		Optional<CustomerDetail> obj = customerRepo.findById(customerDetail.getEmailId());
		CustomerDetail customerObj =  obj.get();
		customerObj.setAddress(customerDetail.getAddress());
		customerRepo.save(customerObj);
	}
	
	public void deleteCustomer(String emailid) {
		customerRepo.deleteById(emailid);
	}
}
