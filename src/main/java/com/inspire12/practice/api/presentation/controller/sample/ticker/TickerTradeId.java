package com.inspire12.practice.api.presentation.controller.sample.ticker;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickerTradeId implements Serializable {
    private LocalDate tradeDate;
    private String stockCode;
}
