import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class NotebookConexao {

    // Conexao conexaoMySQL = new Conexao();
    // JdbcTemplate con = conexaoMySQL.getConexaoMySql();
    ConexaoSQL conexaoSQL = new ConexaoSQL();
    JdbcTemplate con = conexaoSQL.getConexaoSqlServerLocal();

    public Notebook cadastrarNotebook(Integer idNotebook){

        String sql = "SELECT * FROM Notebook WHERE idNotebook = ?;";

        try {
            Notebook notebook = con.queryForObject(sql, new BeanPropertyRowMapper<>(Notebook.class), idNotebook);
            return notebook;
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
