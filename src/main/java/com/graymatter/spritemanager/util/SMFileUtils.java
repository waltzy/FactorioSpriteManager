package com.graymatter.spritemanager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;

public class SMFileUtils {

	public static File createOrLoadDirectory(String string) throws ProjectSetupException {
		File directory = new File(string);
		if (!directory.exists()) {
			try {
				if (!directory.mkdirs())
					throw new ProjectSetupException("Could not create directory, "
							+ "does this application have permissions to create the folder " + string + " ?");
			} catch (RuntimeException e) {
				throw new ProjectSetupException("Could not create directory, details: " + e.getMessage());
			}
		}
		
		if (!directory.isDirectory()){
			throw new ProjectSetupException("File exists at "+string+" and its not a directory.");
		}
		return directory;
	}
	
	public static <T> File ensureFile(File file, T object) throws ProjectSetupException{
		if (!file.exists()) {
			writeJson(file, object);
		}
		return file;
	}
	
	public static <T> File writeJson(File file, T object) throws ProjectSetupException{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return writeFile(file, gson.toJson(object));
		
	}	
	
	public static File writeFile(File file, String string) throws ProjectSetupException{
		file = createOrLoadFile(file.getAbsolutePath());
		PrintWriter out;
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			throw new ProjectSetupException("could not create info.json "+ e.getMessage(), e);
		}
		out.print(string);
		out.flush();
		out.close();
		return file;
	}	
	
	public static File createOrLoadFile(String string) throws ProjectSetupException {
		File file = new File(string);
		if (!file.exists()) {
			try {
				if (!file.createNewFile())
					throw new ProjectSetupException("Could not create file, "
							+ "does this application have permissions to create the file " + string + " ?");
			} catch (RuntimeException | IOException e) {
				throw new ProjectSetupException("Could not create file, details: " + e.getMessage());
			}
		}
		
		if (file.isDirectory()){
			throw new ProjectSetupException("Directory already exists at "+string+" and its not a File.");
		}
		return file;
	}
	
	public static String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}
	
	public static Object getJson(String file, Class<?> clazz) throws ProjectSetupException{
		try {
			return new Gson().fromJson(readFile(file), clazz);
		} catch (JsonSyntaxException | IOException e) {
			throw new ProjectSetupException(e.getMessage());
		}
	}
	
}
