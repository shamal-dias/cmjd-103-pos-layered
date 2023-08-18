/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.layerd.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import supermarket.layerd.dao.DaoFactory;
import supermarket.layerd.dao.custom.CustomerDao;
import supermarket.layerd.dto.CustomerDto;
import supermarket.layerd.entity.CustomerEntity;
import supermarket.layerd.service.custom.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @Override
    public String addCustomer(CustomerDto dto) throws Exception {
        CustomerEntity entity = new CustomerEntity(dto.getId(),
                dto.getTitle(), dto.getName(), dto.getDob(),
                dto.getSalary(), dto.getAddress(), dto.getCity(),
                dto.getProvince(), dto.getZip());

        return customerDao.add(entity) ? "Success" : "Fail";
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws Exception {
        List<CustomerEntity> customerEntitys = customerDao.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for (CustomerEntity entity : customerEntitys) {
            customerDtos.add(new CustomerDto(entity.getId(),
                    entity.getTitle(), entity.getName(),
                    entity.getDob(),
                    entity.getSalary(), entity.getAddress(),
                    entity.getCity(), entity.getProvince(), entity.getZip()));
        }

        return customerDtos;
    }

    @Override
    public CustomerDto searchCustomer(String custId) throws Exception {
        CustomerEntity entity = customerDao.get(custId);

        CustomerDto dto = new CustomerDto(entity.getId(),
                entity.getTitle(), entity.getName(),
                entity.getDob(),
                entity.getSalary(), entity.getAddress(),
                entity.getCity(), entity.getProvince(), entity.getZip());

        return dto;
    }

}
