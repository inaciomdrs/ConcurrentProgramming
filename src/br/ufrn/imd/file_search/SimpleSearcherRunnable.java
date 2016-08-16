package br.ufrn.imd.file_search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Traverse a directory searching for files that match a set of predefined
 * filters
 * 
 * @author inacio-medeiros
 *
 */
public class SimpleSearcherRunnable implements Runnable {

	public static final String NAME_FILTER = "name";
	public static final String FORMAT_FILTER = "format";
	public static final String SIZE_FILTER = "size";
	public static final String DATE_FILTER = "date";

	private String directoryName;

	private static HashMap<String, Object> filters = new HashMap<String, Object>();
	private List<ResultRecord> results;
	private List<String> directoriesFound;

	public SimpleSearcherRunnable(String directoryName) {
		this.directoryName = directoryName;
	}

	public void addFilter(String name, Object value) {
		filters.put(name, value);
	}

	public void clearFilters() {
		filters.clear();
	}

	private boolean fileMatchesFiltersList(File file) {		
		String filename = (String) filters.get(SimpleSearcherRunnable.NAME_FILTER);
		if ((filename != null) && (!file.getName().contains(filename))) {
			return false;
		}

		String format = (String) filters.get(SimpleSearcherRunnable.FORMAT_FILTER);
		String[] filename_splited;
		String fileformat;
		if (format != null) {
			filename_splited = file.getName().split("/\\./");
			int FORMAT_FIELD = filename_splited.length-1;
			fileformat = filename_splited[FORMAT_FIELD];

			if (!fileformat.contains(format)) {
				return false;
			}
		}

		Double size = (Double) filters.get(SimpleSearcherRunnable.SIZE_FILTER);
		double fileSize;
		int kilobyte = 1024;
		if (size != null) {
			fileSize = file.length() / kilobyte;

			if (fileSize < size) {
				return false;
			}
		}

		Date date = (Date) filters.get(SimpleSearcherRunnable.DATE_FILTER);
		long fileCreationDate;
		if (date != null) {
			try {
				fileCreationDate = Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime()
						.toMillis();
				if (fileCreationDate < date.getTime()) {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		}

		return true;
	}

	public List<ResultRecord> getResults() {
		return results;
	}

	public List<String> getDirectoriesFound() {
		return directoriesFound;
	}

	public boolean wasFoundDirectories() {
		return directoriesFound.size() > 0;
	}

	@Override
	public void run() {
		results = new LinkedList<ResultRecord>();
		directoriesFound = new LinkedList<String>();

		File directory = new File(directoryName);

		if (!directory.isDirectory()) {
			return;
		}

		try {
			File[] filesList = directory.listFiles();
			for (File file : filesList) {
				if (file.isDirectory()) {
					try {
						directoriesFound.add(file.getCanonicalPath());
					} catch (IOException e) {
						directoriesFound.add(file.getPath());
					}
				} else if (fileMatchesFiltersList(file)) {
					results.add(new ResultRecord(file.getName(), file.getPath()));
				}
			}	
		} catch(SecurityException | NullPointerException ex ) {
			System.out.println("Não foi possível pesquisar no diretório " + directory.getName());
		}
		
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

}
