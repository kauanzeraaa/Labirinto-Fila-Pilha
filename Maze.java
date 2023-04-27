/**
 * A classe labirinto é responsável por administrar o projeto, ela faz as verificações necessárias de entrada/saída,
 * caracteres inválidos e espaços em branco nas bordas do labirinto.txt.
 * É responsável tambem pela ação de caminhar pelo labirinto, que abrange a questões progressivas e regressivas
 * 
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */

public class Maze implements Cloneable{
    Character[][] space;
    Stack<Coordinate> caminho;
    Stack<Queue<Coordinate>> possibilidades;
    Queue<Coordinate> fila = new Queue<Coordinate>(3);
    Coordinate entrada;
    Coordinate saida;
    Coordinate atual;
    int linhas;
    int colunas;
    
    /**
     * Cria Labirinto a partir dos parametros linhas, colunas e spaceParameters.
     * 
     * @param linhas parametro do tipo int que é referente a quantidade de linhas da Matriz (x)
     * @param colunas parametro do tipo int que é referente a quantidade de colunas da Matriz (y)
     * @param spaceParameters é a matriz passada do arquivo
     * @throws Exception caso o indice tiver um valor invalido e a Matriz passada for nula
     */
    public Maze(int linhas, int colunas, Character[][] spaceParameters) throws Exception{
        if(linhas <0 || colunas <0) throw new Exception("índice inválido");
        if(linhas != spaceParameters.length || colunas != spaceParameters[0].length) throw new Exception("Matriz tem o tamanho especificado errado");
        //if(spaceParameters == null) throw new Exception("Labirinto inexistente");
        this.linhas = linhas;
        this.colunas = colunas;
        this.space = spaceParameters;
        this.caminho = new Stack<Coordinate>(colunas*linhas);
        this.possibilidades = new Stack<Queue<Coordinate>>(colunas*linhas);        
        verificarCaracteres();
        verificarBorda();
        verificarEntrada();
        verificarSaida();
    }

    /**
     * Método necessário para verificar caracteres contidos no labirinto.txt
     * @throws Exception caso houver caracteres não válidos, diferentes de E, S, #
     */
    private void verificarCaracteres() throws Exception{

        for(int l = 0; l<linhas-1; l++){
            for(int c = 0; c<colunas-1; c++){
                if("ES# ".indexOf(this.space[l][c]) == -1) {
                    throw new Exception("Caracteres inválidos");
                }                
            }        
        }
    }


    /**
     * Método necessário para verificar entradas dentro do labirinto.txt
     * @return entrada caso não passe por nenhuma exceção, deve retornar para se utilizar no método caminhar
     * @throws Exception caso não exista entrada, caso exista múltiplas entradas e caso a entrada esteja nos
     * vértices e no interior dos labirintos
     */
    private Coordinate verificarEntrada() throws Exception{        
        if(space[0][0] == 'E' || space[linhas-1][0] == 'E' || space[0][colunas-1] == 'E' || space[linhas-1][colunas-1] == 'E') throw new Exception("Entrada nos vertices");        
        
        for(int l = 1; l<space.length-1; l++){            
            if(space[l][0] == 'E'){
                if(entrada == null)entrada = new Coordinate(l,0);
                else throw new Exception("Multiplas entradas");
            }
            if(space[l][colunas-1] == 'E'){
                if(entrada == null) entrada = new Coordinate(l, colunas-1);
                else throw new Exception("Multiplas entradas");
            }
        }
        
        for(int c = 1; c<space[0].length-1; c++){
            if(space[0][c] == 'E'){
                if(entrada == null)entrada = new Coordinate(0,c);
                else throw new Exception("Multiplas entradas");
            }
            if(space[linhas-1][c] == 'E'){
                if(entrada == null)entrada = new Coordinate(linhas-1,c);
                else throw new Exception("Multiplas entradas");
            }
        }
        
        for(int l=1; l<space.length-2; l++){
            for(int c=1; c<space.length-2; c++){
                if(space[l][c] == 'E') throw new Exception("Entrada dentro do labirinto");
            }
        }

        if(entrada == null) throw new Exception("Sem entrada");
        return entrada;
    }

