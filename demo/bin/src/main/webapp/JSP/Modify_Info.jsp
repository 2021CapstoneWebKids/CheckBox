<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인 정보 수정</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">
    <style>
    	*, *:before, *:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-family: 'Nunito', sans-serif;
  color: #384047;
}

table {
  max-width: 960px;
  margin: 10px auto;
}

caption {
  font-size: 1.6em;
  font-weight: 400;
  padding: 10px 0;
}

thead th {
  font-weight: 400;
  background: #8a97a0;
  color: #FFF;
}

tr {
  background: #f4f7f8;
  border-bottom: 1px solid #FFF;
  margin-bottom: 5px;
}

tr:nth-child(even) {
  background: #e8eeef;
}

th, td {
  text-align: left;
  padding: 20px;
  font-weight: 300;
}

tfoot tr {
  background: none;
}

tfoot td {
  padding: 10px 2px;
  font-size: 0.8em;
  font-style: italic;
  color: #8a97a0;
}
    
    </style>
  </head>
  <body>

    <table>
      <caption>개인정보 수정</caption>
      <h3> ${message}</h3>
      <thead>
        <tr>
          <th scope="col">이름</th>
          <th scope="col">ID</th>
          <th scope="col">PW</th>
        </tr>
      </thead>
      <tbody>
      
        <tr>
          <th scope="row">${MyName}</th>
          <td>${MyID}</td>
          <td>비밀번호 Hash암호화됨</td>
        </tr>
        
        <tr>
          <th scope="row">
          	<form method="post" action="change_name.do">
          		<input name="ch_name" id="ch_name" type="text"/>
          		<input type="submit" value="이름변경"/>
          	</form>
          </th>
          
          <td>
          	<form method="post" action="change_ID.do">
          		<input name="ch_ID" id="ch_ID" type="text"/>
          		<input type="submit" value="ID변경"/>
          	</form>
          </td>
          
          <td>
          	<form method="post" action="change_PW.do">
          		<input name="ch_PW" id="ch_PW" type="text"/>
          		<input type="submit" value="PW변경"/>
          	</form>
          </td>
        </tr>
        
      </tbody>
     </table>
    
  </body>
</html>