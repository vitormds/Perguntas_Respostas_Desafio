package br.com.quizcairu.dao;

import br.com.quizcairu.models.Aluno;
import br.com.quizcairu.utils.DaoUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoDao extends DaoUtil {
    
    public AlunoDao(){
        super();
    }
    
    public void cadastrarAluno(Aluno aluno) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO alunos (nome, matricula, senha,email,semestre, vitorias)  VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setString(1, aluno.getNome());
        statement.setString(2, aluno.getMatricula());
        statement.setString(3, aluno.getSenha());
        statement.setString(4, aluno.getEmail());
        statement.setString(5, aluno.getSemestre());
        statement.setInt(6, aluno.getVitorias());
        statement.execute();
        statement.close();
        fechaTudo();
    }
    
    public ArrayList<Aluno> buscarAlunoPorMatriculaESenha(String matricula, String cpf) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM alunos WHERE matricula = ? and senha = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setString(1, matricula);
        statement.setString(2, cpf);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        while(rs.next()){
            Aluno aluno = new Aluno();
            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setSenha(rs.getString("senha"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setSemestre(rs.getString("semestre"));
            alunos.add(aluno);
        }
         rs.close();
        fechaTudo();
        return alunos;
    }
    
    public ArrayList<Aluno> buscarTodos() throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM alunos where id <> 1 ORDER BY VITORIAS DESC";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        while(rs.next()){
            
            Aluno aluno = new Aluno();
            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setSenha(rs.getString("senha"));
            aluno.setEmail(rs.getString("email"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setSemestre(rs.getString("semestre"));
            aluno.setVitorias(rs.getInt("vitorias"));
            alunos.add(aluno);
        }
         rs.close();
        fechaTudo();
        return alunos;
    }
    public Aluno buscarAlunoPorEmail(String email) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM alunos WHERE email=?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setString(1, email);
     
        
        ResultSet rs = statement.executeQuery();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno aluno = new Aluno();
        while(rs.next()){
            
            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
        
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setEmail(rs.getString("email"));
            aluno.setSemestre(rs.getString("semestre"));
            aluno.setSenha(rs.getString("senha"));
            alunos.add(aluno);
        }
         rs.close();
        fechaTudo();
        return aluno;
    }
}

