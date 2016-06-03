package com.pushtech.commons;

public class Product {

   private String productId = null, parentId = null;
   private String name = null, link = null, description = null, brand = null, category = null, image = null;
   private String keyWord = null;
   private String updated = null;
   private float price = -1f, shippingCost = -1f;
   private int shippingDelay = 0, quantity = 0;
   String color=null,size=null;

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public String getParentId() {
      return parentId;
   }

   public void setParentId(String parentId) {
      this.parentId = parentId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLink() {
      return link;
   }

   public void setLink(String link) {
      this.link = link;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getBrand() {
      return brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getKeyWord() {
      return keyWord;
   }

   public void setKeyWord(String keyWord) {
      this.keyWord = keyWord;
   }

   public String getUpdated() {
      return updated;
   }

   public void setUpdated(String updated) {
      this.updated = updated;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public float getShippingCost() {
      return shippingCost;
   }

   public void setShippingCost(float shippingCost) {
      this.shippingCost = shippingCost;
   }

   public int getShippingDelay() {
      return shippingDelay;
   }

   public void setShippingDelay(int shippingDelay) {
      this.shippingDelay = shippingDelay;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public String getSize() {
      return size;
   }

   public void setSize(String size) {
      this.size = size;
   }

   public String toString() {
      return "Product : " + productId + " - " + name;
   }
}
