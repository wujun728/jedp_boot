/**
 * 用户管理页面的JS操作
 * @param $
 */
(function($){
	$.common={
			
			formatSearchParam:function(formId,limit,pgNo){
				  var object=new Object(); 
				  var queryStr=$("#"+formId).serialize();
				  
				  if(queryStr != null && queryStr != undefined &&  queryStr !=''){
					  var queryStrArray=queryStr.split("&");
					  if(queryStrArray != null && queryStrArray != undefined && queryStrArray.length > 0){
						  for(var i=0;i<queryStrArray.length;i++){
							  var ss=queryStrArray[i].split("=");
							  if(ss != null && ss != undefined && ss.length == 2){
								 if(ss[0] != null && ss[0] != undefined && ss[0] !='' && ss[1] != null && ss[1] != undefined && ss[1] !='')
							         if(object[ss[0]] == null){
							        	 object[ss[0]]=decodeURIComponent(ss[1]);	 
							         }else{
							        	 object[ss[0]]=object[ss[0]]+","+decodeURIComponent(ss[1]);
							        }
									 
							  }
						  }
					  }
				  }
				  if(limit != null && limit != undefined && limit !='')
				  object["pageSize"]=limit;
				  if(pgNo != null && pgNo != undefined && pgNo !='')
				  object["pageNumber"]=pgNo;
				  return object;
			},
			hasauthority:function(auth_url){
				 return USER_AUTHORITY_URL.indexOf(auth_url)>=0;
			}
			
	}
})(jQuery); 