package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transporte implements Serializable {
    
   @Id 
   private String nombreT;
   private int volumenCapacidad;

    public Transporte() {
    }

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
    }

    public int getVolumenCapacidad() {
        return volumenCapacidad;
    }

    public void setVolumenCapacidad(int volumenCapacidad) {
        this.volumenCapacidad = volumenCapacidad;
    }
   
}
