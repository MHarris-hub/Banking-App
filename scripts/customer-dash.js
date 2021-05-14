//retrieve account balance
getbalancebtn.addEventListener("click", () => {
     let accountid = document.getElementById("fromaccountid").value;
     let url = "http://localhost:9000/customer-dash/" + accountid;

     fetch(url)
         .then(res => res.json())
         .then(res1 => {
               let data = "<table class='table table-bordered table-striped'><thead class='thead-dark'><tr><th>Account ID</th><th>Balance</th></thead>";
             
               data = data + "<tr><td>" + accountid + "</td>";
               data = data + "<td>" + res1 + "</td>";

               data = data + "</table>";
               document.getElementById("results").innerHTML = data;
         });

     document.getElementById("fromaccountid").value = "";
})

//deposit to account
depositbtn.addEventListener("click", () => {
	let url = "http://localhost:9000/customer-dash";
     document.getElementById("deposit-message").hidden = true;

	fetch(url, {
          method: 'POST',
          body: JSON.stringify({
               accountid:          document.getElementById('toaccountid').value,
               amount:             document.getElementById('deposit').value,
               transactiontype:    "deposit"
          }),
          headers: {
          'Content-type': 'application/json; charset=UTF-8',
          },
     })
.then((res1) => res1.json())
.then(res2 => {
     document.getElementById("deposit-message").textContent = res2;
     document.getElementById("deposit-message").hidden = false;
});
document.getElementById("toaccountid").value = "";
document.getElementById("deposit").value = "";
})

//withdraw from account
withdrawbtn.addEventListener("click", () => {
	let url = "http://localhost:9000/customer-dash";
     document.getElementById("withdraw-message").hidden = true;

	fetch(url, {
          method: 'POST',
          body: JSON.stringify({
               accountid:          document.getElementById('fromaccountid').value,
               amount:             document.getElementById('withdraw').value,
               transactiontype:    "withdrawal"
          }),
          headers: {
          'Content-type': 'application/json; charset=UTF-8',
          },
     })
.then((res1) => res1.json())
.then(res2 => {
     document.getElementById("withdraw-message").textContent = res2;
     document.getElementById("withdraw-message").hidden = false;
});
document.getElementById("fromaccountid").value = "";
document.getElementById("withdraw").value = "";
})

//transfer funds
transferbtn.addEventListener("click", () => {
	let url = "http://localhost:9000/customer-dash";
     document.getElementById("transfer-message").hidden = true;

	fetch(url, {
          method: 'POST',
          body: JSON.stringify({
               fromaccountid:      document.getElementById('fromaccountid').value,
               toaccountid:        document.getElementById('toaccountid').value,
               amount:             document.getElementById('transfer').value,
               transactiontype:    "transfer"
          }),
          headers: {
          'Content-type': 'application/json; charset=UTF-8',
          },
     })
.then((res1) => res1.json())
.then(res2 => {
     document.getElementById("transfer-message").textContent = res2;
     document.getElementById("transfer-message").hidden = false;
});
document.getElementById("fromaccountid").value = "";
document.getElementById("toaccountid").value = "";
document.getElementById("transfer").value = "";
})