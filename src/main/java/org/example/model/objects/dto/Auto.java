package org.example.model.objects.dto;

public class Auto implements java.io.Serializable{

    private String marke;
    private Integer id;
    private Integer baujahr;
    private String beschreibung;

    //Zugeordneter Vertriebler, der das Auto eingestellt hat
    private User vertriebler;

    public Auto(){

    }

    public Auto(String name, Integer id, Integer ort, String beschreibung){
        this.marke = name;
        this.id = id;
        this.baujahr = ort;
        this.beschreibung = beschreibung;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getMarke(){
        return marke;
    }
    public void setMarke(String marke){
        this.marke = marke;
    }
    public Integer getBaujahr(){
        return baujahr;
    }
    public void setBaujahr(Integer baujahr){
        this.baujahr = baujahr;
    }
    public String getBeschreibung(){
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung){
        this.beschreibung = beschreibung;
    }

    public String toString(){
        return getMarke();
    }
}
