package entiti.mydemik.com;
// Generated Apr 13, 2017 5:35:05 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Thajaran generated by hbm2java
 */
public class Thajaran  implements java.io.Serializable {


     private Integer idThajaran;
     private String tahun;
     private int status;
     private String semester;
     private Set surats = new HashSet(0);

    public Thajaran() {
    }

	
    public Thajaran(String tahun, int status, String semester) {
        this.tahun = tahun;
        this.status = status;
        this.semester = semester;
    }
    public Thajaran(String tahun, int status, String semester, Set surats) {
       this.tahun = tahun;
       this.status = status;
       this.semester = semester;
       this.surats = surats;
    }
   
    public Integer getIdThajaran() {
        return this.idThajaran;
    }
    
    public void setIdThajaran(Integer idThajaran) {
        this.idThajaran = idThajaran;
    }
    public String getTahun() {
        return this.tahun;
    }
    
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSemester() {
        return this.semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public Set getSurats() {
        return this.surats;
    }
    
    public void setSurats(Set surats) {
        this.surats = surats;
    }




}


