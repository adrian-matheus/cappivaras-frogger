# Cappivaras

Desenvolvimento do jogo Cappivara's, o Frogger do Século 21

Projeto Semestral do Laboratório de Programação Orientada a Objetos(MAC0321) do Primeiro Semestre de 2022


# Como iniciar o jogo?

## Método Principal - Baixar o arquivo .jar

  Primeiro, garanta que a máquina em questão possui o Java SE Development Kit 17 instalado.
  Ele pode ser obtido no link: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

  Após isso, basta realizar o download do arquivo Cappivaras.jar e executá-lo.

## Método Alternativo - Importar o Projeto do Git para o Eclipse

- 1° Passo: Baixe o IDE Eclipse

  Entre no link: https://www.eclipse.org/downloads/; Clique em "download x86_64"; Clique em "download"; Abra o instalador baixado;  Escolha a opção "Eclipse IDE for Java Developers"; Clique em instalar; Após a instalação, aceite os termos.

- 2° Passo: Importe o projeto do Git

  Entre no link: https://gitlab.com/m10658/cappivaras; Vá em download; Baixe como .zip; Extraia a pasta "cappivaras-main" do arquivo .zip.

- 3° Passo: Abra o projeto pelo Eclipse

  Abra o Eclipse; No ambiente de desenvolvimento do Eclipse, vá em File -> Import... -> General -> Projects from Folder or Archive -> Directory... e selecione a pasta Cappivara's. Depois, extenda o arquivo "cappivaras-main", "src", "(default package)", selecione o arquivo "InicializadorDoJogo.java" e clique no botão "run".


# Como jogar o jogo?

  As teclas WASD e as setas do teclado podem ser utilizadas para navegar através dos menus e movimentar a capivara pelo mapa. As teclas ENTER e Espaço podem ser utilizadas para confirmar as opções do menu e pausar o jogo.

  O objetivo do jogo é levar a capivara até o parque Villa-Lobos atravessando a Raia Olímpica da USP, o Rio Pinheiros, as marginais do rio e a ciclovia, desviando dos obstáculos encontrados nesse percurso. A capivara começa com 3 vidas e a cada colisão com um obstáculo ela perde uma delas e volta ao começo do mapa. Ao perder todas as suas vidas o jogo acaba.

  O jogador pode escolher entre 2 línguas(Português e Inglês), 3 dificuldades de jogo(Fácil, Médio e Difícil) e 3 estilos gráficos( Década de 1930, Contemporâneo e Futurista) para customizar o seu jogo.


# Relatório de Desenvolvimento

## Fase 1:

  Foram implementados os requisitos 1 e 2 do projeto.

  Nesta fase, primeiro criamos a estrutura base do jogo, composta pelo mecanismo de atualização do laço principal de jogo e pelo sistema de preparação e disposição gráfica da janela de jogo.

  Em seguida, foi implementado o controle de movimentação do personagem por meio do teclado (WASD e setas).
  Também trabalhamos nos elementos visuais do jogo, sendo criados imagens – e em alguns casos até mesmo animações - tanto do cenário do mapa quanto da capivara, as quais portamos para o jogo.

  Ademais, implementamos outros sistemas relacionados jogabilidade, como a movimentação da câmera que acompanha o movimento da capivara e delimita-se aos limites do mapa e a criação preliminar da colisão da capivara com os obstáculos do mapa.


## Fase 2:

  Foram implementados os requisitos 3 e 4 do projeto.

  Nesta fase, o projeto foi dividido em 2 branches: um relativo ao requisito 3, e outro relativo ao requisito 4. Em ambos, primeiramente foram resolvidos alguns bugs com relação ao sistema de colisão da capivara e eventualmente o sistema de controle do teclado foi reimplementado para melhorar a jogabilidade.

  Na parte relativa ao requisito 3, foi implementado o sistema de colisão da capivara com entidades móveis no mapa, o sistema de vida relacionado a essa colisão e a criação dessas entidades baseada no padrão Strategy.

  Já na parte relativa ao requisito 4, foi implementado o padrão Abstract Factory para a criação dos diferentes estilos gráficos para o jogo. Esses estilos em si ainda não foram totalmente implementados, mas a arquitetura para tal está completa, restando agora apenas adicionar ao projeto as imagens .png referentes a esses estilos.

  Além disso, foram implementados também o sistema de menus do jogo, através do qual é possível pausar o jogo e escolher o idioma, o nível de dificuldade e o estilo de gráfico do jogo, o comportamento da capivara frente a possíveis colisões com obstáculos no mapa, e o sistema que indica o fim do jogo, seja quando a capivara chegua ao parque ou quando suas vidas acabam.

  Adicionalmente, partes de fases futuras, como a implementação do sistema de estados de jogo pelo padrão State, foram já parcialmente incluídas.


