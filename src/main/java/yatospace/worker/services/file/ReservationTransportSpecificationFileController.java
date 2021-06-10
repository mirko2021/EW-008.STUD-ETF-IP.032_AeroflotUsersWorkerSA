package yatospace.worker.services.file;

import java.io.File;

import yatospace.worker.services.io.ReservationsDAO;

/**
 * Контролер којим се евидентирају и баратају датотеке, 
 * које се односе на спецификацију транспорта робе.
 * @author VM
 * @version 1.0
 */
public class ReservationTransportSpecificationFileController implements GeneralTransportSpecificationFileController{
	private ArticleTransportSpecificationFileController astf; 
	private ReservationsDAO rDAO; 
	
	public ReservationTransportSpecificationFileController(ArticleTransportSpecificationFileController astf, ReservationsDAO rDAO) {
		if(astf==null) throw new NullPointerException("ArticleTransportSpecificationFileController");
		if(rDAO==null) throw new NullPointerException("ReservationsDAO");
		this.astf = astf;
		this.rDAO = rDAO;
	}

	public ArticleTransportSpecificationFileController getAstf() {
		return astf;
	}

	public ReservationsDAO getDAO() {
		return rDAO;
	}

	@Override
	public void add(String rId, String name, byte[] data) {
		astf.add(name, data);
		rDAO.setReservationInfo(rId, name);
	}

	@Override
	public void remove(String rId, String name) {
		astf.remove(name);
		rDAO.resetReservationInfo(rId);
	}

	@Override
	public File get(String rId, String name) {
		if(rDAO.get(rId).getArticleTransportFile().contentEquals(name))
			return astf.get(name);
		else 
			return null;
	}

	@Override
	public void rename(String rId, String oldName, String neoName) {
		astf.rename(oldName, neoName);
		rDAO.setReservationInfo(rId, neoName);
	}

	@Override
	public void update(String rId, String oldname, String neoname, byte[] data) {
		astf.update(oldname, neoname, data);
		rDAO.setReservationInfo(rId, neoname);
	}

	@Override
	public void put(String rId, String name, byte[] data) {
		String oldFile = rDAO.get(rId).getArticleTransportFile(); 
		if(oldFile!=null && oldFile.trim().length()!=0 && !oldFile.contentEquals(name)) astf.remove(oldFile);
		astf.put(name, data);
		rDAO.setReservationInfo(rId, name);
	}	
}
