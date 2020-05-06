function loadAllAgents() {
	var req = new XMLHttpRequest();

	req.onreadystatechange = function () {

		if (req.readyState == 4 && req.status == 200) {
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
	}
	req.open("GET", "http://localhost:8080/JSP_Project-1/rs/agent/getallagents");
	req.send();

}
function loadAgentInfo(agentId) {
	console.log("in loadForm, agentId " + agentId);
	var req = new XMLHttpRequest();

	req.onreadystatechange = function () {
		if (req.readyState == 4 && req.status == 200) {
			var agent = JSON.parse(req.responseText);
			console.log("agent: " + agent);

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
	req.open("GET", "http://localhost:8080/JSP_Project-1/rs/agent/getagent/" + agentId);
	req.send();
}

function updateAgent() {
	console.log("in updateAgent");
	var req = new XMLHttpRequest();

	req.onreadystatechange = function () {
		if (req.readyState == 4 && req.status == 200) {
			//	document.getElementById("message").innerHTML = req.responseText;
			console.log("agent updated");
		}
	}

	const jsonObject = {};
	jsonObject.agentId = document.getElementById("AgentID").value;
	jsonObject.agtFirstName = document.getElementById("AgtFirstName").value;
	jsonObject.agtMiddleInitial = document.getElementById("AgtMiddleInitial").value;
	jsonObject.agtLastName = document.getElementById("AgtLastName").value;
	jsonObject.agtBusPhone = document.getElementById("AgtBusPhone").value;
	jsonObject.agtEmail = document.getElementById("AgtEmail").value;
	jsonObject.agtPosition = document.getElementById("AgtPosition").value;
	jsonObject.agencyId = document.getElementById("AgencyID").value;
	var jsonString = JSON.stringify(jsonObject);

	/* var obj = "{"
	+ agentId + ":" + document.getElementById("AgentID").value + ","
	+ agtFirstName + ":'" + document.getElementById("AgtFirstName").value + "',"
	+ agtMiddleInitial + ":'" + document.getElementById("AgtMiddleInitial").value + "',"
	+ agtLastName + ":'" + document.getElementById("AgtLastName").value + "',"
	+ agtBusPhone + ":'" + document.getElementById("AgtBusPhone").value + "',"
	+ agtEmail + ":'" + document.getElementById("AgtEmail").value + "',"
	+ agtPosition + ":'" + document.getElementById("AgtPosition").value + "',"
	+ agencyId + ":" + document.getElementById("AgencyID").value + "}" */



	console.log(jsonString);

	req.open("POST", "rs/agent/postagent");
	req.setRequestHeader("Content-Type", "application/json");
	req.send(jsonString);
}