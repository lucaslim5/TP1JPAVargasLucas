package com.example.practico01;

import com.example.practico01.entidades.*;
import com.example.practico01.enumeraciones.Estado;
import com.example.practico01.enumeraciones.FormaPago;
import com.example.practico01.enumeraciones.Tipo;
import com.example.practico01.enumeraciones.TipoEnvio;
import com.example.practico01.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class Practico01Application {


	public static void main(String[] args) {
		SpringApplication.run(Practico01Application.class, args);
		System.out.println("---Probando 1-n Unidireccional Cliente- domicilio/pedido HECHO---");
		System.out.println("---Probando 1-n Unidireccional Rubro- Producto HECHO---");
		System.out.println("---Probando n-1 Unidireccional DetallPedido- Producto---");
	}
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, RubroRepository rubroRepository, DomicilioRepository domicilioRepository,ProductoRepository productoRepository) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");
			SimpleDateFormat formatoFecha= new SimpleDateFormat("dd-MM-yyyy");
			String fechaString = "07-12-2023";

			//Cliente-Domicilio
			// Crear instancias de domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle 1")
					.numero("123")
					.localidad("Ciudad")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle 2")
					.numero("456")
					.localidad("Ciudad")
					.build();

			// Crear instancia de Persona y agregar domicilios
			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2613334444")
					.email("JuanP@gmail.com")
					.build();




			//Rubro-Producto
			// Crear instancia de Rubro y ...
			Rubro rubro = Rubro.builder()
					.denominacion("Comida")
					.build();
			//creo instancia de los prodcutos con sus valores
			Producto producto1 = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(15)
					.denominacion("Papas Fritas")
					.precioVenta(777)
					.precioCompra(550)
					.stockActual(120)
					.stockMinimo(10)
					.unidadMedida("2")
					.receta("Receta:...")
					.build();
			Producto producto2 = Producto.builder()
					.tipo(Tipo.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Papas")
					.precioVenta(500)
					.precioCompra(350)
					.stockActual(250)
					.stockMinimo(100)
					.unidadMedida("10")
					.receta("Receta:...")
					.build();



			Pedido pedido1 = Pedido.builder()
					.estado(Estado.INICIADO)
					.fecha(formatoFecha.parse("10-11-2023"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(1500)
					.build();
			Pedido pedido2 = Pedido.builder()
					.estado(Estado.PREPARACION)
					.fecha(formatoFecha.parse("11-11-2023"))
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(1700)
					.build();

			//Pedido-DetallePedido
			//creo instancias de los detallePedidos
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(7)
					.subtotal(377)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(1210)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(44)
					.subtotal(2200)
					.build();

			//Pedido-Factura
			//creo instancias de factura
			Factura factura = Factura.builder()
					.numero(10)
					.fecha(formatoFecha.parse("10-09-2023"))
					.descuento(0.5)
					.formaPago(FormaPago.EFECTIVO)
					.total(500)
					.build();

			//... agregar productos
			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);

			// Guardar el objeto Rubro en la base de datos
			rubroRepository.save(rubro);

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);

			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);



			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido1);

//vinculo detallepedido con su producto
			detallePedido1.setProducto(producto1);
			detallePedido1.setProducto(producto2);
			detallePedido2.setProducto(producto2);
//vinculo factura con su pedido
			pedido1.setFactura(factura);
			pedido2.setFactura(factura);

			// Guardar el objeto Persona en la base de datos
			clienteRepository.save(cliente);

			// Recuperar el objeto Cliente desde la base de datos

			Cliente clienteRecuperada = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperada != null) {
				System.out.println("Nombre: " + clienteRecuperada.getNombre());
				System.out.println("Apellido: " + clienteRecuperada.getApellido());
				System.out.println("Telefono: " + clienteRecuperada.getTelefono());
				System.out.println("Email: " + clienteRecuperada.getEmail());
				clienteRecuperada.mostrarDomicilios();
				//met para mostrar pedidos
				clienteRecuperada.mostrarPedidos();


			}








			// Recuperar el objeto Rubro desde la base de datos

			Rubro rubroRecuperada = rubroRepository.findById(rubro.getId()).orElse(null);

			if (rubroRecuperada != null) {
				System.out.println("-Mostrar productos dentro de un rubro-");
				System.out.println("Rubro: " + rubroRecuperada.getDenominacion());
				rubroRecuperada.mostrarProductos();

			}



		};
	}


}
