package entidade;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeEntidades {
	
	private PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	
	private final int numeroDeTiposDeEntidades = 6; // N�mero total de tipos de entidades diferentes.
	
	public Entidade[] entidades; // Conjunto das entidades instanciadas.
	public Entidade[] prototiposDasEntidades; // Conjunto dos prot�tipos dos diferentes tipos de entidades.
	
 	
	public GerenciadorDeEntidades( PainelDeJogo painelDeJogo, String endereco ) {
		
		this.painelDeJogo = painelDeJogo;
				
		prototiposDasEntidades = new Entidade[numeroDeTiposDeEntidades];
		prototiposDasEntidades[0] = Capivara.getInstancia( painelDeJogo );
		prototiposDasEntidades[1] = new Canoa( this.painelDeJogo );
		prototiposDasEntidades[2] = new Carro( this.painelDeJogo );
		prototiposDasEntidades[3] = new Caminhao( this.painelDeJogo );
		prototiposDasEntidades[4] = new Barco( this.painelDeJogo );
		prototiposDasEntidades[5] = new Ciclista( this.painelDeJogo );
		
		carregaImagensDasEntidades( endereco );
	}
	
	
	// M�todo que carrega as imagens dos diferentes tipos de entidades nos seus respectivos prot�tipos.
    public void carregaImagensDasEntidades( String endereco ) {
		
    	// Busca os endere�os das imagens de cada tipo de entidade num arquivo de texto.
		try {
			InputStream stringDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( stringDeEntrada ) );
			
			int numeroDeEntidadesCarregadas;
			
			// L� o arquivo de endere�os das imagens dos tipos de entidade linha por linha a cada ciclo do la�o.
			for( numeroDeEntidadesCarregadas = 0; numeroDeEntidadesCarregadas < numeroDeTiposDeEntidades; numeroDeEntidadesCarregadas++ ) {
				
				// Altera o endere�o de imagens do prot�tipo da entidade atual de acordo com o endere�o lido.
				prototiposDasEntidades[numeroDeEntidadesCarregadas].enderecoDeImagens = leitor.readLine();
				
				// Carrega as imagens do prot�tipo da entidade atual segundo seu endere�o de imagens carregado anteriormente.
				prototiposDasEntidades[numeroDeEntidadesCarregadas].carregaImagensDaEntidade();
			}
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
	}
  
 	
    // M�todo que instancia as entidades no mapa de jogo.
    public void instanciaEntidadesNoMapa( String endereco ) {
    	
    	// Busca as informa��es de cada entidade num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );		
			
	    	int linha = 0;
	    	int numeroDeEntidadesNoMapa = Integer.parseInt( leitor.readLine() ); // L�-se o n�mero de entidades no mapa na primeira linha do arquivo de texto.
	    	
			entidades = new Entidade[numeroDeEntidadesNoMapa];
			
	    	// L� o arquivo do mapa de entidades linha por linha a cada ciclo do la�o.
			while( linha < numeroDeEntidadesNoMapa ) {
				// Divide a linha atual a cada espa�o e pega par�metro por par�metro em cada linha.
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia uma nova entidade no mapa a partir do seu respectivo prot�tipo segundo as informa��es lidas no arquivo de texto.
				entidades[linha] = prototiposDasEntidades[Integer.parseInt( linhaDeLeitura[0] )].criaEntidade( Integer.parseInt( linhaDeLeitura[1] ), 
						Integer.parseInt( linhaDeLeitura[2] ), Integer.parseInt( linhaDeLeitura[3] ), linhaDeLeitura[4] );
				
				linha++;
			} 
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
    }
}
