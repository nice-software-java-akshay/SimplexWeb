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
		var URL_STR = contextRoot+'master/paymentTermsMaster/getPaymentTermsListByPartnerId';
		var paymentTermId = $(this).val();
		var $paymentTermSelect = $('.dual_select.payment-terms-select');
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
            		$paymentTermSelect.find('option').remove();
            		$('.dual_select').bootstrapDualListbox('refresh', true);

            		var PYAMENT_TERMS_LIST = data.PYAMENT_TERMS_LIST;
            		var PYAMENT_TERMS_LIST_FOR_PARTNER = data.PYAMENT_TERMS_LIST_FOR_PARTNER;
            		var TO_BE_SELECTED_TERMS_ARRAY = [];
            		
            		var PYAMENT_TERMS_LIST_LENGTH = 0;
            		var PYAMENT_TERMS_LIST_FOR_PARTNER_LENGTH = 0;
            		
            		if(PYAMENT_TERMS_LIST != null){
            			PYAMENT_TERMS_LIST_LENGTH = PYAMENT_TERMS_LIST.length;
            		}
            		
            		if(PYAMENT_TERMS_LIST_FOR_PARTNER != null){
            			PYAMENT_TERMS_LIST_FOR_PARTNER_LENGTH = PYAMENT_TERMS_LIST_FOR_PARTNER.length
            		}
            		
            		for(var i=0; i<PYAMENT_TERMS_LIST_LENGTH; i++){
            			debugger
            			for(var j=0; j<PYAMENT_TERMS_LIST_FOR_PARTNER_LENGTH; j++){
                			if(PYAMENT_TERMS_LIST[i].paymentTermId == PYAMENT_TERMS_LIST_FOR_PARTNER[j].paymentTermId){
                				TO_BE_SELECTED_TERMS_ARRAY.push(PYAMENT_TERMS_LIST_FOR_PARTNER[j].paymentTermId);
                			}
                		}
            		}
            		for(var i=0; i<PYAMENT_TERMS_LIST_LENGTH; i++){
        				if(jQuery.inArray(PYAMENT_TERMS_LIST[i].paymentTermId, TO_BE_SELECTED_TERMS_ARRAY)!='-1' ){
        					var $option_html = "<option selected value='"+ PYAMENT_TERMS_LIST[i].paymentTermId +"'>"+ PYAMENT_TERMS_LIST[i].paymentTermCode +"</option>";
                			$paymentTermSelect.append($option_html);
        				}else{
        					var $option_html = "<option value='"+ PYAMENT_TERMS_LIST[i].paymentTermId +"'>"+ PYAMENT_TERMS_LIST[i].paymentTermCode +"</option>";
                			$paymentTermSelect.append($option_html);
        				}
            		}
            		$('.dual_select').bootstrapDualListbox('refresh', true);
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
		
		if ($('.payment-terms-select option').length == 0) {
			$('.savePaymentTermAndPartnerMappingBtn').hide();
		}else{
			$('.savePaymentTermAndPartnerMappingBtn').show();
		}
	});
	
	$('#partnerSelect').trigger('change');
	
	$('.savePaymentTermAndPartnerMappingBtn').on('click', function() {
		/*var selectedPaymentTerms = [];
		var selectedUserId = $('#partnerSelect').val();
		
		var items = $('.payment-terms-select option:selected');
        $(items).each(function(index, brand){
        	selectedPaymentTerms.push($(this).val());
        });*/
		
		/*if ( $('.dual_select.payment-terms-select').children(":selected").val() === "" ) {
			toastr['error']("Nothing is selected", "Error");
		}*/
		
		
        
        $.ajax({
        	url:contextRoot + 'master/paymentTermsMaster/updatePaymentTermsForDistributer',
            type:'POST',
            data:$('#assignPaymentTermsForm').serialize(),
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
                	toastr['success']("Payment terms assigned to partner successfully", "Success");
                	setTimeout(function(){// wait for 2 secs
                        location.reload(false); // then reload the page.
                   }, 2000);
            	}catch (e) {
            		hideloader();
				}
            },
            error: function (request, status, error) {
            	console.log("assign-payment-terms-to-partner.js error : " + error);
            	toastr['error']("Error occurred", "Error");
            },
            complete:function(data){
                 // Hide loading image container
            	 hideloader();
            }
        });
	});
});