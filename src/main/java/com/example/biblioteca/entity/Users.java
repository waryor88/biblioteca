package com.example.biblioteca.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {


  @Id
  @GeneratedValue
  private Long id;

  private String userName;

  private String password;

  private String externalId;

  @OneToOne
  Reader reader;
}
