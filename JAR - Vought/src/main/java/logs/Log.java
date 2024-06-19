package logs;

public class Log {
    private String data;
    private String logLevel;
    private Integer statusCode;
    private String mensagem;
    private String idMaquina;
    private String hostName;
    private String stackTrace;
    private String sistemaOperacional;
    private Integer arquitetura;

    public Log(String data, String logLevel, Integer statusCode, String mensagem, String idMaquina, String hostName, String stackTrace, String sistemaOperacional, Integer arquitetura) {
        this.data = data;
        this.logLevel = logLevel;
        this.statusCode = statusCode;
        this.mensagem = mensagem;
        this.idMaquina = idMaquina;
        this.hostName = hostName;
        this.stackTrace = stackTrace;
        this.sistemaOperacional = sistemaOperacional;
        this.arquitetura = arquitetura;
    }

    public Log(String data, String logLevel, Integer statusCode, String mensagem, String sistemaOperacional, Integer arquitetura) {
        this.data = data;
        this.logLevel = logLevel;
        this.statusCode = statusCode;
        this.mensagem = mensagem;
        this.sistemaOperacional = sistemaOperacional;
        this.arquitetura = arquitetura;
    }

    @Override
    public String toString() {
        return """
                {
                Data: %s
                Log Level: %s
                Status Code: %d
                Mensagem: %s
                Id Máquina: %s
                Host Name: %s
                Stack Trace: %s
                Sistema Operacional: %s
                Arquitetura: %d
                }
                """.formatted(data, logLevel, statusCode, mensagem, idMaquina, hostName, stackTrace, sistemaOperacional, arquitetura);
    }
}

