package com.pushtech.crawler.launcher;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pushtech.crawler.beans.Page;

public class CrawlListing {

   public static ArrayList<String> getProductLinks(Page page) {
      ArrayList<String> products = new ArrayList<String>();
      Document document = page.getDoc();
      Elements items = getOffers(document);
      for (Element item : items) {
         String link = getProductLink(item);
         System.out.println("Path :" + link);
         // for (char a : link.toCharArray()) {
         // System.err.println(" " + ((int) a) + " " + a);
         // }
         products.add(link);
      }
      return products;
   }

   private static Elements getOffers(Document doc) {
      return doc.select(Selectors.LISTING_PAGE_PRODUCTS);
   }

   private static Element getNextPageElement(Document doc) {
      return doc.select(Selectors.NEXT_PAGE_LINK).first();
   }

   public static String getNextPageLink(Document doc) {
      Element nextPageElement = getNextPageElement(doc);
      String nextPageLink = fromUrlAttribute(nextPageElement);
      System.out.println("Next page : " + nextPageLink);
      return nextPageLink;
   }

   private static String fromUrlAttribute(Element element) {
      String url = null;
      if (element != null) url = element.attr("href");
      return cleanPath(url);
   }

   private static String getProductLink(Element item) {
      Element product = item.select(Selectors.LISTING_PAGE_PRODUCT_LINK).first();
      if (product != null) {
         return cleanPath(product.attr("href"));
      } else {
         System.out.println("Error listing");
      }
      return "";
   }

   private static String cleanPath(String path) {
      if (path == null) return null;
      path = path.replace("" + (char) 201, "%C3%89").replace(" ", "%20").replace("" + (char) 232, "%C3%A8");
      path = path.replace("" + ((char) 96), "%60").replace("" + ((char) 233), "%C3%A9").replace("" + ((char) 146), "%E2%80%99");
      if (!StringUtils.startsWith(path, "http:")) {
         path = "http://www.alcodistributions.fr" + path;

      }
      // try {
      // path = URIUtil.encodeQuery(URIUtil.decode(path));
      // } catch (URIException e) {
      // // TODO Auto-generated catch block
      // // return path;
      // System.out.println("tsssssssss mety ttttttttttttt");
      // e.printStackTrace();
      // }
      return path;
   }
}