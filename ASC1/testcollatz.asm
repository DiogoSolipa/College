.text

MAIN:
	addi $t0, $zero, 2
	addi $t1, $zero, 3
	addi $t4, $zero, 1
	addi $a0, $zero, 3


FOR:
	
	jal COLLATZ
	nop
	
	bne $a0, $t4, FOR
	nop	
		
	move $t5, $v0
	
	#print do nº de iterações
	li $v0, 1
	move $a0, $t5
	syscall
	#encerrar programa
	li $v0, 10 
	syscall
	


COLLATZ:

div $a0, $t0 # 4 / 2 = 2
mfhi $t2 # resto = 0

beq $t2, $zero, PAR # 0 = 0
nop

mult $a0, $t1 # multiplica a0 * 3 = 9
mflo $a0 # valor inteiro do produto = 9

addi $a0, $zero, 1 # 9 + 1 = 10 etc ERRO AQUI!!! NA MEMORIA 9 + 1 = 1 ???????!

addi $v0, $v0 , 1 # contador +1

div $a0, $t0
mfhi $t2 #resto 

beq $t2, $zero, PAR
nop

jr $ra
nop

PAR:

div $a0, $t0 # 4 / 2 = 2
mflo $a0 # quociente = 2

addi $v0, $v0, 1 # contador +1

jr $ra
nop