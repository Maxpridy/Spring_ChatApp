

(function(window){

	var stompClient = null;

	var App = {


		init : function(){
			this.bindEvents();

			this.connect();

			this.loadChatData();
		},

		bindEvents : function(){

		    $('form').on('submit', function (e) {
		        e.preventDefault();

		    }.bind(this));
		    $( "#connect" ).on('click', this.connect.bind(this));
		    $( "#disconnect" ).on('click', this.disconnect.bind(this));
		    $( "#send" ).on('click', this.sendName.bind(this));


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

		connect : function() {
		    var socket = new SockJS('/gs-guide-websocket');
		    stompClient = Stomp.over(socket);
		    stompClient.connect({}, function (frame) {
		        App.setConnected(true);
		        console.log('Connected: ' + frame);
		        stompClient.subscribe('/topic/greetings', function (greeting) {
		            App.showGreeting(JSON.parse(greeting.body).content);
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

		sendName : function() {
			var data = JSON.stringify({'name': $("#name").val(), 'chat':$("#chat").val()});
			stompClient.send("/app/hello", {}, data);
		    $.ajax({
		    	  "url": "./api/chats", "method": "POST", "headers": {"content-type": "application/json"}, "data": data
		    	}).fail(function(error){
		    		console.log(error);
		    	})

		    $("#chat").val('');
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
			for(var i=0; i< data.length-1; i++){
				$("#greetings").append("<tr><td>" + data[i].name + " : " + data[i].chat + "</td></tr>");
			}
		},

		showGreeting : function(message) {
		    $("#greetings").append("<tr><td>" + message + "</td></tr>");
		}

	};



	App.init();

})(window);
