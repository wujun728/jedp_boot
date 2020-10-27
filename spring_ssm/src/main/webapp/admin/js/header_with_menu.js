$(function(){
	$("#pwdModify").click(function(){
		$("#pwdModal").modal({backdrop: "static", keyboard: false});
	});
	
	$("#submit").click(function(){
		var oldPassword = CryptoJS.MD5($("input[name='oldPassword']").val()) + "";
		var newPassword = CryptoJS.MD5($("input[name='newPassword']").val()) + "";
		var confirmPassword = CryptoJS.MD5($("input[name='confirmPassword']").val()) + "";
		$.ajax({
		   	type: "POST",
		   	url: ctxPath+"/user/pwdModify",
		   	dataType: "json",
		   	data: {oldPassword:oldPassword,newPassword:newPassword,confirmPassword:confirmPassword},
		   	success: function(response){
		   		if(response.code == 0 && response.msg == "success"){
		   			alert("修改成功");
		   			$('#pwdModal').modal('hide');
		   		}else{
		   			alert(response.msg);
		   		}
		   	}
		});
	});
});