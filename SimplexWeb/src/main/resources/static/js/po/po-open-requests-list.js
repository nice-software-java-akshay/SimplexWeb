$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'PO':'#', 'Open Requests':'#'});
	
	
	$('body').on('click', '.viewPODetails, .processPO', function() {
		var poId = $(this).data('po-id');
		var poNumber = $(this).data('po-number');
		
		var VIEW_PO_DETAILS_URL = contextRoot + 'po/newPOController/getPODetailsBeforeProcessing?poId='+poId+'&poNumber='+poNumber;
		var PROCESS_PO_URL = contextRoot + 'po/newPOController/processNewPO?poId='+poId+'&poNumber='+poNumber;
		
		var URL = '';
		
		if($(this).hasClass('viewPODetails')){
			URL = VIEW_PO_DETAILS_URL;
		}else{
			URL = PROCESS_PO_URL;
		}
		
		window.location.href = URL;
		
	});
	
	function loadDataTable(){
		//var table = $('.dataTables-example').DataTable(); 
		var URL = contextRoot + 'po/newPOController/getOpenPORequestsList';
		$.ajax({
			url:URL,
            type:'GET',
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    //showloader();
    		},
            success:function(data) {
            	var DATA_SET = prepareTableDataSet(data);
            	if ($.fn.DataTable.isDataTable(".dataTables-example")) {
        		  $('.dataTables-example').DataTable().clear().destroy();
        		}
            	var table = $('.dataTables-example').DataTable({
        	        data: DATA_SET,
        	        columns: [
        	            { title: "#" },
        	            { title: "PO#" },
        	            { title: "Company" },
        	            { title: "Request By" },
        	            { title: "PO Date" },
        	            { title: "ETD" },
        	            { title: "Amount ($)" },
        	            { title: "Action" }
        	        ]
        	    });
            	
            	//var intervalID = setInterval(function(){table.ajax.reload();}, 3000);
            },
            complete:function(data){
                 // Hide loading image container
            	 //hideloader();
            }
         });
	}
	
	loadDataTable();
});

function prepareTableDataSet(PO_DETAIL){
	var DATA_SET = [];
	if(PO_DETAIL != ''){
		for(var i=0; i<PO_DETAIL.length; i++){
			var TABLE_ITEM = [];
			TABLE_ITEM.push(parseInt(i)+1);
			TABLE_ITEM.push(PO_DETAIL[i].poNumber);
			TABLE_ITEM.push(PO_DETAIL[i].requester.companyName);
			TABLE_ITEM.push(PO_DETAIL[i].requester.fullName);
			TABLE_ITEM.push(PO_DETAIL[i].poCreateTimestamp);
			TABLE_ITEM.push(PO_DETAIL[i].etd);
			TABLE_ITEM.push(PO_DETAIL[i].poTotalAmount);
			TABLE_ITEM.push(
				'<button type="button" data-po-id="'+PO_DETAIL[i].poId+'" data-po-number="'+PO_DETAIL[i].poNumber+'" class="btn btn-success btn-xs processPO" title="Process PO">' + 
					' <i class="fa fa-check"></i> ' + 
				' </button>' + 
				
				' <button type="button" data-po-id="'+PO_DETAIL[i].poId+'" data-po-number="'+PO_DETAIL[i].poNumber+'" class="btn btn-info btn-xs viewPODetails" title="View PO Detail">' + 
					' <i class="fa fa-eye"></i> ' +
				' </button>'
			);
			
			DATA_SET.push(TABLE_ITEM);
		}
	}
	return DATA_SET;
}