package com.tw.account.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Data     会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法
@Getter
@Setter
public class Account {
    @Id
    private int id;
    private String email;
    private String name;
    private String password;
}
