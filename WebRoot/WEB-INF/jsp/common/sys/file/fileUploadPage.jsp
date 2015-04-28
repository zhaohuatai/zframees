<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript" src="${ctx}/resources/webuploader015/webuploader.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/webuploader.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/css/syntax.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/webuploader015/css/style.css">
    
<title></title>
<script>
$(function(){
    var $ = jQuery,
    $list = $('#thelist'),
    $btn = $('#ctlBtn'),
    state = 'pending',
    uploader;

uploader = WebUploader.create({
    // 不压缩image
    resize: false,
    // swf文件路径
    swf:"${ctx}/resources/webuploader015/Uploader.swf",
    // 文件接收服务端。
    server: "${ctx}/common/sys/file/ajaxUpload",
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker'
});

// 当有文件添加进来的时候
uploader.on( 'fileQueued', function( file ) {
    $list.append( '<div id="' + file.id + '" class="item">' +
        '<h4 class="info">' + file.name + '</h4>' +
        '<p class="state">等待上传...</p>' +
    '</div>' );
});
// 文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress .progress-bar');
    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<div class="progress progress-striped active">' +
          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
          '</div>' +
        '</div>').appendTo( $li ).find('.progress-bar');
    }
    $li.find('p.state').text('上传中');
    $percent.css( 'width', percentage * 100 + '%' );
});

uploader.on( 'uploadAccept', function( object,ret ) {
	if(ret.statusCode == '200'){
		alert("uploadSuccess "+ret.statusCode);
		return true;
	}else{
		alert("uploadError "+ret.statusCode);
		return false;
	}
});
uploader.on( 'uploadSuccess', function( file,reason  ) {
	alert("uploadSuccess "+reason.message);
    $( '#'+file.id ).find('p.state').text('已上传');
});

uploader.on( 'uploadError', function( file,reason  ) {
	alert("uploadError "+reason.message);
    $( '#'+file.id ).find('p.state').text('上传出错');
});

uploader.on( 'uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').fadeOut();
});

uploader.on( 'all', function( type ) {
    if ( type === 'startUpload' ) {
        state = 'uploading';
    } else if ( type === 'stopUpload' ) {
        state = 'paused';
    } else if ( type === 'uploadFinished' ) {
        state = 'done';
    }

    if ( state === 'uploading' ) {
        $btn.text('暂停上传');
    } else {
        $btn.text('开始上传');
    }
});

$btn.on( 'click', function() {
    if ( state === 'uploading' ) {
        uploader.stop();
    } else {
        uploader.upload();
    }
}); 
});
</script>
</head>
<body>

<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>

</body>
</html>