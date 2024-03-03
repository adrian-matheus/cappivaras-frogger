package audio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeAudio {
	
	private PainelDeJogo painelDeJogo; // Refer�ncia para o painel de jogo.
	
	public Audio[] audios; // Conjunto dos diferentes tipos de �udios instanciados.
 	
	public GerenciadorDeAudio( PainelDeJogo painelDeJogo, String endereco ) {
		
		this.painelDeJogo = painelDeJogo;
		
		instanciaAudios( endereco );
	}
	
	
	// M�todo que instancia os diferentes tipos de �udios.
    public void instanciaAudios( String endereco ) {

    	// Busca os endere�os dos clipes de cada tipo de �udio num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );
			
			int linha = 0; 
			int numeroDeTiposDeAudio = Integer.parseInt( leitor.readLine() ); // L�-se o n�mero de tipos de �udio na primeira linha do arquivo de texto.

			audios = new Audio[numeroDeTiposDeAudio];
			
			// L� o arquivo de endere�os dos clipes de �udio linha por linha a cada ciclo do la�o.
			while( linha < numeroDeTiposDeAudio ) {
				// L� a linha atual do arquivo de endere�os dos clipes de �udio.
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia o �udio atual de acordo com o arquivo do clipe de �udio carregado do endere�o lido.
				AudioInputStream fluxoDeAudio = AudioSystem.getAudioInputStream( getClass().getResource( linhaDeLeitura[0] ) );
				audios[linha] = new Audio();
				audios[linha].clipeDeAudio = AudioSystem.getClip();
				audios[linha].clipeDeAudio.open( fluxoDeAudio );
				audios[linha].tipoDeAudio = Integer.parseInt( linhaDeLeitura[1] );
				
				if( audios[linha].tipoDeAudio == 0 ) {
					audios[linha].alteraVolume( painelDeJogo.seletorDeVolumeDasMusicas * 0.1f );
				} else {
					audios[linha].alteraVolume( painelDeJogo.seletorDeVolumeDosEfeitosSonoros * 0.1f );
				}
			    
			    linha++;
			} 
			
			leitor.close();	
		} catch( Exception e ) { e.printStackTrace(); }		
    }
}