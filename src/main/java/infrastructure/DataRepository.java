package infrastructure;

import java.io.*;
import java.util.*;

/**
 * A repository of data formed by tuples <String, Integer>
 * where the Strings are Keys (must be unique...).
 * The repository is a simple text file.
 * All the operations that modify the contents of this
 * repository perform an "autocommit" (i.e., they save the
 * data to disk).
 * @author rbejar
 *
 */
public class DataRepository {
	private HashMap<String,Integer> data;
	private String filePath;

	public DataRepository(String filePath) throws Exception {
		this.filePath = filePath;
		data = read();
	}

	public void reload() throws Exception {
		data = read();
	}

	public void save() throws Exception {
		write(data);
	}

	public Integer getValue(String key) {
		return data.get(key);
	}

	public List<String> getAllKeys() {
		// I create and return a list instead of returning the keySet
		// because changes in that keySet would be reflected in the
		// HashMap, and I do not want to allow that
		ArrayList<String> list = new ArrayList<String>();
		for(String key: data.keySet()) {
			list.add(key);
		}
		return list;
	}

	public void insertData(String key, Integer value) throws Exception {
		if (!data.containsKey(key)) {
			data.put(key, value);
		} else {
			throw new Exception("key already exists in data. Did you want to update instead?");
		}
		// "Auto-commit" mode
		save();
	}

	public void updateData(String key, Integer value) throws Exception {
		if (data.containsKey(key)) {			
			data.put(key, value);
		} else {
			throw new Exception("key did not exist in data. Did you want to insert instead?");
		}	
		// "Auto-commit" mode
		save();
	}

	public void deleteData(String key) throws Exception {
		if (data.remove(key) == null) {
			// I could ignore this. Deleting something which does not exist is normally
			// not harmful, but I prefer to know if I try to delete something which is
			// not there. I can always ignore this exception in the client code
			throw new Exception("key did not exist in data, you can't delete it");
		}
		// "Auto-commit" mode
		save();
	}

	private HashMap<String,Integer> read() throws Exception  {
		// Assume it is a small file, that we can read fast into memory				
		File file = new File(filePath);

		HashMap<String,Integer> data = new HashMap<String,Integer>();
		try (Scanner scanner =  new Scanner(file, "UTF-8")) {
			while (scanner.hasNext()) {			
				String country = scanner.next();
				int population = scanner.nextInt();
				data.put(country, new Integer(population));	        
			}      
		}

		return data;		
	}

	private void write(HashMap<String,Integer> data) throws Exception  {				
		File file = new File(filePath);

		try (FileWriter fw = new FileWriter(file)) {
			try (BufferedWriter bw = new BufferedWriter(fw)){
				for(String key: data.keySet()){
					String line = key + " " + data.get(key).toString();
					bw.write(line);
					bw.newLine();
				}
			}	
		}
	}

}
