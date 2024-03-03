package entidade;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Capivara extends Entidade {
	
	private static Capivara instancia = null;
	
	public int vida; // Vidas da capivara.
	
	public int telaX, telaY; // Posi��o da capivara na tela (nota: na tela, n�o no mapa).
	
	public int mapaXInicial, mapaYInicial; // Posi��o inicial da capivara no mapa.
	public int velocidadeInicial; // Velocidade inicial da capivara.
	public String direcaoInicial; // Dire��o inicial da capivara.
	
	public boolean emColisaoComCelula = false;   // Estado de colis�o da capivara com c�lulas do mapa.
	public boolean emColisaoComEntidade = false; // Estado de colis�o da capivara com entidades no mapa.
	public boolean emColisaoComBarco = false;    // Estado de colis�o da capivara com barcos para travessia do rio pinheiros.
	
	public boolean emMovimento = false; // Estado de movimento ou repouso da capivara.
	public int contadorDeMovimento = 0; // Guarda quantos pixels a capivara j� percorreu durante o estado de movimento atual.
	public int contadorDeRepouso = 0;   // Guarda quantos ciclos a capivara permaneceu parada durante o estado de repouso atual.
	
    // Teclas do movimento da capivara.
	public boolean teclaParaCima, teclaParaBaixo, teclaParaDireita, teclaParaEsquerda;
	
	// Imagens da anima��o de movimento da capivara.
	public BufferedImage cima1, baixo1, direita1, esquerda1,
                         cima2, baixo2, direita2, esquerda2,
                         cima3, baixo3, direita3, esquerda3;
	
    private Capivara( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 1;
		
		/* Estabelece a posi��o da capivara na tela (nota: na tela, n�o no mapa). 
		 * As coordenadas s�o para o canto esquerdo superior da c�lula da capivara, 
		 * ent�o para que o centro seja o centro da c�lula, e n�o sua ponta, basta
		 * subtrair metade do lado da c�lula em ambas as dimens�es. 
		 */
		telaX = painelDeJogo.larguraDaTela/2 - (painelDeJogo.tamanhoDaCelula/2); 
		telaY = painelDeJogo.alturaDaTela/2 - (painelDeJogo.tamanhoDaCelula/2);
		
		/* Reposiciona a c�mera, que � dada em fun��o da posi��o da capivara,
		 * nas coordenadas inicias para a disposi��o das telas de menus.
		 */
		atualizaInformacoesDaCapivara( 0, 0, 0, "", 0);
    }
	
    
    // M�todo que atualiza as informa��es da capivara num dado evento pontual.
	public void atualizaInformacoesDaCapivara( int mapaX, int mapaY, int velocidade, String direcao, int vida ) {
		
		// Reseta os contadores da capivara.
		this.contadorDeCicloDaEntidade = 0;
		this.contadorDeMovimento = 0;
		this.contadorDeRepouso = 0;
		
		// Atualiza a posi��o da capivara no mapa.
		this.mapaX = mapaX; 
		this.mapaY = mapaY;
		
		// Atualiza a velocidade da capivara.
		this.velocidade = velocidade;
		
		// Atualiza a dire��o da capivara.
		this.direcao = direcao;
		
		// Atualiza o contador de vidas da capivara.
		this.vida = vida;
		
		// Atualiza o estado de colis�o da capivara.
		this.emColisaoComEntidade = false;
	}
	
	
	// M�todo que permite o acesso � inst�ncia �nica desta capivara.
	public static Capivara getInstancia( PainelDeJogo painelDeJogo ) {
		
		if( instancia == null )
			instancia = new Capivara( painelDeJogo );
		return instancia;
	}
	
	
	public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) { 
		
		mapaXInicial = mapaX*painelDeJogo.tamanhoDaCelula;
		mapaYInicial = mapaY*painelDeJogo.tamanhoDaCelula;
		velocidadeInicial = velocidade;
		direcaoInicial = direcao;
		
		return Capivara.getInstancia( painelDeJogo );
	}
	
	
    public void carregaImagensDaEntidade() {
		
		try {
			cima1     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraCima1.png" ) );
			cima2     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraCima2.png" ) );
			cima3     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraCima3.png" ) );
			baixo1    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraBaixo1.png" ) );
			baixo2    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraBaixo2.png" ) );
			baixo3    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraBaixo3.png" ) );
			direita1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraDireita1.png" ) );
			direita2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraDireita2.png" ) );
			direita3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraDireita3.png" ) );
			esquerda1 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraEsquerda1.png" ) );
			esquerda2 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraEsquerda2.png" ) );
			esquerda3 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "capivaraEsquerda3.png" ) );
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	public void atualizaEntidade() {
		
		emColisaoComBarco = false;
		emColisaoComEntidade = false;
		painelDeJogo.verificadorDeColisao.verificaColisaoComEntidades();
		
		// Checa a colis�o da capivara com outras entidades no mapa.
		if( emColisaoComEntidade ) {
			if( vida-1 == 0 ) {
				painelDeJogo.gerenciadorDeAudio.audios[11].tocaAudioSimples();
			} else {
				painelDeJogo.gerenciadorDeAudio.audios[10].tocaAudioSimples();
			}
			atualizaInformacoesDaCapivara( mapaXInicial, mapaYInicial, velocidadeInicial, direcaoInicial, --this.vida );
		}
		
		int colunaAtual = mapaX/painelDeJogo.tamanhoDaCelula;
		int linhaAtual  = mapaY/painelDeJogo.tamanhoDaCelula;
		
		// Checa a colis�o da capivara com o rio pinheiros.
		if( mapaX % painelDeJogo.tamanhoDaCelula == 0 && mapaY % painelDeJogo.tamanhoDaCelula == 0 ) {
			if( painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual][colunaAtual] > 9 &&
				painelDeJogo.gerenciadorDeCelulas.listaDeMapas.get(0)[linhaAtual][colunaAtual] < 19 ) {
				if( !emColisaoComBarco ) {
					if( vida-1 == 0 ) {
						painelDeJogo.gerenciadorDeAudio.audios[11].tocaAudioSimples();
					} else {
						painelDeJogo.gerenciadorDeAudio.audios[9].tocaAudioSimples();
					}
					atualizaInformacoesDaCapivara( mapaXInicial, mapaYInicial, velocidadeInicial, direcaoInicial, --this.vida );
				}
			}
		}
		
		
		/* A capivara somente aceita comandos de movimento do teclado quando est� em repouso, 
		 * isto �, apenas depois de terminar de realizar o movimento anterior, o que garante
		 * um movimento 'c�lula a c�lula' da capivara pelo mapa de jogo.
		 */
		if( !emMovimento ) {
			if( teclaParaCima || teclaParaBaixo || teclaParaEsquerda || teclaParaDireita ) {
				
				if( teclaParaCima ) {
					direcao = "cima";
				}
				else if( teclaParaBaixo ) {
					direcao = "baixo";
				}
				else if( teclaParaDireita ) {
					direcao = "direita";
				}
				else if( teclaParaEsquerda ) {
					direcao = "esquerda";
				}	
				
				emMovimento = true;
		    }
			else {
				// Reseta a imagem da capivara quando ela fica parada por um certo tempo.
				contadorDeRepouso++;
				
				if( contadorDeRepouso == 6 ) {
					contadorDeImagem = 1;
					contadorDeRepouso = 0;
				}
			}
		}
		
		
		if( emMovimento )  {
			// Checa a colis�o da capivara com as c�lulas do mapa.
			emColisaoComCelula = false;
			painelDeJogo.verificadorDeColisao.verificaColisaoComCelulas();
				
			if( !emColisaoComCelula ) {
				if( mapaY/painelDeJogo.tamanhoDaCelula > 62 && mapaY/painelDeJogo.tamanhoDaCelula < 71 ) {
					painelDeJogo.gerenciadorDeAudio.audios[6].tocaAudioSimples();
				} else {
					painelDeJogo.gerenciadorDeAudio.audios[5].tocaAudioSimples();
				}
				
	    		switch( direcao ) {
				    case "cima":
					    mapaY -= velocidade;
					    break;
					    	
					case "baixo":
						mapaY += velocidade;
					    break;
					    	
					case "direita":
						mapaX += velocidade;
					    break;
					    	
					case "esquerda":
						mapaX -= velocidade;
					    break;
	    		}
			} else {
				if( ( teclaParaCima || teclaParaBaixo || teclaParaEsquerda || teclaParaDireita ) && ( painelDeJogo.contadorDeCiclo % 18 == 0 ) ) {
					painelDeJogo.gerenciadorDeAudio.audios[8].tocaAudioSimples();
				}
			}
			
			// Atualiza a imagem da capivara.
			if( contadorDeCicloDaEntidade % 6 == 0 ) {
				if( contadorDeImagem == 1 ) {
					contadorDeImagem = 2;
				}
				else if (contadorDeImagem == 2) {
					contadorDeImagem = 3;
				}
				else if (contadorDeImagem == 3) {
					contadorDeImagem = 1;
				}
			}
			
			// Garante que o pr�ximo movimento s� possa ser iniciado ap�s o t�rmino deste.
			contadorDeMovimento += velocidade;
			if( contadorDeMovimento == painelDeJogo.tamanhoDaCelula ) {
				emMovimento = false;
				contadorDeMovimento = 0;
			}
		}
		
		// Atualiza a imagem da capviara a ser desenhada.
		switch( direcao ) {
			case "cima":	
				if( contadorDeImagem == 1 ) {
					imagem = cima1;
				}
				else if( contadorDeImagem == 2 ) {
					imagem = cima2;
				}
				else if( contadorDeImagem == 3 ) {
					imagem = cima3;
				}
				break;
				
			case "baixo":	
				if( contadorDeImagem == 1 ) {
					imagem = baixo1;
				}
				else if( contadorDeImagem == 2 ) {
					imagem = baixo2;
				}
				else if( contadorDeImagem == 3 ) {
					imagem = baixo3;
				}
				break;
				
			case "direita":
				if( contadorDeImagem == 1 ) {
					imagem = direita1;
				}
				else if( contadorDeImagem == 2 ) {
					imagem = direita2;
				}
				else if( contadorDeImagem == 3 ) {
					imagem = direita3;
				}
				break;
				
			case "esquerda":
				if( contadorDeImagem == 1 ) {
					imagem = esquerda1;
				}
				else if( contadorDeImagem == 2 ) {
					imagem = esquerda2;
				}
				else if( contadorDeImagem == 3 ) {
					imagem = esquerda3;
				}
				break;
		}
		
		imagemX = telaX;
		imagemY = telaY;
		
		if( telaX > mapaX ) {
			imagemX = mapaX;
		}
		if( telaY > mapaY ) {
			imagemY = mapaY;
		}
		if( painelDeJogo.larguraDaTela - telaX > painelDeJogo.larguraDoMapa - mapaX ) {
			imagemX = painelDeJogo.larguraDaTela - (painelDeJogo.larguraDoMapa - mapaX);
	    }
	    if( painelDeJogo.alturaDaTela - telaY > painelDeJogo.alturaDoMapa - mapaY ) {
	    	imagemY = painelDeJogo.alturaDaTela - (painelDeJogo.alturaDoMapa - mapaY);
	    }
		
		contadorDeCicloDaEntidade++;
		
		if( contadorDeCicloDaEntidade == 600 ) {
			contadorDeCicloDaEntidade = 0;
		}
	}
}
