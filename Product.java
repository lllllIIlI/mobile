package com.inhatc.project_android;

public class Product {
    private String id;
    private String name;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int imageResId; // 이미지 리소스 ID 필드 추가

    // 생성자와 getter, setter 메서드 생략

    public Product(String name, int price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public Product(String name, int price, int imageResId,String id) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
