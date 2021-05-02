<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>직원용 메인 페이지</title>

<style>
	* {
  transition: all 500ms ease-out;
  -webkit-transition: all 500ms ease-out;
}

html, body, section, .page {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  transition: all 1s cubic-bezier(.5, -0.005, 0.2, 1) !important;
  -webkit-transition: all 1s cubic-bezier(.5, -0.005, 0.2, 1) !important;
  color: #fff;
  background: #374046;
  overflow: hidden;
}

section {
  width:400%;  
}

.page {
  position: absolute;
}

#p1 {
  left: 0;
}

#p2 {
  left: 100%;
  background: #FF5722;
}

#p3 {
  left: 200%;
  background: #593C1F;
}

#p4 {
  left: 300%;
  background: deeppink;
}

ul {
 position: fixed; 
 z-index: 1;
}
#t1:target .page#p1 {
    transform: translateX(0);
}
#t2:target .page#p2 {
    transform: translateX(-90%);
}
#t3:target .page#p3 {
    transform: translateX(-190%);
}
#t4:target .page#p4 {
    transform: translateX(-290%);
}

#t2:target .page#p1, 
#t3:target .page#p1,
#t4:target .page#p1 {
  background: black;
}

#t2:target .page#p1 .icon, 
#t3:target .page#p1 .icon,
#t4:target .page#p1 .icon {
  -webkit-filter: blur(3px);
  filter: blur(3px);
}

.icon {
  color: #fff;
  font-size: 32px;
  display: block;
}

ul .icon:hover {
  opacity: 0.6;
}

#p1 .icon .title {
  margin-bottom: 20px;
}

#t2:target ul .icon,
#t3:target ul .icon,
#t4:target ul .icon {
  transform: scale(.6); 
  transition-delay: .25s;
}

#t2:target #dos {
  transform: scale(1.2) !important;
}

#t3:target #tres {
  transform: scale(1.2) !important;
}

#t4:target #cuatro {
  transform: scale(1.2) !important;
}

ul {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  /*right: 0;*/
  margin: auto;
  height: 280px;
  width: 10%;
  padding: 0;
  text-align: center;
 }

ul li {
  margin: 30px 0;
}

a {
  text-decoration: none;
  font-family: open sans, sans-serif;
}

.title, .hint {
  font-family: open sans, sans-serif;
  display: block;
}

.title {
  font-size: 38px;
}

.hint {
  font-size: 13px;
}

.hint a {
  color: #EFFF06;
  transition: all 250ms easet-out;
}

.hint a:hover {
  color: #FFF;
}

.page .icon {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 10%;
  left: 0;
  width: 270px;
  height: 170px;
  margin: auto;
  text-align: center;
  font-size: 80px;
  transform: translateX(360%);
  line-height: 1.3;
}

.page#p1 .icon {
  height: 220px;
}

.page#p1 .icon {
  transform: translateX(0) !important;
}

#t2:target .page#p2 .icon {
  transform: translateX(0) !important;
  transition-delay: 1s;
}

#t3:target .page#p3 .icon {
  transform: translateX(0) !important;
  transition-delay: 1s;
}

#t4:target .page#p4 .icon {
  transform: translateX(0) !important;
  transition-delay: 1s;
}

.credit{
  position: absolute;
  bottom: 0;
  width: 100%;
  text-align: center;
  font-size: 10px;
}

.credit a {
  color: #fff
}

</style>

<style>


	.Calender
{
  width: 680px; 
  margin: 100px auto auto auto;
  text-align: center;
  border: 3px solid black;

  
}

