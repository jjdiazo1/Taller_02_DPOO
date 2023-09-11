package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.ProductoAjustado;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.procesamiento.Restaurante;

public class Aplicacion
{	
	
	public void ejecutarAplicacion()
	{
		System.out.println("¡Bienvenido a Javamburguesas!");

		ejecutarCargarRestaurante();

		boolean continuar = true;
		while (continuar)
		{
			System.out.println("\nOpciones de la aplicación\n");
			System.out.println("1. Mostrar el menú");
			System.out.println("2. Iniciar un nuevo pedido");
			System.out.println("3. Agregar un elemento a un pedido");
			System.out.println("4. Cerrar un pedido y guardar la factura");
			System.out.println("5. Consultar la información de un pedido dado su id");
			System.out.println("6. Salir de la aplicación\n");
			try
			{
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				
				if (opcion_seleccionada == 1)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 2)
					ejecutarIniciarPedido();
				else if (opcion_seleccionada == 3)
					ejecutarAgregarProductos();
				else if (opcion_seleccionada == 4)
					ejecutarCerraryGuardarPedido();
				else if (opcion_seleccionada == 5)
					ejecutarBuscarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Gracias por venir a Javamburguesas");
					continuar = false;
				}
				else
				{
					System.out.println("Seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Seleccione un numero de las opciones");
			}
		}
	}


	private Restaurante restaurante;
	
	private void ejecutarIniciarPedido() {
		
		if (!(restaurante.getPedidoEnCurso() == null)) {
			System.out.println("Ya hay un pedido.");}
		
		else {
			System.out.println("Bienvenido, soy Javita y le voy a tomar su orden.");
			System.out.print("Cual es tu nombre ?");
			String cliente = input("");
			System.out.print("Cual es tu dirección ?");
			String direccion = input("");

			restaurante.iniciarPedido(cliente, direccion);

			System.out.println("\nYa registre tu pedido, gracias por confiar.");
		}
	}
	
	private void ejecutarCerraryGuardarPedido() {
		
		Pedido currentOrder = restaurante.getPedidoEnCurso();
		mostrarPedido(currentOrder);

		List<Producto> orderItems = currentOrder.getItemsPedido();
		if (orderItems.isEmpty()) {
		    System.out.println("\nTu pedido no pudo ser procesado.");
		}

		int response = Integer.parseInt(input("Quieres finalizar tu pedido?  Presione 1 para si y 0 para no."));

		if (response == 1) {
			
		    try {
		        restaurante.cerrarYGuardarPedido();
		        System.out.println("\nTerminaste tu pedido, mira tu factura.");
		    } catch (IOException e) {
		        e.printStackTrace();
		        }}}
	
	
	private void ejecutarMostrarMenu() {	
	
		System.out.println("\n---- MENÚ ----\n");
	
		// Productos
        System.out.println("Productos:\n");
        System.out.println("1.) Corral: $14000");
        System.out.println("2.) Corral queso: $16000");
        System.out.println("3.) Corral pollo: $15000");
        System.out.println("4.) Corralita: $13000");
        System.out.println("5.) Todoterreno: $25000");
        System.out.println("6.) 1/2 libra: $25000");
        System.out.println("7.) Especial: $24000");
        System.out.println("8.) Casera: $23000");
        System.out.println("9.) Mexicana: $22000");
        System.out.println("10.) Criolla: $22000");
        System.out.println("11.) Costeña: $20000");
        System.out.println("12.) Hawaiana: $20000");
        System.out.println("13.) Wrap de pollo: $15000");
        System.out.println("14.) Wrap de lomo: $22000");
        System.out.println("15.) Ensalada mexicana: $20900");
        System.out.println("16.) Papas medianas: $5500");
        System.out.println("17.) Papas grandes: $6900");
        System.out.println("18.) Papas en casco medianas: $5500");
        System.out.println("19.) Papas en casco grandes: $6900");

        // Ingredientes
        System.out.println("\nIngredientes:\n");
        System.out.println("1.) Lechuga_mod: $2000");
        System.out.println("2.) Tomate_mod: $2000");
        System.out.println("3.) Cebolla_mod: $2000");
        System.out.println("4.) Queso mozzarella_mod: $5000");
        System.out.println("5.) Huevo_mod: $5000");
        System.out.println("6.) Queso americano_mod: $5000");
        System.out.println("7.) Tocineta express_mod: $5000");
        System.out.println("8.) Papa callejera_mod: $4000");
        System.out.println("9.) Pepinillos_mod: $5000");
        System.out.println("10.) Cebolla grille_mod: $5000");
        System.out.println("11.) Suero costeño_mod: $6000");
        System.out.println("12.) Frijol refrito_mod: $9000");
        System.out.println("13.) Queso fundido_mod: $9000");
        System.out.println("14.) Tocineta picada_mod: $12000");
        System.out.println("15.) Piña_mod: $5000");

        // Bebidas
        System.out.println("\nBebidas:\n");
        System.out.println("1.) Agua cristal sin gas: $5000");
        System.out.println("2.) Agua cristal con gas: $5000");
        System.out.println("3.) Gaseosa: $5000");

        // Combos
        System.out.println("\nCombos:\n");
        System.out.println("1.) Combo corral_c_mod");
        System.out.println("Productos del combo:");
        System.out.println("- Corral");
        System.out.println("- Papas medianas");
        System.out.println("- Gaseosa");
        System.out.println("Precio del combo: $19600\n");

        System.out.println("2.) Combo corral queso_c_mod");
        System.out.println("Productos del combo:");
        System.out.println("- Corral queso");
        System.out.println("- Papas medianas");
        System.out.println("- Gaseosa");
        System.out.println("Precio del combo: $21200\n");

        System.out.println("3.) Combo todoterreno_c_mod");
        System.out.println("Productos del combo:");
        System.out.println("- Todoterreno");
        System.out.println("- Papas grandes");
        System.out.println("- Gaseosa");
        System.out.println("Precio del combo: $31734\n");

        System.out.println("4.) Combo especial_c_mod");
        System.out.println("Productos del combo:");
        System.out.println("- Especial");
        System.out.println("- Papas medianas");
        System.out.println("- Gaseosa");
        System.out.println("Precio del combo: $29670\n");
    }
	
	private void ejecutarAgregarProductos() {
		boolean running = true;
		Pedido currentOrder = restaurante.getPedidoEnCurso();
		Map<Integer, ProductoMenu> baseMenu = restaurante.getMenuBase();
		Map<Integer, ProductoMenu> drinks = restaurante.getBebidas();
		Map<Integer, Combo> combos = restaurante.getCombos();

		ejecutarMostrarMenu();
		do {
		    int option = Integer.parseInt(input("Presione 1 para producto, 2 para bebida, 3 para combo y 4 para salir: "));
		    
		    if (option == 2) {
		        int drinkOption = Integer.parseInt(input("Ingrese el ID de la bebida"));
		        ProductoMenu drink = drinks.get(drinkOption);
		        
		        if (!(drink == null)) {
		            currentOrder.agregarProducto(drink);
		            System.out.println("\nSe agregó la bebida " + drink.getNombre() + "\n");
		            
		        } else {
		            System.out.println("\nNo existe una bebida con este ID.");
		        }
		        
		    } else if (option == 3) {
		    	
		        int comboOption = Integer.parseInt(input("Ingrese el ID del combo"));
		        Combo combo = combos.get(comboOption);
		        
		        if (!(combo == null)) {
		            currentOrder.agregarProducto(combo);
		            System.out.println("\nSe agregó el combo " + combo.getNombre() + "\n");
		            
		        } else {
		            System.out.println("\nNo existe un combo con este ID.");
		        }
		        
		    } else if (option == 1) {
		    	
		        int productOption = Integer.parseInt(input("Ingrese el ID del producto"));
		        ProductoMenu baseProduct = baseMenu.get(productOption);

		        List<Ingrediente> addedIngredients = new ArrayList<>();
		        List<Ingrediente> removedIngredients = new ArrayList<>();

		        int addChoice = Integer.parseInt(input("¿Desea agregar ingredientes? Presione 1 para si y 0 para no"));

		        if (addChoice == 1) {
		            do {
		                int ingredientID = Integer.parseInt(input("Ingrese el ID del ingrediente a agregar"));
		                Ingrediente ingredient = restaurante.buscarIngrediente(ingredientID);
		                
		                if (!(ingredient == null)) {
		                    addedIngredients.add(ingredient);
		                    System.out.println("\nSe agregó el ingrediente " + ingredient.getNombre() + "\n");
		                    System.out.println("\nNo existe un ingrediente con este ID.");
		                }
		                addChoice = Integer.parseInt(input("¿Desea agregar otro ingrediente?  Presione 1 para si y 0 para no"));
		            } while (addChoice == 1);
		        }

		        int removeChoice = Integer.parseInt(input("¿Desea eliminar ingredientes?  Presione 1 para si y 0 para no"));

		        if (removeChoice == 1) {
		            do {
		                int ingredientID = Integer.parseInt(input("Ingrese el ID del ingrediente a eliminar"));
		                Ingrediente ingredient = restaurante.buscarIngrediente(ingredientID);
		                
		                if (!(ingredient == null)) {
		                    removedIngredients.add(ingredient);
		                    System.out.println("\nSe eliminó el ingrediente " + ingredient.getNombre() + "\n");
		                } else {
		                    System.out.println("\nNo existe un ingrediente con este ID.");
		                }
		                removeChoice = Integer.parseInt(input("¿Desea eliminar otro ingrediente? Presione 1 para si y 0 para no."));
		            } while (removeChoice == 1);
		        }

		        if (!(baseProduct == null)) {
		        	
		            Producto product;
		            
		            if (!addedIngredients.isEmpty() || !removedIngredients.isEmpty()) {
		                product = new ProductoAjustado(baseProduct, addedIngredients, removedIngredients);
		                
		            } else {
		                product = baseProduct;
		            }
		            
		            currentOrder.agregarProducto(product);
		            System.out.println("\nSe agregó el producto " + product.getNombre() + "\n");
		        } else {
		            System.out.println("\nNo existe un producto con ese ID.");
		        }
		    } else if (option == 4) {
		        running = false;
		        
		    } else {
		        System.out.println("\nPor favor selecciona una opción válida.");
		    }
		} while (running);

		mostrarPedido(currentOrder);
	}
	

	private void mostrarPedido(Pedido pedido) {
		
		List<Producto> orderItems = pedido.getItemsPedido();
		
		if (orderItems.isEmpty()) {
		    System.out.println("\nTu pedido está actualmente sin productos.");
		    
		} else {
		    System.out.println("\nDetalle del Pedido:\n");
		    
		    for (Producto item : orderItems) {
		        System.out.println("* " + item.getNombre() + ": $" + item.getPrecio());
		    }
		    System.out.println("\nPrecio Neto: " + pedido.getPrecioNeto() + "\nIVA: " + pedido.getIVAPedido() + "\nPrecio Total: " + pedido.getPrecioTotalPedido());
		}
	}
	private void ejecutarCargarRestaurante() {
		
		String menuTxt = "./data/menu.txt";
		String ingredientsTxt = "./data/ingredientes.txt";
		String combosTxt = "./data/combos.txt";
		String drinksTxt = "./data/bebidas.txt";

		try {
		    restaurante = Restaurante.cargarArchivos(menuTxt, drinksTxt, ingredientsTxt, combosTxt);
		    
		} catch (FileNotFoundException e) {
		    System.out.println("ERROR: No encontre el archivo.");
		    
		} catch (IOException e) {
		    System.out.println("ERROR: No pude leer el archivo.");
		    System.out.println(e.getMessage());
		}
	}


	private void ejecutarBuscarPedido() {
		int orderID;
		try {
		    orderID = Integer.parseInt(input("\nIngresa el ID del pedido que deseas buscar"));
		} catch (NumberFormatException e) {
		    System.out.println("\nPor favor, introduce un número válido.");
		    return;
		}
		Pedido foundOrder = restaurante.buscarPedido(orderID);
		if (foundOrder == null) {
		    System.out.println("\nNo encontre ningún pedido con este ID.");
		    return;
		}
		mostrarPedido(foundOrder);
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] argumentos) {
	    Aplicacion aplic = new Aplicacion();
	    aplic.ejecutarAplicacion();
	}

}
