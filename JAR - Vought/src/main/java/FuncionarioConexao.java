import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class FuncionarioConexao {

    // Conexao conexaoMySQL = new Conexao();
    // JdbcTemplate con = conexaoMySQL.getConexaoMySql();

    public Integer contarUsuarioExistente(String email){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        String sql = "SELECT COUNT(*) FROM Usuario WHERE emailUsuario = ?;";

        try {
            Integer countLocal = con.queryForObject(sql, Integer.class, email);
            return countLocal;
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

    public Integer capturarIdFuncionario(String email){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        String sql = "SELECT idUsuario FROM Usuario WHERE emailUsuario = ?;";

        try {
            Integer idFuncionario = con.queryForObject(sql, Integer.class, email);
            return idFuncionario;
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

    public Integer capturarIdNotebook(Integer idFuncionario, Integer idEmpresa){

        ConexaoSQL conexaoSQL = new ConexaoSQL();
        JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

        String sql = "SELECT fkNotebook FROM Alocacao WHERE fkUsuario = ? AND fkEmpresaUsuario = ?;";

        try {
            Integer idNotebook = con.queryForObject(sql, Integer.class, idFuncionario, idEmpresa);
            return idNotebook;
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

}
