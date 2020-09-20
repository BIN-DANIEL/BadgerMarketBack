package com.BadgerMarket.server;

import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;
import com.BadgerMarket.service.ItemService;
import com.BadgerMarket.service.UserService;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
public class UploadItemServer {
    @Value("${resourceDest.itemImageDiskURL}")
    private String ItemImageDest;
    //= "/Users/shaobindanielhong/MyProject/BadgerMarket/webBackEnd/src/main/resources/static/";
    @Value("${resourceDest.itemImageHttpURL}")
    public String ItemImageHttpURL;
    //"http://127.0.0.1:80/"
    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;
    private class Request {
        private String title;
        private Double price;
        private String description;
        private String username;
        private String category;
        private MultipartFile coverImage;
        private List<MultipartFile> otherImages;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Request(){
        }

        public List<MultipartFile> getOtherImages() {
            return otherImages;
        }

        public void setOtherImages(List<MultipartFile> otherImages) {
            this.otherImages = otherImages;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MultipartFile getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(MultipartFile coverImage) {
            this.coverImage = coverImage;
        }

    }
    private class UploadReply {
        private boolean success;
        private String itemId;
        private String urlToCover;
        private List<String> urlsToOther;

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public UploadReply() {
            urlsToOther = new ArrayList<>();
        }
        public boolean isSuccess() {
            return success;
        }
        public void addOtherURL(String url) {
            urlsToOther.add(url);
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getUrlToCover() {
            return urlToCover;
        }

        public void setUrlToCover(String urlToCover) {
            this.urlToCover = urlToCover;
        }

        public List<String> getUrlsToOther() {
            return urlsToOther;
        }

        public void setUrlsToOther(List<String> urlsToOther) {
            this.urlsToOther = urlsToOther;
        }
    }
    public ItemImage createItemImage(byte[] itemId, MultipartFile image) {

        String filename = image.getOriginalFilename();
           String imageId = UUID.randomUUID().toString().replace("-","");
           if (filename.endsWith(".jpeg")) {
               filename = imageId + ".jpeg";
           } else if (filename.endsWith(".png")) {
               filename = imageId + ".png";
           } else if (filename.endsWith(".JPEG")) {
               filename = imageId + ".JPEG";
           } else if (filename.endsWith(".PNG")) {
               filename = imageId + ".PNG";
           }
           File destFile = new File(ItemImageDest, filename);
           ItemImage itemImage = new ItemImage();
           itemImage.setHttpUrl(ItemImageHttpURL + filename);
           itemImage.setDiskUrl(ItemImageDest + filename);
           itemImage.setImageId(userService.hexString2ByteArray(imageId));
           itemImage.setItemId(itemId);
        try {
            destFile.createNewFile();
            image.transferTo(destFile);
        } catch (Exception e) {
            return null;
        }
        return itemImage;
    }
    /**
     * This is used when no user image is provided
     * @return
     */
    private byte[] getDefaultCoverImageId() {
            return new byte[]{};
    }
    private String getDefaultCoverImageURL() {
         return ItemImageHttpURL + "NoImage.jpg";
    }
    public void createItem(Request request, UploadReply reply) {
        //TODO: 检查上传的图片是否为NULL

        // Set Up the Item
        Item item = new Item();
        item.setDescription(request.getDescription());
        item.setUsername(request.getUsername());
        item.setTitle(request.getTitle());
        item.setPrice(request.getPrice());
        String itemIdInString =  UUID.randomUUID().toString().replace("-","");
        reply.setItemId(itemIdInString);
        item.setCategory(request.getCategory());
        byte[] itemId = userService.hexString2ByteArray(itemIdInString);
        item.setItemId(itemId);

        ItemImage coverImage = null;

        if (request.getCoverImage() != null) { // When Cover Image is provided
            coverImage = createItemImage(itemId, request.getCoverImage());
            reply.setUrlToCover(coverImage.getHttpUrl());
            item.setCoverImageId(coverImage.getImageId());
        } else { // When No Cover Image is Provided, use default one
            reply.setUrlToCover(getDefaultCoverImageURL());
            item.setCoverImageId(getDefaultCoverImageId());
        }
        userService.addItem(item);
        if (coverImage != null) {
            itemService.addItemImage(coverImage);
        }
        List<MultipartFile> otherImages = request.getOtherImages();
        if (otherImages != null) {
            for (int i = 0; i < otherImages.size() ; i++) {
                 ItemImage otherImage = createItemImage(itemId, otherImages.get(i));
                 if (otherImage == null) {
                     reply.setSuccess(false);
                     return;
                 }
                 if (!itemService.addItemImage(otherImage)) {
                     reply.setSuccess(false);
                     return;
                 }
                 reply.addOtherURL(otherImage.getHttpUrl());
            }
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/uploadItem", method = RequestMethod.POST)
    public UploadReply uploadItem(Request request) {
        UploadReply reply = new UploadReply();
        reply.setSuccess(true);
        createItem(request, reply);
        return reply;
    }
}
