import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.sql.SQLException;

public class SuporteConexao {

    public void executarQuery(JdbcTemplate jdbcTemplate, String query, Class<?> clazz){
        try{
            jdbcTemplate.execute(query);
        } catch (CannotGetJdbcConnectionException e){
            System.err.println("Erro de conexão com o banco de dados ao executar query: " + e.getMessage());
        }
    }

    public Integer contarUsuarioExistente(String email, String senha){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        if (con == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }

        String sql = "SELECT COUNT(*) FROM Usuario WHERE emailUsuario = ? AND senhaUsuario = ?;";

        try {
            Integer countLocal = con.queryForObject(sql, Integer.class, email, senha);
            return countLocal;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public Integer capturarIdEmpresa(String email, String senha){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        if (con == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }

        String sql = "SELECT fkEmpresa FROM Usuario WHERE emailUsuario = ? AND senhaUsuario = ?;";

        try {
            Integer idEmpresa = con.queryForObject(sql, Integer.class, email, senha);
            return idEmpresa;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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

    public Empresa cadastrarEmpresa(Integer idEmpresa){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        String sql = "SELECT * FROM Empresa WHERE idEmpresa = ?;";

        try {
            Empresa empresa = con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class), idEmpresa);
            return empresa;
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
