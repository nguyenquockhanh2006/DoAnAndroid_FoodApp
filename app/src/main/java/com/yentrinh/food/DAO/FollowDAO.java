package com.yentrinh.food.DAO;

import android.content.Context;
import android.database.Cursor;

import com.yentrinh.food.SQL;

public class FollowDAO {
    private Context context;
    private SQL dbNauAn;

    public int Insert (String idfled, String id){
        try {
            String sql = "INSERT INTO Follow VALUES ('"+idfled+"','"+id+"')";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public int Delete(String idfled , String id){
        try{
            String sql = "DELETE FROM Follow WHERE IdFled = '"+idfled+"' AND IdFl = '"+id+"'";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    public int DemFollow(String id){
        try{
            String sql = "SELECT * FROM Follow WHERE IdFled = '"+ id+"';";
            Cursor cursor = dbNauAn.GetData(sql);
            return  cursor.getCount();
        }catch (Exception e){
            return 0;
        }
    }

    public int DemLuotFollow(String id){
        try{
            String sql = "SELECT * FROM Follow WHERE IdFl = '"+ id+"';";
            Cursor cursor = dbNauAn.GetData(sql);
            return  cursor.getCount();
        }catch (Exception e){
            return 0;
        }
    }




    public FollowDAO(Context context, SQL dbNauAn) {
        this.context = context;
        this.dbNauAn = dbNauAn;
    }
    public FollowDAO(Context context){
        this.context = context;
        dbNauAn = new SQL(context);
    }
    public FollowDAO() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SQL getDbNauAn() {
        return dbNauAn;
    }

    public void setDbNauAn(SQL dbNauAn) {
        this.dbNauAn = dbNauAn;
    }
}
