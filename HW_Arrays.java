//.......1.........2.........3.........4.........5.........6.........7.........8
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * This program plugs in the prompted values to an equation that will measure
 * the skydiver's velocity at specific intervals of time. Then, it continues to
 * print it to the board and to a prompted csv file.
 *
 * @author (Raphael Juco)
 * @version (2/22/2018)
 */
public class HW_Arrays
{
    /**
     * In this code we prompt the user to enter values for the necessary 
     * components needed to calculate the velocity of the skydiver for each 
     * timestep and assign it to a variable. Then, we created an array
     * for time and velocity so we can assign values of velocity at 
     * each interval of time to each arrays.
     */
    public static void main(String[] args){
        PrintWriter output = null;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the mass of the skydiver (kg): ");
        double mass = keyboard.nextDouble();
        System.out.print("Enter the cross-sectional area of the skydiver");
        System.out.println("(m^2): ");
        double csArea = keyboard.nextDouble();
        System.out.println("Enter the drag co-efficient of the skydiver: ");
        double drag = keyboard.nextDouble();
        System.out.println("Enter the ending time (sec): ");
        double endingTime = keyboard.nextDouble();
        System.out.println("Enter the time step (sec): ");
        double timeStep = keyboard.nextDouble();
        System.out.println("Enter the output filename: ");
        String file = keyboard.next();//Reads in the next line
        System.out.println("Writing out file. Here are the first few lines: ");

        //testVariableValue(mass, csArea);
        double gravity = 9.81;//m/s/s
        double airDensity = 1.14;//kg/m^3
        double currentTime = 0.0;
        int totalTime = totalTime(endingTime, timeStep);
        double[] time = new double[totalTime];//(s)
        double[] Velocity = new double[totalTime];//(m/s)
        Velocity[0] = 0;//assigns the first index of the array to be 0m/s
        time[0] = 0;//at time 0s velocity is 0m/s whic makes sense
        try
        {
            output = new PrintWriter(new FileOutputStream(file));
        }
        catch(IOException e){
            System.out.println("File cannot be opened!");
            System.exit(0);
        }
        /*
         * In the loop, we assign each indeces of the array of velocities
         * to the value of the previous velocity plus the velocity at
         * the current timestep. We then increase the timestep and assign
         * each value to the array of times. Then we print it out to the 
         * board and to the prompted file above.
         */
        for(int i = 1; i < totalTime; i++){
            Velocity[i] = Velocity[i-1] + (gravity - 
                ((drag * csArea * airDensity)/(2 * mass)) 
                * (Velocity[i-1]
                    * Velocity[i-1])) * timeStep;
            currentTime = currentTime + timeStep;
            time[i] = currentTime;
            System.out.printf("%4.3f" + ", " + "%5.4f" ,time[i], Velocity[i]);
            System.out.println();
            output.printf("%4.3f" + ", " + "%5.4f" ,time[i],Velocity[i]);
            output.println();
        }
        output.close();//Closes the file
    }

    /**
     * Tests the variables listed above. Plug in any variables. Examples 
     * are shown below.
     */
    public static void testVariableValues(double mass, double csArea){
        System.out.println(mass + csArea);
    }

    /**
     * I created this method because when assigning array sizes, it only takes
     * in whole numbers or int, so I had to convert the double value of 
     * endingTime/timeStep into an array.
     */
    public static int totalTime(double endingTime, double timeStep){
        int totalTime = 0;
        for(int j = 0; j < endingTime/timeStep; j++){
            totalTime++;
        }
        return totalTime;//Total amount of values or length of the arrays
    }
}
//.......1.........2.........3.........4.........5.........6.........7.........8