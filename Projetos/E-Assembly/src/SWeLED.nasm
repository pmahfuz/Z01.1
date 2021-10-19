; Arquivo: SWeLED.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi
; Data: 28/3/2018
;
; Faça os LEDs exibirem 
; LED = ON ON ON ON ON !SW3 !SW2 !SW1 0
; Mesma questão da prova

leaw $496, %A
movw %A, %D 
leaw $21184, %A
movw %D, (%A)

leaw $2, %A
movw %A, %D 
leaw $21185, %A 
andw %D, (%A), %D
leaw $IF, %A
jne 
nop  
leaw $2, %A 
movw %A, %D 
leaw $21184, %A
addw %D, (%A), %D
movw %D, (%A)

IF:
leaw $4, %A
movw %A, %D 
leaw $21185, %A 
andw %D, (%A), %D 
leaw $ELIF, %A 
jne 
nop 
leaw $4, %A 
movw %A, %D
leaw $21184, %A 
addw %D, (%A), %D
movw %D, (%A)

ELIF:
leaw $8, %A 
movw %A, %D 
leaw $21185, %A 
andw %D, (%A), %D 
leaw $END, %A 
jne 
nop 
leaw $8, %A 
movw %A, %D 
leaw $21184, %A 
addw %D, (%A), %D 
movw %D, (%A)
END:
