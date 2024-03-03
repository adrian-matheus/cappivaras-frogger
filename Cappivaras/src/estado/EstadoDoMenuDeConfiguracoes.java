package estado;

import java.awt.Color;
import java.awt.Graphics2D;

import audio.Audio;
import entidade.Capivara;
import entidade.NivelDeDificuldadeDificil;
import entidade.NivelDeDificuldadeFacil;
import entidade.NivelDeDificuldadeMedio;
import sistemasFundamentais.PainelDeJogo;

public class EstadoDoMenuDeConfiguracoes extends Estado {
	
	private int opcaoDoMenu = 0; // Índice da opção do menu atualmente selecionada.
    private final int numeroDeOpcoesDoMenu = 6; // Número de opções do menu deste estado.
	
    // Número de opções selecionáveis de cada tipo.
	private final int numeroDeDificuldades = 3;
	private final int numeroDeLinguas = 2;
	private final int numeroDeEstilos = 3;
	private final int numeroDeVolumes = 11;

	// Verificadores que acusam quando uma opção específica está sendo selecionada.
	private boolean selecionandoDificuldade = false;
	private boolean selecionandoLingua = false;
	private boolean selecionandoEstilo = false;
	private boolean selecionandoVolumeDasMusicas = false;
	private boolean selecionandoVolumeDosEfeitosSonoros = false;
	
	// Possíveis textos a serem escritos na tela deste estado.
	private String[] textosDoEstado = { "Configurações", "Dificuldade", "Língua", "Estilo", "Volume das Músicas", "Volume dos Efeitos Sonoros", "Salvar e Voltar ao Menu Principal",
			                            "Settings", "Difficulty", "Language", "Look and Feel", "Music Volume", "SFX Volume", "Save and Go Back to Main Menu" };
	
	private String[] dificuldades = { "Fácil", "Médio", "Difícil",
			                          "Easy", "Medium", "Hard" };
	
	private String[] lingua = { "Português", "English" };
	
	private String[] estilos = { "Década de 1930", "Contemporâneo", "Futurista",
			                     "1930s", "Present Day", "Futuristic" };
	
	private String[] volumes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	
	
