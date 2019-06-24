package com.example.getrequestexample.data;

import android.graphics.Bitmap;


public class Discount {


    //Идентификатор акции
    private int id;
    //Тип акции
    private int type;
    //Название акции
    private String name;
    //Идентификатор категории товара
    private int articleCategory;
    //Категория скидки
    private String discountCategory;
    //imageName как есть из базы (без расширения)
    private String image;
    //описание акции
    private String description;
    //дата начала акции (Date String)  в формате 2014-04-01
    private String startDate;
    //дата окончания акции (Date String)  в формате 2014-04-01
    private String endDate;
    //Единица измерения
    private String unit;
    //Цена по акции
    private double price;
    //Цена без акции
    private double oldPrice;
    //Приоритет
    private int priority;
    //imageName для "позиции дня"
    private String discountShopImage;
    //Признак алкоголя
    private boolean aclohol;
    //Наименование потребителя данных (МПМ, МК, Едадил)
    private String publisher;
    //Дата начала публикации
    private String showDate;
    //Единица измерения
    private String unit1;
    //Штрихкод
    private String barcode;

    public Bitmap getImageDiscount() {
        return imageDiscount;
    }

    public void setImageDiscount(Bitmap imageDiscount) {
        this.imageDiscount = imageDiscount;
    }

    private Bitmap imageDiscount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(int articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getDiscountCategory() {
        return discountCategory;
    }

    public void setDiscountCategory(String discountCategory) {
        this.discountCategory = discountCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDiscountShopImage() {
        return discountShopImage;
    }

    public void setDiscountShopImage(String discountShopImage) {
        this.discountShopImage = discountShopImage;
    }

    public boolean isAclohol() {
        return aclohol;
    }

    public void setAclohol(boolean aclohol) {
        this.aclohol = aclohol;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


}
