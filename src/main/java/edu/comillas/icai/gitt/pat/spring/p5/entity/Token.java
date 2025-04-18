package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



/**
 * TODO#3
 * Completa la entidad Token (cuya tabla en BD se llamará TOKEN)
 * para que, además de la clave primaria ya indicada (cadena autogenerada aleatoria representando la sesión),
 * tenga un campo appUser, que represente la asociación uno a uno con la entidad AppUser (el usuario asociado a la sesión).
 * Este campo deberá configurarse para que en caso de que se borre el usuario, también se borre su sesión asociada.
 */

@Entity
@Builder //Genera los constructores
@Data //Genera los getters y setters
@NoArgsConstructor  // constructor vacío requerido por JPA
@AllArgsConstructor // necesario para que @Builder funcione correctamente si no dan fallos
@Table(name = "TOKEN") //para decir a que tabla pertenece
public class Token {
    @Column(name = "ID")
    @Id @GeneratedValue(strategy = GenerationType.UUID) public String id;

    @JoinColumn(name = "APP_USER_ID", referencedColumnName = "ID", nullable = false) //Crea la columna de clave foránea de appUser, es decir coge la clave principal y la asocia a esta columna
    @OneToOne //sin especificar el tipo de fetch
    @OnDelete(action = OnDeleteAction.CASCADE) //Elimina el Token si el AppUser es eliminado
    private AppUser appUser; //es de tipo AppUser porque representa la relación entre las 2 entidades con un string no se estaría relacionando
    //Esto me permite acceder a todos los datos del usuario relacionado más tarde
}
