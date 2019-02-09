/**
 * 
 * Global Variable Initialization
 */
	
//Set global variable values
function setAllGlobalVariables(){
	//1. Product
	setProductGlobalVariables();
	
	//2. Fabric
	setFabricGlobalVariables();
	
	//3. Top Filling
	setTopFillingGlobalVariables();
	
	//4. Bottom Discharge
	setBottomDischargeGlobalVariables();
	
	//5.Loop
	setLoopGlobalVariables();
	
	//6.Other
	setOtherGlobalVariables();
}


//Assign DOM values to global variables for Product
function setProductGlobalVariables(){
	$productType = $('#productType');
    $productModelType = $('#productModelType');
    $productUnitTypeLength = $('#productUnitTypeLength');
    $productUnitTypeWeight = $('#productUnitTypeWeight');
    $baffleRadio = $("input[name='baffle']");
    $baffleValue = $('#baffleValue');
    $baffleValueDiv = $('.baffleValueDiv');
    $productSurfaceTypeRadio = $("input[name='productSurfaceType']");
    $surfaceLength = $('#surfaceLength');
    $surfaceWidth = $('#surfaceWidth');
    $surfaceHeight = $('#surfaceHeight');
    $swl = $('#swl');
    $unRadio = $("input[name='un']");
    $productSFtype = $('#productSFtype');
    $uvRadio = $("input[name='uv']");
    $productRemarksDiv = $('.productRemarksDiv');
    $productRemarks = $('#productRemarks');
}

//Assign DOM values to global variables for Fabric
function setFabricGlobalVariables(){
	$fabricType = $('#fabricType');
	$fabricColor = $('#fabricColor');
	$fabricGSMTypeRadio = $("input[name='fabricGSMType']");
	$fabricGSMValueDiv = $('.fabricGSMValueDiv');
	$fabricGSMValue = $('#fabricGSMValue');
	$reinforcementRadio = $("input[name='reinforcementRadio']");
	$fabricBagSeamType = $('#fabricBagSeamType');
	$fabricBagSeamColor = $('#fabricBagSeamColor');
}

//Assign DOM values to global variables for Top Filling
function setTopFillingGlobalVariables(){
	$topFillingRadio = $("input[name='topFilling']");
	$topFillingDetailsDiv = $('.topFillingDetailsDiv');
	    $topFillingTypeRadio = $("input[name='topFillingType']");
	    $topType = $('#topType');
		    $topSkirtDetailsDiv = $('.topSkirtDetailsDiv');
		    	$topSkirtLength = $('#topSkirtLength');
		    	$topSkirtGSM = $('#topSkirtGSM');
		    $topSpoutDetailsDiv = $('.topSpoutDetailsDiv');
			    $topSpoutDaimeter = $('#topSpoutDiameter');
			    $topSpoutLength = $('#topSpoutLength');
			    $topSpoutGSM = $('#topSpoutGSM');
		    $topFlapDetailsDiv = $('.topFlapDetailsDiv');
		    	$topFlapGSM = $('#topFlapGSM');
		    	
	    $topStandardTieRadio = $("input[name='topStandardTie']");
	    $topStandardTieNumberDiv = $('.topStandardTieNumberDiv');
	    $topStandardTieNumber = $('#topStandardTieNumber');
	    
	    $topRopeTieRadio = $("input[name='topRopeTie']");
	    $topRopeTieNumberDiv = $('.topRopeTieNumberDiv');
	    $topRopeTieNumber = $('#topRopeTieNumber');
	    
	    $topVelcroTieRadio = $("input[name='topVelcroTie']");
	    $topVelcroTieNumberDiv = $('.topVelcroTieNumberDiv');
	    $topVelcroTieNumber = $('#topVelcroTieNumber');
	    
	    $topBlockRadio = $("input[name='topBlock']");
	    $topBLockNumberDiv = $('.topBlockNumberDiv');
	    $topBLockNumber = $('#topBlockNumber');
}

