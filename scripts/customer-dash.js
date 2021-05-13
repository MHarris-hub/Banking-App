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

     //     if (document.getElementById("depositdd").selected == true)
     //      alert("selected")

})

//deposit to account
depositbtn.addEventListener("click", () => {
	let url = "http://localhost:9000/customer-dash";

	fetch(url, {
          method: 'POST',
          body: JSON.stringify({
               accountid:     document.getElementById('toaccountid').value,
               amount:        document.getElementById('deposit').value,
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
// document.getElementById("toaccountid").value = "";
// document.getElementById("deposit").value = "";
})