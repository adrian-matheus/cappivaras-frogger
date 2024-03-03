package entidade;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Ciclista extends Entidade {
	
	// Imagens da animação de movimento do ciclista.
	public BufferedImage direita1, direita2, direita3, direita4, 
	                     esquerda1, esquerda2, esquerda3, esquerda4;
	
	
	public Ciclista( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 2;
	}
	
	
	public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) {

	    	Ciclista ciclista = new Ciclista( this.painelDeJogo );
			
	    	ciclista.mapaX = mapaX*painelDeJogo.tamanhoDaCelula;
	    	ciclista.mapaY = mapaY*painelDeJogo.tamanhoDaCelula;
			
			ciclista.direcao = direcao;
			
			if( direcao.equals( "direita" ) ) {
				ciclista.velocidade = velocidade;
			}
			else {
				ciclista.velocidade = -velocidade;
			}
			
			ciclista.direita1  = this.direita1;
			ciclista.direita2  = this.direita2;
			ciclista.direita3  = this.direita3;
			ciclista.direita4  = this.direita4;
			ciclista.esquerda1 = this.esquerda1;
			ciclista.esquerda2 = this.esquerda2;
			ciclista.esquerda3 = this.esquerda3;
			ciclista.esquerda4 = this.esquerda4;
			
			return ciclista;
	}

	
    public void carregaImagensDaEntidade() {
		
		try {
			direita1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaDireita1.png" ) );
			direita2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaDireita2.png" ) );
			direita3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaDireita3.png" ) );
			direita4  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaDireita4.png" ) );
			esquerda1 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaEsquerda1.png" ) );
			esquerda2 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaEsquerda2.png" ) );
			esquerda3 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaEsquerda3.png" ) );
			esquerda4 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "ciclistaEsquerda4.png" ) );
		} catch( IOException e ) { e.printStackTrace(); }
	}

	
	public void atualizaEntidade() {
		
		// Atualiza informações gerais do ciclista.
		painelDeJogo.nivelDeDificuldade.atualizaCiclista( this );	
		
		// Atualiza a imagem do ciclista a ser desenhada.
		switch( direcao ) {
			case "direita":
				if( contadorDeCicloDaEntidade % 10 < 4 )
					imagem = direita1;
				else if( contadorDeCicloDaEntidade % 10 < 6 )
					imagem = direita2;
				else if( contadorDeCicloDaEntidade % 10 < 8 )
					imagem = direita3;
				else
					imagem = direita4;
				break;
				
			case "esquerda":
				if( contadorDeCicloDaEntidade % 10 < 4 )
					imagem = esquerda1;
				else if( contadorDeCicloDaEntidade % 10 < 6 )
					imagem = esquerda2;
				else if( contadorDeCicloDaEntidade % 10 < 8 )
					imagem = esquerda3;
				else
					imagem = esquerda4;
				break;
		}
		
		// Atualiza a posição da tela na qual será desenhada a imagem do ciclista.
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
