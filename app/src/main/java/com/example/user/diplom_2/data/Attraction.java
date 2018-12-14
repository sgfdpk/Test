package com.example.user.diplom_2.data;

/**
 * Класс для объекта Аттракия
 */
public class Attraction {
    private int attractionImageId;
    /** Поле название аттракции*/
    private  String name;
    /** Поле Описание аттракции*/
    private String description;
    /** Поле адресс аттракции*/
     private String adress;
    /** Поле ссылка на сайт аттракции*/
     private String url;
     private float latitude;
     private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Конструктор класса задающий параметры объекта
     * @param name название аттракции
     * @param description Описание аттракции
     * @param adress адресс аттракции
     */
    public Attraction(String name, String description, String adress) {
        this.name = name;
        this.description = description;
        this.adress = adress;
    }

    public Attraction(String name, String description, String adress, String url, float latitude, float longitude) {
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Конструктор класса задающий параметры объекта
     * @param name  название аттракции
     * @param description oписание аттракции
     * @param adress адресс аттракции
     * @param url ссылка на сайт аттракции*
     */
    public Attraction(String name, String description, String adress,String url) {
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.url=url;
    }

    public int getAttractionImageId() {
        return attractionImageId;
    }

    public void setAttractionImageId(int attractionImageId) {
        this.attractionImageId = attractionImageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
