package org.example.model.objects.dto;

public class Hotel implements java.io.Serializable{

    private String name;
    private Integer id;
    private String ort;
    private String description;

    public Hotel(){

    }

    public Hotel(String name, Integer id, String ort, String description){
        this.name = name;
        this.id = id;
        this.ort = ort;
        this.description = description;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getOrt(){
        return ort;
    }
    public void setOrt(String ort){
        this.ort = ort;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){
        return getName();
    }
}
