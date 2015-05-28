var arr = ["jpg","png","bmp"];
function uploadValid(photo){
	//先取得后缀名
	var ext = photo.substr(photo.lastIndexOf(".")+1);
	//进行判断
	var flag = false;
	for(var i=0;i<arr.length;i++){
		if(ext.toLowerCase()==arr[i]){
			flag = true;
			break;
		}
	}
	return flag;
}