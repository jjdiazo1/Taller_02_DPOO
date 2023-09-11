package uniandes.dpoo.taller2.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private static int numPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String dirCliente;
	private List<Producto> itemsPedido;

	public Pedido(String n, String d) 
	{ nombreCliente = n; 
	dirCliente = d; 
	idPedido = numPedidos; 
	itemsPedido = new ArrayList<>(); } 
	
	public int getIdPedido() 
	{ return idPedido; } 
	
	public void agregarProducto(Producto nuevoItem) 
	{ itemsPedido.add(nuevoItem); } 
	
	public List<Producto> getItemsPedido() 
	{ return itemsPedido; } 
	
	public int getPrecioNeto() { 
		int p = 0; for (Producto x : itemsPedido) p += x.getPrecio(); return p; } 
	
	public int getIVAPedido() { 
		return (int) (this.getPrecioNeto() * 0.19); } 
	
	public int getPrecioTotalPedido() { 
		return this.getPrecioNeto() + this.getIVAPedido(); } 
	

	
	private String generarTextoFactura() { 
		
		String textoFactura = ""; 
		textoFactura += "Factura\n\nPedido " + this.idPedido + "\nNombre: " + this.nombreCliente + "\nDirección: " + this.dirCliente + "\n";
		
		for (Producto x : itemsPedido)
			textoFactura += x.generarTextoFactura(); 
		
		textoFactura += "\n\nPrecio Neto: $" + this.getPrecioNeto(); 
		textoFactura += "\nIVA: $" + this.getIVAPedido(); 
		textoFactura += "\nPrecio Total: $" + this.getPrecioTotalPedido(); 
		return textoFactura; } 
	
	public void guardarFactura(File archivo) throws IOException { 
		
		PrintWriter imprFactura = new PrintWriter(new FileWriter(archivo)); 
		String textoFactura = generarTextoFactura(); 
		imprFactura.println(textoFactura); 
		imprFactura.close(); }
	
	public boolean equals(Pedido p) 
	{ return this.itemsPedido.equals(p.itemsPedido); } 
	
	public static void incrementarNumeroPedidos() 
	{ numPedidos++; }

}
