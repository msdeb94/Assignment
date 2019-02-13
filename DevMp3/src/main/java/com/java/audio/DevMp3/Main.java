package com.java.audio.DevMp3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
public class Main {

	public static void main(String[] args) {
		try {
			String path = "F:\\Playsong";
			File[] listOfFiles = getFilePath(path);
			Set<Integer> ref = new HashSet<>();
			int counter = 0;
			while (counter < listOfFiles.length) {

				int random = getRandomInteger(listOfFiles.length, 0);
				if (listOfFiles[random].isFile() && ref.add(random)) {
					counter++;
					FileInputStream fileInputStream = new FileInputStream(path + "\\" + listOfFiles[random].getName());
					Player player = new Player(fileInputStream);
					System.out.println(listOfFiles[random].getName() +" is playing...");
					player.play();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}

	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	public static File[] getFilePath(String path) {

		File[] listOfFiles = null;
		try {
			File folder = new File(path);
			listOfFiles = folder.listFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfFiles;
	}

}
