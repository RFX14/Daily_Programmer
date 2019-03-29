import java.io.SequenceInputStream;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
public class Easy {

    //ISSUES:
    /**
     * all of the switch cases are not working and just go to default
     * 8:08 says too much
     *  *//
    public static void main(String[] args) {
        String [] tensPlace = {"twenty ", "thirty ", "forty ", "fifty ", "oh ", "o'clock " };
        String [] onesPlace = {"one ", "two ", "three ", "four ", "five ", "six ",
                                "seven ", "eight ", "nine ", "ten ", "eleven ",
                                "twelve ", "thirteen ", "fourteen ", "fifteen ",
                                "sixteen ", "seventeen ", "eighteen ", "nineteen "};
        int hours = 0, mins = 0, i = 0;
        String time = "", hold = "", pm = "";

        //Seperates the numbers from colon
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
        hold = (hours == 0 || hours == 12) ? onesPlace[11] : onesPlace[hours%12 - 1];
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
        } else if(mins == 0) {
            time += tensPlace[5];
        }

        time += pm;
        System.out.println("It's " + time);
        speak(time);
    }

    public static void speak(String text) {
        ArrayList<String> fileNames = new ArrayList<String>();
        
        //Adds filenames to an array so that we can later concat them
        int count = 0;
        for(String temp: text.split(" ")) {
            switch(temp.toLowerCase()) { 
                case "twenty":
                    fileNames.add("twen");
                    fileNames.add("ty");
                case "thirty":
                    fileNames.add("thir");
                    fileNames.add("ty");
                case "forty":
                    fileNames.add("for");
                    fileNames.add("ty");
                case "fifty":
                    fileNames.add("fif");
                    fileNames.add("ty");
                case "thirteen":
                    fileNames.add("thir");
                    fileNames.add("teen");
                case "fourteen":
                    fileNames.add("for");
                    fileNames.add("teen");
                case "fifteen":
                    fileNames.add("fif");
                    fileNames.add("teen");
                case "sixteen":
                    fileNames.add("six");
                    fileNames.add("teen");
                case "seventeen":
                    fileNames.add("seven");
                    fileNames.add("teen");
                case "eighteen":
                    fileNames.add("eight");
                    fileNames.add("teen");
                case "nineteen":
                    fileNames.add("nine");
                    fileNames.add("teen");
                case "o'clock":
                    fileNames.add("00");
                default:
                    System.out.println("here");
                    fileNames.add(temp);
            }
            count++;
        }

        try {
            //REPLACE FILE DIRECTORY TO FIT YOUR COMPUTER
            File file = new File("/Users/coffeemate/Documents/VS_Fun/#321/AttAudrey/");
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(file + "/" + fileNames.get(0) + ".wav"));
            AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(file + "/" + fileNames.get(1) + ".wav"));
            
            AudioInputStream multi = 
                            new AudioInputStream(
                                new SequenceInputStream(clip1, clip2),
                                clip1.getFormat(),
                                clip1.getFrameLength() + clip2.getFrameLength());

            if(fileNames.size() > 2) {
                for(int i = count; i < fileNames.size(); i++) {
                    clip1 = AudioSystem.getAudioInputStream(new File(file + "/" + fileNames.get(i) + ".wav"));
                    
                    AudioInputStream temp = 
                            new AudioInputStream(
                                new SequenceInputStream(multi, clip1),
                                multi.getFormat(),
                                multi.getFrameLength() + clip2.getFrameLength());
                    multi = temp;
                }
            }
            
            Clip clip = AudioSystem.getClip();
            clip.open(multi);
            clip.start();

            //Pauses program to let audio play
            Thread.sleep(9000);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}