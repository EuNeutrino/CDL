package br.com.cdl.util;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TesteBigDecimal {
     public static void main(String[] args) {
         BigDecimal valorTotal = new BigDecimal("100.00");
         BigDecimal numeroParcelas = new BigDecimal("7");
         BigDecimal valorParcelas;
         BigDecimal totalCalculado;
         BigDecimal diferenca;
         @SuppressWarnings("unused")
		BigDecimal valorUltimaParcela;
         for (int i=0; i < 7; i++) {
          valorParcelas = valorTotal.divide(numeroParcelas, 2, RoundingMode.HALF_UP);
          totalCalculado = valorParcelas.multiply(numeroParcelas);
          diferenca = valorTotal.subtract(totalCalculado);
          valorUltimaParcela = valorParcelas.add(diferenca);
         
          System.out.println("Valor Total            = " + valorTotal);
          System.out.println("número de parcelas     = " + numeroParcelas); 
          System.out.println("Valor das parcelas     = " + valorParcelas);
          System.out.println("Valor da diferença     = " + diferenca);
          System.out.println("");
                
          for (int j=0; j<numeroParcelas.intValue(); j++) {
           if (diferenca.signum() == 0 ||
             diferenca.signum() == 1 && j < (numeroParcelas.intValue() - 
               diferenca.multiply(new BigDecimal("100")).intValue())) {
            System.out.println("Valor das parcela " + (j+1) + " = " + valorParcelas);
           } else if (diferenca.signum() == -1) { 
            System.out.println("Valor das parcela " + (j+1) + " = " +
              valorParcelas.subtract(new BigDecimal("0.01")));
            diferenca = diferenca.add(new BigDecimal("0.01"));
           } else {
            System.out.println("Valor das parcela " + (j+1) + " = " +
              valorParcelas.add(new BigDecimal("0.01")));
           }
          } 
          System.out.println("");
          
          valorTotal = valorTotal.add(new BigDecimal("0.01"));
         }
     }
}

