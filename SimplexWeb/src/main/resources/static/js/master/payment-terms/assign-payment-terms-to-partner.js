$(function() {
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Master':'#', 'Payment Terms':'master/paymentTermsMaster', 'Assign To Partner':'#'});
	
	toastr.options = TOASTER_OPTIONS;
	
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$('.dual_select').bootstrapDualListbox({
        selectorMinimalHeight: 160
    });
	
	$('#partnerSelect').on('change', function() {
		debugger;
		var URL_STR = contextRoot+'master/paymentTermsMaster/getPaymentTermsListByPartnerId';
		var paymentTermId = $(this).val();
		$.ajax({
            url:URL_STR,
            type:'GET',
            contentType: "application/json; charset=utf-8",
            data:'partnerId='+paymentTermId,
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
            		debugger
            		//data = JSON.parse(data);
            		var PYAMENT_TERMS_LIST = data.PYAMENT_TERMS_LIST;
            		var PYAMENT_TERMS_LIST_FOR_PARTNER = data.PYAMENT_TERMS_LIST_FOR_PARTNER;
            		
            		for(var i=0; i<PYAMENT_TERMS_LIST.length; i++){
            			var option_html = "<option value='"+ PYAMENT_TERMS_LIST[i].paymentTermId +"'>"+ PYAMENT_TERMS_LIST[i]. +"</option>";
            		}
            	}catch (e) {
            		console.log(e);
				}
            },
            error: function (request, status, error) {
            	console.log(error);
            },
            complete:function(data){
                 // Hide loading image container
            	 hideloader();
            }
         });
	});
	
	$('#partnerSelect').trigger('change');
});