package com.muze.api.auth.domain.Account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "like")
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = true)
    private String movieCd;

    @Column(nullable = true)
    private String genreNm;

    public Like(String movieCd, String genreNm) {
        this.movieCd = movieCd;
        this.genreNm = genreNm;
    }
}
