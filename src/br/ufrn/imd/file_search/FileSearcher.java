package br.ufrn.imd.file_search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {
	
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		String filename, directoryName, extension, creationDate;
		double size;
		
		SimpleSearcherRunnable searcher;
		
		System.out.print("File name [type \"-\" for no file name]?: ");
		filename = reader.nextLine();
		System.out.print("Directory: ");
		directoryName = reader.nextLine();
		System.out.print("Extension [type \"-\" for no extension]? ");
		extension = reader.nextLine();
		System.out.print("Size (in kilobytes) [type -1 for no size]? ");
		size = Double.parseDouble(reader.nextLine());
		System.out.print("Creation date [format dd/MM/yyyy] [type \"-\" for no date]? ");
		creationDate = reader.nextLine();
		
		searcher = new SimpleSearcherRunnable(directoryName);
		
		if(!filename.equals("-")){
			searcher.addFilter(SimpleSearcherRunnable.NAME_FILTER, filename);
		}
						
		if(!extension.equals("-")){
			searcher.addFilter(SimpleSearcherRunnable.FORMAT_FILTER, extension);
		}
		
		if(size > 0){
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
		
		Thread searchThread = new Thread(searcher);
		searchThread.start();
		
		System.out.println("Searching file...");
		try {
			searchThread.join();
		} catch(InterruptedException ex){
			while(searchThread.isAlive()){}
		}
		
		List<ResultRecord> results = searcher.getResults();
		
		List<String> directories;
		if(searcher.wasFoundDirectories()){
			directories = searcher.getDirectoriesFound();
			searchInDeep(results, directories);
		}
		
		System.out.println(results.size() + " results found!");
		
		for(ResultRecord result : results){
			System.out.println(result.getFilename() + "\t" + result.getLocation());
		}
		
	}
	
	public static void searchInDeep(List<ResultRecord> results, List<String> directories){
		int directoriesQuantity = directories.size();

		Thread[] threads = new Thread[directoriesQuantity];
		SimpleSearcherRunnable[] searchers = new SimpleSearcherRunnable[directoriesQuantity];
				
		for (int i = 0; i < directoriesQuantity; i++) {
			searchers[i] = new SimpleSearcherRunnable(directories.get(i));
			threads[i] = new Thread(searchers[i]);
			threads[i].start();
		}
		
		List<String> newFoundDirectories = new LinkedList<String>();
		
		
		boolean threadsStopped;
		boolean[] threadsProcessed = new boolean[directoriesQuantity];
		Arrays.fill(threadsProcessed, false);
		
		do {
			threadsStopped = true;
			
			for (int i = 0; i < directoriesQuantity; i++) {
				if(!threads[i].isAlive() && !threadsProcessed[i]){
					results.addAll(searchers[i].getResults());
					newFoundDirectories.addAll(searchers[i].getDirectoriesFound());
					threadsProcessed[i] = true;
				} else if(threads[i].isAlive()) {
					threadsStopped = false;
				}
			}
		} while(!threadsStopped);
		
		if(newFoundDirectories.size() > 0){
			searchInDeep(results, newFoundDirectories);
		}
	}
	
}
