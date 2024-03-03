package entidade;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Barco extends Entidade {
	
	// Imagens da animação de movimento do barco-draga.
	public BufferedImage cima1, baixo1, direita1, esquerda1,
					     cima2, baixo2, direita2, esquerda2,
					     cima3, baixo3, direita3, esquerda3,
					     cima4, baixo4, direita4, esquerda4;
	
	
    public Barco( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 2;
	}
	
	
	public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) {

		Barco barco = new Barco( this.painelDeJogo );
		
		barco.mapaX = mapaX*painelDeJogo.tamanhoDaCelula;
		barco.mapaY = mapaY*painelDeJogo.tamanhoDaCelula;
		
		barco.direcao = direcao;
		
		if( direcao.equals( "baixo" ) || direcao.equals( "direita" ) ) {
			barco.velocidade = velocidade;
		}
		else {
			barco.velocidade = -velocidade;
		}
		
		if( direcao.equals( "cima" ) || direcao.equals( "baixo" ) ) {
			barco.altura = 2;
			barco.largura = 1;
		}
		else {
			barco.altura = 1;
			barco.largura = 2;
		}
		
		barco.cima1 = this.cima1;
		barco.cima2 = this.cima2;
		barco.cima3 = this.cima3;
		barco.cima4 = this.cima4;
		barco.baixo1 = this.baixo1;
		barco.baixo2 = this.baixo2;
		barco.baixo3 = this.baixo3;
		barco.baixo4 = this.baixo4;
		barco.direita1 = this.direita1;
		barco.direita2 = this.direita2;
		barco.direita3 = this.direita3;
		barco.direita4 = this.direita4;
		barco.esquerda1 = this.esquerda1;
		barco.esquerda2 = this.esquerda2;
		barco.esquerda3 = this.esquerda3;
		barco.esquerda4 = this.esquerda4;
		
		return barco;
	}

	
    public void carregaImagensDaEntidade() {
		
		try {
			cima1     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoCima1.png" ) );
			cima2     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoCima2.png" ) );
			cima3     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoCima3.png" ) );
			cima4     = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoCima4.png" ) );
			baixo1    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoBaixo1.png" ) );
			baixo2    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoBaixo2.png" ) );
			baixo3    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoBaixo3.png" ) );
			baixo4    = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoBaixo4.png" ) );
			direita1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoDireita1.png" ) );
			direita2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoDireita2.png" ) );
			direita3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoDireita3.png" ) );
			direita4  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoDireita4.png" ) );
			esquerda1 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoEsquerda1.png" ) );
			esquerda2 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoEsquerda2.png" ) );
			esquerda3 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoEsquerda3.png" ) );
			esquerda4 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "barcoEsquerda4.png" ) );
		} catch( IOException e ) { e.printStackTrace(); }
	}
	
	
	public void atualizaEntidade() {
		
		// Atualiza informações gerais do barco.
		painelDeJogo.nivelDeDificuldade.atualizaBarco( this );
		
		// Atualiza a imagem do barco a ser desenhada.
		switch( direcao ) {
			case "cima":
				if( contadorDeCicloDaEntidade % 16 < 4 ) {
					imagem = cima1;
				} else if( contadorDeCicloDaEntidade % 16 < 8 ) {
					imagem = cima2;
				} else if( contadorDeCicloDaEntidade % 16 < 12 ) {
					imagem = cima3;
		        } else {
					imagem = cima3;
			    }
				break;
				
			case "baixo":
				if( contadorDeCicloDaEntidade % 16 < 4 ) {
					imagem = baixo1;
				} else if( contadorDeCicloDaEntidade % 16 < 8 ) {
					imagem = baixo2;
				} else if( contadorDeCicloDaEntidade % 16 < 12 ) {
					imagem = baixo3;
		        } else {
					imagem = baixo3;
			    }
				break;
				
			case "direita":
				if( contadorDeCicloDaEntidade % 16 < 4 ) {
					imagem = direita1;
				} else if( contadorDeCicloDaEntidade % 16 < 8 ) {
					imagem = direita2;
				} else if( contadorDeCicloDaEntidade % 16 < 12 ) {
					imagem = direita3;
		        } else {
					imagem = direita3;
			    }
				break;
				
		    case "esquerda":
		    	if( contadorDeCicloDaEntidade % 16 < 4 ) {
					imagem = esquerda1;
				} else if( contadorDeCicloDaEntidade % 16 < 8 ) {
					imagem = esquerda2;
				} else if( contadorDeCicloDaEntidade % 16 < 12 ) {
					imagem = esquerda3;
		        } else {
					imagem = esquerda3;
			    }
				break;
		}
		
		// Atualiza a posição da tela na qual será desenhada a imagem do barco.
		imagemX = mapaX - painelDeJogo.capivara.mapaX + painelDeJogo.capivara.telaX;
		imagemY = mapaY - painelDeJogo.capivara.mapaY + painelDeJogo.capivara.telaY;
		
		if( painelDeJogo.capivara.telaX > painelDeJogo.capivara.mapaX ) {
			imagemX = mapaX;
		}
	    if( painelDeJogo.capivara.telaY > painelDeJogo.capivara.mapaY ) {
	    	imagemY = mapaY;
		}
	    if( painelDeJogo.larguraDaTela - painelDeJogo.capivara.telaX > painelDeJogo.larguraDoMapa - painelDeJogo.capivara.mapaX ) {
	    	imagemX = painelDeJogo.larguraDaTela - (painelDeJogo.larguraDoMapa - mapaX);
	    }
	    if( painelDeJogo.alturaDaTela - painelDeJogo.capivara.telaY > painelDeJogo.alturaDoMapa - painelDeJogo.capivara.mapaY ) {
	    	imagemY = painelDeJogo.alturaDaTela - (painelDeJogo.alturaDoMapa - mapaY);
	    }
	}
}
