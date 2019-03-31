import java.io.SequenceInputStream;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
public class Easy {

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
        time += "Its ";
        //Assigns pm or am
         pm = (hours > 12) ? "pm" : "am";

        //Hours added to time
        hold = (hours == 0 || hours == 12) ? onesPlace[11] : onesPlace[hours%12 - 1];
        time += hold;

        //oh is added to time or nothing is added
        hold = (mins < 10 && mins != 0) ? tensPlace[4] : "";
        time += hold;

        //Gets general mins
        if(mins == 0) {
            time += tensPlace[5];
        } else if(mins > 0 && mins < 20) {
            time += onesPlace[mins - 1];
        } else if(mins > 19 && mins < 30) {
            time += tensPlace[0];
        } else if(mins > 29 && mins < 40) {
            time += tensPlace[1];
        } else if(mins > 39 && mins < 50) {
            time += tensPlace[2];
        } else if(mins > 49 && mins < 60) {
            time +=tensPlace[3];
        }

        //Gets Ones place of number greater than 19
        int temp = mins % 10;
        if(mins > 19 && temp != 0) {
            time += onesPlace[temp - 1];
        }

        time += pm;
        System.out.println(time);
        speak(time);
    }

    public static void speak(String text) {
        ArrayList<String> fileNames = new ArrayList<String>();
        
        //Adds filenames to an array so that we can later concat them
        for(String temp: text.split(" ")) {
            switch(temp.toLowerCase()) { 
                case "twenty":
                    fileNames.add("twen");
                    fileNames.add("ty");
                    break;
                case "thirty":
                    fileNames.add("thir");
                    fileNames.add("ty");
                    break;
                case "forty":
                    fileNames.add("for");
                    fileNames.add("ty");
                    break;
                case "fifty":
                    fileNames.add("fif");
                    fileNames.add("ty");
                    break;
                case "thirteen":
                    fileNames.add("thir");
                    fileNames.add("teen");
                    break;
                case "fourteen":
                    fileNames.add("for");
                    fileNames.add("teen");
                    break;
                case "fifteen":
                    fileNames.add("fif");
                    fileNames.add("teen");
                    break;
                case "sixteen":
                    fileNames.add("six");
                    fileNames.add("teen");
                    break;
                case "seventeen":
                    fileNames.add("seven");
                    fileNames.add("teen");
                    break;
                case "eighteen":
                    fileNames.add("eight");
                    fileNames.add("teen");
                    break;
                case "nineteen":
                    fileNames.add("nine");
                    fileNames.add("teen");
                    break;
                case "o'clock":
                    fileNames.add("00");
                    break;
                default:
                    //System.out.println(temp);
                    fileNames.add(temp);
                    break;
            }
        }

        try {
            //REPLACE FILE DIRECTORY TO FIT YOUR COMPUTER
            //System.out.println(fileNames);
            File file = new File("/Users/coffeemate/Documents/VS_Fun/#321/AttAudrey/");
            AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(file + "/" + fileNames.get(0) + ".wav"));
            AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(file + "/" + fileNames.get(1) + ".wav"));
            
            AudioInputStream multi = 
                            new AudioInputStream(
                                new SequenceInputStream(clip1, clip2),
                                clip1.getFormat(),
                                clip1.getFrameLength() + clip2.getFrameLength());

            if(fileNames.size() > 2) {
                for(int i = 2; i < fileNames.size(); i++) {
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

            long duraMili = 6000;

            //Pauses program to let audio play
            Thread.sleep(duraMili);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}