package com.pushtech.crawler.launcher;

import static com.pushtech.crawler.launcher.CrawlListing.getNextPageLink;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.connection.ConnectionHandler;
import com.pushtech.crawler.connection.EngineContext;
import com.pushtech.crawler.parsing.ParserFactory;
import com.pushtech.crawler.serialization.AbstractDAOEntity;
import com.pushtech.crawler.serialization.DAOFactory;
import com.pushtech.crawler.serialization.DataBaseDAO;
import com.pushtech.crawler.serialization.ProductDAO;

public class Crawler {

   public static void main(String[] args) {

      try {

         ArrayList<Product> products = new ArrayList<Product>();

         Page page = null;

         String url = "http://www.grossiste-en-ligne.com/vetement-femme";

         try {
            boolean continueCrawl=true;
            while (continueCrawl) {

               page = getPageFromUrl(url, EngineContext.MethodType.GET_METHOD);

               if (PageType.isProductPage(page)) {
                  Product product = new CrawlOffer().doAction(page);
                  products.add(product);
                  continueCrawl = false;
               } else if (PageType.isListingPage(page)) {
                  int indexProduit = 0;
                  for (String link : CrawlListing.getProductLinks(page)) {
                     System.out.println("-------------------- Produit n* " + indexProduit + " --------------------");

                     Product product = new Product();
                     System.out.println("Link : " + link);
                     String productId = getIdFromLink(link);
                     System.out.println("Product Id :" + productId);

                     try {
                        Page productPage = getPageFromUrl(link, EngineContext.MethodType.GET_METHOD);
                        product = new CrawlOffer().doAction(productPage);
                        product.setLink(link);
                        product.setProductId(productId);

                        DAOFactory daoFactory = new DataBaseDAO().getFactoryInstance();
                        AbstractDAOEntity daoEntity = new ProductDAO(daoFactory);
                        daoEntity.updateEntity(product);
                        indexProduit++;
                        // break;
                     } catch (Exception e) {
                        System.out.println("error =>>> IMPOSSIBLE DE SE CONNECTER");
                     }

                  }
                  url = getNextPageLink(page.getDoc());
                  continueCrawl = url != null ? true : false;
               } else continueCrawl = false;
            }


         } catch (Exception e) {

         }

         // CSVService csvService = new CSVService();
         // csvService.buildCSV(products, ";");

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
      }
   }



   private static String cleanPath(String path) {
      if (path == null) return null;
      path = path.replace("" + (char) 201, "%C3%89").replace(" ", "%20").replace("" + (char) 232, "%C3%A8");
      path = path.replace("" + ((char) 96), "%60").replace("" + ((char) 233), "%C3%A9").replace("" + ((char) 146), "%E2%80%99");
      if (!StringUtils.startsWith(path, "http:")) {
         path = "http://www.grossiste-en-ligne.com" + path;

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

   private static Page getPageFromUrl(final String url, EngineContext.MethodType methodeType) {
      Page page = null;
      HttpResponse response = null;
      response = ConnectionHandler.getResponse(url, null, null, methodeType);
      page = (Page) ParserFactory.getAppropriateParsingTemplate(response).parse(url, response, null);
      return page;
   }

   private static String getIdFromLink(String url) {
      String id = null;
      id = url.substring(url.lastIndexOf("/")+1);
      id = id.substring(0, id.indexOf("-"));
      System.out.println("Id : " + id);
      return id;
   }

}
