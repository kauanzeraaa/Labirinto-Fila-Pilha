import java.util.Scanner;

public class Main {
    public static void main(String[] args){        
        try{
            Character[][] maze;
            int linhas;
            int colunas;            
            Maze m;
            String line = "";            
            Scanner result = new Scanner(System.in);                        
            
            for(;;){
                System.out.println("Digite a rota do arquivo nesse modelo: LabirintosCorretos/Teste1.txt || LabirintosErrados/2entradas.txt");
               
                TxtRead file = new TxtRead(result.nextLine());
                linhas = Integer.parseInt(file.lerUmaLinha());
                colunas = Integer.parseInt(file.lerUmaLinha());
                maze = new Character[linhas][colunas];                
                int l = 0;
                while((line = file.lerUmaLinha()) != null){
                    for(int c = 0; c<colunas; c++){ 
                        maze[l][c] = line.charAt(c);
                    }
                    l++;
                }                       
                m = new Maze(linhas, colunas, maze);

                for (Character[] characters : maze) {
                    for (Character characters2 : characters) {
                        System.out.print(characters2);
                    }
                    System.out.println();
                }
                System.out.println("\n");
                
                Stack<Coordinate> x = (Stack<Coordinate>) m.caminhar().clone();
                for(;;) {
                    try {
                        System.out.print(x.removaUmItem());
                    } catch(Exception e){
                        break;
                    }
                }               
                System.out.println("");
                
                String options = "";                
                System.out.println("Deseja continuar: S/N");
                options = result.nextLine();

                switch(options.toLowerCase()){
                    case "s" : continue;
                    case "n":
                        result.close(); 
                        System.out.println("Programa encerrado");
                        System.exit(0);
                    break;
                    default: 
                        System.out.println("Inválido! Digite 's' para sim e 'n' para não");
                        System.out.println("Deseja continuar: S/N");
                        options = result.nextLine();                        
                        if(options.equals("n")) {
                            result.close();
                            System.out.println("Programa encerrado");
                            System.exit(0);
                        }                    
                }                
            }
        }catch(Exception e){
            //e.getMessage();
            System.err.println(e.getMessage());
        }
    }
}