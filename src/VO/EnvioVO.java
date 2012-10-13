/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.util.List;

/**
 *
 * @author CORTES
 */
public class EnvioVO {

    private String nombreE;
    private int hora;
    private boolean prioridad;
    private List<MercanciaVO> mercancias;

    public EnvioVO() {
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public List<MercanciaVO> getMercancias() {
        return mercancias;
    }

    public void setMercancias(List<MercanciaVO> mercancias) {
        this.mercancias = mercancias;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }
    
}
