package model;

public class Product {

    private String productId;

    private String productName;

    private String productdescription;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public Product(String productId, String productName, String productdescription) {
        this.productId = productId;
        this.productName = productName;
        this.productdescription = productdescription;
    }

    public Product() {
    }
}
