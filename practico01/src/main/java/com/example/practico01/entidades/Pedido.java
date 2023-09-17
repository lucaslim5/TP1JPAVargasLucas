package com.example.practico01.entidades;

import com.example.practico01.enumeraciones.Estado;
import com.example.practico01.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Estado estado;
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private double total;

    // 1-n Producto-DetallePedido
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    // OJO ES IMPORTANTE COLOCAR ESTA ANOTACIÓN SINO ME DA ERROR
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detpei) {

        detallePedidos.add(detpei);
    }

    public void mostrarDetallePedidos() {
        System.out.println("DetallePedidos de " + estado + " " + fecha + " " + tipoEnvio + " " + total + ":");
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad: " + detallePedido.getCantidad() + ", Subtotal: " + detallePedido.getSubtotal());
        }

    }

    // RELACION ONE TO ONE UNIDIRECCIONAL con Factura
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private Factura factura;

}

