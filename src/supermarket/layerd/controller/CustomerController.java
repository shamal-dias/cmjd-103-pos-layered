/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.layerd.controller;

import java.util.ArrayList;
import supermarket.layerd.dto.CustomerDto;
import supermarket.layerd.service.ServiceFactory;
import supermarket.layerd.service.custom.CustomerService;

public class CustomerController {

    private CustomerService customerService = (CustomerService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceType.CUSTOMER);

    public String saveCustomer(CustomerDto customerDto) throws Exception {
        return customerService.addCustomer(customerDto);
    }

    public ArrayList<CustomerDto> getAllCustomers() throws Exception {
        return customerService.getAllCustomer();
    }

    public CustomerDto searchCustomer(String custId) throws Exception {
        return customerService.searchCustomer(custId);
    }

}
