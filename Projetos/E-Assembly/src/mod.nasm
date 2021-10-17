; ------------------------------------------------------------
; Arquivo: Mod.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017
;
; Calcula o resto da divisão (modulus) entre RAM[0] por RAM[1]
; e armazena o resultado na RAM[2].
;
; 4  % 3 = 1
; 10 % 7 = 3
; ------------------------------------------------------------

; ************************************************************
; (RAM[0] = 5, RAM[1] = 2)
leaw $0, %A
movw (%A), %D
leaw $2, %A
movw %D, (%A) ; move o valor de RAM[0] para RAM[2] (5 em RAM[2])
LOOP: ; LOOP1-> RAM[0] = 5, RAM[1] = 2, RAM[2] = 5 | LOOP2-> RAM[0] = 5, RAM[1]=2, RAM[2] = 3
leaw $1, %A
movw (%A), %D  ; carrega RAM[1] em %D (2 em RAM[1]) (=)
leaw $2, %A
subw (%A), %D, %D ; faz RAM[2] - RAM [1] e guarda em %D (3 em %D) 
leaw $2, %A
movw %D, (%A) ; carrega resto em RAM[2] (3 em RAM[2])
leaw $1, %A
subw %D, (%A), %D ; faz resto - RAM[1] e guarda em %D (1 em %D)
leaw $LOOP, %A
jg %D ; se %D for maior que 0, volta (1>0,volta)
nop