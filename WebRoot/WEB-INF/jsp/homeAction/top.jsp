<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Top</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link type="text/css" rel="stylesheet" href="style/blue/top.css" />
</head>

<body class="PageBody" style="margin: 0">
 
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">Itcast OA</font> 
			<!--<img border="0" src="style/blue/images/logo.png" />-->
        </div>
		
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="style/images/top/user.gif" /> Hello，<b>${user.name}</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">
            	<a href="javascript:void(0)">
					<img border="0" width="13" height="14" src="style/images/top/user_setup.gif" /> Personal Setting
				</a>
			</div>
			<div id="Head1Right_Time"></div>
		</div>
		
        <div id="Head1Right_SystemButton">
            <s:a action="loginout_logout" target="_parent">
				<img width="78" height="20" alt="Exist System" src="style/blue/images/top/logout.gif" />
			</s:a>
        </div>
		
        <div id="Head1Right_Button">
            <a target="desktop" href="/desktop?method=show">
				<img width="65" height="20" alt="Show Desktop" src="style/blue/images/top/desktop.gif" />
			</a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                <li><a target="desktop" href="javascript:void(0)">
						<img border="0" width="11" height="13" src="style/images/top/msg.gif" /> Message
						<span id="msg"></span>
					</a>
				</li>
                <li class="Line"></li>
                <li><a target="desktop" href="javascript:void(0)">
						<img border="0" width="16" height="11" src="style/images/top/mail.gif" /> Email
						<span id="mail"></span>
					</a>
				</li>
                <li class="Line"></li>
				  <!-- 是否有待审批文档的提示1，数量 -->
                <li><a href="Flow_Formflow/myTaskList.html" target="right">
                		<img border="0" width="12" height="14" src="style/images/top/wait.gif" /> 
                		Todo（<span id="wait" class="taskListSize">1</span>）
                	</a>
                </li>
				  
                <!-- 是否有待审批文档的提示2，提示审批 -->
                <li id="messageArea">You have a pending document, please timely approval! ★★★★★</li>
            </ul>
        </div>
        
		<div id="Head2_FunctionList">
			<marquee style="WIDTH: 100%;" onMouseOver="this.stop()" onMouseOut="this.start()" 
				scrollamount=1 scrolldelay=30 direction=left>
				<b>The Scrolling Message</b>
			</marquee>
		</div>
	</div>

</body>
</html>
