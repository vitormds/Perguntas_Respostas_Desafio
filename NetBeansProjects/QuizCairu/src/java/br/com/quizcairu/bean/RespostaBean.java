
package br.com.quizcairu.bean;

import br.com.quizcairu.dao.PerguntaDao;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class RespostaBean {
    
    public static void verificarResposta(String resposta, int pergunta_id) throws SQLException, ClassNotFoundException{
        
        Boolean resposta_correta = new PerguntaDao().verificarResposta(pergunta_id, resposta);
        
        if(resposta_correta == true)
            PontuacaoBean.setAcertos(1);

    }
}
