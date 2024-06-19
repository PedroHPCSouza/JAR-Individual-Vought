import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import org.springframework.jdbc.core.JdbcTemplate;

public class InfoNotebook {
    Looca looca = new Looca();
    Sistema sistema = new Sistema();

    // Conexão com o banco mysql:

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoMySql();

    // Conexão com o banco SQL Server:

    ConexaoSQL conexaoSQL = new ConexaoSQL();
    JdbcTemplate conSQL = conexaoSQL.getConexaoSqlServerLocal();

    private Integer id = 0;
    private String sistemaOperacional;
    private String processador;
    private Double capacidadeMaxCpu;
    private Double maxDisco;
    private Double maxMemoriaRam;

    public InfoNotebook() {
    }

    public void capturarInformacoesNotebook(Integer fkNotebook, Integer fkEmpresa){

        String sistemaOperacional = sistema.getSistemaOperacional();
        String processador = looca.getProcessador().getNome();
        double capacidadeMaxCpu = Math.round(looca.getProcessador().getFrequencia().doubleValue());
        double maxDisco = Math.round(looca.getGrupoDeDiscos().getTamanhoTotal().doubleValue() / Math.pow(1024,3));
        double maxMemoriaRam = Math.round((looca.getMemoria().getTotal().doubleValue()) / Math.pow(1024,3));
        Integer fkNotebookInsert = fkNotebook;
        Integer fkEmpresaInsert = fkEmpresa;

        // Insert no mysql Local

        con.update("INSERT INTO InfoNotebook (sistemaOperacional, processador, capacidadeMaxCpu, maxDisco, maxMemoriaRam, fkNotebook, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)",
                sistemaOperacional, processador, capacidadeMaxCpu, maxDisco, maxMemoriaRam, fkNotebookInsert, fkEmpresaInsert);

        // Insert no SQL Server

        conSQL.update("INSERT INTO InfoNotebook (sistemaOperacional, processador, capacidadeMaxCpu, maxDisco, maxMemoriaRam, fkNotebook, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)",
                sistemaOperacional, processador, capacidadeMaxCpu, maxDisco, maxMemoriaRam, fkNotebookInsert, fkEmpresaInsert);

        // Cadastrando os dados:

        setId(id++);
        setSistemaOperacional(sistemaOperacional);
        setProcessador(processador);
        setCapacidadeMaxCpu(capacidadeMaxCpu);
        setMaxDisco(maxDisco);
        setMaxMemoriaRam(maxMemoriaRam);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public Double getCapacidadeMaxCpu() {
        return capacidadeMaxCpu;
    }

    public void setCapacidadeMaxCpu(Double capacidadeMaxCpu) {
        this.capacidadeMaxCpu = capacidadeMaxCpu;
    }

    public Double getMaxDisco() {
        return maxDisco;
    }

    public void setMaxDisco(Double maxDisco) {
        this.maxDisco = maxDisco;
    }

    public Double getMaxMemoriaRam() {
        return maxMemoriaRam;
    }

    public void setMaxMemoriaRam(Double maxMemoriaRam) {
        this.maxMemoriaRam = maxMemoriaRam;
    }
}
