package com.stackroute.email.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlertMail {

    @Id
    public String clientName;
    private long count;
    private int positiveCount;
    private double positiveAverage;
    private int NegativeCount;
    private double NegativeAverage;
    private int NeutralCount;
    private double NeutralAverage;

}
