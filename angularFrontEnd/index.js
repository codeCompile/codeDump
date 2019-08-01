// var http = require('http');

// var server = http.createServer(function(request, response) {

//     response.writeHead(200, {"Content-Type": "text/plain"});
//     response.end("Hello World!");

// });

// var port = process.env.PORT || 1337;
// server.listen(port);

// console.log("Server running at http://localhost:%d", port);

var express = require('express');

var app = express();
var server = require('http').Server(app);
//var io = require('socket.io').listen(server);
var port = process.env.PORT || 1337;

var path = require('path');
// app.configure(function(){
//   //app.use('/media', express.static(__dirname + '/media'));
//   app.use(express.static(__dirname + '/public'));
// });

app.use(express.static(path.join(__dirname, 'app'))); //  "public" off of current is root

server = server.listen(port);
// server.use(app.static(__dirname + '/app'));
app.get('/', function (req, res) {
  res.sendFile(__dirname + '/app/index.html');
});

console.log("Server running at http://localhost:%d", port);
var express = require('express');