import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Usuario {

    private String nome;
    private ArrayList<Usuario> amigos = new ArrayList<Usuario>();
    private ArrayList<Usuario> solicitacoes = new ArrayList<Usuario>();
    private ArrayList<Chat> chats = new ArrayList<Chat>();
    private ArrayList<Comunidade> comunidades = new ArrayList<Comunidade>();
    private ArrayList<Comunidade> adminComunidades = new ArrayList<Comunidade>();

    public void removerAmigo(Usuario usuario) {

        Iterator<Usuario> iterator = this.getAmigos().iterator();
        while(iterator.hasNext()) {
            Usuario amigo = iterator.next();
            if(amigo == usuario) {
                iterator.remove();
            }
        }
    }

    public void removerSolicitacoes(Usuario usuario) {

        Iterator<Usuario> iterator = this.getSolicitacoes().iterator();
        while(iterator.hasNext()) {
            Usuario amigo = iterator.next();
            if(amigo == usuario) {
                iterator.remove();
            }
        }

    }

    public void removerChats(Usuario usuario) {

        Iterator<Chat> iterator = this.getChats().iterator();
        while(iterator.hasNext()) {
            Chat chat = iterator.next();
            if(chat.getUsuarios().contains(usuario)) {
                iterator.remove();
            }
        }

    }

    public void listarAmigos() {
        for (Usuario amigo : this.amigos) {
            System.out.println("\t" + amigo.getNome());
        }
    }

    public void entrarEmComunidade(Comunidade comunidade) {
        this.getComunidades().add(comunidade);
        comunidade.getMembros().add(this);
        comunidade.getChatComunidade().addUsuarioAoChat(this);
        comunidade.increase();
    }

    public void criarComunidade() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da Comunidade a ser criada: ");
        String nome = scanner.nextLine();
        Comunidade comunidade = Sistema.checarComunidadeExistente(nome);
        if(comunidade == null) {
            comunidade = new Comunidade(nome, this);
            Sistema.getComunidades().add(comunidade);
            this.entrarEmComunidade(comunidade);
            this.getAdminComunidades().add(comunidade);

            System.out.println("Digite uma descrição para sua comunidade: ");
            comunidade.setDescricao(scanner.nextLine());

        } else {
            System.out.println("Nome ja existente, falha ao criar comunidade");
        }

    }

    public void administrarComunidade(Comunidade comunidade) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Digite sua escolha:\n" +
                    "(1) - Remover um mebro\n" +
                    "(2) - Listar membros\n" +
                    "(3) - Alterar nome da Comunidade\n" +
                    "(4) - Alterar descrição da Comunidade\n" +
                    "(outro) - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if(escolha == 1) {
                System.out.print("Digite o nome do usuário que deseja remover:");
                Usuario usuario = Sistema.getUsuarioPeloNome(scanner.nextLine());

                if(usuario != null) {
                    comunidade.removerMembro(usuario);
                    System.out.println("Usuário removido com sucesso!");
                }

            } else if(escolha == 2) {
                comunidade.listarMembros();
            } else if(escolha == 3) {
                System.out.print("Digite o novo nome da comunidade:");
                comunidade.setNomeDaComunidade(scanner.nextLine());
                System.out.println("Alterações feitas com sucesso!");
            } else if(escolha == 4) {
                System.out.println("Digite a nova descrição da comunidade: ");
                comunidade.setDescricao(scanner.nextLine());
                System.out.println("Alterações feitas com sucesso!");
            }
            else {
                break;
            }

        }

    }

    public void visualizarChatDaComunidade() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da comunidade que deseja olhar o chat: ");
        Comunidade comunidade = Sistema.getComunidadePeloNome(scanner.nextLine());
        if(comunidade != null) {
            if(comunidade.getMembros().contains(this)) {
                comunidade.getChatComunidade().getMensagensComunidade();
            } else {
                System.out.println("Você não pertence a essa comunidade.");
            }
        } else {
            System.out.println("Essa comunidade não existe");
        }

    }

    public void visualizarComunidadesPertencentes() {
        System.out.println("Comunidades: ");
        for (Comunidade comunidade : this.getComunidades()) {
            System.out.println("\t" + comunidade.getNomeDaComunidade());
        }
    }

    public void detalhesUsuario() {
        System.out.println("\tNome: " + this.getNome());
        System.out.println("\tAmigos:");
        for (Usuario amigo : this.getAmigos()) {
            System.out.println("\t\t" + amigo.getNome());
        }
    }

    public void mandarSolicitacao(Usuario amigo) {
        if(amigo != this) {
            amigo.getSolicitacoes().add(this);
        }
    }

    public void aceitarSolicitção(String nome) {

        Iterator<Usuario> iterator = this.getSolicitacoes().iterator();
        while(iterator.hasNext()) {
            Usuario amigo = iterator.next();
            if(amigo.getNome().equals(nome)) {
                this.getAmigos().add(amigo);
                amigo.getAmigos().add(this);
                System.out.println(amigo.getNome() + " foi adicionado a sua lista de amigos.");
                iterator.remove();
            }
        }
    }

    public Chat checarChatExistente(Usuario destinatario, Usuario remetente) {

        for (Chat chat : this.getChats()){
            if(chat.getUsuarios().contains(destinatario) && chat.getUsuarios().contains(remetente) && chat.getUsuarios().size() == 2) {
                return chat;
            }
        }

        return null;

    }

    public void enviarMensagemParaComunidade() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da comunidade que deseja enviar a mensagem: ");
        Comunidade comunidade = Sistema.getComunidadePeloNome(scanner.nextLine());

        if(comunidade != null) {
            if(comunidade.getMembros().contains(this)) {
                System.out.println("Digite a mensagem que deseja enviar: ");
                String mensagem = scanner.nextLine();

                comunidade.getChatComunidade().adicionarMensagem(mensagem, this.getNome());

            } else {
                System.out.println("Você não pertence a essa comunidade.");
            }
        } else {
            System.out.println("Essa comunidade não existe.");
        }


    }

    public void enviarMensagem() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do amigo que deseja enviar a mensagem: ");
        String nomeAmigo = scanner.nextLine();
        Usuario destinatario = Sistema.getUsuarioPeloNome(nomeAmigo);
        if(destinatario != null) {
            if(destinatario != this) {
                Chat chat = this.checarChatExistente(destinatario, this);
                if(chat == null) {
                    chat = new Chat();
                    chat.addUsuarioAoChat(destinatario);
                    chat.addUsuarioAoChat(this);
                    this.getChats().add(chat);
                    destinatario.getChats().add(chat);
                }

                System.out.println("Digite a mensagem que deseja enviar: ");
                String mensagem = scanner.nextLine();
                chat.adicionarMensagem(mensagem, this.getNome());
            } else {
                System.out.println("Mensagens para você mesmo são proibidas.");
            }
        } else {
            System.out.println("Este usuário não existe.");
        }
    }

    public void visualizarChats() {

        for(Chat chat : this.getChats()) {
            System.out.println("Chat entre: ");
            for(Usuario usuario : chat.getUsuarios()) {
                System.out.println("\t" + usuario.getNome());
            }
        }

    }

    public void visualizarChatUnico(Usuario usuario) {

        Chat chat = checarChatExistente(this, usuario);

        if(chat != null) {
            System.out.println("Mensagens:");
            for (String mensagem : chat.getMensagems()) {
                System.out.println("\t" + mensagem);
            }
        } else {
            System.out.println("Você não possui um chat com esse usuário.");
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<Usuario> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(ArrayList<Usuario> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public ArrayList<Comunidade> getComunidades() {
        return comunidades;
    }

    public void setComunidades(ArrayList<Comunidade> comunidades) {
        this.comunidades = comunidades;
    }

    public ArrayList<Comunidade> getAdminComunidades() {
        return adminComunidades;
    }

    public void setAdminComunidades(ArrayList<Comunidade> adminComunidades) {
        this.adminComunidades = adminComunidades;
    }
}
