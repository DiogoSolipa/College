import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LeitorDeCartoes l = new LeitorDeCartoes();
        GestaoDoSistema g = new GestaoDoSistema(l);

        int x = 0;

        while(x != -1) // -1 is the terminator
        {
            System.out.println("\nEscolha uma das seguintes opções para continuar");

            System.out.println(" 1 - Importar dados dos utilizadores (inserir: next para introduzir alunos na aula seguinte)");

            System.out.println(" 2 - Justificar Falta");

            System.out.println(" 3 - Mostrar Relatório de Faltas");

            System.out.println(" 4 - Consultar faltas por aluno");

            System.out.println("-1 - Terminar Programa");

            x = sc.nextInt();

            g.parseInput(l, x, g);
        }

    }
}
