/**
 * Classe responsável por dar a representação da coordenada (x, y) em um plano cartesiano
 * 
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */

public class Coordinate implements Cloneable{
    private int linhas; 
    private int colunas; 

    /**
     * Cria Coordinate a partir dos parametros linhas e colunas (x, y)
     * @param linhas parametro do tipo int que é referente a quantidade de linhas (x)
     * @param colunas parametro do tipo int que é referente a quantidade de colunas (y)
     * @throws Exception caso as linhas e colunas passadas forem inválidas
     */
    public Coordinate(int linhas, int colunas) throws Exception{
        if(linhas<0 || colunas< 0) throw new Exception("Valores inválidos");
        this.linhas = linhas;
        this.colunas = colunas;        
    }

    /**
     * Método responsável por nos dar a coordenada de colunas (y)
     * @return coordenada de y
     */
    public int getColuna() { return colunas; }

    /**
     * Método responsável por nos dar a coordenada de linhas (x)
     * @return coordenada de x
     */
    public int getLinha() { return linhas; }

    /**
     * Método obrigatório toString que retorna uma representação em string de linhas e colunas
     * @return uma string que representa linhas e colunas
     */
    @Override
    public String toString(){ return "("+colunas+","+linhas+")";}
    
    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode(){
        int ret = 8;
        ret = ret *7 + Integer.valueOf(this.linhas).hashCode();
        ret = ret *7 + Integer.valueOf(this.colunas).hashCode();
        if (ret < 0) ret =- ret;
        return ret;
    }

    /**
     * Método obrigatório equals para verificar se este objetos são iguais aos outros
     * @param obj o objeto a ser comparado
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Coordinate c = (Coordinate)obj;
        if(this.colunas != c.colunas) return false;
        if(this.linhas != c.linhas) return false;
        return true;
    }

    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe Coordinate
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
     */
    public Coordinate (Coordinate modelo) throws Exception{
        if (modelo == null) throw new Exception("Coordenada vazia");
        this.colunas = modelo.colunas;
        this.linhas = modelo.linhas;
    }

    /**
     * Método clone que cria e retorna a copia de Coordinate
     * @return um clone da instancia
     */
    @Override
    public Object clone(){
        Coordinate ret = null;
        try{
            ret = new Coordinate(this);
        }catch(Exception e){}
        return ret;
    }
}