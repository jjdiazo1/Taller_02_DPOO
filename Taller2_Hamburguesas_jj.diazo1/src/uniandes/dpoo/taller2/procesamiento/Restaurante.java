package uniandes.dpoo.taller2.procesamiento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

import java.io.File;
import uniandes.dpoo.taller2.modelo.Pedido;

public class Restaurante {

	private Map<Integer, Ingrediente> ingredientes;
	private Map<Integer, ProductoMenu> menuBase;
	private Map<Integer, Pedido> pedidos;
	private Map<Integer, Combo> combos;
	private Map<Integer, ProductoMenu> bebidas;
	private Pedido pedidoEnCurso;

	public Restaurante(Map<Integer, Ingrediente> ingredients, Map<Integer, ProductoMenu> baseMenu, Map<Integer, ProductoMenu> drinks, Map<Integer, Combo> combos){
		
		this.ingredientes  = ingredients;
	    this.menuBase  = baseMenu;
	    this.bebidas  = drinks;
	    this.combos = combos;
	    this.pedidos  = new HashMap<Integer, Pedido>();
	}
	public void iniciarPedido(String customerName, String customerAddress) {
		pedidoEnCurso  = new Pedido(customerName, customerAddress);
	}
	
	public Pedido  getPedidoEnCurso() {
		return this.pedidoEnCurso; }
	
	public Map<Integer, ProductoMenu> getMenuBase() {
		
		return this.menuBase; }
	public Map<Integer, ProductoMenu> getBebidas() { 
		return this.bebidas; }
	
	public Map<Integer, Combo> getCombos() { 
		return this.combos; }
	
	public Map<Integer, Ingrediente> getIngredientes() { 
		return this.ingredientes; }
	
	public Pedido buscarPedido(int ID) { 
		return pedidos.get(ID); }
	
	public Ingrediente buscarIngrediente(int id) { 
		return ingredientes.get(id); }
	
	public void cerrarYGuardarPedido() throws IOException {
		
		int id = this.pedidoEnCurso.getIdPedido();
		File file = new File("./registro_facturas/factura" + id + ".txt");
		file.createNewFile();
		pedidoEnCurso.guardarFactura(file);

		boolean found = false;
		
		for (int i = 0; i < pedidos.keySet().size() && found == false; i++) {
			Pedido pedido = pedidos.get(i);
			
			if (pedidoEnCurso.equals(pedido)) {
				System.out.println("\nUy mira que ya existe un pedido igualito al tuyo.");
				found = true;
			}
		}
		Pedido.incrementarNumeroPedidos(); this.pedidos.put(id, this.pedidoEnCurso); this.pedidoEnCurso = null;
}
	
	public static Restaurante cargarArchivos(String menuFile, String beveragesFile, String ingredientsFile, String combosFile)
	    throws FileNotFoundException, IOException {
	    Map<Integer, ProductoMenu> baseMenu = loadMenu(menuFile);
	    Map<Integer, ProductoMenu> drinks = loadMenu(beveragesFile);
	    Map<Integer, Ingrediente> ingredients = loadIngredients(ingredientsFile);
	    Map<Integer, Combo> comboList = loadCombos(new ArrayList<>(baseMenu.values()), new ArrayList<>(drinks.values()), combosFile);
	    return new Restaurante(ingredients, baseMenu, drinks, comboList);
	}
	
	private static Map<Integer, ProductoMenu> loadMenu(String file) throws IOException {
	    Map<Integer, ProductoMenu> menu = new HashMap<>();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        int id = 1;
	        
	        while ((line = reader.readLine()) != null) {
	            
	            String[] parts = line.split(";");
	            String name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
	            int price = Integer.parseInt(parts[1]);
	            ProductoMenu item = new ProductoMenu(name, price);
	            menu.put(id, item);
	            id++;
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Archivo no encontrado.");
	    }
	    return menu;
	}

	private static ProductoMenu findProduct(List<ProductoMenu> baseMenu, List<ProductoMenu> drinks, String productName) {
	    ProductoMenu selectedProduct = null;

	    for (int i = 0; i < baseMenu.size() && selectedProduct == null; i++) {
	        ProductoMenu product = baseMenu.get(i);
	        if (product.getNombre().equals(productName)) {
	            selectedProduct = product;
	        }
	    }
	    for (int i = 0; i < drinks.size() && selectedProduct == null; i++) {
	        ProductoMenu product = drinks.get(i);
	        if (product.getNombre().equals(productName)) {
	            selectedProduct = product;
	        }
	    }
	    return selectedProduct;
	}

	private static Map<Integer, Ingrediente> loadIngredients(String file) throws IOException {
	    
	    Map<Integer, Ingrediente> ingredients = new HashMap<>();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        int id = 1;
	        
	        while ((line = reader.readLine()) != null) {
	            
	            String[] parts = line.split(";");
	            String name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
	            int additionalCost = Integer.parseInt(parts[1]);
	            Ingrediente ingredient = new Ingrediente(name, additionalCost);
	            ingredients.put(id, ingredient);
	            id++;
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Archivo no encontrado.");
	    }
	    return ingredients;
	}

	private static Map<Integer, Combo> loadCombos(List<ProductoMenu> baseMenu, List<ProductoMenu> beverages, String file)
	    throws IOException {
	    
	    Map<Integer, Combo> combos = new HashMap<>();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        
	        String line;
	        int id = 1;
	        
	        while ((line = reader.readLine()) != null) {
	            
	            String[] parts = line.split(";");
	            String comboName = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
	            double discount = Double.parseDouble(parts[1].replace('%', '\u0000'));
	            Combo combo = new Combo(comboName, discount);
	            
	            for (int i = 2; i < parts.length; i++) {
	                String productName = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
	                ProductoMenu product = findProduct(baseMenu, beverages, productName);
	                combo.addToCombo(product);
	            }
	            combos.put(id, combo);
	            id++;
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Archivo no encontrado.");
	    }
	    return combos;
	}

	
}
