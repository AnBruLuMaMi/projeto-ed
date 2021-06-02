package projetoed.estruturas.arvorebinaria.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.arvorebinaria.ArvoreBinaria;
import projetoed.estruturas.arvorebinaria.interfaces.INodoArvoreBinaria;
import projetoed.estruturas.listanodos.interfaces.INodo;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArvoreBinariaTest {
    ArvoreBinaria buildExpression(String expressao) {
        Stack<ArvoreBinaria> pilha = new Stack();

        Pattern pattern = Pattern.compile("[\\d*/+-]", Pattern.CASE_INSENSITIVE);

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            Matcher matcher = pattern.matcher("" + caractere);
            boolean caracterValido = matcher.find();

            if (caracterValido) {
                ArvoreBinaria<Character> arvoreBinaria = new ArvoreBinaria();
                arvoreBinaria.addRoot(caractere);
                pilha.push(arvoreBinaria);
            } else if (caractere == '(') {
                continue;
            } else {
                ArvoreBinaria<Character> arvoreBinaria2 = pilha.pop();
                ArvoreBinaria<Character> arvoreBinaria = pilha.pop();
                ArvoreBinaria<Character> arvoreBinaria1 = pilha.pop();

                arvoreBinaria.attach(arvoreBinaria.root(), arvoreBinaria1, arvoreBinaria2);
                pilha.push(arvoreBinaria);
            }
        }

        return pilha.pop();
    }

    @Test
    void test() {
        INodo<Character> filhoEsquerda, filhoEsquerda2, filhoDireita;
        INodoArvoreBinaria<Character> irmaoFilhoDireita;

        ArvoreBinaria arvore = buildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");

        assertEquals("[-, /, *, +, 3, 1, 3, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());
        assertEquals("-", arvore.root().element().toString());

        assertEquals(true, arvore.hasLeft(arvore.root()));

        filhoEsquerda = arvore.left(arvore.root());
        assertEquals("/", filhoEsquerda.element().toString());

        assertEquals(true, arvore.hasLeft(filhoEsquerda));

        filhoEsquerda2 = arvore.left(filhoEsquerda);
        assertEquals("*", filhoEsquerda2.element().toString());

        assertEquals(true, arvore.hasRight(filhoEsquerda2));
        filhoDireita = arvore.right(filhoEsquerda2);
        assertEquals("3", filhoDireita.element().toString());

        assertEquals(false, arvore.hasRight(filhoDireita));

        arvore.replace(filhoDireita, 10);
        assertEquals("[-, /, *, +, 3, 1, 10, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());

        assertEquals(true, arvore.isExternal(filhoDireita));
        assertEquals(false, arvore.isInternal(filhoDireita));

        assertEquals(false, arvore.isExternal(filhoEsquerda2));
        assertEquals(true, arvore.isInternal(filhoEsquerda2));

        assertEquals(filhoEsquerda, arvore.parent(filhoEsquerda2));

        assertEquals(false, arvore.isEmpty());
        assertEquals(true, arvore.isRoot(arvore.root()));
        assertEquals(false, arvore.isRoot(filhoDireita));

        irmaoFilhoDireita = ((INodoArvoreBinaria)filhoEsquerda2).getLeft();
        assertEquals(irmaoFilhoDireita, arvore.sibling(filhoDireita));

        ArvoreBinaria arvore2 = buildExpression("((2+2)/2)");
        ArvoreBinaria arvore3 = buildExpression("((4+4)/2)");

        arvore.attach(filhoDireita, arvore2, arvore3);
        assertEquals("[-, /, *, +, 3, 1, 10, /, +, 2, 2, 2, /, +, 4, 4, 2, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());

        assertEquals(29, arvore.size());
    }
}
