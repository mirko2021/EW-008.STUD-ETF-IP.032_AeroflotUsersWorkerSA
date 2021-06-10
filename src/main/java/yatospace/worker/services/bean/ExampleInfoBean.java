package yatospace.worker.services.bean;

import java.io.Serializable;

/**
 * Примјер инфо зрна. 
 * @author VM
 * @version 1.0
 */
public class ExampleInfoBean implements Serializable{
	private static final long serialVersionUID = -514306889786505402L;
	private String data = "Hello from json object.";

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
