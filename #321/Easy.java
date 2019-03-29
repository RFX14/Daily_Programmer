import java.util.*;
import javax.sound.sampled.*;
public class Easy {
    public static void main(String[] args) {
        String [] tensPlace = {"twenty ", "thirty ", "forty ", "fifty ", "oh " };
        String [] onesPlace = {"one ", "two ", "three ", "four ", "five ", "six ",
                                "seven ", "eight ", "nine ", "ten ", "eleven ",
                                "twelve ", "thirteen ", "fourteen ", "fifteen ",
                                "sixteen ", "seventeen ", "eighteen ", "nineteen "};
        int hours = 0, mins = 0, i = 0;
        String time = "", hold = "", pm = "";

        //Seperates the numbers from :
        for(String temp: args[0].split(":")) {
            if(i == 0) {
                hours += Integer.parseInt(temp);
            } else {
                mins += Integer.parseInt(temp);
            }
            i++;
        }

        //Assigns pm or am
         pm = (hours > 12) ? "pm" : "am";

        //Hours added to time
        hold = (hours == 0) ? onesPlace[11] : onesPlace[hours%12 - 1];
        time += hold;

        //oh is added to time or nothing is added
        hold = (mins < 10 && mins != 0) ? tensPlace[4] : "";
        time += hold;

        //Gets the Tens place of mins
        if(mins > 19 && mins < 30) {
            time += tensPlace[0];
        } else if(mins > 29 && mins < 40) {
            time += tensPlace[1];
        } else if(mins > 39 && mins < 50) {
            time += tensPlace[2];
        } else if(mins > 49 && mins < 60) {
            time +=tensPlace[3];
        } 

        if(mins < 20 && mins != 0) {
            time += onesPlace[mins - 1];
        }

        time += pm;
        System.out.println("It's " + time);
        speak(time);
    }

    //Figure how to put together the audio files
    public static void speak(String text) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

            Thread.sleep(500);
            stream.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}