package br.com.quizcairu.models;

import java.util.ArrayList;

public class Pergunta {
    private Integer id;
    private String pergunta;
    private String opcao_correta;
    private ArrayList<String> opcoes;
    private Integer pergunta_id;

    public Integer getPergunta_id() {
        return pergunta_id;
    }

    public void setPergunta_id(Integer pergunta_id) {
        this.pergunta_id = pergunta_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void inserirOpcoes(ArrayList<String> opcoes, int pergunta_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
