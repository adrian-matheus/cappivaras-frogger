package entidade;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sistemasFundamentais.PainelDeJogo;

public abstract class Entidade {

	protected PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	
	public int mapaX, mapaY; // Posição da entidade no mapa de jogo.
	public int largura;      // Dimensão de largura da entidade.
	public int altura;       // Dimensão de comprimento da entidade.
	public int velocidade;   // Quantidade de pixels em cada movimento da entidade.
	public String direcao;   // Direção do movimento da entidade.
	
	public int imagemX, imagemY; // Posição da imagem da entidade na tela.
	public BufferedImage imagem; // Imagem da entidade a ser desenhada na tela.
	public String enderecoDeImagens; // Endereco a partir do qual serão carregadas as imagens da entidade.
	
	public int contadorDeImagem = 1; // Número da imagem a ser desenhada na animação da entidade.
	public int contadorDeCicloDaEntidade = 0; // Número de atualizações feitas na entidade desde seu momento de reset.
	
	// Método que carrega as imagens da entidade na sua instância prototípica.
	public abstract void carregaImagensDaEntidade();
	
	// Método que cria uma nova instância da entidade a partir da sua instância prototípica.
	public abstract Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao );
	
	// Método que atualiza as informações da entidade a cada ciclo do laço principal do jogo.
	public abstract void atualizaEntidade();
	
	// Método que desenha a imagem da entidade na tela a cada ciclo do laço principal do jogo.
	public void desenhaEntidade( Graphics2D graphics2D ) {
		
		graphics2D.drawImage( imagem, imagemX, imagemY, largura*painelDeJogo.tamanhoDaCelula, altura*painelDeJogo.tamanhoDaCelula, null );	
	}
}
