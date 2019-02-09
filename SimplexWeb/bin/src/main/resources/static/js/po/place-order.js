$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'PO':'#', 'Place Order':'#'});
	
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
		
		table.row.add([
            '<input type="text" id="poItmesList'+newRowNumber+'.poItemNumber" name="poItmesList['+newRowNumber+'].poItemNumber" class="form-control invisible-text-input" style="width:100%">',
            '<input type="text" id="poItmesList'+newRowNumber+'.poItemDesc" name="poItmesList['+newRowNumber+'].poItemDesc" class="form-control invisible-text-input" style="width:100%">',
            '<input type="text" id="poItmesList'+newRowNumber+'.poItmeQty" name="poItmesList['+newRowNumber+'].poItmeQty" class="form-control invisible-text-input" style="width:100%">',
            '<input type="text" id="poItmesList'+newRowNumber+'.poItemRate" name="poItmesList['+newRowNumber+'].poItemRate" class="form-control invisible-text-input" style="width:100%">',
            '<input type="text" id="poItmesList'+newRowNumber+'.poItemAmount" name="poItmesList['+newRowNumber+'].poItemAmount" class="form-control invisible-text-input" style="width:100%">',
            
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
			for(i=table.rows().count(); i>0; i--){
				table.row( i ).remove().draw( false );
			}
		}else{
			//Enable the fields
			$('.po-table-fields').val('').removeAttr('disabled');
			$('.addPORowBtn').removeAttr('disabled');
			$('.removePORowBtn').removeAttr('disabled');
		}
	});
	
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