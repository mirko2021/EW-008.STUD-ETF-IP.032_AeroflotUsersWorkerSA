package yatospace.worker.web.registration;

import yatospace.user.util.UserCredentials;

/**
 * Провјера да ли је у питању запослени и 
 * кориосничко име и лозинка. с
 * @author VM
 * @version 1.0
 */
public class WorkerCredentialsTester {
	private UserBasicDAO userBasicDAO; 
	private UserRoleDAO userInfoDAO; 
	
	public WorkerCredentialsTester(UserBasicDAO userBasicDAO, UserRoleDAO userInfoDAO) {
		if(userBasicDAO==null) throw new NullPointerException(); 
		if(userInfoDAO==null) throw new NullPointerException();
		this.userBasicDAO = userBasicDAO;
		this.userInfoDAO  = userInfoDAO;
	}
	
	public UserBasicDAO getUserBasicDAO() {
		return userBasicDAO;
	}


	public void setUserBasicDAO(UserBasicDAO userBasicDAO) {
		this.userBasicDAO = userBasicDAO;
	}

	public UserRoleDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserRoleDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	public boolean testWokrker(String worker, String password) {
		UserBasicDTO basicDTO = userBasicDAO.get(worker);
		if(basicDTO==null) return false;
		UserWorkerDTO workerDTO = userInfoDAO.get(basicDTO.getUserId());
		if(workerDTO==null) return false;
		if(!workerDTO.isWorker()) return false; 
		String oldPassCodeRecord = basicDTO.getPasswordcode();  
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setPasswordSalt(oldPassCodeRecord.split("\\$")[0]);
		userCredentials.setPasswordPlain(password).hashPasswordToRecord();  
		return oldPassCodeRecord.contentEquals(userCredentials.getPasswordRecord());
	}
	
	public boolean existsUser(String username) {
		UserBasicDTO basicDTO = userBasicDAO.get(username);
		if(basicDTO==null) return false;
		UserStandardDTO userDTO = userInfoDAO.user(basicDTO.getUserId());
		if(userDTO==null) return false;
		if(!userDTO.isUser()) return false;
		return true;
	}
}
