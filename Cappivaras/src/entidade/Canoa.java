package entidade;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class Canoa extends Entidade {
	
	// Imagens da animação de movimento da canoa.
	public BufferedImage direita1, direita2, direita3, direita4, direita5, direita6, direita7, direita8, direita9, direita10,
	                     esquerda1, esquerda2, esquerda3, esquerda4, esquerda5, esquerda6, esquerda7, esquerda8, esquerda9, esquerda10 ;
	
	
    public Canoa( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.altura = 1;
		this.largura = 4;
	}

    
    public Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao ) {

    	Canoa canoa = new Canoa( this.painelDeJogo );
		
    	canoa.mapaX = mapaX*painelDeJogo.tamanhoDaCelula;
		canoa.mapaY = mapaY*painelDeJogo.tamanhoDaCelula;
		
		canoa.direcao = direcao;
		
		if( direcao.equals( "direita" ) ) {
			canoa.velocidade = velocidade;
		}
		else {
			canoa.velocidade = -velocidade;
		}
		
		canoa.direita1   = this.direita1;
		canoa.direita2   = this.direita2;
		canoa.direita3   = this.direita3;
		canoa.direita4   = this.direita4;
		canoa.direita5   = this.direita5;
		canoa.direita6   = this.direita6;
		canoa.direita7   = this.direita7;
		canoa.direita8   = this.direita8;
		canoa.direita9   = this.direita9;
		canoa.direita10  = this.direita10;
		canoa.esquerda1  = this.esquerda1;
		canoa.esquerda2  = this.esquerda2;
		canoa.esquerda3  = this.esquerda3;
		canoa.esquerda4  = this.esquerda4;
		canoa.esquerda5  = this.esquerda5;
		canoa.esquerda6  = this.esquerda6;
		canoa.esquerda7  = this.esquerda7;
		canoa.esquerda8  = this.esquerda8;
		canoa.esquerda9  = this.esquerda9;
		canoa.esquerda10 = this.esquerda10;
		
		return canoa;
	}

	
    public void carregaImagensDaEntidade() {
		
		try {
			direita1   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita1.png" ) );
			direita2   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita2.png" ) );
			direita3   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita3.png" ) );
			direita4   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita4.png" ) );
			direita5   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita5.png" ) );
			direita6   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita6.png" ) );
			direita7   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita7.png" ) );
			direita8   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita8.png" ) );
			direita9   = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita9.png" ) );
			direita10  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaDireita10.png" ) );
			esquerda1  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda1.png" ) );
			esquerda2  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda2.png" ) );
			esquerda3  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda3.png" ) );
			esquerda4  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda4.png" ) );
			esquerda5  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda5.png" ) );
			esquerda6  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda6.png" ) );
			esquerda7  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda7.png" ) );
			esquerda8  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda8.png" ) );
			esquerda9  = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda9.png" ) );
			esquerda10 = ImageIO.read( getClass().getResourceAsStream( enderecoDeImagens + "canoaEsquerda10.png" ) );
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
    
	public void atualizaEntidade() {
		
		// Atualiza informações gerais da canoa.
		painelDeJogo.nivelDeDificuldade.atualizaCanoa( this );
		
		// Atualiza a imagem da canoa a ser desenhada.
		switch( direcao ) {
			case "direita":
				if( contadorDeCicloDaEntidade % 40 < 4 )
					imagem = direita1;
				else if( contadorDeCicloDaEntidade % 40 < 8 )
					imagem = direita2;
				else if( contadorDeCicloDaEntidade % 40 < 12 )
					imagem = direita3;
				else if( contadorDeCicloDaEntidade % 40 < 16 )
					imagem = direita4;
				else if( contadorDeCicloDaEntidade % 40 < 20 )
					imagem = direita5;
				else if( contadorDeCicloDaEntidade % 40 < 24 )
					imagem = direita6;
				else if( contadorDeCicloDaEntidade % 40 < 28 )
					imagem = direita7;
				else if( contadorDeCicloDaEntidade % 40 < 32 )
					imagem = direita8;
				else if( contadorDeCicloDaEntidade % 40 < 36 )
					imagem = direita9;
				else
					imagem = direita10;
				break;
				
			case "esquerda":
				if( contadorDeCicloDaEntidade % 40 < 4 )
					imagem = esquerda1;
				else if( contadorDeCicloDaEntidade % 40 < 8 )
					imagem = esquerda2;
				else if( contadorDeCicloDaEntidade % 40 < 12 )
					imagem = esquerda3;
				else if( contadorDeCicloDaEntidade % 40 < 16 )
					imagem = esquerda4;
				else if( contadorDeCicloDaEntidade % 40 < 20 )
					imagem = esquerda5;
				else if( contadorDeCicloDaEntidade % 40 < 24 )
					imagem = esquerda6;
				else if( contadorDeCicloDaEntidade % 40 < 28 )
					imagem = esquerda7;
				else if( contadorDeCicloDaEntidade % 40 < 32 )
					imagem = esquerda8;
				else if( contadorDeCicloDaEntidade % 40 < 36 )
					imagem = esquerda9;
				else
					imagem = esquerda10;
				break;
		}
		
		// Atualiza a posição da tela na qual será desenhada a imagem da canoa.
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