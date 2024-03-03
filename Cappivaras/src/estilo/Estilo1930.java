package estilo;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.GerenciadorDeEntidades;
import sistemasFundamentais.InterfaceGrafica;
import sistemasFundamentais.PainelDeJogo;

public class Estilo1930 extends Estilo {

    private static Estilo1930 instancia = null;
	
	
    private Estilo1930( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
	}
	
	
	// Método que permite o acesso à instância única deste estilo audiovisual.
	public static Estilo1930 getInstancia( PainelDeJogo painelDeJogo ) {
		
		if( instancia == null )
			instancia = new Estilo1930( painelDeJogo );
		return instancia;
	}
	
	
    public InterfaceGrafica criaInterfaceGrafica() {
		
    	InterfaceGrafica interfaceGrafica = new InterfaceGrafica( painelDeJogo, "/estilo1930/fonteGrafica_1930.ttf", "/estilo1930/listaDeImagens_1930.txt");
		return interfaceGrafica;
	}

    
	public GerenciadorDeCelulas criaGerenciadorDeCelulas() {
		
		GerenciadorDeCelulas gerenciadorDeCelulas = new GerenciadorDeCelulas( painelDeJogo, "/estilo1930/listaDeCelulas_1930.txt" );
		return gerenciadorDeCelulas;
	}

    
	public GerenciadorDeEntidades criaGerenciadorDeEntidades() {
		
		GerenciadorDeEntidades gerenciadorDeEntidades = new GerenciadorDeEntidades( painelDeJogo, "/estilo1930/listaDeEntidades_1930.txt" );
		return gerenciadorDeEntidades;
	}
	
	
	public GerenciadorDeAudio criaGerenciadorDeAudio() {
		
		GerenciadorDeAudio criaGerenciadorDeAudio = new GerenciadorDeAudio( painelDeJogo, "/estilo1930/listaDeAudios_1930.txt" );
		return criaGerenciadorDeAudio;
	}
}
