package grafiken;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockTest {
    Timer clock;
    private int delay = 1000; // zählt in Sekunden
    private int count;
 //   int number;

    public ClockTest(int number){
        startTimer(number);
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public void stopClock(){
        clock.stop();
    }

    public void startClock(){
        clock.start();
    }


    public void startTimer(int countPassed) {
        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(count == 0){
                    System.out.println("Die Zeit ist abgelaufen");
                    System.exit(0);
                }
                else{
                    count --;
                }
            }
        };
        clock = new Timer(delay, action);
        clock.setInitialDelay(0);
        clock.start();
        count = countPassed;
    }
}
