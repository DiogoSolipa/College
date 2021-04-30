

extends Node


# Declare member variables here. Examples:

var rng = RandomNumberGenerator.new()

var arrFacil = ["bola", "relógio", "telefone", "carregador", "água", "toalha", "portátil", "tomada"]

var arrMedio = ["holofote","individuais", "python", "frigorifico", "instagram" ]

var arrDificil = ["ortopedia", "espanador", "malmequer", "esquentador", "paradigma"]


# Called when the node enters the scene tree for the first time.
func _ready():
	
	rng.randomize()
	
	var randomF = rng.randi_range(0, arrFacil.size() - 1)
	var randomM = rng.randi_range(0, arrMedio.size() - 1) 
	var randomD = rng.randi_range(0, arrDificil.size() - 1)
	
	
	print(hasLetter('a', arrDificil, randomD))

func hasLetter(letter, arr, index):
	
		for i in arr[index]:
			if i == letter:
				return true
				
		return false


# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass



