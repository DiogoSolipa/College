Relatório da 2ª Parte do Trabalho Prático(MDS)
=============================================================================================
<br>

<p align="center">
  <img src="https://pbs.twimg.com/profile_images/425968415845543936/M_GFf-aJ_400x400.png" alt="Logo Universidade de Évora"/>
</p>

<br>
<br>

* Denis Lapuste, l42616 

* Diogo Solipa, l43071 

* Leonardo Catarro, l43025 

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>



Teste Unitários
=============================================================================================
## Classe User
    * Para esta classe foram testadas as validações dos Nomes, dos Cartões e dos Papeis;
    * Regras usadas em Cada Teste:
        * Nome: O nome de cada User apenas pode conter letras;
        * Cartões: O número do cartao apenas pode conter números;
        * Papeis: Um User apenas pode ser Docente ou Aluno;
        
## Classe Lesson
    * Para esta classe foram testadas as validações da Data e da Hora;
    * Regras usadas em Cada Teste:
        * Hora: A hora de cada aula deve ter o formato "hh:mm";
        * Data: A data de cada aula deve ter o formato "yyyy-MM-dd";

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


Classes
=============================================================================================

<br>

## User
    * Esta classe é usada para representar os Users do Sistema. Os users deste sistema são os alunos e o professor que constam no ficheiro dados.json, fornecido;
    * Cada utilizador contém um Nome, uma Role e um indentificador único, o seu cartão. Todas estas variáveis são encaradas como strings.

    * Esta classe contém os métodos: 
        * isRole(User user) ;
        * isName(User user) ;
        * isCard(User user) ;
        Métodos estes usados para verificação de cada uma das caracteristicas dos utilizadores. Todos este métodos são do tipo boolean.

<br>

## Lesson
    * Nesta classe são representas aulas, aulas estas que estão representadas no ficheiro aulas.json;
      Cada aula é representada por uma Data , no formato yyyu-MM-dd e uma hora, com o formato HH:mm;
      Uma instancia desta classe contém ainda uma LinkedList com os alunos que faltaram a essa aula(LinkedList faltas) e uma lista com os alunos que estiveram presentes(LinkedList presencas) e, ainda, o numero de faltas da aula em questão.

<br>

## LinkedList
    * Nesta classe implementamos a estrutura de dados: LinkedList. Esta estrutura servirá para guardar todos os Users do sistema para futuras alterações, tendo em cada node uma variavel do tipo User;

<br>

## LinkedListLesson
    * Esta LinkedList servirá, tal como o nome indica, para guardar a informação das aulas. Cada lista do tipo LinkedListLesson, conterá, por cada nó uma variável do tipo Lesson;

<br>

## LeitorDeCartões
    * Esta classe foi criada com o intuito de representar o ato de passar o cartão no leitor. Esta ação representa no sistema a presença do aluno, cujo numero de cartão passou no leitor.
    Cada instância desta classe irá ter uma LinkedListLesson e uma LinkedList. A LinkedList;
    * Esta classe contém 5 métodos: 
        * UserDataFileRead(LeitorDeCartoes l) ; 
        * LessonDataFileRead(LeitorDeCartoes l) ;
        * parseUserObjectForLessons(JSONObject user, LinkedListLesson aulas) ; * parseUserObjectForUsers(JSONObject user, LinkedList users) ;
        * lessonsUserInsert(LeitorDeCartoes l) ; 

        Os 2 primeiros métodos são usados para inserção dos dados dos users e dos dados das aulas, respetivamente. Os métodos de parse são usados para fazer a distinção de user para user e de aula para aula(dados dos ficheiros .json).

        O último método espera input de numeros de cartões, registando a presença desse user na aula. A passagem para a aula seguinte é feita através do comando "next" inserido no terminal.

    * Esta classe é a responsável por conter todos dados, a nivél de presenças, faltas, user e aulas de todo o sistema.

<br>

## GestãoDoSistema

    * Esta classe é usada para fazer toda a gestão da informação contida no LeitorDeCartões. Esta classe permite-nos: 
        1-Importar Dados dos alunos;
        2-Justificar faltas;
        3-Verificar faltas por aluno;
        4-Mostrar Relatório de Faltas;
       
    * Importar Dados dos utilizador: o que faz é, a chamada de três métodos da classe LeitorDeCartões, sendo eles: UserDataReadFile , LessonDataReadFile e lessonsUserInsert. Servirá para registar toda a informação das aulas existentes

    * Justificar Falta: Dado um numero de cartão , acedemos ao node corresponde ao user que possui esse cartão e fazemos a decrementação do seu numero de faltas;

    * Verificar Faltas por Aluno: Dado um numero de cartão , vai mostrar no terminal qual o nome desse User e quantas faltas ele tem;

    * Mostrar Relatório de Faltas: Esta opção vai nos mostrar todos os utilizadores e com quantas faltas eles estão.

<br>

## Main
    * Nesta função cria-se uma instância da classe LeitorDeCartoes e uma instância da classe GestaoDoSitema;
    * De seguida, num ciclo que espera inputs no terminal em que damos as indicações do que queremos que o sistema faça em termos da sua gestão;
	* O commando "-1" faz terminar o programa.
