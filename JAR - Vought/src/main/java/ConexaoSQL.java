import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSQL {

    private JdbcTemplate conexaoSqlServerLocal;

    public ConexaoSQL() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://35.173.69.240:1433;encrypt=false;trustServerCertificate=true;databaseName=Noctoramento;");
        dataSource.setUsername("sa");
        dataSource.setPassword("urubu100");

        // Estabelecer a conexão
        this.conexaoSqlServerLocal = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoSqlServerLocal() {
        return this.conexaoSqlServerLocal;
    }
}
