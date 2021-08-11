package Utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dados {
    String[] regioes = {"Sul","Norte","Centro-Oeste","Nordeste"};
    String[] nomes = {"Artur","Sofia","Clara","Evandro"};
    String[] cidades = {"Florianópolis","Porto Alegre","São Paulo","Rio de Janeiro"};
    Random random = new Random();
    int aleatorio = random.nextInt(3);
    public Map cadastrarViagem(){
        Random random = new Random();
        Map<String, Object> params = new HashMap<>();
        params.put("acompanhante",nomes[aleatorio]);
        params.put("dataPartida","2021-08-11");
        params.put("dataRetorno","2021-08-15");
        params.put("localDeDestino",cidades[aleatorio]);
        params.put("regiao",regioes[aleatorio]);

        return params;
    }
    public Map alterarViagem(){
        Map<String, Object> params = new HashMap<>();
        params.put("acompanhante","Eduardo");
        params.put("dataPartida","2021-08-11");
        params.put("dataRetorno","2021-08-15");
        params.put("localDeDestino","Florianópolis");
        params.put("regiao",regioes[aleatorio]);

        return params;
    }
}
