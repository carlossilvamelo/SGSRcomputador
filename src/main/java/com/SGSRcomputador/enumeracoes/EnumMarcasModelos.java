package com.SGSRcomputador.enumeracoes;
import java.util.ArrayList;

public enum EnumMarcasModelos {

	
	ACER(modelosAcer()), DELL(modelosDell())
	, LENOVO(modelosLenovo()), SAMSUNG(modelosSamsung());
	
	
	public ArrayList<String> modelos;
	
	EnumMarcasModelos(ArrayList<String> modelos){
		this.modelos = modelos;
	}
	
	//Listagem de modelos
	
	
	public static ArrayList<String> modelosAcer(){
		ArrayList<String> modelos = new ArrayList<String>();
		modelos.add("Aspire V Nitro");
		modelos.add("Switch Alpha 12");
		modelos.add("Aspire E");
		modelos.add("Aspire V Nitro");
		modelos.add("VN7-592G-77C3");
		modelos.add("VN7-592G-734Z");
		modelos.add("SA5-271-59BH");
		modelos.add("SA5-271-54Z2");
		modelos.add("Aspire R 11");
		modelos.add("R3-131T-P7PY");
		return modelos;
	}
	public static ArrayList<String> modelosDell(){
		ArrayList<String> modelos = new ArrayList<String>();
		modelos.add("Inspiron Série 7000");
		modelos.add("Precision 17");
		modelos.add("I14-5458");
		modelos.add("I14-5457");
		modelos.add("I13-7348");
		modelos.add("I14-3442");
		modelos.add("I14-5448");
		modelos.add("I14-5457");
		modelos.add("Inspiron 15");
		modelos.add("Latitude 12");
		return modelos;
	}
	
	public static ArrayList<String> modelosLenovo(){
		ArrayList<String> modelos = new ArrayList<String>();
		modelos.add("IdeaPad 110");
		modelos.add("B110");
		modelos.add("Yoga 510");
		modelos.add("V320");
		modelos.add("ThinkPad E340");
		modelos.add("ThinkPad X1 Carbon");
		modelos.add("ThinkPad T460s");
		modelos.add("ThinkPad T470");
		modelos.add("ThinkPad X250");
		modelos.add("IdeaPad Y700");
		return modelos;
	}
	
	public static ArrayList<String> modelosSamsung(){
		
		ArrayList<String> modelos = new ArrayList<String>();
		modelos.add("Expert X22");
		modelos.add("Expert X51");
		modelos.add("Expert X37");
		modelos.add("Essentials E34");
		modelos.add("Essentials E21");
		modelos.add("Essentials E32");
		modelos.add("Style S20");
		modelos.add("Style X40");
		modelos.add("Expert X50");
		modelos.add("Style S50");
		return modelos;
	}
	
	//gets
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static EnumMarcasModelos getMarcaById(int id){
		
		for (EnumMarcasModelos marca : EnumMarcasModelos.values()) {
			if(EnumMarcasModelos.valueOf(marca.toString()).ordinal() == id){
				return EnumMarcasModelos.valueOf(marca.toString());
			}
		}

		return null;
	}
	
	public static ArrayList<String> listaMarcas(){
		ArrayList<String> lista = new ArrayList<String>();
		for (EnumMarcasModelos marca : EnumMarcasModelos.values()) {
			lista.add(marca.toString());
		}
		return lista;
	}
	
	public String getModeloById(int id){
		return this.modelos.get(id);
	}
	
}
