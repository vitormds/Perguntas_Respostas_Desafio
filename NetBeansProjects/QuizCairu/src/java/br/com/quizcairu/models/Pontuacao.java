package br.com.quizcairu.models;

public class Pontuacao {
    private Integer id;
    private Integer aluno_id;
    private Integer acertos;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
      this.id = id;
   }

    public Integer getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Integer aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }    
}
