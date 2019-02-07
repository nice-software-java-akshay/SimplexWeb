//Bind change events
function initElementsChangeEvents() {
	//1.Product
	productElementsChangeEvents();
	
	//2.Fabric (Not Required)
	//fabricElementsChangeEvents();
	
	//3.Top Filling
	topFillingElementsChangeEvents();
	
	//4.Bottom Discharge
	bottomDischargeElementsChangeEvents();
	
	//5.Loop
	loopElementsChangeEvents();
}

//Bind change events for product
function productElementsChangeEvents() {
	//1. Product Model Type onChange
		$productModelType.on('change', function(){
			var selectedModelValue = $(this).val();
			//1. If Q Bag/ Baffle selected
			if(selectedModelValue == 6){
				$baffleRadio.filter('[value="true"]').iCheck('check');
			}else{
				$baffleRadio.filter('[value="false"]').iCheck('check');
			}
		});
		
	//2. If baffle radio is selected as Yes
		$baffleRadio.on('ifChecked', function(){
			var baffleRadioVal = $(this).val();
			if(baffleRadioVal == 'true'){
				$baffleValueDiv.show();
				$baffleValue.attr('required','required');
			}else{
				$baffleValueDiv.hide();
				$baffleValue.val('');
				$baffleValue.removeAttr('required');
			}
		});
		
	//3. If UN radio is selected as No
		$unRadio.on('ifChecked', function(){
			var unRadioVal = $(this).val();
			if(unRadioVal == 'false'){
				$productSFtype.val(1).change();
			}
		});
		
	//4. If SF value is being changed, 
		//If UN is selected as No and user is trying to set SF as 5:1, block him from doing so
		$productSFtype.on('change', function(){
			var productSfValue = parseInt($(this).val());
			if(productSfValue == 2){	//6:1
				if($unRadio.filter(":checked").val() == 'false'){
					$productSFtype.val(1).change();
				}
			}
		});
		
		
	//4. If UV radio is selected as Yes
		$uvRadio.on('ifChecked', function(){
			var uvRadioVal = $(this).val();
			if(uvRadioVal == 'true'){
				$productRemarksDiv.show();
			}else{
				$productRemarksDiv.hide();
				$productRemarks.val('');
			}
		});
}

//Bind change events for top filling
function topFillingElementsChangeEvents(){
	//1. Top Filling radio button change event
		$topFillingRadio.on('ifChecked', function(){
			var topFillingRadioVal = $(this).val();
			if(topFillingRadioVal == 'true'){
				$topFillingDetailsDiv.show();
			}else{
				$topFillingDetailsDiv.hide();
			}
		});
		
	//2. Top type change events
		$topType.on('change', function(){
			var topTypeValue = parseInt($(this).val());
			switch (topTypeValue) {
			case 1:			//Top Skirt
				$topSkirtDetailsDiv.show();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.hide();
				break;
			case 2:			//Top Spout
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.show();
				$topFlapDetailsDiv.hide();
				break;
			case 3:			//Top Flap
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.show();
				break;
			case 4:			//Top skirt with Flap
				$topSkirtDetailsDiv.show();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.hide();
				break;
			case 5:			//Top spout with Flap
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.show();
				$topFlapDetailsDiv.hide();
				break;
			case 8:			//Conical top with Flap
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.show();
				break;
			case 9:			//Pleated top with Flap
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.show();
				break;
			default:
				$topSkirtDetailsDiv.hide();
				$topSpoutDetailsDiv.hide();
				$topFlapDetailsDiv.hide();
				break
			}
		});
		
		//3. Tie radio change event
		//3.1 Standard Tie
		$topStandardTieRadio.on('ifChecked', function(){
			var tieradioValue = $(this).val();
			if(tieradioValue  == 'true'){
				$topStandardTieNumberDiv.show();
			}else{
				$topStandardTieNumberDiv.hide();
			}
		});
		//3.2 Rope Tie
		$topRopeTieRadio.on('ifChecked', function(){
			var tieradioValue = $(this).val();
			if(tieradioValue  == 'true'){
				$topRopeTieNumberDiv.show();
			}else{
				$topRopeTieNumberDiv.hide();
			}
		});
		//3.3 Velcro Tie
		$topVelcroTieRadio.on('ifChecked', function(){
			var tieradioValue = $(this).val();
			if(tieradioValue  == 'true'){
				$topVelcroTieNumberDiv.show();
			}else{
				$topVelcroTieNumberDiv.hide();
			}
		});
		//3.4 BLock Tie
		$topBlockRadio.on('ifChecked', function(){
			var tieradioValue = $(this).val();
			if(tieradioValue  == 'true'){
				$topBLockNumberDiv.show();
			}else{
				$topBLockNumberDiv.hide();
			}
		});
}


