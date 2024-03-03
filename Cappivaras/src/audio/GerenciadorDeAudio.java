package audio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import sistemasFundamentais.PainelDeJogo;

public class GerenciadorDeAudio {
	
	private PainelDeJogo painelDeJogo; // Referência para o painel de jogo.
	
	public Audio[] audios; // Conjunto dos diferentes tipos de áudios instanciados.
 	
	public GerenciadorDeAudio( PainelDeJogo painelDeJogo, String endereco ) {
		
		this.painelDeJogo = painelDeJogo;
		
		instanciaAudios( endereco );
	}
	
	
	// Método que instancia os diferentes tipos de áudios.
    public void instanciaAudios( String endereco ) {

    	// Busca os endereços dos clipes de cada tipo de áudio num arquivo de texto.
		try {
			InputStream fluxoDeEntrada = getClass().getResourceAsStream( endereco );
			BufferedReader leitor = new BufferedReader( new InputStreamReader( fluxoDeEntrada ) );
			
			int linha = 0; 
			int numeroDeTiposDeAudio = Integer.parseInt( leitor.readLine() ); // Lê-se o número de tipos de áudio na primeira linha do arquivo de texto.

			audios = new Audio[numeroDeTiposDeAudio];
			
			// Lê o arquivo de endereços dos clipes de áudio linha por linha a cada ciclo do laço.
			while( linha < numeroDeTiposDeAudio ) {
				// Lê a linha atual do arquivo de endereços dos clipes de áudio.
				String linhaDeLeitura[] = (leitor.readLine()).split( " " );
				
				// Instancia o áudio atual de acordo com o arquivo do clipe de áudio carregado do endereço lido.
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