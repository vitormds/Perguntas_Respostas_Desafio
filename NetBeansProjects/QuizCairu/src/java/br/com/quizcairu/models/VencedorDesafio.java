package br.com.quizcairu.models;

public class VencedorDesafio {
    private int id_desafio;
    private int aluno_id;
    private int acertos_aluno;
    private int desafiado_id;
    private int acertos_desafiado;

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public int getAcertos_aluno() {
        return acertos_aluno;
    }

    public void setAcertos_aluno(int acertos_aluno) {
        this.acertos_aluno = acertos_aluno;
    }

    public int getDesafiado_id() {
        return desafiado_id;
    }

    public void setDesafiado_id(int desafiado_id) {
        this.desafiado_id = desafiado_id;
    }

    public int getAcertos_desafiado() {
        return acertos_desafiado;
    }

    public void setAcertos_desafiado(int acertos_desafiado) {
        this.acertos_desafiado = acertos_desafiado;
    }

    public int getId_desafio() {
        return id_desafio;
    }

    public void setId_desafio(int id_desafio) {
        this.id_desafio = id_desafio;
    }
    
    
}
