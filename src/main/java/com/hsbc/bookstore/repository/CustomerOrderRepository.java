/*
 * SIEE - PMDB Management
 *
 * OrderRepository.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.repository;
// ---- Import Statements -----------------------------------------------------

import com.hsbc.bookstore.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 11:12
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{

}