package com.resync.auth.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String phoneNumber;

  private String countryCode;

  private boolean isPhoneVerified;

  @CreationTimestamp private Date createdAt;

  @UpdateTimestamp private Date updatedAt;

  public User(String phoneNumber, String countryCode) {
    this.phoneNumber = phoneNumber;
    this.countryCode = countryCode;
  }
}
