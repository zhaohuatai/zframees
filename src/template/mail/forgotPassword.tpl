<div style="width:600px;margin:auto;line-height:2;font-size:12px;overflow:hidden;border-radius:3px;box-shadow:0 0 10px rgba(0, 0, 0, 0.2);">
	<div style="height:60px;border-radius:3px 3px 0 0;background:#00C5CD;">
		<span style="display:block;width:325px;padding-left:20px;padding-top:15px;line-height:31px;font-size:14px;font-weight:bold;color:#FFF;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
			亲爱的#{userName}，您好：
		</span>
	</div>
	<div style="padding:15px 30px;word-wrap:break-word;border:solid #C5C5C5;border-width:0 1px;">
		<br />
        <p>如果您忘记了密码请点击如下链接，修改您的密码：</p>
    
	<p style="font-size: 14px"> 
	#{url}
	</p>
		如果上面的链接无法点击，请把以上链接地址复制到浏览器地址栏:<br />
		为保障你账号的安全性，以上链接有效期为#{activeDays}天。<br>	<br>
	-------------------------------------------------------------------------------------
	<br>
		<p >
			该邮件是由 #{siteName} 系统发送, 请勿直接回复.
		</p>
		<p >
			#{siteUrl}
		</p>
	</div>
</div>