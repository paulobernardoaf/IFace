import java.util.ArrayList;

public class Chat {

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<String> mensagems = new ArrayList<String>();

    public void adicionarMensagem(String mensagem, String remetente) {
        this.getMensagems().add(remetente + ": " + mensagem);
    }

    public void addUsuarioAoChat(Usuario usuario) {
        if(!this.usuarios.contains(usuario)) {
            this.getUsuarios().add(usuario);
        } else {
            System.out.println(usuario.getNome() + " já pertence ao chat.");
        }
    }

    public void getMensagensComunidade() {

        System.out.println("Mensagens:");
        for(String mensagem : this.getMensagems()) {
            System.out.println("\t" + mensagem);
        }

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<String> getMensagems() {
        return mensagems;
    }

}
