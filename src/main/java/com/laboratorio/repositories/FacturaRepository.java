package com.laboratorio.repositories;

import com.laboratorio.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Long> {

    Factura findFacturaByCreateAt(Date fecha);

}
