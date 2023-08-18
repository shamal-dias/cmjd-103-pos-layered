/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.layerd.controller;

import java.util.List;
import supermarket.layerd.dto.ItemDto;
import supermarket.layerd.service.ServiceFactory;
import supermarket.layerd.service.custom.ItemService;

public class ItemController {

    ItemService itemService = (ItemService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM);

    public String addItem(ItemDto dto) throws Exception {
        return itemService.addItem(dto);
    }

    public String uodateItem(ItemDto dto) throws Exception {
        return itemService.updateItem(dto);
    }

    public String deleteItem(String itemId) throws Exception {
        return itemService.deleteItem(itemId);
    }

    public ItemDto getItem(String itemId) throws Exception {
        return itemService.getItem(itemId);
    }

    public List<ItemDto> getAllItem() throws Exception {
        return itemService.getAllItem();
    }

}
