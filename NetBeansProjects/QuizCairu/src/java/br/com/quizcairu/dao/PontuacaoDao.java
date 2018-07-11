package br.com.quizcairu.dao;

import br.com.quizcairu.models.AlunoPontuacao;
import br.com.quizcairu.utils.DaoUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PontuacaoDao extends DaoUtil{
    public PontuacaoDao(){
        super();
    }
    
    public void cadastrarPontuacao(int acertos, int aluno_id) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO pontuacoes (acertos, aluno_id)  VALUES (?,?)";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, acertos);
        statement.setInt(2, aluno_id);
        statement.execute();
        statement.close();
        fechaTudo();
    }
    
    public int retornaUltimoIdCadastrado() throws SQLException, ClassNotFoundException{
        String sql = "SELECT LAST_INSERT_ID() INTO @PONTUACOES";
        PreparedStatement statement = super.getPreparedStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        int ultimoId = 0;
        
        while(rs.next()){
            ultimoId = rs.getInt("LAST_INSERT_ID");
        }
        
        rs.close();
        fechaTudo();
        
        return ultimoId;
    }
    
    public ArrayList<AlunoPontuacao> listarAlunosPontuacoes() throws SQLException, ClassNotFoundException{
//        String sql = "SELECT alunos.NOME, alunos.MATRICULA, alunos.SEMESTRE, pontuacoes.ACERTOS from alunos, "
//                     + "pontuacoes where pontuacoes.aluno_id = alunos.ID order by pontuacoes.ACERTOS desc";
       
        String sql = "SELECT distinct alunos.NOME, alunos.MATRICULA, alunos.SEMESTRE, max(pontuacoes.ACERTOS) as ACERTOS from alunos, \n" +
                   "pontuacoes where pontuacoes.aluno_id = alunos.ID  group by alunos.NOME, alunos.MATRICULA, alunos.SEMESTRE order by ACERTOS desc  ";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<AlunoPontuacao> alunosPontuacoes = new ArrayList<AlunoPontuacao>();
        while(rs.next()){
            AlunoPontuacao aluno = new AlunoPontuacao();
            aluno.setNome(rs.getString("nome"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setSemestre(rs.getString("semestre"));
            aluno.setAcertos(rs.getInt("acertos"));
            alunosPontuacoes.add(aluno);
        }
        rs.close();
        fechaTudo();
        return alunosPontuacoes;
    }
    
//    public ArrayList<AlunoPontuacao> listarAlunosVitorias() throws SQLException, ClassNotFoundException{
//        String sql = "SELECT alunos.NOME, alunos.MATRICULA, alunos.SEMESTRE, pontuacoes.VITORIAS from alunos, "
//                     + "pontuacoes where pontuacoes.aluno_id = alunos.ID order by pontuacoes.VITORIAS desc";
//       
//        PreparedStatement statement = super.getPreparedStatement(sql);
//        
//        ResultSet rs = statement.executeQuery();
//        ArrayList<AlunoPontuacao> alunosPontuacoes = new ArrayList<AlunoPontuacao>();
//        while(rs.next()){
//            AlunoPontuacao aluno = new AlunoPontuacao();
//            aluno.setNome(rs.getString("nome"));
//            aluno.setMatricula(rs.getString("matricula"));
//            aluno.setSemestre(rs.getString("semestre"));
//            aluno.setVitorias(rs.getInt("vitorias"));
//            alunosPontuacoes.add(aluno);
//        }
//        rs.close();
//        fechaTudo();
//        return alunosPontuacoes;
//    }
    
}
