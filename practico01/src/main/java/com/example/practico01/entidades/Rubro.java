package com.example.practico01.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String denominacion;

    //1-n con Producto
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos= new ArrayList<>();
//metodos producto
    public void agregarProducto(Producto proi) {

        productos.add(proi);
    }

    public void mostrarProductos() {
        System.out.println("Productos del rubro " + denominacion  + ":");
        for (Producto producto : productos) {
            System.out.println("Denominacion"+ producto.getId()+ " : " + producto.getDenominacion()
                    + ", Tiempo Cocina: " + producto.getTiempoEstimadoCocina() + ", Precio: "
                    + producto.getPrecioVenta() + ", Precio Compra: " + producto.getPrecioCompra()
                    + ", Stock: " + producto.getStockActual() + ", Unidad Medida: " + producto.getUnidadMedida()
                    + ", Receta: " + producto.getReceta());
        }

    }
}
