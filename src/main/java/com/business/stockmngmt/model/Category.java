package com.business.stockmngmt.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity{

    @Column(name = "codeCategory")
    String codeCategory;

    @Column(name = "label")
    String label;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    Category(Integer id, Instant CreationDate, Instant lastModifiedDate) {
        super(id, CreationDate, lastModifiedDate);
    }
}
