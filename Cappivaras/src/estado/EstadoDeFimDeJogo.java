package estado;

import java.awt.Color;
import java.awt.Graphics2D;

import entidade.Capivara;
import sistemasFundamentais.PainelDeJogo;

public class EstadoDeFimDeJogo extends Estado {
	
	// Poss�veis textos a serem escritos na tela deste estado.
	private String[] textosDoEstado = { "Parab�ns!", "Voc� chegou ao parque Villa-Lobos!", "Fim de Jogo", "Voc� perdeu, tente novamente.", "Pressione ENTER ou ESPA�O para continuar",
			                            "Congratulations!", "You have arrived at Villa-Lobos park!", "Game Over", "You've lost, try again.", "Press ENTER or SPACE to continue" };
	
	
	public EstadoDeFimDeJogo( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
		
		// Prepara a m�sica da tela de fim de jogo.
		if ( capivara.vida > 0 ) { // Vit�ria da capivara. 
			painelDeJogo.gerenciadorDeAudio.audios[15].tocaAudioContinuo();
        }
		else { // Derrota da capivara. 
			painelDeJogo.gerenciadorDeAudio.audios[14].tocaAudioContinuo();
		}
		
	}

	
	public void atualizaJogo() { }

	
    public void desenhaTela( Graphics2D graphics2D ) {
		
		if( capivara.vida > 0 ) { // Vit�ria da capivara. 
			
			// Desenho da tela na posi��o final da capivara, isto �, o Parque Villa-Lobos.
			painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0), graphics2D );
			capivara.desenhaEntidade( graphics2D );
			painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(1), graphics2D );
			
			// Disposi��o do texto de vit�ria da capivara.
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[0 + painelDeJogo.seletorDeLingua * 5], 2, 40, 2, Color.yellow, Color.black, 55, 150 );
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[1 + painelDeJogo.seletorDeLingua * 5], 2, 30, 2, Color.yellow, Color.black, 55, 230 );
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[4 + painelDeJogo.seletorDeLingua * 5], 2, 20, 2, Color.white, Color.black, 55, 370 );
		}
		else { // Derrota da capivara. 
			
			/* Reposiciona-se a c�mera, que � dada em fun��o da posi��o da capivara,
			 * nas coordenadas inicias para a disposi��o da tela de fim de jogo.
			 */
			capivara.atualizaInformacoesDaCapivara( 0, 0, 0, "", 0);

			// Desenho do fundo da tela de fim de jogo.
			if( painelDeJogo.contadorDeCiclo % 4 == 0 ) {
				painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua++;
				if( painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua == 36 ) {
					painelDeJogo.gerenciadorDeCelulas.contadorDeCicloDaAgua = 0;
				}
			}
			painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(4), graphics2D );
			
			// Disposi��o do texto de derrota da capivara.
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[2 + painelDeJogo.seletorDeLingua * 5], 2, 40, 2, Color.yellow, Color.black, 55, 230 );
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[3 + painelDeJogo.seletorDeLingua * 5], 2, 20, 2, Color.white, Color.black, 55, 290 );
			painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[4 + painelDeJogo.seletorDeLingua * 5], 2, 20, 2, Color.white, Color.black, 55, 380 );
		}
    	
	}

    
    public void teclaPressionada() { }
    
    
    public void teclaConfirma() {
    	
    	capivara.atualizaInformacoesDaCapivara( 0, 0, 0, "", 0 );
    	painelDeJogo.gerenciadorDeAudio.audios[15].paraAudio();
    	painelDeJogo.gerenciadorDeAudio.audios[14].paraAudio();
		painelDeJogo.mudaEstado( new EstadoDoMenuPrincipal( this.painelDeJogo ) );
    }

	
	public void teclaParaCima() { }

	
	public void teclaParaBaixo() { }

	
	public void teclaParaDireita() { }

	
	public void teclaParaEsquerda() { }
}
