package com.vasu.AuthenticationService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasu.AuthenticationService.model.AuthDetails;
import com.vasu.AuthenticationService.repo.AuthRepo;

@Service
public class AuthService {
	
	@Autowired
	private AuthRepo authRepo;
	
	public boolean addDetail(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid()); 
		if(!obj.isEmpty()) {
			return false;
		}
		authRepo.save(authDetail);
		return true;
	}
	
	public boolean validateUserDetail(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid());
		if(obj.isEmpty()) {
			return false;
		}
		
		if(!obj.get().getPassword().equals(authDetail.getPassword())) {
			return false;
		}
		return true;
	}
	
	public List<AuthDetails> getDetails() {
		return authRepo.findAll();
	}
	
	public boolean editDetail(AuthDetails authDetail) {
		Optional<AuthDetails> obj = authRepo.findById(authDetail.getEmailid()); 
		if(obj.isEmpty()) {
			return false;
		}
		authRepo.save(authDetail);
		return true;
	}
	
	public boolean deleteDetail(String username) {
		if(authRepo.findById(username).isEmpty()) {
			return false;
		}
		authRepo.deleteById(username);
		return true;
	}
}
