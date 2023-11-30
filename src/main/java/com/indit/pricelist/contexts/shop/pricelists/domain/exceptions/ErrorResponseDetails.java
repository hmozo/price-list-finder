package com.indit.pricelist.contexts.shop.pricelists.domain.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDetails {
    private String code;
    private String description;
}
