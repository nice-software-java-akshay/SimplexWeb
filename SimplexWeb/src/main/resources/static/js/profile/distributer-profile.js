/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	//Set Country in Chosen Select
	/*var countryId = [[${USER.Country.CountryId}]];*/
	//$('.chosen-select').val(countryId).trigger('chosen:updated');
	//$('.chosen-select').trigger('chosen:updated');
	
	//Compulsory first line should be buildNavPath **
	buildNavPath({'Home':'user/home', 'Profile':'#'});
	
	toastr.options = TOASTER_OPTIONS;
	
	$('.chosen-select').chosen({
		width: "100%",
		max_selected_options: 3
	});
	
	$.validator.setDefaults({ ignore: ":hidden:not(.chosen-select)" });
	
	$.validator.addMethod(
	        "regex",
	        function(value, element, regexp) {
	            var re = new RegExp(regexp);
	            return this.optional(element) || re.test(value);
	        },
	        "Please check your input."
	);
	
    $("#personalInfoForm").validate({
    	errorPlacement: function(label, element) {
            label.addClass('validator_error_syle');
            label.insertAfter(element);
            if(element.attr("id") == "countrySelect"){
            	label.insertAfter("#countrySelect_chosen");
            }
        },
        rules: {
        	fullName: {
        		required: true,
        		regex : /^[a-zA-Z]{1,30}[\s,][a-zA-Z]{1,30}$/
        	},
            password: {
                required: true,
            },
            confirmPassword: {
            	required: true,
                equalTo: "#password"
             }
        }
    });
    
    var $inputImage = $("#inputImage");
    var $image = $("#profileImage");
    var $previewImage = $('#previewProfileImage');
    if (window.FileReader) {
        $inputImage.change(function() {
            var fileReader = new FileReader(),
                    files = this.files,
                    file;

            if (!files.length) {
                return;
            }
           
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
                fileReader.readAsDataURL(file);
                fileReader.onload = function () {
                    //$inputImage.val("");
                    $previewImage.attr('src', this.result);
                    doPreviewProfileImageVisible();
                    $('.set-as-profile-pic-check-btn').trigger('click');
                };
            } else {
                showMessage("Please choose an image file.");
            }
        });
    } else {
        $inputImage.addClass("hide");
    }
    
    function doPreviewProfileImageVisible () {
        var $image = $("#profileImage");
        var $previewImage = $('#previewProfileImage');
        var $previewModeSpan = $('.image-preview-mode-div');
        $('.set-as-profile-pic-check-btn').show();
        
        $image.addClass('hidden');
        $previewModeSpan.removeClass('hidden');
	}
    
    $('.set-as-profile-pic-check-btn').on('click', function() {
		$('#submitNewProfilePicForm').submit();
	});
    
    /*$('#fullName').on('keyup', function(){
    	var fullname = $(this).val();
    	debugger
    	var splittedfullname = fullname.split(' ');
    	if(splittedfullname.length > 0){
    		var firstname = splittedfullname[0];
    		$('#firstName').val(firstname);
    		$('#lastName').val('');
    		
    		if(splittedfullname.length > 1){
    			var lastname = splittedfullname[1];
    			$('#lastName').val(lastname);
    		}
    	}
    });*/
    
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