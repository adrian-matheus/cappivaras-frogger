package estado;

import java.awt.Graphics2D;

import entidade.Capivara;
import sistemasFundamentais.PainelDeJogo;

public abstract class Estado {

	protected PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	protected Capivara capivara; // Referência para a instância única da capivara.
	
	
	// Métodos de atualização do laço principal do jogo.
	public abstract void atualizaJogo();
	public abstract void desenhaTela( Graphics2D graphics2D );
	
	
	// Métodos que executam comandos realizados pelo jogador em função das teclas pressionadas.
	public abstract void teclaConfirma();
	public abstract void teclaParaCima();
	public abstract void teclaParaBaixo();
	public abstract void teclaParaDireita();
	public abstract void teclaParaEsquerda();
}