	public EstadoDoMenuDeConfiguracoes( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
	}

	
	public void atualizaJogo() { }

	
    public void desenhaTela( Graphics2D graphics2D ) {
    	
    	// Desenho do fundo da tela de configurações.
    	painelDeJogo.gerenciadorDeCelulas.desenhaMapaDeCelulas( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(3), graphics2D );
    	
    	// Disposição do título e das opções do menu de configurações.
		painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 0  + painelDeJogo.seletorDeLingua*7 ], 
				2, 50, 3, Color.white, Color.black, 110, 115 );
    	
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 222 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, dificuldades[ painelDeJogo.seletorDeDificuldade + painelDeJogo.seletorDeLingua*3 ], 
    			2, 20, 2, Color.white, Color.black, 280, 222 );

    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 270 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, lingua[ painelDeJogo.seletorDeLingua ], 
    			2, 20, 2, Color.white, Color.black, 280, 270 );
		
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 320 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, estilos[ painelDeJogo.seletorDeEstilo + painelDeJogo.seletorDeLingua*3 ], 
    			2, 20, 2, Color.white, Color.black, 280, 320 );
    	
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 4 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 368 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, volumes[ painelDeJogo.seletorDeVolumeDasMusicas ], 
    			2, 20, 2, Color.white, Color.black, 280 + (1-painelDeJogo.seletorDeLingua)*133, 368 );
    	
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 5 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 418 );
    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, volumes[ painelDeJogo.seletorDeVolumeDosEfeitosSonoros ], 
    			2, 20, 2, Color.white, Color.black, 280 + (1-painelDeJogo.seletorDeLingua)*133, 418 );

    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 6 + painelDeJogo.seletorDeLingua*7 ], 
    			2, 20, 2, Color.white, Color.black, 120, 514 );
    	
    	// Destaque visual da opção do menu de configurações atualmente selecionada.
    	switch( opcaoDoMenu ) {
	    	case 0:
	    		if( !selecionandoDificuldade ) {
	    	    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 1 + painelDeJogo.seletorDeLingua*7 ], 
	    	    			2, 20, 2, Color.yellow, Color.black, 120, 222 );
	    		}
	    		else {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, dificuldades[ painelDeJogo.seletorDeDificuldade + painelDeJogo.seletorDeLingua*3 ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 280, 222 );
	    		}
				break;
	
	    	case 1:
	    		if( !selecionandoLingua ) {
	    	    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 2 + painelDeJogo.seletorDeLingua*7 ], 
	    	    			2, 20, 2, Color.yellow, Color.black, 120, 270 );
	    		}
	    		else {
	    	    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, lingua[ painelDeJogo.seletorDeLingua ], 
	    	    			2, 20, 2, Color.yellow, Color.black, 280, 270 );
	    		}
				break;
	
	    	case 2:
	    		if( !selecionandoEstilo ) {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 3 + painelDeJogo.seletorDeLingua*7 ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 120, 320 );
	    		}
	    		else {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, estilos[ painelDeJogo.seletorDeEstilo + painelDeJogo.seletorDeLingua*3 ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 280, 320 );
	    		}
				break;
	
	    	case 3:
	    		if( !selecionandoVolumeDasMusicas ) {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 4 + painelDeJogo.seletorDeLingua*7 ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 120, 368 );
	    		}
	    		else {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, volumes[ painelDeJogo.seletorDeVolumeDasMusicas ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 280 + (1-painelDeJogo.seletorDeLingua)*133, 368 );
	    		}
				break;
	
	    	case 4:
	    		if( !selecionandoVolumeDosEfeitosSonoros ) {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 5 + painelDeJogo.seletorDeLingua*7 ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 120, 418 );
	    		}
	    		else {
	        	    painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, volumes[ painelDeJogo.seletorDeVolumeDosEfeitosSonoros ], 
	        	    		2, 20, 2, Color.yellow, Color.black, 280 + (1-painelDeJogo.seletorDeLingua)*133, 418 );
	    		}
				break;
	
			case 5:
		    	painelDeJogo.interfaceGrafica.escreveTextoComSombra( graphics2D, textosDoEstado[ 6 + painelDeJogo.seletorDeLingua*7 ], 
		    			2, 20, 2, Color.yellow, Color.black, 120, 514 );
		    	break;
    	}
	}
    
    
    public void teclaConfirma() {
    	
    	painelDeJogo.gerenciadorDeAudio.audios[1].tocaAudioSimples();
    	switch( opcaoDoMenu ) {
	    	case 0:
	        	if( !selecionandoDificuldade ) {
	        		selecionandoDificuldade = true;
	        	}
	        	else {
	        		selecionandoDificuldade = false;
	        		if ( this.painelDeJogo.seletorDeDificuldade == 0 ) {
	        			painelDeJogo.nivelDeDificuldade = new NivelDeDificuldadeFacil( this.painelDeJogo );
	        		}
	        		else if ( this.painelDeJogo.seletorDeDificuldade == 1 ) {
	        			painelDeJogo.nivelDeDificuldade = new NivelDeDificuldadeMedio( this.painelDeJogo );
	        		}
	        		else {
	        			painelDeJogo.nivelDeDificuldade = new NivelDeDificuldadeDificil( this.painelDeJogo );
	        		}
	        	}
				break;
	
	    	case 1:
	        	if( !selecionandoLingua ) {
	        		selecionandoLingua = true;
	        	}
	        	else {
	        		selecionandoLingua = false;
	        	}
				break;
	
	    	case 2:
	        	if( !selecionandoEstilo ) {
	        		selecionandoEstilo = true;
	        	}
	        	else {
	        		selecionandoEstilo = false;
	        	}
				break;
	
	    	case 3:
	        	if( !selecionandoVolumeDasMusicas ) {
	        		selecionandoVolumeDasMusicas = true;
	        	}
	        	else {
	        		selecionandoVolumeDasMusicas = false;
	        	}
				break;
	
	    	case 4:
	        	if( !selecionandoVolumeDosEfeitosSonoros ) {
	        		selecionandoVolumeDosEfeitosSonoros = true;
	        	}
	        	else {
	        		selecionandoVolumeDosEfeitosSonoros = false;
	        	}
				break;
	
			case 5:
		    	painelDeJogo.mudaEstado( new EstadoDoMenuPrincipal( this.painelDeJogo ) );
		    	break;
		}
	}

    
	public void teclaParaCima() {
		
		if( !selecionandoDificuldade && !selecionandoLingua && !selecionandoEstilo && 
			!selecionandoVolumeDasMusicas && !selecionandoVolumeDosEfeitosSonoros ) {
			if( opcaoDoMenu > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				opcaoDoMenu--;
			}
		}
	}

	
	public void teclaParaBaixo() {
		
		if( !selecionandoDificuldade && !selecionandoLingua && !selecionandoEstilo && 
			!selecionandoVolumeDasMusicas && !selecionandoVolumeDosEfeitosSonoros ) {
			if( opcaoDoMenu < numeroDeOpcoesDoMenu-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				opcaoDoMenu++;
			}
		}
	}

	
	public void teclaParaDireita() {
		
		if( selecionandoDificuldade ) {
			if( painelDeJogo.seletorDeDificuldade < numeroDeDificuldades-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeDificuldade++;
			}
		}
		else if( selecionandoLingua ) {
			if( painelDeJogo.seletorDeLingua < numeroDeLinguas-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeLingua++;
			}
		}
		else if( selecionandoEstilo ) {
			if( painelDeJogo.seletorDeEstilo < numeroDeEstilos-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeEstilo++;
				
				painelDeJogo.gerenciadorDeAudio.audios[12].paraAudio();
			
				painelDeJogo.estilo = painelDeJogo.fabricaDeEstilos.getEstilo( painelDeJogo );
				painelDeJogo.interfaceGrafica = painelDeJogo.estilo.criaInterfaceGrafica();
				painelDeJogo.gerenciadorDeCelulas = painelDeJogo.estilo.criaGerenciadorDeCelulas();
				painelDeJogo.gerenciadorDeEntidades = painelDeJogo.estilo.criaGerenciadorDeEntidades();
				painelDeJogo.gerenciadorDeAudio = painelDeJogo.estilo.criaGerenciadorDeAudio();
				
				painelDeJogo.gerenciadorDeAudio.audios[12].tocaAudioContinuo();
			}
		}
		else if( selecionandoVolumeDasMusicas ) {
			if( painelDeJogo.seletorDeVolumeDasMusicas < numeroDeVolumes-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeVolumeDasMusicas++;
				
				for( Audio audio : painelDeJogo.gerenciadorDeAudio.audios ) {
					if( audio.tipoDeAudio == 0 ) {
						audio.alteraVolume( painelDeJogo.seletorDeVolumeDasMusicas * 0.1f );
					}
				}
			}
		}
		else if( selecionandoVolumeDosEfeitosSonoros ) {
			if( painelDeJogo.seletorDeVolumeDosEfeitosSonoros < numeroDeVolumes-1 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeVolumeDosEfeitosSonoros++;
				
				for( Audio audio : painelDeJogo.gerenciadorDeAudio.audios ) {
					if( audio.tipoDeAudio == 1 ) {
						audio.alteraVolume( painelDeJogo.seletorDeVolumeDosEfeitosSonoros * 0.1f );
					}
				}
			}
		}
	}

	
	public void teclaParaEsquerda() {
		
		if( selecionandoDificuldade ) {
			if( painelDeJogo.seletorDeDificuldade > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeDificuldade--;
			}
		}
		else if( selecionandoLingua ) {
			if( painelDeJogo.seletorDeLingua > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeLingua--;
			}
		}
		else if( selecionandoEstilo ) {
			if( painelDeJogo.seletorDeEstilo > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeEstilo--;

				painelDeJogo.gerenciadorDeAudio.audios[12].paraAudio();
			
				painelDeJogo.estilo = painelDeJogo.fabricaDeEstilos.getEstilo( painelDeJogo );
				painelDeJogo.interfaceGrafica = painelDeJogo.estilo.criaInterfaceGrafica();
				painelDeJogo.gerenciadorDeCelulas = painelDeJogo.estilo.criaGerenciadorDeCelulas();
				painelDeJogo.gerenciadorDeEntidades = painelDeJogo.estilo.criaGerenciadorDeEntidades();
				painelDeJogo.gerenciadorDeAudio = painelDeJogo.estilo.criaGerenciadorDeAudio();
				
				painelDeJogo.gerenciadorDeAudio.audios[12].tocaAudioContinuo();
			}
		}
		else if( selecionandoVolumeDasMusicas ) {
			if( painelDeJogo.seletorDeVolumeDasMusicas > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeVolumeDasMusicas--;
				
				for( Audio audio : painelDeJogo.gerenciadorDeAudio.audios ) {
					if( audio.tipoDeAudio == 0 ) {
						audio.alteraVolume( painelDeJogo.seletorDeVolumeDasMusicas * 0.1f );
					}
				}
			}
		}
		else if( selecionandoVolumeDosEfeitosSonoros ) {
			if( painelDeJogo.seletorDeVolumeDosEfeitosSonoros > 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[0].tocaAudioSimples();
				painelDeJogo.seletorDeVolumeDosEfeitosSonoros--;
				
				for( Audio audio : painelDeJogo.gerenciadorDeAudio.audios ) {
					if( audio.tipoDeAudio == 1 ) {
						audio.alteraVolume( painelDeJogo.seletorDeVolumeDosEfeitosSonoros * 0.1f );
					}
				}
			}
		}
	}
}
