package br.com.itau.model;

import br.com.itau.enumerator.Boards;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String name;

    private Boards board;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

}
