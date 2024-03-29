package Frontend;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FrontEndView {
    private final Timestamp waitTimeForRemovalFromView = new Timestamp(5000);
    private final ConcurrentHashMap<String, Timestamp> FrontEndView = new ConcurrentHashMap<>();

    public void adicionarNaFrontEndView(String porto, Timestamp momentoEmQueComunicou) {
        FrontEndView.put(porto, momentoEmQueComunicou);
    }

    public void ifNoResponseFromNodeForTooLongRemoveFromView() {
        for (Map.Entry<String, Timestamp> mapElement : FrontEndView.entrySet()) {
            String key = mapElement.getKey();
            Timestamp Value = mapElement.getValue();
            Timestamp Testingtimestamp = new Timestamp(System.currentTimeMillis());
            if ((Testingtimestamp.getTime() - Value.getTime()) > waitTimeForRemovalFromView.getTime()) {
                FrontEndView.remove(key);
            }
        }
    }

    public int fetchRandomNode() {
        Random rand = new Random();
        int valofnode = rand.nextInt((FrontEndView.size() - 1) + 1) + 1;
        int check = 1;
        for (Map.Entry<String, Timestamp> mapElement : FrontEndView.entrySet()) {
            String key = mapElement.getKey();
            if (check == valofnode) {
                return Integer.parseInt(key);
            }
            check++;
        }
        System.out.print("ERROR IN fetchRandomNode");
        return 0;
    }
}
