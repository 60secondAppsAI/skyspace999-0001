package com.skyspace999.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CancellationDTO {

	private Integer cancellationId;

	private String reason;

	private Date cancellationDate;






}
