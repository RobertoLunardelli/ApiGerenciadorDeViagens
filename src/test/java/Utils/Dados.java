package Utils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import com.github.javafaker.Faker;

public class Dados {
    String[] regioes = {"Sul","Norte","Centro-Oeste","Nordeste"};
    String[] cidades = {"Florianópolis","Porto Alegre","São Paulo","Rio de Janeiro"};
    Random random = new Random();
    int aleatorio = random.nextInt(3);
    private String nome;
    public Map cadastrarViagem(){
        Faker faker = new Faker(new Locale("YOUR_LOCALE"));
        Map<String, Object> params = new HashMap<>();
        nome = faker.name().fullName();
        params.put("acompanhante",nome);
        params.put("dataPartida","2021-08-11");
        params.put("dataRetorno","2021-08-15");
        params.put("localDeDestino",cidades[aleatorio]);
        params.put("regiao",regioes[aleatorio]);

        return params;


    }
    public String getNome(){
        return  nome;
    }
}
