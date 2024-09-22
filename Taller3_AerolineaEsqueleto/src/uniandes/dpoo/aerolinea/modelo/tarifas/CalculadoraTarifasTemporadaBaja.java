package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	protected static final int COSTO_POR_KM_NATURAL = 600;
	protected static final int COSTO_POR_KM_CORPORATIVO = 900;
	protected static final double DESCUENTO_PEQ = 0.02;
	protected static final double DESCUENTO_MEDIANAS = 0.1;
	protected static final double DESCUENTO_GRANDES = 0.28;
    
    public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
    	int costo;
    	if (cliente.getTipoCliente().equals("Natural")){
	    	Ruta ruta=vuelo.getRuta();
	    	int distancia = calcularDistanciaVuelo(ruta);
	        costo =COSTO_POR_KM_NATURAL * distancia;
    	}
    	else {
    		Ruta ruta=vuelo.getRuta();
        	int distancia = calcularDistanciaVuelo(ruta);
            costo= COSTO_POR_KM_CORPORATIVO * distancia;
    		
    	}
    	return costo;
    }
    
    public double calcularPorcentajeDescuento(Cliente cliente) {
    	double descuento=0;
    	if (cliente.getTipoCliente().equals("Corporativo")) {
    		ClienteCorporativo clientecorp = (ClienteCorporativo) cliente;
    		if (clientecorp.getTamanoEmpresa()==1) {
    			descuento=DESCUENTO_GRANDES;	
    		}
    		else if (clientecorp.getTamanoEmpresa()==2) {
    			descuento=DESCUENTO_MEDIANAS;	
    		}
    		else if (clientecorp.getTamanoEmpresa()==3) {
    			descuento=DESCUENTO_PEQ;	
    		}
    	}
    	return descuento;
    }



}
