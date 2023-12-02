package com.desarrollo_web.act3.controladores;

import com.desarrollo_web.act3.modelos.UsuarioModelo;
import com.desarrollo_web.act3.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping(path = "/All")
    public ResponseEntity<ArrayList<UsuarioModelo>> obtenerUsuarios(){
        return new ResponseEntity<>(this.usuarioServicio.obtenerUsuarios(), HttpStatus.OK);
    }

    @PostMapping(path = "/Create")
    public ResponseEntity<UsuarioModelo> guardarUsuario (@RequestBody UsuarioModelo usuario){
        return new ResponseEntity<>(this.usuarioServicio.guardarUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping(path = "/Find/{id}")
    public ResponseEntity<Optional<UsuarioModelo>> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.usuarioServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping(path = "/Edit/{id}")
    public ResponseEntity<UsuarioModelo> updateUserById(@RequestBody UsuarioModelo req, @PathVariable("id") Long id){
        return new ResponseEntity<>(this.usuarioServicio.actualizarPorId(req, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/Remove/{id}")
    public ResponseEntity<String> deleteById (@PathVariable("id") Long id){
        boolean eliminado = this.usuarioServicio.eliminarUsuario(id);
        if (eliminado){
            return new ResponseEntity<>("Eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al intentar eliminar al usuario", HttpStatus.OK);
        }
    }
}