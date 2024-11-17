package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "copias")
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado; // "disponible", "dañada", etc.

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Constructores
    public Copia() {
    }

    public Copia(String estado, int cantidad, Pelicula pelicula, Usuario usuario) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.pelicula = pelicula;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Copia{id=" + id + ", estado='" + estado + "', cantidad=" + cantidad + ", pelicula=" + pelicula.getTitulo() + ", usuario=" + usuario.getNombre() + "}";
    }
}
