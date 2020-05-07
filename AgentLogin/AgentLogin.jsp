<!DOCTYPE html>
<!-- Name: Anthony Okwananke
  Date: May 2020
  Purpose: Agents Login page -->
<html>

<head>
	<meta charset="ISO-8859-1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Agents - Travel Experts</title>
	<link href="Bootstrap.css" rel="stylesheet" />
	<script src="JQuery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
	<script src="ajax.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<link href="Site.css" rel="stylesheet" />
</head>
<body onload="loadAllAgents()">
	<div>
		<nav class="navbar navbar-expand-md navbar-dark bg-primary fixed-top">
			<div class="container">
				<a class="navbar-brand" href="/">Travel Experts</a>
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
		<div class="container bg-light col-lg-5 rounded border border-primary">
			<h5 class="text-center pt-2">Agents Login <br/><span>
					
			</h5>
			<form id="agentLoginPage" action="AgentLogin" method="post">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							
							<div class="form-group">
								<label for="UserId">User ID</label>
								<input class="form-control" id="UserId" name="UserId"
									placeholder="User ID" type="text" value="" />
							</div>
							
							<div class="form-group">
								<label for="Password">Password</label>
								<input class="form-control" id="Password" name="Password" placeholder="Password"
									type="password" value="" />
							</div>
							<div class="row justify-content-center">
								<br />
								<input class="btn btn-primary mb-4" value="Login" onclick="Login()" type="submit"/>
							</div>


						</div>
						
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