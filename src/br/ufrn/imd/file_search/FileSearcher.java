package br.ufrn.imd.file_search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileSearcher {
	
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		String filename, directoryName, extension, creationDate;
		double size;
		
		SimpleSearcherRunnable searcher;
		
		System.out.print("File name [type \"-\" for no file name]?: ");
		filename = reader.next();
		System.out.print("Directory: ");
		directoryName = reader.next();
		System.out.print("Extension [type \"-\" for no extension]? ");
		extension = reader.next();
		System.out.print("Size (in kilobytes) [type -1 for no size]? ");
		size = reader.nextDouble(); reader.nextLine();
		System.out.print("Creation date [format dd/MM/yyyy] [type \"-\" for no date]? ");
		creationDate = reader.nextLine();
		
		searcher = new SimpleSearcherRunnable(directoryName);
						
		if(!filename.equals("-")){
			searcher.addFilter(SimpleSearcherRunnable.NAME_FILTER, filename);
		}
						
		if(!extension.equals("-")){
			searcher.addFilter(SimpleSearcherRunnable.FORMAT_FILTER, extension);
		}
		
		if(size > -1){
			searcher.addFilter(SimpleSearcherRunnable.SIZE_FILTER, size);
		}
		
		String dateFormat = "dd/MM/yyyy";
		DateFormat format = new SimpleDateFormat(dateFormat);
		if(!creationDate.equals("-")){
			try {
				Date dateOfCreation = format.parse(creationDate);
				searcher.addFilter(SimpleSearcherRunnable.DATE_FILTER, dateOfCreation);
			} catch (ParseException e) {
				System.out.println("Invalid date passed!");
			}
		}
		
		
	}

}
