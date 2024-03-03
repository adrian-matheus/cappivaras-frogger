package estilo;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.GerenciadorDeEntidades;
import sistemasFundamentais.InterfaceGrafica;
import sistemasFundamentais.PainelDeJogo;

public abstract class Estilo {

	protected PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	
	
	// Método que cria a fonte gráfica do jogo em função do seu estilo atual.
	public abstract InterfaceGrafica criaInterfaceGrafica(); 
	
	// Método que cria o gerenciador de células do jogo em função do seu estilo atual.
	public abstract GerenciadorDeCelulas criaGerenciadorDeCelulas(); 
	
	// Método que cria o gerenciador de entidades do jogo em função do seu estilo atual.
	public abstract GerenciadorDeEntidades criaGerenciadorDeEntidades();
	
	// Método que cria o gerenciador de células do jogo em função do seu estilo atual.
	public abstract GerenciadorDeAudio criaGerenciadorDeAudio();
}
