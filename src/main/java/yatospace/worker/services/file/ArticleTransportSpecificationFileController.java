package yatospace.worker.services.file;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Контролер који се односи на баратање датотекама 
 * спецификације превоза робе. Чувају се на 
 * серверу у бази података и информација о 
 * њима је повезана у табели за резервације 
 * у бази података. 
 * @author VM
 * @version 1.0
 */
public class ArticleTransportSpecificationFileController{
	public static final boolean ERROR_REMIX = true;
	public static final String ROOT_DIR = "C:\\Eclipse-Workspaces\\EW-008\\026_AeroflotUsersWorkerSA\\upoload"; 
	
	static {
		try {
			File root = new File(ROOT_DIR);
			if(!root.exists()) root.mkdirs(); 
		}catch(Exception ex) {
			if(ERROR_REMIX) ex.printStackTrace();
		}
	}
	
	public void add(String name, byte[] data) {
		try {
			if(name==null) throw new NullPointerException();
			if(data==null) throw new NullPointerException();
			if(name.contains(File.separator)) throw new RuntimeException("Name isn't well formed.");
			File file = new File(ROOT_DIR, name);
			if(file.exists()) throw new RuntimeException("File exists.");
			file.createNewFile();
			try(FileOutputStream fos = new FileOutputStream(file)){
				fos.write(data);
			}catch(RuntimeException ex) {
				throw ex; 
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public void remove(String name) {
		try {
			if(name==null) throw new NullPointerException();
			if(name.contains(File.separator)) throw new RuntimeException("Name isn't well formed.");
			File file = new File(ROOT_DIR, name);
			if(file.exists()) file.delete(); 
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public File get(String name) {
		if(name==null) return null;
		if(name.contains(File.separator)) return null;
		File file = new File(ROOT_DIR, name);
		if(!file.exists()) return null; 
		return file; 
	}
	
	public void rename(String oldName, String neoName) {
		try {
			File file = get(oldName);
			if(file==null) throw new RuntimeException("File not found.");
			if(neoName==null) throw new NullPointerException("Name is illegal.");
			if(neoName.contains(File.separator)) throw new RuntimeException("Name is illegal.");
			if(get(neoName)!=null) throw new RuntimeException("Destination name is used.");
			file.renameTo(new File(neoName));
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void update(String oldname, String neoname, byte[] data) {
		File old = get(oldname);
		File neo = get(neoname);
		if(old==null) throw new RuntimeException("Source not found.");
		if(neo!=null) throw new RuntimeException("Destination is in used.");
		remove(oldname);
		add(neoname, data);
	}
	
	public void put(String name, byte[] data) {
		if(get(name)!=null) remove(name);
		add(name, data);
	}
}
