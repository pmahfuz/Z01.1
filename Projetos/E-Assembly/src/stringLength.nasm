; Arquivo: stringLength.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi 
; Data: 28/03/2018
;
; Assuma que uma string é um conjunto de caracteres terminado
; em NULL (0).
; 
; Supondo que temos uma string que começa no endereço 8 da RAM.
; Calcule o seu tamanho e salve o resultado na RAM[0].
;
; Os caracteres estão formatados em ASCII
; http://sticksandstones.kstrom.com/appen.html
; 
; Exemplo:
;
;   Convertido para ASCII
;             |
;             v
;  RAM[8]  = `o`
;  RAM[9]  = `i`
;  RAM[10] = ` `
;  RAM[11] = `b`
;  RAM[12] =  l`
;  RAM[13] = `z`
;  RAM[14] = `?`
;  RAM[15] = NULL = 0x0000
; --------------------------------------------------------------------------

; ex: RAM[8] = 1, RAM[9] = 2, RAM[10] = 0
leaw $0, %A
movw %A, %D
leaw $0, %A
movw %D, (%A) ; certifica que RAM[0] = 0

leaw $8, %A
movw %A, %D
leaw $1, %A 
movw %D, (%A) ; faz RAM[1] = 8

LOOP:
leaw $0, %A
movw (%A), %D 
incw %D 
movw %D, (%A); faz RAM[0] = RAM[0] + 1 (RAM[0] = 1) (=2)
leaw $1, $A
movw (%A), %D 
incw %D
movw %D, (%A); faz RAM[1] = RAM[1] + 1 (RAM[1] = 9) (=10)
movw %D, %A; faz %A apontar para $RAM[1] (aponta para $9) (=$10)
movw (%A), %D; faz %D = [RAM[1] + 1] (%D = 2) (%D=0)
leaw $LOOP, %A
jg %D; %D > 0, volta (volta)
nop


