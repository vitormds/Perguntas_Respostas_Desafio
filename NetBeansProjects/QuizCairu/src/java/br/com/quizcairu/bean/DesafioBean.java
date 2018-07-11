package br.com.quizcairu.bean;

import br.com.quizcairu.dao.DesafioDao;
import br.com.quizcairu.models.AlunoDesafio;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class DesafioBean {
    
    private static int id_desafiado;
    private int desafiosPendentes = 0;
    private ArrayList<AlunoDesafio> desafiantes;
    private static int idDesafio;
    
    public static Boolean fazerDesafio = false;
    public static Boolean aceitarDesafio = false;
    
    public DesafioBean() throws SQLException, ClassNotFoundException{
        this.desafiosPendentes = new DesafioDao().retornaQtdDesafiosPendentes(AlunoBean.retornaAlunoLogado().getId());
    }
    
    public String fazerDesafio(){
        
        DesafioBean.fazerDesafio = true;
        
        return "mododesafio.xhtml?faces-redirect=true";
    } 
   
    public int getId_desafiado() {
//        if(id_desafiado == 0)
//            DesafioBean.fazerDesafio = false;
        
        return id_desafiado;
    }

    public void setId_desafiado(int id_desafiado) {
        DesafioBean.id_desafiado = id_desafiado;
    }

    public int getDesafiosPendentes() {
        return desafiosPendentes;
    }   

    public ArrayList<AlunoDesafio> getDesafiantes() throws SQLException, ClassNotFoundException {
        this.desafiantes = new DesafioDao().buscarListaDeDesafiantes(AlunoBean.retornaAlunoLogado().getId());
        return desafiantes;
    }
    
    public String recusarDesafio(int idDesafio) throws SQLException, ClassNotFoundException{
        new DesafioDao().deletarDesafioTerminado(idDesafio);
        return "bemvindo.xhtml";
    }
    
    public void setDesafiantes(ArrayList<AlunoDesafio> desafiantes) {
        this.desafiantes = desafiantes;
    }

    public static int getIdDesafio() {
        return idDesafio;
    }

    public static void setIdDesafio(int idDesafio) {
        DesafioBean.idDesafio = idDesafio;
    }
    
    
}
