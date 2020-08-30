package org.example.model.dao;

import org.example.model.objects.dto.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends AbstractDAO{

    public static HotelDAO dao = null;

    private HotelDAO(){

    }

    public static HotelDAO getInstance(){
        if(dao == null){
            dao = new HotelDAO();
        }
        return dao;
    }

    public List<Hotel> getHotelByLocation(String ort){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * "
                    + "FROM oemerdb.hotel "
                    + "WHERE oemerdb.hotel.ort = \'" + ort + "\' ");
        } catch (SQLException ex){

        }
        if(rs == null) return null;

        List<Hotel> liste = new ArrayList<Hotel>();
        Hotel hotel = null;

        try{
            while(rs.next()){
                hotel = new Hotel();
                hotel.setId(rs.getInt(1));
                hotel.setName(rs.getString(2));
                hotel.setOrt(rs.getString(3));
                hotel.setDescription(rs.getString(4));
                liste.add(hotel);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return liste;
    }


}
