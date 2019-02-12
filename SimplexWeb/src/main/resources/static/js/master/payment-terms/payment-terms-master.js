$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Master':'#', 'Payment Terms':'#'});
	
	toastr.options = TOASTER_OPTIONS;
	
	var paymnet_terms_datatable = $('.dataTables-example').DataTable({
        pageLength: 25,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'csv',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'excel', title: 'Distributer List',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'pdf', title: 'Distributer List',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },

            {extend: 'print',
                 customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                                .addClass('compact')
                                .css('font-size', 'inherit');
                },
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            }
        ]
    });
	
	
	//Save Payment Term
	$('.savePaymentTermBtn').on('click', function(e) {
		e.preventDefault();
		var $modalElement = $('#newPaymentTermModal');
		var $viewForm = $('#newPaymentTermForm');
		var URL_STR = $viewForm.attr('action');
		
		if(!$viewForm.valid()){
			return false;
		}
		
    	$.ajax({
            url:URL_STR,
            type:'POST',
            dataType:'html',
            data:$viewForm.serialize(),
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
	               if(data != undefined){
	            	   try{
	            		   $modalElement.modal('hide');
	            		   swal("Saved!", "Payment Term has been saved.", "success");
		                	setTimeout(function(){// wait for 2 secs
		                        location.reload(); // then reload the page.
		                   }, 2000);
	                	}catch (e) {
	                		console.log(e);
	                		hideloader();
						}
	               }
            	}catch (e) {
            		console.log(e);
            		hideloader();
				}
            },
            complete:function(data){
                 // Hide loading image container
            	 hideloader();
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	swal("Error!", "Error occurred while saving payment term.", "error");
        	}
         });
	});
	
	//Edit payment term
	$('.editPaymentTermBtn').on('click', function(e) {
		e.preventDefault();
    	var $modalElement = $('#newPaymentTermModal');
		var $viewForm = $('#newPaymentTermForm');
		var MODAL_TYPE = $modalElement.data('modal-type');
		var paymentTermId = $(this).data('payment-term-id');
		var URL_STR = '';
		
		URL_STR = contextRoot+'master/paymentTermsMaster/getPaymentTermDetailsById';
		$.ajax({
            url:URL_STR,
            type:'GET',
            dataType:'html',
            data:'paymentTermId='+paymentTermId,
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
            		data = JSON.parse(data);
            		debugger
            		$viewForm.find('input[name="paymentTermId"]').val(data.paymentTermId);
            		$viewForm.find('input[name="paymentTermCode"]').val(data.paymentTermCode);
            		$viewForm.find('input[name="paymentTermDays"]').val(data.paymentTermDays);
            		$viewForm.find('textarea[name="paymentTermDesc"]').text(data.paymentTermDesc);
                	$modalElement.modal('show');
            	}catch (e) {
            		console.log(e);
				}
            },
            error: function (request, status, error) {
            	console.log(error);
            	//toastr['error']("Error occurred", "Error");
            },
            complete:function(data){
                 // Hide loading image container
            	 hideloader();
            }
         });
	});
	
	//Delete Payment Term
	$('.deletePaymentTermBtn').on('click', function(e) {
		var paymentTermCode = $(this).data('payment-term-code');
		var paymentTermId = $(this).data('payment-term-id');
		
		swal({
            title: "Are you sure, you want to delete " + paymentTermCode + "?",
            //text: "You will not be able to recover this imaginary file!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        }, function () {
        	$.ajax({
                url:contextRoot+'master/paymentTermsMaster/deletePaymentTermDetailsById',
                type:'GET',
                dataType:'html',
                data:'paymentTermId='+paymentTermId,
        		async:false,
        		beforeSend: function(){
        		    // Show loading image container
        		    showloader();
        		},
                success:function(data) {
                	try{
		               if(data != undefined){
		            	   try{
		            		   swal("Deleted!", "Payment Terms " + paymentTermCode + " has been deleted.", "success");
			                	setTimeout(function(){// wait for 2 secs
			                        location.reload(); // then reload the page.
			                   }, 2000);
		                	}catch (e) {
		                		console.log(e);
		                		hideloader();
							}
		               }
                	}catch (e) {
                		console.log(e);
                		hideloader();
    				}
                },
                complete:function(data){
                     // Hide loading image container
                	 hideloader();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                	swal("Error!", "Error occurred while deleting payment term.", "error");
            	}
             });
            //swal("Deleted!", "Your imaginary file has been deleted.", "success");
        });
	});
});