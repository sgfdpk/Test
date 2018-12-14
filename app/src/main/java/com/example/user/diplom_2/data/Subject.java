package com.example.user.diplom_2.data;

/**
 * Класс для хранения объекта "Предмет" и его свойств
 */
public class Subject {
    /** Поле для хранения id иконки предмета*/
    private int subjectImageId;
    private int progress;
    /** Поле для хранения id иконки предмета*/
    private String subjectName;
    private String subjectProgress;

    /**
     * Метод для получения иконки
     * @return возвращает id ресурса изображения
     */
    public int getSubjectImageId() {
        return subjectImageId;
    }

    /**
     * Метод для задания иконки
     * @param subjectImageId - id ресурса изображения
     */
    public void setSubjectImageId(int subjectImageId) {
        this.subjectImageId = subjectImageId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * Метод для получения названия
     * @return возвращает название предмета
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Метод для задания названия
     * @param subjectName - название предмета
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectProgress() {
        return subjectProgress;
    }

    public void setSubjectProgress(String subjectProgress) {
        this.subjectProgress = subjectProgress;
    }

    /**
     * Конструктор класса
     * @param subjectImageId - id ресурса изображения
     * @param progress - прогресс предмета
     * @param subjectName - название предмета
     * @param subjectProgress - прогресс предмета
     */
    public Subject(int subjectImageId, int progress, String subjectName, String subjectProgress) {
        this.subjectImageId = subjectImageId;
        this.progress = progress;
        this.subjectName = subjectName;
        this.subjectProgress = subjectProgress;


    }
}