## Fase 3

  Foram implementados os requisitos 5 e 6 do projeto.

  Em relação ao requisito 5, o jogo foi organizado numa sequência de fases dispostas continuamente, não havendo uma separação explícita entre níveis, embora seja possível visualizar facilmente diversos conjuntos de obstáculos sequenciais como, em ordem: as canoas na raia olímpica da USP, os carros e caminhões na primeira marginal, o quebra-cabeça dos barcos sobre o rio Pinheiros, a ciclovia após a marginal e, por fim, as duas vias da marginal do outro lado do rio. O padrão State foi utilizado para implemenntar os diferentes estados de jogo, dentre os quais os menus principal e de configurações, o estado do jogo em andamento e em pausa e as telas de fim de jogo, tendo cada estado um comportamento próprio em resposta aos comandos do jogador.

  Já em relação ao requisito 6, foram implementadas a classe de áudio, que contém os métodos base utilizados para implementar os sons, como tocar áudio continuamente ou uma única vez, pausar ou parar de tocar áudio ou ainda alterar o volume desses áudios, e a classe do gerenciador de áudio, que organiza a forma como esses áudios são carregados no jogo e qual o método de acesso deles, de modo análogo às classes dos gerenciadores de células e de entidades, implementadas anteriormente. Cada estado de jogo diferente possui uma música própria associada ao estilo de jogo selecionado e todas as interações do jogador dentro dos menus e da capivara no mapa possuem efeitos sonoros apropriados associados.

  Ademais, vários bugs foram corrigidos, em particular relacionados ao sistema de colisão - que é original, dado que não foi usada nenhuma engine. Ao arrumar alguns detalhes foi possível admitir algumas simplificações no método de checagem da colisão, o que levou à resolução do bug relacionado à capivara não colidir com células e entidades logo após renascer. Tal era causado pelo fato de não ocorrer um ciclo completo de movimento quando ela morria, o que levava sua posição a ficar desencaixada com a de uma célula, consequentemente tornando impossível a colisão em algumas situações. Por fim, nessa classe, testaram-se diferentes limites de posição da capivara com relação às entidades para que houvesse colisão, com a intenção de ajustar a dificuldade do jogo - não torná-lo nem punitivo demais nem muito fácil.


# Recursos Audiovisuais utilizados

## Fontes Tipográficas

  Todas as fontes tipográficas utilizadas neste projeto operam segundo a licença de uso OFL(SIL Open Font License) que permite o seu uso livre.
  Elas estão disponíveis nos seguintes domínios:
  - Kdam Thmor Pro: https://github.com/sovichet/kdam-thmor-pro
  - Cursive: https://fontlibrary.org/pt/font/cursive
  - New Shape: https://fontlibrary.org/pt/font/new-shape


## Imagens

  Todas as imagens e animações de células do mapa e de entidades utilizadas neste projeto foram criadas pelo prórpio grupo.


## Músicas

  Todas as músicas utilizadas neste projeto foram obtidas no catálogo do grupo "HeatleyBros - Royalty Free Video Game Music", que permite o seu uso livre. 
  Esse catálogo está disponível no seguinte domínio: https://drive.google.com/drive/folders/1C9UIL0cf8Ha5xMIyrMJn9aVAxaCDTEjE


## Efeitos sonoros

  Todos os efeitos sonoros utilizados neste projeto foram obtidos no pacote "The Essential Retro Video Game Sound Effects Collection by Juhani Junkala" da plataforma OpenGameArt.org, que permite o seu uso livre. 
  Esse pacote está disponível no seguinte domínio: https://opengameart.org/content/512-sound-effects-8-bit-style





