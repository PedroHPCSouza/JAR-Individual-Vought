import java.io.IOException;
import java.util.concurrent.CompletableFuture;
public class Reboot {
    public static void Desligar(String so) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            if (so.equalsIgnoreCase("windows")) {
                try {
                    Thread.sleep(604800000); // Simula uma operação assíncrona
                    Process processo = Runtime.getRuntime().exec("shutdown /r /t 0");
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            if (so.equalsIgnoreCase("linux") || so.equalsIgnoreCase("mac")){
                try {
                    Thread.sleep(604800000); // Simula uma operação assíncrona
                    Process processo = Runtime.getRuntime().exec("shutdown -P now");
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}