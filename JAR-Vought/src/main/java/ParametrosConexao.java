import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;


public class ParametrosConexao {

    // Conexao conexaoMySQL = new Conexao();

    // JdbcTemplate con = conexaoMySQL.getConexaoMySql();
    ConexaoSQL conexaoSQL = new ConexaoSQL();
    JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

    public Parametros capturarParametros(Integer fkEmpresa){

        String sql = "SELECT * FROM Parametros WHERE fkEmpresa = ?;";

        try {
            Parametros parametros = con.queryForObject(sql, new BeanPropertyRowMapper<>(Parametros.class), fkEmpresa);
            return parametros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null){
                try{
                    con.getDataSource().getConnection().close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
