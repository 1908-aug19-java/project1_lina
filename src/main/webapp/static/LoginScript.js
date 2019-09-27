/**
 * 
 */

 console.log("welcome from login script");
 
 document.getElementById("login-btn").addEventListener("click", requestLogin);

 function requestLogin(){
	console.log("enter requestlogin function");
    let url = "http://localhost:8080/Reimbursement/login";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		console.log("in ready state function");
		if(this.readyState === 4 && this.status===200){
			// set authorization in our browser for future request
			 let auth = xhr.getResponseHeader("Authorization");
			 sessionStorage.setItem("token", auth);
             console.log("this token from login script:" +auth);
             let tokenArr = auth.split(":");
             let title = tokenArr[1];
             if( title == "general"){
                 window.location.href="http://localhost:8080/Reimbursement/employee";
             }
             else if(title == "manager"){
                 window.location.href = "http://localhost:8080/Reimbursement/manager";
             }
			 else{
                 console.log("error: not valid title");
             }
		}
		if(this.readyState === 4 ){
			console.log(this);
		}
	}
	let user = document.getElementById("login-name").value;
	let pass = document.getElementById("login-password").value;
	if(!user || ! pass){
		console.log("please enter the username and password!");
	}
	console.log("user: "+ user);
	
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	
	let requestBody = `username=${user}&password=${pass}`;
	
	xhr.send(requestBody);
}


//register
document.getElementById("register-form").style.visibility = "hidden";
document.getElementById("register-btn").addEventListener("click", showRegisterForm);

function showRegisterForm(){
    document.getElementById("register-form").style.visibility = "visible";
}

//registration information submitted

