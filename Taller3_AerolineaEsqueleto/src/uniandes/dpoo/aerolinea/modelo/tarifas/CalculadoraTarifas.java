package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {

	public static final double IMPUESTO=0.28;
	
	public int calcularTarifa(Vuelo vuelo,Cliente cliente) {
		
		int costoBase=calcularCostoBase(vuelo,cliente);
		double porcentajeDescuento=calcularPorcentajeDescuento(cliente);
		int impuestos=ValorImpuestos(costoBase);
		double costoTotal=(costoBase*porcentajeDescuento)+impuestos;
		
		return(int) costoTotal;
		
	}
		
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		
		Aeropuerto Origen= ruta.getOrigen();
		Aeropuerto Destino=ruta.getDestino();
		double lat1Rad = Math.toRadians(Origen.getLatitud());
	    double lat2Rad = Math.toRadians(Destino.getLatitud());
	    double lon1Rad = Math.toRadians(Origen.getLongitud());
	    double lon2Rad = Math.toRadians(Destino.getLongitud());

	    double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
	    double y = (lat2Rad - lat1Rad);
	    double distancia = Math.sqrt(x * x + y * y) * 6371;

	    return (int) (distancia);
		
		
	}
	
	protected int ValorImpuestos(int costoBase) {
		return (int) (costoBase*IMPUESTO);
		
	}
	
}

