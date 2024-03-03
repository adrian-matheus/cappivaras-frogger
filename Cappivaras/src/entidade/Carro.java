package entidade;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Carro extends Entidade {
	
	// Imagens da animação de movimento do carro.
	public BufferedImage direita1, direita2, direita3, direita4,
	                     esquerda1, esquerda2, esquerda3, esquerda4;
	
	
    public Carro( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 2;
	}
	
	
    public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) {

    	Carro carro = new Carro( this.painelDeJogo );
		
    	carro.mapaX = mapaX*painelDeJogo.tamanhoDaCelula;
    	carro.mapaY = mapaY*painelDeJogo.tamanhoDaCelula;
		
		carro.direcao = direcao;
		
		if( direcao.equals( "direita" ) ) {
			carro.velocidade = velocidade;
		}
		else {
			carro.velocidade = -velocidade;
		}
		
		carro.direita1  = this.direita1;
		carro.direita2  = this.direita2;
		carro.direita3  = this.direita3;
		carro.direita4  = this.direita4;
		carro.esquerda1 = this.esquerda1;
		carro.esquerda2 = this.esquerda2;
		carro.esquerda3 = this.esquerda3;
		carro.esquerda4 = this.esquerda4;
		
		return carro;
	}

	
   public void carregaImagensDaEntidade() {
		
		try {
			direita1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroDireita1.png" ) );
			direita2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroDireita2.png" ) );
			direita3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroDireita3.png" ) );
			direita4  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroDireita4.png" ) );
			esquerda1 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroEsquerda1.png" ) );
			esquerda2 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroEsquerda2.png" ) );
			esquerda3 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroEsquerda3.png" ) );
			esquerda4 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "carroEsquerda4.png" ) );
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	public void atualizaEntidade() {
		
		// Atualiza informações gerais do carro.
		painelDeJogo.nivelDeDificuldade.atualizaCarro( this );
		
		// Atualiza a imagem do carro a ser desenhada.
		switch( direcao ) {
			case "direita":
				if( contadorDeCicloDaEntidade % 8 < 2 )
					imagem = direita1;
				else if( contadorDeCicloDaEntidade % 8 < 4 )
					imagem = direita2;
				else if( contadorDeCicloDaEntidade % 8 < 6 )
					imagem = direita3;
				else
					imagem = direita4;
				break;
				
			case "esquerda":
				if( contadorDeCicloDaEntidade % 8 < 2 )
					imagem = esquerda1;
				else if( contadorDeCicloDaEntidade % 8 < 4 )
					imagem = esquerda2;
				else if( contadorDeCicloDaEntidade % 8 < 6 )
					imagem = esquerda3;
				else
					imagem = esquerda4;
				break;
		}
		
		// Atualiza a posição da tela na qual será desenhada a imagem do carro.
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