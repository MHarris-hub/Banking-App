submit.addEventListener("click", () => {
     
     let accountid = document.getElementById("accountid").value;
     let url = "http://localhost:9000/employee-dash/" + accountid;
     
     fetch(url)

         .then(res => res.json())
         .then(res1 => {             
               let data = "<table class='table table-bordered table-striped'><thead class='thead-dark'><tr><th>Transaction ID</th><th>Transaction Type</th><th>Amount</th><th>To Account</th><th>From Account</th></thead>";
             
             res1.forEach(element => {
                 data = data + "<tr><td>" + element.id + "</td>"
                 data = data + "<td>" + element.type + "</td>"
                 data = data + "<td>" + element.amount + "</td>"
                 data = data + "<td>" + element.toAccount + "</td>"
                 data = data + "<td>" + element.fromAccount + "</td>"
                 //data = data + "<td>" + element.timestamp + "</td>" //need to format so it's human readable
             });
             data = data + "</table>"
             document.getElementById("results").innerHTML = data;
         });
 })