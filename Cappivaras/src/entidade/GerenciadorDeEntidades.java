package entidade;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeEntidades {
	
	private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	
	private final int numeroDeTiposDeEntidades = 6; // Número total de tipos de entidades diferentes.
	
	public Entidade[] entidades; // Conjunto das entidades instanciadas.
	public Entidade[] prototiposDasEntidades; // Conjunto dos protótipos dos diferentes tipos de entidades.
	
 	
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
	
	
	// Método que carrega as imagens dos diferentes tipos de entidades nos seus respectivos protótipos.
    public void carregaImagensDasEntidades( String endereco ) {
		
    	// Busca os endereços das imagens de cada tipo de entidade num arquivo de texto.
		try {
			InputStream stringDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( stringDeEntrada ) );
			
			int numeroDeEntidadesCarregadas;
			
			// Lê o arquivo de endereços das imagens dos tipos de entidade linha por linha a cada ciclo do laço.
			for( numeroDeEntidadesCarregadas = 0; numeroDeEntidadesCarregadas < numeroDeTiposDeEntidades; numeroDeEntidadesCarregadas++ ) {
				
				// Altera o endereço de imagens do protótipo da entidade atual de acordo com o endereço lido.
				prototiposDasEntidades[numeroDeEntidadesCarregadas].enderecoDeImagens = leitor.readLine();
				
				// Carrega as imagens do protótipo da entidade atual segundo seu endereço de imagens carregado anteriormente.
				prototiposDasEntidades[numeroDeEntidadesCarregadas].carregaImagensDaEntidade();
			}
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
	}
  
 	
    // Método que instancia as entidades no mapa de jogo.
    public void instanciaEntidadesNoMapa( String endereco ) {
    	
    	// Busca as informações de cada entidade num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );		
			
	    	int linha = 0;
	    	int numeroDeEntidadesNoMapa = Integer.parseInt( leitor.readLine() ); // Lê-se o número de entidades no mapa na primeira linha do arquivo de texto.
	    	
			entidades = new Entidade[numeroDeEntidadesNoMapa];
			
	    	// Lê o arquivo do mapa de entidades linha por linha a cada ciclo do laço.
			while( linha < numeroDeEntidadesNoMapa ) {
				// Divide a linha atual a cada espaço e pega parâmetro por parâmetro em cada linha.
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia uma nova entidade no mapa a partir do seu respectivo protótipo segundo as informações lidas no arquivo de texto.
				entidades[linha] = prototiposDasEntidades[Integer.parseInt( linhaDeLeitura[0] )].criaEntidade( Integer.parseInt( linhaDeLeitura[1] ), 
						Integer.parseInt( linhaDeLeitura[2] ), Integer.parseInt( linhaDeLeitura[3] ), linhaDeLeitura[4] );
				
				linha++;
			} 
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }
    }
}
