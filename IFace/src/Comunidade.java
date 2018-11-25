import java.util.ArrayList;
import java.util.Iterator;

public class Comunidade {

    private String nomeDaComunidade;
    private String descricao;
    private Usuario admin;
    private ArrayList<Usuario> membros = new ArrayList<Usuario>();
    private Chat chatComunidade = new Chat();
    private int quantidadeDeMembros = 0;

    public Comunidade(String nome, Usuario admin) {
        this.setNomeDaComunidade(nome);
        this.setAdmin(admin);
    }

    public void removerMembro(Usuario usuario) {

        if(this.admin == usuario) {
            this.setAdmin(membros.get(this.getQuantidadeDeMembros()-1));
        }

        Iterator<Usuario> iterator = this.getMembros().iterator();
        while (iterator.hasNext()) {
            Usuario iteratorUsuario = iterator.next();
            if(iteratorUsuario == usuario) {
                iteratorUsuario.getComunidades().remove(this);
                iterator.remove();
            }
        }

        this.decrease();


    }

    public void listarMembros() {

        for (Usuario usuario : this.getMembros()) {
            System.out.println("\t" + usuario.getNome());
        }

    }

    public String getNomeDaComunidade() {
        return nomeDaComunidade;
    }

    public void setNomeDaComunidade(String nomeDaComunidade) {
        this.nomeDaComunidade = nomeDaComunidade;
    }

    public ArrayList<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(ArrayList<Usuario> membros) {
        this.membros = membros;
    }

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

    public void increase() {
        this.quantidadeDeMembros++;
}

    public void decrease() {
        this.quantidadeDeMembros--;
    }

    public int getQuantidadeDeMembros() {
        return quantidadeDeMembros;
    }

    public void setQuantidadeDeMembros(int quantidadeDeMembros) {
        this.quantidadeDeMembros = quantidadeDeMembros;
    }

    public Chat getChatComunidade() {
        return chatComunidade;
    }

    public void setChatComunidade(Chat chatComunidade) {
        this.chatComunidade = chatComunidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
