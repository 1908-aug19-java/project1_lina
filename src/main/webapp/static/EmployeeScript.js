/**
 * 
 */
console.log("hello from employee script!");

let token = sessionStorage.getItem("token");
console.log(token);

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
// if(!token){
//     window.location.href = "http://localhost:8080/Reimbursment/login";
// }else{
//     let tokenArr = token.split(":");
// 	console.log("token got from sessionStorage: "+ tokenArr);
// 	if(tokenArr.length===2){
//         if(tokenArr[1] == "general"){
//             let baseUrl = "http://localhost:8080/Reimbursment/api/employee/user?id=";
//             console.log("home script user id:"+ tokenArr[0]);
//             sendAjaxGet(baseUrl+tokenArr[0], displayName);
//         }else {
//             console.log("Wrong title of employee!");
//         }
		
// 	} else {
// 		window.location.href="http://localhost:8080/Reimbursment/login";
// 	}
// }

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			console.log("got the employee successfully");
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
	console.log("USER: " + user);
    document.getElementById("welcome-user").innerHTML = user.name;	
}

 document.getElementById("logout").addEventListener("click", returnLogin);

 function returnLogin(){
	window.location.href="http://localhost:8080/Reimbursement/login";
 }

