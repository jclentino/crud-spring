package com.desarrollo_web.act3.servicios;

import com.desarrollo_web.act3.modelos.EmpleosModelo;
import com.desarrollo_web.act3.modelos.UsuarioModelo;
import com.desarrollo_web.act3.repositorios.EmpleosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleosServicio {
    @Autowired
    EmpleosRepositorio empleosRepositorio;

    public ArrayList<EmpleosModelo> obtenerEmpleos(){
        return (ArrayList<EmpleosModelo>)  empleosRepositorio.findAll();
    }

    public EmpleosModelo guardarEmpleo(EmpleosModelo empleo){
        return empleosRepositorio.save(empleo);
    }

    public Optional<EmpleosModelo> obtenerPorId(Long id){
        return empleosRepositorio.findById(id);
    }

    public EmpleosModelo actualizarPorId(EmpleosModelo request, Long id){
        EmpleosModelo empleo = empleosRepositorio.findById(id).orElse(null);

        if (empleo != null){
            if (empleo.getNombre() != null){
                empleo.setNombre(request.getNombre());
            }

            if (empleo.getCategoria() != null){
                empleo.setCategoria(request.getCategoria());
            }

            if (empleo.getAreaTrabajo() != null){
                empleo.setAreaTrabajo(request.getAreaTrabajo());
            }

            if (empleo.getNivel() != null){
                empleo.setEmpresa(request.getEmpresa());
            }

            if (empleo.getNivel() != null){
                empleo.setNivel(request.getNivel());
            }

            if (empleo.getSueldo() != null){
                empleo.setSueldo(request.getSueldo());
            }

            if (empleo.getFunciones() != null){
                empleo.setFunciones(request.getFunciones());
            }

            if (empleo.getCargoJefe() != null){
                empleo.setCargoJefe(request.getCargoJefe());
            }

            empleosRepositorio.save(empleo);
            return  empleo;
        } else {
            return null;
        }
    }

    public Boolean eliminarEmpleo (Long id){
       Optional<EmpleosModelo> opt = empleosRepositorio.findById(id);
       if (opt.isPresent()){
           empleosRepositorio.deleteById(id);
           return true;
       } else {
           return false;
       }
    }
}