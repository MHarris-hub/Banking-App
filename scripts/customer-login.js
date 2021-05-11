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
.then((response) => response.json());
})