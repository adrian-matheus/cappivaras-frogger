package celula;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeCelulas {
	
	private PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	
	public Celula[] celulas; // Conjunto dos diferentes tipos de c�lulas instanciadas.
	
	public ArrayList< int[][] > listaDeMapas = new ArrayList<>(); // Lista de mapas do jogo.
	
    public int contadorDeCicloDaAgua = 0; // Contador para sincroniza��o da anima��o da �gua da raia e do rio pinheiros.
    public int contadorDeCicloDoVidro = 0; // Contador para sincroniza��o da anima��o do vidro da USP futurista.
    	
 	
	public GerenciadorDeCelulas( PainelDeJogo painelDeJogo, String endereco ) {
		
		this.painelDeJogo = painelDeJogo;
		
		instanciaCelulas( endereco );
		
		// Os mapas s�o matrizes de inteiros nas quais cada entrada representa o c�digo para um tipo de c�lula.
		carregaMapaDeCelulas( "/mapaDeCelulasInferior.txt" ); // Mapa das c�lulas a serem desenhadas na camada inferior da tela.
		carregaMapaDeCelulas( "/mapaDeCelulasSuperior.txt" ); // Mapa das c�lulass a serem desenhadas na camada superior da tela.
		carregaMapaDeCelulas( "/mapaDeCelulasDoMenuPrincipal.txt" ); // Mapa das c�lulas a serem desenhadas na tela do menu inicial.
		carregaMapaDeCelulas( "/mapaDeCelulasDoMenuDeConfiguracoes.txt" );  // Mapa das c�lulas a serem desenhadas na tela do menu de configura��es.
		carregaMapaDeCelulas( "/mapaDeCelulasDaTelaDeFimDeJogo.txt" ); // Mapa das c�lulas a serem desenhadas na tela de fim de jogo.
	}
	
	
	// M�todo que instancia os diferentes tipos de c�lulas.
    public void instanciaCelulas( String endereco ) {

    	// Busca as informa��es de cada tipo de c�lula num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );
			
			int linha = 1; // A c�lula de �ndice 0� instanciada � parte, ent�o partimos do �ndice 1.
			int numeroDeTiposDeCelulas = Integer.parseInt( leitor.readLine() ); // L�-se o n�mero de tipos c�lulas na primeira linha do arquivo de texto.
			
			celulas = new Celula[numeroDeTiposDeCelulas];
	    	
	    	// Nota: a c�lula de �ndice 0 � denominada 'c�lula vazia' e � reservada para os pontos do mapa nos quais n�o se queira desenhar nada na tela.
	    	celulas[0] = new Celula();
	    	celulas[0].colisao = false;
			
			while( linha < numeroDeTiposDeCelulas ) {
				/* L� a linha atual do arquivo de informa��es dos tipos de c�lula, divide
				 * essa linha a cada caractere de espa�o e pega par�metro por par�metro.
				 */
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia a c�lula atual e carrega os par�metros lidos nos respectivos atributos dessa c�lula.
				celulas[linha] = new Celula();
				celulas[linha].imagem = ImageIO.read( getClass().getResourceAsStream( linhaDeLeitura[0] ) );
				celulas[linha].colisao = Boolean.parseBoolean( linhaDeLeitura[1] );
			    
			    linha++;
			} 
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
	}
    
    
    // M�todo que carrega a matriz do mapa de c�lulas segundo a disposi��o das c�lulas nele.
	public void carregaMapaDeCelulas( String endereco ) {
		  
		// Busca as informa��es das posi��es de cada c�lula num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );		
			
	    	int linha = 0;
	    	int coluna = 0;
			
			String dimensoesDoMapaDeCelulas[] = (leitor.readLine()).split( " " ); // L�-se o n�mero de tipos de �udio na primeira linha do arquivo de texto.

			int mapaDeCelulas[][] = new int[Integer.parseInt( dimensoesDoMapaDeCelulas[0] )][Integer.parseInt( dimensoesDoMapaDeCelulas[1] )];
			
	    	// L� o arquivo do mapa de c�lulas linha por linha a cada ciclo do la�o.
			while( linha < mapaDeCelulas.length ) {
				// Divide a linha atual a cada espa�o e pega n�mero por n�mero em cada linha.
		    	String linhaDeLeitura[] = (leitor.readLine()).split(" "); 
		    	
				while( coluna < mapaDeCelulas[0].length ) {
			    	// Salva o codigo do tipo de celula numa matriz na sua respectiva posi��o em rela��o ao mapa.
				    mapaDeCelulas[linha][coluna] = Integer.parseInt( linhaDeLeitura[coluna] );
				    
					coluna++;
				}
				
				coluna = 0;
				linha++;
			}
			
			listaDeMapas.add( mapaDeCelulas );
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	// M�todo que desenha as imagens do mapa de c�lulas na tela segundo a sua matriz de c�lulas.
	public void desenhaMapaDeCelulas( int mapaDeCelulas[][], Graphics2D graphics2D ) {
		
        int colunaDoMapa = 0;
		int linhaDoMapa = 0;

		while( linhaDoMapa < mapaDeCelulas.length && colunaDoMapa < mapaDeCelulas[0].length ) {
			// Seleciona o tipo de c�lula segundo o c�digo da posi��o atual no mapa de c�lulas.                                                                  
			int tipoDeCelula = mapaDeCelulas[linhaDoMapa][colunaDoMapa];
			
			// Anima��o das c�lulas de �gua da raia e do rio pinheiros.
			if( tipoDeCelula == 1 ) {
				if( linhaDoMapa % 2 == 1 ) {
					if( (0 + contadorDeCicloDaAgua / 2) % 9 != 0 ) {
						tipoDeCelula = (0 + contadorDeCicloDaAgua / 2) % 9;
					}
				} else {
					if( (3 + contadorDeCicloDaAgua / 2) % 9 != 0 ) {
						tipoDeCelula = (3 + contadorDeCicloDaAgua / 2) % 9;
					}
				}
		    } else if( tipoDeCelula == 10 ) {
		    	if( linhaDoMapa % 2 == 0 ) {
					if( (0 + contadorDeCicloDaAgua / 2) % 9 != 0 ) {
						tipoDeCelula = ((0 + contadorDeCicloDaAgua / 2) % 9) + 9;
					}
				} else  {
					if( (3 + contadorDeCicloDaAgua / 2) % 9 != 0 ) {
						tipoDeCelula = ((3 + contadorDeCicloDaAgua / 2) % 9) + 9;
					}
				}
		    }
			
			// Anima��o das c�lulas do vidro da USP futurista.
			if( painelDeJogo.seletorDeEstilo == 2 ) {
				if( tipoDeCelula >= 46 && tipoDeCelula <= 50 ) {
					tipoDeCelula = 46;
					if( contadorDeCicloDoVidro % 6 != 0 ) {
						tipoDeCelula = 47 + (contadorDeCicloDoVidro % 4);
					}
				}
			}
			
			int mapaX = colunaDoMapa * painelDeJogo.tamanhoDaCelula;
			int mapaY = linhaDoMapa * painelDeJogo.tamanhoDaCelula;
			int telaX = mapaX - painelDeJogo.capivara.mapaX + painelDeJogo.capivara.telaX;
			int telaY = mapaY - painelDeJogo.capivara.mapaY + painelDeJogo.capivara.telaY;

			// Limita o movimento da c�mera para o interior das bordas do mapa.
			if( painelDeJogo.capivara.telaX > painelDeJogo.capivara.mapaX ) {
				telaX = mapaX;
			}
            if( painelDeJogo.capivara.telaY > painelDeJogo.capivara.mapaY ) {
            	telaY = mapaY;
			}
            if( painelDeJogo.larguraDaTela - painelDeJogo.capivara.telaX > painelDeJogo.larguraDoMapa - painelDeJogo.capivara.mapaX ) {
            	telaX = painelDeJogo.larguraDaTela - (painelDeJogo.larguraDoMapa - mapaX);
            }
            if( painelDeJogo.alturaDaTela - painelDeJogo.capivara.telaY > painelDeJogo.alturaDoMapa - painelDeJogo.capivara.mapaY ) {
            	telaY = painelDeJogo.alturaDaTela - (painelDeJogo.alturaDoMapa - mapaY);
            }
            
            // Desenha na tela apenas as c�lulas do mapa vis�veis para o jogador, a fim de poupar processamento.
            if( tipoDeCelula != 0 ) {
				if( mapaX + painelDeJogo.tamanhoDaCelula > painelDeJogo.capivara.mapaX - painelDeJogo.capivara.telaX && 
					mapaX - painelDeJogo.tamanhoDaCelula < painelDeJogo.capivara.mapaX + painelDeJogo.capivara.telaX &&
					mapaY + painelDeJogo.tamanhoDaCelula > painelDeJogo.capivara.mapaY - painelDeJogo.capivara.telaY &&
					mapaY - painelDeJogo.tamanhoDaCelula < painelDeJogo.capivara.mapaY + painelDeJogo.capivara.telaY) {
					graphics2D.drawImage( celulas[tipoDeCelula].imagem, telaX, telaY, painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula, null );
				} else if( painelDeJogo.capivara.telaX > painelDeJogo.capivara.mapaX || painelDeJogo.capivara.telaY > painelDeJogo.capivara.mapaY || 
						   painelDeJogo.larguraDaTela - painelDeJogo.capivara.telaX > painelDeJogo.larguraDoMapa - painelDeJogo.capivara.mapaX ||
						   painelDeJogo.alturaDaTela - painelDeJogo.capivara.telaY > painelDeJogo.alturaDoMapa - painelDeJogo.capivara.mapaY ) {
					graphics2D.drawImage( celulas[tipoDeCelula].imagem, telaX, telaY, painelDeJogo.tamanhoDaCelula, painelDeJogo.tamanhoDaCelula, null );
				}
            }
			
			colunaDoMapa++;
			
			if( colunaDoMapa == mapaDeCelulas[0].length) {
				colunaDoMapa = 0;
				linhaDoMapa ++;
			}
		}
	}
}