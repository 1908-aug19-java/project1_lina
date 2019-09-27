/**
 * 
 */
console.log("hello from employee-view script!");
let status =document.getElementById("request-status");
status.style.visibility = "hidden";
let table = document.getElementById("request-table");
table.style.visibility = "hidden";
let resolvedBy = document.getElementById("resolved-by");
let resolvedDate = document.getElementById("resolved-date");
resolvedBy.style.visibility = "hidden";
resolvedDate.style.visibility = "hidden";
let token = sessionStorage.getItem("token");
console.log(token);
let tokenArr=token.split(":");
console.log("ID from token: "+tokenArr[0] +" title from token: "+ tokenArr[1]);

if(!token){
    window.location.href = "http://localhost:8080/Reimbursement/login";
}else{
    let tokenArr = token.split(":");
	console.log("token got from sessionStorage: "+ tokenArr);
	if(tokenArr.length===2){
        if(tokenArr[1] == "general"){
            let baseUrl = "http://localhost:8080/Reimbursement/api/employee/user?id=";
            console.log("home script user id:"+ tokenArr[0]);
            sendAjaxGet(baseUrl+tokenArr[0], displayName);
        }else {
            console.log("Wrong title of employee!");
        }
		
	} else {
		window.location.href="http://localhost:8080/Reimbursement/login";
	}
}

function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			window.location.href="http://localhost:8080/Reimbursement/login";
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	let user = JSON.parse(xhr.response);
//	console.log(user);
    document.getElementById("welcome-user").innerHTML = user.name;	
}

document.getElementById("pending-btn").addEventListener("click", showPending);

function showPending(){
	status.style.visibility = "visible";
	status.innerHTML = "Pending";
	console.log("enter showPending func");
	let baseUrl = "http://localhost:8080/Reimbursement/api/employee/pending?id=";
	sendAjaxPendingGet(baseUrl+tokenArr[0], displayPendingRequest);
}

function sendAjaxPendingGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function (){
		if(this.readyState === 4 && this.status === 200){
			console.log("get pending request successfully");
			callback(xhr.response);
		}
		else{
			let info=document.createElement("p");
			info.innerHTML ="failur to get pending";

		}

	}	
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayPendingRequest(requestJSON){
	if(requestJSON){
		let requests = JSON.parse(requestJSON);
	//	let table = document.getElementById("request-table");
	//	let status =document.getElementById("request-status");
		
		table.style.visibility = "visible";
		for(request of requests){
			let newRow = document .createElement("tr");
			newRow.innerHTML =`<td>${request.requestTime}</td><td>${request.requestAmount}</td><td>${request.spendCategory}</td><td>${request.status}</td>`;
			table.appendChild(newRow);
		}
	}else{
		console.log("issue getting pending requests");
	}
}

// view resolved button
document.getElementById("resolved-btn").addEventListener("click", showResolvedRequest);

function showResolvedRequest(){
	status.style.visibility = "visible";
	status.innerHTML = "Resolved";
	let baseUrl = "http://localhost:8080/Reimbursement/api/employee/resolved?id=";
	sendAjaxResolvedGet(baseUrl+tokenArr[0], displayResolvedRequest);
}
function sendAjaxResolvedGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function (){
		if(this.readyState === 4 && this.status === 200){
			console.log("get resolved request successfully");
			callback(xhr.response);
		}
		else{
			let info=document.createElement("p");
			info.innerHTML ="failur to get resolved request";

		}

	}	
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayResolvedRequest(requestJSON){
	if(requestJSON){
		let requests = JSON.parse(requestJSON);
		//let table = document.getElementById("reqest-table");
		
		table.style.visibility = "visible";
		resolvedBy.style.visibility = "visible";
		resolvedDate.style.visibility = "visible";
		let oldRow = document.getElementsByTagName("tr");
		oldRow.innerHTML = '';
		for(request of requests){
			let newRow = document .createElement("tr");
			newRow.innerHTML =`<td>${request.requestTime}</td><td>${request.requestAmount}</td><td>${request.spendCategory}</td><td>${request.status}</td><td>${request.approvedBy.name}</td><td>${request.resolveDate}</td>`;
			table.appendChild(newRow);
		}
	}else{
		console.log("issue getting resolved requests");
	}
}
//all button
document.getElementById("all-btn").addEventListener("click", showAllRequest);

function showAllRequest(){
	status.style.visibility = "visible";
	status.innerHTML = "All";
	let baseUrl = "http://localhost:8080/Reimbursement/api/employee/all?id=";
	sendAjaxResolvedGet(baseUrl+tokenArr[0], displayAllRequest);
}
function sendAjaxAllGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function (){
		if(this.readyState === 4 && this.status === 200){
			console.log("get all request successfully");
			callback(xhr.response);
		}
		else{
			let info=document.createElement("p");
			info.innerHTML ="failur to get all request";

		}

	}	
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayAllRequest(requestJSON){
	if(requestJSON){
		let requests = JSON.parse(requestJSON);
		//let table = document.getElementById("reqest-table");
		
		table.style.visibility = "visible";
		resolvedBy.style.visibility = "visible";
		resolvedDate.style.visibility = "visible";
		let oldRow = document.getElementsByTagName("tr");
		oldRow.innerHTML = "";
		for(request of requests){
			let newRow = document .createElement("tr");
			newRow.innerHTML =`<td>${request.requestTime}</td><td></td><td>${request.requestAmount}</td><td></td><td>${request.spendCategory}</td><td></td><td>${request.status}</td><td></td><td>${request.resolveDate}</td><td></td><td>${request.approvedBy.name}</td>`;
			table.appendChild(newRow);
		}
	}else{
		console.log("issue getting all requests");
	}
}


//logout button
document.getElementById("logout").addEventListener("click", returnLogin);

function returnLogin(){
	window.location.href="http://localhost:8080/Reimbursement/login";
}

	