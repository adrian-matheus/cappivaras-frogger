package sistemasFundamentais;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class InterfaceGrafica {
	
    private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
    
    public Font fonteGrafica; // Fonte gráfica do jogo.
    public BufferedImage[] imagens; // Conjunto de imagens avulsas do jogo.

	
	public InterfaceGrafica( PainelDeJogo painelDeJogo, String endereco1, String endereco2 ) {

		this.painelDeJogo = painelDeJogo;
		
		try {
			this.fonteGrafica = Font.createFont( Font.TRUETYPE_FONT, getClass().getResourceAsStream( endereco1 ) );
		} catch( Exception e ) { e.printStackTrace(); }
		
		carregaImagens( endereco2 );
	}
	
	
	// Método que carrega as imagens avulsas do jogo.
    public void carregaImagens( String endereco ) {
    	
    	// Busca os endereços de cada imagem avulsa num arquivo de texto.
		try {
			InputStream stringDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( stringDeEntrada ) );
			
			int linha = 0;
			int numeroDeImagens = Integer.parseInt( leitor.readLine() ); // Lê-se o número de imagens avulsas na primeira linha do arquivo de texto.
			
			imagens = new BufferedImage[numeroDeImagens];
			
			// Lê o arquivo de endereços das imagens avulsas linha por linha a cada ciclo do laço.
			while( linha < numeroDeImagens ) {
				String linhaDeLeitura = leitor.readLine(); 
				
				// Adiciona a imagem armazenada no endereço atualmente lido ao conjunto de imagens avulsas do jogo.
				imagens[linha] = ImageIO.read( getClass().getResourceAsStream( linhaDeLeitura ));
			    
			    linha++;
			} 
			
			leitor.close();
		} catch( Exception e ) { e.printStackTrace(); }
	}
	
	
    // Método que desenha uma imagem na tela.
    public void desenhaImagem( Graphics2D graphics2D, int imagem, int posX, int posY, int tamX, int tamY ) {
		
    	graphics2D.drawImage( imagens[imagem] , posX, posY, tamX, tamY, null);
	}
    
    
    // Método que devolve a coordenada X de um texto tal que ele fique centralizado horizontalmente na tela.
	public int centralizaTexto( Graphics2D graphics2D, String texto ) {
		
		int comprimento = (int)graphics2D.getFontMetrics( fonteGrafica ).getStringBounds( texto, graphics2D ).getWidth();
		return (painelDeJogo.larguraDaTela/2) - (comprimento/2);
	}
	
	
	// Método que escreve um texto na tela.
	public void escreveTexto( Graphics2D graphics2D, String texto, int estilo, int tamanho, Color cor, int posX, int posY ) {
		
		switch( estilo ) {
			case 0:
				graphics2D.setFont( fonteGrafica.deriveFont( Font.PLAIN, tamanho ) );
				break;

			case 1:
				graphics2D.setFont( fonteGrafica.deriveFont( Font.ITALIC, tamanho ) );
				break;

			case 2:
				graphics2D.setFont( fonteGrafica.deriveFont( Font.BOLD, tamanho ) );
				break;
		}
		
		graphics2D.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
    	graphics2D.setColor( cor );
    	graphics2D.drawString( texto, posX, posY );
	}
	
	
	// Método que escreve um texto na tela adicionando-lhe um efeito de sombra.
    public void escreveTextoComSombra( Graphics2D graphics2D, String texto, int estilo, int tamanhoTexto, int tamanhoSombra, 
    		Color corTexto, Color corSombra, int posX, int posY ) {
		
    	escreveTexto( graphics2D, texto, estilo, tamanhoTexto, corSombra, posX+tamanhoSombra, posY+tamanhoSombra );
    	escreveTexto( graphics2D, texto, estilo, tamanhoTexto, corTexto, posX, posY );
	}
}
