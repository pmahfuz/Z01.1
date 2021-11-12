/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 */

package assembler;

/**
 * Traduz mnemônicos da linguagem assembly para códigos binários da arquitetura Z0.
 */
public class Code {

    /**
     * Retorna o código binário do(s) registrador(es) que vão receber o valor da instrução.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 4 bits) com código em linguagem de máquina para a instrução.
     */
    public static String dest(String[] mnemnonic) {
        if(mnemnonic[0]=="addw" || mnemnonic[0]=="subw" || mnemnonic[0]=="rsubw" || mnemnonic[0]=="andw" || mnemnonic[0]=="orw"){
            if(mnemnonic.length >= 3) {
                StringBuilder saida = new StringBuilder("0000");
                for (int i = 3; i < mnemnonic.length; i++) {
                    if (mnemnonic[i] == "(%A)") {
                        saida.setCharAt(1, '1');
                    }
                    if (mnemnonic[i] == "%D") {
                        saida.setCharAt(2, '1');
                    }
                    if (mnemnonic[i] == "%A") {
                        saida.setCharAt(3, '1');
                    }
                    //System.out.printf("Saida %s\n", saida.toString());
                }
                return saida.toString();
            }
        }
        if(mnemnonic[0] == "movw"){
            StringBuilder saida = new StringBuilder("0000");
            for (int i = 2; i < mnemnonic.length; i++) {
                if (mnemnonic[i] == "(%A)") {
                    saida.setCharAt(1, '1');
                }
                if (mnemnonic[i] == "%D") {
                    saida.setCharAt(2, '1');
                }
                if (mnemnonic[i] == "%A") {
                    saida.setCharAt(3, '1');
                }
            }
            return saida.toString();
        }

        if(mnemnonic[0] == "leaw"){
            switch (mnemnonic[2]){
                default: return "0000";
                case("(%A)"): return "0100";
                case("%D"): return "0010";
                case("%A"): return "0001";
            }
        }

        if(mnemnonic[0]=="decw" || mnemnonic[0]=="incw" || mnemnonic[0]=="notw" || mnemnonic[0]=="negw"){
            switch (mnemnonic[1]){
                default: return "0000";
                case("(%A)"): return "0100";
                case("%D"): return "0010";
                case("%A"): return "0001";
            }
        }
        if(mnemnonic[0].equals("jmp") || mnemnonic[0].equals("je") || mnemnonic[0].equals("jne") || mnemnonic[0].equals("jg") || mnemnonic[0].equals("jge") || mnemnonic[0].equals("jl") || mnemnonic[0].equals("jle")){
            return "0000";
        }
        else return "0000";
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de cálculo.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 7 bits) com código em linguagem de máquina para a instrução.
     */
    public static String comp(String[] mnemnonic) {
        switch (mnemnonic[0]){
            default: return "000000000";
            case "orw":
                if(mnemnonic[1]=="%A" || mnemnonic[2] == "%A"){
                    return "000" + "010101";
                }
                else{
                    return "001" + "010101";
                }
            case "andw":
                if(mnemnonic[1]=="%A" || mnemnonic[2] == "%A"){
                    return "000" + "000000";
                }
                else{
                    return "001" + "000000";
                }
            case "subw":
                if(mnemnonic[2]=="$1"){
                    if(mnemnonic[1]=="%A"){
                        return "000" + "110010";
                    }
                    else if(mnemnonic[1]=="(%A)"){
                        return "001" + "110010";
                    }
                    else if(mnemnonic[1]=="%D"){
                        return "000" + "001110";
                    }
                }
                else if(mnemnonic[1]=="%A"){
                    return "000" + "000111";
                }
                else if (mnemnonic[1]=="(%A)"){
                    return "001" + "000111";
                }
                else if (mnemnonic[2]=="%A"){
                    return "000" + "010011";
                }
                else if (mnemnonic[2]=="(%A)"){
                    return "001" + "010011";
                }
            case "rsubw":
                if(mnemnonic[1]=="1"){
                    if(mnemnonic[2]=="%A"){
                        return "000" + "110010";
                    }
                    else if(mnemnonic[2]=="(%A)"){
                        return "001" + "110010";
                    }
                    else if(mnemnonic[2]=="%D"){
                        return "000" + "001110";
                    }
                }
                else if(mnemnonic[2]=="%A"){
                    return "000" + "000111";
                }
                else if (mnemnonic[2]=="(%A)"){
                    return "001" + "000111";
                }
                else if (mnemnonic[1]=="%A"){
                    return "000" + "010011";
                }
                else if (mnemnonic[1]=="(%A)"){
                    return "001" + "010011";
                }
            case "addw":
                if(mnemnonic[1]=="$1" || mnemnonic[2]=="1"){
                    if(mnemnonic[1]=="%A" || mnemnonic[2]=="%A"){
                        return "000" + "110111";
                    }
                    else if(mnemnonic[1]=="(%A)" || mnemnonic[2]=="(%A)"){
                        return "001" + "110111";
                    }
                    else if(mnemnonic[1]=="%D" || mnemnonic[2]=="%D"){
                        return "000" + "011111";
                    }
                }
                else if(mnemnonic[1]=="%A" || mnemnonic[2] == "%A"){
                    return "000" + "000010";
                }
                else{
                    return "001" + "000010";
                }
            case "incw":
                if(mnemnonic[1]=="%A"){
                    return "000" + "110111";
                }
                else if(mnemnonic[1]=="(%A)"){
                    return "001" + "110111";
                }
                else if(mnemnonic[1]=="%D"){
                    return "000" + "011111";
                }
            case "decw":
                if(mnemnonic[1]=="%A"){
                    return "000" + "110010";
                }
                else if(mnemnonic[1]=="(%A)"){
                    return "001" + "110010";
                }
                else if(mnemnonic[1]=="%D"){
                    return "000" + "001110";
                }
            case "notw":
                if(mnemnonic[1]=="%A"){
                    return "000" + "110001";
                }
                else if(mnemnonic[1]=="(%A)"){
                    return "001" + "110001";
                }
                else if(mnemnonic[1]=="%D"){
                    return "000" + "001101";
                }
            case "negw":
                if(mnemnonic[1]=="%A"){
                    return "000" + "110011";
                }
                else if(mnemnonic[1]=="(%A)"){
                    return "001" + "110011";
                }
                else if(mnemnonic[1]=="%D"){
                    return "000" + "001111";
                }
            case "movw":
                if(mnemnonic[1]=="%A"){
                    return "000" + "110000";
                }
                else if(mnemnonic[1]=="(%A)"){
                    return "001" + "110000";
                }
                else if(mnemnonic[1]=="%D"){
                    return "000" + "001100";
                }
            case "jmp": return "000" + "001100";
            case "je": return "000" + "001100";
            case "jne": return "000" + "001100";
            case "jg": return "000" + "001100";
            case "jge": return "000" + "001100";
            case "jl": return "000" + "001100";
            case "jle": return "000" + "001100";
        }
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de jump (salto).
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 3 bits) com código em linguagem de máquina para a instrução.
     */
    public static String jump(String[] mnemnonic) {
        switch (mnemnonic[0]) {
            case "jmp":
                return "111";
            case "jg":
                return "001";
            case "je":
                return "010";
            case "jge":
                return "011";
            case "jl":
                return "100";
            case "jne":
                return "101";
            case "jle":
                return "110";
            default:
                return "000";
        }
    }

    /**
     * Retorna o código binário de um valor decimal armazenado numa String.
     * @param  symbol valor numérico decimal armazenado em uma String.
     * @return Valor em binário (String de 15 bits) representado com 0s e 1s.
     */
    public static String toBinary(String symbol) {
        int num;
        try {
            num = Integer.parseInt(symbol);
        }
        catch (NumberFormatException e)
        {
            num = 0;
        }

        String bin = Integer.toBinaryString(num);

        while(bin.length()<16){
            bin = '0' + bin;
        }
        return bin;
    }
}
