package gm.zona_fit.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data //Genera metodos Get y Set
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer ClienteID;
    public String Nombre;
    public String Apellido;
    public Integer Membresia;
}
