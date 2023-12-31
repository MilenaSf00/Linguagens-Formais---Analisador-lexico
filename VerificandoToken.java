import Tokens.TokenCondicional;
import Tokens.TokenEscrita;
import Tokens.TokenLeitura;
import Tokens.TokenPalavraReservadas;
import Tokens.TokenRepeticao;
import Tokens.TokenVariaveis;

public class VerificandoToken {
    // Verifica se a palavra é um identificador
    public boolean isIdentificador(String palavra) {
        // Um identificador deve começar com uma letra (maiúscula ou minúscula) ou
        // underline
        if (!Character.isLetter(palavra.charAt(0)) && palavra.charAt(0) != '_') {
            return false;
        }
        // Verifica se os caracteres subsequentes são letras, dígitos ou underline
        for (int i = 1; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return !isVariaveis(palavra) && !isEscrita(palavra) && !isCondicional(palavra)
                && !isRepeticao(palavra);
    }

    // Verifica se o caractere é um número
    public boolean isNumber(char x) {
        return Character.isDigit(x);
    }

    // Verifica se o trecho de código corresponde a um número real
    public boolean isNReal(String codigo) {
        return codigo.matches("\\d+\\.\\d*");
    }

    // Verifica se o trecho de código corresponde a um número inteiro
    public boolean isNInt(String codigo) {
        return codigo.matches("\\d+");
    }

    // Verifica se a palavra é uma string delimitada por aspas simples ou aspas
    // duplas
    public boolean isString(String palavra) {
        return palavra.matches("(['\"])(.*?)\\1");
    }

    public boolean isOperador(String palavra) {
        return palavra.equals("+") || palavra.equals("-") || palavra.equals("*") || palavra.equals("=") ||
                palavra.equals("%") || palavra.equals("&&") || palavra.equals("||") || palavra.equals("!") ||
                palavra.equals(">") || palavra.equals("<") || palavra.equals(">=") || palavra.equals("<=") ||
                palavra.equals("==") || palavra.equals("/");
    }

    // Verifica se o trecho de código corresponde a um comentário
    public boolean isComentario(String codigo) {
        return codigo.startsWith("//") || codigo.startsWith("/*") || codigo.endsWith("*/");
    }

    // Verifica se o trecho de código corresponde à função prompt()
    public boolean isEscrita(String codigo) {
        for (TokenEscrita token : TokenEscrita.values()) {
            if (codigo.equals(token.getValor())) {
                return true;
            }
        }
        return false;
    }

    // Verifica se o trecho de código corresponde à função console.log()
    public boolean isLeitura(String codigo) {
        return codigo.startsWith(TokenLeitura.CONSOLE_LOG.getValor());
    }

    // Verifica se o caractere é um delimitador
    public boolean isDelimitador(char x) {
        return x == '{' || x == '}' || x == '(' || x == ')' ||
                x == '[' || x == ']' || x == ',' || x == ';' ||
                x == '.' || x == ':';
    }

    // Verifica se a palavra é uma estrutura condicional
    public boolean isCondicional(String palavra) {
        try {
            TokenCondicional.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é uma palavra relacionada a variáveis
    public boolean isVariaveis(String palavra) {
        try {
            TokenVariaveis.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é uma estrutura de repetição
    public boolean isRepeticao(String palavra) {
        try {
            TokenRepeticao.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é um valor booleano
    public boolean isBoolean(String palavra) {
        return palavra.equals("true") || palavra.equals("false");
    }

    // Verifica se a palavra é uma palavra reservada
    public boolean isPalavraReservada(String palavra) {
        try {
            TokenPalavraReservadas.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
