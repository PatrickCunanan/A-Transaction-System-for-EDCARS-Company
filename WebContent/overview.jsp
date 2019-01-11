<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="sets1" type="java.sql.ResultSet" scope="request" />
<jsp:useBean id="sets2" type="java.sql.ResultSet" scope="request" />
<jsp:useBean id="sets3" type="java.sql.ResultSet" scope="request" />
<html lang="en">

<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>EDCARS|Admin Panel</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />

<link href="assets/css/light-bootstrap-dashboard-blue.css"
	rel="stylesheet" type="text/css" title="blue" />
<link href="assets/css/light-bootstrap-dashboard-azure.css"
	rel="stylesheet" type="text/css" title="azure" />
<link href="assets/css/light-bootstrap-dashboard-green.css"
	rel="stylesheet" type="text/css" title="green" />
<link href="assets/css/light-bootstrap-dashboard-orange.css"
	rel="stylesheet" type="text/css" title="orange" />
<link href="assets/css/light-bootstrap-dashboard-purple.css"
	rel="stylesheet" type="text/css" title="purple" />
<link href="assets/css/light-bootstrap-dashboard-red.css"
	rel="stylesheet" type="text/css" title="red" />
<link href="assets/css/light-bootstrap-dashboard-black.css"
	rel="stylesheet" type="text/css" title="black" />

<link href="assets/css/demo.css" rel="stylesheet" />

<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
<script>
	var style_cookie_name = "style";
	var style_cookie_duration = 120;
	var style_domain = document.domain;
	function switch_style(css_title) {
		var i, link_tag;
		for (i = 0, link_tag = document.getElementsByTagName("link"); i < link_tag.length; i++) {
			if ((link_tag[i].rel.indexOf("stylesheet") != -1)
					&& link_tag[i].title) {
				link_tag[i].disabled = true;
				if (link_tag[i].title == css_title) {
					link_tag[i].disabled = false;
				}
			}
			set_cookie(style_cookie_name, css_title, style_cookie_duration,
					style_domain);
		}
	}

	function set_style_from_cookie() {
		var css_title = get_cookie(style_cookie_name);
		if (css_title.length) {
			switch_style(css_title);
		}
	}

	function set_cookie(cookie_name, cookie_value, lifespan_in_days,
			valid_domain) {
		var domain_string = valid_domain ? ("; domain=" + valid_domain) : '';
		document.cookie = cookie_name + "=" + encodeURIComponent(cookie_value)
				+ "; max-age=" + 60 * 60 * 24 * lifespan_in_days + "; path=/"
				+ domain_string;
	}

	function get_cookie(cookie_name) {
		var cookie_string = document.cookie;
		if (cookie_string.length != 0) {
			var cookie_value = cookie_string.match('(^|;)[\s]*' + cookie_name
					+ '=([^;]*)');
			return decodeURIComponent(cookie_value[2]);
		}
		return '';
	}
</script>
<script>
	function printPage(id) {
		//add style to pdf
		var html = "<html><head><style>table{border-collapse: collapse; width: 100%;}table, td, th {border: 1px solid black;}</style></head><body><h2 align="+"center"+">EDCARS Inventory and Transaction Report Log</h2>";
		html += document.getElementById(id).innerHTML;

		html += "</body></html>";

		var printWin = window.open();
		printWin.document.write(html);
		printWin.document.close();
		printWin.focus();
		printWin.print();
		printWin.close();
	}
</script>
</head>

<body onload="set_style_from_cookie()">

	<div class="wrapper">
		<div class="sidebar" data-color data-image="assets/img/sidebar.jpg">
			<div class="sidebar-wrapper">
				<div class="logo">
					<a href="#" class="simple-text"> EDCARS | ADMIN PANEL </a>
				</div>

				<ul class="nav">
					<li class="active"><a href="indexTables.html"> <i
							class="pe-7s-graph"></i>
							<p>Overview</p>
					</a></li>
					<li><a href="generateTables.html"> <i class="pe-7s-note2"></i>
							<p>Manage Inventory</p>
					</a></li>
					<li><a href="transactionRedirect.html"> <i
							class="pe-7s-ribbon"></i>
							<p>Transaction</p>
					</a></li>
					<li><a href="faqsResult.html"> <i class="pe-7s-science"></i>
							<p>Manage Website</p>
					</a></li>
				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default navbar-fixed">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navigation-example-2">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Overview</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="LoginServlet"> Log out </a></li>
					</ul>
				</div>
			</div>
			</nav>


			<div class="content">
				<div id="report" class="container-fluid">
					<div class="row">
						<div class="col-md-6">
							<div class="card">
								<div class="header">
									<h4 class="title">Available Cars</h4>
								</div>
								<div style="max-height: 500px;">
									<div class="content table-responsive table-full-width">
										<table class="table table-hover table-striped">
											<thead>
												<th>Car Brand</th>
												<th>Car Model</th>
												<th>Car Type</th>
												<th>Car Colour</th>
												<th>Car Year</th>
											</thead>

											<tbody>
												<%
													boolean empty = true;
													while (sets1.next()) {
														empty = false;
												%>
												<tr>
													<td><%=sets1.getString("carBrand")%></td>
													<td><%=sets1.getString("carModel")%></td>
													<td><%=sets1.getString("carType")%></td>
													<td><%=sets1.getString("carColour")%>
													<td><%=sets1.getInt("carYear")%></td>
												</tr>
												<%
													}
													sets1.close();
												%>
											</tbody>

										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card">
								<div class="header">
									<h4 class="title">Sold Cars</h4>
								</div>
								<div style="max-height: 500px;">
									<div class="content table-responsive table-full-width">
										<table class="MsoNormalTable table table-hover table-striped">
											<thead>
												<th>Car Brand</th>
												<th>Car Model</th>
												<th>Car Type</th>
												<th>Car Colour</th>
												<th>Car Year</th>
											</thead>

											<tbody>
												<%
													boolean status = true;
													while (sets2.next()) {
														status = false;
												%>
												<tr>
													<td><%=sets2.getString("carBrand")%></td>
													<td><%=sets2.getString("carModel")%></td>
													<td><%=sets2.getString("carType")%></td>
													<td><%=sets2.getString("carColour")%>
													<td><%=sets2.getInt("carYear")%></td>
												</tr>
												<%
													}
													sets2.close();
												%>
											</tbody>

										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Past Transactions</h4>
								</div>
								<div style="max-height: 500px;">
									<div class="content table-responsive table-full-width">
										<table class="table table-hover table-striped">
											<thead>
												<th>ID#</th>
												<th>Payment Amount</th>
												<th>Total Fee</th>
												<th>Payment Type</th>
												<th>Car ID</th>
											</thead>
											<tbody>
												<%
													boolean empty2 = true;
													while (sets3.next()) {
														empty2 = false;
												%>
												<tr>
													<td><%=sets3.getInt("ID")%></td>
													<td><%=sets3.getDouble("Amount")%></td>
													<td><%=sets3.getInt("totalFee")%></td>
													<td><%=sets3.getString("Type")%></td>
													<td><%=sets3.getString("Car_ID")%></td>

												</tr>
												<%
													}
													sets3.close();
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-info btn-fill pull-right"
					onclick="printPage('report');">Print Report</button>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</body>

<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>
<script src="assets/js/chartist.min.js"></script>
<script src="assets/js/bootstrap-notify.js"></script>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="assets/js/light-bootstrap-dashboard.js"></script>
<script src="assets/js/demo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		demo.initChartist();

	});
</script>

</html>