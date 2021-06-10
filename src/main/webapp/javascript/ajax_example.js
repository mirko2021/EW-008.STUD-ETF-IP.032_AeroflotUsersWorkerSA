"UTF-8"

/**
 * Опште функционалности за потребе Јаава Скрипт АЈАКС примјера. 
 */
 
class ExampleInfo {
  constructor(data) {
    if(data===null) data = ''; 
    this.data = data;
  }
}
 
function ajax_get_еxample_info(app_path, backward){
	 var xhttp = new XMLHttpRequest();  
	 xhttp.onreadystatechange = function(){
		if (xhttp.readyState == 4 && xhttp.status == 200) {
		   var response = JSON.parse(xhttp.response);
		   backward(response);
		}
	 };    
	 xhttp.open("GET", "http://localhost:8080"+app_path+"/ex_info/get", true);  
	 xhttp.send();
}


function ajax_set_еxample_info(app_path, data, back_function){
		var xhttp = new XMLHttpRequest();  
		xhttp.onreadystatechange = function(){
			if (xhttp.readyState == 4 && xhttp.status == 200) {
    		    var response = JSON.parse(xhttp.response);
			    back_function(response); 
			}
		}; 
		var request = {data : data}; 
		xhttp.open("POST", "http://localhost:8080"+app_path+"/ex_info/set", true);
		xhttp.send(JSON.stringify(request));
}