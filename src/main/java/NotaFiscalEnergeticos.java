import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class NotaFiscalEnergeticos {

    Scanner input = new Scanner(System.in);

    private static final double VALOR_UNIDADE = 4.50;
    private static final double ICMS_UNIDADE = VALOR_UNIDADE * 18/100;
    private static final double IPI_UNIDADE = VALOR_UNIDADE * 4/100;
    private static final double PIS_UNIDADE = VALOR_UNIDADE * (1.86/100);
    private static final double COFINS_UNIDADE = VALOR_UNIDADE * (8.54/100);

    Map<String, Integer> mapaComercio = new Hashtable<>();
    String nomeEstabelecimento;
    int quantidadeEnergerticos;

    public void calculoImpostos(int quantidadeEnergerticos){
        double valorComImpostos = ((quantidadeEnergerticos*ICMS_UNIDADE)+(quantidadeEnergerticos*IPI_UNIDADE)+
                (quantidadeEnergerticos*PIS_UNIDADE)+(quantidadeEnergerticos*COFINS_UNIDADE)+(quantidadeEnergerticos*VALOR_UNIDADE));

        System.out.printf("Valor SEM impostos: R$%.2f %n", (quantidadeEnergerticos*VALOR_UNIDADE));
        System.out.printf("Valor dos impostos: %nICMS: R$%.2f %nIPI: R$%.2f %nPIS: R$%.2f %nCOFINS: R$%.2f %nValor COM Impostos: R$%.2f %n%n",
                (quantidadeEnergerticos*ICMS_UNIDADE), (quantidadeEnergerticos*IPI_UNIDADE),(quantidadeEnergerticos*PIS_UNIDADE),
                (quantidadeEnergerticos*COFINS_UNIDADE), valorComImpostos);

        if(quantidadeEnergerticos >=500 && quantidadeEnergerticos<= 599){
            double totalGeralComDesconto = valorComImpostos - (valorComImpostos * 0.05);

            System.out.printf("Desconto de 5%% pela compra de %d energéticos %n", quantidadeEnergerticos);
            System.out.printf("Valor COM desconto: R$%.2f %n%n", totalGeralComDesconto);
        }
        else if(quantidadeEnergerticos >=800 && quantidadeEnergerticos<= 899){
            double totalGeralComDesconto = valorComImpostos - (valorComImpostos * 0.10);

            System.out.printf("Desconto de 10%% pela compra de %d energéticos %n", quantidadeEnergerticos);
            System.out.printf("Valor COM desconto: R$%.2f %n%n", totalGeralComDesconto);
        }
        else if(quantidadeEnergerticos >=1000){
            double totalGeralComDesconto = valorComImpostos - (valorComImpostos * 0.15);

            System.out.printf("Desconto de 15%% pela compra de %d energéticos %n", quantidadeEnergerticos);
            System.out.printf("Valor COM desconto: R$%.2f %n%n", totalGeralComDesconto);
        }
    }

    public void totalGeral(int quantidade){
        double totalImpostos = ((quantidade*ICMS_UNIDADE) + (quantidade*IPI_UNIDADE) +(quantidade*PIS_UNIDADE) +
                (quantidade*COFINS_UNIDADE));
        double totalMercadoria = (quantidade*VALOR_UNIDADE);
        double totalGeral = totalImpostos + totalMercadoria;

        System.out.printf("Total impostos: R$%.2f %n", totalImpostos);
        System.out.printf("Total Mercadoria: R$%.2f %n", totalMercadoria);
        System.out.printf("Total Geral: R$%.2f %n%n", totalGeral);
    }

    public void relatorioImpostos() {
        var resposta = 0;
        int quantidadeTotal = 0;

        do {
            System.out.println("Nome do estabelecimento: ");
            nomeEstabelecimento = input.nextLine();

            System.out.println("Quantidade de energéticos: ");
            quantidadeEnergerticos = input.nextInt();
            input.nextLine();

            mapaComercio.put(nomeEstabelecimento, quantidadeEnergerticos);

            System.out.println("Criar novo registro? Digite 1 para sim e 0 para não");
            resposta = input.nextInt();
            input.nextLine();

        } while(resposta == 1);

        for (Map.Entry<String, Integer> entry : mapaComercio.entrySet()) {
            String estabelecimento = entry.getKey();
            Integer quantidade = entry.getValue();
            quantidadeTotal += quantidade;

            System.out.println("Nome do Estabelecimento: " + estabelecimento);
            System.out.println("Quantidade de energéticos: " +quantidade + "\n");

            calculoImpostos(quantidade);
        }
        totalGeral(quantidadeTotal);
    }
}