<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
*{
    margin:0;
    padding:0;
    box-sizing: border-box;
    font-family: 'Epilogue', sans-serif;
}

.clearfix::after{
    content: ' ';
    display: block;
    clear: both;
}

li{
    list-style: none;
}

.main{
    background: rgb(238,126,195);
    background: linear-gradient(74deg, rgba(238,126,195,1) 9%, rgba(149,175,213,0.981127485173757) 100%);
    width: 698px;
    margin: 50px auto 0;
    padding: 20px 0px 20px 25px;
    border-radius: 10px;
}

.main .main-title{
    padding: 20px;
    color: #fff;
    text-align: center;
}

.main .day{
    width: 100%;
    /* overflow: hidden; */
}

.main .day li{
    float: left;
    background: white;
    padding: 10px;
    width: 80px;
    height: 80px;
    margin-right: 14px;
    margin-top: 10px;
}

.main .day li:last-child{
    margin-right: 0;
}

.main .day li:hover{
    cursor: pointer;
    transition: all .2s;
    transform: scale(1.08);
}

.month{
    color: #fff;
    font-size: 22px;
    text-align:center;
    margin-left: -25px;
    margin-bottom: 20px;
    padding: 10px;
}

.month .fas{
    cursor: pointer;
    transition: all .2s ease-in-out;
}

.month .fas:hover{
    transition: all .2s ease-in-out;
    transform: scale(1.2);
}

.month .fa-chevron-left{
    float: left;
    margin-left: 15px;
}

.month .fa-chevron-right{
    float: right;
    margin-right: 15px;
}

.dayCount .dayCount-list li{
    float: left;
    width: 94px;
    color: #fff;
}

.sun,.sat{
    color: red;
}

.none{
    display: none;
}

.todo{
    width: 698px;
    margin: 10px auto 100px;
    background: white;
    padding: 20px;

}

.textToDo{
    margin-top: 20px;
}

.textToDo .textToDo-text[type="text"]{
    border: none;
    border: 3px solid rgb(222, 140, 229);
    border-radius: 10px;;
    padding: 5px 20px;
    width: 300px;
}

.textToDo .textToDo-text[type="text"]:focus{
    outline: none;
    box-shadow: 0px 5px 10px 5px rgba(0,0,0,0.1);
}

.textToDo .textToDo-button{
    margin-left: 10px;
    border: none;
    border: 3px solid rgb(222, 140, 229);
    padding: 6px 10px;
    background: white;
    border-radius: 20px;
    transition: all .2s;
}

.textToDo .textToDo-button:focus{
    outline: none;
}

.textToDo .textToDo-button:hover{
    cursor: pointer;
    transition: all .2s;
    transform: scale(1.1);
}

.todoList{
    padding: 10px;
}

.todoList li{
    padding: 8px;
    border-bottom: 1px solid gray;
}

.deleteBtn{
    border: none;
    float: right;
    cursor: pointer;
    transition: all .2s;
}

.deleteBtn:focus{
    outline:none;
}

