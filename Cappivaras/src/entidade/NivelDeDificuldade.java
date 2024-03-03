package entidade;

import sistemasFundamentais.PainelDeJogo;

public abstract class NivelDeDificuldade {

	protected PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	public static String mapaDeEntidades; // Endere�o para o mapa de entidades, o qual determina a disposi��o das entidades no mapa de jogo.
	
	
	// M�todos que determinam o comportamento das entidades em fun��o do n�vel de difiuldade do jogo.
    public abstract void atualizaCanoa( Entidade entidade );
    public abstract void atualizaCarro( Entidade entidade );
    public abstract void atualizaCaminhao( Entidade entidade );
    public abstract void atualizaBarco( Entidade entidade );
    public abstract void atualizaCiclista( Entidade entidade );
}
