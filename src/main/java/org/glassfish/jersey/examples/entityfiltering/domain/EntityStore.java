package org.glassfish.jersey.examples.entityfiltering.domain;

import org.glassfish.jersey.examples.entityfiltering.DBDataSources;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;


public final class EntityStore {

    private static Map<Long, ProductPrice> PRODUCT_PRICE = new HashMap<>();
    private static Map<String, Price> PRICES = new HashMap<>();
    static DBDataSources dbDataSources;

    static {
        ProductPrice project = createProject("Jersey", "Jersey is the open source 2.0",
                345768, LocalDate.of(2017, Month.APRIL, 1), LocalDate.of(2017, Month.APRIL, 14), Boolean.TRUE);

        dbDataSources = new DBDataSources();
        dbDataSources.dropTable();
        dbDataSources.createTable();
        dbDataSources.fillTable();
    }


    public static ProductPrice createProject(String productName, String description, Integer productPrice, LocalDate validFrom, LocalDate validTo, Boolean sell) {
        ProductPrice project = new ProductPrice(PRODUCT_PRICE.size() + 1L, productName, description, productPrice, validFrom, validTo, sell);
        PRODUCT_PRICE.put(project.getId(), project);
        return project;
    }

    public static ProductPrice getProject(Long id) {
        return PRODUCT_PRICE.get(id);
    }

    public static List<ProductPrice> getProductPrice() {
        return new ArrayList<>(PRODUCT_PRICE.values());
    }


    public static Price getPrice(String productName, LocalDate date) {
        DBDataSources dbDataSources = new DBDataSources();
        Price price = dbDataSources.getPrice(productName, date);
        return price;
    }

    public static List<Price> getListPrices(String productName) {
        DBDataSources dbDataSources = new DBDataSources();
        List<Price> priceList = dbDataSources.getListPrices(productName);
        Collections.sort(priceList);
        return priceList;
    }


    private EntityStore() {

    }
}