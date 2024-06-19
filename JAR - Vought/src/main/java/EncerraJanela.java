import java.io.IOException;

public class EncerraJanela {
    public void encerrarProcessoPorPID(int pid) {
        String comando = "taskkill /F /PID " + pid;

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
            Process processo = builder.start();
            int retorno = processo.waitFor(); // Espera pelo término do processo

            if (retorno == 0) {
                System.out.println("Processo encerrado com sucesso.");
            } else {
                System.out.println("Falha ao encerrar o processo.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
