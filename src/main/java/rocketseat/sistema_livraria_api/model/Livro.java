package rocketseat.sistema_livraria_api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Integer id;

    @Column(name = "titulo", length = 255, nullable = false)
    private String titulo;

    @ManyToOne()
    @JoinColumn(name = "autor_id", insertable = false, updatable = false)
    private Autor autor;

    @Column(name = "disponivel")
    private boolean disponivel = true;


    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.DATE)
    private Date dataAtualizacao;

    @Column(name = "genero")
    private String genero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.dataCadastro = now;
        this.dataAtualizacao = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = new Date();
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
