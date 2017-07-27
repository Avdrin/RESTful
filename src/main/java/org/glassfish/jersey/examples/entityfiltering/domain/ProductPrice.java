package org.glassfish.jersey.examples.entityfiltering.domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@SuppressWarnings({"JavaDoc", "UnusedDeclaration"})
@XmlRootElement
public class ProductPrice {

    private Long id;
    private String productName;
    private String description;
    private Integer productPrice;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean sell;



    public ProductPrice() {
    }

    public ProductPrice(Long id, String productName, String description, Integer productPrice,
                        LocalDate validFrom, LocalDate validTo, Boolean sell) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productPrice = productPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.sell = sell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean getSell() {
        return sell;
    }

    public void setSell(Boolean sell) {
        this.sell = sell;
    }
}
