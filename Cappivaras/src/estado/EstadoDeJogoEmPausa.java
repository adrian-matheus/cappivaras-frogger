package estado;

import java.awt.Color;
import java.awt.Graphics2D;

import entidade.Capivara;
import sistemasFundamentais.PainelDeJogo;

public class EstadoDeJogoEmPausa extends Estado {

	private int opcaoDoMenu = 0; // �ndice da op��o do menu atualmente selecionada.
    private final int numeroDeOpcoesDoMenu = 3; // N�mero de op��es do menu deste estado.
	
    // Poss�veis textos a serem escritos na tela deste estado.
	private String[] textosDoEstado = { "Jogo Pausado", "Voltar ao Jogo", "Voltar ao Menu Inicial", "Sair do Jogo",
			                            "Game Paused", "Resume Game", "Go Back to Main Menu", "Quit Game" };
	
	
	public EstadoDeJogoEmPausa( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
		
		// Prepara a m�sica da tela de jogo em pausa.
		painelDeJogo.gerenciadorDeAudio.audios[13].tocaAudioContinuo();
	}

	
    public void atualizaJogo() { }

    
    public void desenhaTela( Graphics2D graphics2D ) {
    	
    	// Desenho da tela na posi��o da capivara na qual o jogo foi pausado.
		painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0), graphics2D );
		capivara.desenhaEntidade( graphics2D );
		painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1), graphics2D );
		
		// Disposi��o do t�tulo e das op��es do menu de jogo em pausa.
		painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 0  + painelDeJogo.seletorDeLingua*4 ], 
				2, 50, 2, Color.white, Color.black, 110, 170 );
		
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1  + painelDeJogo.seletorDeLingua*4 ], 
    			2, 20, 2, Color.white, Color.black, 120, 350 );

    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2  + painelDeJogo.seletorDeLingua*4 ], 
    			2, 20, 2, Color.white, Color.black, 120, 400 );

    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3  + painelDeJogo.seletorDeLingua*4 ], 
    			2, 20, 2, Color.white, Color.black, 120, 450 );
    	
    	// Destaque visual da op��o do menu de jogo em pausa atualmente selecionada.
    	switch( opcaoDoMenu ) {
	    	case 0:
	        	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1 + painelDeJogo.seletorDeLingua*4 ], 
	        			2, 20, 2, Color.yellow, Color.black, 120, 350 );
				break;
	
			case 1:
		    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2 + painelDeJogo.seletorDeLingua*4 ], 
		    			2, 20, 2, Color.yellow, Color.black, 120, 400 );
		    	break;
	
			case 2:
		    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3 + painelDeJogo.seletorDeLingua*4 ], 
		    			2, 20, 2, Color.yellow, Color.black, 120, 450 );
				break;
    	}
	}
    
    
    public void teclaConfirma() {
    	
    	switch( opcaoDoMenu ) {
	    	case 0:
	    		painelDeJogo.gerenciadorDeAudio.audios[3].tocaAudioSimples();
	    		painelDeJogo.gerenciadorDeAudio.audios[13].pausaAudio();
	        	painelDeJogo.mudaEstado( new EstadoDeJogoEmAndamento( this.painelDeJogo ) );
				break;
	
			case 1:
				painelDeJogo.gerenciadorDeAudio.audios[1].tocaAudioSimples();
				capivara.atualizaInformacoesDaCapivara( 0, 0, 0, "", 0);
				painelDeJogo.gerenciadorDeAudio.audios[16].paraAudio();
				painelDeJogo.gerenciadorDeAudio.audios[13].paraAudio();
		    	painelDeJogo.mudaEstado( new EstadoDoMenuPrincipal( this.painelDeJogo ) );
		    	break;
	
			case 2:
				System.exit( 0 );
				break;
		}
	}
    
    
	public void teclaParaCima() {
		
		if( opcaoDoMenu > 0) {
			painelDeJogo.gerenciadorDeAudio.audios[5].tocaAudioSimples();
			opcaoDoMenu--;
		}
	}

	
	public void teclaParaBaixo() {
		
		if( opcaoDoMenu < numeroDeOpcoesDoMenu-1 ) {
			painelDeJogo.gerenciadorDeAudio.audios[5].tocaAudioSimples();
			opcaoDoMenu++;
		}
	}

	
	public void teclaParaDireita() { }

	
	public void teclaParaEsquerda() { }
}
