import java.util.Scanner;

public class Sessao {

    private Conta conta;

    public Sessao(Conta conta) {
        this.conta = conta;
    }

    public void start() {
        System.out.println("Bem vindo " + this.conta.getUser().getNome());

        while(true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Escolha sua operação:\n" +
                    "(1)  - Editar perfil\n" +
                    "(2)  - Adicionar Amigo\n" +
                    "(3)  - Visualizar solicitações de amizades pendentes [" + this.conta.getUser().getSolicitacoes().size() + "]\n" +
                    "(4)  - Aceitar solicitação de amizade pendente\n" +
                    "(5)  - Chats\n" +
                    "(6)  - Visualizar chat com usuario especifico\n" +
                    "(7)  - Enviar mensagem para um usuário\n" +
                    "(8)  - Visualizar comunidades pertencentes\n" +
                    "(9)  - Criar nova comunidade\n" +
                    "(10) - Entrar em uma comunidade\n" +
                    "(11) - Visualizar amigos\n" +
                    "(12) - Visualizar detalhes da conta\n" +
                    "(13) - Administrar suas comunidades [" + this.conta.getUser().getAdminComunidades().size()  + "]\n" +
                    "(14) - Visualizar chat da comunidade\n" +
                    "(15) - Enviar mensagem para comunidade\n" +
                    "(Outro) - Sair da sessão");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if(escolha == 1) {
                this.conta.editarConta();
            } else if(escolha == 2) {
                System.out.print("Digite o nome de usuário:");
                String nome = scanner.nextLine();
                Usuario amigo = Sistema.getUsuarioPeloNome(nome);
                if(amigo == null) {
                    System.out.println("Usuário não encontrado.");
                } else {
                    this.conta.getUser().mandarSolicitacao(amigo);
                    System.out.println("Solicitação de amizade enviada.");
                }
            } else if(escolha == 3) {
                for (Usuario usuario : this.conta.getUser().getSolicitacoes()) {
                    System.out.println("\t" + usuario.getNome());
                }
            } else if(escolha == 4) {
                System.out.print("Digite o nome de usuário do amigo a ser aceito: ");
                Usuario amigo = Sistema.getUsuarioPeloNome(scanner.nextLine());
                if(amigo == null) {
                    System.out.println("Usuário não encontrado.");
                } else {
                    this.conta.getUser().aceitarSolicitção(amigo.getNome());
                }
            } else if(escolha == 5) {
                this.conta.getUser().visualizarChats();
            } else if(escolha == 6) {
                System.out.print("Digite o nome de usuário a ser buscado: ");
                Usuario usuario =  Sistema.getUsuarioPeloNome(scanner.nextLine());
                if(usuario != null) {
                    this.conta.getUser().visualizarChatUnico(usuario);
                } else {
                    System.out.println("Usuário não existe.");
                }
            } else if(escolha == 7) {
                this.conta.getUser().enviarMensagem();
            } else if(escolha == 8) {
                this.conta.getUser().visualizarComunidadesPertencentes();
            } else if(escolha == 9) {
                this.conta.getUser().criarComunidade();
            } else if(escolha == 10) {
                System.out.println("Comunidades existentes:");
                Sistema.listarTodasComunidades();
                System.out.print("Digite o nome da comunidade: ");
                Comunidade comunidade = Sistema.checarComunidadeExistente(scanner.nextLine());
                if(comunidade != null) {
                    this.conta.getUser().entrarEmComunidade(comunidade);
                } else {
                    System.out.println("Comunidade não existente.");
                }
            } else if(escolha == 11) {
                System.out.println("Amigos:");
                this.conta.getUser().listarAmigos();
            } else if(escolha == 12) {
                this.conta.detalhesDaConta();
            } else if(escolha == 13) {
                System.out.print("Digite o nome da sua comunidade que deseja gerenciar:");
                Comunidade comunidade = Sistema.getComunidadePeloNome(scanner.nextLine());
                if(comunidade != null) {
                    if(this.conta.getUser().getAdminComunidades().contains(comunidade)) {
                        this.conta.getUser().administrarComunidade(comunidade);
                    } else {
                        System.out.println("Você não tem permissão para gerenciar essa comunidade.");
                    }
                } else {
                    System.out.println("Essa comunidade não existe.");
                }

            } else if(escolha == 14) {
                this.conta.getUser().visualizarChatDaComunidade();
            } else if(escolha == 15) {
                this.conta.getUser().enviarMensagemParaComunidade();
            }
            else {
                return;
            }

        }

    }



}
