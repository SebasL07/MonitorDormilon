import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Estudiante extends Thread{

    private Semaphore monitor; //El despertador del monitor

    private Semaphore atencion; //El semáforo que le permite ser atendido

    private String name; //Nombre del estudiante

    private Random espera; //El random que genera un timepo de espera aleatorio
    public static final int TIEMPO_PROGRAMANDO_MAX = 3000; //Maximo de tiempo programando

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

                //Simula que programa
                programar();

                //Intenta obtener ayuda
                System.out.println(name + " busca ayuda de un monitor");

                if(atencion.tryAcquire()) {

                    //Si consigue atencion, lo despierta
                    if (monitor.availablePermits() == 0) {

                        System.out.println(name + " desperto al monitor");
                        monitor.release(); // Se despierta al monitor

                    }

                    //Ahora esta siendo ayudado
                    ayuda();

                    //cuando acaba, se libera el monitor
                    atencion.release();

                } else {

                    // Si no hay espacio en el pasillo vuelve a programar
                    System.out.println(name + " no encontro una silla vacia, Vuelve a programar");

                }

        }
    }

    public void programar() {

        try{

            int tiempo = espera.nextInt(TIEMPO_PROGRAMANDO_MAX);
            System.out.println(name + " esta programando por " + tiempo + "ms");
            Thread.sleep(tiempo);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    private void ayuda() {

        try{

            int tiempo = espera.nextInt(2000); // La ayuda va por tiempo aleatorio
            System.out.println(name + " esta siendo ayudado por el monitor durante " + tiempo + "ms");
            Thread.sleep(tiempo);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
