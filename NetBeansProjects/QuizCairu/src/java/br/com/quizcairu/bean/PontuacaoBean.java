package br.com.quizcairu.bean;

import br.com.quizcairu.dao.PontuacaoDao;
import br.com.quizcairu.models.AlunoPontuacao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class PontuacaoBean {
    
    private static Integer acertos = 0;
    //private ArrayList<AlunoPontuacao> pontuacoes;
    
    public static void inserirPontuacao() throws SQLException, ClassNotFoundException{
        
        int aluno_id = AlunoBean.retornaAlunoLogado().getId();
        
        new PontuacaoDao().cadastrarPontuacao(acertos, aluno_id);

    }
    
//    public String ordenarPorAcertos() throws SQLException, ClassNotFoundException{
//        this.pontuacoes = new PontuacaoDao().listarAlunosPontuacoes();
//        return "bemvindo";
//    }
//    
//    public String ordenarPorVitorias() throws SQLException, ClassNotFoundException{
//        this.pontuacoes = new PontuacaoDao().listarAlunosVitorias();
//        return "bemvindo";
//    }
    
    public Integer getAcertos() {
        return acertos;
    }

    public static void setAcertos(Integer acertos) {
        if(acertos == 0) 
            PontuacaoBean.acertos = 0; 
        else
            PontuacaoBean.acertos += acertos; 
    }
    
    public ArrayList<AlunoPontuacao> lista () throws SQLException, ClassNotFoundException{
        
        return new PontuacaoDao().listarAlunosPontuacoes();
    }
}
