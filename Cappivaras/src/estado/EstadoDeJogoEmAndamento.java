package estado;

import java.awt.Color;
import java.awt.Graphics2D;

import entidade.Capivara;
import entidade.Entidade;
import sistemasFundamentais.PainelDeJogo;

public class EstadoDeJogoEmAndamento extends Estado {

	public EstadoDeJogoEmAndamento( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
		
		// Prepara a música da tela de jogo em andamento.
		painelDeJogo.gerenciadorDeAudio.audios[16].tocaAudioContinuo();
	}

	
    public void atualizaJogo() {
		
		// Atualiza-se o contador de ciclo da animação da água em função do contador de ciclo geral.
		if( painelDeJogo.contadorDeCiclo % 4 == 0 ) {
			painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua++;
			if( painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua == 36 ) {
				painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua = 0;
			}
		}
		
		// Atualiza-se o contador de ciclo da animação do vidro futurista em função do contador de ciclo geral.
		if( painelDeJogo.contadorDeCiclo % 36 == 0 ) {
			painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDoVidro++;
			if( painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDoVidro == 6 ) {
				painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDoVidro = 0;
			}
		}
		
		// Atualizam-se todas as entidades de jogo instancidas no mapa.
    	for( Entidade entidade : painelDeJogo.gerenciadorDeEntidades.entidades ) {
    		entidade.atualizaEntidade();
    	}
    	
		// Caso a capivara chegue ao parque Villa-Lobos ou perca todas as suas vidas, o jogo acaba.
		if( capivara.vida == 0 || capivara.mapaY == painelDeJogo.tamanhoDaCelula*6 ) {
			painelDeJogo.gerenciadorDeAudio.audios[16].paraAudio();
			painelDeJogo.mudaEstado( new EstadoDeFimDeJogo( this.painelDeJogo ) );
		}
	}

    
    public void desenhaTela( Graphics2D graphics2D ) {
    	
    	// Primeiro, desenha-se a camada inferior do mapa.
    	painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0), graphics2D );
    	
    	// Depois, desenham-se todas as entidades de jogo instancidas no mapa.
    	for( Entidade entidade : painelDeJogo.gerenciadorDeEntidades.entidades ) {
    		entidade.desenhaEntidade( graphics2D );
    	}
    	
    	// Depois, desenha-se a camada superior do mapa.
    	painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1), graphics2D );
    	
    	// Por último, desenham-se os elementos da interface gráfica do jogador.
		painelDeJogo.interfaceGrafica.desenhaImagem( graphics2D, 0, painelDeJogo.tamanhoDaCelula/4, painelDeJogo.tamanhoDaCelula/4,
				painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula );
		painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, "x " + capivara.vida, 
				0, 20, 2, Color.white, Color.black, painelDeJogo.tamanhoDaCelula/4 + 43, painelDeJogo.tamanhoDaCelula );
	}
    
    
    public void teclaConfirma() {
    	
    	painelDeJogo.gerenciadorDeAudio.audios[16].pausaAudio();
    	painelDeJogo.gerenciadorDeAudio.audios[2].tocaAudioSimples();
		painelDeJogo.mudaEstado( new EstadoDeJogoEmPausa( this.painelDeJogo ) );
	}
    
    
	public void teclaParaCima() { }

	
	public void teclaParaBaixo() { }

	
	public void teclaParaDireita() { }

	
	public void teclaParaEsquerda() { }
}