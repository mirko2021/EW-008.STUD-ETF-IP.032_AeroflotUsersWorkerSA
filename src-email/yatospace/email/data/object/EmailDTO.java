package yatospace.email.data.object;

import java.io.Serializable;

import yatospace.email.data.model.UserEmail;

/**
 * Општи модел примопредајних података, са базе, 
 * а везаних за кориснике и њихове адресе електронске 
 * поште, а при апликацији, за ИП управљање информацијама 
 * о летовима, пројектног задатка из предмета. 
 * @author VM
 * @version 1.0
 */
public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 6405268979033292553L;
	private UserEmail userEmail = new UserEmail();
	
	public UserEmail getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(UserEmail userEmail) {
		this.userEmail = userEmail;
	}
}
