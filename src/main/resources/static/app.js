

(function(window){

	var ENTER_KEY = 13;
	
	var stompClient = null;

	var App = {


		init : function(){
			this.bindEvents();
			this.connect();
			this.loadChatData();
			
		},

		bindEvents : function(){
		    $("#chatContent").on('keyup', this.create.bind(this));
		    $("#send").on('click', this.clickcreate.bind(this));
			
			//$("#connect").on('click', this.connect.bind(this));
		    //$("#disconnect").on('click', this.disconnect.bind(this));
		    //$("#send").on('click', this.sendData.bind(this));
		    

		},

		setConnected : function(connected) {
		    $("#connect").prop("disabled", connected);
		    $("#disconnect").prop("disabled", !connected);
//		    if (connected) {
//		        $("#conversation").show();
//		    }
//		    else {
//		        $("#conversation").hide();
//		    }
//		    $("#greetings").html("");
		},
		
		
		clickcreate: function(event){
			this.sendData();
		},


		connect : function() {
		    var socket = new SockJS('/gs-guide-websocket');
		    stompClient = Stomp.over(socket);
		    stompClient.connect({}, function (frame) {
		        App.setConnected(true);
		        console.log('Connected: ' + frame);
		        stompClient.subscribe('/topic/chatline', function (messageData) {
		            //App.showMessage(JSON.parse(messageData.body).content);
		        	App.showMessage(JSON.parse(messageData.body));
		        });
		    });
		},

		disconnect : function() {
		    if (stompClient != null) {
		        stompClient.disconnect();
		    }
		    this.setConnected(false);
		    console.log("Disconnected");
		},

		sendData : function() {
			var data = JSON.stringify({'chatName': $("#chatName").val(), 'chatContent': $("#chatContent").val()});
			stompClient.send("/app/hello", {}, data);
		    $.ajax({
		    	  "url": "./api/chats", "method": "POST", "headers": {"content-type": "application/json"}, "data": data
		    	}).fail(function(error){
		    		console.log(error);
		    	})

		    $("#chatContent").val('');
		},

		loadChatData : function(){
			$.ajax({"url": "./api/chats", "method": "GET","headers": {"content-type": "application/json"}
			}).done(function(data){
				App.draw(data);
			}).fail(function(error){
				console.log(error);
			});
			
		},

		draw : function(data){
			for(var i=0; i< data.length; i++){
				
			    $(".media").append("<a class='pull-left' href='#'>\
										<img class='media-object img-circle' src='./images/spacemax2.jpg'>\
			    					</a>\
			    					<div class='media-body'>\
										<h4 class='media-heading'>\
											" + data[i].chatName + "<span class='small pull-right'>" + data[i].date + "</span>\
										</h4>\
									</div>\
									<p style='height: 40px;'>" + data[i].chatContent + "</p>"	);
			}
			$("#chatroom").scrollTop($("#chatroom")[0].scrollHeight);
		},

		showMessage : function(message) {
			$(".media").append("<a class='pull-left' href='#'>\
					<img class='media-object img-circle' src='./images/spacemax2.jpg'>\
				</a>\
				<div class='media-body'>\
					<h4 class='media-heading'>\
						" + message.chatName + "<span class='small pull-right'>" + message.date + "</span>\
					</h4>\
				</div>\
				<p style='height: 40px;'>" + message.chatContent + "</p>"	);
			$("#chatroom").scrollTop($("#chatroom")[0].scrollHeight);
		},
		
		create: function (event) {
			var $input = $(event.target);
			var val = $input.val().trim();

			if (event.which !== ENTER_KEY || !val || event.shiftKey) {
				return;
			}
			
			this.sendData();
		}
	};



	App.init();

})(window);
