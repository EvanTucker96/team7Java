/*
Bilal Ahmad
May 7, 2020
Purpose: rest calls and javascript for Agent.jsp
*/

//loads all agents to the comboBox
function loadAllAgents() {
	var req = new XMLHttpRequest();

	req.onload = function () {
		var agentsArray = JSON.parse(req.responseText);
		var agentSelect = document.getElementById("agentsSelect");

		for (i = 0; i < agentsArray.length; i++) {
			var agent = agentsArray[i];
			var option = document.createElement("option");
			option.text = agent.agtFirstName + " " + agent.agtLastName;
			option.value = agent.agentId;
			agentSelect.add(option);
		}
	}
	req.open("GET", "http://localhost:8080/JSP_Project-1/rs/agent/getallagents");
	req.send();

}

//Loads agent's data into the fields when an agent is selected
function loadAgentInfo(agentId) {
	var req = new XMLHttpRequest();
	if (agentId != "") { // agent selected
		document.getElementById("btnUpdateAgent").style.display = "block";
		document.getElementById("btnDeleteAgent").style.display = "block";
		document.getElementById("btnCancelAgent").style.display = "block";
		document.getElementById("btnInsertAgent").style.display = "none";
		document.getElementById("agtDetailsForm").style.display = "block";

	} else { // no agent selected
		document.getElementById("agtDetailsForm").style.display = "none";
	}

	req.onreadystatechange = function () {
		if (req.readyState == 4 && req.status == 200) {
			var agent = JSON.parse(req.responseText);
			console.log("agent: " + agent.agtFirstName);

			document.getElementById("AgentID").value = agent.agentId;
			document.getElementById("AgtFirstName").value = agent.agtFirstName;
			if (agent.agtMiddleInitial == null) {
				document.getElementById("AgtMiddleInitial").value = "";
			} else {
				document.getElementById("AgtMiddleInitial").value = agent.agtMiddleInitial;
			}
			document.getElementById("AgtLastName").value = agent.agtLastName;
			document.getElementById("AgtBusPhone").value = agent.agtBusPhone;
			document.getElementById("AgtEmail").value = agent.agtEmail;
			document.getElementById("AgtPosition").value = agent.agtPosition;
			document.getElementById("AgencyID").value = agent.agencyId;
		}
	}
	if (agentId != "") {
		req.open("GET", "rs/agent/getagent/" + agentId);
		req.send();
	}
}
//updates agent when update button is clicked
function updateAgent() {
	var req = new XMLHttpRequest();
	req.onload = function () {
		if (req.readyState == 4 && req.status == 200) {

			Swal.fire({
				icon: 'success',
				title: 'Agent Updated Successfully!',
				showConfirmButton: false,
				timer: 2000
			})


			console.log(req.responseText);
			reset();
			loadAllAgents();
			console.log("dropdown updated");
			setTimeout(function () {
				//select the updated agent
				document.getElementById("agentsSelect").value = document.getElementById("AgentID").value;
			}, 1000);
		} else {
			if (req.readyState != 3) {
				//setTimeout(function(){
				Swal.fire({
					icon: 'error',
					title: 'Agent Update Failed!',
					showConfirmButton: false,
					timer: 2000
				})
				//},1000)

			}
		}
	}
	if (validateFields() == true) {
		req.open("POST", "rs/agent/postagent");
		req.setRequestHeader("Content-Type", "application/json");
		req.send(grabData("update"));
	}

}

//insert's new agent when insertAgent button is clicked
function insertAgent() {
	var req = new XMLHttpRequest();
	req.onload = function () {
		if (req.readyState == 4 && req.status == 200) {
			Swal.fire({
				icon: 'success',
				title: 'Agent Inserted Successfully!',
				showConfirmButton: false,
				timer: 2000
			})
			document.getElementById("agtDetailsForm").style.display = "none";

			reset();
			loadAllAgents();
			console.log("dropdown updated");
			setTimeout(function () {
				document.getElementById("agentsSelect").selectedIndex = 0;
			}, 1000);
			document.getElementById("agentsSelect").disabled = false;
			document.getElementById("btnCancelAgent").style.display = "none";
			document.getElementById("btnInsertAgent").style.display = "none";
		} else {
			if (req.readyState != 3) {
				Swal.fire({
					icon: 'error',
					title: 'Agent Insert Failed!',
					showConfirmButton: false,
					timer: 2000
				})
			}
		}
	}
	if (validateFields() == true) {
		req.open("PUT", "rs/agent/putagent");
		req.setRequestHeader("Content-Type", "application/json");
		req.send(grabData("insert"));
	}

}

//delete's currently selected agent
function deleteAgent() {
	agentId = document.getElementById("AgentID").value;
	var xhr = new XMLHttpRequest();
	if (confirm("Delete Agent: " + agentId)) {

		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {
				reset();
				loadAllAgents();
				console.log("dropdown updated");
				Swal.fire({
					icon: 'success',
					title: 'Agent Deleted Successfully!',
					showConfirmButton: false,
					timer: 2000
				})
				document.getElementById("agentsSelect").selectedIndex = "0";
				document.getElementById("agtDetailsForm").style.display = "none";

			} else {
				if (xhr.readyState != 3) {
					Swal.fire({
						icon: 'error',
						title: 'Agent Delete Failed!',
						showConfirmButton: false,
						timer: 2000
					})
				}
			}
		}
		xhr.open("Delete", "rs/agent/deleteagent/" + agentId);
		xhr.send();
	}
}