.deleteBtn:hover{
    transition: all .2s;
    transform: scale(1.2);
}
    
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Epilogue:wght@500&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/c92986acdf.js" crossorigin="anonymous"></script>
    <script type="text/javascript" defer>
    
    const Day = document.querySelector('.day');
    const month = document.querySelector('.month-name');
    const date = new Date();

    const pre = document.querySelector('.left');
    const next = document.querySelector('.right');

    const todoField = document.querySelector('.todo');
    const todoTitle = document.querySelector('.todo-title');
    const todoList = document.querySelector('.todoList');

    const input = document.querySelector('input[type="text"]');
    const add = document.querySelector('.add');
    const reset = document.querySelector('.reset');
    const allReset = document.querySelector('.allreset');


    let currentMon = date.getMonth()+1;   
    let currentYear = date.getFullYear();
    let currentDay = date.getDate();

    let DayOfChoice = currentDay;
    let MonOfChoice = currentMon;
    let yearOfChoice = currentYear;

    let year = currentYear;
    let mon = currentMon;

    let clickEventArr = [];
    let storeToDo = [];

    function isLeapYear(year){
        return (year%4==0)&&(year%400==0||year%100!=0);
    }

    function getDayOfMon(mon,year){
        if(mon===1||mon===3||mon===5||mon===7||mon===8||mon===10||mon===12){
            return 31;
        }
        else if(mon===2){
            return isLeapYear(year)? 29 : 28;
        }
        else{
            return 30;
        }
    }

    function getDay(year,mon,date){
        const conYMD = year+'-'+mon+'-'+date;
        return(new Date(conYMD).getDay());
    }
    function makeCalendar(year,mon,dayCount){
        clickEventArr=[];
        Day.innerHTML='';
        let getFirstDay = getDay(year,mon,1);
        let previousMon;
        if(currentMon-1<0){
            previousMon = 12;
        }
        else{
            previousMon = currentMon - 1;
        }
        let getDayOfPreMon = getDayOfMon(previousMon,year);
        for(let i=(getFirstDay+6)%7; i>0; i--){
            const listPre = document.createElement('li');
            listPre.textContent = `${getDayOfPreMon-(i-1)}`;
            listPre.style.opacity = '0.5';
            listPre.classList.add('disabled');
            Day.appendChild(listPre);
        }
       
        for(let i=1; i<=dayCount; i++){
            if(i===currentDay&&year===currentYear&&mon===currentMon){
                //ì íí ë, ì, ì¼ ë¤ë¥¼ ë íì¬ ë ì§ì ê²ìì íëë¦¬
                const onlyOneList = document.createElement('li');

                onlyOneList.textContent = `${i}`;
                if(currentYear === yearOfChoice && currentMon === MonOfChoice && currentDay === DayOfChoice){
                    onlyOneList.style.border = '3px solid skyblue';
                }
                else{
                    onlyOneList.style.border = '3px solid black';
                }

                if(0===getDay(year,mon,i)){
                    onlyOneList.style.color = 'red';
                }
                else if(6==getDay(year,mon,i)){
                    onlyOneList.style.color = 'blue';
                }

                //íì¬ ë, ì ê°ì ë
                
                Day.addEventListener('click',(event)=>{
                    if(event.target!==onlyOneList){
                        onlyOneList.style.border = '3px solid black';
                    }
                });

                Day.appendChild(onlyOneList);
                continue;
            }

            const list = document.createElement('li');
            list.textContent = `${i}`;
            if(i===DayOfChoice&&year===yearOfChoice&&mon===MonOfChoice){
                list.style.border = '3px solid skyblue';
                Day.addEventListener('click',(event)=>{
                    if(event.target!==list){
                        list.style.border = 'none';
                    }
                });
            }    

            if(0===getDay(year,mon,i)){
                list.style.color = 'red';
            }
            else if(6==getDay(year,mon,i)){
                list.style.color = 'blue';
            }

            Day.appendChild(list);
        }
    }

    function setMonthTitle(year,mon){
        month.textContent = `${year}.${mon}`
    }

    function nextMonthOrYear(){
        if(mon===12){
            year = year+1;
            mon = 1;
        }
        else{
            mon = mon+1;
        }
        setMonthTitle(year,mon);
        makeCalendar(year,mon,getDayOfMon(mon,year));
    }

    function preMonthOrYear(){
        if(mon===1){
            year = year-1;
            mon = 12;
        }
        else{
            mon = mon-1;
        }
        setMonthTitle(year,mon);
        makeCalendar(year,mon,getDayOfMon(mon,year));
    }


    function main(){
        setMonthTitle(year,mon);
        makeCalendar(year,mon,getDayOfMon(mon,year));
        todoTitle.textContent = `${year}.${mon}.${currentDay} ì¤ëì ì¼ì ì ìë ¥íì¸ì `;
        displayToDoOnDays();
    }

    function displayToDoOnDays(){
        todoList.innerHTML='';
        const YMD = year+'-'+mon+'-'+DayOfChoice;
        let arrayToDo;
        const elementToDo = document.createElement('li');
        if(!localStorage.getItem(YMD)){
            return;
        }
        if(localStorage.getItem(YMD).includes(',')){
            
            arrayToDo = localStorage.getItem(YMD).split(',');
            arrayToDo.forEach((value)=>{
                const deleteBtn = document.createElement('button');
                deleteBtn.setAttribute('class','deleteBtn');
                deleteBtn.innerHTML = '<i class="far fa-minus-square"></i>';
                const elementToDo = document.createElement('li');
                
                elementToDo.innerText = value;
                elementToDo.appendChild(deleteBtn);

                elementToDo.scrollTo();

                todoList.appendChild(elementToDo);
            });
            
        }
        else{
            const deleteBtn = document.createElement('button');
            deleteBtn.setAttribute('class','deleteBtn');
            deleteBtn.innerHTML = '<i class="far fa-minus-square"></i>';

            elementToDo.textContent = localStorage.getItem(YMD);
            elementToDo.appendChild(deleteBtn);
            todoList.appendChild(elementToDo);
        }
    }

    pre.addEventListener('click',preMonthOrYear);
    next.addEventListener('click',nextMonthOrYear);


    function clearEvent(){
        clickEventArr.forEach((value)=>{
            value.style.border = 'none';
        });
    }

    Day.addEventListener('click',(event)=>{
        if(event.target.tagName==='UL')return;
        if(event.target.className!=='disabled'){
            clearEvent();
            todoTitle.textContent = `ì¤ëì í ì¼ ${year}.${mon}.${event.target.textContent} ðâ`;
            event.target.style.border='3px solid skyblue';
            DayOfChoice = (event.target.textContent)*1;
            MonOfChoice = mon;
            yearOfChoice = year;
            
            displayToDoOnDays();
            clickEventArr.push(event.target);
            console.log(clickEventArr);
            input.focus();
        }
        
    });

    function keepStore(){
        const YMD = year+'-'+mon+'-'+DayOfChoice;
        let arrayToDo;
        let arr = new Array();
        const elementToDo = document.createElement('li');
        if(!localStorage.getItem(YMD)){
            return arr;
        }
        if(localStorage.getItem(YMD).includes(',')){
            arrayToDo = localStorage.getItem(YMD).split(',');
            arrayToDo.forEach((value)=>{
                arr.push(value);
            });
        }
        else{
            arr.push(localStorage.getItem(YMD));
        }
        return arr;
    }

    function addToDoList(){
        if(input.value === ''){
            alert('í ì¼ì ìë ¥íì¸ì');
            return;
        }

        storeToDo = keepStore();
        storeToDo.push(input.value);
        
        const YMD = year+'-'+mon+'-'+DayOfChoice;
        localStorage.setItem(YMD,storeToDo);
        
        displayToDoOnDays();
        input.value="";
        input.focus();
    }

    add.addEventListener('click',(event)=>{
        addToDoList();
    });

    input.addEventListener('keypress',(event)=>{
        if(event.key==='Enter'){
           addToDoList();
        }
    });

    reset.addEventListener('click',()=>{
        const result = prompt(`ì¼ì ì ì­ì íìê² ìµëê¹? ${year} ${mon} ${DayOfChoice}? Enter (y/n)`);
        const YMD = year+'-'+mon+'-'+DayOfChoice;
        if(result==='y'){
            localStorage.removeItem(YMD);
            displayToDoOnDays();
        }
    });

    allReset.addEventListener('click',()=>{
        const result = prompt(`ð¥ëª¨ë ì¼ì ì ì´ê¸°ííìê² ìµëê¹? (y/n)ð¥`);
        if(result==='y'){
            localStorage.clear();
            displayToDoOnDays();
        }
    });

    todoList.addEventListener('click',(event)=>{
        if(event.target.className==='far fa-minus-square'){
            console.log("a: "+event.target.parentNode.parentNode.textContent);
                 
            const YMD = year+'-'+mon+'-'+DayOfChoice;
            
            if(localStorage.getItem(YMD).includes(',')){
                let array = localStorage.getItem(YMD).split(',');
                let copyArray = [];
                array.forEach((value)=>{
                    if(value !== event.target.parentNode.parentNode.textContent){
                        copyArray.push(value);
                    }
                });
                localStorage.setItem(YMD,copyArray);
            }
            else{
                localStorage.removeItem(YMD);
            }
            
            todoList.removeChild(event.target.parentNode.parentNode);
        }
    }); 

    main();
    
    </script>
</head>
<body>
    <section class="main">
        <h1 class="main-title">CheckBox</h1>
        <div class="month clearfix">
            <span class="left">
                <i class="fas fa-chevron-left left"></i>
            </span>
            <span class="right">
                <i class="fas fa-chevron-right right"></i>
            </span>
            <p class="month-name"></p>
        </div>
        <div class="dayCount">
            <ul class="dayCount-list clearfix">
                <li>월</li>
                <li>화</li>
                <li>수</li>
                <li>목</li>
                <li>금</li>
                <li>토</li>
                <li>일</li>
            </ul>
        </div>
        <ul class="day clearfix">
          
        </ul>

    </section>
    <section class="todo">
        <h1 class="todo-title">
            Today's To Do List
        </h1>
        <div class="textToDo">
            <input type="text" class="textToDo-text">
            <button class="textToDo-button add">Add</button>
            <button class="textToDo-button reset">Delete</button>
            <button class="textToDo-button allreset">reset</button>
        </div>
        <ul class="todoList"></ul>
    </section>

</body>
</html>