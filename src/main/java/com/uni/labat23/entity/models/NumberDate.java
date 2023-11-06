package com.uni.labat23.entity.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NumberDate {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;
}
