package com.example.myreminder;

public class ToDo {

    String titleMR, descMR, dueDateMR, idMR;

    public ToDo(){

    }

    public ToDo(String titleMR, String descMR, String dueDateMR, String idMR) {
        this.titleMR = titleMR;
        this.descMR = descMR;
        this.dueDateMR = dueDateMR;
        this.idMR = idMR;
    }

    public String getTitleMR() {
        return titleMR;
    }

    public void setTitleMR(String titleMR) {
        this.titleMR = titleMR;
    }

    public String getDescMR() {
        return descMR;
    }

    public void setDescMR(String descMR) {
        this.descMR = descMR;
    }

    public String getDueDateMR() {
        return dueDateMR;
    }

    public void setDueDateMR(String dueDateMR) {
        this.dueDateMR = dueDateMR;
    }

    public String getIdMR() {
        return idMR;
    }

    public void setIdMR(String idMR) {
        this.idMR = idMR;
    }
}
