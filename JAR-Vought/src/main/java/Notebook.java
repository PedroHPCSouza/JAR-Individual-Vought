import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

public class Notebook {

    Conexao conexaoMySQL = new Conexao();
    JdbcTemplate con = conexaoMySQL.getConexaoMySql();

    private Integer idNotebook;
    private String numeroSerie;
    private String fabricante;
    private String modelo;
    private LocalDateTime dtRegistro;
    private Integer fkEmpresa;
    private InfoNotebook infoNotebook;
    private List<Registro> registros;

    public Notebook(Integer idNotebook, String numeroSerie, String fabricante, String modelo, LocalDateTime dtRegistro, Integer fkEmpresa) {
        this.idNotebook = idNotebook;
        this.numeroSerie = numeroSerie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.dtRegistro = dtRegistro;
        this.fkEmpresa = fkEmpresa;
    }

    public Notebook(Integer idNotebook, String numeroSerie, String fabricante, String modelo, LocalDateTime dtRegistro, Integer fkEmpresa, InfoNotebook infoNotebook, List<Registro> registros) {
        this.idNotebook = idNotebook;
        this.numeroSerie = numeroSerie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.dtRegistro = dtRegistro;
        this.fkEmpresa = fkEmpresa;
        this.infoNotebook = infoNotebook;
        this.registros = registros;
    }

    public Notebook() {
    }

    public void insertNotebook(){
        con.update("INSERT INTO Notebook (numeroSerie, fabricante, modelo, fkEmpresa) VALUES (?, ?, ?, ?);",
                numeroSerie, fabricante, modelo, fkEmpresa);
    }

    public Integer getIdNotebook() {
        return idNotebook;
    }

    public void setIdNotebook(Integer idNotebook) {
        this.idNotebook = idNotebook;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public InfoNotebook getInfoNotebook() {
        return infoNotebook;
    }

    public void setInfoNotebook(InfoNotebook infoNotebook) {
        this.infoNotebook = infoNotebook;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "idNotebook=" + idNotebook +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", dtRegistro=" + dtRegistro +
                ", fkEmpresa=" + fkEmpresa +
                ", infoNotebook=" + infoNotebook +
                ", registros=" + registros +
                '}';
    }
}
