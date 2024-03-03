package estado;

import java.awt.Graphics2D;

import entidade.Capivara;
import sistemasFundamentais.PainelDeJogo;

public abstract class Estado {

	protected PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	protected Capivara capivara; // Refer�ncia para a inst�ncia �nica da capivara.
	
	
	// M�todos de atualiza��o do la�o principal do jogo.
	public abstract void atualizaJogo();
	public abstract void desenhaTela( Graphics2D graphics2D );
	
	
	// M�todos que executam comandos realizados pelo jogador em fun��o das teclas pressionadas.
	public abstract void teclaConfirma();
	public abstract void teclaParaCima();
	public abstract void teclaParaBaixo();
	public abstract void teclaParaDireita();
	public abstract void teclaParaEsquerda();
}
