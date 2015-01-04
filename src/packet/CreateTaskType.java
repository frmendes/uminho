package packet;

import java.util.HashMap;
import java.util.Map;

public class CreateTaskType extends Packet {
    //query
    public String q_name;
    public Map<String, Integer> q_itens;

    // special
    public Integer id;

    public CreateTaskType() {
        super();
        this.q_name = new String();
        this.q_itens = new HashMap<>();
        this.id = -1;
    }
}
