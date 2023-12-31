package com.desarrollo_web.act3.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class UsuarioModelo {

    @Id
    @Column(name = "cedula")
    private int cedula;

    @Column
    private String clave;

    @Column
    private String nombre;

    @Column
    private String telefono;

    @Column
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EmpleosModelo> empleos = new ArrayList<EmpleosModelo>();

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public List<EmpleosModelo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(List<EmpleosModelo> empleo) {
        this.empleos = empleo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}