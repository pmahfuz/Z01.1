; Arquivo: LCDletraGrupo.nasm
; Curso: Elementos de Sistemas
; Criado por: Rafael Corsi
; Data: 28/3/2018
;
; Escreva no LCD a letra do grupo de vocês
;  - Valide no hardawre
;  - Bata uma foto!
; traço do meio (linha e coluna do meio)
leaw $18773, %A
movw $-1, (%A)

;traço no topo (subindo 10 pixel para cima do traço do meio)
leaw $18573, %A   ;119 linha
movw $-1, (%A)

;traço que conecat as parte de cima (cima para baixo)
leaw $32769, %A
movw %A, %D
leaw $18593, %A    ;110
movw %D, (%A)   ;32769 sera aceso o peimiro e ultimo bit

leaw $32769, %A
movw %A, %D
leaw $18613, %A  ;111
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18633, %A  ;112
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18653, %A  ;113
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18673, %A  ;114
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18693, %A  ;115
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18713 %A  ;116
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18733 %A  ;117
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18753 %A  ;118
movw %D, (%A)

;traço que vai ate em baixo
leaw $32769, %A
movw %A, %D
leaw $18793, %A      ;120
movw %D, (%A)   ;32769 sera aceso o peimiro e ultimo bit

leaw $32769, %A
movw %A, %D
leaw $18813, %A  ;121
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18833, %A  ;122
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18853, %A  ;123
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18873 %A  ;124
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18893 %A  ;125
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18913 %A  ;126
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18933 %A  ;127
movw %D, (%A)

leaw $32769, %A
movw %A, %D
leaw $18953 %A  ;128
movw %D, (%A)