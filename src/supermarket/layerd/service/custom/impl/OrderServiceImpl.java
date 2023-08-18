/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.layerd.service.custom.impl;

import supermarket.layerd.dao.DaoFactory;
import supermarket.layerd.dao.custom.ItemDao;
import supermarket.layerd.dao.custom.OrderDao;
import supermarket.layerd.dao.custom.OrderDetailDao;
import supermarket.layerd.dto.OrderDto;
import supermarket.layerd.service.custom.OrderService;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import supermarket.layerd.db.DBConnection;
import supermarket.layerd.dto.OrderDetailDto;
import supermarket.layerd.entity.ItemEntity;
import supermarket.layerd.entity.OrderDetailEntity;
import supermarket.layerd.entity.OrderEntity;

public class OrderServiceImpl implements OrderService {

    OrderDetailDao orderDetailDao = (OrderDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDERDETAILS);
    ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ITEM);
    OrderDao orderDao = (OrderDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDER);

    @Override
    public String placeOrder(OrderDto dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (orderDao.add(new OrderEntity(dto.getId(), sdf.format(new Date()), dto.getCustId()))) {
                boolean isOrderDetailAdded = true;

                for (OrderDetailDto detailDto : dto.getDetailDtos()) {
                    if (!orderDetailDao.add(new OrderDetailEntity(dto.getId(), detailDto.getItemCode(),
                            detailDto.getQty(), detailDto.getDisccount()))) {
                        isOrderDetailAdded = false;
                    }
                }

                if (isOrderDetailAdded) {
                    boolean isItemUpdated = true;

                    for (OrderDetailDto detailDto : dto.getDetailDtos()) {
                        ItemEntity entity = itemDao.get(detailDto.getItemCode());
                        entity.setQty(entity.getQty() - detailDto.getQty());
                        if (!itemDao.update(entity)) {
                            isItemUpdated = false;
                        }
                    }

                    if (isItemUpdated) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Item Update Error";
                    }

                } else {
                    connection.rollback();
                    return "Order Detail Save Error";
                }
            } else {
                connection.rollback();
                return "Order Save Error";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
