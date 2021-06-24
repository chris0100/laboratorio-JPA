package com.laboratorio.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;

    private Double precio;

    //alt+enter
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;



    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }



    public static final long serialVersionUID = 1L;
}
