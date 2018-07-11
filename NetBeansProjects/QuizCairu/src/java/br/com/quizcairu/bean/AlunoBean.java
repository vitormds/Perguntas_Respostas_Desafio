package br.com.quizcairu.bean;

import br.com.quizcairu.dao.AlunoDao;
import br.com.quizcairu.dao.PontuacaoDao;
import br.com.quizcairu.models.Aluno;
import br.com.quizcairu.models.AlunoPontuacao;
import br.com.quizcairu.utils.Validacoes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@ManagedBean
public class AlunoBean {

    private static Aluno aluno = null;
    private final AlunoDao alunoDao = new AlunoDao();

    private Integer id;
    private String nome;
    private String senha;
    private String matricula;
    private String email;
    private String semestre;
    private ArrayList<AlunoPontuacao> pontuacoes;

    public AlunoBean() throws SQLException, ClassNotFoundException {
        this.pontuacoes = new PontuacaoDao().listarAlunosPontuacoes();
    }

    public String adicionarAluno() throws SQLException, ClassNotFoundException {
        aluno = new Aluno();

        aluno.setNome(nome);
        aluno.setSenha(senha);
        aluno.setMatricula(matricula);
        aluno.setEmail(email);
        aluno.setSemestre(semestre);
        aluno.setVitorias(0);

        if(new Validacoes().validaAluno(aluno)) {
            alunoDao.cadastrarAluno(aluno);
            return "index.xhtml?faces-redirect=true";
        }
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
         ,"Matrícula já cadastrada: "+ "","Solicitar matrícula válida"));
       
    
       return "";
          
    }
    

    public String autenticarAluno() throws SQLException, ClassNotFoundException {
        aluno = new Aluno();
        ArrayList<Aluno> alunos = alunoDao.buscarAlunoPorMatriculaESenha(matricula, senha);
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula) && aluno.getSenha().equals(senha)) {

                this.aluno.setId(aluno.getId());
                this.aluno.setNome(aluno.getNome());
                this.aluno.setMatricula(aluno.getMatricula());
                this.aluno.setSenha(aluno.getSenha());
                this.aluno.setEmail(aluno.getEmail());
                this.aluno.setSemestre(aluno.getSemestre());

                return "bemvindo.xhtml?faces-redirect=true";
            }
        } 
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
         ,"Matrícula ou senha incorreta"+ "",""));
        return "";
    }

    public String enviarEmail() throws SQLException, EmailException, ClassNotFoundException{

        aluno = new Aluno();
        

            aluno = alunoDao.buscarAlunoPorEmail(email);
            if(aluno.getEmail().equals(email)) {
          this.aluno.setId(aluno.getId());
            this.aluno.setNome(aluno.getNome());
            this.aluno.setMatricula(aluno.getMatricula());
            this.aluno.setEmail(aluno.getEmail());
            this.aluno.setSemestre(aluno.getSemestre());
            this.aluno.setSenha(aluno.getSenha());
       
        // for(Aluno aluno: alunos);
        
        
        
            String para = aluno.getEmail();
        String senhausu = aluno.getSenha();
        String nome = aluno.getNome();
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("quizcairu@gmail.com", "vitorvitor"));
        email.setSSLOnConnect(true);
        email.setFrom("Teste@gmail.com");
        email.setSubject("QuizCairu, esqueci senha.");
        email.setMsg("Olá, "+ nome+ " sua senha é: " +senhausu + ". Bons estudos!");
        email.addTo(para);
        email.send();
             
    
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
       ,"E-mail enviado com sucesso!"+ "",""));
            return "index.xhtml";
        }
            
      return "";
    }
 
 


    public String sair() {
        this.aluno = null;

        return "index.xhtml?faces-redirect=true";
    }

    public List<SelectItem> getAlunosComboBox() throws SQLException, ClassNotFoundException {

        List<Aluno> alunos = new AlunoDao().buscarTodos();

        List<SelectItem> itens = new ArrayList<SelectItem>(alunos.size());

        for (Aluno a : alunos) {
            if (a.getId() != AlunoBean.retornaAlunoLogado().getId()) {
                itens.add(new SelectItem(a.getId(), a.getNome()));
            }
        }

        return itens;
    }

    public List<SelectItem> getSemestresComboBox() {
        ArrayList<String> semestres = new ArrayList<String>();

        semestres.add("1");
        semestres.add("2");
        semestres.add("3");
        semestres.add("4");
        semestres.add("5");

        List<SelectItem> itens = new ArrayList<SelectItem>(semestres.size());

        for (String s : semestres) {
            itens.add(new SelectItem(s, s));
        }

        return itens;

    }

    public static Aluno retornaAlunoLogado() {
        return AlunoBean.aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public ArrayList<AlunoPontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(ArrayList<AlunoPontuacao> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    public ArrayList<Aluno> getAlunosComVitorias() throws SQLException, ClassNotFoundException {
        ArrayList<Aluno> alunos;
        alunos = new AlunoDao().buscarTodos();

        ArrayList<Aluno> alunosComVitorias = new ArrayList<Aluno>();

        for (Aluno a : alunos) {
            if (a.getVitorias() > 0) {
                alunosComVitorias.add(a);
            }
        }

        return alunosComVitorias;
    }
}
