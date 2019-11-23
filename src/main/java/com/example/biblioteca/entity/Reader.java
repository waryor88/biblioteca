package com.example.biblioteca.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reader {
  @Id
  @Generated
  private Long id;

  private String externalId;

  private String fname;

  private String lname;

  private String tel;

  private String address;

  private String email;

}
