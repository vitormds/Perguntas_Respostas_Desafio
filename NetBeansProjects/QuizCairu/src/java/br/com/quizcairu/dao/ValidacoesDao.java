package br.com.quizcairu.dao;

import br.com.quizcairu.utils.DaoUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidacoesDao extends DaoUtil{
    public ValidacoesDao(){
        super();
    }
    
    public int matriculaEhRepetida(String matricula) throws SQLException, ClassNotFoundException{
        
        String sql = "SELECT COUNT(*) FROM ALUNOS WHERE MATRICULA = ?";
        
        PreparedStatement statement = super.getPreparedStatement(sql);
        statement.setString(1, matricula);
        
        ResultSet rs = statement.executeQuery();
        int valor = 0;
        while(rs.next()){
            valor = rs.getInt("COUNT(*)");
        }
        
        return valor;
        
    }
}
