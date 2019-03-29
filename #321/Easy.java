import java.io.SequenceInputStream;
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

    public static void speak(String text) {
        ArrayList<String> fileNames = new Arraylist<String>();
        
        //Adds filenames to an array so that we can later concat them
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
                default:
                    fileNames.add(temp);
            }
        }

        try {
            File file = new File("/AttAudrey/");
            AudioInputStream clip1 = AudioInputStream.getAudioInputStream(new File(file + fileNames.get(0) + ".wav"));
            AudioInputStream clip2 = AudioInputStream.getAudioInputStream(new File(file + fileNames.get(1) + ".wav"));
            
            AudioInputStream multi = 
                            new AudioInputStream(
                                new SequenceInputStream(clip1, clip2),
                                clip1.getFormat(),
                                clip1.getFrameLength() + clip2.getFrameLength());

            if(fileNames.size() > 2) {
                for(int i = 0; i < fileNames.size(); i++) {
                    clip1 = AudioInputStream.getAudioInputStream(new File(file + fileNames.get(i) + ".wav"));
                }
            }
            
            Clip clip = AudioSystem.getClip();
            clip.open(multi);
            clip.start();

            //Pauses program to let audio play
            Thread.sleep(500);
            stream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}