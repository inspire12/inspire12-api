package com.inspire12.practice.api.presentation.controller.sample.ticker;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table
@IdClass(TickerTradeId.class)
public class TickerTrade {

    @Id
    @Comment(value = "거래일자")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate tradeDate;
    @Id
    @Comment(value = "종목코드")
    private String stockCode;
    @Comment(value = "종목 이름")
    private String stockName;
    @Comment(value = "상장된 시장(KOSPI/KOSDAQ)")
    private String stockMkt;
    @Comment(value = "시가 총액")
    private Long mktCap;
    @Comment(value = "종가( 해당 거래일 마지막 주식 가격)")
    private Long close;
    @Comment(value = "종가등락액 (직전 거래일 대비 등락액)")
    private Long netChg;
    @Comment(value = "종가등락율 (직전 거래일 대비 등락 비율)")
    private Double pctChg;

    @Comment(value = "딜리버리 날짜")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;


}
