/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Master':'#', 'Distributer Master':'#'});
	
	toastr.options = TOASTER_OPTIONS;
	
	/*chosen-select*/
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$('.dataTables-example').DataTable({
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
    
        
    $.validator.setDefaults({ ignore: ":hidden:not(.chosen-select)" });
    $("#editDistributerForm").validate({
    	errorPlacement: function(label, element) {
            label.addClass('validator_error_syle');
            label.insertAfter(element);
            if(element.attr("id") == "countrySelectEdit"){
            	label.insertAfter("#countrySelectEdit_chosen");
            }
        },
	});
    
    
    $('.addNewDistributerBtn, .editDistributerBtn').on('click', function(){
    	var MODAL_TYPE = $(this).data('modal-type');
    	var MODAL_TITLE_NEW = "New Distributer";
    	var MODAL_SUBTITLE_NEW = "Please fill the required fields to add new distributer.";
    	var MODAL_TITLE_UPDATE = "Distributer Information";
    	var MODAL_SUBTITLE_UPDATE = "Shows the available details of selected distributer to edit.";
    	var DATA_MAP = null;    	
    	var $modalElement = $('#editDistributerModal');
		var $viewForm = $('#editDistributerForm');
    	
    	//Get information to display
    	var userid = $(this).data('userid');
    	if($(this).hasClass('editDistributerBtn')){
	    	$.ajax({
	    		url:contextRoot+'master/distributerMaster/getDistributerDetailsById',
	            type:'GET',
	            dataType:'html',
	            data:'userid='+userid,
	    		async:false,
	    		beforeSend: function(){
	    		    // Show loading image container
	    		    showloader();
	    		},
	            success:function(data) {
	            	 data = JSON.parse(data).DISTRIBUTER;
	            	 DATA_MAP = {
	                	'userId':data.userId,
	              		'fullName':data.fullName,
	              		'firstName':data.firstName,
	              		'lastName':data.lastName,
	              		'primaryContactNumber':data.primaryContactNumber,
	              		'secondaryContactNumber':data.secondaryContactNumber,
	              		'email':data.email,
	              		'companyName':data.companyName,
	              		'address':data.address,
	              		'dob':data.dob,
	              		'country.countryId':data.country.countryId,
	                 };
	            },
	            complete:function(data){
	                 // Hide loading image container
	            	 hideloader();
	            }
	         });
    	}
    	
    	setUpModelAccordingToType(MODAL_TYPE, $modalElement
								, $viewForm, DATA_MAP
								, MODAL_TITLE_NEW, MODAL_SUBTITLE_NEW
								, MODAL_TITLE_UPDATE, MODAL_SUBTITLE_UPDATE)
    });
    
    
    
	$('.saveDistBtn').on('click', function(e){
    	e.preventDefault();
    	var $modalElement = $('#editDistributerModal');
		var $viewForm = $('#editDistributerForm');
		var MODAL_TYPE = $modalElement.data('modal-type');
		var URL_STR = '';
		
		if(MODAL_TYPE == 'NEW'){
			URL_STR = contextRoot+'master/distributerMaster/addNewDistributer';
		}else{
			URL_STR = contextRoot+'master/distributerMaster/updateDistributer';
		}
		console.log($viewForm.serialize());
    	if(!$viewForm.valid()){
    		return false;
    	}else{
    		$.ajax( {
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
	                	$modalElement.modal('hide');
	                	toastr['success']("Distributer saved successfully", "Success");
	                	setTimeout(function(){// wait for 2 secs
	                        location.reload(false); // then reload the page.
	                   }, 2000);
                	}catch (e) {
                		hideloader();
					}
                },
                error: function (request, status, error) {
                	console.log("distributer-master.js error : " + error);
                	toastr['error']("Error occurred", "Error");
                },
                complete:function(data){
	                 // Hide loading image container
                	 hideloader();
                }
             });
    	}
    });
	
	$('.viewDistributerBtn').on('click', function(){
    	var userid = $(this).data('userid');
    	$.ajax({
            url:contextRoot+'master/distributerMaster/getDistributerDetailsById',
            type:'GET',
            dataType:'html',
            data:'userid='+userid,
    		async:false,
    		beforeSend: function(){
    		    // Show loading image container
    		    showloader();
    		},
            success:function(data) {
            	try{
		               data = JSON.parse(data).DISTRIBUTER;
		               var dataMap = {
		            		'mode':'view',
		            		'fullName':data.fullName,
		            		'firstName':data.firstName,
		            		'lastName':data.lastName,
		            		'primaryContactNumber':data.primaryContactNumber,
		            		'secondaryContactNumber':data.secondaryContactNumber,
		            		'email':data.email,
		            		'companyName':data.companyName,
		            		'address':data.address,
		            		'dob':data.dob,
		            		'country.countryName':data.country.countryName,
		            		'image':contextRoot + 'profile/getMyProfilePictureByUserId?userId=' + data.userId
		               };
		               var $viewDistributerForm = $('#viewDistributerForm');
		               var $viewDistributerModal = $('#viewDistributerModal');
		               buildViewForm($viewDistributerForm, dataMap, $viewDistributerModal);
		               $viewDistributerModal.modal('show');
            	}catch (e) {
            		console.log(e);
            		hideloader();
				}
            },
            complete:function(data){
                 // Hide loading image container
            	 hideloader();
            }
         });
    });
	
	$('.deleteDistributerBtn').on('click', function(){
		var userid = $(this).data('userid');
		var username = $(this).data('user-fullname');
		
		swal({
            title: "Are you sure, you want to delete " + username + "?",
            //text: "You will not be able to recover this imaginary file!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        }, function () {
        	$.ajax({
                url:contextRoot+'master/distributerMaster/deleteDistributer',
                type:'GET',
                dataType:'html',
                data:'userid='+userid,
        		async:false,
        		beforeSend: function(){
        		    // Show loading image container
        		    showloader();
        		},
                success:function(data) {
                	try{
		               if(data != undefined){
		            	   try{
		            		   swal("Deleted!", "Distributer " + username + " has been deleted.", "success");
			                	setTimeout(function(){// wait for 2 secs
			                        location.reload(); // then reload the page.
			                   }, 1000);
		                	}catch (e) {
		                		hideloader();
							}
		               }else{
		            	   swal("Error!", "Error occurred while deleting an distributer.", "error");
		               }
                	}catch (e) {
                		console.log(e);
                		hideloader();
    				}
                },
                complete:function(data){
                     // Hide loading image container
                	 hideloader();
                }
             });
            //swal("Deleted!", "Your imaginary file has been deleted.", "success");
        });
	});
    
    
    $('#firstName, #lastName').on('input', function(){
    	createFullName();
    });
    
    function createFullName(){
    	var $targetInput = $('#fullName');
    	var fname = $('#firstName').val().trim();
    	var lname = $('#lastName').val().trim();
    	
    	$targetInput.val(fname + ' ' + lname);
    }
        
   });