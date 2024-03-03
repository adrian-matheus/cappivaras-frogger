package audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {

	public Clip clipeDeAudio; // Clipe de áudio a ser tocado.
	public int tipoDeAudio; // Tipo de áudio (Música ou efeito sonoro).
	public boolean audioTocando = false; // Verificador que acusa se o clipe de áudio está tocando ou não.
	
	
	// Método que altera o volume o clipe de áudio.
	public void alteraVolume( float volume ) {
		
		if( clipeDeAudio != null ) {
			
			FloatControl controleDeVolume = (FloatControl)clipeDeAudio.getControl( FloatControl.Type.MASTER_GAIN );
			
			float faixaDeVolume = controleDeVolume.getMaximum() - controleDeVolume.getMinimum();
			float volumeDoAudio = (faixaDeVolume * volume) + controleDeVolume.getMinimum();
			
			controleDeVolume.setValue( volumeDoAudio );
		}
	}
	
		
	// Método que toca o clipe de áudio completo uma única vez.
	public void tocaAudioSimples() {
		
		if( clipeDeAudio != null ) {
			if( clipeDeAudio.getFramePosition() == clipeDeAudio.getFrameLength() ) {
				clipeDeAudio.setFramePosition( 0 );
			}
			clipeDeAudio.start();
			audioTocando = true;
		}
	}
	
	
	// Método que toca o clipe de áudio repetidamente de forma continua.
	public void tocaAudioContinuo() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.start();
			clipeDeAudio.loop( Clip.LOOP_CONTINUOUSLY );
			audioTocando = true;
		}
	}
	
	
	/* Método que para de tocar o clipe de áudio de modo que ele possa ser retomado de onde
	 * parou ao se chamar o método 'tocaAudioSimples' ou 'tocaAudioContinuo' posteriormente.
	 */
	public void pausaAudio() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.stop();
			audioTocando = false;
		}
	}
	
	
	/* Método que para de tocar o clipe de áudio de modo que ele recomece do início ao
	 * se chamar o método 'tocaAudioSimples' ou 'tocaAudioContinuo' posteriormente.
	 */
	public void paraAudio() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.setFramePosition( 0 );
			clipeDeAudio.stop();
			audioTocando = false;
		}
	}
}
