package com.korchagin.java.task3;

import javax.persistence.*;

@Entity
@Table(name = "Lots")
public class Lots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lot_name")
    private String lotName;

    @Column(name = "current_rate")
    private Integer currentRate;

    @ManyToOne
    @JoinColumn(name = "last_user_id")
    private Users user;

    @Version
    private Long version;

    public Lots(){}

    public Lots(String lotName, Integer currentRate, Users user) {
        this.lotName = lotName;
        this.currentRate = currentRate;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getLotName() { return lotName; }
    public Integer getCurrentRate() { return currentRate; }
    public Users getUser() { return user; }
    public Long getVersion() { return version; }

    public void setLotName(String lotName) { this.lotName = lotName; }
    public void setCurrentRate(Integer currentRate) { this.currentRate = currentRate; }
    public void setUser(Users user) { this.user = user; }

    public void print(){
        System.out.printf("%d %s %d %d\n", id, lotName, currentRate, user.getId());
    }
}