//Bind change events for bottom discharge
function bottomDischargeElementsChangeEvents(){
	//1. Bottom Discharge radio button change event
		$bottomDischargeRadio.on('ifChecked', function(){
			var bottomDischargeRadioVal = $(this).val();
			if(bottomDischargeRadioVal == 'true'){
				$bottomDischargeDetailsDiv.show();
			}else{
				$bottomDischargeDetailsDiv.hide();
			}
		});
		
	//3. Tie radio change event
	//3.1 Standard Tie
	$bottomStandardTieRadio.on('ifChecked', function(){
		var tieradioValue = $(this).val();
		if(tieradioValue  == 'true'){
			$bottomStandardTieNumberDiv.show();
		}else{
			$bottomStandardTieNumberDiv.hide();
		}
	});
	//3.2 Rope Tie
	$bottomRopeTieRadio.on('ifChecked', function(){
		var tieradioValue = $(this).val();
		if(tieradioValue  == 'true'){
			$bottomRopeTieNumberDiv.show();
		}else{
			$bottomRopeTieNumberDiv.hide();
		}
	});
	//3.3 Velcro Tie
	$bottomVelcroTieRadio.on('ifChecked', function(){
		var tieradioValue = $(this).val();
		if(tieradioValue  == 'true'){
			$bottomVelcroTieNumberDiv.show();
		}else{
			$bottomVelcroTieNumberDiv.hide();
		}
	});
	//3.4 BLock Tie
	$bottomBlockRadio.on('ifChecked', function(){
		var tieradioValue = $(this).val();
		if(tieradioValue  == 'true'){
			$bottomBlockNumberDiv.show();
		}else{
			$bottomBlockNumberDiv.hide();
		}
	});
}

//Bind change events for loop
function loopElementsChangeEvents(){
	$loopProtectorRadio.on('ifChecked', function(){
		var tieradioValue = $(this).val();
		if(tieradioValue  == 'true'){
			$loopProtectorValueDiv.show();
		}else{
			$loopProtectorValueDiv.hide();
			$loopProtectorValue.val('');
		}
	});
}

//Bind change events for other
function loopElementsChangeEvents(){
	//Other A
		$docPouchRadio.on('ifChecked', function(){
			var docPouchRadioValue = $(this).val();
			if(docPouchRadioValue  == 'true'){
				$docPouchTypeDetailsDiv.show();
			}else{
				$docPouchTypeDetailsDiv.hide();
				$docPouchValue.val('');
			}
		});
		
		$otherLabelsRadio.on('ifChecked', function(){
			var otherLabelsRadioValue = $(this).val();
			if(otherLabelsRadioValue  == 'true'){
				$otherLabelsValueDiv.show();
			}else{
				$otherLabelsValueDiv.hide();
			}
		});
		
	//Other B
		$printingRadio.on('ifChecked', function(){
			var printingRadioValue = $(this).val();
			if(printingRadioValue  == 'true'){
				$printingDetialsDiv.show();
			}else{
				$printingDetialsDiv.hide();
			}
		});
		
		$linerRadio.on('ifChecked', function(){
			var linerRadioValue = $(this).val();
			if(linerRadioValue  == 'true'){
				$linerDetailsDiv.show();
			}else{
				$linerDetailsDiv.hide();
				$linerTubeValue.val('');
				$linerHeightValue.val('');
				$linerMacronValue.val('');
			}
		});
		
		$linerTabbingRadio.on('ifChecked', function(){
			var linerTabbingRadioValue = $(this).val();
			if(linerTabbingRadioValue  == 'true'){
				$linerTabbingNumberDiv.show();
			}else{
				$linerTabbingNumberDiv.hide();
			}
		});
}