/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	toastr.options = TOASTER_OPTIONS;
	
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
    $("#editEmployeeForm").validate({
    	errorPlacement: function(label, element) {
            label.addClass('validator_error_syle');
            label.insertAfter(element);
            if(element.attr("id") == "deptSelectEdit"){
            	label.insertAfter("#deptSelectEdit_chosen");
            }
            if(element.attr("id") == "designationSelectEdit"){
            	label.insertAfter("#designationSelectEdit_chosen");
            }
            if(element.attr("id") == "countrySelectEdit"){
            	label.insertAfter("#countrySelectEdit_chosen");
            }
        },
	});
    
    
    $('.addNewEmpBtn, .editEmpBtn').on('click', function(){
    	var MODAL_TYPE = $(this).data('modal-type');
    	var MODAL_TITLE_NEW = "New Employee";
    	var MODAL_SUBTITLE_NEW = "Please fill the required fields to add new employee.";
    	var MODAL_TITLE_UPDATE = "Employee Information";
    	var MODAL_SUBTITLE_UPDATE = "Shows the available details of selected employee to edit.";
    	var DATA_MAP = null;    	
    	var $modalElement = $('#editEmployeeModal');
		var $viewForm = $('#editEmployeeForm');
    	
    	//Get information to display
    	var userid = $(this).data('userid');
    	if($(this).hasClass('editEmpBtn')){
	    	$.ajax( {
	    		url:contextRoot+'master/employeeMaster/getEmployeeDetailsById',
	            type:'GET',
	            dataType:'html',
	            data:'userid='+userid,
	    		async:false,
	    		beforeSend: function(){
	    		    // Show loading image container
	    		    showloader();
	    		},
	            success:function(data) {
	            	 data = JSON.parse(data).EMPLOYEE;
	            	 DATA_MAP = {
	                	'userId':data.userId,
	              		'empId':data.empId,
	              		'doj':data.doj,
	              		'fullName':data.fullName,
	              		'firstName':data.firstName,
	              		'lastName':data.lastName,
	              		'primaryContactNumber':data.primaryContactNumber,
	              		'secondaryContactNumber':data.secondaryContactNumber,
	              		'role.roleId':data.role.roleId,
	              		'role.dept.deptName':data.role.dept.deptName,
	              		'email':data.email,
	              		'address':data.address,
	              		'dob':data.dob,
	              		'country.countryId':data.country.countryId,
	              		'esicNum':data.esicNum,
	              		'pfNum':data.pfNum,
	              		'aadhaarNum':data.aadhaarNum,
	              		'panNum':data.panNum,
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
    
    
    
	$('.saveEmpBtn').on('click', function(e){
    	e.preventDefault();
    	var $modalElement = $('#editEmployeeModal');
		var $viewForm = $('#editEmployeeForm');
		var MODAL_TYPE = $modalElement.data('modal-type');
		var URL_STR = '';
		
		if(MODAL_TYPE == 'NEW'){
			URL_STR = contextRoot+'master/employeeMaster/addNewEmployee';
		}else{
			URL_STR = contextRoot+'master/employeeMaster/updateEmployee';
		}
		console.log($viewForm.serialize());
    	if(!$viewForm.valid()){
    		return false;
    	}else{
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
	                	$modalElement.modal('hide');
	                	toastr['success']("Employee saved successfully", "Success");
	                	setTimeout(function(){// wait for 2 secs
	                        location.reload(); // then reload the page.
	                   }, 2000);
                	}catch (e) {
                		hideloader();
					}
                },
                error: function (request, status, error) {
                	console.log("employee-master.js error : " + error);
                	toastr['error']("Error occurred", "Error");
                },
                complete:function(data){
	                 // Hide loading image container
                	 hideloader();
                }
             });
    	}
    });
	
	$('.viewEmpBtn').on('click', function(){
    	var userid = $(this).data('userid');
    	$.ajax({
            url:contextRoot+'master/employeeMaster/getEmployeeDetailsById',
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
		               data = JSON.parse(data).EMPLOYEE;
		               var dataMap = {
		            		'mode':'view',
		            		'empId':data.empId,
		            		'doj':data.doj,
		            		'fullName':data.fullName,
		            		'firstName':data.firstName,
		            		'lastName':data.lastName,
		            		'primaryContactNumber':data.primaryContactNumber,
		            		'secondaryContactNumber':data.secondaryContactNumber,
		            		'role.roleName':data.role.roleName,
		            		'role.dept.deptName':data.role.dept.deptName,
		            		'email':data.email,
		            		'address':data.address,
		            		'dob':data.dob,
		            		'country.countryName':data.country.countryName,
		            		'esicNum':data.esicNum,
		            		'pfNum':data.pfNum,
		            		'aadhaarNum':data.aadhaarNum,
		            		'pan':data.pan,
		               };
		               var $viewEmployeeForm = $('#viewEmployeeForm');
		               var $viewEmployeeModal = $('#viewEmployeeModal');
		               buildViewForm($viewEmployeeForm, dataMap, $viewEmployeeModal);
		               $viewEmployeeModal.modal('show');
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
	
	$('.deleteEmpBtn').on('click', function(){
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
                url:contextRoot+'master/employeeMaster/deleteEmployee',
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
		            		   swal("Deleted!", "Employee " + username + " has been deleted.", "success");
			                	setTimeout(function(){// wait for 2 secs
			                        location.reload(); // then reload the page.
			                   }, 2000);
		                	}catch (e) {
		                		hideloader();
							}
		            	   swal("Error!", "Error occurred while deleting an employee.", "error");
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
    
    $('#designationSelectEdit').on('change', function() {
    	$('#deptSelectEdit').val($(this).children("option:selected").data('deptname'));
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