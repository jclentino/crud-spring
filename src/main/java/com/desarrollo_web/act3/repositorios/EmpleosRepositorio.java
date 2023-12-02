package com.desarrollo_web.act3.repositorios;

import com.desarrollo_web.act3.modelos.EmpleosModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleosRepositorio extends JpaRepository<EmpleosModelo, Long> { }