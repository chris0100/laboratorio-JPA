package com.laboratorio.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 4, max = 20, message = "La longitud del nombre no es valida, debe estar entre 4 y 20 caracteres")
    private String nombre;

    @Column(name = "apellido")
    @NotEmpty(message = "El apellido no puede estar vacio")
    @Size(min = 4, max = 20, message = "La longitud del apellido no es valida, debe estar entre 4 y 20 caracteres")
    private String apellido;


    @Email(message = "El email no es valido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha no puede estar vacia")
    private Date createAt;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //
    private List<Factura> facturas;


    private String foto;




    public static final long serialVersionUID = 1L;


}
