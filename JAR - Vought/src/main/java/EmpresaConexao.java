import org.springframework.jdbc.core.JdbcTemplate;

public class EmpresaConexao {

    Conexao conexaoMySQL = new Conexao();
    JdbcTemplate con = conexaoMySQL.getConexaoMySql();



}
