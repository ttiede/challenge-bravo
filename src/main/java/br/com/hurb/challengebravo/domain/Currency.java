package br.com.hurb.challengebravo.domain;


import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Currency")
public class Currency implements Serializable {
    String id;
    String name;
    String value;

    public Currency(String id, String name, String value) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }

}
