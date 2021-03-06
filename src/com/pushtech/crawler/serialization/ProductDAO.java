package com.pushtech.crawler.serialization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.pushtech.commons.Product;
import com.pushtech.crawler.launcher.DataBaseUpdaterHelper;
import com.pushtech.crawler.launcher.DataBaseUpdaterHelper.UpdateWay;

public class ProductDAO extends AbstractDAOEntity {

   private static final String TABLE_NAME = "grossiste";
   private static final String INSERT_REQUEST = initInsertRequest();
   private static final String READ_REQUEST = initReadRequest();
   private static final String SEARCH_REQUEST = initSearchRequest();
   private static final String UPDATE_REQUEST = initUpdateRequest();

   private DAOFactory daoFactory = null;

   public ProductDAO(DAOFactory daoFactory) {
      this.daoFactory = daoFactory;
   }

   public Product searchEntity(Product siteProduct) {
      return searchEntity(siteProduct.getProductId());
   }

   @Override
   public Product searchEntity(String entityIdentifier) {
      Product product = null;
      Connection connection = null;
      PreparedStatement searchStatement = null;
      ResultSet resultSet = null;
      try {
         connection = daoFactory.getConnection();
         searchStatement = initPreparedRequest(connection, SEARCH_REQUEST, false, entityIdentifier);
         resultSet = searchStatement.executeQuery();
         while (resultSet.next()) {
            product = mapper(resultSet);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return product;
   }

   @Override
   public int saveEntity(Product product) {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      int status = 0;
      try {
         connection = daoFactory.getConnection();
         preparedStatement = initPreparedRequest(connection, INSERT_REQUEST, true, product.getProductId(), validate(product.getName()), validate(product.getLink()), validate(product.getImage()), validate(product.getDescription()), validate(product.getKeyWord()), product.getPrice(), product.getShippingCost(), product.getShippingDelay(), validate(product.getBrand()), validate(product.getCategory()), product.getQuantity(), getNowDate());
         status = preparedStatement.executeUpdate();
         if (status == 0) {
            System.err.println("Save product failed");
         } else System.out.println("Save product passed");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return status;
   }

   // @Override
   // public void setEntity(AbstractDAOEntity abstractDAOEntity) {
   // // TODO Auto-generated method stub
   //
   // }

   public AbstractDAOEntity getAbstractDAOEntity(DAOFactory DAOFactoryInstance) {
      // TODO Auto-generated method stub
      return null;
   }

   public static PreparedStatement initPreparedRequest(Connection connection, String request, boolean returnGeneratedKeys, Object... objects) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(request, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
      for (int i = 0; i < objects.length; i++) {
         preparedStatement.setObject(i + 1, objects[i]);
      }
      return preparedStatement;
   }

   private Product mapper(ResultSet resultSet) throws SQLException {
      Product product = new Product();
      product.setProductId(resultSet.getString("productId"));
      product.setParentId(resultSet.getString("parentId"));
      product.setName(resultSet.getString("name"));
      product.setLink(resultSet.getString("link"));
      product.setDescription(resultSet.getString("description"));
      product.setBrand(resultSet.getString("brand"));
      product.setCategory(resultSet.getString("category"));
      product.setImage(resultSet.getString("image"));
      product.setKeyWord(resultSet.getString("keyWord"));
      product.setPrice(resultSet.getFloat("price"));
      product.setShippingCost(resultSet.getFloat("shippingCost"));
      product.setShippingDelay(resultSet.getInt("shippingDealy"));
      product.setQuantity(resultSet.getInt("quantity"));
      product.setColor(resultSet.getString("color"));
      product.setSize(resultSet.getString("size"));
      product.setUpdated(resultSet.getString("updateTime"));//

      return product;
   }

   @Override
   public int setEntity(Product product) {
      return 0;
   }

   private static String initInsertRequest() {
      String insertRequest = "INSERT INTO " + TABLE_NAME + " ";
      insertRequest += "(productId, parentId, name, description, keyWord, link, image, price, olprice, shippingCost, shippingDelay, category, quantity, brand, color, size, updatetime)";

      insertRequest += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      return insertRequest;
   }

   private static String initUpdateRequest() {
      String updateRequest = "UPDATE " + TABLE_NAME + " SET";
      updateRequest += " , " + "price = ?";
      updateRequest += " , " + "shippingCost = ?";
      updateRequest += " , " + "shippingDealy = ?";
      updateRequest += " , " + "quantity = ?";
      updateRequest += " , " + "updatetime = ?";
      updateRequest += " WHERE productId = ? ";
      return updateRequest;
   }

   @Override
   public int updateEntity(Product siteProduct) {
      Connection connection = null;
      PreparedStatement updateStatement = null;
      int status = 0;
      Product databaseProduct = null;
      Product product;
      try {
         databaseProduct = searchEntity(siteProduct);
         if (databaseProduct != null) {
            System.out.println("Existing product");
            DataBaseUpdaterHelper dbuh = DataBaseUpdaterHelper.getUpdaterMode(true, UpdateWay.SIMPLE_UPDATE);
            product = dbuh.updateProduct(siteProduct, databaseProduct);
            connection = daoFactory.getConnection();
            updateStatement = initPreparedRequest(connection, UPDATE_REQUEST, true, product.getPrice(), product.getShippingCost(), product.getShippingDelay(), product.getQuantity(), getNowDate(), product.getProductId());
            status = updateStatement.executeUpdate();
            if (status != 0) System.out.println("Update passed");
            else System.out.println("Update failed");
         } else {
            System.out.println("New product");
            saveEntity(siteProduct);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return status;
   }

   private static String initSearchRequest() {
      String searchRequest = "SELECT * FROM " + TABLE_NAME + " WHERE productId = ? ";
      return searchRequest;
   }

   private static String initReadRequest() {
      final String readRequest = "SELECT * FROM " + TABLE_NAME + " LIMIT 10";
      return readRequest;
   }

   private String getNowDate() {
      DateTime dt = new DateTime();
      DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
      return dt.toString(df);
   }

   private String validate(String fieldValue) {
      return (fieldValue == null) ? "" : fieldValue;
   }

}
