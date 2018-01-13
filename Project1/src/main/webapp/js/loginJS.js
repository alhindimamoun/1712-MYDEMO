/**
 * 
 */

window.onload = function(){
	existUserLoad();
}


//VIEW FOR ALL USERS
function existUserLoad(){

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginview').innerHTML = xhr.responseText;
		document.getElementById('eSignin').addEventListener("click",employeeSignin,false);
		document.getElementById('aSignin').addEventListener("click",employerSignin,false);
	}
	}
	xhr.open("GET","existUser",true);
	xhr.send();
}



//VIEWS FOR LOGIN
function employeeSignin(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginbox').innerHTML = xhr.responseText;
		document.getElementById('newUser').addEventListener("click",getNewUserEmployee,false);
	}
	}
	xhr.open("GET","employeelogin",true);
	xhr.send();
}




function employerSignin(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginbox').innerHTML = xhr.responseText;
		document.getElementById('newUser').addEventListener("click",getNewUserEmployer,false);
	}
	}
	xhr.open("GET","employerlogin",true);
	xhr.send();
}







//REGISTER ----------------------------------------------------------------------------------------------
function getNewUserEmployee(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginview').innerHTML = xhr.responseText;
		document.getElementById('backButton').addEventListener("click",existUserLoad,false);
		document.getElementById('confirm').addEventListener("click",registerNewUser,false);
		}
	}
	
	xhr.open("GET","newuser",true);
	xhr.send();
}


function getNewUserEmployer(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginview').innerHTML = xhr.responseText;
		document.getElementById('backButton').addEventListener("click",existUserLoad,false);
		document.getElementById('confirm').addEventListener("click",registerNewUserEmpoloyer,false);
		}
	}
	
	xhr.open("GET","newuseradmin",true);
	xhr.send();
}







//ALERTS-----------------------------------------------------------------------------------------------------
function userStatus(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('alerts').innerHTML = xhr.responseText;
		}
	}
	
	xhr.open("GET","alert",true);
	xhr.send();
}




















//SEND REGISTRATION -----------------------------------------------------------------------------------------


function registerNewUser(){
	
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;
	
	var user = {
		username: username,
		password: password,
		email: email,
		firstname: firstname,
		lastname: lastname
	}
	
	
	user = JSON.stringify(user);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState ==4 && xhr.status == 200){
			console.log(xhr.responseText);
			userStatus();
			existUserLoad();
		}
	}
	xhr.open("POST","newuser",true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(user);
}




function registerNewUserEmpoloyer(){
	
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;	
	
	jArray = JSON.stringify(jArray);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState ==4 && xhr.status == 200){
			console.log(xhr.responseText);
			userStatus();
			existUserLoad();
		}
	}
	xhr.open("POST","newuseradmin",true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(jArray);
}



