/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.layerd.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import supermarket.layerd.dao.DaoFactory;
import supermarket.layerd.dao.custom.ItemDao;
import supermarket.layerd.dto.ItemDto;
import supermarket.layerd.entity.ItemEntity;
import supermarket.layerd.service.custom.ItemService;

public class ItemServiceImpl implements ItemService {

    ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ITEM);

    @Override
    public String addItem(ItemDto dto) throws Exception {
        Boolean resp = itemDao.add(
                new ItemEntity(dto.getId(), dto.getDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQty()));
        return resp ? "Success" : "Fail";
    }

    @Override
    public String updateItem(ItemDto dto) throws Exception {
        Boolean resp = itemDao.update(
                new ItemEntity(dto.getId(), dto.getDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQty()));
        return resp ? "Success" : "Fail";
    }

    @Override
    public String deleteItem(String id) throws Exception {
        Boolean resp = itemDao.delete(id);
        return resp ? "Success" : "Fail";
    }

    @Override
    public ItemDto getItem(String id) throws Exception {
        ItemEntity entity = itemDao.get(id);
        return new ItemDto(entity.getId(),
                entity.getDescription(), entity.getPackSize(),
                entity.getUnitPrice(), entity.getQty());
    }

    @Override
    public List<ItemDto> getAllItem() throws Exception {
        List<ItemDto> dtos = new ArrayList<>();
        List<ItemEntity> itemEntitys = itemDao.getAll();

        itemEntitys.forEach(e -> {
            dtos.add(new ItemDto(e.getId(), e.getDescription(), e.getPackSize(), e.getUnitPrice(), e.getQty()));
        });

        return dtos;
    }

}
