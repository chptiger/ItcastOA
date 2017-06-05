<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Forum List</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
<s:debug></s:debug>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Forum
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">Forum Name</td>
                <td width="300px">Forum Description</td>
                <td>Operation</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        
        <s:iterator value="forumList" status="status">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="forumManage_delete?id=%{id}" onclick="return delConfirm()">Delete</s:a>
					<s:a action="forumManage_editUI?id=%{id}">Edit</s:a>
					
					<%-- 最上面的不能上移 --%>
					<s:if test="#status.first">
						<span style="color: gray; cursor: pointer">moveUp</span>
					</s:if>
					<s:else>
						<s:a action="forumManage_moveUp?id=%{id}">moveUp</s:a>
					</s:else>
					
					<%-- 最下面的不能下移 --%>
					<s:if test="#status.last">
						<span style="color: gray; cursor: pointer">moveDown</span>
					</s:if>
					<s:else>
						<s:a action="forumManage_moveDown?id=%{id}">moveDown</s:a>
					</s:else>
					
				</td>
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="forumManage_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，显示的列表按其位置排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>

</body>
</html>
	