//Assign DOM values to global variables for Bottom Discharge
function setBottomDischargeGlobalVariables(){
	$bottomDischargeRadio = $("input[name='bottomDischarge']");
	$bottomDischargeDetailsDiv = $('.bottomDischargeDetailsDiv');
		$bottomDischargeTypeRadio = $("input[name='bottomDischargeType']");
		$bottomType = $('#bottomType');
			$bottomSpoutDetailsDiv = $('.bottomSpoutDetailsDiv');
				$bottomSpoutDiameter = $('#bottomSpoutDiameter');
				$bottomSpoutLength = $('#bottomSpoutLength');
				$bottomSpoutGSM = $('#bottomSpoutGSM');
				
			$bottomStandardTieRadio = $("input[name='bottomStandardTie']");
			$bottomStandardTieNumberDiv = $('.bottomStandardTieNumberDiv');
			$bottomStandardTieNumber = $('#bottomStandardTieNumber');
    
			$bottomRopeTieRadio = $("input[name='bottomRopeTie']");
			$bottomRopeTieNumberDiv = $('.bottomRopeTieNumberDiv');
			$bottomRopeTieNumber = $('#bottomRopeTieNumber');
    
			$bottomVelcroTieRadio = $("input[name='bottomVelcroTie']");
			$bottomVelcroTieNumberDiv = $('.bottomVelcroTieNumberDiv');
			$bottomVelcroTieNumber = $('#bottomVelcroTieNumber');
    
			$bottomBlockRadio = $("input[name='bottomBlock']");
			$bottomBlockNumberDiv = $('.bottomBlockNumberDiv');
			$bottomBlockNumber = $('#bottomBlockNumber');
}

//Assign DOM values to global variables for Loop
function setLoopGlobalVariables(){
	$loopType = $('#loopType');
	$loopMaterial = $('#loopMaterial');
	$loopHeight = $('#loopHeight');
	$loopColor = $('#loopColor');
	$loopProtectorRadio = $("input[name='loopProtector']");
	$loopProtectorValueDiv = $('.loopProtectorValueDiv');
			$loopProtectorValue = $('#loopProtectorValue');
	$loopSewingType = $('#loopSewingType');
}

//Assign DOM values to global variables for Other
function setOtherGlobalVariables(){
	//Other A
		$perimeterBandRadio = $("input[name='perimeterBand']");
		$bellyBandRadio = $("input[name='bellyBand']");
		$steevdoorStrapRadio = $("input[name='steevdoorStrap']");
		
		$docPouchRadio = $("input[name='docPouch']");
			$docPouchTypeDetailsDiv = $('.docPouchTypeDetailsDiv');
				$docPouchType = $('#docPouchType');
				$docPoucDetailType = $('#docPoucDetailType');
				$docPouchValue = $('#docPouchValue');
				
		$otherLabelsRadio = $("input[name='otherLabels']");
			$otherLabelsValueDiv = $('.otherLabelsValueDiv');
				$otherLabelsValue = $('#otherLabelsValue');
		
	//Other B
			$printingRadio = $("input[name='printing']");
			
			$printingDetialsDiv = $('.printingDetailssDiv');
				$sides = $('#sides');
				$printingColor = $('#printingColor');
				
			$extraCleaningRadio = $("input[name='extraCleaning']");
			$metalDetectorRadio = $("input[name='metalDetector']");
			$packagingType = $('#packagingType');
			
			$linerRadio = $("input[name='liner']");
				$linerDetailsDiv = $('.linerDetailsDiv');
					$linerType = $('#linerType');
					$linerCalculationDiv = $('.linerCalculationDiv');
						$linerTubeValue = $('#linerTubeValue');
						$linerHeightValue = $('#linerHeightValue');
						$linerMacronValue = $('#linerMacronValue');
					$linerTabbingRadio = $("input[name='linerTabbing']");
						$linerTabbingNumberDiv = $('.linerTabbingNumberDiv');
						$linerTabbingNumber = $('#linerTabbingNumber');
					$linerGluedRadio = $("input[name='linerGlued']");
}