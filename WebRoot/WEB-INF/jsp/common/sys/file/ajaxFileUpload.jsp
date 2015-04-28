<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx}/resources/uploadscript/ajaxfileupload/ajaxfileupload.js"></script>
<script>
function ajaxUpload(){
	var url="${ctx}/common/sys/file/ajaxUpload";
    $.ajaxFileUpload  ({  
            url: url,  
            secureuri: false,  
            fileElementId: "file",
            data:{},
            type:"post",
            dataType: "text",  
            beforeSend: function() {  
               // $("#loading").show();  
            },  
            complete: function() {  
                //$("#loading").hide();  
            },  
            success: function(data, status) { 
            	alert(message);
            },  
            error: function(data, status, e) { 
            	alert(e);  
            }  
        }  
    ) 
}
</script>
</head>
<body>
<input id="file" type="file"  name="file" class="input"  onchange="ajaxUpload();" />  
<input type="submit" value="sdf"/>
</body>
</html>