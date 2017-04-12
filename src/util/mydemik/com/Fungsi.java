/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mydemik.com;

import entiti.mydemik.com.Thajaran;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class Fungsi {
    private Integer ta_aktif;
    public int getTa() 
    {
        try
        {
            
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session s=sf.openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("FROM Thajaran where status=1");
            List resultList = q.list();
            for(Object o : resultList) 
            {
                Thajaran tj = (Thajaran)o;
                ta_aktif=tj.getIdThajaran();
            }
            s.flush();
            tx.commit();
            s.close();
            
        }
        catch (ClassCastException e) 
        {
            System.out.println("Err :" + e);
        }
        
        return ta_aktif;
    }
    
    public int getTahun() 
    {
        int year;
        Date date=new Date();
        LocalDate lc=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        year=lc.getYear();
        return year;
    }
    
    public int getBulan() 
    {
        int month;
        Date date=new Date();
        LocalDate lc=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        month=lc.getMonthValue();
        return month;
    }
    
    public int getDay() 
    {
        int day;
        Date date=new Date();
        LocalDate lc=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        day=lc.getDayOfMonth();
        return day;
    }
    
    
    
    public String hurufromawai(int bln)
    {
       int bulan = bln;
       String rom;
        switch(bulan)
        {
            case 1:  rom="I";
                     break;
            case 2:  rom="II";
                     break;
            case 3:  rom="III";
                     break;
            case 4:  rom="IV";
                     break;
            case 5:  rom="V";
                     break;
            case 6:  rom="VI";
                     break;
            case 7:  rom="VII";
                     break;
            case 8:  rom="VIII";
                     break;
            case 9:  rom="IX";
                     break;
            case 10:  rom="X";
                     break;
            case 11:  rom="XI";
                     break;
            case 12:  rom="XII";
                     break;
            default: rom = "Bulan Belum dimasukan";
            break;
        }
        
        return rom;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Fungsi f=new Fungsi();
        System.out.println(f.hurufromawai(10));
    }
}
