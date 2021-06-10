package yatospace.worker.services.file;

import java.io.File;

/**
 * Опште функционалност за конролере баратања 
 * датотекама спецификације превоза робе.
 * @author VM
 * @version 1.0
 */
public interface GeneralTransportSpecificationFileController {
	public void add(String rId, String name, byte[] data);
	public void remove(String rid, String name);
	public File get(String rId, String name);
	public void rename(String rId, String oldName, String neoName);
	public void update(String rId, String oldname, String neoname, byte[] data);
	public void put(String rId, String name, byte[] data);
}