//validates fields for correct input
function validateFields() {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var phoneFormat = /^(\+0?1\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/g;

	if (document.forms["agtDetailsForm"]["AgtFirstName"].value == "") {
		alert("First Name must be filled!", "Agents Page");
		document.forms["agtDetailsForm"]["AgtFirstName"].focus();
		return false;
	}
	if (document.forms["agtDetailsForm"]["AgtMiddleInitial"].value != "") {
		if (document.forms["agtDetailsForm"]["AgtMiddleInitial"].value.length > 5) {
			alert("Middle Initial can't be longer than 5 characters!");
			document.forms["agtDetailsForm"]["AgtMiddleInitial"].focus();
			return false;
		}
	}
	if (document.forms["agtDetailsForm"]["AgtLastName"].value == "") {
		alert("Last Name must be filled!");
		document.forms["agtDetailsForm"]["AgtLastName"].focus();
		return false;
	}
	if (document.forms["agtDetailsForm"]["AgtLastName"].value == "") {
		alert("Last Name must be filled!");
		document.forms["agtDetailsForm"]["AgtLastName"].focus();
		return false;
	}
	if (["1", "2"].includes(document.forms["agtDetailsForm"]["AgencyID"].value) == false) {
		alert("AgencyID must be 1 or 2");
		document.forms["agtDetailsForm"]["AgencyID"].focus();
		return false;
	}
	if (document.forms["agtDetailsForm"]["AgtPosition"].value == "") {
		alert("Position must be filled!");
		document.forms["agtDetailsForm"]["AgtPosition"].focus();
		return false;
	}
	if(!document.forms["agtDetailsForm"]["AgtBusPhone"].value.match(phoneFormat)){
		alert("Invalid Phone number!");
		document.forms["agtDetailsForm"]["AgtBusPhone"].focus();
		return false;
	}
	if (!document.forms["agtDetailsForm"]["AgtEmail"].value.match(mailformat)) {
		alert("You have entered an invalid email address!");
		document.forms["agtDetailsForm"]["AgtEmail"].focus();
		return false;
	}
	else {
		return true;
	}
}

//grabs data from form and returns it in a string
function grabData(insertOrUpdate) {
	const jsonObject = {};
	if (insertOrUpdate == "update") { //if update grab AgentID 
		jsonObject.agentId = document.getElementById("AgentID").value;
	}
	jsonObject.agtFirstName = document.getElementById("AgtFirstName").value;
	jsonObject.agtMiddleInitial = document.getElementById("AgtMiddleInitial").value;
	jsonObject.agtLastName = document.getElementById("AgtLastName").value;
	jsonObject.agtBusPhone = document.getElementById("AgtBusPhone").value;
	jsonObject.agtEmail = document.getElementById("AgtEmail").value;
	jsonObject.agtPosition = document.getElementById("AgtPosition").value;
	jsonObject.agencyId = document.getElementById("AgencyID").value;
	var jsonString = JSON.stringify(jsonObject);
	return jsonString;
}

//cancel button clicked
function cancelClicked() {

	if (document.getElementById("agentsSelect").value != "") { // if update
		loadAgentInfo(document.getElementById("agentsSelect").value);
		document.getElementById("btnInsertAgent").style.display = "none";
		document.getElementById("btnCancelAgent").style.display = "block";
		document.getElementById("btnUpdateAgent").style.display = "block";
		document.getElementById("btnDeleteAgent").style.display = "block";
	} else { //if insert
		document.getElementById("agentsSelect").selectedIndex = "0";
		document.getElementById("agtDetailsForm").style.display = "none";
		document.getElementById("agentsSelect").disabled = false;
	}

}

//new agent button clicked
function newAgentClicked() {
	document.getElementById("AgentID").value = "";
	document.getElementById("AgentID").placeholder = "Not Required";
	document.getElementById("AgtFirstName").value = "";
	document.getElementById("AgtMiddleInitial").value = "";
	document.getElementById("AgtLastName").value = "";
	document.getElementById("AgtBusPhone").value = "";
	document.getElementById("AgtEmail").value = "";
	document.getElementById("AgtPosition").value = "";
	document.getElementById("AgencyID").value = "";

	document.getElementById("btnInsertAgent").style.display = "block";
	document.getElementById("btnCancelAgent").style.display = "block";
	document.getElementById("btnUpdateAgent").style.display = "none";
	document.getElementById("btnDeleteAgent").style.display = "none";

	document.getElementById("agentsSelect").disabled = true;
	document.getElementById("agentsSelect").selectedIndex = "0";

	document.getElementById("agtDetailsForm").style.display = "block";

}

//resets the comboBox, so that the previous options are removed
function reset() {
	var select = document.getElementById("agentsSelect");
	var length = select.options.length;
	for (i = length - 1; i >= 0; i--) {
		select.options[i] = null;
	}
	var option = document.createElement("option");
	option.text = "Select an agent...";
	option.value = "";
	select.add(option);
}