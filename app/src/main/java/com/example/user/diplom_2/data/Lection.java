package com.example.user.diplom_2.data;

/**
 * Класс для хранения объекта "Лекция" и его свойств
 */
public class Lection {
    /** Название лекции*/
    private String lectionName;
    private boolean isChecked;
    /** Ссылка на файл лекции*/
    private String adress;

    /**
     * Конструктор класса
     * @param lectionName - Название лекции
     * @param adress - Ссылка на файл лекции
     */
    public Lection(String lectionName, String adress) {
        this.lectionName = lectionName;
        this.adress = adress;
    }

    public Lection(String lectionName, boolean isChecked) {
        this.lectionName = lectionName;
        this.isChecked = isChecked;
    }

    /**
     * Получение названия лекции
     * @return Название лекции
     */
    public String getLectionName() {
        return lectionName;
    }


    /**
     * Задание названия лекции
     * @param lectionName - Название лекции
     */
    public void setLectionName(String lectionName) {
        this.lectionName = lectionName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
