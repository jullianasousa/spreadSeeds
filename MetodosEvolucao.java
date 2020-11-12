/**
 * Esta classe permite implementar a evolucao da classe MetodosEvolucao
 * 
 * @author Grupo 11 <br />
 *         Daniela Goncalves - 53546 <br />
 *         Julliana Sousa - 53927 <br />
 *         Tomas Neves - 54935
 * @since Outubro de 2020
 * 
 */
public class MetodosEvolucao {

    /**
     * Provocar um passo do processo de espalhamento de sementes num dado terreno,
     * com determinadas direcoes e forca do vento.
     * 
     * @param terreno      O terreno onde vai ser aplicado o passo de espalhamento
     * @param ventoLimites Tabela para calcular quantos vizinhos sao afetados com o
     *                     vento
     * @param ventoDir     A direcao do vento
     * @param ventoForca   A forca do vento
     * @requires terreno != null &amp;&amp; ventoLimites != null &amp;&amp; ventoDir
     *           != null &amp;&amp; ventoDir in {"N","S","E","O"} &amp;&amp;
     *           ventoForca >= 0
     */
    static void evolucao(char[][] terreno, int[] ventoLimites, String ventoDir, int ventoForca) {

        char[][] copia = new char[terreno.length][terreno[0].length];
        criaCopia(copia, terreno);

        int nAfetados = obterVizinhos(ventoLimites, ventoForca);

        for (int i = 0; i < copia.length; i++) {
            for (int j = 0; j < copia[i].length; j++) {
                if (copia[i][j] == '*') {
                    boolean fertil = true;
                    switch (ventoDir) {
                        case "N":
                            origemNorte(copia, terreno, i, j, fertil, nAfetados);
                            break;
                        case "S":
                            origemSul(copia, terreno, i, j, fertil, nAfetados);
                            break;
                        case "O":
                            origemOeste(copia, terreno, i, j, fertil, nAfetados);
                            break;
                        case "L":
                            origemLeste(copia, terreno, i, j, fertil, nAfetados);
                            break;
                    }
                }
            }
        }

    }

