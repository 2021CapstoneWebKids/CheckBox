<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
 
 	<style>
 		@import url(//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css);
}
@import url(https://fonts.googleapis.com/css?family=Titillium+Web:300);
.fa-2x {
font-size: 2em;
}
.fa {
position: relative;
display: table-cell;
width: 60px;
height: 36px;
text-align: center;
vertical-align: middle;
font-size:20px;
}


.main-menu:hover,nav.main-menu.expanded {
width:250px;
overflow:visible;
}

.main-menu {
background:#212121;
border-right:1px solid #e5e5e5;
position:absolute;
top:0;
bottom:0;
height:100%;
left:0;
width:60px;
overflow:hidden;
-webkit-transition:width .05s linear;
transition:width .05s linear;
-webkit-transform:translateZ(0) scale(1,1);
z-index:1000;
}

.main-menu>ul {
margin:7px 0;
}

.main-menu li {
position:relative;
display:block;
width:250px;
}

.main-menu li>a {
position:relative;
display:table;
border-collapse:collapse;
border-spacing:0;
color:#999;
 font-family: arial;
font-size: 14px;
text-decoration:none;
-webkit-transform:translateZ(0) scale(1,1);
-webkit-transition:all .1s linear;
transition:all .1s linear;
  
}

.main-menu .nav-icon {
position:relative;
display:table-cell;
width:60px;
height:36px;
text-align:center;
vertical-align:middle;
font-size:18px;
}

.main-menu .nav-text {
position:relative;
display:table-cell;
vertical-align:middle;
width:190px;
  font-family: 'Titillium Web', sans-serif;
}

.main-menu>ul.logout {
position:absolute;
left:0;
bottom:0;
}

.no-touch .scrollable.hover {
overflow-y:hidden;
}

.no-touch .scrollable.hover:hover {
overflow-y:auto;
overflow:visible;
}

a:hover,a:focus {
text-decoration:none;
}

nav {
-webkit-user-select:none;
-moz-user-select:none;
-ms-user-select:none;
-o-user-select:none;
user-select:none;
}

nav ul,nav li {
outline:0;
margin:0;
padding:0;
}
.main-menu li:hover>a,nav.main-menu li.active>a,.dropdown-menu>li>a:hover,.dropdown-menu>li>a:focus,.dropdown-menu>.active>a,.dropdown-menu>.active>a:hover,.dropdown-menu>.active>a:focus,.no-touch .dashboard-page nav.dashboard-menu ul li:hover a,.dashboard-page nav.dashboard-menu ul li.active a {
color:#fff;
background-color:#5fa2db;
}
.area {
float: left;
background: #e2e2e2;
width: 100%;
height: 100%;
}
@font-face {
  font-family: 'Titillium Web';
  font-style: normal;
  font-weight: 300;
  src: local('Titillium WebLight'), local('TitilliumWeb-Light'), url(http://themes.googleusercontent.com/static/fonts/titilliumweb/v2/anMUvcNT0H1YN4FII8wpr24bNCNEoFTpS2BTjF6FB5E.woff) format('woff');
}
 	</style>
 	
 	<style>
 	table {width: 90%; margin: 0% 0% 0% 7%; box-sizing: border-box; box-shadow: inset 0px 0px 0px 0px rgba(0,0,0,0.1); font-family: 'Ubuntu'; /*border-collapse: collapse;*/ border-spacing: 0; background-color: #333333; }

	th, td { border: 1px solid rgba(255,255,255,0.1); box-sizing: border-box; }

	th { text-transform: uppercase; font-size:10px; font-weight:700; padding: 10px 0; color: rgba(255,255,255,0.5); background-color: #292929; letter-spacing: 1px; }

	td { width: 14.285%; transition: all 0.3s; font-size: 14px; color: rgba(255,255,255,0.6); font-weight: 400; font-size: 14px; padding: 1.5% 1.5% 5%; vertical-align: initial; padding: 1.5% 0 ; height: 75px; }

	.day:hover { background-color: rgba(0,0,0,0.1); cursor:pointer; }

	.today { color: #FFF; background-color: rgba(0,0,0,.25) !important; }

	span.number { margin-left: 10% }

	span.event { height: 4px; background-color: rgba(0,0,0,.3); display: block; margin: 5px 10%; border-radius: 2px; background-color: #91c33b; }

	span.event-multiday { margin: 9px -4px; border-radius: 0; }
	span.event-multiday-start { margin-right: -7px;  }
	span.event-multiday-finish { margin-left: -7px;  }

	span.event-ghost { background-color:transparent; }
 	
 	</style>
  </head>
  <body>
  
  <h2 margin: 0% 0% 0% 10%;>2021년 3월 [ 직원용 메인 페이지  ]</h2>
  <div class="area">
  
  	<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,500,700' rel='stylesheet' type='text/css'>

	
<table>
  <tr>
    <th class="day-name">Sun</th>
    <th class="day-name">Mon</th>
    <th class="day-name">Tue</th>
    <th class="day-name">Wed</th>
    <th class="day-name">Thu</th>
    <th class="day-name">Fri</th>
    <th class="day-name">Sat</th>
  </tr>
  <tr>
    <td class="day"><span class="number">31</span></td>
    <td class="day"><span class="number">1</span><span class="event"></span><span class="event"></span></td>
    <td class="day"><span class="number">2</span></td>
    <td class="day"><span class="number">3</span><span class="event event-multiday-start"></span></td>
    <td class="day"><span class="number">4</span><span class="event event-multiday"></span><span class="event event-multiday-start eventclass" style="background-color:#5a9ab2;"></span><span class="event"></td>
    <td class="day"><span class="number">5</span><span class="event event-multiday-finish"></span><span class="event event-multiday eventclass" style="background-color:#5a9ab2;"></span></td>
    <td class="day"><span class="number">6</span><span class="event event-ghost"></span><span class="event event-multiday-finish eventclass" style="background-color:#5a9ab2;"></span></td>
  </tr>
  <tr>
    <td class="day"><span class="number">7</span></td>
    <td class="day"><span class="number">8</span><span class="event"></span></td>
    <td class="day"><span class="number">9</span></td>
    <td class="day"><span class="number">10</span></td>
    <td class="day"><span class="number">11</span></td>
    <td class="day"><span class="number">12</span></td>
    <td class="day"><span class="number">13</span></td>
  </tr>
  <tr>
    <td class="day"><span class="number">14</span></td>
    <td class="day"><span class="number">15</span></td>
    <td class="day"><span class="number">16</span><span class="event"></span></td>
    <td class="day"><span class="number">17</span><span class="event"></span></td>
    <td class="day"><span class="number">18</span></td>
    <td class="day"><span class="number">19</span></td>
    <td class="day"><span class="number">20</span></td>
  </tr>
  <tr>
    <td class="day"><span class="number">21</span></td>
    <td class="day"><span class="number">22</span></td>
    <td class="day"><span class="number">23</span></td>
    <td class="day"><span class="number">24</span></td>
    <td class="day"><span class="number">25</span></td>
    <td class="day"><span class="number">26</span></td>
    <td class="day"><span class="number">27</span><span class="event event-multiday-start" style="background-color:#da5f5f;"></td>
  </tr>
  <tr>
    <td class="day"><span class="number">28</span><span class="event event-multiday" style="background-color:#da5f5f;"></td>
    <td class="day today"><span class="number">29</span><span class="event event-multiday-finish" style="background-color:#da5f5f;"></td>
    <td class="day"><span class="number">30</span></td>
    <td class="day"><span class="number">31</span></td>
  </tr>
</table>
  
  
  </div>
  
  <nav class="main-menu">
            <ul>
                <li>
                    <a href="http://justinfarrow.com">
                        <i class="fa fa-home fa-2x"></i>
                        <span class="nav-text">
                            Dashboard
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="#">
                        <i class="fa fa-laptop fa-2x"></i>
                        <span class="nav-text">
                            Stars Components
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="#">
                       <i class="fa fa-list fa-2x"></i>
                        <span class="nav-text">
                            Forms
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="#">
                       <i class="fa fa-folder-open fa-2x"></i>
                        <span class="nav-text">
                            Pages
                        </span>
                    </a>
                   
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-bar-chart-o fa-2x"></i>
                        <span class="nav-text">
                            Graphs and Statistics
                        </span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Quotes
                        </span>
                    </a>
                </li>
                <li>
                   <a href="#">
                       <i class="fa fa-table fa-2x"></i>
                        <span class="nav-text">
                            Tables
                        </span>
                    </a>
                </li>
                <li>
                   <a href="#">
                        <i class="fa fa-map-marker fa-2x"></i>
                        <span class="nav-text">
                            Maps
                        </span>
                    </a>
                </li>
                <li>
                	<a href="modify_info.do">
                       <i class="fa fa-info fa-2x"></i>
                        <span class="nav-text">
                            ${ID}님 으로 사용중
                        </span>
                    </a>
                </li>
            </ul>

            <ul class="logout">
                <li>
                   <a href="logout.do">
                         <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                            Logout
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
  </body>
    </html>