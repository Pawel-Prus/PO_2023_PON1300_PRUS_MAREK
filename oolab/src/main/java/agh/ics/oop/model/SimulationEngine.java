package agh.ics.oop.model;
import agh.ics.oop.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable {
    private  final List<Simulation> simulations;


    public SimulationEngine(List<Simulation> simulations) {

        this.simulations = simulations;

    }

    public void runSyn(){
        for(Simulation sim : simulations){
            sim.run();
        }
    }

    public void runAsync(){
       List<Thread> threads = new ArrayList<>();
       for(Simulation sim : simulations){
           threads.add(new Thread(sim));
       }
        for(Thread t : threads){
            t.start();


        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }
    public void awaitSimulationsEnd (ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }


    public void runAsyncInThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (Simulation sim : simulations) {
            executorService.submit(sim);
        }
       //awaitSimulationsEnd(executorService);


    }

    public List<Simulation> getSimulations() {
        return simulations;
    }

    @Override
    public void run() {
        runAsync();
    }
}