    /**
     * Método necessário para verificar saida dentro do labirinto.txt
     * @return saida caso não passe por nenhuma exceção, deve retornar para se utilizar no método caminhar
     * @throws Exception caso não exista saida, caso exista múltiplas saidas e caso a saída esteja nos
     * vértices e no interior dos labirintos
     */
    private Coordinate verificarSaida() throws Exception{        
        
        if(space[0][0] == 'S' || space[linhas-1][0] == 'S' || space[0][colunas-1] == 'S' || space[linhas-1][colunas-1] == 'S') throw new Exception("Saída nos vertices");       

        for (int l = 0; l<space.length-1; l++) {            
            if(space[l][0] == 'S'){
                if(saida == null) saida = new Coordinate(l,0);
                else throw new Exception("Multiplas saídas");
            }
            if(space[l][colunas-1] == 'S'){
                if(saida == null) saida = new Coordinate(l,colunas-1);
                else throw new Exception("Multiplas saídas");
            }
        }
        for (int c = 1; c<space[0].length; c++) {
            if(space[0][c] == 'S'){
                if(saida == null) saida = new Coordinate(0, c);
                else throw new Exception("Múltiplas saídas");
            }
            if(space[linhas-1][c] == 'S'){
                if(saida == null) saida = new Coordinate(linhas-1, c);
                else throw new Exception("Múltiplas saídas");
            }
        }    
        
        for(int l=1; l<space.length-2; l++){
            for(int c=1; c<space.length-2; c++){
                if(space[l][c] == 'S') throw new Exception("Saída dentro do labirinto");
            }
        }

        if(saida == null)  throw new Exception("Sem saída");
        return saida;
    }    

    /**
     * Método necessário para verificar se existe espaço em branco nas bordas do labirinto.txt
     * @return true caso o labirinto.txt não passe pela exceção, deve retornar para se utilizar no método caminhar
     * @throws Exception caso o labirinto tenha espaço em branco nas bordas, além da coordenada especifica do local
     */
    private boolean verificarBorda() throws Exception{
        int a = colunas-1;
        int b = linhas-1;
        for(int l = 0; l<space.length-1; l++) {
            for (int c = 0; c<space.length-1; c++) {
                if(space[l][0] == ' ') throw new Exception("Espaço em branco em: "+l+" X "+0);
                if(space[l][colunas-1] == ' ') throw new Exception("Espaço em branco em: "+l+" X "+a);
                if(space[0][c++] == ' ') throw new Exception("Espaço em branco em: "+0+" X "+c);
                if(space[linhas-1][c++] == ' ') throw new Exception("Espaço em branco em: "+b+" X "+c);
            }            
        }
        return true;
    }

