package edu.comillas.icai.gitt.pat.spring.p5.entity;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 */
@Entity
@Builder //Genera los constructores
@Data //Genera los getters y setters
@NoArgsConstructor  // constructor vacío requerido por JPA
@AllArgsConstructor // necesario para que @Builder funcione correctamente si no dan fallos
@Table(name = "APP_USER") //para decir a que tabla pertenece
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false) // Esto hace que no pueda ser NULL , (no haria falta poner unique porque ya genera irrepetibles)
    private long id;

    @Column(name = "EMAIL", nullable = false, unique = true)// Esto hace que no pueda ser NULL e irrepetible
    private String email; //Que sea email ya se comprueba en el model

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) //Para que lo guarde como una cadena de texto string indicando si es user o admin
    @Column(name = "ROL", nullable = false)
    private Role role;

    @Column(name = "NAME", nullable = false)
    private String name;


}

















