package com.example.user.diplom_2.data;

/**
 * Класс для типов аттракций
 */
public class AttractionItem {
    /** Поле название */
    private String attractName;
    /** Поле иконка */
    private int imgID;

    /**
     * Конструктор класса задающий параметры
     * @param attractName - название
     * @param imgID - иконка
     */
    public AttractionItem(String attractName, int imgID) {
        this.attractName = attractName;
        this.imgID = imgID;
    }

    /**
     * Получение накзвания типа аттракции
     * @return тип аттракции
     */
    public String getAttractName() {
        return attractName;
    }

    /**
     * Задание накзвания типа аттракции
     * @param attractName название тип String
     */
    public void setAttractName(String attractName) {
        this.attractName = attractName;
    }

    /**
     * Получение иконки типа аттракции
     * @return иконка аттракции
     */
    public int getImgID() {
        return imgID;
    }

    /**
     * Задание иконки типа аттракции
     * @param imgID изображение тип int
     */
    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
