package estilo;

import audio.GerenciadorDeAudio;
import celula.GerenciadorDeCelulas;
import entidade.GerenciadorDeEntidades;
import sistemasFundamentais.InterfaceGrafica;
import sistemasFundamentais.PainelDeJogo;

public class Estilo2022 extends Estilo {

    private static Estilo2022 instancia = null;
	
	
    private Estilo2022( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
	}
	
	
	// Método que permite o acesso à instância única deste estilo audiovisual.
	public static Estilo2022 getInstancia( PainelDeJogo painelDeJogo ) {
		
		if( instancia == null )
			instancia = new Estilo2022( painelDeJogo );
		return instancia;
	}
	
	
    public InterfaceGrafica criaInterfaceGrafica() {
		
    	InterfaceGrafica interfaceGrafica = new InterfaceGrafica( painelDeJogo, "/estilo2022/fonteGrafica_2022.ttf", "/estilo2022/listaDeImagens_2022.txt");
		return interfaceGrafica;
	}

    
	public GerenciadorDeCelulas criaGerenciadorDeCelulas() {
		
		GerenciadorDeCelulas gerenciadorDeCelulas = new GerenciadorDeCelulas( painelDeJogo, "/estilo2022/listaDeCelulas_2022.txt" );
		return gerenciadorDeCelulas;
	}

    
	public GerenciadorDeEntidades criaGerenciadorDeEntidades() {
		
		GerenciadorDeEntidades gerenciadorDeEntidades = new GerenciadorDeEntidades( painelDeJogo, "/estilo2022/listaDeEntidades_2022.txt" );
		return gerenciadorDeEntidades;
	}
	
	
	public GerenciadorDeAudio criaGerenciadorDeAudio() {
		
		GerenciadorDeAudio criaGerenciadorDeAudio = new GerenciadorDeAudio( painelDeJogo, "/estilo2022/listaDeAudios_2022.txt" );
		return criaGerenciadorDeAudio;
	}
}
