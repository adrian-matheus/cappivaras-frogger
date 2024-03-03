package estado;

import java.awt.Color;
import java.awt.Graphics2D;

import entidade.Capivara;
import entidade.NivelDeDificuldade;
import sistemasFundamentais.PainelDeJogo;

public class EstadoDoMenuPrincipal extends Estado {
	
    private int opcaoDoMenu = 0; // Índice da opção do menu atualmente selecionada.
    private final int numeroDeOpcoesDoMenu = 3; // Número de opções do menu deste estado.
	
    // Possíveis textos a serem escritos na tela deste estado.
	private String[] textosDoEstado = { "- O Frogger do Século 21 -", "Iniciar Jogo", "Configurações", "Sair do Jogo",
			                            " The 21st Century Frogger ", "Start Game", "Settings", "Quit Game" };
	
	
	public EstadoDoMenuPrincipal( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
		
		// Prepara a música das telas dos menus principal e de configurações.
		painelDeJogo.gerenciadorDeAudio.audios[12].tocaAudioContinuo();
	}

	
	public void atualizaJogo() { }

	
    public void desenhaTela( Graphics2D graphics2D ) {
    	
    	// Desenho do fundo da tela do menu principal.
		if( painelDeJogo.contadorDeCiclo % 4 == 0 ) {
			painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua++;
			if( painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua == 36 ) {
				painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua = 0;
			}
		}
    	painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(2), graphics2D );
    	painelDeJogo.interfaceGrafica.desenhaImagem( graphics2D, 0, 10*painelDeJogo.tamanhoDaCelula,
    			7*painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula );
    	painelDeJogo.interfaceGrafica.desenhaImagem( graphics2D, 2, 7*painelDeJogo.tamanhoDaCelula,
    			10*painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula );
    	painelDeJogo.interfaceGrafica.desenhaImagem( graphics2D, 3, 13*painelDeJogo.tamanhoDaCelula,
    			9*painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula );
    	
    	// Disposição do título e subtítulo do jogo e das opções do menu principal.
		painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, "Cappivara's", 2, 100, 3, Color.white, Color.black, 90, 170 );
		
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 0 + painelDeJogo.seletorDeLingua*4 ],
    			1, 29, 3, Color.white, Color.black, 294 + painelDeJogo.seletorDeLingua*painelDeJogo.seletorDeEstilo*5, 206 );
    	
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1 + painelDeJogo.seletorDeLingua*4 ],
    			2, 20, 2, Color.white, Color.black, 100, 350 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2 + painelDeJogo.seletorDeLingua*4 ],
    			2, 20, 2, Color.white, Color.black, 100, 400 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3 + painelDeJogo.seletorDeLingua*4 ],
    			2, 20, 2, Color.white, Color.black, 100, 450 );
    	
    	// Destaque visual da opção do menu principal atualmente selecionada.
    	switch( opcaoDoMenu ) {
	    	case 0:
	        	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1 + painelDeJogo.seletorDeLingua*4 ],
	        			2, 20, 2, Color.yellow, Color.black, 100, 350 );
				break;
	
			case 1:
		    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2 + painelDeJogo.seletorDeLingua*4 ],
		    			2, 20, 2, Color.yellow, Color.black, 100, 400 );
		    	break;
	
			case 2:
		    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3 + painelDeJogo.seletorDeLingua*4 ],
		    			2, 20, 2, Color.yellow, Color.black, 100, 450 );
				break;
    	}
	}
    
	
	public void teclaConfirma() {
		
		painelDeJogo.gerenciadorDeAudio.audios[1].tocaAudioSimples();
		switch( opcaoDoMenu ) {
	    	case 0:
	    		painelDeJogo.gerenciadorDeEntidades.instanciaEntidadesNoMapa( NivelDeDificuldade.mapaDeEntidades );
	    		capivara.atualizaInformacoesDaCapivara( capivara.mapaXInicial, capivara.mapaYInicial,
	    				capivara.velocidadeInicial, capivara.direcaoInicial, 3 );
	    		painelDeJogo.gerenciadorDeAudio.audios[12].paraAudio();
	        	painelDeJogo.mudaEstado( new EstadoDeJogoEmAndamento( this.painelDeJogo ) );
				break;
	
			case 1:
		    	painelDeJogo.mudaEstado( new EstadoDoMenuDeConfiguracoes( this.painelDeJogo ) );
		    	break;
	
			case 2:
				System.exit( 0 );
				break;
		}
	}

	
	public void teclaParaCima() {
		
		if( opcaoDoMenu > 0 ) {
			painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
			opcaoDoMenu--;
		}
	}

	
	public void teclaParaBaixo() {
		
		if( opcaoDoMenu < numeroDeOpcoesDoMenu-1 ) {
			painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
			opcaoDoMenu++;
		}
	}

	
	public void teclaParaDireita() { }

	
	public void teclaParaEsquerda() { }
}
