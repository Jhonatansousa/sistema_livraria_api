package rocketseat.sistema_livraria_api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;


    private Date dataEmprestimo;
    private Date dataDevolucao;


}
