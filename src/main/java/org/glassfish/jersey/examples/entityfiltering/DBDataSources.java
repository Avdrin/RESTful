package org.glassfish.jersey.examples.entityfiltering;

import org.glassfish.jersey.examples.entityfiltering.domain.Price;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DBDataSources {

    DBConnection dbConnection = new DBConnection();

    public void dropTable() {
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            String sql = "DROP TABLE IF EXISTS testTable";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }
        public void createTable() {
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            String sql = "CREATE TABLE testTable (id IDENTITY NOT NULL, title VARCHAR(255) NOT NULL, price DECIMAL(10,2), dateFrom DATE, dateTo DATE, sell BOOLEAN DEFAULT FALSE NOT NULL);";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public void fillTable() {
        Statement statement;
        try {
            statement = dbConnection.getConnection().createStatement(); //connection.createStatement();
            String sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per1',10.50,'2017-04-13','2017-04-23', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per4',243.90,'2017-05-13','2017-05-16', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per2',11.20,'2017-06-13','2017-06-30', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per4',340.10,'2017-07-13','2017-07-29', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per3',12.30,'2017-08-13','2017-08-28', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per4',23.50,'2017-09-13','2017-09-27', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per5',16.20,'2017-10-13','2017-10-26', TRUE)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO testTable (title,price,dateFrom,dateTo,sell) VALUES('per3',190.20,'2017-11-13','2017-11-24', TRUE)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Price> getListPrices(String productName) {
        Statement statement;
        ResultSet resultSet = null;
        List<Price> priceList = new ArrayList<>();
        try {
            statement = dbConnection.getConnection().createStatement();

            String sql = "SELECT id, title, price, dateFrom, dateTo, sell FROM testTable WHERE title="+"'"+productName+"'";  //id, namepr, price
            resultSet = statement.executeQuery(sql);


            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate lDateFrom;
            LocalDate lDateTo;
            Float p;
            String n;
            Boolean b;
            while (resultSet.next()){
                lDateFrom = LocalDate.parse(resultSet.getString(4),format);
                lDateTo = LocalDate.parse(resultSet.getString(5),format);
                p = Float.valueOf(resultSet.getString(3));
                n = resultSet.getString(2);
                b = resultSet.getBoolean(6);
                Price price = new Price(n, BigDecimal.valueOf(p),lDateFrom,lDateTo,b);
                priceList.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return priceList;
    }




    public Price getPrice(String productName, LocalDate date) {
        Statement statement;
        ResultSet resultSet = null;
        List<Price> priceList = new ArrayList<>();
        Price price = null;

        try {
            statement = dbConnection.getConnection().createStatement();
            String sql = "SELECT id, title, price, dateFrom, dateTo, sell FROM testTable WHERE title="+"'"+productName+"'" +
                    " AND dateFrom <="+ "'" + date + "'" + " AND dateTo >=" + "'" + date + "'";
            resultSet = statement.executeQuery(sql);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate lDateFrom;
            LocalDate lDateTo;
            Float p;
            String n;
            Boolean b;
            while (resultSet.next()){
                lDateFrom = LocalDate.parse(resultSet.getString(4),format);
                lDateTo = LocalDate.parse(resultSet.getString(5),format);
                p = Float.valueOf(resultSet.getString(3));
                n = resultSet.getString(2);
                b = resultSet.getBoolean(6);
                price = new Price(n, BigDecimal.valueOf(p),lDateFrom,lDateTo,b);
                priceList.add(price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

}
