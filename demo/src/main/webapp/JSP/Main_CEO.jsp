<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>CEO용 메인 페이지</title>

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
  background: #374046;
}

#p3 {
  left: 200%;
  background: #374046;
}

#p4 {
  left: 300%;
  background: #374046;
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
</style>

<script language="javascript">
 function time(){
  var time= new Date(); //시간받기위해서 new date
      document.getElementById("now").innerHTML=time.getHours()+"시"+time.getMinutes()+"분"+time.getSeconds()+"초";
     setInterval("time()",1000);     //1초 지난후 time()실행
  }
</script>


<SCRIPT LANGUAGE="JavaScript">
function checkBox() {
var check_Box = document.getElementsByName("FixOrFlow"); //체크박스 name
for (j = 0; j < check_Box.length; j++) {
 var disign = document.getElementById([j+1]); // 보여질내용
 if(check_Box[j].checked == true ){
  disign.style.display = "block";
 }else{
  check_Box[j].checked = false;
  disign.style.display = "none";
 }
 }
}
</script>

</head>
	<body onload="time()">
	
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
  	
    	<div class="Current-Day">${year}년 ${month}월${day}일</div>
	    <div>
		    <span>
		    	${ID}님 으로 사용중 | 권한 : CEO , Manager
		    </span>
	    </div>
     
  	</div>
  	
  </div>
  
  
  <div class="page" id="p2">
  
    
    <div class="Calender">
    
    	<div>
    	
		    <h2 style="color:yellow">TODO LIST (CEO)</h2>
		    <hr>
		
		    <h3 style="color:red">Incomplted</h3>
		    
		    <!--  <p id="p1" style="color:red"> 
		    	abcd 
		    	<button onclick="location.href=''">
		    	삭제
		    	</button>
		    </p>
		    -->
		    
		    ${todo_incompleted}
		    
		    <hr>
		
		    
		    
		    <details>
		    	<summary style="color:skyblue">Completed</summary>
		    		<p style="color:skyblue"> asqw </p>
		    		<p style="color:skyblue"> ughethf </p>
		    
		    		${todo_completed }
		    	
		    	
		    </details>
		    
		    <hr>
		    
		    <details>
		    
    			<summary style="color:greenyellow">ToDo 추가</summary>
    			
    				<form id="contact" action="./Make_Todo.do" method="post">
  
					    <fieldset>
					      
					      <p> 업무 발행자 : ${ID} 
					      	<br>
					      	현재 시간: <span id="now"></span>
					      </p>
					      
					      <br>
					      <input type="radio" id="TT1" name="TT" value="Type1">타입1
						  <input type="radio" id="TT2" name="TT" value="Type2">타입2
						  <input type="radio" id="TT3" name="TT" value="Type3">타입3
					      <br>
					      <input type="radio" id="FixOrFlow1" name="FixOrFlow" value="Fix" onClick="checkBox()">고정업무
						  <input type="radio" id="FixOrFlow2" name="FixOrFlow" value="Flow" onClick="checkBox()">유동업무
						  <br><br>
						  
						  <div id="1" style="display:none">
						  	고정업무날짜
						  	<br>
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Monday">월요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Tuesday">화요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Wednesday">수요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Thursday">목요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Friday">금요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Saturday">토요일
						  	<input type="radio" id="Fix_day" name="Fix_Day" value="Sunday">일요일
						  </div>
						  
						  <div id="2" style="display:none">
						  	업무기한
						  	<input type="datetime-local" id="Limit" name="Limit" value="업무기한">
						  </div>
						  <br><br>
						  <input type="radio" id="Alarm" name="Alarm" value="Alarm_On">알람설정O
						  <input type="radio" id="Alarm" name="Alarm" value="Alarm_Off">알람설정X
						  
						  <br><br>
						  <input name="Context" id="Context" placeholder="업무내용" type="text" style="width:400px;height:150px;" required autofocus>
					      <br><br>
					      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">ToDo등록</button>
					    </fieldset>
					      
					</form>
					
			</details>
    	</div>
    
    </div>
    
    
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