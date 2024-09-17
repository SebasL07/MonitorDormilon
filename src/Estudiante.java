import java.util.*;
import java.util.concurrent.Semaphore;

public class Estudiante extends Thread{

    private Semaphore monitor; //El despertador del monitor

    private Semaphore atencion; //El semáforo que le permite ser atendido

    private String name; //Nombre del estudiante

    private Random espera; //El random que genera un timepo de espera aleatorio

    public Estudiante(Semaphore monitor, Semaphore atencion, String name, long seed){
        super();

        this.monitor = monitor;
        this.atencion = atencion;
        this.name = name;
        espera = new Random(seed);
    }
    //TODO: como adquiere los permisos para seguir en la fila y como se va a la sala de computo
    @Override
    public void run() {
        while (true){
            try {
                atencion.acquire();
            }catch (InterruptedException e){

            }
        }
    }
}
