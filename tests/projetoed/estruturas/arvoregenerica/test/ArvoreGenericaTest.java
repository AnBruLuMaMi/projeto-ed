package projetoed.estruturas.arvoregenerica.test;

import org.junit.jupiter.api.Test;
import projetoed.estruturas.arvoregenerica.Arvore;
import projetoed.estruturas.arvoregenerica.NodoArvore;
import projetoed.estruturas.arvoregenerica.interfaces.INodoArvore;
import projetoed.estruturas.listanodos.ListaNodo;
import projetoed.estruturas.listanodos.interfaces.IListaNodo;
import projetoed.estruturas.listanodos.interfaces.INodo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArvoreGenericaTest {
    class DiscNode {
        private String name;
        private int kbytes;

        public DiscNode(String name, int kbytes) {
            this.name = name;
            this.kbytes = kbytes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getKbytes() {
            return kbytes;
        }

        public void setKbytes(int kbytes) {
            this.kbytes = kbytes;
        }

        @Override
        public String toString() {
            return "'" + getName() + "'";
        }
    }

    private <T> NodoArvore<T> criarFilho(NodoArvore<T> nodo, T elemento) {
        IListaNodo<INodo<T>> filhos;

        NodoArvore<T> aux;
        filhos = nodo.getChildren();

        aux = new NodoArvore<T>(elemento, nodo, new ListaNodo());

        filhos.addLast(aux);

        return aux;
    }

    private Arvore<DiscNode> criarArvoreDisco() {
        Arvore<DiscNode> arvore = new Arvore<>();
        NodoArvore<DiscNode> raiz, v, m, i, u, z, y, w;


        arvore.addRoot(new DiscNode("/usuário/rt/cursos/", 1));

        raiz = (NodoArvore<DiscNode>)arvore.root();

        raiz.setChildren(new ListaNodo());

        // Filhos da raiz: /usuário/rt/cursos/
        v = criarFilho(raiz, new DiscNode("cs016/", 2));
        m = criarFilho(raiz, new DiscNode("cs252/", 1));

        // Filhos de cs016/
        criarFilho(v, new DiscNode("notas", 8));
        i = criarFilho(v, new DiscNode("temas/", 1));
        u = criarFilho(v, new DiscNode("programas/", 1));

        // Filhos de temas/
        criarFilho(i, new DiscNode("hw1", 3));
        criarFilho(i, new DiscNode("hw2", 2));
        criarFilho(i, new DiscNode("hw3", 4));

        // Filhos de programas/
        criarFilho(u, new DiscNode("pr1", 57));
        criarFilho(u, new DiscNode("pr2", 97));
        criarFilho(u, new DiscNode("pr3", 74));

        // Filhos de cs252/
        z = criarFilho(m, new DiscNode("projetos/", 1));
        criarFilho(m, new DiscNode("notas", 3));

        // Filhos de projetos/
        y = criarFilho(z, new DiscNode("trabalhos/", 1));
        w = criarFilho(z, new DiscNode("demos/", 1));

        // Filhos de trabalhos/
        criarFilho(y, new DiscNode("compre baixo", 26));
        criarFilho(y, new DiscNode("venda alto", 55));

        // Filhos de demos//
        criarFilho(w, new DiscNode("mercado", 4786));

        return arvore;
    }

    @Test
    void test() {
        INodoArvore<DiscNode> raiz;
        INodo<INodo<DiscNode>> nodeCs016, nodeCs252;
        IListaNodo<INodo<DiscNode>> filhos;

        Arvore<DiscNode> arvore = criarArvoreDisco();
        assertFalse(arvore.isEmpty());

        assertEquals("['/usuário/rt/cursos/', 'cs016/', 'notas', 'temas/', 'hw1', 'hw2', 'hw3', 'programas/', 'pr1', 'pr2', 'pr3', 'cs252/', 'projetos/', 'trabalhos/', 'compre baixo', 'venda alto', 'demos/', 'mercado', 'notas']",
                arvore.toString());

        raiz = arvore.root();
        filhos = raiz.getChildren();
        nodeCs016 = filhos.first();

        assertEquals("'cs016/'", nodeCs016.element().toString());
        assertTrue(arvore.isInternal(nodeCs016.element()));
        assertEquals(raiz, arvore.parent(nodeCs016.element()));

        nodeCs252 = filhos.next(nodeCs016);
        assertEquals("'cs252/'", nodeCs252.element().toString());

        INodo<DiscNode> nodoNotas = ((INodoArvore<DiscNode>)nodeCs016.element()).getChildren().first().element();
        assertTrue(arvore.isExternal(nodoNotas));

        arvore.replace(nodeCs016.element(), new DiscNode("cs200/", 1));

        assertEquals("['/usuário/rt/cursos/', 'cs200/', 'notas', 'temas/', 'hw1', 'hw2', 'hw3', 'programas/', 'pr1', 'pr2', 'pr3', 'cs252/', 'projetos/', 'trabalhos/', 'compre baixo', 'venda alto', 'demos/', 'mercado', 'notas']",
                arvore.toString());

        assertTrue(arvore.isRoot(raiz));

        arvore.swapElements(nodeCs016.element(), nodeCs252.element());

        assertEquals("['/usuário/rt/cursos/', 'cs252/', 'notas', 'temas/', 'hw1', 'hw2', 'hw3', 'programas/', 'pr1', 'pr2', 'pr3', 'cs200/', 'projetos/', 'trabalhos/', 'compre baixo', 'venda alto', 'demos/', 'mercado', 'notas']",
                arvore.toString());
    }
}
