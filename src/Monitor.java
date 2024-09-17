import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread{

    private Semaphore monitor; //Los estudiantes lo utilizan para indicar que ya dejan libre al monitor

    private Semaphore estudianteO; //El estudiante que tiene ocupado al monitor

    private Random espera; //Genera un tiempo de espera aleatorio

    public Monitor(Semaphore monitor, Semaphore estudianteO, long seed){
        super();
        this.monitor = monitor;
        this.estudianteO = estudianteO;
        espera = new Random(seed);
    }
    //TODO: como se dan los permisos para que pase el estudiante en la fila
    @Override
    public void run() {
        while (true){
            try {
                //Espera a que no haya estudiantes para atender
                monitor.acquire();


            } catch (InterruptedException e) {
            }
        }
    }
}
