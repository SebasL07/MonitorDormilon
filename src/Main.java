import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    private static final int N_ESTUD = 4;
    public static void main(String[] args) {

        // Revisar que se recibe la semilla como parámetro
        if (args.length != 1) {
            System.out.println("Sintaxis: Carrera semilla_aleatoria");
            System.exit(1);
        }

        long Semilla=Long.parseLong(args[0]);


        //Los semaforos necesarios
        Semaphore monitor = new Semaphore(1,true);
        Semaphore estudianteO = new Semaphore(1,true);

        //Lista de los N estudiantes que se requieran
        List<Estudiante> estudiantes = new ArrayList<>();
        Monitor monitor1 = new Monitor(monitor,estudianteO,Semilla);
        //TODO: inicializar los estudiantes con diferentes semaforos?
        for (int i = 0; i < N_ESTUD; i++) {
            estudiantes.add(new Estudiante(monitor,estudianteO,("Estud_" + (i + 1)),Semilla));
        }

        estudianteO.drainPermits();

        monitor1.start();
        for (Estudiante e:
             estudiantes) {
            e.start();
        }

    }
}