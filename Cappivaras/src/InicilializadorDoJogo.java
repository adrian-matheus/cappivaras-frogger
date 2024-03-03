import java.awt.Color;

import javax.swing.JFrame;

import sistemasFundamentais.PainelDeJogo;

public class InicilializadorDoJogo {

	public static void main( String args[] ) {
		
		JFrame janela = new JFrame();
		PainelDeJogo painelDeJogo = new PainelDeJogo();
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // Determina o fechamento da janela ao clicar-se no ícone de X no canto superior direito da mesma.
		janela.setResizable( false ); // Impede o ajuste do tamanho da janela pelo usuário.
		janela.setTitle( " Cappivara's - o Frogger do Século 21 " ); // Determina o título da janela.
		janela.add( painelDeJogo );  // Adiciona o painel de jogo à janela.
		janela.pack(); // Prepara o tamanho da janela de acordo com o necessário por seus componentes.
		janela.setLocationRelativeTo( null ); // Determina a inicialização da janela no centro da tela.
		janela.setVisible( true ); // Determina a visibilidade da janela.
		janela.setBackground( Color.black ); // Determina a cor preta para o fundo a janela.
		
		painelDeJogo.iniciaThreadDoJogo(); // Inicia a Thread do jogo, que por sua vez iniciará o laço principal do jogo.
	}
}





