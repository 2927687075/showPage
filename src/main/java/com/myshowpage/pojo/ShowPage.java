package com.myshowpage.pojo;

import javax.persistence.*;

@Table(name = "showpage")
public class ShowPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="produce")
    private String produce;
    @Column(name="img")
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ShowPage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", produce='" + produce + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
