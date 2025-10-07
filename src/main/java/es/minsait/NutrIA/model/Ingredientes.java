package es.minsait.NutrIA.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredientes")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private double calorias;
    private double proteinas;
    private double gorduras;
    private double carboidratos;

    public Ingredientes(){}
    public Ingredientes(Long id, String nome, double calorias, double proteinas, double gorduras, double carboidratos) {
        this.id = id;
        this.nome = nome;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.gorduras = gorduras;
        this.carboidratos = carboidratos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double gorduras) {
        this.gorduras = gorduras;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double carboidratos) {
        this.carboidratos = carboidratos;
    }
}
