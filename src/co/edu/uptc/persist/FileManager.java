package co.edu.uptc.persist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	private File file;

	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;

	public BufferedReader getBufferedReader() {
		return this.bufferedReader;
	}

	public FileManager(String nameFile) {
		file = new File(nameFile);
	}

	public void openFile(char modo) {
		try {
			if (modo == 'w') {
				fileWriter = new FileWriter(file, true);
				bufferedWriter = new BufferedWriter(fileWriter);
			} else {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() {
		String cad = "";
		try {
			cad = bufferedReader.readLine();
		} catch (IOException | NullPointerException | OutOfMemoryError e) {
			e.printStackTrace();
		}
		return cad;
	}

	public List<String> readFile() {
		List<String> lines = new ArrayList<>();
		String line = "";
		openFile('r');
		while ((line = readLine()) != null) {
			lines.add(line);
		}
		closeFile();
		return lines;
	}

	public void writeFile(String line) {
		openFile('w');
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeFile(); // Cerrar el archivo después de escribir
		}

	}

	public void closeFile() {
		try {
			if (bufferedReader != null)
				bufferedReader.close();
			if (bufferedWriter != null)
				bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean exist() {
		return file.exists();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
