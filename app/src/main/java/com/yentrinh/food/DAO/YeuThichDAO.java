package com.yentrinh.food.DAO;

import android.content.Context;
import android.database.Cursor;

import com.yentrinh.food.SQL;

public class YeuThichDAO {
    private Context context;
    private SQL dbNauAn;

    public YeuThichDAO(Context context, SQL dbNauAn) {
        this.context = context;
        this.dbNauAn = dbNauAn;
    }
    public YeuThichDAO(Context context){
        this.context = context;
        dbNauAn = new SQL(context);
    }
    public YeuThichDAO() {
    }

    public int Insert (String idmon, String idnguoi){
        try {
            String sql = "INSERT INTO YeuThich VALUES ('"+idmon+"','"+idnguoi+"')";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    public Cursor GETSLfull(String IdChuKho){
        Cursor cursor = null;
        try{
            String sql = "SELECT * FROM YeuThich WHERE IdChuKho = '"+ IdChuKho +"'";
            cursor = dbNauAn.GetData(sql);
            return  cursor;
        }catch (Exception e){
            return cursor;
        }
    }

    public Cursor GETSL(String idmon, String idnguoi){
        Cursor cursor = null;
        try{
            String sql = "SELECT * FROM YeuThich WHERE MaMon = '"+ idmon +"' AND IDChuKho = '"+ idnguoi +"'";
            cursor = dbNauAn.GetData(sql);
            return  cursor;
        }catch (Exception e){
            return cursor;
        }
    }

    public int Delete(String idmon , String idnguoi){
        try{
            String sql = "DELETE FROM YeuThich WHERE MaMon = '"+idmon+"' AND IdChuKho = '"+idnguoi+"'";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
