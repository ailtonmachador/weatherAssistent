package whetherAsssistent;

import java.io.File;

public class Voice {
	
	public void Voice(){}
	
	public String audioPath(String audio) {
		String audioName = audio;
		String path = "C:\\Users\\junior machado\\Downloads\\" + audio + ".mp3";
		File musicaFile = new File(path);
        MusicPlayer music = new MusicPlayer(musicaFile);
        music.play();
		return path;
	}
	
	public void speakTemperature(double temp) {		       
		
		
		if (temp < -5) {		
			 audioPath("-5degress");
		}
		else  if (temp < 8) {
			 audioPath("8degrees");			  
		}
		else if (temp > 8 && temp < 14){
			 audioPath("8-14degrees");	
		}else if (temp > 14 && temp < 20) {
			 audioPath("14-20degress");			  
		}else if (temp > 20 && temp < 40) {
			 audioPath("20-40degress");
		} else if (temp > 41) {
			 audioPath("41degress");
		}
	}
	
	public void introduction() {
		audioPath("introduction");
	}
}
