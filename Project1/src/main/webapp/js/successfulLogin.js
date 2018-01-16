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
		document.getElementById('newticket').addEventListener('click',newTicketPage,false);
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
		document.getElementById('employeelist').addEventListener('click',employeeTableLoad,false);
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

function employeeTableLoad(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('main-view').innerHTML = xhr.responseText;
		loadAllEmployees();
	}
	}
	xhr.open("GET","emptablestructure",true);
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
		else if(user.type === 1)
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
		
		var arrayOfTickets = JSON.parse(xhr.responseText); 
		var root = document.getElementById('tablebody');
		
		for (var i = 0; i < arrayOfTickets.length; i++) {
			var ticket = arrayOfTickets[i];
			var tr = document.createElement('tr');
			tr.id =  "r"+ticket.ticketKey;
			if(ticket.status=="CLOSED/APPROVED"){
				tr.className="bg-success";
			}
			else if(ticket.status=="CLOSED/DENIED"){
				tr.className ="bg-danger";
			}
			else if(ticket.status=="OPEN")
				{tr.className="bg-info"}
			tr.onclick = function(){rowClick(this.id);}
			root.appendChild(tr);
			var td = document.createElement('td');
			td.innerHTML = ticket.amount;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.description;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.submitted;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.resolved;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.authorFirstname;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.authorLastname;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.resolverFirstname;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.resolverLastname;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.type;
			tr.appendChild(td);
			var td = document.createElement('td');
			td.innerHTML = ticket.status;
			tr.appendChild(td);
			
		}
		
		function rowClick(rownumber)
		{
			rownumber = rownumber.replace( /^\D+/g, '');
			console.log(rownumber);
			for(var i = 0; i < arrayOfTickets.length; i++){
				
				ticketKey = arrayOfTickets[i].ticketKey;
				if(+rownumber === ticketKey)
				{
					openPicture(arrayOfTickets[i].picture);
				}
			}
		}
		
		}
	}
	xhr.open("GET","getemployeetickets",true);
	xhr.send();
}

function loadManagerTableInfo(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		
		var arrayOfTickets = JSON.parse(xhr.responseText); 
		var root = document.getElementById('tablebody');
		
		for (var i = 0; i < arrayOfTickets.length; i++) {
			var ticket = arrayOfTickets[i];
			var tr = document.createElement('tr');
			tr.id =  "r"+ticket.ticketKey;
			if(ticket.status=="CLOSED/APPROVED"){
				tr.className="bg-success";
			}
			else if(ticket.status=="CLOSED/DENIED"){
				tr.className ="bg-danger";
			}
			else if(ticket.status=="OPEN")
			{tr.className="bg-info"}
			tr.onclick = function(){rowClick(this.id);}
			root.appendChild(tr);
			var tdAmount = document.createElement('td');
			tdAmount.innerHTML = ticket.amount;
			tr.appendChild(tdAmount);
			var tdDescription = document.createElement('td');
			tdDescription.innerHTML = ticket.description;
			tr.appendChild(tdDescription);
			var tdTimeSubmitted = document.createElement('td');
			tdTimeSubmitted.innerHTML = ticket.submitted;
			tr.appendChild(tdTimeSubmitted);
			var tdTimeResolved = document.createElement('td');
			tdTimeResolved.innerHTML = ticket.resolved;
			tr.appendChild(tdTimeResolved);
			var tdEFirstname = document.createElement('td');
			tdEFirstname.innerHTML = ticket.authorFirstname;
			tr.appendChild(tdEFirstname);
			var tdELastname = document.createElement('td');
			tdELastname.innerHTML = ticket.authorLastname;
			tr.appendChild(tdELastname);
			var tdAFirstname = document.createElement('td');
			tdAFirstname.innerHTML = ticket.resolverFirstname;
			tr.appendChild(tdAFirstname);
			var tdALastname = document.createElement('td');
			tdALastname.innerHTML = ticket.resolverLastname;
			tr.appendChild(tdALastname);
			var tdType = document.createElement('td');
			tdType.innerHTML = ticket.type;
			tr.appendChild(tdType);
			var tdStatus = document.createElement('td');
			tdStatus.innerHTML = ticket.status;
			tr.appendChild(tdStatus);
			
			var tdApDe = document.createElement('td');
			if(tdStatus.innerHTML == "OPEN")
			{
			var success = document.createElement('button');
			success.className = 'btn btn-success';
			success.id = ticket.ticketKey;
			success.onclick = function(){acceptTicket(this.id);}
			var deny = document.createElement('button');
			deny.id = ticket.ticketKey;
			deny.onclick = function(){denyTicket(this.id);}
			deny.className = 'btn btn-danger';
			tdApDe.appendChild(success);
			tdApDe.appendChild(deny);
			tr.appendChild(tdApDe);
			}
			
		}
		
		function rowClick(rownumber)
		{
			rownumber = rownumber.replace( /^\D+/g, '');
			console.log(rownumber);
			for(var i = 0; i < arrayOfTickets.length; i++){
				
				ticketKey = arrayOfTickets[i].ticketKey;
				if(+rownumber === ticketKey)
				{
					openPicture(arrayOfTickets[i].picture);
				}
			}
		}
	}
		
	}
	xhr.open("GET","getadmintickets",true);
	xhr.send();
}

