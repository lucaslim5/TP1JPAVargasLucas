package com.example.practico01.entidades;

import com.example.practico01.enumeraciones.Tipo;
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
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tipo tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;

    /*// 1-n Producto-DetallePedido
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    // OJO ES IMPORTANTE COLOCAR ESTA ANOTACIÓN SINO ME DA ERROR
    @Builder.Default
    private List<DetallePedido> detallePedidoProductos = new ArrayList<>();

    public void agregarDetallePedidoProducto(DetallePedido detpri) {

        detallePedidoProductos.add(detpri);
    }

    public void mostrarDetallePedidoProductos() {
        System.out.println("Detalles de " + denominacion + ":");
        for (DetallePedido detallePedidoProducto : detallePedidoProductos) {
            System.out.println("Cantidad: " + detallePedidoProducto.getCantidad() + "Subtotal: " + detallePedidoProducto.getSubtotal());
        }

    }*/
}
