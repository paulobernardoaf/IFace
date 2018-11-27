import java.util.InputMismatchException;
import java.util.Scanner;

public class Conta {

    private Usuario user = new Usuario();
    private String login;
    private String senha;
    private int id;

    public Conta(int id) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        while(Sistema.indisponibilidadeLogin(login)) {
            System.out.println("Login ja esta em uso, escolha outro:");
            login = scanner.nextLine();
        }
        this.setLogin(login);
        System.out.print("Digite a senha: ");
        this.setSenha(scanner.nextLine());
        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();
        while (Sistema.indisponibilidadeUsuario(usuario)) {
            System.out.println("Nome de usuario ja esta em uso, escolha outro:");
            usuario = scanner.nextLine();
        }
        this.getUser().setNome(usuario);
        this.setId(id);

    }

    public void detalhesDaConta() {
        System.out.println("Id: " + this.getId());
        System.out.println("Login: " + this.getLogin());
        System.out.println("Senha: " + this.getSenha());
        System.out.println("Detalhes do usuário:");
        this.user.detalhesUsuario();
        System.out.println();
    }

    public void editarConta(){

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Escolha o que deseja alterar:\n" +
                    "(1) - Senha\n" +
                    "(2) - Login\n" +
                    "(3) - Nome de Usuário\n" +
                    "(Outro) - Voltar para sessão");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if(escolha == 1) {
                System.out.print("Digite a nova senha: ");

                try {
                    String str = scanner.nextLine();
                    this.setSenha(str);
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Entrada inválida." + e.getMessage());
                }

            } else if(escolha == 2) {
                System.out.print("Digite o novo login: ");
                this.setLogin(scanner.nextLine());
            } else if(escolha == 3) {
                System.out.print("Digite o novo nome de usuário: ");
                this.getUser().setNome(scanner.nextLine());
            } else {
                return;
            }

            System.out.println("Alterações realizadas.");

        }
    }

    public Usuario getUser() {
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
