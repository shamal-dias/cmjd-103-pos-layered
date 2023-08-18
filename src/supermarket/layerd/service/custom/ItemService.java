/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package supermarket.layerd.service.custom;

import java.util.List;
import supermarket.layerd.dto.ItemDto;
import supermarket.layerd.service.SuperService;

public interface ItemService extends SuperService {

    String addItem(ItemDto dto) throws Exception;

    String updateItem(ItemDto dto) throws Exception;

    String deleteItem(String id) throws Exception;

    ItemDto getItem(String id) throws Exception;

    List<ItemDto> getAllItem() throws Exception;
}
