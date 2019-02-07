/**
 * Level One Calculations
 */
var defaultProtectorGSMValue=90;
var defaultTopGSMValue=60;
var defaultBottomGSMValue=80;
var defaultBaffelGSMValue=160;
var defaultFabricCoatedValue=30;
var defaultCoatedValue=20;
var defaultLinerMicronVal=75;
var defaultRainforcementVal=20;
var totalTopTie=0;
var totalBottomTie=0;
		
//Perform All Calculations
function performAllCalculations(){
	//1.Product
	productCalculations();
	
	//2.Fabric
	fabricCalculations();
	
	//3.Top Filling
	topFillingCalculations();
	
	//4.Bottom Discharge
	bottomDischargeCalculations();
	
	//5.Loop
	loopCalculations();
	
	//6.Other
	otherCalculations();
}

//calculations for Product
function productCalculations(){
	//1. Assign 0 if input left blank
	var assign_zero = [$surfaceLength, $surfaceWidth, $surfaceHeight];
	$.each(assign_zero, (index, $item) => {
		assignDefaultIfNull($item, 0);
	});
	
	//2. Unit MF
	multiplyAccordingToUnit[$surfaceLength, $surfaceWidth, $surfaceHeight];
}

//calculations for Fabric
function fabricCalculations(){
	
}

//calculations for Top Filling
function topFillingCalculations(){
	//1. Assign 0 if input left blank
	var assign_zero = [$topSkirtLength, $topSpoutDaimeter, $topSpoutLength];
	$.each(assign_zero, (index, $item) => {
		assignDefaultIfNull($item, 0);
	});
	
	//2. Unit MF
	multiplyAccordingToUnit[$topSkirtLength, $topSpoutDaimeter, $topSpoutLength];
}

//calculations for Bottom Discharge
function bottomDischargeCalculations(){
	//1. Assign 0 if input left blank
	var assign_zero = [$bottomSpoutDiameter, $bottomSpoutLength];
	$.each(assign_zero, (index, $item) => {
		assignDefaultIfNull($item, 0);
	});
	
	//2. Unit MF
	multiplyAccordingToUnit[$bottomSpoutDiameter, $bottomSpoutLength];
}

//calculations for Loop
function loopCalculations(){
	//1. Assign 0 if input left blank
	assignDefaultIfNull($loopHeight, 0);
	
	//2. Unit MF
	multiplyAccordingToUnit[$loopHeight];
}

//calculations for Other
function otherCalculations(){
	//1. Assign 0 if input left blank
	var assign_zero = [$linerTubeValue, $linerHeightValue];
	$.each(assign_zero, (index, $item) => {
		assignDefaultIfNull($item, 0);
	});
	
	//2. Unit MF
	multiplyAccordingToUnit[$linerTubeValue, $linerHeightValue];
}

/**
 * 
 * Misc/ Supporting/ Utility Functions --------------------------------------------------------------------------------------------
 * 
 */
//Assign default values if left null/blank be user
function assignDefaultIfNull($dom, defaultVal){
	//If DOM
	try{
		if ($dom.length > 0 && $dom[0] instanceof HTMLElement){
			if($dom.val().trim() == ''){
				$dom.val(defaultVal);
			}
		}else{
			if($dom == null || $dom.trim() == ''){
				$dom = defaultVal;
			}
		}
	}catch(e){
		console.log(e);
	}
}

//Multiply values with multi-factor according to unit
function multiplyAccordingToUnit(eleArray){
	if($productUnitTypeLength.find(':selected').data('abbr') == 'INCHES'){
		$.each(eleArray, (index, $item) => {
			multiplyByFactor($item, 2.54);
		});
	}else if($productUnitTypeLength.find(':selected').data('abbr') == 'MILLIMETER'){
		$.each(eleArray, (index, $item) => {
			multiplyByFactor($item, 0.1);
		});
	}
}

//Returns ($dom x mf) as float
function multiplyByFactor($dom, mf){
	//If DOM
	try{
		if ($dom.length > 0 && $dom[0] instanceof HTMLElement){
			if($dom.val().trim() == ''){
				$dom.val(parseFloat($dom.val()) * mf);
			}
		}else{
			if($dom == null || $dom.trim() == ''){
				$dom = parseFloat(defaultVal) * mf;
			}
		}
	}catch(e){
		console.log(e);
	}
}

//