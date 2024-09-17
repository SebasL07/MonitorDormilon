import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread{

    private Semaphore monitor; //Los estudiantes lo utilizan para indicar que ya dejan libre al monitor

    private Semaphore estudianteO; //El estudiante que tiene ocupado al monitor

    private Random espera; //Genera un tiempo de espera aleatorio
    private static final int TIEMPO_MAX_DORMIR = 5000; // Tiempo máximo que el monitor duerme

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

    private void ayudarEstudiante() {

        try{

            int tiempo = espera.nextInt(TIEMPO_MAX_DORMIR);
            System.out.println("El monitor ayuda por " + tiempo + "ms" );
            Thread.sleep(tiempo);
            estudianteO.release();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
