package entidade;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sistemasFundamentais.PainelDeJogo;

public abstract class Entidade {

	protected PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	
	public int mapaX, mapaY; // Posi��o da entidade no mapa de jogo.
	public int largura;      // Dimens�o de largura da entidade.
	public int altura;       // Dimens�o de comprimento da entidade.
	public int velocidade;   // Quantidade de pixels em cada movimento da entidade.
	public String direcao;   // Dire��o do movimento da entidade.
	
	public int imagemX, imagemY; // Posi��o da imagem da entidade na tela.
	public BufferedImage imagem; // Imagem da entidade a ser desenhada na tela.
	public String enderecoDeImagens; // Endereco a partir do qual ser�o carregadas as imagens da entidade.
	
	public int contadorDeImagem = 1; // N�mero da imagem a ser desenhada na anima��o da entidade.
	public int contadorDeCicloDaEntidade = 0; // N�mero de atualiza��es feitas na entidade desde seu momento de reset.
	
	// M�todo que carrega as imagens da entidade na sua inst�ncia protot�pica.
	public abstract void carregaImagensDaEntidade();
	
	// M�todo que cria uma nova inst�ncia da entidade a partir da sua inst�ncia protot�pica.
	public abstract Entidade criaEntidade( int mapaX, int mapaY, int velocidade, String direcao );
	
	// M�todo que atualiza as informa��es da entidade a cada ciclo do la�o principal do jogo.
	public abstract void atualizaEntidade();
	
	// M�todo que desenha a imagem da entidade na tela a cada ciclo do la�o principal do jogo.
	public void desenhaEntidade( Graphics2D graphics2D ) {
		
		graphics2D.drawImage( imagem, imagemX, imagemY, largura*painelDeJogo.tamanhoDaCelula, altura*painelDeJogo.tamanhoDaCelula, null );	
	}
}
