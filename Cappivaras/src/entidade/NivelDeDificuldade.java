package entidade;

import sistemasFundamentais.PainelDeJogo;

public abstract class NivelDeDificuldade {

	protected PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	public static String mapaDeEntidades; // Endereço para o mapa de entidades, o qual determina a disposição das entidades no mapa de jogo.
	
	
	// Métodos que determinam o comportamento das entidades em função do nível de difiuldade do jogo.
    public abstract void atualizaCanoa( Entidade entidade );
    public abstract void atualizaCarro( Entidade entidade );
    public abstract void atualizaCaminhao( Entidade entidade );
    public abstract void atualizaBarco( Entidade entidade );
    public abstract void atualizaCiclista( Entidade entidade );
}
