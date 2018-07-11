package br.com.quizcairu.dao;

import br.com.quizcairu.models.Pergunta;
import br.com.quizcairu.utils.DaoUtil;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class PerguntaDao extends DaoUtil{
    public PerguntaDao(){
        super();
    }
    
    

    
      public ArrayList<String> buscarOpcoes(int pergunta_id) throws SQLException, ClassNotFoundException{
        String sql = "SELECT opcao FROM opcoes WHERE pergunta_id = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        statement.setInt(1, pergunta_id);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<String> opcoes = new ArrayList<String>();
        while(rs.next()){
            String opcao;
            opcao = (rs.getString("opcao"));
            opcoes.add(opcao);
        }

        return opcoes;
    }
    
    public ArrayList<Pergunta> buscarPerguntas() throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM perguntas";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
        while(rs.next()){
            Pergunta pergunta = new Pergunta();
            pergunta.setId(rs.getInt("id"));
            pergunta.setPergunta(rs.getString("pergunta"));
            pergunta.setOpcao_correta(rs.getString("opcao_correta"));
            pergunta.setOpcoes(buscarOpcoes(pergunta.getId()));
            perguntas.add(pergunta);
        }
        rs.close();
        fechaTudo();
        return perguntas;
    }
    
  
    
    public Boolean verificarResposta(int pergunta_id, String resposta) throws SQLException, ClassNotFoundException{
        String sql = "SELECT COUNT(*) FROM perguntas WHERE id = ? and opcao_correta = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        
        statement.setInt(1, pergunta_id);
        statement.setString(2, resposta);
        
        ResultSet rs = statement.executeQuery();
        Boolean r = null;
        while(rs.next()){
            r = (rs.getBoolean("COUNT(*)"));
        }
        rs.close();
        fechaTudo();
        return r;
    }

  

    
}
