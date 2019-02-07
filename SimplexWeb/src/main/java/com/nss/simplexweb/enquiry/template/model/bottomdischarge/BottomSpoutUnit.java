package com.nss.simplexweb.enquiry.template.model.bottomdischarge;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class BottomSpoutUnit implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long bottomSpoutDiameter;
	private Long bottomSpoutLength;
	private Long bottomSpoutGSM;
}
