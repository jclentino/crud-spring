package com.desarrollo_web.act3.servicios;

import com.desarrollo_web.act3.modelos.UsuarioModelo;
import com.desarrollo_web.act3.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public ArrayList<UsuarioModelo> obtenerUsuarios(){
        return (ArrayList<UsuarioModelo>)  usuarioRepositorio.findAll();
    }

    public UsuarioModelo guardarUsuario(UsuarioModelo usuario){
        return usuarioRepositorio.save(usuario);
    }

    public Optional<UsuarioModelo> obtenerPorId(Long id){
        return usuarioRepositorio.findById(id);
    }

    public UsuarioModelo actualizarPorId(UsuarioModelo req, Long id){
        UsuarioModelo usuario = usuarioRepositorio.findById(id).orElse(null);
        if (usuario != null){
            usuario.setClave(req.getClave());
            usuario.setNombre(req.getNombre());
            usuario.setTelefono(req.getTelefono());
            usuario.setEmail(req.getEmail());
            usuarioRepositorio.save(usuario);

            return  usuario;
        }

        return null;
    }

    public Boolean eliminarUsuario (Long id){
        Optional<UsuarioModelo> opt = usuarioRepositorio.findById(id);
        if (opt.isPresent()){
            usuarioRepositorio.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}