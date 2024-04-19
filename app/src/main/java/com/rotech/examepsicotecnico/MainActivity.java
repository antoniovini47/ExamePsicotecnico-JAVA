package com.rotech.examepsicotecnico;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends Activity {

    public String[] lerESeparaQuestoes(String asQuestoesTodasJuntas, int quantasQuestoes){
        String[] questoesSeparadas = new String[quantasQuestoes+1];
        int nQuestao = 1, ini=0, fim=0;

        Log.i("MSGLOG", "Entramos no lerESeparaQuestoes()");
        //Separa a questão "i"
        for (int i=0; i<asQuestoesTodasJuntas.length()-1; i++){
            //Um teste para ler caractere a caractere: Log.i("MSGLOG", "lerESeparaQuestoes() no caractere N: " + String.valueOf(i) + " - " + asQuestoesTodasJuntas.substring(i,i+1));
            if (asQuestoesTodasJuntas.substring(i, i + 2).equals("F-")) {
                fim = i+2;
                questoesSeparadas[nQuestao] = asQuestoesTodasJuntas.substring(ini,fim+1);
                nQuestao++;
                ini = fim+1;
            }
        }

        return questoesSeparadas;
    }

    public Questao lerEMontaQuestao(String questaoBruta){
        Questao questaoMontada = new Questao();
        String altA = "a)", altB = "b)", altC="c)", altD="d)", altE="e)",
                questaoInteira = questaoBruta, textoDaQuestao = "texto da questao";
        int inicioAlt = 0, fimAlt = 0, numeroDaQuestao, imgDaQuestao = 0, altCorreta = 0, grupoQuestao = 0;

        //Separa o número da alternativa
        numeroDaQuestao = Integer.parseInt(questaoInteira.substring(0, 2));

        //Lê as alternativas e vai fazendo o que quero
        for (int i = 0; i < questaoInteira.length()-1; i++) {
            //Separa o texto da questão
            if (questaoInteira.substring(i, i + 2).equals(". ")) {
                inicioAlt = i+2;
            }
            if (questaoInteira.substring(i, i + 2).equals("a)")) {
                fimAlt = i;
                textoDaQuestao = questaoInteira.substring(inicioAlt, fimAlt);
            }

            //Separa a alternativa A
            if (questaoInteira.substring(i, i + 2).equals("a)")) {
                inicioAlt = i;
            }
            if (questaoInteira.substring(i, i + 2).equals("b)")) {
                fimAlt = i;
                altA = questaoInteira.substring(inicioAlt, fimAlt);
            }

            //Separa a alternativa B
            if (questaoInteira.substring(i, i + 2).equals("b)")) {
                inicioAlt = i;
            }
            if (questaoInteira.substring(i, i + 2).equals("c)")) {
                fimAlt = i;
                altB = questaoInteira.substring(inicioAlt, fimAlt);
            }

            //Separa a alternativa C
            if (questaoInteira.substring(i, i + 2).equals("c)")) {
                inicioAlt = i;
            }
            if (questaoInteira.substring(i, i + 2).equals("d)")) {
                fimAlt = i;
                altC = questaoInteira.substring(inicioAlt, fimAlt);
            }

            //Separa a alternativa D
            if (questaoInteira.substring(i, i + 2).equals("d)")) {
                inicioAlt = i;
            }
            if (questaoInteira.substring(i, i + 2).equals("e)")) {
                fimAlt = i;
                altD = questaoInteira.substring(inicioAlt, fimAlt);
            }

            //Separa a alternativa E e a alternativa correta
            if (questaoInteira.substring(i, i + 2).equals("e)")) {
                inicioAlt = i;
            }
            if (questaoInteira.substring(i, i + 2).equals("F-")) {
                fimAlt = i;
                altE = questaoInteira.substring(inicioAlt, fimAlt);
                altCorreta = Integer.parseInt(questaoInteira.substring(i+2,i+3));
            }
        }
        questaoMontada.setDados(numeroDaQuestao,imgDaQuestao, textoDaQuestao, altA, altB, altC, altD, altE, altCorreta, grupoQuestao);
        return questaoMontada;
    }

    public Questao[] leitorDeBD(int quantasQuestoes){
        int quantidadeDeQuestoesNoBD = quantasQuestoes+1;//Define quantas questões tem no Banco de Dados
        Questao[] bancoDeQuestoes = new Questao[quantidadeDeQuestoesNoBD];
        String[] questoes = new String[quantidadeDeQuestoesNoBD];

        //instancia as questões
        for (int i = 0; i<quantidadeDeQuestoesNoBD; i++){
            bancoDeQuestoes[i] = new Questao();
            questoes[i] = new String();
        }

        Log.i("MSGLOG", "Antes de lerESeparaQuestoes()");
        questoes = lerESeparaQuestoes("01. Na presença de sangramento abundante, qual o cuidado indicado? a) Garrotear (usar torniquete ). b) Fazer compressão no local do sangramento. c)Dar bastante líquido para a pessoa ir tomando. d)Jogar bastante água oxigenada para coagular e limpar o ferimento. e)Deixar o sangramento parar sozinho.F-202. Uma pessoa foi atropelada e está caída no meio da rua, o que fazer em primeiro lugar?a)Remover a pessoa para a calçada.b)Anotar a chapa ou correr atrás do carro que atropelou.c)Tentar chamar algum parente da vítima.d)Iniciar imediatamente o atendimento, no local.e)Sinalizar o local para evitar outros acidentes.F-503. Um acidentado apresenta um pedaço de vidro encravado no olho, o que fazer no local, antes de remover a vítima?a)Retirar o vidro com os dedos.b)Retirar o vidro com uma pinça.c)Pingar colírio anestésico/desinfetante.d)Cobrir o ferimento e fechar o outro olho.e)Lavar com água gelada.F-404. Uma pessoa ao fechar a porta do carro teve seu dedo arrancado (amputado) . O que fazer com o dedo?a)Desprezar o dedo arrancado e socorrer a vítima, imediatamente.b)Recolher o dedo e colocá-lo diretamente no gelo.c)Recolher o dedo e colocá-lo no álcool.d)Embrulhar o dedo em gaze e levá-lo junto com a pessoa para o hospital.e)Tentar colar o dedo, imediatamente, enfaixando-o com esparadrapo.F-405. Em caso de acidentea)é obrigação de todos sempre prestar auxílio à vítima.b)é obrigação de todos prestar auxílio desde que não corra risco pessoal.c)é obrigação de todos prestar auxílio, mesmo com risco pessoal.d)é obrigação de socorrer apenas para quem causou o acidente.e)não existe obrigação legal em socorrer.F-206. Vítima que usava cinto de segurança está inconsciente dentro do veículo. O que fazer em primeiro lugar?a)Sinalizar o local e chamar o resgate.b)Retirar o cinto de segurança.c)Retirar a vítima do veículo e deitá-la.d)Se for banco reclinável, incliná-lo o máximo possível.e)Esperar que recobre a consciência.F-107. Vítima de acidente pede água para beber. O que fazer?a)Mantê-la em jejum.b)Dar bastante líquido para hidratar a vítima.c)Dar um copo, no máximo.d)Não forçar, deixar tomar apenas o que quiser.e)Dar leite ou líquidos adocicados, de preferência.F-108.Vítima apresenta fratura exposta (o osso quebrado está para fora ). O que fazer?a)Garrotear o membro fazendo um torniquete.b)Empurrar aquele osso para dentro.c)Puxar o membro para que o osso volte para seu lugar.d)Observar se a vítima está respirando, imobilizar o membro e acalmar a vítima.e)Ir jogando água gelada até chegar o resgate.F-409.Vítima de acidente de trânsito parou de respirar. Nesta situação, vocêa)avalia que a vítima morreu, não há mais nada a fazer.b)avalia que a vítima ainda pode estar viva, senão estiver roxa.c)avalia que a vítima pode estar viva e deve ser atendida imediatamente.d)fica impedido de prestar socorro se estiver sozinho.e)aplica alguns tapas nas costas, pois a vítima pode estar engasgada.F-310.Uma pessoa bateu a cabeça, perdeu a consciência e depois acordou e diz que está bem. O que fazer?a)Neste caso, não há necessidade de ir ao hospital.b)Recomendar que a pessoa fique acordada durante 24 horas.c)Sempre levar a pessoa ao hospital.d)Levar ao hospital somente se tiver que fazer curativo.e)Apenas fazer compressas com gelo no local da batida.F-311.Se você estiver sozinho com uma vítima de acidente de trânsito e precisar fazer o socorro da mesma, como proceder?a)Somente iniciar o socorro se conseguir alguém para ajudar.b)Verificar as vias aéreas e imobilizá-la para o transporte.c)Apenas colocar a vítima no carro e correr para o hospital.d) Pedir para balançar a cabeça e os membros para ver se não quebrou nada.e)Beliscar e cutucar a vítima para ver se está viva.F-212.Vítima de acidente apresenta corpo estranho(parte metálica ) encravado em seu corpo. O que fazer?a)Retirar imediatamente o corpo estranho.b)Verificararespiraçãoenãotentarremoverocorpoestranho.c)Retirar o corpo estranho e comprimir o local com gaze.d)Só retirar o corpo estranho se este estiver causando dor.e)Retirar o corpo estranho e esperar a coagulação do sangue.F-213.Em caso de atropelamento ou acidente com vítimas, qual o melhor local para estacionar o veículo e prestar auxílio?a)Ao lado da vítima.b)Um pouco mais a frente do acidente.c)Antes do local onde está a vítima, evitando causar outro acidente.d)O importante é ser rápido, não importando o local.e)Atravessar o veículo na pista, impedindo o tráfego.F-314.Sabe-se que o primeiro trauma é aquele que ocorre no acidente e que o segundo trauma é aquele que ocorre quando não socorremos com cuidado. É INCORRETO afirmar que:a)o cinto de segurança e o capacete evitam que o primeiro trauma seja mais grave.b)o uso de colar cervical ajuda a evitar o segundo trauma.c)quando desacordada, a vítima pode sufocar-se com a própria língua.d)deve-se sempre evitar gesto brusco no atendimento da vítima.e)deve-se sempre colocar a vítima sentada.F-515. Ao transportar uma vítima, é INCORRETOa)manter a vítima em jejum.b)transportar a vítima imobilizada.c)comprimir os ferimentos que estejam sangrando.d)manter as vias aéreas livres, desobstruídas.e)manter, a qualquer custo, a vítima acordada.F-516. Uma vítima de acidente de trânsito está gritando, com muita dor. O que fazer?a)Dar remédio para dor.b)Levar imediatamente para o hospital.c)Esperar a chegada do resgate.d)Fazer compressas geladas no local da dor.e)Fazer compressas quentes no local da dor.F-317. Trafegando por uma via alguém lhe acena para parar afim de prestar socorro em um acidente com vítimas. Você, já tendo estacionado e ligado o pisca-alerta do seu veículo,a)facilita a respiração do acidentado, afrouxando suas roupas, sem alterar sua posição, enquanto aguarda socorro.b)movimenta a vítima para analisar a extensão do acidente.c)remove os veículos do local para desobstruir a via.d)dá líquidos ao acidentado, tentando reanimá-lo.e)coloca a vítima em seu veículo, conduzindo-a ao hospital.F-118. Para socorrer, corretamente, uma pessoa que sofreu um trauma em acidente com veículo, deve-se, em primeiro lugar, levar em consideraçãoa)a obstrução das vias aéreas.b)uma possível parada cardíaca.c)o sangramento das feridas.d)uma possível fratura de osso.e)uma lesão cerebral.F-119. O que fazer, no local, com o acidentado que sofreu queimaduras?a)Passar pasta de dente na ferida.b)Passar desinfetante na ferida.c)Cobrir a ferida com gaze.d)Dar leite para a pessoa tomar.e)Lavar com água limpa, apenas.F-520. Diante de uma vítima, em primeiro lugar, deve-se verificar se:a)há obstrução de vias aéreas.b)há muito sangramento.c)há muitas fraturas.d)a dor é muito forte.e)consegue ficar em pé e andar sozinha.F-121. Um motociclista sofreu acidente e encontra-se caído com o capacete na cabeça. O que fazer?a)Remover imediatamente o capacete.b)Remover o capacete somente se ele estiver consciente.c)Remover o capacete somente se ele estiver inconsciente.d)Sinalizar o local, chamar o resgate e verificar a respiração.e)Ajudá-lo a erguer-se do chão.F-422. O melhor local no corpo para se verificar a pulsação é:a)no pé.b)no pescoço.c)no punho.d)atrás do joelho.e)em alguma veia saliente.F-223. Num acidente de trânsito deverá receber os primeiros socorros, em primeiro lugar, a vítima que estivera)gritando, com muita dor.b)sangrando muito.c)respirando com muita dificuldade.d)xingando, com muitas ameaças.e)com muitas fraturas.F-324.Num acidente de trânsito, em primeiro lugar, deve-se avaliar as vias aéreas e estabilizar a coluna cervical (pescoço)da vítima, imobilizando-a. Esta ação é muito importante porquea)a cabeça despenca após o acidente.b)segurando a vítima pelo pescoço ela não se debate.c)o pescoço é de fácil alcance, não tendo que tirar roupas.d)evita que a pessoa fique paralítica.e)evita que a vítima se vire para ver o que fazemos.F-425.O \"estado de choque\" ou \"a vítima está chocada\" ou \"entrou em choque\" querem dizer quea)certamente há alguns ossos quebrados.b)a vítima está sentindo muita dor.c)a vítima está emocionalmente abalada, quase desmaiando.d)a vítima chocou-se contra objetos.e)a oxigenação do organismo está deficiente. F-526.Prevenir-se ao prestar socorros a alguém significa:a)somente socorrer se estiver acompanhado por alguém.b)evitar riscos pessoais e acidentes secundários.c)evitar ser chamado como testemunha.d)socorrer somente durante o dia.e)usar uma luva em cima da outra (dois pares).F-227.Ao transportar uma vítima com fraturas expostas, deve-se em primeiro lugar:a)prevenir a vítima que ela sentirá dor e em seguida puxar o membro machucado, colocando-o no lugar.b)enfaixar toda a região machucada para evitar contaminação.c)procurar algo rígido, enfaixando-o junto ao membro machucado para imobilizá-lo.d)segurar o membro quebrado enquanto outros levantam a vítima.e)não mexer na fratura.F-328.Ao observar uma pessoa tendo convulsões, deve-sea)não interferir porque isto passa espontaneamente.b)abrir a boca da vítima e colocar um pano entre os dentes para evitar que ela morda a língua.c)pedir ajuda de outras pessoas e tentar imobilizá-la segurando-a firmemente contra o chão.d)proteger a cabeça da pessoa contra traumas e virá-la de lado em caso de vômitos.e)abrir as vestes para melhorar a respiração, sacudindo-a para tirá-la do transe.F-429.Você está só e depara-se com uma vítima que não tem movimentos respiratórios e nem pulsação. Nesta situação vocêa)verifica se a vítima está fria ou quente.b)chama o serviço de verificação de óbitos.c)inicia imediatamente manobras de reanimação cardiopulmonar.d)procura um telefone chamando o resgate.e)verifica os documentos da vítima e chama a família.F-330.Quando alguém sofre um traumatismo e desmaia, o que é mais perigoso e comum em causar obstrução das vias aéreas?a)Dentes quebrados que são engolidos.b)Sangue do nariz que entope a garganta.c)O catarro do pulmão que não consegue sair.d)A própria língua ao relaxar.e)Quando desmaiada a pessoa se esquece de puxar o ar.F-431. Queimaduras podem ser causadas por produtos químicos. Oprocedimento mais adequado quando alguém derrama ácido de bateria em seu próprio corpo é:a)remover imediatamente o produto lavando com água.b)limpar com pano o local afetado e colocar uma pomada para queimadura.c)usar álcool para limpar o local, friccionando a região.d)cobrir a área com um pano limpo sem remover o produto.e)não fazer nada de imediato, somente se inflamar.F-432. Correntes elétricas podem ocasionar queimaduras, mesmo que a pele aparente estar normal. Nestasituação, recomenda-sea)observar a evolução, sem fazer nada.b)levar a vítima para o hospital.c)dar um analgésico em caso de dor e levá-la para casa.d)umedecer a região com algum creme hidratante.e)lavar a região e cobrir com gaze.F-233. Em um acidente deve-se evitar atitude que possa colocar a vítima em perigo ocasionando maiores danos. Qual das atitudes está INCORRETA?a)Levar a vítima imediatamente ao hospital, não perder mais tempo.b)Verificar sua respiração, pulsação e sangramento.c)Imobilizar a vítima caso seja necessário, evitando movimentá-la desnecessariamente.d)Sinalizar o local para evitar outros acidentes.e)Prestar auxílio e chamar o resgate.F-134. Considere as afirmações: Sempre usar de bom senso ao dirigir não colocando a própria vida e a de outros em risco. Prevenir acidentes sendo solidário em situações difíceis.É INCORRETO afirmar que se devea) manter a calma em todas as situações evitando ser agressivo.b)fazer curso básico de primeiros socorros para realmente aprender.c) encarar as advertências de segurança como sendo para nós mesmos, e não para os outros.d)manter o veículo em boas condições de uso.e)dirigir bem devagar nas rodovias, caso tenha bebido.F-535. Em um acidente a vítima está dentro do veículo que tem fumaça em seu interior. Nesta situação, o que fazer após chegar à conclusão que não há risco pessoal?a)Retirar a pessoa de dentro do carro, após imobilizá-la da melhor forma possível.b)Deixaravítimasentadadentrodoveículoeoferecermuitoleite a ela, aguardando a dissipação da fumaça.c)Afastar-se rapidamente chamando o resgate.d)Jogar água no veículo e até na vítima para resfriar o local.e)Arejar a vítima ligando os circuladores de ar do veículo, aguardando a dissipação da fumaça.F-136. A contaminação pelo vírus da AIDS ocorre de várias maneiras. NÃO corre risco de contaminação quema)está tomando antibióticos.b)socorre a vítima usando luvas.c)executa respiração boca a boca na vítima, sem máscara.d)socorre a vítima fazendo higiene após atendimento.e)se alimenta bem e toma vitaminas.F-237. É correto afirmar quea)em um acidente uma criança tem mais resistência à perda de sangue.b)uma pessoa idosa tem ossos mais resistentes aos impactos.c)uma mulher grávida não deve usar cinto de segurança.d)manter a calma significa não ter nenhuma pressa.e)alguém sempre deverá assumir a liderança do socorro.F-538.Que atitude deve-se tomar quando alguém que sofreu acidente e necessita de socorro é portador do vírus da AIDS ?a)Deixar de prestar socorro à vítima, pois não há, neste caso, obrigatoriedade.b)Chamar o resgate, apenas.c)Prestar socorro à vítima com as devidas precauções.d)Ignorar a vítima pois ela tem pouca resistência.e)Prestar socorro à vítima sem qualquer receio ou precaução.F-339.Qual das atitudes abaixo é a correta quando precisamos, por meios próprios, transportar uma vítima que está gritando de dor?a)Deitá-la rapidamente no banco de trás do veículo.b)Jamais deitá-la, pois poderá desfalecer.c)Acionar a buzina e dirigir em alta velocidade.d)Dirigir em baixa velocidade para não machucar a vítima.e)Imobilizar a vítima antes do transporte.F-540.Quando há sinais claros que a vítima não tem respiração ou pulsação e não há mais tempo a perder. O que fazer?a)Remover a vítima imediatamente, sem se preocupar com mais nada levando-a para o hospital.b)Virar a vítima de bruços e tentar comprimir suas costas.c)Colocar a vítima de costas sobre uma superfície rígida ou no chão e iniciar manobras de reanimação.d)Iniciar imediatamente manobras de reanimação, esteja como estiver a vítima.e)Não adianta fazer mais nada.F-341.Num acidente observamos que o ferimento de uma vítima esguicha sangue no mesmo ritmo de sua pulsação. Conclui-se que ocorreu o corte dea)uma artéria.b)uma veia.c)um nervo.d)um tendão.e)um músculo.F-142.Ao observar uma vítima que está sacudindo-se, ora contrai os músculos, ora relaxa, com respiração ruidosa, com secreção (espuma ) pela boca, deve-se:a)não se aproximar pois a secreção é contagiosa.b)esperar a pessoa acordar e perguntar se já teve estes sintomas antes.c)virar a pessoa de bruços pois ela pode se afogar.d)tomar cuidado para que ela não se machuque com seus próprios movimentos.e)tentar abrir a boca da vítima.F-443.O atendimento inicial que é feito no local de um acidente visaa)socorrer a vítima evitando despesas hospitalares.b)auxiliar a vítima e evitar consequências danosas no atendimento e no transporte.c)manter a vítima viva, sem se preocupar com as conseqüências.d)preparar a vítima para cirurgia.e)fazer a ocorrência policial evitando-se ir à delegacia.F-244.Quando for necessária a remoção de uma vítima e você estiver sozinho, como deverá ser feita esta remoção?a)Dar apoio lateral, colocando o braço da vítima em seu pescoço, puxando-a de lado.b)Levantando-a no colo, com a cabeça pendente, andando para frente.c)Puxando-a pelos braços, arrastando-a cuidadosamente.d)Puxando-a pelas pernas, arrastando-a cuidadosamente.e)Colocando-seportrásdavítima,abraçando-aefirmandoseu tórax e sua coluna, andando de marcha a ré.F-545. Quando for necessária a remoção de uma vítima e houver no local mais duas pessoas, além de você, qual o procedimento correto?a)Uma pessoa seguraria as pernas, a outra os braços e a terceira daria apoio às costas.b)Uma pessoa pegaria a vítima abraçando-a por trás, a outra pessoa levantaria as duas pernas e a terceira daria apoio às costas.c) Uma pessoa cuidaria da cabeça e da coluna cervical, outra do tronco e membros superior e a terceira dos membros inferiores.d) Duas pessoas removeriam a vítima segurando-a pelos braços e pernas e a terceira ficaria para pegá-la dentro do carro.e) Duas pessoas dariam apoio–uma de cada lado–abraçando a vítima de pé e a terceira apoiaria suas pernas.F-346. Ao prestar auxílio em um acidente deve-se seguir uma sequência de procedimentos. Qual a ordem correta das ações quando avistamos um acidente?a)1º Resgatar as vítimas; 2º Isolar e sinalizar a área; 3º Avaliar o estado das vítimas; 4º Chamar o resgate.b)1º Isolar e sinalizar a área; 2º Chamar o resgate; 3º Avaliar o estado das vítimas.c) 1º Chamar imediatamente o resgate; 2º Avaliar o estado das vítimas; 3º Remover as vítimas para o acostamento; 4º Isolar e sinalizar a área.d)1º Isolar e sinaliz a área; 2º Avaliar o estado das vítimas; 3º Chamar o resgate.e)1º Avaliar a situação; 2º Socorrer imediatamente os feridos; 3º Remover as vítimas para o acostamento; 4º Chamar o resgate.F-447. Quando o acidente envolve cargas perigosas e liberação de produtos químicos no meio ambiente, deve-sea)acionar um caminhão pipa para jogar água, limpar e liberar rapidamente a rodovia.b)tentar liberar parte da rodovia sinalizando o local.c)se não houver risco de explosão, socorrer as vítimas sem outros receios.d)isolar rapidamente o local, avaliar o perigo para si mesmo e depois tentar o socorro.e)afastar-se o mais rapidamente possível do local, abandonando o veículo no acostamento.F-448. No atendimentoàvítima,deve-se dar prioridade ao desbloqueio das vias aéreas e às possíveis lesões da coluna cervical. Estes procedimentos são indispensáveis porquea) se não cuidarmos da oxigenação e não considerarmos que a medula foi atingida, os danos podem ser irreversíveis.b)tanto a coluna quanto a boca e o nariz estão mais visíveis e de fácil acesso para uma primeira avaliação.c)evitam processos judiciais por imperícia ou imprudência.d)é mais fácil desbloquear as vias aéreas e estabilizar a coluna cervical do que estancar as hemorragias.e)se não houver oxigenação, é obrigatório o procedimento de respiração boca a boca.F-149. Vítima se apresenta desmaiada na via pública. É procedimento INCORRETOa)sinalizar o local do acidente protegendo a vítima.b)chacoalhar a vítima tentando acordá-la para que ela se levante.c)tentar ajuda de terceiros para chamar o resgate.d)na presença de outras pessoas, mexer na bolsa ou bolsos para achar os documentos da vítima.e)apenas avisar o resgate, anonimamente.F-250.Em um acidente quem tem autoridade máxima no atendimento à vítima éa)quem tiver o estojo de primeiros socorros.b)o policial.c)quem iniciou o atendimento.d) o técnico em emergência e resgate o médico.e) nda.F-5 00FIM", quantasQuestoes);
        Log.i("MSGLOG", "Pós lerESeparaQuestoes()");

        //A questão Zero é ignorada, ou seja, bancoDeQuestoes[0] == null
        for(int i=1;i<=quantasQuestoes;i++) {
            Log.i("MSGLOG", "Tentando inserir questão " + i + " no banco.");
            Log.i("MSGLOG", "Questao bruta: " + questoes[i]);
            bancoDeQuestoes[i] = lerEMontaQuestao(questoes[i]);
            Log.i("MSGLOG", "Questao " + i + " inseriada no banco.");
        }
        Log.i("MSGLOG", "Banco de questões preenchido com sucesso.");
        //Inserir questões aqui

        /*DEFINIR GRUPO DAS QUESTÕES EM LOTES
        for(int i=0; i<=bancoDeQuestoes.length-1; i++){
            if (i<=60) {
                bancoDeQuestoes[i].setGrupoDaQuestao(1);
            }
        }*/

        //Aqui entra a função de sortear
        Questao[] questoesSelecionadas = new Questao[quantasQuestoes+1];
        System.arraycopy(bancoDeQuestoes, 0,
                questoesSelecionadas, 0,
                quantasQuestoes + 1);
        /*Esse comando acima ou:
        for (int i=0; i<=quantasQuestoes; i++){
            questoesSelecionadas[i] = bancoDeQuestoes[i];
        }*/

        return questoesSelecionadas;
    }


    //Quantas questões tem no BD e Contador de Questão
    static int totalQuestoesBD = 50,
            emQualQuestaoEsta = 0,
            quantasQuestoesASortearEApresentar,
            pontuacao = 0,
            pausaDramatica = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tela Cheia
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Seek Bar - Define o máximo e mínimo de questões
        SeekBar sbQuantQuest = (SeekBar) findViewById(R.id.sbQuantQuest);
        sbQuantQuest.setMax(totalQuestoesBD); //Esse número tem que ser a quantidade de questões que tem no BD
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sbQuantQuest.setMin(3); //Define o número mínimo de questões a sortear
        }
        TextView tvQuantQuest = (TextView) findViewById(R.id.tvQuantQuest);
        tvQuantQuest.setText(String.valueOf(totalQuestoesBD) + " Questões");
        sbQuantQuest.setProgress(totalQuestoesBD);
        sbQuantQuest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQuantQuest.setText(String.valueOf(sbQuantQuest.getProgress()) + " Questões");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Istancia todas as questões nesse BD
        Questao[] todasAsQuestoesEmOrdem = leitorDeBD(totalQuestoesBD);
        Questao[] questoesSelecionadas = new Questao[totalQuestoesBD + 1];

        //Istancia os radio buttons e local pra apresentação da questão
        TextView tvTextoDaQuestao = (TextView) findViewById(R.id.tvTextoQuestao);
        RadioGroup rgAlternativas = (RadioGroup) findViewById(R.id.rgOpcoes);
        RadioButton rbAlternativaA = (RadioButton) findViewById(R.id.rbA);
        RadioButton rbAlternativaB = (RadioButton) findViewById(R.id.rbB);
        RadioButton rbAlternativaC = (RadioButton) findViewById(R.id.rbC);
        RadioButton rbAlternativaD = (RadioButton) findViewById(R.id.rbD);
        RadioButton rbAlternativaE = (RadioButton) findViewById(R.id.rbE);

        //Instacia o audio
        final AssetFileDescriptor afdCorreto = getResources().openRawResourceFd(R.raw.correto), afdIncorreto = getResources().openRawResourceFd(R.raw.incorreto);


        //Configura o botão
        Button bProxima = (Button) findViewById(R.id.bProxima);
        bProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Funções do clique no botão caso seja uma alternativa
                if (emQualQuestaoEsta>0 && emQualQuestaoEsta<=quantasQuestoesASortearEApresentar){
                    RadioButton rbMarcado = findViewById(rgAlternativas.getCheckedRadioButtonId());
                    int correta = questoesSelecionadas[emQualQuestaoEsta].getqCorreta(), marcada = 0;
                    try {
                        if (rbMarcado.getId() == rbAlternativaA.getId()){
                            marcada = 1;
                        }else if (rbMarcado.getId() == rbAlternativaB.getId()){
                            marcada = 2;
                        }else if (rbMarcado.getId() == rbAlternativaC.getId()){
                            marcada = 3;
                        }else if (rbMarcado.getId() == rbAlternativaD.getId()){
                            marcada = 4;
                        }else if (rbMarcado.getId() == rbAlternativaE.getId()){
                            marcada = 5;
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Escolha uma opção!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(marcada==0){
                        Toast.makeText(getApplicationContext(), "Escolha uma opção! (0)", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(marcada==correta){
                        //ALTERNATIVA CORRETA
                        pontuacao++;
                        //Toca som positivo
                        try {
                            final MediaPlayer mpResultado = new MediaPlayer();
                            mpResultado.setDataSource(afdCorreto.getFileDescriptor(), afdCorreto.getStartOffset(), afdCorreto.getLength());
                            mpResultado.prepareAsync();
                            mpResultado.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Colore a alternativa
                        rbMarcado.setTextColor(getResources().getColor(R.color.verde));
                    }else{
                        //ALTERNATIVA INCORRETA
                        //Toca som de erro
                        try {
                            final MediaPlayer mpResultado = new MediaPlayer();
                            mpResultado.setDataSource(afdIncorreto.getFileDescriptor(), afdIncorreto.getStartOffset(), afdIncorreto.getLength());
                            mpResultado.prepareAsync();
                            mpResultado.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Colore as alternativas
                        rbMarcado.setTextColor(getResources().getColor(R.color.vermelho));
                        if (correta==1){
                            rbAlternativaA.setTextColor(getResources().getColor(R.color.verde));
                        }else if (correta==2){
                            rbAlternativaB.setTextColor(getResources().getColor(R.color.verde));
                        }else if (correta==3){
                            rbAlternativaC.setTextColor(getResources().getColor(R.color.verde));
                        }else if (correta==4){
                            rbAlternativaD.setTextColor(getResources().getColor(R.color.verde));
                        }else if (correta==5){
                            rbAlternativaE.setTextColor(getResources().getColor(R.color.verde));
                        }
                    }
                    emQualQuestaoEsta++;
                    bProxima.setEnabled(false);
                    rbAlternativaA.setEnabled(false);
                    rbAlternativaB.setEnabled(false);
                    rbAlternativaC.setEnabled(false);
                    rbAlternativaD.setEnabled(false);
                    rbAlternativaE.setEnabled(false);

                    //Dá uma pausa dramática antes de avançar, pra dar tempo do usuário ouvir o som e ver a cor
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            //Funções do botão após a última questão
                            if(emQualQuestaoEsta<=quantasQuestoesASortearEApresentar){
                                exibirQuestao(emQualQuestaoEsta);
                            }else if (emQualQuestaoEsta>quantasQuestoesASortearEApresentar){
                                //chama o resultado
                                Log.i("MSGLOG", "Seu resultado foi de: " + pontuacao + " de um total de " + quantasQuestoesASortearEApresentar + " questões.");
                                chamaOResultado();
                            }
                        }
                    }, pausaDramatica);
                }

                //Funções pro primeiro clique no botão
                if(emQualQuestaoEsta==0) {
                    Log.i("MSGLOG", "Leu o onClick, transferindo as questões pra apresentar");
                    //Escolhe quantas questões vão ser sorteadas e apresentadas
                    quantasQuestoesASortearEApresentar = sbQuantQuest.getProgress();
                    Random rSorteador = new Random();

                    for (int questao = 1; questao <= quantasQuestoesASortearEApresentar; questao++) {
                        int numeroSorteado = rSorteador.nextInt(totalQuestoesBD) + 1;
                        questoesSelecionadas[questao] = todasAsQuestoesEmOrdem[numeroSorteado];
                        Log.i("MSGLOG", "A questão " + questao + " recebeu a questão " + numeroSorteado + " do BD.");
                    }

                    //Impressão das questões sorteadas
                    Log.i("MSGLOG", "Início da apresentação");
                    /*for (int questao = 1; questao <= quantasQuestoesASortearEApresentar; questao++) {
                        Log.i("MSGLOG", "");
                        Log.i("MSGLOG", "Número: " + questoesSelecionadas[questao].getnQuestao());
                        Log.i("MSGLOG", "Texto: " + questoesSelecionadas[questao].getTextoDaQuestao());
                        Log.i("MSGLOG", questoesSelecionadas[questao].getTextoAltA());
                        Log.i("MSGLOG", questoesSelecionadas[questao].getTextoAltB());
                        Log.i("MSGLOG", questoesSelecionadas[questao].getTextoAltC());
                        Log.i("MSGLOG", questoesSelecionadas[questao].getTextoAltD());
                        Log.i("MSGLOG", questoesSelecionadas[questao].getTextoAltE());
                        Log.i("MSGLOG", "Correta: " + questoesSelecionadas[questao].getqCorreta());
                    }*/
                    Log.i("MSGLOG", "Fim da apresentação");

                    //Transição do bIniciarTeste pra bProxima
                    bProxima.setText("PRÓXIMA");
                    sbQuantQuest.setVisibility(View.INVISIBLE);
                    tvQuantQuest.setVisibility(View.INVISIBLE);
                    rgAlternativas.setVisibility(View.VISIBLE);
                    tvTextoDaQuestao.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
                    emQualQuestaoEsta++;
                    exibirQuestao(emQualQuestaoEsta);
                }
            }

            private void exibirQuestao(int i) {
                tvTextoDaQuestao.setText(questoesSelecionadas[i].getTextoDaQuestao());
                rbAlternativaA.setText(questoesSelecionadas[i].getTextoAltA());
                rbAlternativaB.setText(questoesSelecionadas[i].getTextoAltB());
                rbAlternativaC.setText(questoesSelecionadas[i].getTextoAltC());
                rbAlternativaD.setText(questoesSelecionadas[i].getTextoAltD());
                rbAlternativaE.setText(questoesSelecionadas[i].getTextoAltE());
                rbAlternativaA.setTextColor(getResources().getColor(R.color.branco));
                rbAlternativaB.setTextColor(getResources().getColor(R.color.branco));
                rbAlternativaC.setTextColor(getResources().getColor(R.color.branco));
                rbAlternativaD.setTextColor(getResources().getColor(R.color.branco));
                rbAlternativaE.setTextColor(getResources().getColor(R.color.branco));
                Log.i("MSGLOG", "Questão " + emQualQuestaoEsta + " exibida.");
                bProxima.setEnabled(true);
                rbAlternativaA.setEnabled(true);
                rbAlternativaB.setEnabled(true);
                rbAlternativaC.setEnabled(true);
                rbAlternativaD.setEnabled(true);
                rbAlternativaE.setEnabled(true);
                rbAlternativaE.setSelected(true);
            }

            private void chamaOResultado(){
                tvTextoDaQuestao.setText("O seu resultado foi de: " + pontuacao + " de " + quantasQuestoesASortearEApresentar + " questões.\n\nEscolha novamente a quantidade de questões e clique em ''REINICIAR TESTE'' para praticar novamente.");
                rgAlternativas.setVisibility(View.INVISIBLE);
                emQualQuestaoEsta=0;
                pontuacao=0;
                sbQuantQuest.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    sbQuantQuest.setProgress(totalQuestoesBD, true);
                }
                tvQuantQuest.setVisibility(View.VISIBLE);
                bProxima.setText("Reiniciar teste");
                bProxima.setEnabled(true);
                rbAlternativaA.setEnabled(true);
                rbAlternativaB.setEnabled(true);
                rbAlternativaC.setEnabled(true);
                rbAlternativaD.setEnabled(true);
                rbAlternativaE.setEnabled(true);
            }
        });
    }
}