package br.com.quizcairu.dao;

import br.com.quizcairu.models.Aluno;
import br.com.quizcairu.models.AlunoDesafio;
import br.com.quizcairu.models.Desafio;
import br.com.quizcairu.models.VencedorDesafio;
import br.com.quizcairu.utils.DaoUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DesafioDao extends DaoUtil{
    
    public DesafioDao(){
        super();
    }
    
    public void cadastrarDesafio(int aluno_id, int acertos_aluno, int desafiado_id) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO desafios (aluno_id, acertos_aluno, desafiado_id)  VALUES (?, ?, ?)";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, aluno_id);
        statement.setInt(2, acertos_aluno);
        statement.setInt(3, desafiado_id);
        statement.execute();
        statement.close();
        fechaTudo();
    }
    
    public int retornaQtdDesafiosPendentes(int idAlunoLogado) throws SQLException, ClassNotFoundException{
        String sql = "SELECT COUNT(*) FROM DESAFIOS WHERE DESAFIADO_ID = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, idAlunoLogado);
        
        ResultSet rs = statement.executeQuery();
        int qtdDesafiosPendentes = 0;
        while(rs.next()){
            qtdDesafiosPendentes = rs.getInt("COUNT(*)");
        }
        return qtdDesafiosPendentes;
    }
    
    public ArrayList<AlunoDesafio> buscarListaDeDesafiantes(int desafiado_id) throws SQLException, ClassNotFoundException{
        String sql = "SELECT D.ID, NOME, SEMESTRE FROM ALUNOS A, DESAFIOS D WHERE A.ID = D.ALUNO_ID AND D.DESAFIADO_ID = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, desafiado_id);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<AlunoDesafio> alunosDesafio = new ArrayList<AlunoDesafio>();
        while(rs.next()){
            AlunoDesafio alunoDesafio = new AlunoDesafio();
            alunoDesafio.setIdDesafio(rs.getInt("id"));
            alunoDesafio.setNome(rs.getString("nome"));
            alunoDesafio.setSemestre(rs.getString("semestre"));
            alunosDesafio.add(alunoDesafio);
        }
        return alunosDesafio;
    }

    public void atualizarDesafio(int idDesafio, int acertos_aluno) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE DESAFIOS SET ACERTOS_DESAFIADO = ? WHERE ID = ?";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, acertos_aluno);
        statement.setInt(2, idDesafio);
        statement.execute();
        statement.close();
        fechaTudo();
    }
    
    public Desafio buscaDesafio(int idDesafio) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM DESAFIOS WHERE ID = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        statement.setInt(1, idDesafio);

        Desafio desafio = new Desafio();
        desafio.setId(rs.getInt("id"));
        desafio.setAlunoId(rs.getInt("aluno_id"));
        desafio.setAcertosAluno(rs.getInt("acertos_aluno"));
        desafio.setDesafiadoId(rs.getInt("desafiado_id"));
        desafio.setAcertosDesafiado(rs.getInt("acertos_desafiado"));
        
        return desafio;
        
    }

    public void inserirIdVencedor(int idVencedor, int idDesafio) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE DESAFIOS SET VENCEDOR_ID = ? WHERE ID = ?";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, idVencedor);
        statement.setInt(2, idDesafio);
        statement.execute();
        statement.close();
        fechaTudo();
    }

    public String buscaNomeVencedor(int alunoId) throws SQLException, ClassNotFoundException {
        if(alunoId == 0)
            return "Empate";
        else{
            
           String sql = "SELECT NOME FROM ALUNOS WHERE ID = ?";

           PreparedStatement statement = super.getPreparedStatement(sql);
           statement.setInt(1, alunoId);
           ResultSet rs = statement.executeQuery();
           
           String nome = null;
           while(rs.next()){
               nome = rs.getString("NOME");
           }

           return nome;
        }
    }
    
    public VencedorDesafio retornaInformacoesDesafio(int idDesafio) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM DESAFIOS WHERE ID = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, idDesafio);
        
        ResultSet rs = statement.executeQuery();
        
        VencedorDesafio vencedorDesafio = new VencedorDesafio();
        while(rs.next()){
            vencedorDesafio.setId_desafio(rs.getInt("id"));
            vencedorDesafio.setAluno_id(rs.getInt("aluno_id"));
            vencedorDesafio.setAcertos_aluno(rs.getInt("acertos_aluno"));
            vencedorDesafio.setDesafiado_id(rs.getInt("desafiado_id"));
            vencedorDesafio.setAcertos_desafiado(rs.getInt("acertos_desafiado"));
        }
        
        return vencedorDesafio;
    }
    
    public void cadastrarVitoria(int aluno_id) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE ALUNOS SET VITORIAS = VITORIAS + 1 WHERE ID = ?";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, aluno_id);
        statement.execute();
        statement.close();
        fechaTudo();
    }
    
    public void deletarDesafioTerminado(int idDesafio) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM DESAFIOS WHERE ID = ?";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setInt(1, idDesafio);
        statement.execute();
        statement.close();
        fechaTudo();
        
    }
}
