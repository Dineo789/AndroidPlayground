package com.example.admin.androidplayground.model;

/**
 * Created by admin on 2017/09/26.
 */

public class Menu {

    String itemName;
    String itemContent;
    int isLiked;

    public Menu(){
    }
    public Menu(String itemName, String itemContent) {
        this.itemName = itemName;
        this.itemContent = itemContent;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemContent() {
        return itemContent;
    }
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;}
    public int getIsLiked() {
        return isLiked;
    }
    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }
}
