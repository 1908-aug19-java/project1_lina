/**
 * 
 */
console.log("hello from employee-request script!");

let token = sessionStorage.getItem("token");
console.log("TOKEN:"+ token);

if(!token){
	console.log("NO TOKEN FOUND");
    window.location.href = "http://localhost:8080/Reimbursement/login";
}else{
    let tokenArr = token.split(":");
	console.log("token got from sessionStorage: "+ tokenArr);
	if(tokenArr.length===2){
		//console.log("enter tokenArr==2 branch");
        if(tokenArr[1] == "general"){
            let baseUrl = "http://localhost:8080/Reimbursement/api/employee/user?id=";
           // console.log("home script user id:"+ tokenArr[0]);
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
	console.log("USER returned in json:"+user);
    document.getElementById("welcome-user").innerHTML = user.name;	
}

document.getElementById("request-btn").addEventListener("click", newRequest);

function newRequest(){
	let url = "http://localhost:8080/Reimbursement/api/employee/new";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function (){
		if(this.readyState === 4 && this.status === 200){
			console.log("the new request has been successfully submitted");
			document.getElementById("request-result").innerHTML = "your request is submitted successfully!";
		}

	}
	console.log("send post Ajax request");
	let tokenArr = token.split(":");
    let date = document.getElementById("request-time").value;
    console.log("date: " + date);
	let amount =document.getElementById("request-amount").value;
	console.log("amt: " + amount);
	let category = document.getElementById("request-category").value;
	console.log("cat: " + category);
	let from = tokenArr[0];   
	console.log("submitfrom id:"+tokenArr[0]);
	
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let requestBody = `requestTime=${date}&submitFrom=${from}&requestAmount=${amount}&requestCategory=${category}`;
	xhr.send(requestBody);
}

//logout button
document.getElementById("logout").addEventListener("click", returnLogin);

function returnLogin(){
	window.location.href="http://localhost:8080/Reimbursement/login";
}