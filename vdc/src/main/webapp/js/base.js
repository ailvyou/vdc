function initPush(){
	if(currentUserId!=undefined && currentUserId!=null && currentUserId!=''){
		PL.webRoot=ctx+'/proxy/';
		PL._init();
		PL.userId=currentUserId;
		PL.joinListen('/topic/userMsgNotifyPusher');
	}
}
function onData(event){
	if(event!=undefined){
		var total_reminder_count=event.get('total_reminder_count');
		var notes_reminder_count=event.get('notes_reminder_count');
		var comment_reminder_count=event.get('comment_reminder_count');
		var private_msg_reminder_count=event.get('private_msg_reminder_count');
		var add_friend_apply_reminder_count=event.get('add_friend_apply_reminder_count');
		var other_reminder_count=event.get('other_reminder_count');
		if(notes_reminder_count!=undefined && notes_reminder_count!=null && parseInt(notes_reminder_count)>0){
			$("#notesReminderCountSpan").html("("+notes_reminder_count+")");
		}else{
			$("#notesReminderCountSpan").html("");
		}
		if(comment_reminder_count!=undefined && comment_reminder_count!=null && parseInt(comment_reminder_count)>0){
			$("#commentReminderCountSpan").html("("+comment_reminder_count+")");
		}else{
			$("#commentReminderCountSpan").html("");
		}
		if(private_msg_reminder_count!=undefined && private_msg_reminder_count!=null && parseInt(private_msg_reminder_count)>0){
			$("#privatemsgReminderCountSpan").html("("+private_msg_reminder_count+")");
		}else{
			$("#privatemsgReminderCountSpan").html("");
		}
		if(add_friend_apply_reminder_count!=undefined && add_friend_apply_reminder_count!=null && parseInt(add_friend_apply_reminder_count)>0){
			$("#addfriendReminderCountSpan").html("("+add_friend_apply_reminder_count+")");
		}else{
			$("#addfriendReminderCountSpan").html("");
		}
		if(other_reminder_count!=undefined && other_reminder_count!=null && parseInt(other_reminder_count)>0){
			$("#otherReminderCountSpan").html("("+other_reminder_count+")");
		}else{
			$("#otherReminderCountSpan").html("");
		}
		if(total_reminder_count!=undefined && total_reminder_count!=null && parseInt(total_reminder_count)>0){
			$("#totalReminderCountSpan").html(total_reminder_count);
			$("#btn-news").attr("class","btn-news2");
			$("#totalReminderCountDiv").attr("class","news-popup popupshow");			
		}else{
			$("#btn-news").attr("class","btn-news");
			$("#totalReminderCountDiv").attr("class","news-popup");
			$("#totalReminderCountSpan").html("");
		}
	}
}
function ajaxRequestCommon(div,url,methodType,dataType,callBack){
	if (url == undefined || url == null || url == "null" || url == "") {
		alert("没有请求连接！");
		return;
	}
	if(url.indexOf("?")!=-1){
		url+="&t="+Math.random();
	}else{
		url+="?t="+Math.random();
	}
	url+="&accessFromAjax=true";
	if (methodType == undefined){
		methodType = "get";
	}
	var dt="html";
	if(dataType=="json"){
		dt=dataType;
	}
	$.ajax({
		url : url,
		type : methodType,
		dataType : dt,
		success : function(data){
			if(data!=null && data.msg!=null && data.msg=="noSession" && data.redirectUrl!=null){
				alert("对不起，您已经长时间没有操作了，请重新登录！");
				top.location=data.redirectUrl;
			}else if(data!=null && data.msg!=null && data.msg=="accessDenied"){
				alert("对不起，您没有此链接的访问权限，请联系管理员！");
			}else{
				if(dt=="json"){
					if(callBack!=undefined&&callBack!=null&&typeof(callBack)=="function"){
						callBack.call(this,data);
					}else{
						alert(data);
					}
				}else {
					if(callBack!=undefined&&callBack!=null&&typeof(callBack)=="function"){
						callBack.call(this,data,div);
					}else{
						$(div).html(data);
					}
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("ajaxRequestCommon().error:\ntextStatus="+textStatus+"\nerrorThrown="+errorThrown);
		}
	});	
}

function ajaxRequestCommon4Form(form,autoClose,div,methodType,dataType,callBack){
	if (methodType == undefined){
		methodType = "post";
	}
	var dt="json";
	if(dataType=="html"){
		dt=dataType;
	}
	var options={
			type : methodType,
			dataType : dt,
			success : function(msg){
				if(dt=="json"){
					if(callBack!=undefined&&callBack!=null&&typeof(callBack)=="function"){
						callBack.call(this,msg);
					}else{
						alert(msg);
						if(autoClose!=false){
							closeDialog();
						}
					}
				}else {
					if(callBack!=undefined&&callBack!=null&&typeof(callBack)=="function"){
						callBack.call(this,msg,div);
					}else{
						$(div).html(msg);
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("ajaxRequestCommon().error:\ntextStatus="+textStatus+"\nerrorThrown="+errorThrown);
			}			
	};
	$(form).ajaxSubmit(options);	
}