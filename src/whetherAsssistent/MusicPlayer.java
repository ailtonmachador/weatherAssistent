package whetherAsssistent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
 //config. to run voice in the main class
public class MusicPlayer{
    private File music;
    private Player player;
 
    public MusicPlayer(File music){
        this.music = music;
    }
 
    public void play(){
        try{
            FileInputStream stream = new FileInputStream(music);
            BufferedInputStream buffer = new BufferedInputStream(stream);
            this.player = new Player (buffer);
            System.out.println("Executing...");
            this.player.play();
            System.out.println("Finished");
        }
        catch (Exception e) {
            System.out.println("Erro!");
            e.printStackTrace();
        }
    }
}