    /**
     * Método responsável para caminhar no labirinto iniciando na coordenada de entrada, e achando as posições adjacentes com espaços em branco para avançar.
     * Caso haja mais uma possiblidade de caminho em uma bifurcação, ele escolherá um caminho para seguir e o outro será
     * guardado em uma possiblidade. Caso esse caminho escolhido coloque em um ponto sem saída, ele retrocederá até o ponto
     * que existe a bifurcação e seguirá com outro caminho, fazendo esse processo até encontrar a saída
     * @return inverso, que é o caminho percorrido até a saída com asteriscos
     * @throws Exception herdado de retroceder (caso o programa entre em modo regressivo e não consiga mais voltar ao modo progressivo,
     * esgotando todo o conteúdo de possibilidades e caminho, assim informando que o labirinto não tem solução)
     */
    public Stack<Coordinate> caminhar() throws Exception{
        
        Coordinate adjacenteCima;
        Coordinate adjacenteBaixo;
        Coordinate adjacenteEsquerda;
        Coordinate adjacenteDireita;
        Stack<Coordinate> inverso = new Stack<Coordinate>(colunas*linhas);
        
        for(;;){
            if(atual == null) atual =  new Coordinate(entrada.getLinha(), entrada.getColuna());  
            try{
                adjacenteCima = new Coordinate(atual.getLinha()-1,atual.getColuna());
            }catch(Exception e){
                adjacenteCima = null;
            }
            try{
                adjacenteBaixo = new Coordinate(atual.getLinha()+1, atual.getColuna());
            }catch(Exception e){
                adjacenteBaixo = null;
            }
            try{
                adjacenteEsquerda = new Coordinate(atual.getLinha(), atual.getColuna()-1);
            }catch(Exception e){
                adjacenteEsquerda = null;
            }
            try{
                adjacenteDireita = new Coordinate(atual.getLinha(), atual.getColuna()+1);
            }catch(Exception e){
                adjacenteDireita = null;    
            }   
            
            if(adjacenteCima != null && (space[adjacenteCima.getLinha()][adjacenteCima.getColuna()] == ' '|| space[adjacenteCima.getLinha()][adjacenteCima.getColuna()] == 'S' 
             && !caminho.recupereUmItem().equals(adjacenteCima))){
                fila.guardeUmItem(adjacenteCima);
            }
            if(adjacenteBaixo != null && (space[adjacenteBaixo.getLinha()][adjacenteBaixo.getColuna()] == ' ' || space[adjacenteBaixo.getLinha()][adjacenteBaixo.getColuna()] == 'S' 
             && !caminho.recupereUmItem().equals(adjacenteBaixo))){
                fila.guardeUmItem(adjacenteBaixo);
            }
            if(adjacenteEsquerda != null && (space[adjacenteEsquerda.getLinha()][adjacenteEsquerda.getColuna()] == ' ' || space[adjacenteEsquerda.getLinha()][adjacenteEsquerda.getColuna()] == 'S' 
             && !caminho.recupereUmItem().equals(adjacenteEsquerda))){
                fila.guardeUmItem(adjacenteEsquerda);
            }
            if(adjacenteDireita != null && (space[adjacenteDireita.getLinha()][adjacenteDireita.getColuna()] == ' ' || space[adjacenteDireita.getLinha()][adjacenteDireita.getColuna()] == 'S' 
             && !caminho.recupereUmItem().equals(adjacenteDireita))){
                fila.guardeUmItem(adjacenteDireita);
            }
           
            if(fila.isEmpty()){
               try{
                    fila = retroceder();                   
               }catch(Exception e){
                    e.getMessage();
                    break;
               }               
            }
           
            else{
                atual = (Coordinate)fila.removaUmItem();               
        
                if(space[atual.getLinha()][atual.getColuna()] == 'S'){
                    
                    for (Character[] characters : space) {
                        for (Character characters2 : characters) {
                            System.out.print(characters2);
                        }
                        System.out.println();
                    }
                    System.out.println("\n");
                   
                    break;
                }
                
                space[atual.getLinha()][atual.getColuna()] = '*';
                
                caminho.guardeUmItem(atual);               
                
                possibilidades.guardeUmItem(fila);    
            
                if(!fila.isEmpty())while(!fila.isEmpty()) fila.removaUmItem();

            }
        }
        while(!caminho.isEmpty()){
            inverso.guardeUmItem(caminho.removaUmItem());
        }
        return inverso;    
    }

    /**
     * Método necessario para entrar no modo regressivo do caminhar, basicamente é responsável por fazer
     * exatamente o contrário de caminhar
     * @return fila após passar pelas verificações necessárias de retroceder
     * @throws Exception caso o programa entre em modo regressivo e não consiga mais voltar ao modo progressivo,
     * esgotando todo o conteúdo de possibilidades e caminho, assim informando que o labirinto não tem solução
     */
    private Queue<Coordinate> retroceder() throws Exception{        
        if(caminho.isEmpty()) throw new Exception("não há solução");
        atual = caminho.removaUmItem();        
        // space[atual.getLinha()][atual.getColuna()] = ' ';
        fila = possibilidades.removaUmItem();        
        return fila;        
    } 

