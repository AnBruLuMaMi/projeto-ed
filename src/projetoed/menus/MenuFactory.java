package projetoed.menus;

import projetoed.menus.interfaces.IMenu;

public class MenuFactory {
    public static IMenu create(int opcao) {
        switch (opcao) {
            case 1:
                return new ListaArranjoMenu();
            case 2:
                return new PilhaMenu();
            case 3:
                return new FilaMenu();
            case 4:
                return new ListaNodoMenu();
            case 5:
                return new ArvoreGenericaMenu();
            case 6:
                return new ArvoreBinariaMenu();
            case 7:
                return new FilaPrioridadeMenu();
            case 8:
                return new MapaMenu();
            case 9:
                return new DicionarioMenu();
            case 10:
                return new MapaOrdenadoABBMenu();
            case 11:
                return new MapaOrdenadoAVLMenu();
            case 12:
                return new GrafoMenu();
            default:
                return null;
        }
    }
}
