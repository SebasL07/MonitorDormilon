import java.util.concurrent.Semaphore;
import java.util.Random;

public class Monitor extends Thread {

    private Semaphore monitor; // El semáforo para "despertar" al monitor
    private Semaphore estudianteO; // El estudiante que está siendo atendido
    private Random espera; // Genera tiempos aleatorios de atención

    public Monitor(Semaphore monitor, Semaphore estudianteO, long seed) {
        this.monitor = monitor;
        this.estudianteO = estudianteO;
        this.espera = new Random(seed);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("El monitor está durmiendo...");
                // El monitor espera hasta que un estudiante lo despierte

                monitor.acquire();  // Espera a que un estudiante lo despierte
                System.out.println("El monitor está atendiendo a un estudiante.");

                // Simula el tiempo de atención del monitor
                Thread.sleep(espera.nextInt(3000));

                // Liberar al estudiante para que continúe programando
                estudianteO.release();
                System.out.println("El monitor ha terminado de ayudar a un estudiante.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
