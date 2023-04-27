/**
 * A classe Stack implementa uma pilha genérica, utilizando um vetor de objetos
 * Ela também é Cloneable, permitindo a criação de cópias de objetos Stack
 * 
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */
public class Stack<X> implements Cloneable{
    Object[] elem;
    int cont = -1;

    /**
     * Construtor que recebe o tamanho do vetor de objetos e o inicializa
     * @param index O tamano do vetor de objetos
     * @throws Exception se caso o índice seja inválido (igual a -1)
     */
    public Stack(int index)throws Exception {
        if(index == -1 ) throw new Exception("Index inválido");
        this.elem = new Object[index];
    }

    /**
     * Verifica se a pilha esta vazia
     * @return true se a pilha estiver vazia e false caso contrario
     */
    public boolean isEmpty(){
        if(cont ==-1) return true;
        return false;
    }
    
    /**
     * Verifica se a pilha esta cheia
     * @return true se a pilha estiver cheia e false caso contrario
     */
    public boolean isFull(){
        if(cont == elem.length) return true;
        return false;
    }

    /**
     * Adiciona um item na pilha
     * @param x o item a ser adicionado
     * @throws Exception caso a pilha estiver cheia
     */
    public void guardeUmItem(X x)throws Exception{
        if(isFull()) throw new Exception("O vetor está com a capacidade máxima");
        cont ++;
        elem[cont] = x;
    }

    /**
     * Remove e retorna um item da pilha
     * @return o item removido da fila
     * @throws Exception caso a pilha esteja vazia
     */
    public X removaUmItem()throws Exception{
        if(isEmpty()) throw new Exception("Pilha vazia");
        X ret = this.recupereUmItem();
        elem[cont] = null;
        cont --;
        return ret;
    }

    /**
     * Retorna o ultimo item adicionado na pilha sem remove-lo
     * @return o ultimo item adicionado na pilha
     * @throws Exception caso a pilha esteja vazia
     */
    public X recupereUmItem() throws Exception{
        if(isEmpty()) throw new Exception("O vetor está vazio");
        return (X)elem[cont];
    }

    /**
     * Método obrigatório toString que retorna uma representação em string de elem
     * @return uma string que representa elem
     */
    @Override 
    public String toString(){
        if(isEmpty()) return "O valor é nulo";
        return ""+elem[cont];        
    }

    /** 
     * Verifica se um objeto passado como parametro é igual a esta instancia de Stack
     * @param obj O objeto a ser comparado com esta instância de Stack.
     * @return true caso os objetos sejam iguais e false caso contrário
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Stack<X> s = (Stack<X>)obj;
        if(!this.elem.equals(s.elem)) return false;
        if(this.cont != s.cont) return false;
        return true;
    }

    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode(){
        int ret = 49;
        ret = 23*ret + this.elem.hashCode();
        ret = 19*ret + Integer.valueOf(this.cont).hashCode();
        if(ret<0) ret = - ret;
        return ret;
    }

    /**
    Constrói uma nova instância de Queue a partir do modelo fornecido,
     * copiando o valor dos atributos elem e cont do objeto modelo para o novo objeto
     * @param model o objeto Queue que servirá como modelo
     * @throws Exception se o objeto modelo for nulo
     */
    public Stack (Stack<X> model) throws Exception{
        if(model == null) throw new Exception("Modelo ausente");
        this.elem = model.elem;
        this.cont = model.cont;
    }

    /**
     * Método clone que cria e retorna a copia de Labirinto
     * @return um clone da instancia
     */
    @Override
    public Object clone(){
        Stack<X> ret = null;        
        try{
            ret = new Stack<X>(this);
        }catch(Exception e){}        
        return ret;
    }
}