package entidade;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Caminhao extends Entidade {
	
	// Imagens da animação de movimento do caminhão.
	public BufferedImage direita1, direita2, direita3, direita4,
	                     esquerda1, esquerda2, esquerda3, esquerda4;
	
	
    public Caminhao( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 2;
	}
	
	
    public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) {

    	Caminhao caminhao = new Caminhao( this.painelDeJogo );
		
    	caminhao.mapaX = mapaX*painelDeJogo.tamanhoDaCelula;
    	caminhao.mapaY = mapaY*painelDeJogo.tamanhoDaCelula;
		
		caminhao.direcao = direcao;
		
		if( direcao.equals( "direita" ) ) {
			caminhao.velocidade = velocidade;
		}
		else {
			caminhao.velocidade = -velocidade;
		}
		
		caminhao.direita1  = this.direita1;
		caminhao.direita2  = this.direita2;
		caminhao.direita3  = this.direita3;
		caminhao.direita4  = this.direita4;
		caminhao.esquerda1 = this.esquerda1;
		caminhao.esquerda2 = this.esquerda2;
		caminhao.esquerda3 = this.esquerda3;
		caminhao.esquerda4 = this.esquerda4;
		
		return caminhao;
	}

	
   public void carregaImagensDaEntidade() {
		
		try {
			direita1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoDireita1.png" ) );
			direita2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoDireita2.png" ) );
			direita3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoDireita3.png" ) );
			direita4  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoDireita4.png" ) );
			esquerda1 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoEsquerda1.png" ) );
			esquerda2 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoEsquerda2.png" ) );
			esquerda3 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoEsquerda3.png" ) );
			esquerda4 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "caminhaoEsquerda4.png" ) );
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	public void atualizaEntidade() {
		
		// Atualiza informações gerais do caminhão.
		painelDeJogo.nivelDeDificuldade.atualizaCaminhao( this );
		
		// Atualiza a imagem do caminhão a ser desenhada.
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
		
		// Atualiza a posição da tela na qual será desenhada a imagem do caminhão.
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