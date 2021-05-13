//login (method is post so that username/password are not displayed in url)
login.addEventListener("click", () => {
	let url = "http://localhost:9000/customerlogin";

	fetch(url, {
	method: 'POST',
	body: JSON.stringify({
		username: document.getElementById('username').value,
		password: document.getElementById('password').value,
	}),
	headers: {
	'Content-type': 'application/json; charset=UTF-8',
	},
})
.then((res1) => res1.json())
.then(res2 => {
     if (res2 == "authentication failure")
          document.getElementById("failed-login-message").hidden = false;
     else
          window.location.href = 'customer-dash.html'
});
document.getElementById("username").value = "";
document.getElementById("password").value = "";
})