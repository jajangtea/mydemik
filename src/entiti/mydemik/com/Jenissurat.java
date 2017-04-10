package entiti.mydemik.com;
// Generated Mar 28, 2017 6:13:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Jenissurat generated by hbm2java
 */
public class Jenissurat  implements java.io.Serializable {


     private Integer idJenis;
     private String kodeJenis;
     private String jenisSurat;
     private Set surats = new HashSet(0);

    public Jenissurat() {
    }

	
    public Jenissurat(String kodeJenis, String jenisSurat) {
        this.kodeJenis = kodeJenis;
        this.jenisSurat = jenisSurat;
    }
    public Jenissurat(String kodeJenis, String jenisSurat, Set surats) {
       this.kodeJenis = kodeJenis;
       this.jenisSurat = jenisSurat;
       this.surats = surats;
    }
   
    public Integer getIdJenis() {
        return this.idJenis;
    }
    
    public void setIdJenis(Integer idJenis) {
        this.idJenis = idJenis;
    }
    public String getKodeJenis() {
        return this.kodeJenis;
    }
    
    public void setKodeJenis(String kodeJenis) {
        this.kodeJenis = kodeJenis;
    }
    public String getJenisSurat() {
        return this.jenisSurat;
    }
    
    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }
    public Set getSurats() {
        return this.surats;
    }
    
    public void setSurats(Set surats) {
        this.surats = surats;
    }

    @Override
    public String toString(){
        return jenisSurat;
    }


}


