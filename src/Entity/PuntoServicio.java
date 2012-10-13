package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PuntoServicio implements Serializable {

@Id
private String nombrePS;
private String Ciudad;

    public PuntoServicio() {
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getNombrePS() {
        return nombrePS;
    }

    public void setNombrePS(String nombrePS) {
        this.nombrePS = nombrePS;
    }

}
