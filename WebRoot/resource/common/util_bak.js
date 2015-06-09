var util = {
	addOption:function(targetid,opt,pos){
		var targetObj = $(targetid);
		var optionObj = new Option(opt.label,opt.value);
		if (!pos || pos == -1 || pos > targetObj.options.length){ 
    		targetObj.options[targetObj.options.length] = optionObj;
    	}
  		else{
  			targetObj.add(optionObj, pos);
  		}   				
	},
	addOptions:function(targetid,opts){
		var targetObj = $(targetid);
		for(var i=0;i<opts.length;i++){
			targetObj.options[targetObj.options.length]=opts[i];	
		}		
	},
	deleteAllOption:function(targetid){
		var targetObj = $(targetid);
		targetObj.options.length=0;	
	},
	deleteOption:function(targetid){
		var targetObj = $(targetid);
		while(targetObj.selectedIndex!=-1){
			targetObj.options[targetObj.selectedIndex]=null;
		}
	},
	getOptions:function(targetid){
		var targetObj = $(targetid);
		return targetObj.options;
	},
	validateStrLength:function(val,lenmin,lenmax){
		if(!val){
			return false;
		}		
		len = val.length;
		if(len<lenmin || len>lenmax) {		
			return false;
		}
		return true;
	},
	trim:function(str){
	  return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	ltrim:function(str){
		return str.replace(/(^\s*)/g, "");
	},
	rtrim:function(str){
		return str.replace(/(\s*$)/g, "");		
	},
	validateFloat:function(val) {  		
	  if(val==="")
	  {
	  	return true;
	  }
	  if(this.trim(val)==="") {
	  	return false;
	  }
  	  var len=val.length-1;
  	  len=val.length-1;
	  if(val.charAt(len)=='.')
	  {
	  	val=val+'0';
	  }
	  var reg=new RegExp("^(-?\\d+)(\\.\\d+)?$");
	  if(reg.test(val)===false){
	    return false;
	  }	  
	  return true;	
	},
	formatNumber:function (str) {
		var reg1=/^(-?00)[.]*/;
		var reg2=/^(-?0)[^\.]+/;
		if(reg1.test(str)) {
			reg1=/00/;
			str=str.replace(reg1,'0');
			str=this.formatNumber(str);	
		}
		else if(reg2.test(str)) {
			reg2=/0/;
			str=str.replace(reg2,'');
			str=this.formatNumber(str);
		}
		return str;
	},
	validateInteger:function (val) {		
	  if(this.trim(val)==="") {
	  	return false;
	  }
	  
	  var reg=new RegExp("^-?\\d+$");
	  if(reg.test(val)===false){
	    return false;
	  }
	  return true;		
	},
	validatePlusInteger:function(val){
		if(!this.validateInteger(val)){
			return false;
		} else {
			if(eval(val)<=0){
				return false;
			}
		}
		return true;
	},
	validateDate:function (date,format){
	  var time=this.trim(date);
	  if(time==="") return false;
	  var reg=format;
	  var reg=reg.replace(/yyyy/,"[0-9]{4}");
	  var reg=reg.replace(/yy/,"[0-9]{2}");
	  var reg=reg.replace(/MM/,"((0[1-9])|1[0-2])");
	  var reg=reg.replace(/M/,"(([1-9])|1[0-2])");
	  var reg=reg.replace(/dd/,"((0[1-9])|([1-2][0-9])|30|31)");
	  var reg=reg.replace(/d/,"([1-9]|[1-2][0-9]|30|31))");
	  var reg=reg.replace(/HH/,"(([0-1][0-9])|20|21|22|23)");
	  var reg=reg.replace(/H/,"([0-9]|1[0-9]|20|21|22|23)");
	  var reg=reg.replace(/mm/,"([0-5][0-9])");
	  var reg=reg.replace(/m/,"([0-9]|([1-5][0-9]))");
	  var reg=reg.replace(/ss/,"([0-5][0-9])");
	  var reg=reg.replace(/s/,"([0-9]|([1-5][0-9]))");
	  reg=new RegExp("^"+reg+"$");
	  if(reg.test(date)==false){
	    return false;
	  }
	  return true;
	},
	validateDateGroup:function(year,month,day){
	  var array=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	  var y=parseInt(year);
	  var m=parseInt(month);
	  var d=parseInt(day);
	  var maxday=array[m-1];
	  if(m==2){
	    if((y%4==0&&y%100!=0)||y%400==0){
	      maxday=29;
	    }
	  }
	  if(d>maxday){
	    return false;
	  }
	  return true;
	},
	validateCheckbox:function(obj){
	  var rs=false;
	  if(obj!=null){
	    if(obj.length==null){
	      if(obj.checked)	
	      	return true;
	    }
	    else {
	    	for(var i=0;i<obj.length;i++){
	      		if(obj[i].checked==true){
	        		return true;
	      		}
	    	}
	    }
	  }
	  return rs;
	},
	validateRadio:function(obj){
	  var rs=false;
	  if(obj!=null){
	    if(obj.length==null){
	      return obj.checked;
	    }
	    for(i=0;i<obj.length;i++){
	      if(obj[i].checked==true){
	        return true;
	      }
	    }
	  }
	  return rs;
	},
	getRadioValue:function(RadioName){
	    var obj;    
	    obj=document.getElementsByName(RadioName);
	    if(obj!=null){
	        var i;
	        for(i=0;i<obj.length;i++){
	            if(obj[i].checked){
	                return obj[i].value;            
	            }
	        }
	    }
	    return null;
	},
	validateSelect:function(obj){
	  var rs=false;
	  if(obj!=null){
	    for(i=0;i<obj.options.length;i++){
	      if(obj.options[i].selected==true){
	        return true;
	      }
	    }
	  }
	  return rs;
	},
	validateEmail:function(email,separator){
	  var mail=this.trim(email.value);
	  if(mail=="") {
	      email.focus();
		  return false;
	  }
	  var em;
	  var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
	  if(separator==null){
	    if(myReg.test(email.value)==false){
	      email.focus();
	      return false;
	    }
	  }
	  else{
	    em=email.value.split(separator);
	    for(i=0;i<em.length;i++){
	      em[i]=em[i].trim();
	      if(em[i].length>0&&myReg.test(em[i])==false){
	        email.focus();
	        return false;
	      }
	    }
	  }
	  return true;
	},
	mask_HTMLCode:function(strInput) { 
	   var myReg = /<(\w+)>/; 
	   return strInput.replace(myReg, "&lt;$1&gt;"); 
	},
	isIdCardNo:function(val) { 
	    var len = val.length, re;  
	    if (len == 15) 
	      re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/); 
	    else if (len == 18) 
	      re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})([0-9xX])$/); 
	    else 
	    {	    
	    	return false;
	    } 
	    var a = val.match(re); 
	    if (a != null) 
	    { 
	      if (len==15) 
	      { 
	        var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
	        var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
	      } 
	      else 
	      { 
	        var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
	        var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
	      } 
	      if (!B) 
	      {	      	
	      	return false;
	      } 
	    }
	    else
	    {	    	
	    	return false;
	    } 
	    return true; 
	},
	isNumber:function(val)
	{
		var reg=new RegExp(/^[0-9]+$/);
		return reg.test(val);
	},
	checkIP:function(ip){
		var reg= RegExp(/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/);
		return reg.test(ip);
	},
	checkDate:function(date)
	{
		
		if(this.validateDate(date,'yyyy-MM-dd'))
		{
			var ymd=date.split('-');
			if(ymd.length!=3)
			{
				return false;
			}
			else
			{
				if(!this.validateDateGroup(ymd[0],ymd[1],ymd[2]))
				{
					return false;
				} 
			}
		}
		else
		{
			return false;
		}
		return true;
	},
	movetoend:function(obj){
		if(obj.value.length==0){
			return;
		}
		var len=obj.value.length;
		var rng=obj.createTextRange();
		rng.moveStart('character',len);
		rng.collapse(true);
		rng.select();
	},
	DateDiff:function(sDate1, sDate2){  
	    var aDate, oDate1, oDate2, iDays 
	    aDate = sDate1.split('-') 
	    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
	    aDate = sDate2.split('-') 
	    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]) 
	    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24) 
	    return iDays 
	},
	addOneDay:function(sDate){
		var aDate,oDate,iDays;
		aDate = sDate.split('-');
		oDate = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
		iDays = parseInt(oDate/(1000*60*60*24));
		iDays = iDays+2;
		var newDate = new Date(iDays*1000*60*60*24);
		var giYear = newDate.getFullYear()+'';
		var giMonth = newDate.getMonth()+1+'';
		var giDay = newDate.getDate()+'';
		
		if(giMonth.length<2){
			giMonth='0'+giMonth;
		}
		if(giDay.length<2){
			giDay = '0'+giDay;
		}	
		return giYear+'-'+giMonth+'-'+giDay;
	}
};


