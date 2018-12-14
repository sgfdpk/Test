package com.example.user.diplom_2.data;

/**
 * Класс для объекта "Страна"
 */
public class Country {
    /** Поле Флаг страны*/
    private int countryImageId;
    /** Поле Название страны */
    private String countryName;

    private String countryInfo;

    /**
     * Конструктор класса задающий параметры объекта
     * @param countryImageId - Флаг страны*
     * @param countryName - Название страны
     */


    public Country(int countryImageId, String countryName,  String countryInfo) {
        this.countryImageId = countryImageId;
        this.countryName = countryName;
        this.countryInfo = countryInfo;
    }

    /**
     * Получение флага страны
     * @return возвращает ресурс изображения
     */
    public int getCountryImageId() {
        return countryImageId;
    }

    /**
     * Задание флага страны
     * @param countryImageId - ресурс изображения
     */
    public void setCountryImageId(int countryImageId) {
        this.countryImageId = countryImageId;
    }

    /**
     * Получение названия страны
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Задание названия страны
     * @param countryName - название страны
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Получение информации о стране
     * @return информация о стране
     */
    public String getCountryInfo() {
        return countryInfo;
    }

    /**
     * Задание информации о стране
     * @param countryInfo информация о стране
     */
    public void setCountryInfo(String countryInfo) {
        this.countryInfo = countryInfo;
    }
}
