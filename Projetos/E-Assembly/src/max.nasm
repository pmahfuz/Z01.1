; Arquivo: Max.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares 
; Data: 27/03/2017
; Log :
;     - Rafael Corsi portado para Z01
;
; Calcula R2 = max(R0, R1)  (R0,R1,R2 se referem a  RAM[0],RAM[1],RAM[2])
; ou seja, o maior valor que estiver, ou em R0 ou R1 sera copiado para R2
; Estamos considerando número inteiros


leaw $1, %A         ; carrega a constant 1 em %A
movw (%A), %D       ; move o valor da constante %A em RAM[%D]
leaw $0, %A         ; carrega a constant 0 em %A
movw (%A), %A       ; move o valor da constante %A em RAM[%A]
subw %D, %A, %D     ; faz %RAM[1] - RAM[0] e salva em %D
leaw $ELSE, %A
jl %D               ; salta se valor em %D for menor que zero
nop
 
leaw $1, %A
movw (%A), %D
leaw $END, %A
jmp
nop
 
ELSE:
leaw $0, %A
movw (%A), %D
 
END:
leaw $2, %A        ; carrega a constant 2 em %A
movw %D, (%A)      ; copia o valor de %D para RAM[%A]
