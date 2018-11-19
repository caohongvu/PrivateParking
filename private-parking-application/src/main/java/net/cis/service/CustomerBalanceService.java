package net.cis.service;

import net.cis.jpa.entity.CustomerBalanceEntity;

/**
 * Created by Vincent 23/05/2018
 */
public interface CustomerBalanceService {

    CustomerBalanceEntity save(CustomerBalanceEntity customerBalance);

    CustomerBalanceEntity findById(long id);

}
 