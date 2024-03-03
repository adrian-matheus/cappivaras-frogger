package sistemasFundamentais;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

import entidade.Capivara;

public class ControladorDoTeclado implements KeyListener {
	
	private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	private Capivara capivara; // Referência para a instância única da capivara.
	
	private InputMap mapaDeTeclas; // Mapa das teclas reconhecíveis pelo jogo.
	private ActionMap mapaDeAcoes; // Mapa das ações associadas a cada tecla.
	
	
    public ControladorDoTeclado( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		this.capivara = Capivara.getInstancia( painelDeJogo );
		
		this.mapaDeTeclas = this.painelDeJogo.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW );
		this.mapaDeAcoes = this.painelDeJogo.getActionMap();
		
		// Lista de teclas reconhecíveis pelo jogo associadas a suas respectivas ações.
		adicionaTecla( KeyEvent.VK_W, "Cima", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaCima() );
		adicionaTecla( KeyEvent.VK_UP, "Cima", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaCima() );
		
		adicionaTecla( KeyEvent.VK_S, "Baixo", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaBaixo() );
		adicionaTecla( KeyEvent.VK_DOWN, "Baixo", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaBaixo() );
		
		adicionaTecla( KeyEvent.VK_D, "Direita", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaDireita() );
		adicionaTecla( KeyEvent.VK_RIGHT, "Direita", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaDireita() );
		
		adicionaTecla( KeyEvent.VK_A, "Esquerda", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaEsquerda() );
		adicionaTecla( KeyEvent.VK_LEFT, "Esquerda", false, (evt) -> painelDeJogo.estadoDeJogo.teclaParaEsquerda() );
		
		adicionaTecla( KeyEvent.VK_ENTER, "confirma", false, (evt) -> painelDeJogo.estadoDeJogo.teclaConfirma() );
		adicionaTecla( KeyEvent.VK_SPACE, "confirma", false, (evt) -> painelDeJogo.estadoDeJogo.teclaConfirma() );
	}
	
    
    // Método que adiciona uma nova tecla ao mapa de teclas e associa a ela uma determinada ação no mapa de ações("key bindings").
    @SuppressWarnings("serial")
	private void adicionaTecla( int codigoDaTecla, String idDaAcao, boolean respostaContinua, ActionListener detectorDeAcao ) {
    	
    	mapaDeTeclas.put( KeyStroke.getKeyStroke( codigoDaTecla, 0, respostaContinua ), idDaAcao );
    	mapaDeAcoes.put( idDaAcao, new AbstractAction() {
    		
			public void actionPerformed( ActionEvent evento ) {
				
				detectorDeAcao.actionPerformed( evento );
			}
    	});
    }
    
    
    // Métodos da interface 'KeyListener' que detectam quando teclas são digitadas, pressionadas e/ou soltas.
	public void keyTyped( KeyEvent e ) { }

	public void keyPressed( KeyEvent e ) { 

		int tecla = e.getKeyCode(); 
		
		if ( tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP ) {
			capivara.teclaParaCima = true;
		}

		if ( tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN ) { 
			capivara.teclaParaBaixo = true;
		}

		if ( tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT ) { 
			capivara.teclaParaEsquerda = true;
		}

		if ( tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT ) { 
			capivara.teclaParaDireita = true;
		}
	}

	public void keyReleased( KeyEvent e ) {

		int tecla = e.getKeyCode();

		if ( tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP ) {
			capivara.teclaParaCima = false;
		}

		if ( tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN ) { 
			capivara.teclaParaBaixo = false;
		}

		if ( tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT ) { 
			capivara.teclaParaEsquerda = false;
		}

		if ( tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT ) { 
			capivara.teclaParaDireita = false;
		}
	}
}

