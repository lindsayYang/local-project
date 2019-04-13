package com.lindsay.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author pengzai
 * @Description: 库存dto
 * @date 2019/2/19 上午9:19
 */
@Data
public class StockDto implements Serializable {

    private Long id;

    @JsonProperty("available_stock")
    private Double availableStock;

    @JsonProperty("lock_stock")
    private Double lockStock;


}
