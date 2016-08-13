package br.ufrn.imd.file_search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

public class DirectoryFileListing {

	public static void main(String[] args) throws IOException {
		double MEGABYTE = Math.pow(2, 20);
		File directory = new File("/home/inacio-medeiros/Music");
		
		if(directory.isDirectory()){
			File[] filesList = directory.listFiles();
			for(File file : filesList){
				Path path = file.toPath();
				
				BasicFileAttributes attr = null;
				try {
					attr = Files.readAttributes(path, BasicFileAttributes.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.print(file.getCanonicalPath() + " - ");
				System.out.print(attr.creationTime() + " - ");
				
				double length = (file.length() / MEGABYTE);
				
				System.out.print(new DecimalFormat(".##").format(length) + "\n");
			}
		}
	}

}
