a)
Select Matricula
From taxi
NATURAL INNER JOIN modelo 
Where Marca = 'Mercedes' 

b)
Select Nome
From motorista
NATURAL INNER JOIN taxi NATURAL INNER JOIN turno NATURAL INNER JOIN modelo
Where Marca = 'Mercedes'
