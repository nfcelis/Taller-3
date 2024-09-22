package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	protected static final int COSTO_POR_KM=1000;

	
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
    	Ruta ruta=vuelo.getRuta();
    	int distancia=calcularDistanciaVuelo(ruta);
    	int costo= distancia*COSTO_POR_KM;
    	return costo;
	}
	
	public double calcularPorcentajeDescuento(Cliente cliente) {
		return 0;
		
	}
}

