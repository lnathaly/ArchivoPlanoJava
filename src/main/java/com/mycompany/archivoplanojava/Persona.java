/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivoplanojava;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Alexander Martinez
 */
public class Persona {
    private int cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private List<Reporte> reporte;

    public Persona(int cedula, String nombre, String apellido, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
         List<Reporte> reporte = new ArrayList();
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Reporte> getReporte() {
        return reporte;
    }

    public void setReporte(List<Reporte> reporte) {
        this.reporte = reporte;
    }
    
    
    
    
}
