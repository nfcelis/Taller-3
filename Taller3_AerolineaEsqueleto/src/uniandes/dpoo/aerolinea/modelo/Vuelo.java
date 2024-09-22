package uniandes.dpoo.aerolinea.modelo;

import java.text.SimpleDateFormat;


import java.util.Collection;
import java.util.Date;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private String fecha;
	private Map<String, Tiquete> tiquetes;
	private Ruta ruta;
	private Avion avion;
	
	public Vuelo(String fecha, Ruta ruta, Avion avion) {
		this.fecha = fecha;
		this.ruta = ruta;
		this.avion = avion;
	}
	public String getFecha() {
		return fecha;
	}
	public Collection<Tiquete> getTiquetes() {
		return tiquetes.values();
	}
	public Ruta getRuta() {
		return ruta;
	}
	public Avion getAvion() {
		return avion;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifasTemporadaAlta calculadoraAlta,CalculadoraTarifasTemporadaBaja calculadoraBaja, int cantidad)throws VueloSobrevendidoException {
		if (tiquetes.size()>=avion.getCapacidad()) {
			 throw new VueloSobrevendidoException(this);
	
		}
		CalculadoraTarifas calculadora = null;
	      try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	            Date inicioDiciembre = formatoFecha.parse("15/12/2024");
	            Date finDiciembre = formatoFecha.parse("15/01/2025");

	            Date inicioJulio = formatoFecha.parse("01/07/2024");
	            Date finJulio = formatoFecha.parse("31/07/2024");
	            
	            Date fecha_d;
	            fecha_d = formatoFecha.parse(fecha);
	            
	      

	            // Si la fecha del vuelo está en temporada alta
	            if ((fecha_d.after(inicioDiciembre) && fecha_d.before(finDiciembre)) ||
	                (fecha_d.after(inicioJulio) && fecha_d.before(finJulio))) {
	            	calculadora = calculadoraAlta;
	            } else {
	            	calculadora = calculadoraBaja;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            calculadora = calculadoraBaja;
	        }
	      
	      int costoTotal = 0;
	      for (int i = 0; i < cantidad; i++) {
	    	  int tarifa =calculadora.calcularTarifa(this, cliente);
	          costoTotal += tarifa;
	          Tiquete tiquete=GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
	          tiquetes.put(tiquete.getCodigo(), tiquete);
	          }
	      return costoTotal;
	}
	
	public boolean equals(Object obj) {
	
	if (this==obj) {
		return true;
	}
	
	if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
	
	 Vuelo other = (Vuelo) obj;
	
	 return fecha.equals(other.fecha) &&
             ruta.equals(other.ruta) &&
             avion.equals(other.avion);
	}
}
