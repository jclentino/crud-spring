package com.desarrollo_web.act3.controladores;

import com.desarrollo_web.act3.modelos.EmpleosModelo;
import com.desarrollo_web.act3.modelos.UsuarioModelo;
import com.desarrollo_web.act3.servicios.EmpleosServicio;
import com.desarrollo_web.act3.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleos")
public class EmpleosControlador {

    @Autowired
    private EmpleosServicio empleosServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping(path = "/All")
    public ResponseEntity<ArrayList<EmpleosModelo>> obtenerEmpleos(){
        ArrayList<EmpleosModelo> todosLosEmpleos = this.empleosServicio.obtenerEmpleos();
        return new ResponseEntity<>(todosLosEmpleos, HttpStatus.OK);
    }

    @PostMapping(path = "/Create/{userid}")
    public ResponseEntity<EmpleosModelo> guardarEmpleo (@RequestBody EmpleosModelo empleo, @PathVariable("userid") Long userid ){
        Optional<UsuarioModelo> opt = usuarioServicio.obtenerPorId(userid);
        opt.ifPresent(usuario -> {
            empleo.setUsuario(usuario);
            usuario.getEmpleos().add(empleo);
            usuarioServicio.guardarUsuario(usuario);
        });
        EmpleosModelo nuevoEmpleo = this.empleosServicio.guardarEmpleo(empleo);
        return new ResponseEntity<>(nuevoEmpleo, HttpStatus.OK);
    }

    @GetMapping(path = "/Find/{id}")
    public ResponseEntity<Optional<EmpleosModelo>> getTeacherById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.empleosServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/Edit/{id}")
    public ResponseEntity<EmpleosModelo> updateTeacherById(@RequestBody EmpleosModelo request, @PathVariable("id") Long id){
        EmpleosModelo empleo = this.empleosServicio.actualizarPorId(request, id);
        return new ResponseEntity<>(empleo, HttpStatus.OK);
    }

    @DeleteMapping(path = "/Remove/{id}")
    public ResponseEntity<String> deleteById (@PathVariable("id") Long id){
        boolean eliminado = this.empleosServicio.eliminarEmpleo(id);
        if (eliminado){
            return new ResponseEntity<>("Empleo eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al intentar eliminar el empleo", HttpStatus.OK);
        }
    }
}