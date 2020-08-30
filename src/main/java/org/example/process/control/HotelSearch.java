package org.example.process.control;

import org.example.model.dao.HotelDAO;
import org.example.model.objects.dto.Hotel;

import java.util.List;

public class HotelSearch {

    Hotel hotel1 = new Hotel("Hotel Maier", 1, "Köln", "Ein schönes Hotel");
    Hotel hotel2 = new Hotel("Hotel Maritim", 2, "Bonn", "Ein wunderschönes Hotel");
    Hotel hotel3 = new Hotel("Hotel Königshof", 3, "Bonn", "Zentrales Hotel");

    private HotelSearch(){

    }

    public static HotelSearch search = null;

    public static HotelSearch getInstance(){
        if(search==null){
            search = new HotelSearch();
        }
        return search;
    }

    public List<Hotel> getHotelByOrt(String ort){
        return HotelDAO.getInstance().getHotelByLocation(ort);
    }
}
