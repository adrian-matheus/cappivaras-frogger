package sistemasFundamentais;

import entidade.Barco;
import entidade.Capivara;
import entidade.Entidade;

public class VerificadorDeColisao {

	private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	private Capivara capivara; // Referência para a instância única da capivara.
	
	public VerificadorDeColisao( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
	}

	
	// Método que verifica se ocorre colisão entre a capivara e as células do mapa adjacentes a ela.
	public void verificaColisaoComCelulas() {
		
		int colunaAtual = capivara.mapaX / painelDeJogo.tamanhoDaCelula;
		int linhaAtual  = capivara.mapaY / painelDeJogo.tamanhoDaCelula;
			    
		if ( capivara.mapaX % painelDeJogo.tamanhoDaCelula/painelDeJogo.escala == 0 && capivara.mapaY % painelDeJogo.tamanhoDaCelula/painelDeJogo.escala == 0 ) {
			switch( capivara.direcao ) {
			    case "cima":
			    	if( linhaAtual-1 < 0 ) 
			    		capivara.emColisaoComCelula = true;
			    	else if( painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual-1][colunaAtual]].colisao || 
			    			 painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1)[linhaAtual-1][colunaAtual]].colisao ) 
			    		capivara.emColisaoComCelula = true;
			    	break;
			    	
			    case "baixo":
			    	if( linhaAtual+1 >= painelDeJogo.numeroDeLinhasDoMapa ) 
			    		capivara.emColisaoComCelula = true;		
			    	else if( painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual+1][colunaAtual]].colisao || 
			    			 painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1)[linhaAtual+1][colunaAtual]].colisao ) 
			    		capivara.emColisaoComCelula = true;	
			    	break;		
			    	
			    case "direita":
			    	if( colunaAtual+1 >= painelDeJogo.numeroDeColunasDoMapa ) 
			    		capivara.emColisaoComCelula = true;
			    	else if( painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual][colunaAtual+1]].colisao || 
			    			 painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1)[linhaAtual][colunaAtual+1]].colisao ) 
			    		capivara.emColisaoComCelula = true;
			    	break;
			    	
			    case "esquerda":
			    	if( colunaAtual-1 < 0 ) 
			    		capivara.emColisaoComCelula = true;
			    	else if( painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual][colunaAtual-1]].colisao || 
			    			 painelDeJogo.gerenciadorDeCelulas.celulas[painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1)[linhaAtual][colunaAtual-1]].colisao ) 
			    		capivara.emColisaoComCelula = true;
			    	break;
		    }
		}
	}
	
	// Método que verifica se ocorre colisão entre a capivara e as outras entidades no mapa.
	public void verificaColisaoComEntidades() {
		
		for( Entidade entidade : painelDeJogo.gerenciadorDeEntidades.entidades ) {
			if( !(entidade instanceof Capivara) ) {
				if( entidade instanceof Barco &&
					(capivara.mapaX > entidade.mapaX - painelDeJogo.tamanhoDaCelula && capivara.mapaX < entidade.mapaX + entidade.largura*painelDeJogo.tamanhoDaCelula) &&
				    (capivara.mapaY > entidade.mapaY - painelDeJogo.tamanhoDaCelula && capivara.mapaY < entidade.mapaY + entidade.altura*painelDeJogo.tamanhoDaCelula) ) {
					capivara.emColisaoComBarco = true;
				} else if( (capivara.mapaX > entidade.mapaX - 3*painelDeJogo.tamanhoDaCelula/4 && capivara.mapaX < entidade.mapaX + 3*entidade.largura*painelDeJogo.tamanhoDaCelula/4) &&
						   (capivara.mapaY > entidade.mapaY - 3*painelDeJogo.tamanhoDaCelula/4 && capivara.mapaY < entidade.mapaY + 3*entidade.altura*painelDeJogo.tamanhoDaCelula/4) ) {
					capivara.emColisaoComEntidade = true;
				}
			}
		}
	}
}
