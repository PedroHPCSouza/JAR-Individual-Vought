import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Parametros {

    // Conexão com o banco mysql:
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoMySql();

    // Conexão com o banco SQLServer:

    ConexaoSQL conexaoSQL = new ConexaoSQL();
    JdbcTemplate conSQL = conexaoSQL.getConexaoSqlServerLocal();

    ParametrosConexao parametrosConexao = new ParametrosConexao();

    private Integer idParametros;
    private Integer tempoSegCapturaDeDados;
    private Integer tempoSegAlertas;
    private Integer usoCriticoCpu;
    private Integer usoCriticoDisco;
    private Integer usoCriticoMemoriaRam;
    private Integer usoAlarmanteCpu;
    private Integer usoAlarmanteDisco;
    private Integer usoAlarmanteMemoriaRam;
    private Integer usoNormalCpu;
    private Integer usoNormalDisco;
    private Integer usoNormalMemoriaRam;
    private Integer fkEmpresa;

    public Parametros(Integer idParametros, Integer tempoSegCapturaDeDados, Integer tempoSegAlertas, Integer usoCriticoCpu, Integer usoCriticoDisco, Integer usoCriticoMemoriaRam, Integer usoAlarmanteCpu, Integer usoAlarmanteDisco, Integer usoAlarmanteMemoriaRam, Integer usoNormalCpu, Integer usoNormalDisco, Integer usoNormalMemoriaRam, Integer fkEmpresa) {
        this.idParametros = idParametros;
        this.tempoSegCapturaDeDados = tempoSegCapturaDeDados;
        this.tempoSegAlertas = tempoSegAlertas;
        this.usoCriticoCpu = usoCriticoCpu;
        this.usoCriticoDisco = usoCriticoDisco;
        this.usoCriticoMemoriaRam = usoCriticoMemoriaRam;
        this.usoAlarmanteCpu = usoAlarmanteCpu;
        this.usoAlarmanteDisco = usoAlarmanteDisco;
        this.usoAlarmanteMemoriaRam = usoAlarmanteMemoriaRam;
        this.usoNormalCpu = usoNormalCpu;
        this.usoNormalDisco = usoNormalDisco;
        this.usoNormalMemoriaRam = usoNormalMemoriaRam;
        this.fkEmpresa = fkEmpresa;
    }

    public Parametros(){
    }


    public void alertar(String nomeFuncionario, String numeroNotebook , Double usoCpu, Double usoDisco, Double usoMemoriaRam, Integer fkRegistro, Integer fkNotebook){

        String alerta = "";
        Boolean emitirAlerta = false;

        if (usoCpu > usoAlarmanteCpu && usoCpu < usoCriticoCpu){
            alerta += ("Cpu está em estado de alerta\n");
            emitirAlerta = true;
        } else if (usoCpu > usoAlarmanteCpu && usoCpu > usoCriticoCpu){
            alerta += ("Cpu está em estado crítico\n");
            emitirAlerta = true;
        }

        if (usoDisco > usoAlarmanteDisco && usoDisco < usoCriticoDisco){
            alerta += ("Utilização do disco está em estado de alerta\n");
            emitirAlerta = true;
        } else if (usoDisco > usoAlarmanteDisco && usoDisco > usoCriticoDisco){
            alerta += ("Utilização do disco está em estado crítico\n");
            emitirAlerta = true;
        }

        if (usoMemoriaRam > usoAlarmanteMemoriaRam && usoMemoriaRam < usoCriticoMemoriaRam){
            alerta += ("Utilização da memória RAM está em estado de alerta\n");
            emitirAlerta = true;
        } else if (usoMemoriaRam > usoAlarmanteMemoriaRam && usoMemoriaRam > usoCriticoMemoriaRam){
            alerta += ("Utilização da memória RAM está em estado crítico\n");
            emitirAlerta = true;
        }

        alerta += "Número de série do notebook: " + numeroNotebook;
        final String alertaFinal = alerta;

        if (emitirAlerta){

            // Código para enviar notificação via slack e notificar no console

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    System.out.println(alertaFinal);
                    Slack.EnviarAlertaSlack(alertaFinal);
                    Thread.sleep(tempoSegAlertas * 1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });

            // Insert no banco mysql

            con.update("INSERT INTO Alerta (fkParametros, fkEmpresaParametros) VALUES (?, ?);",
                    idParametros, fkEmpresa);

            // Insert no SQL Server

            conSQL.update("INSERT INTO Alerta (fkParametros, fkEmpresaParametros) VALUES (?, ?);",
                    idParametros, fkEmpresa);

        }

    }

    public void insertParametros (){
        con.update("INSERT INTO Parametros (tempoSegCapturaDeDados, tempoSegAlertas, UsoCriticoCpu, UsoCriticoDisco, UsoCriticoMemoriaRam, UsoAlarmanteCpu, UsoAlarmanteDisco, UsoAlarmanteMemoriaRam, UsoNormalCpu, UsoNormalDisco, UsoNormalMemoriaRam, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                tempoSegCapturaDeDados, tempoSegAlertas, usoCriticoCpu, usoCriticoDisco, usoCriticoMemoriaRam, usoAlarmanteCpu, usoAlarmanteDisco, usoAlarmanteMemoriaRam, usoNormalCpu, usoNormalDisco, usoNormalMemoriaRam, fkEmpresa);
    }

    public Integer getIdParametros() {
        return idParametros;
    }

    public void setIdParametros(Integer idParametros) {
        this.idParametros = idParametros;
    }

    public Integer getTempoSegCapturaDeDados() {
        return tempoSegCapturaDeDados;
    }

    public void setTempoSegCapturaDeDados(Integer tempoSegCapturaDeDados) {
        this.tempoSegCapturaDeDados = tempoSegCapturaDeDados;
    }

    public Integer getTempoSegAlertas() {
        return tempoSegAlertas;
    }

    public void setTempoSegAlertas(Integer tempoSegAlertas) {
        this.tempoSegAlertas = tempoSegAlertas;
    }

    public Integer getUsoCriticoCpu() {
        return usoCriticoCpu;
    }

    public void setUsoCriticoCpu(Integer usoCriticoCpu) {
        this.usoCriticoCpu = usoCriticoCpu;
    }

    public Integer getUsoCriticoDisco() {
        return usoCriticoDisco;
    }

    public void setUsoCriticoDisco(Integer usoCriticoDisco) {
        this.usoCriticoDisco = usoCriticoDisco;
    }

    public Integer getUsoCriticoMemoriaRam() {
        return usoCriticoMemoriaRam;
    }

    public void setUsoCriticoMemoriaRam(Integer usoCriticoMemoriaRam) {
        this.usoCriticoMemoriaRam = usoCriticoMemoriaRam;
    }

    public Integer getUsoAlarmanteCpu() {
        return usoAlarmanteCpu;
    }

    public void setUsoAlarmanteCpu(Integer usoAlarmanteCpu) {
        this.usoAlarmanteCpu = usoAlarmanteCpu;
    }

    public Integer getUsoAlarmanteDisco() {
        return usoAlarmanteDisco;
    }

    public void setUsoAlarmanteDisco(Integer usoAlarmanteDisco) {
        this.usoAlarmanteDisco = usoAlarmanteDisco;
    }

    public Integer getUsoAlarmanteMemoriaRam() {
        return usoAlarmanteMemoriaRam;
    }

    public void setUsoAlarmanteMemoriaRam(Integer usoAlarmanteMemoriaRam) {
        this.usoAlarmanteMemoriaRam = usoAlarmanteMemoriaRam;
    }

    public Integer getUsoNormalCpu() {
        return usoNormalCpu;
    }

    public void setUsoNormalCpu(Integer usoNormalCpu) {
        this.usoNormalCpu = usoNormalCpu;
    }

    public Integer getUsoNormalDisco() {
        return usoNormalDisco;
    }

    public void setUsoNormalDisco(Integer usoNormalDisco) {
        this.usoNormalDisco = usoNormalDisco;
    }

    public Integer getUsoNormalMemoriaRam() {
        return usoNormalMemoriaRam;
    }

    public void setUsoNormalMemoriaRam(Integer usoNormalMemoriaRam) {
        this.usoNormalMemoriaRam = usoNormalMemoriaRam;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "Parametros{" +
                "idParametros=" + idParametros +
                ", tempoSegCapturaDeDados=" + tempoSegCapturaDeDados +
                ", tempoSegAlertas=" + tempoSegAlertas +
                ", usoCriticoCpu=" + usoCriticoCpu +
                ", usoCriticoDisco=" + usoCriticoDisco +
                ", usoCriticoMemoriaRam=" + usoCriticoMemoriaRam +
                ", usoAlarmanteCpu=" + usoAlarmanteCpu +
                ", usoAlarmanteDisco=" + usoAlarmanteDisco +
                ", usoAlarmanteMemoriaRam=" + usoAlarmanteMemoriaRam +
                ", usoNormalCpu=" + usoNormalCpu +
                ", usoNormalDisco=" + usoNormalDisco +
                ", usoNormalMemoriaRam=" + usoNormalMemoriaRam +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
