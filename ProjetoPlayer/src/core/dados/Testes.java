package core.dados;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

import core.negocio.ManipulaTransferencia;;

public class Testes {

	/**@remember Fazer métodos para atualizar as informações de downloads a cada segundo */
	public static void main(String[] args) {
		Hashtable <String, Double> tabela = new Hashtable<String, Double>(5);
		Hashtable <String, Double> abc = new Hashtable<String, Double>();
		
		ConcurrentHashMap <String, Integer> show = new ConcurrentHashMap<String, Integer>();
		
		ArrayList<Download> listaDownloads = new ArrayList<Download>();
		
		ManipulaTransferencia test = new ManipulaTransferencia();

		int pos=5;
		
		/**TODO à medida que chegar uma nova solicitação de download, deve coloca-lo no arrayList e ir atualizando p/ o cliente a cada segundo
		 * a transferência dos dados. */
		Download aux = new Download();
		
		for (int i=0; i<10; i++)
		{
			aux.setIP("192.354.");
			
			if (i*2 > 5)
				aux.setMusica("Eramos dois");
			
			aux.setTempoEstimado(2.3);
			
			test.inserirDownload(aux);
			
			aux.setAtivo(true);
			aux.setTamanho(500);
		}
		
		for (int i=0; i<10; i++)
			System.out.printf("Musica: %s\n", test.getLista().get(i).musica);
		
		/**
		tabela.put("aaaaa", 1.0);
		tabela.put("bbbb", 2.5);
		tabela.put("ccc", 3.5);
		tabela.put("ddd", 4.9);
		
		abc.put("aasdfas", 1.0);
		abc.put("hutruty", 2.7);
		abc.put("werter", 3.5);
		abc.put("hfhf", 4.1);
		
		System.out.printf("Quantidade de chaves em tabela: %d\n\n", tabela.size());
		
		System.out.printf("Chaves da tabela: %s\n\n", tabela.keySet().toString());
		tabela.putAll(abc);
		System.out.printf("Chaves da nova tabela: %s\n\n", tabela.keySet().toString());
		System.out.printf("Coleção de indices da tabela: %s\n\n", tabela.values().toString());
		
		show.put("aaaaa", 1);
		show.put("bbbb", 2);
		show.put("ccc", 3);
		show.put("ddd", 4);
		show.put("eee", 4);
		
		System.out.printf("Chaves do show: %s\n\n", show.keySet().toString());
		System.out.printf("Coleção: %s\n\n", show.values().toString());
		
		System.out.printf("A sua string está em: %s\n",tabela.get("bbbb").toString());
		System.out.printf("Tabela tem %d itens.\n",tabela.size());
		System.out.printf("Tabela e show são iguais? %b\n",tabela.equals(show));
		*/
	}

}
