$(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Master':'#', 'Shipment Terms':'#'});
	
	toastr.options = TOASTER_OPTIONS;
	
	var shipment_terms_datatable = $('.dataTables-example').DataTable({
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
            {extend: 'excel', title: 'Shipment Terms List',
            	exportOptions: {
                    columns: 'th:not(:last-child)'
                }
            },
            {extend: 'pdf', title: 'Shipment Terms List',
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
	
	
	//Save Shipment Term
	$('.saveShipmentTermBtn').on('click', function(e) {
		e.preventDefault();
		var $modalElement = $('#newShipmentTermModal');
		var $viewForm = $('#newShipmentTermForm');
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
	            		   swal("Saved!", "Shipment Term has been saved.", "success");
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
            	swal("Error!", "Error occurred while saving shipment term.", "error");
        	}
         });
	});
	
	//Edit shipment term
	$('.editShipmentTermBtn').on('click', function(e) {
		e.preventDefault();
    	var $modalElement = $('#newShipmentTermModal');
		var $viewForm = $('#newShipmentTermForm');
		var MODAL_TYPE = $modalElement.data('modal-type');
		var shipmentTermId = $(this).data('shipment-term-id');
		var URL_STR = '';
		
		URL_STR = contextRoot+'master/shipmentTermsMaster/getShipmentTermDetailsById';
		$.ajax({
            url:URL_STR,
            type:'GET',
            dataType:'html',
            data:'shipmentTermId='+shipmentTermId,
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
            		data = JSON.parse(data);
            		debugger
            		$viewForm.find('input[name="shipmentTermId"]').val(data.shipmentTermId);
            		$viewForm.find('input[name="shipmentTermName"]').val(data.shipmentTermName);
            		$viewForm.find('input[name="shipmentTermDays"]').val(data.shipmentTermDays);
            		$viewForm.find('textarea[name="shipmentTermDesc"]').text(data.shipmentTermDesc);
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
	
	//Delete Shipment Term
	$('.deleteShipmentTermBtn').on('click', function(e) {
		var shipmentTermName = $(this).data('shipment-term-name');
		var shipmentTermId = $(this).data('shipment-term-id');
		
		swal({
            title: "Are you sure, you want to delete " + shipmentTermName + "?",
            //text: "You will not be able to recover this imaginary file!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        }, function () {
        	$.ajax({
                url:contextRoot+'master/shipmentTermsMaster/deleteShipmentTermDetailsById',
                type:'GET',
                dataType:'html',
                data:'shipmentTermId='+shipmentTermId,
        		async:false,
        		beforeSend: function(){
        		    // Show loading image container
        		    showloader();
        		},
                success:function(data) {
                	try{
		               if(data != undefined){
		            	   try{
		            		   swal("Deleted!", "Shipment Terms " + shipmentTermName + " has been deleted.", "success");
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
                	swal("Error!", "Error occurred while deleting shipment term.", "error");
            	}
             });
            //swal("Deleted!", "Your imaginary file has been deleted.", "success");
        });
	});
});