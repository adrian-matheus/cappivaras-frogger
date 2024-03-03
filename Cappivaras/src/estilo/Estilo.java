package estilo;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.GerenciadorDeEntidades;
import sistemasFundamentais.InterfaceGrafica;
import sistemasFundamentais.PainelDeJogo;

public abstract class Estilo {

	protected PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	
	
	// M�todo que cria a fonte gr�fica do jogo em fun��o do seu estilo atual.
	public abstract InterfaceGrafica criaInterfaceGrafica(); 
	
	// M�todo que cria o gerenciador de c�lulas do jogo em fun��o do seu estilo atual.
	public abstract GerenciadorDeCelulas criaGerenciadorDeCelulas(); 
	
	// M�todo que cria o gerenciador de entidades do jogo em fun��o do seu estilo atual.
	public abstract GerenciadorDeEntidades criaGerenciadorDeEntidades();
	
	// M�todo que cria o gerenciador de c�lulas do jogo em fun��o do seu estilo atual.
	public abstract GerenciadorDeAudio criaGerenciadorDeAudio();
}
