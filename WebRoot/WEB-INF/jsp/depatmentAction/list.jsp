<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Department
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">Department</td>
				<td width="150px">Higher</td>
				<td width="200px">Responsibilities</td>
				<td>Operation</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        
        <s:iterator value="departmentList">
			<tr class="TableDetail1 template">
				<td>
				<s:a action="department_list?parentId=%{id}">${name}</s:a></td>
				<td>${parent.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('Delete all lower departments？')">Delete</s:a>
					<s:a action="department_editUI?id=%{id}">Edit</s:a>
				</td>
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="department_addUI?parentId=%{#parent.id}"><img src="${pageContext.request.contextPath}/style/images/insert.gif" /></s:a>
            <s:if test="#parent != null">
            <s:a action="department_list?parentId=%{#parent.parent.id}"><img src="${pageContext.request.contextPath}/style/images/folder_up.gif" /></s:a>
            </s:if>
        </div>
    </div>
</div>

<!--说明-->	
<div id="Description"> 
	Instruction：<br />
	1.Show one level of department, default is top level<br />
	2.Click department will show lower level of departments<br />
	3.Click delete will delete all lower level of chosed departments.
</div>

</body>
</html>
