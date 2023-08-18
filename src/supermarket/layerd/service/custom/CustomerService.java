/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package supermarket.layerd.service.custom;

import java.util.ArrayList;
import supermarket.layerd.dto.CustomerDto;
import supermarket.layerd.service.SuperService;

public interface CustomerService extends SuperService {
    public String addCustomer(CustomerDto customerDto) throws Exception;

    public ArrayList<CustomerDto> getAllCustomer() throws Exception;

    public CustomerDto searchCustomer(String custId) throws Exception;
}
