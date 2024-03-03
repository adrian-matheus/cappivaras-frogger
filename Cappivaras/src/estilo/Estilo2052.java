package estilo;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.GerenciadorDeEntidades;
import sistemasFundamentais.InterfaceGrafica;
import sistemasFundamentais.PainelDeJogo;

public class Estilo2052 extends Estilo {

    private static Estilo2052 instancia = null;
	
	
    private Estilo2052( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
	}
	
	
	// Método que permite o acesso à instância única deste estilo audiovisual.
	public static Estilo2052 getInstancia( PainelDeJogo painelDeJogo ) {
		
		if( instancia == null )
			instancia = new Estilo2052( painelDeJogo );
		return instancia;
	}
	
	
    public InterfaceGrafica criaInterfaceGrafica() {
		
    	InterfaceGrafica interfaceGrafica = new InterfaceGrafica( painelDeJogo, "/estilo2052/fonteGrafica_2052.ttf", "/estilo2052/listaDeImagens_2052.txt");
		return interfaceGrafica;
	}

    
	public GerenciadorDeCelulas criaGerenciadorDeCelulas() {
		
		GerenciadorDeCelulas gerenciadorDeCelulas = new GerenciadorDeCelulas( painelDeJogo, "/estilo2052/listaDeCelulas_2052.txt" );
		return gerenciadorDeCelulas;
	}

    
	public GerenciadorDeEntidades criaGerenciadorDeEntidades() {
		
		GerenciadorDeEntidades gerenciadorDeEntidades = new GerenciadorDeEntidades( painelDeJogo, "/estilo2052/listaDeEntidades_2052.txt" );
		return gerenciadorDeEntidades;
	}
	
	
	public GerenciadorDeAudio criaGerenciadorDeAudio() {
		
		GerenciadorDeAudio criaGerenciadorDeAudio = new GerenciadorDeAudio( painelDeJogo, "/estilo2052/listaDeAudios_2052.txt" );
		return criaGerenciadorDeAudio;
	}
}
