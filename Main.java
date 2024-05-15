import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pilha a = new Pilha();
        Pilha estacao = new Pilha();
        Pilha b = new Pilha();

        //entrada de numero de vagoes
        Scanner scanner = new Scanner(System.in);
        int numeroVagoes = scanner.nextInt();
        
        //ordens que ele quer;
        // int[] resultConvertido = {2, 4, 6, 1, 5, 3};


        //pegar a ordem que o usuario quer e converter em uma lista de inteiros
        List<Integer> listaNumeros = new ArrayList<>();
        scanner.next();
        String entrada = scanner.nextLine();

        String[] partes = entrada.split("\\s+");

        for (String parte : partes) {
            try {
                int numero = Integer.parseInt(parte);
                listaNumeros.add(numero);
            } catch (NumberFormatException e) {
                System.out.println("Ignorando entrada inválida: " + parte);
            }
        }

        for (int numero : listaNumeros) {
            System.out.println(numero);
        }

        //pilha A
        for (int i = numeroVagoes; i>=1; i--){
            a.push(i);
        }

        int cont = 0;
        //logica de esvaziar a pilha A
        for(int i = 0; i< numeroVagoes; i++){
            int temp;
            if( listaNumeros.get(cont) == a.peek()) {
                temp = a.pop();
                estacao.push(temp);
                estacao.pop();
                b.push(temp);
                cont++;
            }
            else{
                temp = a.pop();
                estacao.push(temp);
            }
        }

        //logica de ver se a ordem é possível
        boolean possivel = true;
        while(cont < listaNumeros.size() && possivel){
            int temp;
            if(listaNumeros.get(cont) == estacao.peek()){
                temp = estacao.pop();
                b.push(temp);
                cont++;
            }
            else{
                possivel = false;
            }
        }

        if (possivel){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }

        scanner.close();
    }

}

 class No{
    private int info;
    private No proximo;


    public No (int info){
        setInfo(info);
        proximo = null;
    }

    public int getInfo() {
        return info;
    }

    public No getProximo() {
        return proximo;
    }

    public void setInfo(int info){
        this.info = info;
    }

    public void setProximo(No proximo){
        this.proximo = proximo;
    }

    @Override
    public String toString(){
        return "|" + info + "|->";
    }
}


 class Pilha {
    private No topo;
    //construtor padrão

    public boolean estaVazio(){
        return topo==null;
    }

    public void push(int i){
        No novo = new No(i);
        if (!estaVazio()) {
            novo.setProximo(topo);
        }
        topo = novo;
    }
    
    public int peek(){
        return topo.getInfo();
    }

    @Override
    public String toString() {
        String s = "pilha: ";
        if(estaVazio()){
            s += "vazia";
        }else{
            No aux = topo;
            while(aux!=null){ 
                s = s + aux + "\n";
                aux = aux.getProximo();
            }
            s = s + "\\\\";
        }
        return s;
    }

    public int pop(){
        int temp = topo.getInfo();
        topo = topo.getProximo();
        return temp;
    }

}