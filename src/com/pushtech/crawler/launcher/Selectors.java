package com.pushtech.crawler.launcher;

public class Selectors {

   // home page
//	public static final String ALL_LISTING = "td:has(input[name=codigo]):has(table)";
   // category page
   // listing page

   // product page
   public static final String PRODUCT_PAGE_IDENTIFIER = "span[itemprop=name]";
   public static final String PRODUCT_NAME = "span[itemprop=name]";
   public static final String PRODUCT_LINK = "a.link_to_item";
   public static final String PRODUCT_DESCRIPTION = "meta[name=Description]";
   public static final String PRODUCT_KEYWORDS = "meta[name=keywords]";
   public static final String PRODUCT_IDENTIFIER = "span.mini";

   public static final String PRODUCT_CATEGORY = ".crumb0>a";
   public static final String PRODUCT_IMAGE = "img[id^=picture]";
   public static final String PRODUCT_PRICE = "span#articlePrice>span>span#price";

   // listing page
   public static final String LISTING_PAGE_IDENTIFIER = "ul#productsList>li";
   public static final String LISTING_PAGE_PRODUCTS = "ul#productsList>li";
  public static final String LISTING_PAGE_PRODUCT_LINK = "a.link_to_item";
   public static final String NEXT_PAGE_LINK = "ul.pagination>li>a:contains(>)";
 // public static final String ALL_LISTING="div#capa118839>ul>li>a";//LICENCE

}
