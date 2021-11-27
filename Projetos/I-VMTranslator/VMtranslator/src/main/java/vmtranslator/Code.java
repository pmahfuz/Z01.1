/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 * Created by Luciano Soares <lpsoares@insper.edu.br>
 * Date: 2/05/2017
 * Adaptado por Rafael Corsi <rafael.corsi@insper.edu.br>
 * Date: 5/2018
 */

package vmtranslator;

import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Traduz da linguagem vm para códigos assembly.
 */
public class Code {

    PrintWriter outputFile = null;  // arquivo .nasm de saída
    String filename = null;         // arquivo .vm de entrada
    int lineCode = 0;               // Linha do codigo vm que gerou as instrucoes
    int cont = 0;
    /**
     * Abre o arquivo de saida e prepara para escrever
     * @param filename nome do arquivo NASM que receberá o código traduzido.
     */
    public Code(String filename) throws FileNotFoundException,IOException {
        File file = new File(filename);
        this.outputFile = new PrintWriter(new FileWriter(file));
    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para executar o comando aritmético.
     * @param  command comando aritmético a ser analisado.
     */
    public void writeArithmetic(String command) {

        if ( command.equals("")) {
            Error.error("Instrução invalida");
        }

        List<String> commands = new ArrayList<String>();

        if(command.equals("add")) {
            commands.add(String.format("; %d - ADD", lineCode++));
            commands.add("leaw $0, %A");
            commands.add("movw (%A), %A");
            commands.add("decw %A");
            commands.add("movw (%A), %D");
            commands.add("decw %A");
            commands.add("addw (%A), %D, %D");
            commands.add("movw %D, (%A)");
            //commands.add("addw $1, %A, %D");
            //commands.add("leaw $0, %A");
            //commands.add("movw %D, (%A)");

        } else if (command.equals("sub")) {
            commands.add(String.format("; %d - SUB", lineCode++));
            // IMPLEMENTAR AQUI O LAB
            // LEMBRAR DE USAR A FUNÇÃO commands.add()!
        } else if (command.equals("neg")) {
            commands.add(String.format("; %d - NEG", lineCode++));

        } else if (command.equals("eq")) {
            commands.add(String.format("; %d - EQ", lineCode++));

        } else if (command.equals("gt")) {
            commands.add(String.format("; %d - GT", lineCode++));

        } else if (command.equals("lt")) {
            commands.add(String.format("; %d - LT", lineCode++));

        } else if (command.equals("and")) {
            commands.add(String.format("; %d - AND", lineCode++));

        } else if (command.equals("or")) {
            commands.add(String.format("; %d - OR", lineCode++));

        } else if (command.equals("not")) {

        }

        String[] stringArray = new String[ commands.size() ];
        commands.toArray( stringArray );
        write(stringArray);

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para executar o comando de Push ou Pop.
     * @param  command comando de push ou pop a ser analisado.
     * @param  segment segmento de memória a ser usado pelo comando.
     * @param  index índice do segkento de memória a ser usado pelo comando.
     */
    public void writePushPop(Parser.CommandType command, String segment, Integer index) {

        if ( command.equals("")) {
            Error.error("Instrução invalida");
        }

        List<String> commands = new ArrayList<String>();

        if(command == Parser.CommandType.C_POP) {
            commands.add(String.format("; %d - POP %s %d", lineCode++ ,segment, index));

            if (segment.equals("constant"))
                Error.error("Não faz sentido POP com constant");
            } else if (segment.equals("local")) {
                commands.add("leaw $SP, %A");      // carega SP em %A
                commands.add("movw (%A), %D");     // pega valor que SP aponta
                commands.add("decw %D");           // diminui o valor da linha em 1
                commands.add("movw %D, (%A)");     // salva o valor da linha anterior da pinha em SP
                commands.add("leaw $"+ String.valueOf(index)+" ,%A");
                commands.add("movw %A, %D");
                commands.add("leaw $LCL, %A");    // carega o LCL em %A
                commands.add("movw (%A), %A");    // move o que esta na momoria do LCL para %A
                commands.add("addw %A, %D, %D");  // soma o index com a primira linha do LCL para pegar o valor correto
                commands.add("leaw $R5, %A");     // carregando o temp 0 RAM[5] em %A
                commands.add("movw %D, (%A)");    // salvando o valor de acesso ao local em temp 0
                commands.add("leaw $SP, %A");     // carrega SP em %A
                commands.add("movw (%A), %A");    // valor do SP em %A
                commands.add("movw (%A), %D");    // movendo para D o que está no topo da pilha
                commands.add("leaw $R5, %A");     // acessando temp 0
                commands.add("movw (%A), %A");    // movendo o valor salvo em temp 0 (possição da memoria do local) para %A
                commands.add("movw %D, (%A)");   // movendo o que estava no topo da pilha para local 1

            } else if (segment.equals("argument")) {
                commands.add("leaw $SP, %A");      // carega SP em %A
                commands.add("movw (%A), %D");     // pega valor que SP aponta
                commands.add("decw %D");           // diminui o valor da linha em 1
                commands.add("movw %D, (%A)");     // salva o valor da linha anterior da pinha em SP
                commands.add("leaw $"+ String.valueOf(index)+" ,%A");
                commands.add("movw %A, %D");
                commands.add("leaw $ARG, %A");    // carega o LCL em %A
                commands.add("movw (%A), %A");    // move o que esta na momoria do ARG para %A
                commands.add("addw %A, %D, %D");  // soma o index com a primira linha do LCL para pegar o valor correto
                commands.add("leaw $R5, %A");     // carregando o temp 0 RAM[5] em %A
                commands.add("movw %D, (%A)");    // salvando o valor de acesso ao arg em temp 0
                commands.add("leaw $SP, %A");     // carrega SP em %A
                commands.add("movw (%A), %A");    // valor do SP em %A
                commands.add("movw (%A), %D");    // movendo para D o que está no topo da pilha
                commands.add("leaw $R5, %A");     // acessando temp 0
                commands.add("movw (%A), %A");    // movendo o valor salvo em temp 0 (possição da memoria do arg)para %A
                commands.add("movw %D, (%A)");   // movendo o que estava no topo da pilha para arg

            } else if (segment.equals("this")) {
                commands.add("leaw $SP, %A");      // carega SP em %A
                commands.add("movw (%A), %D");     // pega valor que SP aponta
                commands.add("decw %D");           // diminui o valor da linha em 1
                commands.add("movw %D, (%A)");     // salva o valor da linha anterior da pinha em SP
                commands.add("leaw $"+ String.valueOf(index)+" ,%A");
                commands.add("movw %A, %D");
                commands.add("leaw $THIS, %A"); //carrega THIS em %A
                commands.add("movw (%A), %A");    // move o que esta na momoria do LCL para %A
                commands.add("addw %A, %D, %D");  // soma o index com a primira linha do THIS pegar o valor correto
                commands.add("leaw $R5, %A");     // carregando o temp 0 RAM[5] em %A
                commands.add("movw %D, (%A)");    // salvando o valor de acesso ao this temp0
                commands.add("leaw $SP, %A");     // carrega SP em %A
                commands.add("movw (%A), %A");    // valor do SP em %A
                commands.add("movw (%A), %D");    // movendo para D o que está no topo da pilha
                commands.add("leaw $R5, %A");     // acessando temp 0
                commands.add("movw (%A), %A");    // movendo o valor salvo em temp 0 (possição da memoria do this) para %A
                commands.add("movw %D, (%A)");   // movendo o que estava no topo da pilha para this
            } else if (segment.equals("that")) {
                commands.add("leaw $SP, %A");      // carega SP em %A
                commands.add("movw (%A), %D");     // pega valor que SP aponta
                commands.add("decw %D");           // diminui o valor da linha em 1
                commands.add("movw %D, (%A)");     // salva o valor da linha anterior da pinha em SP
                commands.add("leaw $"+ String.valueOf(index)+" ,%A");
                commands.add("movw %A, %D");
                commands.add("leaw $THAT, %A");   //carega THAT em %A
                commands.add("movw (%A), %A");    // move o que esta na momoria do LCL para %A
                commands.add("addw %A, %D, %D");  // soma o index com a primira linha do LCL para pegar o valor correto
                commands.add("leaw $R5, %A");     // carregando o temp 0 RAM[5] em %A
                commands.add("movw %D, (%A)");    // salvando o valor de acesso ao local em temp 0
                commands.add("leaw $SP, %A");     // carrega SP em %A
                commands.add("movw (%A), %A");    // valor do SP em %A
                commands.add("movw (%A), %D");    // movendo para D o que está no topo da pilha
                commands.add("leaw $R5, %A");     // acessando temp 0
                commands.add("movw (%A), %A");    // movendo o valor salvo em temp 0 (possição da memoria do local) para %A
                commands.add("movw %D, (%A)");   // movendo o que estava no topo da pilha para local 1
            } else if (segment.equals("static")) {
                commands.add("leaw $SP, %A");   // carega SP em %A
                commands.add("movw (%A), %D");  // move valor que SP aponta para %D
                commands.add("decw %D");        // dominui o valor da linha em 1
                commands.add("movw %D, (%A)"); // salva o novo valor da linha em SP
                commands.add("movw (%A), %A"); // move o valor da linha para %A
                commands.add("movw (%A). %D"); //move o valor da linha para %D
                commands.add("leaw $"+ this.filename+"."+ String.valueOf(index)+",%A");
                commands.add("movw %D, (%A)");  // move o valor da linha para o lugar que static aponta
            } else if (segment.equals("temp")) {
                commands.add("leaw $SP,%A");                       //carrega SP em %A
                commands.add("movw (%A),%D");                     // move valor apontado por SP para %D
                commands.add("decw %D");                          // diminui o valor em 1
                commands.add("movw %D,(%A)");                     // salva o valor - 1 para SP
                commands.add("movw (%A),%A");                     // move o valor da linha anterior da pilha para %A
                commands.add("movw (%A),%D");                     // pega o ultimo valor que estava na pilha
                commands.add("leaw $" + String.valueOf(index+5) + ",%A"); // carega o valor do temp em %A
                commands.add("movw %D,(%A)");                     // move o ultimo valor da pilha para o temp 
            } else if (segment.equals("pointer")) {
                if(index==0) {
                    commands.add("leaw $SP,%A");
                    commands.add("movw (%A),%D");
                    commands.add("decw %D");
                    commands.add("movw %D,(%A)");
                    commands.add("movw (%A),%A");
                    commands.add("movw (%A),%D");
                    commands.add("leaw $THIS,%A");
                    commands.add("movw %D,(%A)");
                } else {
                    commands.add("leaw $SP,%A");
                    commands.add("movw (%A),%D");
                    commands.add("decw %D");
                    commands.add("movw %D,(%A)");
                    commands.add("movw (%A),%A");
                    commands.add("movw (%A),%D");
                    commands.add("leaw $THAT,%A");
                    commands.add("movw %D,(%A)");
                }
            }
        } else if (command == Parser.CommandType.C_PUSH) {
            commands.add(String.format("; %d - PUSH %s %d", lineCode++ ,segment, index));

            if (segment.equals("constant")) {
                commands.add("leaw $"+String.valueOf(index)+", %A");  //carrega o valor da constante
                commands.add("movw %A, %D");                          //salva valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw (%A), %A");                        //%A = 256
                commands.add("movw %D, (%A)");                        //mov o valor de %D para a RAM[256]
                commands.add("incw %A");                              // aumenta em 1 o valor de %A/SP
                commands.add("movw %A, %D");                          // salva esse valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw %D, (%A)");                        // carega o novo valor que SP aponta


            } else if (segment.equals("local")) {
                commands.add("leaw $"+String.valueOf(index)+", %A");  //carrega o valor da constante
                commands.add("movw %A, %D");                          //salva valor em %D
                commands.add("leaw $LCL, %A");
                commands.add("movw (%A), %A");                        //pega valor da linha que comeca o primeiro local
                commands.add("addw %D, %A, %A");                      //soma o numero do index com o valor apontado polo LCL
                commands.add("movw (%A), %D");                        // move o valor qu eta no local para %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw (%A), %A");                        //%A = 256
                commands.add("movw %D, (%A)");                        //mov o valor de %D para a RAM[256]
                commands.add("incw %A");                              // aumenta em 1 o valor de %A/SP
                commands.add("movw %A, %D");                          // salva esse valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw %D, (%A)");                        // carega o novo valor que SP aponta



            } else if (segment.equals("argument")) {
                commands.add("leaw $"+String.valueOf(index)+", %A");  //carrega o valor da constante
                commands.add("movw %A, %D");                          //salva valor em %D
                commands.add("leaw $ARG, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %D, %A, %A");                      //soma o numero do index com o valor apontado polo ARG
                commands.add("movw (%A), %D");                        // move o valor qu eta no local para %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw (%A), %A");                        //%A = 256
                commands.add("movw %D, (%A)");                        //mov o valor de %D para a RAM[256]
                commands.add("incw %A");                              // aumenta em 1 o valor de %A/SP
                commands.add("movw %A, %D");                          // salva esse valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw %D, (%A)");                        // carega o novo valor que SP aponta

            } else if (segment.equals("this")) {
                commands.add("leaw $"+String.valueOf(index)+", %A");  //carrega o valor da constante
                commands.add("movw %A, %D");                          //salva valor em %D
                commands.add("leaw $THIS, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %D, %A, %A");                      //soma o numero do index com o valor apontado polo THIS
                commands.add("movw (%A), %D");                        // move o valor qu esta no local para %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw (%A), %A");                        //%A = 256
                commands.add("movw %D, (%A)");                        //mov o valor de %D para a RAM[256]
                commands.add("incw %A");                              // aumenta em 1 o valor de %A/SP
                commands.add("movw %A, %D");                          // salva esse valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw %D, (%A)");                        // carega o novo valor que SP aponta

            } else if (segment.equals("that")) {
                commands.add("leaw $"+String.valueOf(index)+", %A");  //carrega o valor da constante
                commands.add("movw %A, %D");                          //salva valor em %D
                commands.add("leaw $THAT, %A");
                commands.add("movw (%A), %A");
                commands.add("addw %D, %A, %A");                      //soma o numero do THAT com o valor apontado polo LCL
                commands.add("movw (%A), %D");                        // move o valor qu eta no local para %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw (%A), %A");                        //%A = 256
                commands.add("movw %D, (%A)");                        //mov o valor de %D para a RAM[256]
                commands.add("incw %A");                              // aumenta em 1 o valor de %A/SP
                commands.add("movw %A, %D");                          // salva esse valor em %D
                commands.add("leaw $SP, %A");                         //%A = 0
                commands.add("movw %D, (%A)");                        // carega o novo valor que SP aponta

            } else if (segment.equals("static")) {
                commands.add("leaw $"+this.filename+"."+String.valueOf(index)+", %A");  //carrega o label statir no reg %A
                commands.add("movw %A, %D");                                            //salva valor em %D
                commands.add("leaw $SP, %A");                                          //%A = 0
                commands.add("movw (%A), %A");                                         //%A é o valor da memoria
                commands.add("movw %D, (%A)");                                         //move o resultado de %d (static) para o valor da memória apontada pelo SP
                commands.add("leaw $SP, %A");                                          //%A = 0
                commands.add("movw (%A), %D");                                         //pega o valor a pontado por SP e salva em %D
                commands.add("incw %D");                                               //aumenta em 1 o valor de %D
                commands.add("movw %D, (%A)");                                         //carega o novo valor que SP aponta

            } else if (segment.equals("temp")) {
                commands.add("leaw $"+String.valueOf(index + 5)+", %A");                //carrega o numero passado depois da palavra e soma 5 já que temp comeca na RAM[5]
                commands.add("movw %A, %D");                                            //carega o valor da linha da memoria e salva em %D
                commands.add("leaw $SP, %A");                                          //%A = 0
                commands.add("movw (%A), %A");                                         //%A é o valor da memoria
                commands.add("movw %D, (%A)");                                         //move o resultado de %d (static) para o valor da memória apontada pelo SP
                commands.add("leaw $SP, %A");                                          //%A = 0
                commands.add("movw (%A), %D");                                         //pega o valor a pontado por SP e salva em %D
                commands.add("incw %D");                                               //aumenta em 1 o valor de %D
                commands.add("movw %D, (%A)");                                         //carega o novo valor que SP aponta

            } else if (segment.equals("pointer")) {
                if(index==0) {
                    commands.add("leaw $THIS,%A");
                    commands.add("movw (%A),%D");    //move o valor que esta no THIS para o %D
                    commands.add("leaw $SP,%A");     //Carega SP em %A
                    commands.add("movw (%A),%A");    //Pega o nuemero que SP aponta e salva em %A
                    commands.add("movw %D,(%A)");    //move o valor do %D para a momoria apontada pelo SP
                    commands.add("leaw $SP,%A");     //Carega SP em %A
                    commands.add("movw (%A),%D");    //Pega numero que SP aponta e salva em %D
                    commands.add("incw %D");         //Aumenta o valor em 1
                    commands.add("movw %D,(%A)");    //Salva esse valor em SP

                } else {
                    commands.add("leaw $THAT,%A");
                    commands.add("movw (%A),%D");    //move o valor que esta no THAT para o %D
                    commands.add("leaw $SP,%A");     //Carega SP em %A
                    commands.add("movw (%A),%A");    //Pega o nuemero que SP aponta e salva em %A
                    commands.add("movw %D,(%A)");    //move o valor do %D para a momoria apontada pelo SP
                    commands.add("leaw $SP,%A");     //Carega SP em %A
                    commands.add("movw (%A),%D");    //Pega numero que SP aponta e salva em %D
                    commands.add("incw %D");         //Aumenta o valor em 1
                    commands.add("movw %D,(%A)");    //Salva esse valor em SP

                }
            }
        }

        String[] stringArray = new String[ commands.size() ];
        commands.toArray( stringArray );
        write(stringArray);

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para inicializar o processo da VM (bootstrap).
     * Também prepara a chamada para a função Sys.init
     * O código deve ser colocado no início do arquivo de saída.
     */
    public void writeInit(boolean bootstrap, boolean isDir) {

        List<String> commands = new ArrayList<String>();

        if(bootstrap || isDir)
            commands.add( "; Inicialização para VM" );

        if(bootstrap) {
            commands.add("leaw $256,%A");
            commands.add("movw %A,%D");
            commands.add("leaw $SP,%A");
            commands.add("movw %D,(%A)");
        }

        if(isDir){
            commands.add("leaw $Main.main, %A");
            commands.add("jmp");
            commands.add("nop");
        }

        if(bootstrap || isDir) {
            String[] stringArray = new String[commands.size()];
            commands.toArray(stringArray);
            write(stringArray);
        }
    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar o labels (marcadores de jump).
     * @param  label define nome do label (marcador) a ser escrito.
     */
    public void writeLabel(String label) {

        List<String> commands = new ArrayList<String>();
        commands.add( "; Label (marcador)" );

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar as instruções de goto (jumps).
     * Realiza um jump incondicional para o label informado.
     * @param  label define jump a ser realizado para um label (marcador).
     */
    public void writeGoto(String label) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Goto Incondicional", lineCode++));

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para gerar as instruções de goto condicional (jumps condicionais).
     * Realiza um jump condicional para o label informado.
     * @param  label define jump a ser realizado para um label (marcador).
     */
    public void writeIf(String label) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Goto Condicional", lineCode++));

     }

    /**
     * Grava no arquivo de saida as instruções em Assembly para uma chamada de função (Call).
     * @param  functionName nome da função a ser "chamada" pelo call.
     * @param  numArgs número de argumentos a serem passados na função call.
     */
    public void writeCall(String functionName, Integer numArgs) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - chamada de funcao %s", lineCode++, functionName));

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para o retorno de uma sub rotina.
     */
    public void writeReturn() {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Retorno de função", lineCode++));

    }

    /**
     * Grava no arquivo de saida as instruções em Assembly para a declaração de uma função.
     * @param  functionName nome da função a ser criada.
     * @param  numLocals número de argumentos a serem passados na função call.
     */
    public void writeFunction(String functionName, Integer numLocals) {

        List<String> commands = new ArrayList<String>();
        commands.add(String.format("; %d - Declarando função %s", lineCode++, functionName));

    }

    /**
     * Armazena o nome do arquivo vm de origem.
     * Usado para definir os dados estáticos do código (por arquivo).
     * @param file nome do arquivo sendo tratado.
     */
    public void vmfile(String file) {

        int i = file.lastIndexOf(File.separator);
        int j = file.lastIndexOf('.');
        this.filename = file.substring(i+1,j);

    }

    // grava as instruções em Assembly no arquivo de saída
    public void write(String[] stringArray) {
        // gravando comandos no arquivos
        for (String s: stringArray) {
            this.outputFile.println(s);
        }
    }

    // fecha o arquivo de escrita
    public void close() throws IOException {
        this.outputFile.close();
    }

}
