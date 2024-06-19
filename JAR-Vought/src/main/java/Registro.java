import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;


public class Registro {

    // Conexão com o banco mysql:

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoMySql();

    // Conexão com o banco SQL Server:

    ConexaoSQL conexaoSQL = new ConexaoSQL();
    JdbcTemplate conSQL = conexaoSQL.getConexaoSqlServerLocal();

    Looca looca = new Looca();
    Sistema sistema = new Sistema();
    LocalDateTime dataHora = LocalDateTime.now();

    private Integer id;
    private Double usoCpu;
    private Double usoDisco;
    private String tempoAtividadeDisco;
    private Double usoMemoriaRam;
    private Integer qtdJanelasEmUso;
    private LocalDateTime dtHoraCaptura;

    public Registro() {
    }

    public void capturarDados(Integer fkNotebook, Integer fkEmpresa){

        double usoCpu = Math.round(looca.getProcessador().getUso()); // "%.0f".formatted(looca.getProcessador().getUso());
        double usoDisco = 0.0;
        String tempoAtividadeDisco = (Conversor.formatarSegundosDecorridos(sistema.getTempoDeAtividade()));
        double usoMemoriaRam = Math.round(Double.valueOf(looca.getMemoria().getEmUso())/ Math.pow(1024,3)); //
        Integer qtdJanelasEmUso = looca.getGrupoDeJanelas().getTotalJanelasVisiveis();
        LocalDateTime dtHoraCaptura = dataHora;
        Integer fkNotebookInsert = fkNotebook;
        Integer fkEmpresaInsert = fkEmpresa;

        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

        for (int i = 0; i < volumes.size(); i++){
            Double totalMemoria = volumes.get(i).getTotal() / Math.pow(1024,3);
            Double discoDisponivel = volumes.get(i).getDisponivel() / Math.pow(1024,3);

            usoDisco = Math.round(((((totalMemoria - discoDisponivel) * 100) / totalMemoria)));

        }

        // Insert no mysql local

        con.update("INSERT INTO RegistroUsoNotebook (usoCpu, usoDisco, tempoAtividadeDisco, usoMemoriaRam, qtdJanelasEmUso, dtHoraCaptura, fkNotebook, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                usoCpu, usoDisco, tempoAtividadeDisco, usoMemoriaRam, qtdJanelasEmUso,dtHoraCaptura, fkNotebookInsert, fkEmpresaInsert);

        // Insert no SQL Server

        conSQL.update("INSERT INTO RegistroUsoNotebook (usoCpu, usoDisco, tempoAtividadeDisco, usoMemoriaRam, qtdJanelasEmUso, dtHoraCaptura, fkNotebook, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                usoCpu, usoDisco, tempoAtividadeDisco, usoMemoriaRam, qtdJanelasEmUso,dtHoraCaptura, fkNotebookInsert, fkEmpresaInsert);

        // Guardando dados no objeto:

        setUsoCpu(usoCpu);
        setUsoDisco(usoDisco);
        setTempoAtividadeDisco(tempoAtividadeDisco);
        setUsoMemoriaRam(usoMemoriaRam);
        setQtdJanelasEmUso(qtdJanelasEmUso);
        setDtHoraCaptura(dtHoraCaptura);

        // Exibição dos dados na tela

        System.out.println(toString());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getUsoCpu() {
        return usoCpu;
    }

    public void setUsoCpu(Double usoCpu) {
        this.usoCpu = usoCpu;
    }

    public Double getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(Double usoDisco) {
        this.usoDisco = usoDisco;
    }

    public String getTempoAtividadeDisco() {
        return tempoAtividadeDisco;
    }

    public void setTempoAtividadeDisco(String tempoAtividadeDisco) {
        this.tempoAtividadeDisco = tempoAtividadeDisco;
    }

    public Double getUsoMemoriaRam() {
        return usoMemoriaRam;
    }

    public void setUsoMemoriaRam(Double usoMemoria) {
        this.usoMemoriaRam = usoMemoria;
    }

    public Integer getQtdJanelasEmUso() {
        return qtdJanelasEmUso;
    }

    public void setQtdJanelasEmUso(Integer qtdJanelasEmUso) {
        this.qtdJanelasEmUso = qtdJanelasEmUso;
    }

    public LocalDateTime getDtHoraCaptura() {
        return dtHoraCaptura;
    }

    public void setDtHoraCaptura(LocalDateTime dtHoraCaptura) {
        this.dtHoraCaptura = dtHoraCaptura;
    }

    @Override
    public String toString() {
        return  "usoCpu=" + usoCpu +
                ", usoDisco=" + usoDisco +
                ", tempoAtividadeDisco='" + tempoAtividadeDisco + '\'' +
                ", usoMemoriaRam=" + usoMemoriaRam +
                ", qtdJanelasEmUso=" + qtdJanelasEmUso +
                ", dtHoraCaptura=" + dtHoraCaptura + "\n";
    }
}
