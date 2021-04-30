var canvas = document.querySelector('canvas');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var s = canvas.getContext('2d');
var rd = canvas.getContext('2d');
var sd = canvas.getContext('2d');
var bd = canvas.getContext('2d');
var wd = canvas.getContext('2d');
var m = canvas.getContext('2d');
var d = canvas.getContext('2d');
var b = canvas.getContext('2d');
var body = canvas.getContext('2d');

var colors = [
	"#80ccff", "#990000", "#99ff99", "#996633", "#d6d6c2", "#476b6b", "#a6a6a6"
];

function sky(s)
{
	var grd = s.createLinearGradient(0, 100, 1500, 0);
	grd.addColorStop(1, "#80d4ff");
	grd.addColorStop(0,"#004080");

	s.fillStyle = grd;
	s.fillRect(0, 100, canvas.width, -200);
}

function buildings(bd)
{
	var offsetBuildings = 0;

	for (var i = 0; i < 7; i++)
	{
		bd.fillStyle = colors[i];
		bd.fillRect(0 + offsetBuildings, 500, 300, -400);

		offsetBuildings += 300;
	}
}

function doors(d)
{
	var offsetDoors = 0;

	d.globalAlpha = 0.5;

	for(var i = 0; i < 7; i++)
	{
		d.fillStyle = "#595959";
		d.fillRect(125 + offsetDoors, 500, 50, -70);

		offsetDoors += 300
	}

	d.globalAlpha = 1.0;
}

function windows(wd)
{
	var offsetWindows = 0;

	for (var i = 0; i < 7; i++)
	{
		wd.fillStyle = "#ffffb3";
		wd.fillRect(65 + offsetWindows, 215, 50, -40);
		wd.fillStyle = "#ffffb3";
		wd.fillRect(180 + offsetWindows, 215, 50, -40);
		wd.fillStyle = "#ffffb3";
		wd.fillRect(65 + offsetWindows, 315, 50, -40);
		wd.fillStyle = "#ffffb3";
		wd.fillRect(180 + offsetWindows, 315, 50, -40);
		wd.fillStyle = "#ffffb3";
		wd.fillRect(65 + offsetWindows, 415, 50, -40);
		wd.fillStyle = "#ffffb3";
		wd.fillRect(180 + offsetWindows, 415, 50, -40);

		offsetWindows += 300;
	}
}

function moon(m)
{
	m.beginPath();
	m.arc(70, 15, 50, Math.PI * 2, false);
	m.fillStyle = "#F4F1C9";
	m.fill();
}

function road(rd)
{
	var grd = rd.createLinearGradient(0, 550, canvas.width, 300);
	grd.addColorStop(0, "#808080");
	grd.addColorStop(1,"#333333");

	rd.fillStyle = grd;
	rd.fillRect(0, 550, canvas.width, 300);

	rd.beginPath();
	rd.moveTo(0, 550);
	rd.lineTo(canvas.width, 550);
	rd.lineWidth = 10;
	rd.strokeStyle = "white";
	rd.stroke();

	rd.beginPath();
	rd.moveTo(0, 850);
	rd.lineTo(canvas.width, 850);
	rd.lineWidth = 10;
	rd.strokeStyle = "white";
	rd.stroke();

	var offsetRoad = 0;

	for (var i = 0; i < 25; i++)
	{
		rd.fillStyle = "white";
		rd.fillRect(0 + offsetRoad, 700, 60, 25);

		offsetRoad += 80;
	}
	
}

function sideWalk(sd)
{
	
	sd.fillStyle = "grey";
	sd.fillRect(0, 500, canvas.width, 50);

	sd.fillRect(0, 850, canvas.width, 200);
	
}

function bicycle(b, x)
{
	this.x = x;

	this.draw = function()
	{
		//left wheel
		b.beginPath();
		b.arc(this.x + 10, 645, 30, Math.PI * 2, false);
		b.strokeStyle = "black";
		b.lineWidth = 5;
		b.stroke();

		b.beginPath();
		b.moveTo(this.x, 645); //450
		b.lineTo(this.x + 50, 645);
		b.lineTo(this.x + 100, 600);
		b.lineTo(this.x + 120, 600);
		b.lineTo(this.x + 140, 645);
		b.moveTo(this.x + 140, 645);
		b.strokeStyle = "black";
		b.stroke();

		//right wheel
		b.beginPath();
		b.arc(this.x + 140, 645, 30, Math.PI * 2	, false);
		b.strokeStyle = "black";
		b.stroke();

		b.beginPath();
		b.moveTo(this.x + 50, 645);
		b.lineTo(this.x + 30, 595);
		b.moveTo(this.x, 645);
		b.lineTo(this.x + 35, 610);
		b.lineTo(this.x + 100, 600);
		b.lineTo(this.x + 100, 590);
		b.lineTo(this.x + 105, 630);
		b.moveTo(this.x + 100, 590);
		b.lineTo(this.x + 95, 580);
		b.stroke();
	}

	this.update = function()
	{
		if (this.x > canvas.width)
		{
			this.x = 0;
		}

		this.x += 5;
		this.draw();
	}
	
	

}

function createBody(body, x)
{
	this.x = x;

	this.draw = function()
	{
	var grd = rd.createLinearGradient(0, 560, canvas.width, 590);
	grd.addColorStop(0, "#0099e6");
	grd.addColorStop(1,"#0059b3");
	
	//head
	body.beginPath();
	body.arc(this.x + 30, 540, 18, Math.PI * 2, false);
	body.strokeStyle = "#ffe6e6";
	body.stroke();

	//body
	body.beginPath();
	body.fillStyle = grd;
	body.fillRect(this.x + 15, 560, 32, 40);
	
	//left arm
	body.beginPath();
	body.moveTo(this.x + 45, 565);
	body.lineTo(this.x + 100, 580);
	body.strokeStyle = "#ffe6e6";
	body.stroke();
	//right arm
	body.beginPath();
	body.moveTo(this.x + 47, 580);
	body.lineTo(this.x + 97, 600);
	body.strokeStyle = "#ffe6e6";
	body.stroke();

	//left leg
	body.beginPath();
	body.moveTo(this.x + 25, 600);
	body.lineTo(this.x + 40, 640);
	body.strokeStyle = "#ffe6e6";
	body.stroke();
	//right leg
	body.beginPath();
	body.moveTo(this.x + 40, 600);
	body.lineTo(this.x + 60, 640);
	body.strokeStyle = "#ffe6e6";
	body.stroke();
	}

	this.update = function()
	{
		if (this.x > canvas.width)
		{
			this.x = 0;
		}

		this.x += 5;
		this.draw();
	}
	
}

function main()
{
	body1 = new createBody(body, 0);
	body1.draw();

	bic = new bicycle(b, 0);

	function animate() {
		requestAnimationFrame(animate);
		

		sky(s);
		moon(m);
		buildings(bd);
		windows(wd);
		road(rd);
		sideWalk(sd);
		doors(d);

		bic.update();
		body1.update();

		//body1.update();
	}

	animate();

}

/*
body = new body(valores);
body.draw();

function animate() {
	requestAnimationFrame(animate);
	
	funcoes normais
	updates
}

animate();*/