function openPicture(picture){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
	
		document.getElementById('main-view').innerHTML = xhr.responseText;
		var img = document.getElementById("ticketPicture");
		img.src = "data:image/png;base64," + picture;
		
	}
	}
	xhr.open("GET","showpicture",true);
	xhr.send();
}

//----MANAGE TICKET ACCEPT/DENY REQUEST
function acceptTicket(ticketNumber){
	event.stopPropagation();
	
	var acceptToken = {
		ticketNumber: ticketNumber,
		ticketType: 1
	}
	
	acceptToken = JSON.stringify(acceptToken);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState ==4 && xhr.status == 200){
			
			var errorCode = JSON.parse(xhr.responseText); 
			
			if(errorCode === 1){
				console.log("SUCCESS");
				tableRow = document.getElementById("r"+ticketNumber);
				tableRow.className = "bg-success";
				tds = tableRow.getElementsByTagName('td');
				tds[9].innerHTML = 'CLOSED/APPROVED';
				tds[10].innerHTML = "";
			}
			
			else{
				console.log("FAIL");
			}
		}
	}
	xhr.open("POST","tickethandler",true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(acceptToken);
}


function denyTicket(ticketNumber){
	
event.stopPropagation();
	
	var denyToken = {
		ticketNumber: ticketNumber,
		ticketType: 2
	}
	
	denyToken = JSON.stringify(denyToken);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState ==4 && xhr.status == 200){
			
			var errorCode = JSON.parse(xhr.responseText); 
			
			if(errorCode === 1){
				tableRow = document.getElementById("r"+ticketNumber);
				tableRow.className = "bg-danger";
				tds = tableRow.getElementsByTagName('td');
				tds[9].innerHTML = 'CLOSED/DENIED';
				tds[10].innerHTML = "";
			}
			
			else{
				alert("Failed To Make Connection To Database Change Not Persisted");
			}
		}
	}
	xhr.open("POST","tickethandler",true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(denyToken);
	
}



//LOAD EMPLOYEES
function loadAllEmployees(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
	
		var arrayOfUsers = JSON.parse(xhr.responseText); 
		var root = document.getElementById('tablebody');
		
		for(var i = 0; i < arrayOfUsers.length; i++){
		var user = arrayOfUsers[i];
		var tr = document.createElement('tr');
		root.appendChild(tr);
		var td = document.createElement('td');
		td.innerHTML = user.firstName;
		tr.appendChild(td);
		var td = document.createElement('td');
		td.innerHTML = user.lastName;
		tr.appendChild(td);
		var td = document.createElement('td');
		td.innerHTML = user.email;
		tr.appendChild(td);
		}
	}
	}
	xhr.open("GET","getallusers",true);
	xhr.send();
}

//LOAD NEW TICKET PAGE
function newTicketPage(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('main-view').innerHTML = xhr.responseText;
		document.getElementById('pictureInput').addEventListener('change',handleFileSelect,false);
	    document.getElementById('confirm').addEventListener('click',sendNewTicket,false);
	}
	}
	xhr.open("GET","newrequest",true);
	xhr.send();
	
}

//SEND NEW TICKET
function sendNewTicket(){
	formdata = new FormData();
	formdata.append('amount',document.getElementById('amount').value);
	formdata.append('description',document.getElementById('description').value);
	formdata.append('ticketType',document.getElementById('ticketType').value);
	formdata.append('picture',document.getElementById('pictureInput').files[0]);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('main-view').innerHTML = xhr.responseText;
	}
	}
	xhr.open("POST","newticketsend",true);
	xhr.send(formdata);
	
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
	xhr.onreadystatechange = function() {
	if(xhr.readyState == 4 && xhr.status == 200){
		document.getElementById('loginview').innerHTML = xhr.responseText;
		document.getElementById('eSignin').addEventListener("click",employeeSignin,false);
		document.getElementById('aSignin').addEventListener("click",employerSignin,false);
	}}
	xhr.open("POST","logout",true);
	xhr.send();
}

//Picture Function
function handleFileSelect(evt) {
    let files = evt.target.files;
    let f = files[0];
    let reader = new FileReader();
    reader.onload = function(e) {
    	$('#reimbursementReceiptImg')
    		.attr('src',	 e.target.result)
    		.width(150)
    		.height(200)
    		.show();
       };
    reader.readAsDataURL(f);
}

//--------TABLE SERACH ALGORITHMS------------------
function searchAlgorithm0() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("a");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm1() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("d");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm2() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("fn");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[4];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm3() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("ln");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[5];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm4() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("afn");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[6];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm5() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("aln");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[7];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm6() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("t");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[8];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function searchAlgorithm7() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("s");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("beginTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 2; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[9];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}



