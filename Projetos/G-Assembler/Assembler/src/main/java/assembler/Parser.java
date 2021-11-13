/**
 * Curso: Elementos de Sistemas
 * Arquivo: Parser.java
 */

package assembler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Encapsula o código de leitura. Carrega as instruções na linguagem assembly,
 * analisa, e oferece acesso as partes da instrução  (campos e símbolos).
 * Além disso, remove todos os espaços em branco e comentários.
 */
public class Parser {

    private final BufferedReader fileReader;
    public String inputFile;                // arquivo de leitura
    public int lineNumber = 0;                // linha atual do arquivo (nao do codigo gerado)
    public String currentCommand = "";      // comando atual
    public String currentLine;                // linha de codigo atual


    /**
     * Enumerator para os tipos de comandos do Assembler.
     */
    public enum CommandType {
        A_COMMAND,      // comandos LEA, que armazenam no registrador A
        C_COMMAND,      // comandos de calculos
        L_COMMAND       // comandos de Label (símbolos)
    }

    /**
     * Abre o arquivo de entrada NASM e se prepara para analisá-lo.
     *
     * @param file arquivo NASM que será feito o parser.
     */
    public Parser(String file) throws FileNotFoundException {
        this.inputFile = file;
        this.fileReader = new BufferedReader(new FileReader(file));
        this.lineNumber = 0;
    }

    // fecha o arquivo de leitura
    public void close() throws IOException {
        fileReader.close();
    }

    /**
     * Carrega uma instrução e avança seu apontador interno para o próxima
     * linha do arquivo de entrada. Caso não haja mais linhas no arquivo de
     * entrada o método retorna "Falso", senão retorna "Verdadeiro".
     *
     * @return Verdadeiro se ainda há instruções, Falso se as instruções terminaram.
     */
    public Boolean advance() {
        /* ja esta pronto */
        while (true) {
            try {
                currentLine = fileReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lineNumber++;
            if (currentLine == null)
                return false;  // caso não haja mais comandos
            currentCommand = currentLine.replaceAll(";.*$", "").trim();
            if (currentCommand.equals(""))
                continue;
            return true;   // caso um comando seja encontrado
        }
    }

    /**
     * Retorna o comando "intrução" atual (sem o avanço)
     *
     * @return a instrução atual para ser analilisada
     */
    public String command() {
        /* ja esta pronto */
        return currentCommand;
    }

    /**
     * Retorna o tipo da instrução passada no argumento:
     * A_COMMAND para leaw, por exemplo leaw $1,%A
     * L_COMMAND para labels, por exemplo Xyz: , onde Xyz é um símbolo.
     * C_COMMAND para todos os outros comandos
     *
     * @param command instrução a ser analisada.
     * @return o tipo da instrução.
     */
    public CommandType commandType(String command) {
        if (command.contains("leaw")) {
            return assembler.Parser.CommandType.A_COMMAND;
        } else if (command.contains(":")) {
            return assembler.Parser.CommandType.L_COMMAND;
        } else {
            return assembler.Parser.CommandType.C_COMMAND;
        }
    }

    /**
     * Retorna o símbolo ou valor numérico da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é A_COMMAND.
     *
     * @param command instrução a ser analisada.
     * @return somente o símbolo ou o valor número da instrução.
     */
    public String symbol(String command) {
        String simbolo = ""; // variavel que guarda o símbolo para colocar no return
        // se o command (comando usado para entrar nessa função ser um comando
        // que contempla o tipo A, ele entra no if
        if (commandType(command) == assembler.Parser.CommandType.A_COMMAND) {
            boolean comeca_symbol = false;
            for (int i = 0; i < command.length(); i++) { // percorre toda string (letra por letra)
                if (command.charAt(i) == '$' && comeca_symbol == false) {
                    comeca_symbol = true;
                } else if (comeca_symbol && command.charAt(i) == ',') { // só pega o $
                    comeca_symbol = false;
                    break;
                } else if (comeca_symbol) {
                    simbolo += command.charAt(i);
                }
            }
        }
        return simbolo;
    }

    /**
     * Retorna o símbolo da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é L_COMMAND.
     *
     * @param command instrução a ser analisada.
     * @return o símbolo da instrução (sem os dois pontos).
     */
    public String label(String command) {
        String simbolo = "";

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) != ':') {
                simbolo += command.charAt(i);
            } else {
                return simbolo;
            }
        }
        return simbolo;
    }

    /**
     * Separa os mnemônicos da instrução fornecida em tokens em um vetor de Strings.
     * Deve ser chamado somente quando CommandType () é C_COMMAND.
     *
     * @param command instrução a ser analisada.
     * @return um vetor de string contendo os tokens da instrução (as partes do comando).
     * <p>
     * linha 164 -> remove todos os espaços do início e do fim da string
     * linha 165 -> troca todas as vírgulas com espaços por somente vírgulas
     * linha 166 -> troca todos os espaços por vírgulas
     * linha 167 -> separa em diferentes strings dentro da variável array
     */
    public String[] instruction(String command) {
        command = command.trim();
        command = command.replace(", ", ",");
        command = command.replace(" ", ",");
        String[] array = command.split(",");
        return array;
    }
}