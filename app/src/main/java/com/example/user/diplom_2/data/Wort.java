package com.example.user.diplom_2.data;

public class Wort {
    private String workName;
    private String adress;

    public Wort(String workName, String adress) {
        this.workName = workName;
        this.adress = adress;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
