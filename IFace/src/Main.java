import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while(true) {
            System.out.println("Escolha a opção:\n" +
                    "(1) - Criar nova conta\n" +
                    "(2) - Visualizar detalhes de uma conta\n" +
                    "(3) - Visualizar todas as contas\n" +
                    "(4) - Remover conta\n" +
                    "(5) - Iniciar Sessão\n" +
                    "(6) - Visualizar lista de comunidades\n" +
                    "(Outro) - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if(escolha == 1) {
                sistema.adicionarConta();
            } else if(escolha == 2) {
                System.out.print("Digite o id do usuário a ser detalhado: ");
                sistema.visualizarDetalhes(scanner.nextInt());
            } else if(escolha == 3) {
                sistema.listarTodasContas();
            } else if(escolha == 4) {
                System.out.print("Digite o nome de usuário a ser excluído: ");
                Usuario usuario = Sistema.getUsuarioPeloNome(scanner.nextLine());
                Sistema.removerConta(usuario);
            } else if(escolha == 5) {
               Usuario user = sistema.iniciarSessao();
               if(user != null) {
                   Sistema.removerConta(user);
               }
            } else if(escolha == 6) {
                System.out.println("Comunidades: ");
                Sistema.listarTodasComunidades();
            }
            else {
                break;
            }
        }

    }
}
