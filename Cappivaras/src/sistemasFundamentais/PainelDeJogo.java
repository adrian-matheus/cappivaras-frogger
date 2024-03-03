package sistemasFundamentais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.Capivara;
import entidade.GerenciadorDeEntidades;
import entidade.NivelDeDificuldade;
import entidade.NivelDeDificuldadeMedio;
import estado.Estado;
import estado.EstadoDoMenuPrincipal;
import estilo.Estilo;
import estilo.FabricaDeEstilos;

@SuppressWarnings("serial")
public class PainelDeJogo extends JPanel implements Runnable {
	
	// Configura��es de jogo.
	public int seletorDeDificuldade = 1; // Dificuldade do jogo.
	public int seletorDeLingua = 0; // Lingua dos tesxtos do jogo.
	public int seletorDeEstilo = 1; // Estilo audiovisual do jogo.
	public int seletorDeVolumeDasMusicas = 7; // Volume das m�sicas do jogo.
	public int seletorDeVolumeDosEfeitosSonoros = 7; // Volume dos efeitos sonoros do jogo.
	
	// Configura��es e ajustes das c�lulas.
	public final int tamanhoBaseDaCelula = 16; // 16x16 pixels.
	public final int escala = 3; // Escala de resolu��o do jogo.
	public final int tamanhoDaCelula = tamanhoBaseDaCelula * escala;
	
	// Configura��es e ajustes da tela.
	public final int numeroDeColunasNaTela = 16;
	public final int numeroDeLinhasNaTela = 12; 
	public final int larguraDaTela = tamanhoDaCelula * numeroDeColunasNaTela;
	public final int alturaDaTela = tamanhoDaCelula * numeroDeLinhasNaTela; 
	
	// Configura��es e ajustes do mapa.
	public final int numeroDeColunasDoMapa = 37;
	public final int numeroDeLinhasDoMapa = 85;
	public final int larguraDoMapa = tamanhoDaCelula * numeroDeColunasDoMapa;
	public final int alturaDoMapa = tamanhoDaCelula * numeroDeLinhasDoMapa;
	
	// Configura��es de sincroniza��o.
	public final int FPS = 60; // N�mero de atualiza��es de tela por segundo.
	public int contadorDeCiclo = 0; // Contador que sincroniza as anima��es na tela com o la�o principal do jogo.

	
	// Instancia��o de objetos fundamentais.
	public Thread threadDoJogo;
	public Estado estadoDeJogo;
	public FabricaDeEstilos       fabricaDeEstilos       = new FabricaDeEstilos();
	public Estilo                 estilo                 = fabricaDeEstilos.getEstilo( this );
	public InterfaceGrafica       interfaceGrafica       = estilo.criaInterfaceGrafica();
	public GerenciadorDeCelulas   gerenciadorDeCelulas   = estilo.criaGerenciadorDeCelulas();
	public GerenciadorDeEntidades gerenciadorDeEntidades = estilo.criaGerenciadorDeEntidades();
	public GerenciadorDeAudio     gerenciadorDeAudio     = estilo.criaGerenciadorDeAudio();
	public VerificadorDeColisao   verificadorDeColisao   = new VerificadorDeColisao( this );
	public ControladorDoTeclado   controladorDoTeclado   = new ControladorDoTeclado( this );
	public NivelDeDificuldade     nivelDeDificuldade     = new NivelDeDificuldadeMedio( this );
	public Capivara               capivara               = Capivara.getInstancia( this );
	
	
    public PainelDeJogo() {
		
		this.setBackground( Color.green ); // Determina a cor preta para o fundo do painel de jogo.
		this.setPreferredSize( new Dimension( larguraDaTela, alturaDaTela ) ); // Ajusta o tamanho da tela inicializada.
		this.setDoubleBuffered( true ); // Melhora a efici�ncia no processo de desenhar imagens na tela.
		this.setFocusable( true ); // Coloca a tela inicializada em primeiro plano.
		this.addKeyListener( this.controladorDoTeclado ); // Prepara o painel de jogo para receber comandos do teclado.
		
		mudaEstado( new EstadoDoMenuPrincipal( this ) ); // Inicializa o painel de jogo no seu estado inicial.
	}
    
	
	
	// M�todo que muda o estado atual do jogo.
	public void mudaEstado( Estado estado ) {
		
		this.estadoDeJogo = estado;
	}
	
	
	// M�todo que d� in�cio � thread do jogo, o que incia o la�o principal do jogo.
	public void iniciaThreadDoJogo() {
		
		threadDoJogo = new Thread(this);
		threadDoJogo.start();
	}
	
	
	// M�todo que configura o la�o principal do jogo.
	public void run() {
		
		// A unidade de tempo � nanosegundos, assim 10^9ns = 1s. Se temos FPS atualiza��es por segundo, o tempo de atualiza��o � 1/FPS.
		double intervaloDeAtualizacao = 1000000000/FPS;
		double delta = 0;
		long momentoDoUltimoCiclo = System.nanoTime();
		long momentoAtual;
		
		while( threadDoJogo != null ) {
			momentoAtual = System.nanoTime();		
			delta += ( momentoAtual - momentoDoUltimoCiclo ) / intervaloDeAtualizacao;
			momentoDoUltimoCiclo = momentoAtual;
			
			if( delta >= 1 ) {
				atualizaJogo();
				repaint(); // Por defini��o, chama o m�todo paintComponent desta classe.
				
				delta--;
			}
		}
	}
	
	
	// M�todo que atualiza as informa��es dos objetos a cada ciclo do la�o principal do jogo.
	public void atualizaJogo() {
		
		/* Atualiza��o do contador de ciclo geral. O contador � resetado a partir de um dado limite para evitar
		 * overflow, e usa-se 10! = 3628800 como limite por ser m�ltiplo de v�rios n�meros convenientes, o que 
		 * permite diversas sincroniza��es em cad�ncias diferentes baseadas no mesmo contador de ciclo geral.
		 */
		contadorDeCiclo++;
		if( contadorDeCiclo == 3628800 ) {
			contadorDeCiclo = 0;
		}
		
		estadoDeJogo.atualizaJogo();
	}
	
	// M�todo que atualiza o desenho de imagens na tela com base nas informa��es dos objetos a cada ciclo do la�o principal do jogo.
	public void paintComponent( Graphics graphics ) {
		
		super.paintComponent( graphics );
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		estadoDeJogo.desenhaTela( graphics2D );
		
		graphics2D.dispose(); 
	}
}


















