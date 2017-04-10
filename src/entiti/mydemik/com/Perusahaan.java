package entiti.mydemik.com;
// Generated Apr 10, 2017 12:09:36 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Perusahaan generated by hbm2java
 */
public class Perusahaan  implements java.io.Serializable {


     private Integer idPerusahaan;
     private String namaPerusahaan;
     private String alamat;
     private String tlp;
     private Set surats = new HashSet(0);

    public Perusahaan() {
    }

	
    public Perusahaan(String namaPerusahaan, String alamat, String tlp) {
        this.namaPerusahaan = namaPerusahaan;
        this.alamat = alamat;
        this.tlp = tlp;
    }
    public Perusahaan(String namaPerusahaan, String alamat, String tlp, Set surats) {
       this.namaPerusahaan = namaPerusahaan;
       this.alamat = alamat;
       this.tlp = tlp;
       this.surats = surats;
    }
   
    public Integer getIdPerusahaan() {
        return this.idPerusahaan;
    }
    
    public void setIdPerusahaan(Integer idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }
    public String getNamaPerusahaan() {
        return this.namaPerusahaan;
    }
    
    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }
    public String getAlamat() {
        return this.alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getTlp() {
        return this.tlp;
    }
    
    public void setTlp(String tlp) {
        this.tlp = tlp;
    }
    public Set getSurats() {
        return this.surats;
    }
    
    public void setSurats(Set surats) {
        this.surats = surats;
    }




}


