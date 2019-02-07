//Set Initial Show/Hide states of all elements
function setAllElementsInitialStates(){
	//1.Product
	setProductElementsInitialStates();
	
	//2.Fabric (Not Required)
	
	//3.Top Filling
	setTopFillingElementsInitialStates();
	
	//4.Bottom Discharge
	setBottomDischargeElementsInitialStates();
	
	//5.Loop
	setLoopElementsInitialStates();
	
	//6.Other
	setOtherElementsInitialStates();
}


//Set Initial Show/Hide states of product elements
function setProductElementsInitialStates(){
	$baffleValueDiv.hide();
	$productRemarksDiv.hide();
}

//Set Initial Show/Hide states of top filling
function setTopFillingElementsInitialStates(){
	$topFillingDetailsDiv.hide();
	$topSkirtDetailsDiv.show();
	$topSpoutDetailsDiv.hide();
	$topFlapDetailsDiv.hide();
	
	$topStandardTieNumberDiv.hide();
	$topRopeTieNumberDiv.hide();
	$topVelcroTieNumberDiv.hide();
	$topBLockNumberDiv.hide();
}

//Set Initial Show/Hide states of bottom discharge
function setBottomDischargeElementsInitialStates(){
	$bottomDischargeDetailsDiv.hide();
	$bottomSpoutDetailsDiv.show();
	
	$bottomStandardTieNumberDiv.hide();
	$bottomRopeTieNumberDiv.hide();
	$bottomVelcroTieNumberDiv.hide();
	$bottomBlockNumberDiv.hide();
}

//Set Initial Show/Hide states of Loop
function setLoopElementsInitialStates(){
	$loopProtectorValueDiv.hide();
}

//Set Initial Show/Hide states of Other
function setOtherElementsInitialStates(){
	//Other A
	$docPouchTypeDetailsDiv.hide();
	$otherLabelsValueDiv.hide();
	
	//Other B
	$printingDetialsDiv.hide();
	$linerDetailsDiv.hide();
	$linerTabbingNumberDiv.hide();
}