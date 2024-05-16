package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;

public interface IAddressService {
    /**
     * @param uid
     * @param username
     * @param address
     * @return
     */
    void addNewAddress(Integer uid, String username, Address address);

    void setDefault(Integer aid, Integer uid, String username);

    void deleteAddress(Integer aid, Integer uid, String username);
}
