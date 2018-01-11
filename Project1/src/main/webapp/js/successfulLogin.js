/**
 * 
 */

window.onload = function(){
	unloadLogin();
}

function unloadLogin(){

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('good-login').innerHTML = xhr.responseText;
		loadInfo();
		}
	}
	xhr.open("GET","navbar",true);
	xhr.send();
}

function loadInfo(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		var user = JSON.parse(xhr.responseText);
		document.getElementById('username').innerHTML += user.firstName + " " + user.lastName;
		}
	}
	xhr.open("GET","getaccountinfo",true);
	xhr.send();
}




