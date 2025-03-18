package com.pideep.tinyurl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings")
@Getter
@Setter
@NoArgsConstructor
public class URLMappingDB {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Long URL cannot be blank")
    @Column(nullable = false, unique = true)
    private String longURL;

    @Column(nullable = false, unique = true)
    private String shortURL;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public URLMappingDB(String longURL, String shortURL) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.createdAt = LocalDateTime.now();
    }
}
