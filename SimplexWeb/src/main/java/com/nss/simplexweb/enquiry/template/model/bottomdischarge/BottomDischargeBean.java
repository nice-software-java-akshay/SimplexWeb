package com.nss.simplexweb.enquiry.template.model.bottomdischarge;

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
public class BottomDischargeBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean bottomDischarge;
	private ArrayList<BottomDischargeType> bottomDischargeType;
	private ArrayList<BottomType> bottomType;
	private BottomSpoutUnit bottomSpoutUnit;
	private StandardTie standardTie;
	private RopeTie ropeTie;
	private VelcroTie velcroTie;
	private BLock block;
	
}
