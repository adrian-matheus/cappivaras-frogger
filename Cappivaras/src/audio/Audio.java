package audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {

	public Clip clipeDeAudio; // Clipe de �udio a ser tocado.
	public int tipoDeAudio; // Tipo de �udio (M�sica ou efeito sonoro).
	public boolean audioTocando = false; // Verificador que acusa se o clipe de �udio est� tocando ou n�o.
	
	
	// M�todo que altera o volume o clipe de �udio.
	public void alteraVolume( float volume ) {
		
		if( clipeDeAudio != null ) {
			
			FloatControl controleDeVolume = (FloatControl)clipeDeAudio.getControl( FloatControl.Type.MASTER_GAIN );
			
			float faixaDeVolume = controleDeVolume.getMaximum() - controleDeVolume.getMinimum();
			float volumeDoAudio = (faixaDeVolume * volume) + controleDeVolume.getMinimum();
			
			controleDeVolume.setValue( volumeDoAudio );
		}
	}
	
		
	// M�todo que toca o clipe de �udio completo uma �nica vez.
	public void tocaAudioSimples() {
		
		if( clipeDeAudio != null ) {
			if( clipeDeAudio.getFramePosition() == clipeDeAudio.getFrameLength() ) {
				clipeDeAudio.setFramePosition( 0 );
			}
			clipeDeAudio.start();
			audioTocando = true;
		}
	}
	
	
	// M�todo que toca o clipe de �udio repetidamente de forma continua.
	public void tocaAudioContinuo() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.start();
			clipeDeAudio.loop( Clip.LOOP_CONTINUOUSLY );
			audioTocando = true;
		}
	}
	
	
	/* M�todo que para de tocar o clipe de �udio de modo que ele possa ser retomado de onde
	 * parou ao se chamar o m�todo 'tocaAudioSimples' ou 'tocaAudioContinuo' posteriormente.
	 */
	public void pausaAudio() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.stop();
			audioTocando = false;
		}
	}
	
	
	/* M�todo que para de tocar o clipe de �udio de modo que ele recomece do in�cio ao
	 * se chamar o m�todo 'tocaAudioSimples' ou 'tocaAudioContinuo' posteriormente.
	 */
	public void paraAudio() {
		
		if( clipeDeAudio != null ) {
			clipeDeAudio.setFramePosition( 0 );
			clipeDeAudio.stop();
			audioTocando = false;
		}
	}
}
