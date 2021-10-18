; Arquivo: Abs.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017

; Copia o valor de RAM[1] para RAM[0] deixando o valor sempre positivo.

leaw $1, %A         ; carrega a constant 1 em %A
movw (%A), %D       ; move o valor da constante %A em RAM[%D]
leaw $ELSE, %A
jl %D               ; salta se valor em %D for menor que zero
nop
 
leaw $1, %A
movw (%A), %D
leaw $END, %A
jmp
nop
 
ELSE:
leaw $1, %A
movw (%A), %D
negw %D
 
END:
leaw $0, %A        ; carrega a constant 0 em %A
movw %D, (%A)      ; copia o valor de %D para RAM[%A]
