package com.pushtech.crawler.launcher;

public class Selectors {

   // home page
//	public static final String ALL_LISTING = "td:has(input[name=codigo]):has(table)";
   // category page
   // listing page

   // product page
   public static final String PRODUCT_PAGE_IDENTIFIER = "h1";
   public static final String PRODUCT_NAME = "h1";
   public static final String PRODUCT_LINK = "td:has(input[name=codigo]):has(table) a:not(:has(img))";
   public static final String PRODUCT_DESCRIPTION = "meta[name=Description]";
   public static final String PRODUCT_KEYWORDS = "meta[name=keywords]";
   public static final String PRODUCT_IDENTIFIER = "span.mini";

   public static final String PRODUCT_CATEGORY = ".crumb0>a";
   public static final String PRODUCT_IMAGE = "img[name=foto]";
   public static final String PRODUCT_PRICE = "input[name^=precio]";

   // listing page
   public static final String LISTING_PAGE_IDENTIFIER = "td:has(input[name=codigo]):has(table)";
   public static final String LISTING_PAGE_PRODUCTS = "td:has(input[name=codigo]):has(table)";
   public static final String LISTING_PAGE_PRODUCT_LINK = "td:has(input[name=codigo]):has(table) a:not(:has(img))";
   public static final String NEXT_PAGE_LINK = "a[name=siguiente_pagina]";
 //public static final String ALL_LISTING="div.divino>ul>li>div>ul>ul>li>a";//OTHERS
  public static final String ALL_LISTING="div#capa118839>ul>li>a";//LICENCE
 //  public static final String ALL_LISTING="div#capa117895>ul>li>a";// EXPOSANTS
}
