package com.BadgerMarket.server;

import com.BadgerMarket.dao.UserDao;
import com.BadgerMarket.entity.ItemImage;
import com.BadgerMarket.service.ItemService;
import com.BadgerMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class DeleteItemServer {
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @CrossOrigin
    @RequestMapping(value = "/deleteItem", method = RequestMethod.PUT)
    public boolean deleteItem(@RequestParam(name = "itemId") String itemId) {
        List<ItemImage> allImages = itemService.getAllImages(itemId);
        if (userService.deleteItem(itemId)) {
            for (int i = 0; i < allImages.size(); i++) {
                ItemImage itemImage = allImages.get(i);
                File file = new File(itemImage.getDiskUrl());
                if (file != null) {
                    file.delete();
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
