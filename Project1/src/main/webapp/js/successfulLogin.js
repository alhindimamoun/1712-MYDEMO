/**
 * 
 */

window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		var user = JSON.parse(xhr.responseText);
		if(user.type === 0)
		{
		employeeLogin();
		}
		else if(user.type ===1)
		{
		 adminLogin();
		}
		}
	}
	xhr.open("GET","getaccountinfo",true);
	xhr.send();
}



//SUCCESSFULL LOGINS
function employeeLogin(){

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('good-login').innerHTML = xhr.responseText;
		document.getElementById('logout').addEventListener('click',logout,false)
		document.getElementById('account').addEventListener('click',infoView,false);
		document.getElementById('tickets').addEventListener('click',tableLoad,false);
		loadInfo();
		}
	}
	xhr.open("GET","navbar",true);
	xhr.send();
}

function adminLogin(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('good-login').innerHTML = xhr.responseText;
		document.getElementById('logout').addEventListener('click',logout,false);
		document.getElementById('account').addEventListener('click',infoView,false);
		document.getElementById('tickets').addEventListener('click',tableLoad,false);
		loadInfo();
		}
	}
	xhr.open("GET","adminnavbar",true);
	xhr.send();
}


//USED TO LOAD INFO INTO NAVBAR
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

function infoView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('main-view').innerHTML = xhr.responseText;
			provideInfo();
		}
	}
	xhr.open("GET","accountview",true);
	xhr.send();
}


//Table Loaded 
function tableLoad(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('main-view').innerHTML = xhr.responseText;
		findTypeTableLoad();
		}
	}
	xhr.open("GET","loadtable",true);
	xhr.send();
}

//Load Information Into The Table
function findTypeTableLoad(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		var user = JSON.parse(xhr.responseText);
		if(user.type === 0)
		{
		loadEmployeeTableInfo();
		}
		else if(user.type ===1)
		{
		 loadManagerTableInfo();
		}
	}
	}
	xhr.open("GET","getaccountinfo",true);
	xhr.send();
} 

function loadEmployeeTableInfo(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		console.log(xhr.responseText);
		}
	}
	xhr.open("GET","getemployeetickets",true);
	xhr.send();
}

function loadManagerTableInfo(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		console.log(xhr.responseText);
		}
	}
	xhr.open("GET","getmanagertickets",true);
	xhr.send();
	
}

//ACCOUT INFO LOADED WHEN NAVBAR ACCOUNT INFO CLICKED 
function provideInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		
		var user = JSON.parse(xhr.responseText);
		var root = document.getElementById('startinfo')
		var div = document.createElement('div');
		div.className = 'text-center text-white';
		div.innerHTML = "User Name: " + user.username;
		root.appendChild(div);
		var div = document.createElement('div');
		div.className = 'text-center text-white';
		div.innerHTML = "First Name: " + user.firstName;
		root.appendChild(div);
		var div = document.createElement('div');
		div.className = 'text-center text-white';
		div.innerHTML = "Last Name: " + user.lastName;
		root.appendChild(div);
		var div = document.createElement('div');
		div.className = 'text-center text-white';
		div.innerHTML = "Email: " + user.email;
		root.appendChild(div);
		var div = document.createElement('div');
		div.className = 'text-center text-white';
		div.innerHTML = "Status: ";
		
		if(user.type == 0)
		{
		div.innerHTML += 'Employee'
		}
		
		else if(user.type == 1)
		{
		div.innerHTML += 'Manager'
		}
		
		root.appendChild(div);
		
		}
	}
	xhr.open("GET","getaccountinfo",true);
	xhr.send();
}


//LOGOUT 
function logout(){
	var xhr = new XMLHttpRequest();
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginview').innerHTML = xhr.responseText;
		document.getElementById('eSignin').addEventListener("click",employeeSignin,false);
		document.getElementById('aSignin').addEventListener("click",employerSignin,false);
	}
	xhr.open("POST","logout",true);
	xhr.send();
}



