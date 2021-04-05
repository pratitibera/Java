import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static int interval;
    static Timer timer;
    int i = 0; // Keeps the count of lane

    void lights() {
        if (i == 4) {
            i = 0;
        }
        i++;
        int secs = 0;
        int z = vehicleCount(i); // Fetches the count of vehicles of each lane
        if(z == 0){
            secs = 1; // Setting release time for no traffic density
            System.out.println("No Traffic");
        }
        else if (z <= 10 && z > 0){ // Comparing with reference count
            secs = 5; // Setting release time for low traffic density
            System.out.println("Low Traffic");
        }
        else if (z > 10 && z < 30){ // Comparing with reference count
            secs = 10; // Setting release time for medium traffic density
            System.out.println("Medium Traffic");
        }
        else if(z >= 30){ // Comparing with reference count
            secs = 15; // Setting release time for high traffic density
            System.out.println("High Traffic");
        }

        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = secs;
        System.out.print("Lane 1 "); // Setting the signals
        System.out.print("Lane 2 ");
        System.out.print("Lane 3 ");
        System.out.print("Lane 4 ");
        System.out.println();
        timer.scheduleAtFixedRate(new TimerTask() { // Timer starts

            public void run() { // countdown  function

                if (setInterval() == 0) { // condition to check timeup
                    trafficLightSwitch(); // calls function to switch lights
                    lights(); // Initialising for the next lane
                }
                trafficLights(interval); // Signaling with traffic lights

            }
        }, delay, period);
    }

    void trafficLightSwitch() { // Function to switch lights of next lane
        for (int j = 1; j < 5; j++) {
            if (i + 1 == j && i < 4) {
                System.out.print("G      ");
            } else if (j == 1 && i == 4) {
                System.out.print("G      ");
            } else {
                System.out.print("R      ");
            }
        }
        System.out.println();
        System.out.println();
    }

    void trafficLights(int interv) { // Regulating all three traffic lights

        for (int j = 1; j < 5; j++) {

            if (i == j) {
                System.out.print("G      ");
            } else if (i + 1 == j && i < 4) {
                if (interv < 3) {
                    System.out.print("Y      ");
                } else {
                    System.out.print("R      ");
                }
            } else if (i == 4 && j == 1) {
                if (interv < 3) {
                    System.out.print("Y      ");
                } else {
                    System.out.print("R      ");
                }
            } else {
                System.out.print("R      ");
            }
        }
        System.out.println();
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    int vehicleCount(int a) {
        int number;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vehicles in lane " + a + ": ");
        number = s.nextInt();
        if(number >= 0){
            return number;
        }
        else{
            System.out.println("Traffic density cannot be negative");
            return 0;
        }
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.lights();
    }
}