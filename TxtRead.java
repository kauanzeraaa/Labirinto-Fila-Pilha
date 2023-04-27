/** A classe TxtRead é responsável por ler arquivos de texto de uma rota especificada.
 * 
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */

import java.io.*;

public class TxtRead implements Cloneable{   
    String route;
    BufferedReader in;
   
    /**
     * Cria txtRead a partir do parametro route que é o responsável por nos dar o labirinto
     * @param route que é a rota do arquivo
     * @throws Exception caso a rota dada for nula
     */
    public TxtRead(String route)throws Exception{
        if(route == null) throw new Exception("Rota não encontrada");
        this.route = route;
        in = new BufferedReader(new FileReader(route));
    }
    
    /**
     * Método responsável por ler uma linhas do labirinto
     * @return o método readline para ler a linha
     * @throws Exception herdado de readLine()
     */
    public String lerUmaLinha() throws Exception{
        return in.readLine();
    }

    /**
     * Obtém a rota do arquivo
     * @return a rota do arquivo
     */
    public String getRoute(){ return route;}
   
    /**
     * Método obrigatório toString que retorna uma representação em string de route
     * @return uma string que representa route
     */
    @Override 
    public String toString() { return "Rota: "+route;}    

    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode(){
        int ret = 8;
        ret = ret * 7 + this.route.hashCode();
        ret = ret * 7 + this.in.hashCode();
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
        TxtRead txtRead = (TxtRead)obj;
        if(!this.route.equals(txtRead.route)) return false;       
        if(!this.in.equals(txtRead.in)) return false;
        return true;
    }

    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe TxtRead
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
     */
    public TxtRead (TxtRead modelo) throws Exception{
        if(modelo == null) throw new Exception("Modelo ausente");        
        this.route = modelo.route;
    }
    
    /**
     * Método clone que cria e retorna a copia de TxtRead
     * @return um clone da instancia
     */
    @Override
    public Object clone() {
        TxtRead ret = null;
        try {
            ret = new TxtRead(this);
        } catch (Exception e) {}
        return ret;
    }
}