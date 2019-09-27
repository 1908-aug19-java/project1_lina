/**
 * 
 */
/**
 * 
 */
console.log("hello from employee-profile script!");

let table = document.getElementById("profile-table");
table.style.visibility = "hidden";

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
	table.style.visibility ="visible";
	
   let user = JSON.parse(xhr.response);
   console.log(user);
   document.getElementById("welcome-user").innerHTML = user.name;	
//	   let table = document.getElementById("profile-table");
   console.log("profile table now");
   let newRow = document.createElement("tr");
   newRow.innerHTML =`<td>${user.name}</td><td></td><td type="password">${user.password}</td><td></td><td>${user.title}</td><td></td><td>${user.reportTo}</td>`;
   table.appendChild(newRow);

}





// profile navigate new button
document.getElementById("new").addEventListener("click", showNewRequest);

function showNewRequest(){
	window.location.href="http://localhost:8080/Reimbursement/employee/new";
}


//profile page	navigate view button
document.getElementById("view").addEventListener("click", showViewRequest);

function showViewRequest(){
	window.location.href="http://localhost:8080/Reimbursement/employee/view";
}
//profile page	navigate update button
document.getElementById("view").addEventListener("click", showUpdateRequest);

function showViewRequest(){
	window.location.href="http://localhost:8080/Reimbursement/employee/update";
}

//logout button
document.getElementById("logout").addEventListener("click", returnLogin);

function returnLogin(){
	window.location.href="http://localhost:8080/Reimbursement/login";
}

	