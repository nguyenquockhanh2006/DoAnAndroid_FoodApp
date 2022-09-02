package com.yentrinh.food.DAO;

import android.content.Context;
import android.database.Cursor;

import com.yentrinh.food.Model.MonAn;
import com.yentrinh.food.Model.TaiKhoan;
import com.yentrinh.food.SQL;

public class MonAnDAO {
    private Context context;
    private SQL dbNauAn;

    public MonAnDAO(Context context) {
        this.context = context;
        dbNauAn = new SQL(context);
    }
    public MonAnDAO() {

        dbNauAn = new SQL();
    }
    public int InsertMon(MonAn t) // thêm dữ liệu
    {
        try{
            String sql = "INSERT INTO TaiKhoan VALUES('"+t.getMamon()+"','"+t.getTenMon()+"','"+t.getLoai()+"','"+t.getNguoiDang()+"','"+t.getCongThuc()+"','"+t.getHinhAnh() + "'" +")";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception ex){
            return 0;
        }
    }
    public Cursor GetMon(String id){
        Cursor cs =  null;
        try{
            String sql= " SELECT * FROM MonAn WHERE MaMon ="+"'"+id+"'";
            cs = dbNauAn.GetData(sql);
            return cs;
        }catch (Exception ex){
            return cs;
        }
    }

    public Cursor GetMonid(String id){
        Cursor cs =  null;
        try{
            String sql= " SELECT * FROM MonAn WHERE IDNguoiDang ="+"'"+id+"'";
            cs = dbNauAn.GetData(sql);
            return cs;
        }catch (Exception ex){
            return cs;
        }
    }

    public Cursor GetLoai(String id){
        Cursor cs =  null;
        try{
            String sql= " SELECT * FROM MonAn WHERE MaLoai LIKE "+"'"+id+"'";
            cs = dbNauAn.GetData(sql);
            return cs;
        }catch (Exception ex){
            return cs;
        }
    }

    public Cursor GETALL(){
        Cursor cs =  null;
        try{
            String sql= " SELECT * FROM MonAn";
            cs = dbNauAn.GetData(sql);
            return cs;
        }catch (Exception ex){
            return cs;
        }
    }


    public int UpdateMon(String sql){
        try{
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception ex){
            return 0;
        }

    }
    public void UpdateToMon(String MaMon, String TenMon, String MaLoai, String IDNguoiDang, String CongThuc, byte[] hinh){
        dbNauAn.UpdateToMon(MaMon,TenMon,MaLoai, IDNguoiDang,CongThuc,hinh);
    }
}
