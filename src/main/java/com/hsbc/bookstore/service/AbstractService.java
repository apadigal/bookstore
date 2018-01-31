/*
 * SIEE - PMDB Management
 *
 * AbstractService.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.service;
// ---- Import Statements -----------------------------------------------------

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 11:40
 */
public class AbstractService {

    protected  <T> T convertToObject(Object fromValue, Class<T> toValueType) throws IllegalArgumentException
    {

        return modelMapper(false).map(fromValue, toValueType);
    }

    protected ModelMapper modelMapper(boolean ignoreLazyLoadedResources) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if(ignoreLazyLoadedResources) {
            modelMapper.getConfiguration().setPropertyCondition(context ->  !(context.getSource() instanceof PersistentCollection));
        }
        return modelMapper;
    }

}