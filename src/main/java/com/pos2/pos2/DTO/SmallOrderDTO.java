package com.pos2.pos2.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SmallOrderDTO {

    private int id;

    private Date date;

    private Double value;

    private Double margin;

}