    /**
     * Obtém o caminho andado de labirinto
     * @return o caminho utilizado no método caminhar
     */
    public Stack<Coordinate> getCaminho(){return this.caminho;}

    /**
     * Retorna as possibilidades de caminho do labirinto
     * @return as possibilidades
     */
    public Stack<Queue<Coordinate>> getPossibilidades(){return this.possibilidades;}

    /**
     * Obtém a fila de coordenadas usadas no labirinto
     * @return fila de coordenadas
     */
    public Queue<Coordinate> getFila(){return this.fila;}
    
    /**
     * Obtém a entrada do labirinto
     * @return entrada do labirinto
     */
    public Coordinate getEntrada(){return this.entrada;}

    /**
     * Obtém a saida do labirinto
     * @return a saida do labirinto
     */
    public Coordinate getSaida(){return this.saida;}

    /**
     * Obtém a posição atual do labirinto
     * @return a posição atual do labirinto
     */
    public Coordinate getAtual(){return this.atual;}

    /**
     * Obtém as linhas do labirinto (x)
     * @return as linhas do labirinto (x)
     */
    public int getLinhas(){return this.linhas;}

    /**
     * Obtém as colunas do labirinto (y)
     * @return as colunas do labirinto (y)
     */
    public int getColunas(){return this.colunas;}
    
    /**
     * Método get que em especifico é responsável por dizer qual a capacidade do labirinto,
     * linhas*colunas
     * @return texto dizendo linhas x colunas
     */
    public String getLenghtMaze(){return linhas+"x"+colunas;}

    /**
     * Método obrigatório toString que retorna uma representação em string de space
     * @return uma string que representa space
     */
    @Override
    public String toString(){return ""+space;}
    
    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode(){
        int ret = 8;
        ret = ret *23 + this.space.hashCode();
        ret = ret *23 + this.caminho.hashCode();
        ret = ret *23 + this.fila.hashCode();
        ret = ret *23 + this.entrada.hashCode();
        ret = ret *23 + this.saida.hashCode();
        ret = ret *23 + this.atual.hashCode();
        ret = ret *23 + Integer.valueOf(this.linhas).hashCode();
        ret = ret *23 + Integer.valueOf(this.colunas).hashCode();
        if(ret <0) ret = -ret;
        return ret;
    }

    /**
     * Método obrigatório equals para verificar se estes objetos são iguais aos outros
     * @param obj o objeto a ser comparado
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj){

        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Maze m = (Maze)obj;
        if(!this.space.equals(m.space)) return false;
        if(!this.caminho.equals(m.caminho)) return false;
        if(!this.possibilidades.equals(m.possibilidades)) return false;
        if(!this.fila.equals(m.fila)) return false;
        if(!this.entrada.equals(m.entrada)) return false;
        if(!this.saida.equals(m.saida)) return false;
        if(!this.atual.equals(m.atual)) return false;
        if(this.linhas != m.linhas) return false;
        if(this.colunas != m.colunas) return false;
        return true;
    }

    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe Labirinto
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
     */
    public Maze (Maze modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");
        this.space = modelo.space;
        this.caminho = modelo.caminho;
        this.possibilidades = modelo.possibilidades;
        this.fila = modelo.fila;
        this.entrada = modelo.entrada;
        this.saida = modelo.saida;
        this.atual = modelo.atual;
        this.linhas = modelo.linhas;
        this.colunas = modelo.colunas;
    }

    /**
     * Método clone que cria e retorna a copia de Labirinto
     * @return um clone da instancia
     */
    @Override
    public Object clone(){
        Maze ret = null;
        try {
            ret = new Maze(this);
        } catch (Exception e) {}
        return ret;
    }
}