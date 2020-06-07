package com.tw.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data    // 会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法
public class Account {
    @Id
    private int id;
    private String email;
    private String name;
    private String password;
}
