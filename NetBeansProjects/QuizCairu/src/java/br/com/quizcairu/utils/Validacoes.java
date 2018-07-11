package br.com.quizcairu.utils;

import br.com.quizcairu.dao.ValidacoesDao;
import br.com.quizcairu.models.Aluno;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Validacoes {
    
    ValidacoesDao vdao = new ValidacoesDao();
    
    public Boolean validaAluno(Aluno aluno) throws SQLException, ClassNotFoundException{
        
//        return  !(aluno.getNome().isEmpty()   ||
//                aluno.getSemestre().isEmpty() ||
//                aluno.getMatricula().isEmpty()||
//                aluno.getSenha().isEmpty());

        return vdao.matriculaEhRepetida(aluno.getMatricula()) == 0;
     
    }
    
  

    
}
