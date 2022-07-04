package com.application.types.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * A model(POJO) class for the type entity
 * 
 * Other possible approch to create a model class is to use the 
 * base entity with a few meta informations for each dataset 
 * like created date, last modification date and version for collision management
 * and then extend that class for all the model classes 
 *
 * @author Yashesh Patel
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "type")
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private long id;

	@Column(unique = true)
	@NotBlank(message = "Annotation can't be null or emplty")
	private String annotation;

	@Column
	private String operation;

	@Column
	private boolean comments;
	@Column
	private String segmentation;

	@Column(name = "patient_segment_length_in_paragraphs")
	private int patientSegmentLengthInParagraphs;

	@Column(name = "patient_segment_table_rows")
	private boolean patientSegmentTableRows;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "highlight_colour")
	private String highlightColour;

	@Column(name = "default_mask_value")
	private String defaultMaskValue;

	@Column(name = "highlight_colour_hex_code")
	private String highlightColourHexCode;

	@Column
	private String classification;

	@CreationTimestamp
	@Column(updatable = false, name = "date_created")
	Timestamp dateCreated;

	@UpdateTimestamp
	@Column(name = "date_modified")
	Timestamp lastModified;

}
