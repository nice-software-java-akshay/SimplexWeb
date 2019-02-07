package com.nss.simplexweb.enquiry.template.model.loop;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class LoopBean implements Serializable {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	private ArrayList<LoopType> loopType;
	private ArrayList<LoopMaterial> loopMaterial;
	private Long loopHeight;
	private ArrayList<LoopColor> loopColor;
	private boolean loopProtector;
	private ArrayList<LoopSewingType> loopSewingType;
}
