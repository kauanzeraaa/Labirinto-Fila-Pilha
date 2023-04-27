/**
 * Essa classe é uma implementação de uma estrutura de dados chamada fila (queue em inglês),
 * que segue o princípio FIFO (First-In-First-Out), onde o primeiro elemento que entra é o primeiro a sair
 * A classe é genérica, permitindo que a fila armazene elementos de qualquer tipo.
 * 
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
*/
public class Queue<X> implements Cloneable{
    Object[] elem;
    int cont = -1;

    /**
     * Cria uma fila com um tamanho definido pelo parâmetro index
     * @param index tamanho da fila
     * @throws Exception se o index for negativo
     */
    public Queue(int index)throws Exception{
        if(index <0) throw new Exception("O index nã pode ser negativo");
        elem = new Object[index];
    }
    
    /**
     * Verifica se a fila está vazia
     * @return true se a fila está vazia, false caso contrário
     */
    public boolean isEmpty(){
        if(cont == -1) return true;
        return false;
    }
    
    /**
     * Verifica se a fila está cheia
     * @return true se a fila estiver cheia, false caso contrário
     */
    public boolean isFull() {
        if(cont == elem.length) return true;
        return false;
    }
    
    /**
     * Adiciona um item ao final da fila
     * @param x Item a ser adicionado
     * @throws Exception se a fila(vetor) estiver cheia
     */
    public void guardeUmItem(X x) throws Exception{
        if(isFull()) throw new Exception("Vetor cheio");
        cont ++;
        elem[cont] = x;
    }
    
    /**
     * Remove e retorna o primeiro item da fila
     * @return O primeiro item da fila
     * @throws Exception se a fila estiver vazia
     */
    public X removaUmItem() throws Exception{
        if(isEmpty()) throw new Exception("Vetor nulo");        
        X ret = (X)elem[0];
        for(int i = 0; i <= cont-1; i++ ) elem[i] = elem[i+1];
        cont --;
        return ret;
    }

    /**
     * Retorna o primeiro item da fila sem removê-lo
     * @return O primeiro item da fila
     * @throws Exception se a fila estiver vazia
     */
    public X recupereUmItem() throws Exception{  
        if(isEmpty()) throw new Exception("O vetor está vazio");
        return (X)elem[0];
    }

    /**
     * Método obrigatório toString que retorna uma representação em string de elem
     * @return uma string que representa elem
     */
    @Override
    public String toString(){ return ""+elem[0];}

    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode(){
        int ret = 49;
        ret = ret*23 + this.elem.hashCode();
        ret = ret*23 + Integer.valueOf(cont).hashCode();
        if(ret<0) ret = -ret;
        return ret;
    }

    /**
     * Compara o objeto atual com o objeto especificado para determinar se são iguais
     * @param obj O objeto a ser comparado com o objeto atual
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Queue<X> q = (Queue<X>)obj;
        if(!this.elem.equals(q.elem)) return false;
        if(this.cont != q.cont) return false;
        return true;
    }

    /**
     * Constrói uma nova instância de Queue a partir do modelo fornecido,
     * copiando o valor dos atributos elem e cont do objeto modelo para o novo objeto
     * @param model o objeto Queue que servirá como modelo
     * @throws Exception se o objeto modelo for nulo
     */
    public Queue (Queue<X> model) throws Exception{
        if(model == null) throw new Exception("Modelo ausente");
        this.elem = model.elem;
        this.cont = model.cont;        
    }

    /**
     * Método clone que cria e retorna a copia de Fila
     * @return um clone da instancia
     */
    public Object clone(){
        Queue<X> ret = null;
        try{
            ret = new Queue<X>(this);
        }catch(Exception e){}
        return ret;
    }
}