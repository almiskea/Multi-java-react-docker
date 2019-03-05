package com.redis.watcher.domains;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "numbers")
public class Numbers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Numbers() {
    }

    public Numbers(long num) {
        this.num = num;
    }

    @Column(name = "num")
    private long num;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
