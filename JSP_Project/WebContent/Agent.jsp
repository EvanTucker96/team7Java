<!-- Name: Bilal Ahmad
  Date: May 4, 2020
  Purpose: Agents page -->
<jsp:include page="header.jsp" />

<body class="container body-content" onload="loadAllAgents()">
	<div>
		<nav class="navbar navbar-expand-md navbar-dark bg-primary fixed-top">
			<div class="container">
				<a class="navbar-brand" href="Agent.jsp">Travel Experts</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapseNav"
					aria-controls="collapseNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="collapseNav">
					<ul class="navbar-nav mr-auto">
					</ul>
					<form class="form-inline my-2 my-lg-0">
						<a class="nav-link" href="Agent.jsp">Agents</a>
					</form>
				</div>
			</div>
		</nav>
	</div>
	<!-- 	Body Starts Here -->
	<div class="container body-content mt-6">
		<div class="col-md-4"></div>
		<div class="container bg-light col-lg-7 rounded border border-primary">
			<div class="container">
				<h5 class="text-center pt-2">Agents <br />
					<span>
						 <div id="selectionRow" class="row container">
							<select class="custom-select mr-md-2 mt-2" id="agentsSelect"
								onchange="loadAgentInfo(this.value)">
								<option value="">Select an agent...</option>
							</select>
							<input class="btn btn-primary my-2" value="New Agent" onclick="newAgentClicked()"
								type="button" />
						</div> 
					</span>
				</h5>
			</div>
			<!-- Form  -->
			<form id="agtDetailsForm" action="rs/agent/postagent" method="post">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="AgentID">AgentID</label>

								<input class="form-control" id="AgentID" name="AgentID" placeholder="AgentID"
									type="text" value="" disabled />
							</div>
							<div class="form-group">
								<label for="AgtFirstName">First Name</label>
								<input class="form-control" id="AgtFirstName" name="AgtFirstName"
									placeholder="First Name" type="text" value="" required />
							</div>
							<div class="form-group">
								<label for="AgtMiddleInitial">Middle Initial</label>
								<input class="form-control" id="AgtMiddleInitial" name="AgtMiddleInitial"
									placeholder="Middle Initial" type="text" value="" />
							</div>
							<div class="form-group">
								<label for="AgtLastName">Last Name</label>
								<input class="form-control" id="AgtLastName" name="AgtLastName" placeholder="Last Name"
									type="text" value="" required />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="AgencyID">AgencyID</label>
								<input class="form-control" id="AgencyID" name="AgencyID" placeholder="AgencyID"
									type="text" value="" required />
							</div>
							<div class="form-group">
								<label for="AgtPosition">Position</label>
								<input class="form-control" id="AgtPosition" name="AgtPosition" placeholder="Position"
									type="text" value="" required />
							</div>
							<div class="form-group">
								<label for="AgtBusPhone">Business Phone</label>
								<input class="form-control" id="AgtBusPhone" name="AgtBusPhone"
									placeholder="Business Phone" type="text" value="" required />
							</div>
							<div class="form-group">
								<label for="AgtEmail">Email</label>
								<input class="form-control" id="AgtEmail" name="AgtEmail" placeholder="Email"
									type="text" value="" required />
							</div>
						</div>
					</div>
					<hr />
					<!-- Row of buttons -->
					<div class="row justify-content-center">
						<br />
						<input id="btnInsertAgent" class="btn btn-primary mb-4" value="Insert Agent"
							onclick="insertAgent()" type="button" />
						<input id="btnUpdateAgent" class="btn btn-primary mb-4" value="Update" onclick="updateAgent()"
							type="button" />
						<input id="btnDeleteAgent" class="btn btn-danger mb-4 ml-1" value="Delete"
							onclick="deleteAgent()" type="button" />
						<input id="btnCancelAgent" class="btn btn-primary mb-4 ml-1" value="Cancel"
							onclick="cancelClicked()" type="button" />
						
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-4"></div>
		<hr />
	</div>
	<footer class="row justify-content-center bottom pt-4">
		<p>&copy; 2020 - Travel Experts</p>
	</footer>

</body>

</html>