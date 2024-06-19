import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate conexaoMySql;
    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Noctoramento");
        dataSource.setUsername("root");
        dataSource.setPassword("urubu100");

        conexaoMySql = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoMySql() {
        return conexaoMySql;
    }

}
