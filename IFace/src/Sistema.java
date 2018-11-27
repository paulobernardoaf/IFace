import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    private static ArrayList<Conta> contas = new ArrayList<Conta>();
    private static ArrayList<Comunidade> comunidades = new ArrayList<Comunidade>();
    private static int totalDeContas = 0;

    public static Comunidade checarComunidadeExistente(String nome) {

        for (Comunidade comunidade : Sistema.getComunidades()){
            if(comunidade.getNomeDaComunidade().equals(nome)) {
                return comunidade;
            }
        }
        return null;
    }

    public static void listarTodasComunidades(){
        for(Comunidade comunidade : Sistema.getComunidades()) {
            System.out.println("\t" + comunidade.getNomeDaComunidade() + " -- " + comunidade.getQuantidadeDeMembros());
        }
    }

    public static boolean indisponibilidadeLogin(String login) {

        for (Conta conta : Sistema.getContas()) {
            if(conta.getLogin().equals(login)) {
                return true;
            }
        }

        return false;

    }

    public static boolean indisponibilidadeUsuario(String usuario) {

        for (Conta conta : Sistema.getContas()) {
            if(conta.getUser().getNome().equals(usuario)) {
                return true;
            }
        }

        return false;

    }

    public void adicionarConta() {
        int id = Integer.parseInt(Sistema.getTotalDeContas());
        Conta novaConta = new Conta(id);
        this.contas.add(novaConta);
        Sistema.totalDeContas++;
    }

    public void visualizarDetalhes(int id) {
        for(Conta conta : this.contas) {
            if(conta.getId() == id) {
                conta.detalhesDaConta();
                return;
            }
        }

        System.out.println("Conta não existente no sistema.");
    }

    public static void apagarComunidade(Comunidade comunidade) {

        for (Conta conta : Sistema.getContas()) {
            conta.getUser().getAdminComunidades().remove(comunidade);
            conta.getUser().getComunidades().remove(comunidade);
        }

        Sistema.getComunidades().remove(comunidade);

    }

    public static void removerConta(Usuario usuario) {

        Iterator<Comunidade> iterator = Sistema.getComunidades().iterator();
        while(iterator.hasNext()) {
            iterator.next().removerMembro(usuario);
        }

        Iterator<Conta> iteratorConta = Sistema.getContas().iterator();
        while(iteratorConta.hasNext()) {
            Conta iter = iteratorConta.next();
            iter.getUser().removerAmigo(usuario);
            iter.getUser().removerSolicitacoes(usuario);
            iter.getUser().removerChats(usuario);
        }

        iteratorConta = Sistema.getContas().iterator();
        while(iteratorConta.hasNext()) {
            Conta iterUsuario = iteratorConta.next();
            if(iterUsuario.getUser() == usuario) {
                iteratorConta.remove();
                return;
            }
        }

        System.out.println("Conta não existente no sistema.");
    }

    public static Usuario getUsuarioPeloNome(String nome) {

        for (Conta conta: Sistema.getContas()) {
            if(conta.getUser().getNome().equals(nome)) {
                return conta.getUser();
            }
        }

        return null;
    }

    public static Comunidade getComunidadePeloNome(String nome) {

        for (Comunidade comunidade : Sistema.getComunidades()) {
            if(comunidade.getNomeDaComunidade().equals(nome)) {
                return comunidade;
            }
        }

        return null;

    }

    public void listarTodasContas(){
        for(Conta conta : this.contas) {
            conta.detalhesDaConta();
        }
    }

    public Usuario iniciarSessao() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        for (Conta conta : this.contas) {
            if(conta.getLogin().equals(login) && conta.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                Sessao sessao = new Sessao(conta);
                return sessao.start();
            }
        }

        System.out.println("Login ou senha incorretos!");
        return null;
    }

    public static ArrayList<Conta> getContas() {
        return contas;
    }

    public static  String getTotalDeContas() {
        return String.valueOf(totalDeContas);
    }

    public static ArrayList<Comunidade> getComunidades() {
        return comunidades;
    }

}
