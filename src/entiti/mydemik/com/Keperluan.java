package entiti.mydemik.com;
// Generated Apr 13, 2017 5:35:05 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Keperluan generated by hbm2java
 */
public class Keperluan  implements java.io.Serializable {


     private Integer idKeperluan;
     private String keperluan;
     private Set surats = new HashSet(0);

    public Keperluan() {
    }

	
    public Keperluan(String keperluan) {
        this.keperluan = keperluan;
    }
    public Keperluan(String keperluan, Set surats) {
       this.keperluan = keperluan;
       this.surats = surats;
    }
   
    public Integer getIdKeperluan() {
        return this.idKeperluan;
    }
    
    public void setIdKeperluan(Integer idKeperluan) {
        this.idKeperluan = idKeperluan;
    }
    public String getKeperluan() {
        return this.keperluan;
    }
    
    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }
    public Set getSurats() {
        return this.surats;
    }
    
    public void setSurats(Set surats) {
        this.surats = surats;
    }

    @Override
    public String toString() {
        return keperluan;
    }



}


