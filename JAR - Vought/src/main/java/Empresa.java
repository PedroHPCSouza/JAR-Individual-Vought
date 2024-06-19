import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Empresa {

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoMySql();

    private Integer idEmpresa;
    private String razaoSocial;
    private String cnpjEmpresa;
    private String email;
    private String senha;
    private Parametros parametros;
    private List<Funcionario> funcionarios;

    public Empresa(Integer idEmpresa, String razaoSocial, String cnpjEmpresa, String email, String senha) {
        this.idEmpresa = idEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpjEmpresa = cnpjEmpresa;
        this.email = email;
        this.senha = senha;
    }

    public Empresa(Conexao conexao, JdbcTemplate con, Integer idEmpresa, String razaoSocial, String cnpjEmpresa, String email, String senha, Parametros parametros, List<Funcionario> funcionarios) {
        this.conexao = conexao;
        this.con = con;
        this.idEmpresa = idEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpjEmpresa = cnpjEmpresa;
        this.email = email;
        this.senha = senha;
        this.parametros = parametros;
        this.funcionarios = funcionarios;
    }

    public Empresa() {
    }

    public void insertEmpresa(){
        con.update("INSERT INTO Empresa (razaoSocial, cnpjEmpresa, email, senha) VALUES (?, ?, ?, ?);",
                razaoSocial, cnpjEmpresa, email, senha);
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpjEmpresa='" + cnpjEmpresa + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
