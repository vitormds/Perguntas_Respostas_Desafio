package br.com.quizcairu.bean;

import static br.com.quizcairu.bean.DesafioBean.aceitarDesafio;
import br.com.quizcairu.dao.PerguntaDao;
import br.com.quizcairu.models.Pergunta;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Random;

@ManagedBean
@SessionScoped
public class PerguntaBean {

    private ArrayList<Pergunta> perguntas;
    private static Integer posPergunta = 0;
    private String pergunta = null;
    private String opcao_correta = null;

    private ArrayList<String> opcoes;
    private int pergunta_id;
    private String resposta;

    public PerguntaBean() throws SQLException, ClassNotFoundException {
        this.perguntas = getPerguntas();
        
    }
    
    public String proxPergunta() throws SQLException, ClassNotFoundException {
        RespostaBean.verificarResposta(resposta, pergunta_id);
        if (posPergunta < perguntas.size()) {

            return mostrarPergunta();
        } else {
            PerguntaBean.posPergunta = 0;
            return "fimjogo.xhtml?faces-redirect=true";
        }
    }

    public String mostrarPergunta() throws SQLException, ClassNotFoundException {

        Pergunta p = perguntas.get(posPergunta);

        this.setPergunta_id(p.getId());
        this.setPergunta(p.getPergunta());
        this.setOpcoes(p.getOpcoes());

        posPergunta++;

        return "principal.xhtml?faces-redirect=true";
    }

    public String mostrarPergunta(int idDesafio) throws SQLException, ClassNotFoundException {
        
        aceitarDesafio = true;
        
        DesafioBean.setIdDesafio(idDesafio);
        
        Pergunta p = perguntas.get(posPergunta);

        this.setPergunta_id(p.getId());
        this.setPergunta(p.getPergunta());
        this.setOpcoes(p.getOpcoes());

        posPergunta++;

        return "principal.xhtml?faces-redirect=true";
    }
        
    public static void embaralhar(ArrayList<Pergunta> perguntas) {
		
        Random random = new Random();

        for (int i=0; i < (perguntas.size()-1); i++) {

                int j = random.nextInt(perguntas.size()); 

                Pergunta p = perguntas.get(i);
                perguntas.set(i, perguntas.get(j));
                perguntas.set(j, p);
        }
		
    }
    
    
    public ArrayList<Pergunta> getPerguntas() throws SQLException, ClassNotFoundException {
        this.perguntas = new PerguntaDao().buscarPerguntas();
        embaralhar(perguntas);
        //perguntas = sortearPerguntas();
        return perguntas;
    }

    public void setPerguntas(ArrayList<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
      public String getOpcao_correta() {
        return opcao_correta;
    }
      
    public void setOpcao_correta(String opcao_correta) {
        this.opcao_correta = opcao_correta;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<String> opcoes) {
        this.opcoes = opcoes;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getPergunta_id() {
        return pergunta_id;
    }

    public void setPergunta_id(int pergunta_id) {
        this.pergunta_id = pergunta_id;
    }

    public static void setPosPergunta(Integer posPergunta) {
        PerguntaBean.posPergunta = posPergunta;
    }

    public static Integer getPosPergunta() {
        return posPergunta;
    }
    
    
}
