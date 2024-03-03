package estilo;

import sistemasFundamentais.PainelDeJogo;

public class FabricaDeEstilos {
	
	public Estilo getEstilo( PainelDeJogo painelDeJogo ) {

	    if( painelDeJogo.seletorDeEstilo == 0 ){
	        return Estilo1930.getInstancia( painelDeJogo ); // Estilo audiovisual da década de 1930.
	    } 
	    else if( painelDeJogo.seletorDeEstilo == 1 ){
	        return Estilo2022.getInstancia( painelDeJogo ); // Estilo audiovisual contemporâneo de 2022.  
	    } 
	    else if( painelDeJogo.seletorDeEstilo == 2 ){
	        return Estilo2052.getInstancia( painelDeJogo ); // Estilo audiovisual futurista de 2052.
	    } 
	    else {
	    	return null;
	    }
	}
}
