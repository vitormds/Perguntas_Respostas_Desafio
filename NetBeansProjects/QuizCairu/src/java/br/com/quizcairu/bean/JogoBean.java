package br.com.quizcairu.bean;

import br.com.quizcairu.dao.DesafioDao;
import br.com.quizcairu.models.VencedorDesafio;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class JogoBean {
    
    private static String nomeVencedor;
    
    public String fimDejogo() throws SQLException, ClassNotFoundException{
     
        int acertos_aluno = new PontuacaoBean().getAcertos();
        int aluno_id = AlunoBean.retornaAlunoLogado().getId();
        int desafiado_id = new DesafioBean().getId_desafiado();
        
        if(DesafioBean.fazerDesafio){
            
            new DesafioDao().cadastrarDesafio(aluno_id, acertos_aluno, desafiado_id);
            DesafioBean.fazerDesafio = false;
            
        } else if(DesafioBean.aceitarDesafio){
            
            new DesafioDao().atualizarDesafio(DesafioBean.getIdDesafio(), acertos_aluno);
            int idVencedor = retornaIdVencedor();
            new DesafioDao().inserirIdVencedor(idVencedor, DesafioBean.getIdDesafio());
            new DesafioDao().cadastrarVitoria(idVencedor);
            new DesafioDao().deletarDesafioTerminado(DesafioBean.getIdDesafio());
            
            nomeVencedor = new DesafioDao().buscaNomeVencedor(idVencedor);
            
            PontuacaoBean.setAcertos(0);
            DesafioBean.aceitarDesafio = false;
            
            return "fimdejogodesafio.xhtml?faces-redirect=true";
        }
        else 
            PontuacaoBean.inserirPontuacao();
        
        PontuacaoBean.setAcertos(0);
        
        return "bemvindo.xhtml?faces-redirect=true";
    }   
    
    public String sair(){
        PerguntaBean.setPosPergunta(0);
        PontuacaoBean.setAcertos(0);
        
        return "bemvindo.xhtml?faces-redirect=true";
    }
    
    private int retornaIdVencedor() throws SQLException, ClassNotFoundException{
        VencedorDesafio vencedorDesafio = new DesafioDao().retornaInformacoesDesafio(DesafioBean.getIdDesafio());
            
        if(vencedorDesafio.getAcertos_aluno() > vencedorDesafio.getAcertos_desafiado())
            return vencedorDesafio.getAluno_id();
        else if(vencedorDesafio.getAcertos_desafiado() > vencedorDesafio.getAcertos_aluno())
            return vencedorDesafio.getDesafiado_id();
        return 0; //empate
    }
    
    public String getNomeVencedor() {
        return nomeVencedor;
    }
    
    public boolean ativarBotoes(){
        if(AlunoBean.retornaAlunoLogado().getId() == 1)
            return false;
        
        return true;
    }
    
    public boolean ativarBotaoDesafio() throws SQLException, ClassNotFoundException{
        int desafios = new DesafioBean().getDesafiosPendentes();
        if(AlunoBean.retornaAlunoLogado().getId() == 1 || desafios == 0)
            return false;
        
        return true;
    }
}
