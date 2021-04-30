.data
imgInput:     .asciiz"/home/lockit/Desktop/TrabalhoASC1/lena.gray"  
imgOutput:    .asciiz"/home/lockit/Desktop/TrabalhoASC1/lenaMean.gray"
imgOutput2:   .asciiz"/home/lockit/Desktop/TrabalhoASC1/lenaMedian.gray"
imgSize:      .space 512
bufferIn:     .space 262144
bufferOut:    .space 262144
bufferMedian: .space 9

.text
MAIN:
	jal read_gray_image
	nop	
	la $a0, bufferIn
	la $a1, bufferOut	
	la $a2, imgSize
	jal mean_filter
	nop
	la $a0, imgOutput
	la $a1, bufferOut
	la $a2, 262144
	jal write_gray_image
	nop
	la $a0, bufferIn
	la $a1, bufferOut
	la $a2, imgSize
	jal median_filter
	nop
	la $a0, imgOutput2
	la $a1, bufferOut
	la $a2, 262144
	jal write_gray_image
	nop
	
	li $v0, 10			# Fim do programa
	syscall


#########################################################################################	
#read_gray_image - Esta funcao abre o ficheiro e le a imagem em memória(dados dinamicos)#
#											#
#Argumentos:										#
# a0 - string contendo o nome do ficheiro para abrir					#
# a1 - 											#
# a2 - apenas para leitura								#
# 											#
#Retorna:										#
# v0 - abrir o ficheiro									#
#########################################################################################	 
read_gray_image:

#OPEN TO READ
la $a0, imgInput
li $a1, 0
li $a2, 0
li $v0, 13 
syscall
move $s0, $v0


#READ
li $v0, 14
move $a0, $s0
la $a1, bufferIn
li $a2, 262144
syscall

jr $ra
nop

################################################################################
#write_gray_image - Esta funcao escreve uma imagem em formato GRAY num ficheiro#
#									       #
#Argumentos:								       #
# a0 - nome do ficheiro                                                        #
# a1 - buffer(com a imagem e o comprimento do buffer)                          #
#                                                                              #
#Retorna:                                                                      #
# v0 - a imagem no formato GRAY                                                #
################################################################################
write_gray_image:

#OPEN TO WRITE
li $a1, 1
li $a2, 0
li $v0, 13
syscall
	
#WRITE
move $a0, $v0
la $a1, bufferOut
li $a2, 262144
li $v0, 15
syscall



#CLOSE FILES
li $v0, 16
syscall

jr $ra
nop

###########################################################################################
#mean_filter: funcao que calcula a convolucao da imagem, com uma mascara(uma matriz 3x3)
#             
#Argumentos:
# a0 -
# a1-
# a2- dimensoes da imagem
# 
#Retorna:
#
############################################################################################
mean_filter:

	addi $t3, $zero, 511
	addi $a0, $a0, 511
	addi $a1, $a1, 511

FORMEAN:
#if()
#correspondentes a execução do mean_filter:


	lbu $t1, 0($a0)
	add $t2, $zero, $t1

	lbu $t1, -512($a0)
	add $t2, $t2, $t1

	lbu $t1, -511($a0)
	add $t2, $t2, $t1

	lbu $t1, -510($a0)
	add $t2, $t2, $t1

	lbu $t1, -1($a0)
	add $t2, $t2, $t1

	lbu $t1, 1($a0)
	add $t2, $t2, $t1

	lbu $t1, 511($a0)
	add $t2, $t2, $t1

	lbu $t1, 512($a0)
	add $t2, $t2, $t1

	lbu $t1, 513($a0)
	add $t2, $t2, $t1

	div $t2, $t2, 9
	sb $t2, 513($a1)

	#Contador

	addi $a0, $a0, 1
	addi $a1, $a1, 1
	addi $t3, $t3, 1

	ble $t3, 262114, FORMEAN	
	nop
		
	jr $ra 
	nop
#########################################
median_filter:
	addi $sp, $sp, -4
	sw $s0, 0($sp)
	
	addi $s0, $zero, 511
	addi $a0, $a0, 511
	addi $a1, $a1, 511
	la $t0, bufferMedian
	
FORMEDIAN:
	

	lbu $t1, 0($a0)
	lbu $t2, -512($a0)
	lbu $t3, -511($a0)
	lbu $t4, -510($a0)
	lbu $t5, -1($a0)
	lbu $t6, 1($a0)
	lbu $t7, 511($a0)
	lbu $t8, 512($a0)
	lbu $t9, 513($a0)

	sb $t1, 0($t0)
	sb $t2, 4($t0)
	sb $t3, 8($t0)
	sb $t4, 12($t0)
	sb $t5, 16($t0)
	sb $t6, 20($t0)
	sb $t7, 24($t0)
	sb $t8, 28($t0)
	sb $t9, 32($t0)
SORT:
	
	
	
	
	
	
	addi $a0, $a0, 1
	addi $a1, $a1, 1
	addi $s0, $s0, 1

	ble $s0, 262114, FORMEDIAN	
	nop
		
	lw $s0, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra 
	nop
