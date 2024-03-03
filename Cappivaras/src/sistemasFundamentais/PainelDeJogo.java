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
	
	// Configurações de jogo.
	public int seletorDeDificuldade = 1; // Dificuldade do jogo.
	public int seletorDeLingua = 0; // Lingua dos tesxtos do jogo.
	public int seletorDeEstilo = 1; // Estilo audiovisual do jogo.
	public int seletorDeVolumeDasMusicas = 7; // Volume das músicas do jogo.
	public int seletorDeVolumeDosEfeitosSonoros = 7; // Volume dos efeitos sonoros do jogo.
	
	// Configurações e ajustes das células.
	public final int tamanhoBaseDaCelula = 16; // 16x16 pixels.
	public final int escala = 3; // Escala de resolução do jogo.
	public final int tamanhoDaCelula = tamanhoBaseDaCelula * escala;
	
	// Configurações e ajustes da tela.
	public final int numeroDeColunasNaTela = 16;
	public final int numeroDeLinhasNaTela = 12; 
	public final int larguraDaTela = tamanhoDaCelula * numeroDeColunasNaTela;
	public final int alturaDaTela = tamanhoDaCelula * numeroDeLinhasNaTela; 
	
	// Configurações e ajustes do mapa.
	public final int numeroDeColunasDoMapa = 37;
	public final int numeroDeLinhasDoMapa = 85;
	public final int larguraDoMapa = tamanhoDaCelula * numeroDeColunasDoMapa;
	public final int alturaDoMapa = tamanhoDaCelula * numeroDeLinhasDoMapa;
	
	// Configurações de sincronização.
	public final int FPS = 60; // Número de atualizações de tela por segundo.
	public int contadorDeCiclo = 0; // Contador que sincroniza as animações na tela com o laço principal do jogo.

	
	// Instanciação de objetos fundamentais.
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
		this.setDoubleBuffered( true ); // Melhora a eficiência no processo de desenhar imagens na tela.
		this.setFocusable( true ); // Coloca a tela inicializada em primeiro plano.
		this.addKeyListener( this.controladorDoTeclado ); // Prepara o painel de jogo para receber comandos do teclado.
		
		mudaEstado( new EstadoDoMenuPrincipal( this ) ); // Inicializa o painel de jogo no seu estado inicial.
	}
    
	
	
	// Método que muda o estado atual do jogo.
	public void mudaEstado( Estado estado ) {
		
		this.estadoDeJogo = estado;
	}
	
	
	// Método que dá início à thread do jogo, o que incia o laço principal do jogo.
	public void iniciaThreadDoJogo() {
		
		threadDoJogo = new Thread(this);
		threadDoJogo.start();
	}
	
	
	// Método que configura o laço principal do jogo.
	public void run() {
		
		// A unidade de tempo é nanosegundos, assim 10^9ns = 1s. Se temos FPS atualizações por segundo, o tempo de atualização é 1/FPS.
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
				repaint(); // Por definição, chama o método paintComponent desta classe.
				
				delta--;
			}
		}
	}
	
	
	// Método que atualiza as informações dos objetos a cada ciclo do laço principal do jogo.
	public void atualizaJogo() {
		
		/* Atualização do contador de ciclo geral. O contador é resetado a partir de um dado limite para evitar
		 * overflow, e usa-se 10! = 3628800 como limite por ser múltiplo de vários números convenientes, o que 
		 * permite diversas sincronizações em cadências diferentes baseadas no mesmo contador de ciclo geral.
		 */
		contadorDeCiclo++;
		if( contadorDeCiclo == 3628800 ) {
			contadorDeCiclo = 0;
		}
		
		estadoDeJogo.atualizaJogo();
	}
	
	// Método que atualiza o desenho de imagens na tela com base nas informações dos objetos a cada ciclo do laço principal do jogo.
	public void paintComponent( Graphics graphics ) {
		
		super.paintComponent( graphics );
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		estadoDeJogo.desenhaTela( graphics2D );
		
		graphics2D.dispose(); 
	}
}


















