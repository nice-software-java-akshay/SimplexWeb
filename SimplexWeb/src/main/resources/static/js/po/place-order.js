$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'PO':'#', 'Place Order':'#'});
	
	/*chosen-select*/
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	//Data table
	var table = $('.dataTables-example').DataTable({
        responsive: true,
        searching: false, paging: false, info: false,
        dom: '<"html5buttons"B>lTfgitp',
        columnDefs: [{
                targets: 5,
                className: 'text-center'
            }],
        buttons: [{
                text: 'Add Entry',
                className: 'addPORowBtn',
                /*action: function ( e, dt, node, config ) {
                    //alert( 'Button activated' );
                }*/
            }]
    });
	
	//Add PO row button
	$('body').on('click', '.addPORowBtn', function() {
		var newRowNumber = 0;
		if(table.rows().count() > 0){
			//debugger
			newRowNumber = parseInt($.parseHTML(table.rows(table.rows().count() - 1).data()[0][0])[0].name.replace('poItmesList[', '').replace('].poItemNumber', '')) + 1; 
		}
		
		var product_model_type_options = $('.PRODUCT_MODEL_TYPE_LIST_DUMMY_12345').html();
		table.row.add([
            '<input type="text" data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.poItemNumber" name="poItmesList['+newRowNumber+'].poItemNumber" class="form-control invisible-text-input" style="width:100%" required="required">',
            '<input type="text" data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.poItemDesc" name="poItmesList['+newRowNumber+'].poItemDesc" class="form-control invisible-text-input" style="width:100%">',
            '<select data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.productModelType" name="poItmesList['+newRowNumber+'].productModelType" class="form-control invisible-text-input" style="width:100%" required="required">'+ 
				product_model_type_options +
			'</select>',
            '<input type="text" data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.poItmeQty" name="poItmesList['+newRowNumber+'].poItmeQty" class="form-control invisible-text-input poItmeQty" style="width:100%" required="required">',
            '<input type="text" data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.poItemRate" name="poItmesList['+newRowNumber+'].poItemRate" class="form-control invisible-text-input poItemRate" style="width:100%" onc required="required"lick="">',
            '<input type="text" data-row-number="'+newRowNumber+'" id="poItmesList'+newRowNumber+'.poItemAmount" name="poItmesList['+newRowNumber+'].poItemAmount" class="form-control invisible-text-input" style="width:100%" readonly="readonly">',
            
            '<button type="button" class="btn btn-warning btn-xs addPORowBtn"> <i class="fa fa-plus"></i> </button>'+
            ' <button type="button" class="btn btn-warning btn-xs removePORowBtn"> <i class="fa fa-minus"></i> </button>'
        ]).draw( false );
		rearrangeTableRowsIndexes();
	});
		
	//Remove PO row button
	$('body').on('click', '.removePORowBtn', function() {
		if(table.row($(this).closest('tr')).index() != 0){	//Not a first row
			table.row($(this).closest('tr')).remove().draw( false );
			rearrangeTableRowsIndexes();
        }else{
        	alert('You can not delete this row');
        }
	});
	
	//Generate first row on page load
	$('.addPORowBtn').trigger('click');
	
	//Adjust text field width according typed text
	/*$('.invisible-text-input').on('keypress', function() {
		this.style.width = ((this.value.length + 1) * 8) + 'px';
	});*/
	
	//Skip po info checkbox
	$('#checkbox-skip-po-info').on('change', function() {
		if($(this). prop("checked") == true){
			//Disable the fields
			$('.po-table-fields').val('').attr('disabled', 'disabled');
			$('.addPORowBtn').attr('disabled', 'disabled');
			$('.removePORowBtn').attr('disabled', 'disabled');
			
			//Remove table rows excluding first row
			/*for(i=table.rows().count(); i>0; i--){
				table.row( i ).remove().draw( false );
			}*/
			
			//Remove all table rows
			table
				.rows()
			    .remove()
			    .draw();
		}else{
			//Enable the fields
			$('.po-table-fields').val('').removeAttr('disabled');
			$('.addPORowBtn').removeAttr('disabled');
			$('.removePORowBtn').removeAttr('disabled');
		}
	});
	
	/*$('.poItmeQty, .poItemRate').on('keyup', function(){
		debugger
		var thisRowNumber = parseInt($(this).data('row-number'));
		calculateAmount(thisRowNumber);
	});*/
	
	$(document).on('keyup', '.poItmeQty, .poItemRate', function(){
		debugger
		var thisRowNumber = parseInt($(this).data('row-number'));
		calculateAmount(thisRowNumber);
	});
	
	/*$('.poItemRate').on('keyup', function(){
		calculateAmount
	});*/
	
	function calculateAmount(rowNumber){
		var qty = parseFloat($('#poItmesList'+rowNumber+'\\.poItmeQty').val());
		var rate = parseFloat($('#poItmesList'+rowNumber+'\\.poItemRate').val());
		var $amount = $('#poItmesList'+rowNumber+'\\.poItemAmount');
		
		if(isNaN(qty)){
			qty = 0.0;
		}
		
		if(isNaN(rate)){
			rate = 0.0;
		}
		
		var calculated_amount = qty * rate;
		$amount.val(calculated_amount);
		
		//Calculate Total Amount
		calculateTotalAmount();
	}
	
	function calculateTotalAmount(){
		var rowCount = table.rows().count();
		var $totalAmount = $('#poTotalAmount');
		var totalAmount = 0.0;
		
		for(var rowNumber=0; rowNumber<rowCount; rowNumber++){
			var thisAmount = $('#poItmesList'+rowNumber+'\\.poItemAmount').val();
			if(isNaN(thisAmount)){
				thisAmount = 0.0;
			}
			totalAmount += parseFloat(thisAmount); 
		}
		
		$totalAmount.val(totalAmount);
	}
	
	//**important function**
	function rearrangeTableRowsIndexes(){
		var rowCount = table.rows().count();	//Total rows
		
		for(var i=0; i<rowCount; i++){
			var columnCount = table.rows(i).data()[0].length - 1;	//Total columns in current row
			
			for(var j=0; j<columnCount; j++){
				var oldName = $.parseHTML(table.rows(i).data()[0][j])[0].name;
				var oldId = oldName.replace('[', '').replace(']', '');
				
				var newName = oldName.replace(/\[([0-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|1000)\]/, "["+i+"]");	//0-1000 range
				var newId = newName.replace('[', '').replace(']', '');
				
				$('#'+oldId.replace('.', '\\.')).attr('name', newName);
				$('#'+oldId.replace('.', '\\.')).attr('id', newId);
				
			}
		}
	}
	
});