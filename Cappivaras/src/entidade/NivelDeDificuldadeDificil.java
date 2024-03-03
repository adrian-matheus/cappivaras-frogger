package entidade;

import sistemasFundamentais.PainelDeJogo;

public class NivelDeDificuldadeDificil extends NivelDeDificuldade {

    public NivelDeDificuldadeDificil( PainelDeJogo painelDeJogo ) {
		
		this.painelDeJogo = painelDeJogo;
		NivelDeDificuldade.mapaDeEntidades = "/mapaDeEntidades_Dificil.txt";
	}		

	
    public void atualizaBarco( Entidade entidade ) {
		
    	if( (entidade.direcao.equals( "cima" ) || entidade.direcao.equals( "baixo" )) &&
       		 entidade.mapaY/painelDeJogo.tamanhoDaCelula >= 34 && entidade.mapaY/painelDeJogo.tamanhoDaCelula <= 46 ) {
       		if( entidade.contadorDeCicloDaEntidade > 48 && entidade.contadorDeCicloDaEntidade < 96 ) {
       			entidade.mapaY += entidade.velocidade;
       		}
       		else if( entidade.contadorDeCicloDaEntidade == 144 ) {
       			entidade.velocidade = -entidade.velocidade;
       			entidade.contadorDeCicloDaEntidade = 0;
       			if( entidade.direcao.equals( "cima" ) ) {
           			entidade.direcao = "baixo";
           		}
           		else {
           			entidade.direcao = "cima";
           		}
       		}
       	}
       	else if( (entidade.direcao.equals( "direita" ) || entidade.direcao.equals( "esquerda" )) &&
       			  entidade.mapaX/painelDeJogo.tamanhoDaCelula >= 0 && entidade.mapaX/painelDeJogo.tamanhoDaCelula <= painelDeJogo.numeroDeColunasDoMapa-2 ) {
       		if( entidade.contadorDeCicloDaEntidade > 48 && entidade.contadorDeCicloDaEntidade < 96 ) {
       			entidade.mapaX += entidade.velocidade;
       		}
       		else if( entidade.contadorDeCicloDaEntidade == 144 ) {
       			entidade.velocidade = -entidade.velocidade;
       			entidade.contadorDeCicloDaEntidade = 0;
       			if( entidade.direcao.equals( "direita" ) ) {
           			entidade.direcao = "esquerda";
           		}
           		else {
           			entidade.direcao = "direita";
           		}
       		}
       	}
		
		entidade.contadorDeCicloDaEntidade++;
	}
	
    
    public void atualizaCanoa( Entidade entidade ) {
        
		if( entidade.mapaX <= painelDeJogo.numeroDeColunasDoMapa*painelDeJogo.tamanhoDaCelula &&
			entidade.mapaX >= -entidade.largura*painelDeJogo.tamanhoDaCelula ) {
			if( entidade.direcao.equals( "direita" ) ) {
				if( entidade.contadorDeCicloDaEntidade > 50 ) {
					entidade.mapaX += entidade.velocidade + 5;
				}
				else {
					entidade.mapaX += entidade.velocidade + 15;
				}
			}	
			if( entidade.direcao.equals( "esquerda" ) ) {
				if( entidade.contadorDeCicloDaEntidade > 50 ) {
					entidade.mapaX += entidade.velocidade - 5;
				}
				else {
					entidade.mapaX += entidade.velocidade - 15;
				}
			}
		}
		else if ( entidade.direcao.equals( "direita" ) ) {
			entidade.mapaX = -entidade.largura*painelDeJogo.tamanhoDaCelula;
		}
		else {
			entidade.mapaX = painelDeJogo.tamanhoDaCelula*painelDeJogo.numeroDeColunasDoMapa;
		}
			
		entidade.contadorDeCicloDaEntidade++;
		
		if( entidade.contadorDeCicloDaEntidade == 100 ) {
			entidade.contadorDeCicloDaEntidade = 0;
		}
	}
    
    
    public void atualizaCarro( Entidade entidade ) {	
		
		if( entidade.contadorDeCicloDaEntidade > 0 ) {
			if( entidade.direcao.equals( "direita" ) ) {
				if( entidade.mapaX <= painelDeJogo.numeroDeColunasDoMapa*painelDeJogo.tamanhoDaCelula ) {				
					if( entidade.contadorDeCicloDaEntidade > 50 ) {
						entidade.mapaX += entidade.velocidade + 5;
					}
					else {
						entidade.mapaX += entidade.velocidade + 10;
					}
				}
				else {
					entidade.mapaX = -entidade.largura*painelDeJogo.tamanhoDaCelula;
				}	
			}
			if( entidade.direcao.equals( "esquerda" ) ) {
				if( entidade.mapaX >= -entidade.largura*painelDeJogo.tamanhoDaCelula*painelDeJogo.tamanhoDaCelula ) {
					if( entidade.contadorDeCicloDaEntidade > 150 ) {
						entidade.mapaX += entidade.velocidade - 5;
					}
					else {
						entidade.mapaX += entidade.velocidade - 12;
					}
				}
				else {
					entidade.mapaX = painelDeJogo.tamanhoDaCelula*painelDeJogo.numeroDeColunasDoMapa;
				}		
			}
		}
		
		entidade.contadorDeCicloDaEntidade++;
		
		if( entidade.contadorDeCicloDaEntidade > 200 ) {
			entidade.contadorDeCicloDaEntidade = 0;
		}
	}
    
    
    public void atualizaCaminhao( Entidade entidade ) {	
		
		if( entidade.contadorDeCicloDaEntidade > 0 ) {
			if( entidade.direcao.equals( "direita" ) ) {
				if( entidade.mapaX <= painelDeJogo.numeroDeColunasDoMapa*painelDeJogo.tamanhoDaCelula ) {				
					if( entidade.contadorDeCicloDaEntidade > 50 ) {
						entidade.mapaX += entidade.velocidade + 5;
					}
					else {
						entidade.mapaX += entidade.velocidade + 10;
					}
				}
				else {
					entidade.mapaX = -entidade.largura*painelDeJogo.tamanhoDaCelula;
				}	
			}
			if( entidade.direcao.equals( "esquerda" ) ) {
				if( entidade.mapaX >= -entidade.largura*painelDeJogo.tamanhoDaCelula ) {
					if( entidade.contadorDeCicloDaEntidade > 150 ) {
						entidade.mapaX += entidade.velocidade - 5;
					}
					else {
						entidade.mapaX += entidade.velocidade - 12;
					}
				}
				else {
					entidade.mapaX = painelDeJogo.tamanhoDaCelula*painelDeJogo.numeroDeColunasDoMapa;
				}		
			}
		}
		
		entidade.contadorDeCicloDaEntidade++;
		
		if( entidade.contadorDeCicloDaEntidade > 200 ) {
			entidade.contadorDeCicloDaEntidade = 0;
		}
	}
    
    
	public void atualizaCiclista( Entidade entidade ) {
		
		if( entidade.mapaX >= -entidade.largura*painelDeJogo.tamanhoDaCelula && 
			entidade.mapaX <= painelDeJogo.numeroDeColunasDoMapa*painelDeJogo.tamanhoDaCelula ) {
			entidade.mapaX += entidade.velocidade;
		} else if( entidade.direcao.equals( "direita" ) ) {
			entidade.mapaX = -entidade.largura*painelDeJogo.tamanhoDaCelula;
		} else {
			entidade.mapaX = painelDeJogo.tamanhoDaCelula*painelDeJogo.numeroDeColunasDoMapa;
		}
		
        entidade.contadorDeCicloDaEntidade++;
		
		if( entidade.contadorDeCicloDaEntidade > 10 ) {
			entidade.contadorDeCicloDaEntidade = 0;
		}
	}
}