.Current-Day
{

  background:linear-gradient(#3468af,#3468af);
  color:#FFF;
  line-height: 50px;
  font-size: 19pt;
}
.post
{
  background:linear-gradient(#e64c65,#e64c65);
  color:#fff;
}

.Week-Days
{
  background: #394264;
  color: #4ec4ed;
}
.Week-Days td, .Days td
{
  width: 480px;
}
.Days .T
{
  color: #8e898e;
}
.Days table td 
{
  background-color: #394264;
  color: #FFF;
}
.Days table td:hover
{
  background: #fff;
  cursor: pointer;
  color: #3468af;
}
/*KHALED CODER :) */

</style>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  1000,      400],
          ['2005',  1170,      460],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
    
</head>
	<body>
	
		<div class="ct" id="t1">
 		<div class="ct" id="t2">
   		<div class="ct" id="t3">
     	<div class="ct" id="t4">
<section>
  
 <ul>
   <a href="#t1"><li class="icon fa fa-home" id="uno"></li></a>
   <a href="#t2"><li class="icon fa fa-keyboard-o" id="dos"></li></a>
   <a href="#t3"><li class="icon fa fa-coffee" id="tres"></li></a>
   <a href="#t4"><li class="icon fa fa-info-circle" id="cuatro"></li></a>
 </ul>
 
 
  <div class="page" id="p1">
     
     <div class="Calender">
    <div class="Current-Day">${year}년 ${month}월</div>
    <div class="Week-Days">
      <table>
        <tr>
          <td>일</td>
          <td>월</td>
          <td>화</td>
          <td>수</td>
          <td>목</td>
          <td>금</td>
          <td>토</td>
        </tr>
      </table>
    </div>
    <div class="Days">
      <table>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td ${today1}>1</td>
          <td ${today2}>2</td>
          <td ${today3}>3</td>
          
        </tr>
          <td ${today4}>4</td>
          <td ${today5}>5</td>
          <td ${today6}>6</td>
          <td ${today7}>7</td>
          <td ${today8}>8</td>
          <td ${today9}>9</td>
          <td ${today10}>10</td>
          
        </tr>
          
          <!--  <td class="T" ${today11}>11</td> -->
          <td ${today11}>11</td>
          <td ${today12}>12</td>
          <!--  <td style="color:green" onClick="location.href='./login'">13</td> -->
          <td ${today13}>13</td>
          <td ${today14}>14</td>
          <td ${today15}>15</td>
          <td ${today16}>16</td>
          <td ${today17}>17</td>
          
        </tr>
          <tr>
          <td ${today18}>18</td>
          <td ${today19}>19</td>
          <td ${today20}>20</td>
          <td ${today21}>21</td>
          <td ${today22}>22</td>
          <td ${today23}>23</td>
          <td ${today24}>24</td>
          
        </tr>
          <tr>
          <td ${today25}>25</td>
          <td ${today26}>26</td>
          <td ${today27}>27</td>
          <td ${today28}>28</td>
          <td ${today29}>29</td>
          <td ${today30}>30</td>
        </tr>
      </table>
    </div>
    
    <p>
    	<button type="button" onclick="location.href='./CheckIn.do'">출근</button>
    	
    	<button type="button" onclick="location.href='./CheckOut.do'">퇴근</button>
    </p>
    
    <div>
    <span>
    	${ID}님 으로 사용중 | 권한 : Employee
    </span>
    </div>
    
    <div>
    <span>
    	${On_Work}
    </span>
    </div>
    
    <div id="curve_chart" style="width: 100%; height: 100%">
    </div>
    
    <div>
    	<p style="background-color:green">&nbsp&nbsp&nbsp</p> 
    	<p> ▶ 현재 일 </p>
    </div>
    
    <div>
    	<p style="background-color:gray">&nbsp&nbsp&nbsp</p> 
    	<p> ▶  지난 날 </p>
    </div>
    
    <div>
    	<p style="background-color:blue">&nbsp&nbsp&nbsp</p> 
    	<p> ▶  출근한 날 </p>
    </div>
    
  </div>
 
     
  </div>
  
  
  <div class="page" id="p2">
    <li class="icon fa fa-keyboard-o"><span class="title">Type</span></li>
  </div>  
  
  
  <div class="page" id="p3">
    <li class="icon fa fa-coffee"><span class="title">Coffee</span></li>
  </div>
  
  
   <div class="page" id="p4">
   <li class="icon fa fa-info-circle">
   	<span class="title">Info</span>
   	<span class="hint">
   		<a href="modify_info.do">개인정보 수정</a>
   	</span>
   	<span class="hint">
   		<a href="logout.do">로그아웃</a>
   	</span>
   	<span class="hint">
   		<a href="withdrawal.do">회원탈퇴</a>
   	</span>
   </li>
  </div>  
  
  
  
</section>
       
     	</div>
     	</div>
  		</div>
  		</div>
  
	</body>
</html>