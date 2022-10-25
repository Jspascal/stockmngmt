package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Category;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    String codeCategory;

    String label;

    @JsonIgnore
    private List<ArticleDto> articles;

    /*
     * function that allow us to map from Category to CategoryDto
     *
     * @param Category category
     * */
    public static CategoryDto fromEntity (Category category) {
        if (category == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.CATEGORY_NOT_FOUND);

        }

        return CategoryDto.builder()
                .id(category.getId())
                .codeCategory(category.getCodeCategory())
                .label(category.getLabel())
                .build();
    }

    /*
     * function that allow us to map from CategoryDto to Category
     *
     * @param CategoryDto categoryDto
     * */
    public static Category toEntity (CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.CATEGORY_NOT_FOUND);

        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCodeCategory(categoryDto.getCodeCategory());
        category.setLabel(categoryDto.getLabel());

        return category;
    }
}
