package celula;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeCelulas {
	
	private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	
	public Celula[] celulas; // Conjunto dos diferentes tipos de células instanciadas.
	
	public ArrayList< int[][] > listaDeMapas = new ArrayList<>(); // Lista de mapas do jogo.
	
    public int contadorDeCicloDaAgua = 0; // Contador para sincronização da animação da água da raia e do rio pinheiros.
    public int contadorDeCicloDoVidro = 0; // Contador para sincronização da animação do vidro da USP futurista.
    	
 	
	public GerenciadorDeCelulas( PainelDeJogo painelDeJogo, String endereco ) {
		
		this.painelDeJogo = painelDeJogo;
		
		instanciaCelulas( endereco );
		
		// Os mapas são matrizes de inteiros nas quais cada entrada representa o código para um tipo de célula.
		carregaMapaDeCelulas( "/mapaDeCelulasInferior.txt" ); // Mapa das células a serem desenhadas na camada inferior da tela.
		carregaMapaDeCelulas( "/mapaDeCelulasSuperior.txt" ); // Mapa das célulass a serem desenhadas na camada superior da tela.
		carregaMapaDeCelulas( "/mapaDeCelulasDoMenuPrincipal.txt" ); // Mapa das células a serem desenhadas na tela do menu inicial.
		carregaMapaDeCelulas( "/mapaDeCelulasDoMenuDeConfiguracoes.txt" );  // Mapa das células a serem desenhadas na tela do menu de configurações.
		carregaMapaDeCelulas( "/mapaDeCelulasDaTelaDeFimDeJogo.txt" ); // Mapa das células a serem desenhadas na tela de fim de jogo.
	}
	
	
	// Método que instancia os diferentes tipos de células.
    public void instanciaCelulas( String endereco ) {

    	// Busca as informações de cada tipo de célula num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );
			
			int linha = 1; // A célula de índice 0é instanciada à parte, então partimos do índice 1.
			int numeroDeTiposDeCelulas = Integer.parseInt( leitor.readLine() ); // Lê-se o número de tipos células na primeira linha do arquivo de texto.
			
			celulas = new Celula[numeroDeTiposDeCelulas];
	    	
	    	// Nota: a célula de índice 0 é denominada 'célula vazia' e é reservada para os pontos do mapa nos quais não se queira desenhar nada na tela.
	    	celulas[0] = new Celula();
	    	celulas[0].colisao = false;
			
			while( linha < numeroDeTiposDeCelulas ) {
				/* Lê a linha atual do arquivo de informações dos tipos de célula, divide
				 * essa linha a cada caractere de espaço e pega parâmetro por parâmetro.
				 */
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia a célula atual e carrega os parâmetros lidos nos respectivos atributos dessa célula.
				celulas[linha] = new Celula();
				celulas[linha].imagem = ImageIO.read( getClass().getResourceAsStream( linhaDeLeitura[0] ) );
				celulas[linha].colisao = Boolean.parseBoolean( linhaDeLeitura[1] );
			    
			    linha++;
			} 
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
	}
    
    
    // Método que carrega a matriz do mapa de células segundo a disposição das células nele.
	public void carregaMapaDeCelulas( String endereco ) {
		  
		// Busca as informações das posições de cada célula num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );		
			
	    	int linha = 0;
	    	int coluna = 0;
			
			String dimensoesDoMapaDeCelulas[] = (leitor.readLine()).split( " " ); // Lê-se o número de tipos de áudio na primeira linha do arquivo de texto.

			int mapaDeCelulas[][] = new int[Integer.parseInt( dimensoesDoMapaDeCelulas[0] )][Integer.parseInt( dimensoesDoMapaDeCelulas[1] )];
			
	    	// Lê o arquivo do mapa de células linha por linha a cada ciclo do laço.
			while( linha < mapaDeCelulas.length ) {
				// Divide a linha atual a cada espaço e pega número por número em cada linha.
		    	String linhaDeLeitura[] = (leitor.readLine()).split(" "); 
		    	
				while( coluna < mapaDeCelulas[0].length ) {
			    	// Salva o codigo do tipo de celula numa matriz na sua respectiva posição em relação ao mapa.
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
	
	
	// Método que desenha as imagens do mapa de células na tela segundo a sua matriz de células.
	public void desenhaMapaDeCelulas( int mapaDeCelulas[][], Graphics2D graphics2D ) {
		
        int colunaDoMapa = 0;
		int linhaDoMapa = 0;

		while( linhaDoMapa < mapaDeCelulas.length && colunaDoMapa < mapaDeCelulas[0].length ) {
			// Seleciona o tipo de célula segundo o código da posição atual no mapa de células.                                                                  
			int tipoDeCelula = mapaDeCelulas[linhaDoMapa][colunaDoMapa];
			
			// Animação das células de água da raia e do rio pinheiros.
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
			
			// Animação das células do vidro da USP futurista.
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

			// Limita o movimento da câmera para o interior das bordas do mapa.
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
            
            // Desenha na tela apenas as células do mapa visíveis para o jogador, a fim de poupar processamento.
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