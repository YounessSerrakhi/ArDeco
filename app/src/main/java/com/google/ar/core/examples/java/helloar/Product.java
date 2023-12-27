package com.google.ar.core.examples.java.helloar;

public class Product {
        private String name;
        private String imageResourceId;
        private float price;
        private String img1;
        private String img2;
        private String img3;

    public Product(String name, String imageResourceId, String img1, String img2, String img3, float price) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.price = price;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public Product(String name, String imageResourceId) {
            this.name = name;
            this.imageResourceId = imageResourceId;
        }

        public String getName() {
            return name;
        }

        public String getImageResourceId() {
            return imageResourceId;
        }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", imageResourceId='" + imageResourceId + '\'' +
                ", price=" + price +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                '}';
    }
}
