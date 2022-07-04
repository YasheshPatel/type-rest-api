package com.application.types.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * TypeDTO to encapsulate data and pass it
 * @author Yashesh Patel
 */
@Data
public class TypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(required = true)
	private String annotation;

	@JsonProperty(required = true)
	private String operation;

	@JsonProperty(required = true)
	private boolean comments;
	@JsonProperty(required = true)
	private String segmentation;

	@JsonProperty(required = true)
	private int patientSegmentLengthInParagraphs;

	@JsonProperty(required = true)
	private boolean patientSegmentTableRows;

	@JsonProperty(required = true)
	private String displayName;

	@JsonProperty(required = true)
	private String highlightColour;

	@JsonProperty(required = true)
	private String defaultMaskValue;

	@JsonProperty(required = true)
	private String highlightColourHexCode;

	@JsonProperty(required = true)
	private String classification;
}
