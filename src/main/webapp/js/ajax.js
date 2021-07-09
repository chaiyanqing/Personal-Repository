		/**
         * ajax封装
         * url 发送请求的地址
         * data 发送到服务器的数据，数组存储，如：{"username": "张三", "password": 123456}
         * succCallback 成功回调函数
         * errorCallback 失败回调函数
         * type 请求方式("POST" 或 "GET")， 默认已经设置为 "POST"
         * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
         * reference jquery-1.7.1.js
         */
		
		var loginUrl = "/login.jsp";
		var baseUrl = "/bodyPlus";
		
        //插入loading
        var html = '<div  class="js_loading" style="position:fixed;top:0;left:0;right:0;bottom:0;z-index:10000;background-color:gray;background-color:rgba(70,70,70,0.2);"><img style="position:absolute;top:50%;left:50%;" width="54px" height="54px" alt="请稍等" src="/bodyPlus/assets/images/loading.gif" /></div>';
        $("body").append(html);
        $(".js_loading").hide();

        function $ajax(url, type, postData, callBack){
            var type = type || "post";
//            var dataType = dataType || "json";

            $.ajax({
                type: type,
                url: baseUrl+url,
                data: postData,
                dataType: "json",
                beforeSend: function(){  //开始loading
                    $(".js_loading").show();                    
                },

                success: function(res){
                	callBack(res);
                	//session超时判断
                    /*if(res.success){
                        if(callBack){
                        	callBack(res);
                        }                       
                    }*//*else{
                        if(errorCallback){
                            errorCallback(res);
                        }
                    } */                  
                },
                error: function(res) {
                	// 添加异常情况处理，以及登录超时处理，下同。
                	console.info(res);
                	//alert("服务器错误!");
                	if(res.status != 200){
                		bootbox.alert(res.status+"：" + res.statusText);
                	}else if(res.status == 200){
                		var sessionstatus = res.getResponseHeader("sessionstatus");
                		console.info("sessionstatus is " + sessionstatus)
                		if(sessionstatus == "timeout"){
                			bootbox.alert("登录超时，请重新登录！", function(rst){
                				window.location.href = baseUrl + loginUrl;
                			});
                		}
                	}
                },
                complete: function(){   //结束loading
                    //$(".js_loading").remove();
                    $(".js_loading").hide();
                }
            });
        }
        function $post(url, postData, callBack){
            var type = "post";
            
            $.ajax({
                type: type,
                url: url,
                data: postData,
                dataType: "json",
                beforeSend: function(){  //开始loading
                    $(".js_loading").show();                    
                },
                success: function(res){
                	callBack(res);
                },
                error: function(res) {
                	console.info(res);
                	// alert("服务器错误!");
                	if(res.status != 200){
                		bootbox.alert(res.status+"：" + res.statusText);
                	}else if(res.status == 200){
                		var sessionstatus = res.getResponseHeader("sessionstatus");
                		console.info("sessionstatus is " + sessionstatus)
                		if(sessionstatus == "timeout"){
                			bootbox.alert("登录超时，请重新登录！", function(rst){
                				window.location.href = baseUrl + loginUrl;
                			});
                		}
                	}
                },
                complete: function(){   //结束loading
                    $(".js_loading").hide();
                }
            });
        }
        
        function $get(url, data, callBack, callBack2){
            var type = "get";
            $.ajax({
                type: type,
                url: url,
                data: data,
                dataType: "json",
                success: function(res){
                	callBack(res);
                },
                error: function(res) {
                	callBack2(res);
                }
            });
        }
        
        function $ajaxAsync(url, type, postData, callBack){
            var type = type || "post";
//            var dataType = dataType || "json";

            $.ajax({
                type: type,
                async:false,
                url: baseUrl+url,
                data: postData,
                dataType: "json",
                beforeSend: function(){  //开始loading
                    $(".js_loading").show();
                },

                success: function(res){
                	callBack(res);
                	//session超时判断
                    /*if(res.success){
                        if(callBack){
                        	callBack(res);
                        }
                    }*//*else{
                        if(errorCallback){
                            errorCallback(res);
                        }
                    } */
                },
                error: function(res) {
                	console.info(res);
                	// alert("服务器错误!");
                	if(res.status != 200){
                		bootbox.alert(res.status+"：" + res.statusText);
                	}else if(res.status == 200){
                		var sessionstatus = res.getResponseHeader("sessionstatus");
                		console.info("sessionstatus is " + sessionstatus)
                		if(sessionstatus == "timeout"){
                			bootbox.alert("登录超时，请重新登录！", function(rst){
                				window.location.href = baseUrl + loginUrl;
                			});
                		}
                	}
                },
                complete: function(){   //结束loading
                    //$(".js_loading").remove();
                    $(".js_loading").hide();
                }
            });
        }


        function $ajaxArray(url, type, postData, callBack){
            var type = type || "post";
//            var dataType = dataType || "json";

            $.ajax({
                traditional:true,
                type: type,
                url: baseUrl+url,
                data: postData,
                dataType: "json",

                beforeSend: function(){  //开始loading
                    $(".js_loading").show();
                },

                success: function(res){
                    callBack(res);
                    //session超时判断
                    /*if(res.success){
                     if(callBack){
                     callBack(res);
                     }
                     }*//*else{
                     if(errorCallback){
                     errorCallback(res);
                     }
                     } */
                },
                error: function(res) {
                	console.info(res);
                    // alert("服务器错误!");
                	if(res.status != 200){
                		bootbox.alert(res.status+"：" + res.statusText);
                	}else if(res.status == 200){
                		var sessionstatus = res.getResponseHeader("sessionstatus");
                		console.info("sessionstatus is " + sessionstatus)
                		if(sessionstatus == "timeout"){
                			bootbox.alert("登录超时，请重新登录！", function(rst){
                				window.location.href = baseUrl + loginUrl;
                			});
                		}
                	}
                },
                complete: function(){   //结束loading
                    //$(".js_loading").remove();
                    $(".js_loading").hide();
                }
            });
        }


        // 将form表单转化为JsonObj
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
        
        function $showError(res){
        	console.info(res);
        	if(res.status != 200){
        		bootbox.alert(res.status+"：" + res.statusText);
        	}else if(res.status == 200){
        		var sessionstatus = res.getResponseHeader("sessionstatus");
        		console.info("sessionstatus is " + sessionstatus)
        		if(sessionstatus == "timeout"){
        			bootbox.alert("登录超时，请重新登录！", function(rst){
    					window.location.href = baseUrl + loginUrl;
        			});
        		}
        	}
        }