$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'PO':'#', 'Open Requests': 'po/newPOController/getOpenPORequestsView', 'Update PO Status':'#'});

	
	/*chosen-select*/
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$('#poNumberSelect').on('change', function(){
		var poId = $(this).find('option:selected').val();
		var poNumber = $(this).find('option:selected').text();
		
		var GET_UPDATE_PO_STATUS_PAGE_URL = contextRoot + 'po/newPOController/getUpdatePOStatusPage?poId='+poId+'&poNumber='+poNumber;
		
		window.location.href = GET_UPDATE_PO_STATUS_PAGE_URL;
	});
	
	
	
});