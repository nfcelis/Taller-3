package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	
	protected Tiquete tiquetesSinUsar;
	protected Tiquete tiquetesUsados;
	
	
	public Cliente() {
		
	}
	public abstract String getTipoCliente();
	public abstract String getidentificador();
	
	public void agregarTiquete(Tiquete tiquete) {
		if (this.tiquetesSinUsar==null) {
			this.tiquetesSinUsar=tiquete;
		}
		else {
			System.out.println("Ya existe un tiquete");
		}
		
	}
	public void usarTiquetes(Vuelo vuelo) {
		if (vuelo!=null) {
			this.tiquetesUsados=this.tiquetesSinUsar;
			this.tiquetesSinUsar=null;
		}
		else {
			System.out.println("No hay tiquetes disponibles para usar");
		}
	}
	public int calcularValorTotalTiquetes() {
		int valortotal=0;
		
		if(this.tiquetesSinUsar!=null) {
			valortotal+=this.tiquetesSinUsar.getTarifa();
		}
		if(this.tiquetesUsados!=null) {
			valortotal+=this.tiquetesUsados.getTarifa();
		}
		return valortotal;
	}
		
	
	
}
