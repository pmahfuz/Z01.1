; Arquivo: Pow.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017

; Eleva ao quadrado o valor da RAM[1] e armazena o resultado na RAM[0].
; Só funciona com números positivos

leaw $1, %A         ; carrega a const 1 em %A, ou seja, %A = 1
movw (%A), %D       ; move o valor da RAM[%A] para %D, ou seja, %D = RAM[1]
leaw $2, %A         ; carrega a const 2 em %A, ou seja, %A = 2
movw %D, (%A)       ; move o valor da RAM[%A] para %D, ou seja, %D = RAM[2]
LOOP:               ; começa o loop
leaw $1, %A         ; carrega a const 1 em %A, ou seja, %A = 1
movw (%A), %D       ; move o valor da RAM[%A] para %D, ou seja, %D = RAM[1]
leaw $0, %A         ; carrega a const 0 em %A, ou seja, %A = 0
addw (%A), %D, %D   ; soma o primeiro valor ao segundo valor e armazena o resultado no terceiro parâmetro, ou seja, RAM[0] + D = D
movw %D, (%A)       ; move o valor de %D para a RAM[%A]
leaw $2, %A         ; carrega a const 2 em %A, ou seja, %A = 2
movw (%A), %D       ; move o valor da RAM[%A] para %D, ou seja, %D = RAM[2]
subw %D, $1, (%A)   ; subtrai o segundo valor do primeiro valor e armazena o resultado no terceiro parâmetro, ou seja, D - 1 = RAM[%A]
movw (%A), %D       ; move o valor da RAM[%A] para %D
leaw $LOOP, %A      ; carrega o valor do salto em %A
jne                 ; faz o salto incondicional
nop                 ; nop