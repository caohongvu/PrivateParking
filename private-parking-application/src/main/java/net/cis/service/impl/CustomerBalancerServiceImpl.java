package net.cis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cis.jpa.entity.CustomerBalanceEntity;
import net.cis.repository.inhouse.parking.center.CustomerBalanceRepository;
import net.cis.service.CustomerBalanceService;

/**
 * Created by Vincent on 02/10/2018
 */
@Service
public class CustomerBalancerServiceImpl implements CustomerBalanceService {

	@Autowired
	CustomerBalanceRepository repository;

	@Override
	public CustomerBalanceEntity save(CustomerBalanceEntity customerBalance) {
		return repository.save(customerBalance);
	}

	@Override
	public CustomerBalanceEntity findById(long id) {
		return repository.findOne(id);
	}
	
	
	
}