    /**
     * Cria a copia de uma matriz
     * 
     * @param copia  Copia da matriz
     * @param matriz Matriz que será copiada
     * @requires copia != null &amp;&amp; terreno != null
     */
    public static void criaCopia(char[][] copia, char[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                copia[i][j] = matriz[i][j];
            }
        }
    }

    /**
     * Aplica o espalhamento no terreno do vento com origem Norte
     * 
     * @param copia     Copia do terreno onde vai ser aplicado o espalhamento
     * @param terreno   Terreno onde vai ser aplicado o espalhamento
     * @param i         A linha em que se verificar se eh fertil
     * @param j         A coluna em que se verificar se eh fertil
     * @param fertil    Fertilidade do local do terreno onde vai ser aplicado o
     *                  espalhamento
     * @param nAfetados Numero de vizinhos afetados
     * @requires copia != null &amp;&amp; terreno != null
     */
    public static void origemNorte(char[][] copia, char[][] terreno, int i, int j, boolean fertil, int nAfetados) {

        for (int k = i + 1; (k <= copia.length - 1) && (k <= i + nAfetados) && fertil; k++) {
            fertil = recebeSemente(copia, terreno, k, j, fertil);
        }
    }

    /**
     * Aplica o espalhamento no terreno do vento com origem Sul
     * 
     * @param copia     Copia do terreno onde vai ser aplicado o espalhamento
     * @param terreno   Terreno onde vai ser aplicado o espalhamento
     * @param i         A linha em que se verificar se eh fertil
     * @param j         A coluna em que se verificar se eh fertil
     * @param fertil    Fertilidade do local do terreno onde vai ser aplicado o
     *                  espalhamento
     * @param nAfetados Numero de vizinhos afetados
     * @requires copia != null &amp;&amp; terreno != null
     */
    public static void origemSul(char[][] copia, char[][] terreno, int i, int j, boolean fertil, int nAfetados) {

        for (int k = i - 1; (k >= 0) && (k >= i - nAfetados) && fertil; k--) {
            fertil = recebeSemente(copia, terreno, k, j, fertil);
        }
    }

    /**
     * Aplica o espalhamento no terreno do vento com origem Oeste
     * 
     * @param copia     Copia do terreno onde vai ser aplicado o espalhamento
     * @param terreno   Terreno onde vai ser aplicado o espalhamento
     * @param i         A linha em que se verificar se eh fertil
     * @param j         A coluna em que se verificar se eh fertil
     * @param fertil    Fertilidade do local do terreno onde vai ser aplicado o
     *                  espalhamento
     * @param nAfetados Numero de vizinhos afetados
     * @requires copia != null &amp;&amp; terreno != null
     */
    public static void origemOeste(char[][] copia, char[][] terreno, int i, int j, boolean fertil, int nAfetados) {

        for (int k = j + 1; (k <= copia[i].length - 1) && (k <= j + nAfetados) && fertil; k++) {
            fertil = recebeSemente(copia, terreno, i, k, fertil);
        }
    }

    /**
     * Aplica o espalhamento no terreno do vento com origem Leste
     * 
     * @param copia     Copia do terreno onde vai ser aplicado o espalhamento
     * @param terreno   Terreno onde vai ser aplicado o espalhamento
     * @param i         A linha em que se verificar se eh fertil
     * @param j         A coluna em que se verificar se eh fertil
     * @param fertil    Fertilidade do local do terreno onde vai ser aplicado o
     *                  espalhamento
     * @param nAfetados Numero de vizinhos afetados
     * @requires copia != null &amp;&amp; terreno != null
     */
    public static void origemLeste(char[][] copia, char[][] terreno, int i, int j, boolean fertil, int nAfetados) {

        for (int k = j - 1; (k >= 0) && (k >= j - nAfetados) && fertil; k--) {
            fertil = recebeSemente(copia, terreno, i, k, fertil);
        }
    }

    /**
     * Acrescenta uma semente no local caso este seja fertil
     * 
     * @param copia   Copia do terreno onde vai ser aplicado o espalhamento
     * @param terreno Terreno onde vai ser aplicado o espalhamento
     * @param i       A linha em que se verifica se eh fertil
     * @param j       A coluna em que se verifica se eh fertil
     * @param fertil  Fertilidade do local do terreno onde vai ser aplicado o
     *                espalhamento
     * @requires copia != null &amp;&amp; terreno != null
     * @return True se o local do terreno for fertil para receber uma semente e
     *         False caso contrario
     */
    public static boolean recebeSemente(char[][] copia, char[][] terreno, int i, int j, boolean fertil) {

        if (copia[i][j] == 'x') {
            fertil = false; // encontrou obstaculo
        } else {
            if (copia[i][j] == '.') {
                terreno[i][j] = '*'; // substitui e coloca uma semente no terreno fertil
            }
        }
        return fertil;
    }

    /**
     * Calcula o numero de vizinhos afetados mediante a combinacao de valores da
     * forca do vento com a lista de limites
     * 
     * @param ventoLimites Tabela para calcular quantos vizinhos sao afetados
     * @param ventoForca   A forca do vento
     * @requires ventoLimites != null &amp;&amp; ventoForca >= 0
     * @return numero de "vizinhos" totais afetados pelo vento
     */
    public static int obterVizinhos(int[] ventoLimites, int ventoForca) {

        int vizinhos = 0; // se ventoForca <= ventoLimites[0]
        if (ventoForca > ventoLimites[ventoLimites.length - 1]) {
            vizinhos = ventoLimites.length; // devolve o indice do último elemento + 1
        } else {
            for (int i = 1; i < ventoLimites.length; i++) {
                if (ventoForca > ventoLimites[i - 1] && ventoForca <= ventoLimites[i]) {
                    vizinhos = i;
                }
            }
        }
        return vizinhos;
    }

}
